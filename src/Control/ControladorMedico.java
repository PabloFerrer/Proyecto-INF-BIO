package Control;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Model.Conexion;
import Model.Constantes;
import Model.ECG;
import Model.Lectura;
import Model.Medico;
import Model.Paciente;
import Model.Usuario;
import Model.Utilidades;
import Pruebas.MemoCalendar;
import View.BuscadorMedico;
import View.CompararECG;
import View.Formulario;
import View.VentanaHelp;
import View.VentanaLogin;
import View.VentanaMedico;
import View.VentanaMedicoECG;
import View.VentanaMensajes;


/**
 * ControladorMedico es la clase que se encargara de ejercer como controlador de 
 * la clase VentanaMedico. Su funcion es asignar una accion dependiendo del boton 
 * que se pulse, teniendo como opciones los botones "inicio", "pacientes", "revisar ECGs"
 * ,"crear", "logout", "enviar", "cancelar" y finalmente "ayuda". Para mas informacion 
 * de la funcionalidad de cada boton, revisar el manual tecnico o el apartado de ayuda
 * de la VentanaMedico dentro de la aplicacion.
 * 
 * @author Heartlight
 * 
 * @version Final
 * 
 * @see Lectura
 * @see Medico
 * @see Usuario
 * @see BuscadorMedico
 * @see CompararECG
 * @see Formulario
 * @see VentanaHelp
 * @see VentanaLogin
 * @see VentanaMedico
 * @see VentanaMedicoECG
 */
public class ControladorMedico implements ActionListener,MouseListener,KeyListener{
	static public String INICIO= "INICIO";
	static public String ALTA="ALTA";
	static public String ECG="ECG";
	static public String PAC="PAC";
	public static String LOGOUT ="LOGOUT";
	public static String HELP ="HELP";
	public static String ENVIAR="ENVIAR";
	public static String CANCEL="CANCEL";
	public static String AGREGAR="AGREGAR";
	private String nombre = null;
	private FileInputStream fis;
	private File imagen;


	public static int s = 0;


	private VentanaMedico vm;
	private Formulario formulario=null;
	private VentanaLogin ven;
	private Medico med;
	private VentanaHelp help;
	private MemoCalendar memo;
	
	
	
	
	/**
	 * Primer constructor de la clase ControladorMedico
	 * @param vm VentanaMedico 
	 * @param us Medico 
	 */
	public ControladorMedico(VentanaMedico vm, Medico us,MemoCalendar memo) {
		this.memo=memo;
		this.vm=vm;
		med= us;
	}


