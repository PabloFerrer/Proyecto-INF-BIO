package View;


import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.datatransfer.*;
import java.awt.event.*;
import java.io.*;
import java.util.UUID;

import javax.swing.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

import org.w3c.dom.*;
import org.xml.sax.InputSource;

import com.mindfusion.common.*;
import com.mindfusion.scheduling.*;
import com.mindfusion.scheduling.model.*;

import Model.Medico;


public class MainWindow extends BaseWindow implements ClipboardOwner
{
	public MainWindow(Medico m)
	{
		super(m);
		// Calendar initialization start
		getCalendar().beginInit();
		getCalendar().setTheme(ThemeType.Standard);
		getCalendar().setCurrentView(CalendarView.Timetable);

		// Setup the dates displayed in the Schedule view
		DateTime today = DateTime.today();
		getCalendar().getTimetableSettings().getDates().clear();
		getCalendar().getTimetableSettings().getDates().add(today);
		getCalendar().endInit();
		// Calendar initialization end

		getCalendar().addCalendarListener(new CalendarAdapter() {
			@Override
			public void itemClick(ItemMouseEvent e) {
				onItemClicked(e);
			}

			@Override
			public void dateClick(ResourceDateEvent e) {
				onDateClicked(e);
			}
		});

		content.add(getCalendar());

		contextMenu = new JPopupMenu();
		contextMenu.setEnabled(true);
		
		mICopy = new JMenuItem("Copy");
		mICopy.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				onCopy();
			}
		});
		
		mIDelete = new JMenuItem("Delete");
		mIDelete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				onDelete();
			}
		});
		
		mIPaste = new JMenuItem("Paste");
		mIPaste.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				onPaste();
			}
		});
		
		contextMenu.add(mICopy);
		contextMenu.add(mIPaste);
		contextMenu.add(mIDelete);
	}
	
	public void lostOwnership(Clipboard clipboard, Transferable contents)
	{
	}

	protected void onCopy()
	{
		if (_clickedItem == null)
			return;

		// Copy the clicked item
		String value = serializeItem(_clickedItem);
		if (value != null)
		{
			value = ((int) (Math.random() * 900000)+100000)+"x"+((int) (Math.random() * 900000)+100000)+"p"+value;
			java.awt.Toolkit.getDefaultToolkit().getSystemClipboard().setContents(
				new StringSelection(value), this);
		}
	}

	protected void onPaste()
	{
		Clipboard clipboard = java.awt.Toolkit.getDefaultToolkit().getSystemClipboard();
		if (!clipboard.getContents(this).isDataFlavorSupported(DataFlavor.stringFlavor))
			return;

		String value;
		try
		{
			value = (String)clipboard.getData(DataFlavor.stringFlavor);
			if (value != null && value.length()>14 && value.charAt(13)=='p' && value.charAt(6)=='x') {
				value=value.substring(14);
			} else return;
		}
		catch (Exception ex)
		{
			return;
		}

		Item newItem = deserializeItem(value);
		if (newItem != null)
		{
			// Set the appropriate start and end time for the
			// newly created item
			Duration length = newItem.getEndTime().subtract(newItem.getStartTime());
			newItem.setStartTime(_clickedDate);
			newItem.setEndTime(_clickedDate.add(length));

			// Ensure the item has unique id
			newItem.setId(UUID.randomUUID().toString());

			// Add the item to the schedule
			getCalendar().getSchedule().getItems().add(newItem);
		}
	}
	
	protected void onDelete()
	{
		if (_clickedItem == null)
			return;

		// Copy the clicked item
		Item value = _clickedItem;
		if (value != null)
		{
			getCalendar().getSchedule().getAllItems().remove(value);
		}
	}

	private void onItemClicked(ItemMouseEvent e)
	{
		if (e.getButton() == MouseEvent.BUTTON3)
		{
			// Select the right-clicked item
			getCalendar().getItemSelection().clear();
			getCalendar().getItemSelection().add(e.getItem());

			// Remember the item
			_clickedItem = e.getItem();

			mICopy.setEnabled(true);
			mIPaste.setEnabled(false);
			mIDelete.setEnabled(true);
			
			// Pop-up the context menu
			Point where = MouseInfo.getPointerInfo().getLocation();
			where.x -= getCalendar().getLocationOnScreen().x;
			where.y -= getCalendar().getLocationOnScreen().y;
			contextMenu.show(getCalendar(), where.x, where.y);
		}
	}

	private void onDateClicked(ResourceDateEvent e)
	{
		if (e.getButton() == MouseEvent.BUTTON3)
		{
			// Select the right-clicked date
			getCalendar().getSelection().set(e.getDate(), DateTime.op_Addition(e.getDate(), getCalendar().getTimetableSettings().getCellTime()));
			// Remember the date
			_clickedDate = e.getDate();

			mICopy.setEnabled(false);
			mIPaste.setEnabled(false);
			mIDelete.setEnabled(false);

			// Update the menu
			Clipboard clipboard = java.awt.Toolkit.getDefaultToolkit().getSystemClipboard();
			try
			{
				Transferable dataObj = clipboard.getContents(this);
				if (dataObj.isDataFlavorSupported(DataFlavor.stringFlavor))
				{
					String value = (String)clipboard.getData(DataFlavor.stringFlavor);
					
					if (value != null && value.length()>14 && value.charAt(13)=='p' && value.charAt(6)=='x') {
						value=value.substring(14);
						Item it=deserializeItem(value);
						mIPaste.setEnabled(it != null);
					}
				}
			}
			catch (Exception exp)
			{
			}
			
			// Pop-up the context menu
			Point where = MouseInfo.getPointerInfo().getLocation();
			where.x -= getCalendar().getLocationOnScreen().x;
			where.y -= getCalendar().getLocationOnScreen().y;
			contextMenu.show(getCalendar(), where.x, where.y);
		}
	}

	private String serializeItem(Item item)
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document document;
		try
		{
			document = factory.newDocumentBuilder().newDocument();
		}
		catch (ParserConfigurationException e)
		{
			return null;
		}
        Element element = document.createElement("MyItem");
        document.appendChild(element);
		XmlSerializationContext context = new XmlSerializationContext(getCalendar().getSchedule(), document);
		item.saveTo(element, context);

		StringWriter writer = new StringWriter();
		StreamResult streamResult = new StreamResult(writer);

		DOMSource source = new DOMSource(document);
		try
		{
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
	        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-16");
	        transformer.transform(source, streamResult);
	
	        writer.flush();

	        return writer.toString();
		}
		catch (Exception ex)
		{
		}

		return null;
	}

	private Item deserializeItem(String value)
	{
		StringReader reader = new StringReader(value);
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document document;
		try
		{
			document = factory.newDocumentBuilder().parse(new InputSource(reader));
		}
		catch (Exception e)
		{
			return null;
		}
		XmlSerializationContext context = new XmlSerializationContext(getCalendar().getSchedule(), document);
        Element element = (Element)document.getFirstChild();

        Item item = new Appointment();
		item.loadFrom(element, context);

		return item;
	}


	private Item _clickedItem;
	private DateTime _clickedDate;
	private JPopupMenu contextMenu;
	private JMenuItem mIPaste;
	private JMenuItem mICopy;
	private JMenuItem mIDelete;

	private static final long serialVersionUID = 1L;
}
