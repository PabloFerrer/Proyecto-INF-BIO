package View;


import java.awt.*;
import java.io.IOException;

import javax.swing.*;

import org.jsoup.Jsoup;

import com.mindfusion.common.*;
import com.mindfusion.scheduling.*;
import com.mindfusion.scheduling.model.*;

import Model.Conexion;
import Model.Medico;


public abstract class BaseWindow extends JPanel
{
	private Integer id;
	private Medico me;
	private Schedule saved;

	protected BaseWindow(Medico m)
	{
		me=m;
		setCalendar(new Calendar());
		getCalendar().setCurrentTime(DateTime.now());
		getCalendar().setDate(new DateTime(DateTime.now().getYear(), DateTime.now().getMonth(), DateTime.now().getDay()));
		
		
		content = new JPanel();
		content.setBackground(new Color(242, 242, 242));
		content.setLayout(new GridLayout(1, 1));
	
		
		setLayout(new BorderLayout());
		add(content);
	}
	
	
	public boolean save() {
		if (!getCalendar().getSchedule().getAllItems().isEmpty()) {
			
				try {
					String aux = getCalendar().getSchedule().saveToString(ContentType.Xml);
					Conexion.sentenciaSQL("Insert Into agenda(data,fecha,dniMedico) values('" + aux + "',"
							+ Integer.parseInt(getCalendar().getDate().getYear() + ""
									+ (getCalendar().getDate().getMonth() < 10 ? "0" : "") + getCalendar().getDate().getMonth()
									+ "" + (getCalendar().getDate().getDay() < 10 ? "0" : "") + getCalendar().getDate().getDay())
							+ "," + me.getDni() + ") ON DUPLICATE key UPdate data='"+aux+"';");
					return true;
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		} else {
				Conexion.sentenciaSQL("delete from agenda where dniMedico="+me.getDni()+" and fecha="+ Integer.parseInt(getCalendar().getDate().getYear() + ""
						+ (getCalendar().getDate().getMonth() < 10 ? "0" : "") + getCalendar().getDate().getMonth()
						+ "" + (getCalendar().getDate().getDay() < 10 ? "0" : "") + getCalendar().getDate().getDay())+";");
				return true;
		}
		return false;
	}

	public void open() {
		Object data[]=Conexion.getAgenda(Integer.parseInt(getCalendar().getDate().getYear() + ""
				+ (getCalendar().getDate().getMonth() < 10 ? "0" : "") + getCalendar().getDate().getMonth()
				+ "" + (getCalendar().getDate().getDay() < 10 ? "0" : "") + getCalendar().getDate().getDay()), me.getDni());
		getCalendar().getSchedule().clear();
		if(data[1]!=null && (int)data[1]!=0) {
			id=(int) data[1];
			try {
				getCalendar().getSchedule().loadFromString(data[0].toString(), ContentType.Xml);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (getCalendar().getGroupType() != GroupType.None)
			{
				try {
					saved.loadFromString((String) data[0], ContentType.Xml);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				getCalendar().beginInit();
				getCalendar().getContacts().clear();
				getCalendar().getResources().clear();
				getCalendar().getLocations().clear();
				getCalendar().getTasks().clear();
				getCalendar().getContacts().addAll(getCalendar().getSchedule().getContacts());
				getCalendar().getResources().addAll(getCalendar().getSchedule().getResources());
				getCalendar().getLocations().addAll(getCalendar().getSchedule().getLocations());
				getCalendar().getTasks().addAll(getCalendar().getSchedule().getTasks());
				getCalendar().endInit();
			}
		}
	}


	private static final long serialVersionUID = 1L;

	private Calendar calendar;
	protected JPanel content;

	public Medico getMe() {
		return me;
	}


	/**
	 * @return the calendar
	 */
	public Calendar getCalendar() {
		return calendar;
	}


	/**
	 * @param calendar the calendar to set
	 */
	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}
}