	/**
	 * Metodo actionPerformed propio de un ActionListener
	 * @param e ActionEvent  
	 */
	public void actionPerformed(ActionEvent e) {
		if(memo!=null) {
			memo.getCalendar().save();
		}
		String cmd = e.getActionCommand().toString();
		if(cmd.equals(ControladorMedico.ALTA)) {
			vm.cleanButton();
			vm.getMenu().ladoDer();
			vm.getBtnDardeAlta().setBackground(new Color(51,153,255).darker());
			vm.getCentro().setVisible(false);
			vm.getCentro().removeAll();
			vm.getCentro().setVisible(true);
			
			for(int i=0;i<JFrame.getFrames().length;i++){
				if(JFrame.getFrames()[i] instanceof CompararECG)
					JFrame.getFrames()[i].dispose();
				else if(JFrame.getFrames()[i] instanceof VentanaMensajes)
					JFrame.getFrames()[i].dispose();
			}
			if(help!=null){
				help.dispose();
			}
			if(formulario!=null){
				formulario.dispose();
			}
			formulario= new Formulario();		
			JPanel a = new JPanel();
			a.setOpaque(false);
			vm.getCentro().add(a,BorderLayout.NORTH);
			vm.getCentro().add(formulario.paciente(this),BorderLayout.CENTER);
			
		}
		else if(cmd.equals(ControladorMedico.ECG)) {
			if(help!=null){
				help.dispose();
			}
			if(formulario!=null){
				formulario.dispose();
			}
			for(int i=0;i<JFrame.getFrames().length;i++){
				if(JFrame.getFrames()[i] instanceof CompararECG)
					JFrame.getFrames()[i].dispose();
				else if(JFrame.getFrames()[i] instanceof VentanaMensajes)
					JFrame.getFrames()[i].dispose();
			}
			vm.cleanButton();
			vm.getMenu().ningunExtremo();
			vm.getBtnRevisarEcg().setBackground(new Color(51,153,255).darker());
			vm.getCentro().setVisible(false);
			vm.getCentro().removeAll();
			for(int i=0;i<med.getPacientes().size();i++) {
				med.getPacientes().get(i).setEcgs(Conexion.consultaECG(med.getPacientes().get(i)));
			}
			VentanaMedicoECG ecg = new VentanaMedicoECG(vm,med);
			ecg.ver();
			vm.getCentro().add(ecg);
			vm.getCentro().setVisible(true);
		}
		else if (cmd.equals(ControladorMedico.INICIO)) {
			if(help!=null){
				help.dispose();
			}
			if(formulario!=null){
				formulario.dispose();
			}
			for(int i=0;i<JFrame.getFrames().length;i++){
				if(JFrame.getFrames()[i] instanceof CompararECG)
					JFrame.getFrames()[i].dispose();
				else if(JFrame.getFrames()[i] instanceof VentanaMensajes)
					JFrame.getFrames()[i].dispose();
			}
			
			vm.cleanButton();
			vm.getBtnInicio().setBackground(new Color(51,153,255).darker());
			vm.getCentro().setVisible(false);
			vm.getCentro().removeAll();
			vm.getMenu().ladoIzq();
			memo=new MemoCalendar(med);
			vm.getCentro().add(memo);
			vm.getCentro().setVisible(true);
		}
		else if (cmd.equals(ControladorMedico.PAC)) {
			if(help!=null){
				help.dispose();
			}
			if(formulario!=null){
				formulario.dispose();
			}
			for(int i=0;i<JFrame.getFrames().length;i++){
				if(JFrame.getFrames()[i] instanceof CompararECG)
					JFrame.getFrames()[i].dispose();
				else if(JFrame.getFrames()[i] instanceof VentanaMensajes)
					JFrame.getFrames()[i].dispose();
			}
			vm.getMenu().ningunExtremo();
			vm.cleanButton();
			vm.getBtnBuscarPacientes().setBackground(new Color(51,153,255).darker());
			vm.getCentro().setVisible(false);
			vm.getCentro().removeAll();
			BuscadorMedico bm = new BuscadorMedico();
			bm.crearVista(med,vm);
			vm.getCentro().add(bm);
			vm.getCentro().setVisible(true);
		}
		else if(cmd.equals(ControladorMedico.LOGOUT)){
			for(int i=0;i<JFrame.getFrames().length;i++){
				if(JFrame.getFrames()[i] instanceof CompararECG)
					JFrame.getFrames()[i].dispose();
				else if(JFrame.getFrames()[i] instanceof VentanaMensajes)
					JFrame.getFrames()[i].dispose();
			}
			
			if(help!=null){
				help.dispose();
			}
			if(formulario!=null){
				formulario.dispose();
			}
			vm.dispose();
			ven=new VentanaLogin();
			ControladorLogin con=new ControladorLogin(ven);
			ven.asignarControlador(con);
			ven.ver();
		} else if(cmd.equals(ControladorMedico.HELP)){
			if(help!=null) {
				help.dispose();
			}
			if(formulario!=null)
				formulario.dispose();
			for(int i=0;i<JFrame.getFrames().length;i++){
				if(JFrame.getFrames()[i] instanceof CompararECG)
					JFrame.getFrames()[i].dispose();
				else if(JFrame.getFrames()[i] instanceof VentanaMensajes)
					JFrame.getFrames()[i].dispose();
			}
			
			File fileH = new File("Resource/Usuarios/4.txt");
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
		else if(cmd.equals(ControladorMedico.CANCEL)) {
			if(formulario!=null)
				formulario.dispose();
			vm.getBtnInicio().doClick();
		}
		else if(cmd.equals(ControladorMedico.AGREGAR)){
			JFileChooser fc = new JFileChooser();
			fc.showOpenDialog(null);
			File f = fc.getSelectedFile();
			nombre = f.getAbsolutePath();
			
			try{
				
				imagen = new File(nombre);
				fis = new FileInputStream(imagen);
				
//				ByteArrayOutputStream bos = new ByteArrayOutputStream(); 
//				byte[] buf = new byte[1024];
//				
//				for(int leer; (leer=fis.read(buf))!=-1;){
//					bos.write(buf,0,leer);
//				}				
				
			}catch (Exception o){
				JOptionPane.showMessageDialog(null,o);
			}
			
		}
		else if(cmd.equals(ControladorMedico.ENVIAR)) {
			boolean bien=true;
			boolean comprobar = Conexion.dniPaciente(formulario);
			if(formulario.getNombre().getText().isEmpty()){
				formulario.getNombre().setBackground(Color.RED);
				bien=false;
			} else {
				formulario.getNombre().setBackground(Color.WHITE);
			}
			if(formulario.getApellido1().getText().isEmpty()){
				formulario.getApellido1().setBackground(Color.RED);
				bien=false;
			} else {
				formulario.getApellido1().setBackground(Color.WHITE);
			}
			if(formulario.getApellido2().getText().isEmpty()){
				formulario.getApellido2().setBackground(Color.RED);
				bien=false;
			} else {
				formulario.getApellido2().setBackground(Color.WHITE);
			}
			if(formulario.getDni().getText().isEmpty()){
				formulario.getDni().setBackground(Color.RED);
				bien=false;
			} else {
				formulario.getDni().setBackground(Color.WHITE);
			}
			if(formulario.getLugar().getText().isEmpty()){
				formulario.getLugar().setBackground(Color.RED);
				bien=false;
			} else {
				formulario.getLugar().setBackground(Color.WHITE);
			}
			if(formulario.getDireccion().getText().isEmpty()){
				formulario.getDireccion().setBackground(Color.RED);
				bien=false;
			} else {
				formulario.getDireccion().setBackground(Color.WHITE);
			}
			if(formulario.getSs().getText().isEmpty()){
				formulario.getSs().setBackground(Color.RED);
				bien=false;
			} else {
				formulario.getSs().setBackground(Color.WHITE);
			}if(comprobar==false){
				JOptionPane.showMessageDialog(null, "Ya existe un paciente con dni " + formulario.getDni().getText()
						+ ". Porfavor introduzca el dni correcto", "Error", JOptionPane.ERROR_MESSAGE);
				bien = false;
			}
			if(bien==true) {
				Conexion.crearPaciente(formulario, med,this);
				
					
			} else if(bien==false && comprobar == true){
				JOptionPane.showMessageDialog(null, "Por favor, rellene todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * Metodo KeyTyped propio de un Keylistener 
	 * @param e Keyevent 
	 */
	public void keyTyped(KeyEvent e) {
		if(e.getComponent().getName()!=null){
		if(e.getComponent().getName().equals("N")) {
			if(!Character.isDigit(e.getKeyChar())){
				e.consume();
			}
		} else if(e.getComponent().getName().equals("T")) {
			if(!Character.isAlphabetic(e.getKeyChar())){
				e.consume();
			}
		} 
	} else {
			if(!Character.isAlphabetic(e.getKeyChar()) && !Character.isDigit(e.getKeyChar())){
				e.consume();
			}
		}
	}

	/**
	 * Metodo KeyPressed propio de un Keylistener
	 * @param e Keyevent 
	 */
	public void keyPressed(KeyEvent e) {
	}

	/**
	 * Metodo KeyReleased propio de un Keylistener
	 * @param e Keyevent 
	 */
	public void keyReleased(KeyEvent e) {
	}

	/**
	 * Metodo MouseClicked propio de un Mouselistener que se encarga
	 * de que al hacer click se cambie el color del fondo a blanco.
	 * 
	 * @param e MouseEvent 
	 */
	public void mouseClicked(MouseEvent e) {
		e.getComponent().setBackground(Color.white);
		
		
	}

	/**
	 * Metodo MousePressed propio de un Mouselistener 
	 * @param e MouseEvent
	 */
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Metodo MouseReleased propio de un Mouselistener 
	 * @param e MouseEvent 
	 */
	public void mouseReleased(MouseEvent e) {
		
	}

	/**
	 * Metodo MouseEntered propio de un Mouselistener 
	 * @param e MouseEvent 
	 */
	public void mouseEntered(MouseEvent e) {
		
	}

	/**
	 * Metodo MouseExited propio de un Mouselistener 
	 * @param e MouseEvent 
	 */
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Metodo que se encarga de crear un archivo de texto asociado al paciente en cuestion
	 * en el cual introducira la informacion de cada paciente.
	 * 
	 * @param nombre String 
	 * @param apellido1 String 
	 * @param apellido2 String 
	 * @param dni String 
	 * @param ss String 
	 * @param poblacion String 
	 * @param direccion String 
	 * @param urgencia String 
	 */
	public void escribirPaciente(String nombre,String apellido1,String apellido2,String dni,String ss,String poblacion,String direccion,String urgencia){
		try(FileWriter aux=new FileWriter("Resource/Pacientes/"+nombre+".txt",true)){
			aux.write(nombre+";"+apellido1+""+apellido2+";"+dni+";"+ss+";"+poblacion+";"+direccion+"\r\n"+urgencia);
		}catch(Exception exc){
		}
		try(FileWriter aux=new FileWriter("Resource/Pacientes/pacientes.txt",true)){
			aux.write(nombre+";"+apellido1+""+apellido2+";"+dni+"\r\n");
		}catch(Exception exc){
		}
	}

	public FileInputStream getFis() {
		return fis;
	}
	
	public File getImagen() {
		return imagen;
	}

}


