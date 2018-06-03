package Control;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import com.panamahitek.ArduinoException;

import Model.MutableInt;
import Model.Paciente;
import Model.Tecnico;
import Model.Usuario;
import View.PanelPaciente;
import View.VentanaHelp;
import View.VentanaLogin;
import View.VentanaTecnico;
/**
 * ControladorTecnico es la clase que se encargara de ejercer como controlador de 
 * la clase VentanaTecnico. Su funcion es asignar una accion dependiendo del boton 
 * que se pulse, teniendo como opciones los botones "logout" y "help". Para mas 
 * informacion de la funcionalidad de cada boton, revisar el manual tecnico o 
 * el apartado de ayuda de la VentanaTecnico dentro de la aplicacion.
 * 
 * @author Heartlight
 * 
 * @version Final
 * 
 * @see PacienteTecnico
 * @see PanelPaciente
 * @see VentanaHelp
 * @see VentanaLogin
 * @see VentanaTecnico
 * 
 */
public class ControladorTecnico implements ActionListener,KeyListener {
	
	public static String LOGOUT ="LOGOUT";
	public static String HELP ="HELP";
	private VentanaTecnico vt;
	private ArrayList<Paciente> pacientes;
	private VentanaHelp help;
	private Usuario us;
	private Tecnico tec;
	
	/**
	 * Constructor de la clase ControladorTecnico
	 * @param vt VentanaTecnico 
	 */
	public ControladorTecnico (VentanaTecnico vt, Tecnico tec,ArrayList<Paciente> aux){
		this.vt = vt;
		this.us=us;
		pacientes=aux;
	}

	/**
	 * 	
	 * Metodo actionPerformed propio de un ActionListener
	 * @param a ActionEvent  
	 *
	 */
	public void actionPerformed(ActionEvent a) {
		String cmd=a.getActionCommand().toString();
		if(cmd.equals(LOGOUT)){
			try {
				if(vt.getFicha().getEcg().getIno()!=null)
					vt.getFicha().getEcg().getIno().killArduinoConnection();
			} catch (ArduinoException e) {
			}
			if(help!=null){
				help.dispose();
			}
			vt.dispose();
			VentanaLogin ven=new VentanaLogin();
			ControladorLogin con=new ControladorLogin(ven);
			ven.asignarControlador(con);
			ven.ver();
		} else if(cmd.equals(HELP)){
			  File fileH = new File("Resource/Usuarios/2.txt");
			  help=new VentanaHelp(3);
			  help.setAlwaysOnTop(true);
		        try (BufferedReader read = new BufferedReader(new FileReader(fileH))){
		            
		            String line = read.readLine();
		            
		            while(line !=null){
		                help.getText().append(line+" "+"\n");
		                line =read.readLine();
		            }
		            
		        } catch (Exception ex) {
		            
		        }
	}
}

	/**
	 * Metodo KeyPressed propio de un Keylistener
	 * @param e Keyevent  
	 */
	public void keyPressed(KeyEvent e) {
		//vt.mostrarPacientes(pacientes, vt.getBuscador().getText());
		
	}

	/**
	 * Metodo KeyReleased propio de un Keylistener
	 * @param arg0 Keyevent  
	 */
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * Metodo KeyTyped propio de un Keylistener el cual se encarga 
	 * mediante una concatenacion de sentencias condicionales de 
	 * permitir al usuario buscar un paciente a traves del campo 
	 * de texto que se encuentra en la VentanaTecnico
	 *  
	 * @param arg0 Keyevent 
	 */
	public void keyTyped(KeyEvent arg0) {

		String auxi = vt.getBuscador().getText().toString();

		if (Character.isAlphabetic(arg0.getKeyChar()) || Character.isDigit(arg0.getKeyChar())) {
			auxi = auxi + arg0.getKeyChar();
		}

		String[] aux0 = auxi.split(" ");
		Vector<String> aux = new Vector<String>();
		for (int i = 0; i < aux0.length; i++) {
			if (!aux0[i].equals(" ") || !aux0[i].equals("")) {
				aux.add(aux0[i]);
			}
		}
		
		
		MutableInt nombre=new MutableInt(0);
		MutableInt dni=new MutableInt(0);
		MutableInt fape=new MutableInt(0);
		MutableInt sape=new MutableInt(0);
		
		@SuppressWarnings("unchecked")
		ArrayList<Paciente> pacientes = ((ArrayList<Paciente>) this.pacientes.clone());
		for (int j = 0; j < aux.size(); j++) {
			for (int i = pacientes.size()-1; i >=0 ; i--) {
				Paciente pa = pacientes.get(i);
				if (((nombre.toInteger()==0)? (pa.getNombre().toLowerCase().startsWith(aux.get(j).toLowerCase())? aumentar(nombre):false):false)
						|| ((dni.toInteger()==0)?(pa.getDni().toLowerCase().toString().startsWith(aux.get(j).toLowerCase())? aumentar(dni):false):false)
						|| ((fape.toInteger()==0)?(pa.getApellido().toLowerCase().toString().split(" ")[0].startsWith(aux.get(j).toLowerCase())? aumentar(fape):false):false)
						|| ((pa.getApellido().split(" ").length>1)?((sape.toInteger()==0)?(pa.getApellido().toLowerCase().toString().split(" ")[1].startsWith(aux.get(j).toLowerCase())? aumentar(sape):false):false):false)
								) {
				} else {
					pacientes.remove(pa);
				}
			}
			}
	
		vt.getRey4().setVisible(false);
		if (aux.size() != 0) {
			vt.getRey4().removeAll();
			vt.getRey4().setLayout(new BoxLayout(vt.getRey4(), BoxLayout.Y_AXIS));
				for (int i = 0; i < pacientes.size(); i++) {
						PanelPaciente pan = new PanelPaciente(pacientes.get(i));
						pan.setBorder(new LineBorder(Color.gray, 2));

						ControladorPanel cp = new ControladorPanel(vt, pacientes.get(i),tec);
						pan.addMouseListener(cp);
						JLabel invi = new JLabel("lalalalalal");
						invi.setVisible(false);

						vt.getRey4().add(pan);
						vt.getRey4().add(invi);
					}
			
		} else {
			pacientes=this.pacientes;
			vt.getRey4().removeAll();
			vt.getRey4().setLayout(new BoxLayout(vt.getRey4(), BoxLayout.Y_AXIS));
			for (int i = 0; i < this.pacientes.size(); i++) {
				PanelPaciente pan = new PanelPaciente(this.pacientes.get(i));

				pan.setBorder(new LineBorder(Color.gray, 2));
				ControladorPanel cp = new ControladorPanel(vt, pacientes.get(i),tec);
				pan.addMouseListener(cp);
				JLabel invi = new JLabel("lalalalalal");
				invi.setVisible(false);

				vt.getRey4().add(pan);
				vt.getRey4().add(invi);

			}
		}
		
		if (pacientes.size() < 10) {
			for (int i = pacientes.size(); i < 10; i++) {
				PanelPaciente pan = new PanelPaciente(new Paciente(""," ", " ", " ",0,0), "");

				JLabel invi = new JLabel("lalalalalal");
				invi.setVisible(false);

				vt.getRey4().add(pan);
				vt.getRey4().add(invi);
			}

		}

		vt.getRey4().setVisible(true);
	}
	
	private boolean aumentar(MutableInt i) {
		i.setValue(i.toInteger()+1);
		return true;
	}

}
