package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Model.Conexion;
import Model.Constantes;
import Model.Mensaje;
import Model.Paciente;
import Model.Usuario;
import Model.Utilidades;
import View.VentanaMensajes;

public class ControladorMensaje implements ActionListener,ListSelectionListener{

	public ControladorMensaje(Paciente p,Usuario us) {
		super();
		this.p = p;
		this.us=us;
	}

	private VentanaMensajes ven;
	private Paciente p;
	private Usuario us;
	public static String ATRASMENS="ATRASMENS";
	public static String SENDMENS="SENDMENS";
	public static String MENSAJE ="MENSAJE";
	public static String NEWMENSAJE ="NEWMENSAJE";
	public static String ORDENAR="ORDENAR";
	private boolean newtoold=true;
	
	public void valueChanged(ListSelectionEvent e) {
		Mensaje men=((JList<Mensaje>) e.getSource()).getSelectedValue();
		if(e.getSource().equals(ven.getEnvlist())) {
			if(!ven.getList().isSelectionEmpty())
				ven.getList().clearSelection();;
		} else {
			if(!ven.getEnvlist().isSelectionEmpty())
				ven.getEnvlist().clearSelection();
		}
		if(men!=null) {
			if(men.getDniUsuario()!=us.getDni() && men.getLeido()!=Constantes.LEIDO) {
				men.setLeido(Constantes.LEIDO);
				Conexion.sentenciaSQL("Update mensaje set leido="+Constantes.LEIDO+" where id="+men.getId()+";");
			}
			ven.actInfo(men);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd=e.getActionCommand();
		if(cmd.equals(MENSAJE)) {
			System.out.println("HOLAAAAAAA");
			if(ven!=null) {
				ven.dispose();
			}
			
			ven=new VentanaMensajes(this);
			ven.VentanaMensajesTodos(p, this,us);
			ven.setVisible(true);
		} else if(cmd.equals(NEWMENSAJE)) {
			ven.mensajeNuevo(this);
		} else if(cmd.equals(ATRASMENS)) {
			ven.VentanaMensajesTodos(p, this,us);
		} else if(cmd.equals(SENDMENS)) {
			String mon=Calendar.getInstance().get(Calendar.MONTH)+"";
			if(mon.length()<2) {
				mon="0"+mon;
			}
			String day=Calendar.getInstance().get(Calendar.DATE)+"";
			if(day.length()<2) {
				day="0"+day;
			}
			Conexion.sentenciaSQL("Insert into mensaje(datos,leido,dniUsuario,dniPaciente,fecha,asunto) values('"+ven.getTexto().getText()+"',"
					+Constantes.NO_LEIDO+","+us.getDni()+","+p.getDni().substring(0, p.getDni().length()-1)+","+Integer.parseInt(Calendar.getInstance().get(Calendar.YEAR)+""+mon+""+day)+",'"
					+ven.getAsunto().getText()+"');");
			ven.VentanaMensajesTodos(p, this,us);
		} else if(cmd.equals(ORDENAR)) {
			char c;
			if(newtoold) {
				c='<';
				ven.getOrden().setText("v fecha");
			} else {
				ven.getOrden().setText("^ fecha");
				c='>';
			}
			newtoold=!newtoold;
		
			Mensaje[] mens=new Mensaje[0];
			mens=((Vector<Mensaje>) p.getMensajes()).toArray(mens).clone();
			Utilidades.mergesort(mens,0,mens.length-1,c);
			Vector<Mensaje> recibidos=new Vector<Mensaje>();
			Vector<Mensaje> enviados=new Vector<Mensaje>();
			for(int i=mens.length-1;i>=0;i--) {
				if(mens[i].getDniUsuario()==us.getDni()) {
					enviados.add(mens[i]);
				} else {
					recibidos.add(mens[i]);
				}
			}
			ven.getList().setListData(recibidos);
			ven.getEnvlist().setListData(enviados);
		}
		
	}

	public void setP(Paciente p) {
		this.p = p;
	}

	public void setUs(Usuario us) {
		this.us = us;
	}
	
	


}
