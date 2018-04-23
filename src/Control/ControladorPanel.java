package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Model.Paciente;
import Model.Usuario;
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
	
	/**
	 * Constructor de la clase ControladorPanel
	 * @param vt VentanaTecnico 
	 * @param p PacienteTecnico 
	 */
	public ControladorPanel(VentanaTecnico vt,Paciente p){
		this.vt = vt;
		this.p=p;
		
	}
	
	/**
	 * Segundo constructor de la clase ControladorPanel
	 * @param vt VentanaTecnico 
	 * @param c ControladorAdmin 
	 * @param f JTextField 
	 */
	public ControladorPanel(Usuario vt,ControladorAdmin c,JTextField f){
		this.us = vt;
		this.c=c;
		fl=f;
		
	}
	
	/**
	 * Metodo actionPerformed propio de un ActionListener que elimina
	 * el usuario y coge el texto que hay en el JTextField
	 * 
	 * @param arg0 ActionEvent 
	 */
	public void actionPerformed(ActionEvent arg0) {
		c.getUsuario().remove(us);
		c.actPanel(fl.getText());
		
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
		
		if(respuesta!=null){
		if(respuesta.toLowerCase().equals(p.getDni().toLowerCase())){
			vt.getFicha().getLblNewLabel().setText(p.getNombre());
			vt.getFicha().getLblApellidos().setText(p.getApellido());
			vt.getFicha().getLblDni().setText(p.getDni());
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
