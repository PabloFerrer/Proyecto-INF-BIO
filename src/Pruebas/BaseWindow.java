package Pruebas;


import java.awt.*;
import java.io.IOException;

import javax.swing.*;

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
		calendar = new Calendar();
		calendar.setCurrentTime(DateTime.now());
		calendar.setDate(new DateTime(DateTime.now().getYear(), DateTime.now().getMonth(), DateTime.now().getDay()));
		
		
		content = new JPanel();
		content.setBackground(new Color(242, 242, 242));
		content.setLayout(new GridLayout(1, 1));
	
		
		setLayout(new BorderLayout());
		add(content);
	}
	
	
	public boolean save() {
		if (!calendar.getSchedule().getAllItems().isEmpty()) {
			if (id != null) {
				try {
					Conexion.sentenciaSQL("UPDATE agenda set data='"
							+ calendar.getSchedule().saveToString(ContentType.Xml) + "' where id=" + id + ";");
					return false;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				try {
					String aux = calendar.getSchedule().saveToString(ContentType.Xml);
					Conexion.sentenciaSQL("Insert Into agenda(data,fecha,dniMedico) values('" + aux + "',"
							+ Integer.parseInt(calendar.getDate().getYear() + ""
									+ (calendar.getDate().getMonth() < 10 ? "0" : "") + calendar.getDate().getMonth()
									+ "" + (calendar.getDate().getDay() < 10 ? "0" : "") + calendar.getDate().getDay())
							+ "," + me.getDni() + ");");
					return true;
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
		} else {
			if (id != null) {
				Conexion.sentenciaSQL("DELETE FROM agenda WHERE id=" + id + ";");
				return true;
			}
		}
		return false;
	}

	public void open() {
		Object data[]=Conexion.getAgenda(Integer.parseInt(calendar.getDate().getYear() + ""
				+ (calendar.getDate().getMonth() < 10 ? "0" : "") + calendar.getDate().getMonth()
				+ "" + (calendar.getDate().getDay() < 10 ? "0" : "") + calendar.getDate().getDay()), me.getDni());
		calendar.getSchedule().clear();
		id=null;
		if(data[1]!=null && (int)data[1]!=0) {
			id=(int) data[1];
			try {
				calendar.getSchedule().loadFromString(data[0].toString(), ContentType.Xml);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (calendar.getGroupType() != GroupType.None)
			{
				try {
					saved.loadFromString((String) data[0], ContentType.Xml);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				calendar.beginInit();
				calendar.getContacts().clear();
				calendar.getResources().clear();
				calendar.getLocations().clear();
				calendar.getTasks().clear();
				calendar.getContacts().addAll(calendar.getSchedule().getContacts());
				calendar.getResources().addAll(calendar.getSchedule().getResources());
				calendar.getLocations().addAll(calendar.getSchedule().getLocations());
				calendar.getTasks().addAll(calendar.getSchedule().getTasks());
				calendar.endInit();
			}
		}
	}


	private static final long serialVersionUID = 1L;

	protected Calendar calendar;
	protected JPanel content;

	public Medico getMe() {
		return me;
	}
}
