package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.panamahitek.ArduinoException;

import Model.Conexion;
import Model.Constantes;
import Model.Mensaje;
import Model.Paciente;
import Model.Tecnico;
import Model.Usuario;
import Model.Utilidades;
import View.VentanaMensajes;
import View.VentanaTecnico;

/**
 * Controlador que permite que cuando el usuario haga click en un paciente
 * en la VentanaTecnico le muestre en la ficha de dicho paciente la informacion
 * correspondiente. A su vez se encarga de hacer un doble check cuando se haga click
 * en uno de los pacientes de forma que el tecnico no se equivoque de paciente
 *  
 * @author Heartlight 
 * 
 * @version Final
 * 
 * @see PacienteTecnico
 * @see Usuario
 * @see VentanaTecnico
 * 
 */
public class ControladorPanel implements ActionListener, MouseListener {

	private VentanaTecnico vt;
	private Paciente p;
	private Usuario us;
	private ControladorAdmin c;
	private JTextField fl;
	private Vector<Usuario> elimi;
	
	/**
	 * Constructor de la clase ControladorPanel
	 * @param vt VentanaTecnico 
	 * @param p PacienteTecnico 
	 */
	public ControladorPanel(VentanaTecnico vt,Paciente p,Tecnico t){
		this.vt = vt;
		this.p=p;
		this.us=t;
		
	}
	
	/**
	 * Segundo constructor de la clase ControladorPanel
	 * @param vt VentanaTecnico 
	 * @param c ControladorAdmin 
	 * @param f JTextField 
	 */
	public ControladorPanel(Usuario vt,ControladorAdmin c,JTextField f,Vector<Usuario> elimi){
		this.us = vt;
		this.c=c;
		fl=f;
		this.elimi=elimi;
		
	}
	
	/**
	 * Metodo actionPerformed propio de un ActionListener que elimina
	 * el usuario y coge el texto que hay en el JTextField
	 * 
	 * @param arg0 ActionEvent 
	 */
	public void actionPerformed(ActionEvent arg0) {
		if(elimi.contains(us)) {
			elimi.remove(us);
			c.actPanel(fl.getText());
		} else {
			int resp = JOptionPane.showConfirmDialog(c.getA(), "Seguro que desea eliminar al usuario propietario del DNI: "+us.getDni()+Utilidades.letraDNI(us.getDni()), "Eliminacion de Usuario",JOptionPane.YES_NO_OPTION);
			if(resp==JOptionPane.YES_OPTION) {
				elimi.add(us);
				c.actPanel(fl.getText());
			} 
		}
	}
	
	/**
	 * Metodo mouseClicked propio de un MouseListener que permite realizar 
	 * el doble check y obtener los datos del paciente que se halla clickado
	 * y posteriormente dar dichos datos a la ficha de paciente.
	 * 
	 * @param arg0 MouseEvent 
	 */
	public void mouseClicked(MouseEvent arg0) {
		String respuesta = JOptionPane.showInputDialog("Escriba el DNI del paciente");
		vt.getFicha().MensajeCont(new ControladorMensaje(p,us));
		if(respuesta!=null){
		if(respuesta.toLowerCase().equals(p.getDni().toLowerCase())){
			try {
				if(vt.getFicha().getEcg().getIno()!=null)
					vt.getFicha().getEcg().getIno().killArduinoConnection();
			} catch (ArduinoException e) {
			}
			vt.getFicha().getBtnEnivar().setEnabled(false);
			vt.getFicha().getBtnStop().setEnabled(false);
			vt.getFicha().getBtnTomarDatos().setEnabled(true);
			vt.getFicha().getLblNewLabel().setText(p.getNombre());
			vt.getFicha().getLblApellidos().setText(p.getApellido());
			vt.getFicha().getLblDni().setText(p.getDni());
			vt.getFicha().getLblNewLabel_1().setImage((BufferedImage) p.getFoto());
			vt.getFicha().setP(p);
			vt.getFicha().getBtnEnivar().setEnabled(false);
			vt.getFicha().getEcg().cleanGraph();
			vt.getFicha().setVisible(true);
		} else {
			JOptionPane.showMessageDialog(vt, "El DNI introcido no concuerda con el del paciente seleccionado.", "Doble Confirmación fallida", JOptionPane.WARNING_MESSAGE);
		}
		}
		
		
		
		
	}

	/** 
	 * Metodo mouseEntered propio de un mouseListener
	 * 
	 */
	public void mouseEntered(MouseEvent arg0) {
	}

	/** 
	 * Metodo mouseExited propio de un mouseListener
	 * 
	 */
	public void mouseExited(MouseEvent arg0) {
	}

	/** 
	 * Metodo mousePressed propio de un mouseListener
	 * 
	 */
	public void mousePressed(MouseEvent arg0) {
	}

	/** 
	 * Metodo mouseReleased propio de un mouseListener
	 * 
	 */
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	


	
	

}
