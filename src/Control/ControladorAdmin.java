package Control;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JOptionPane;

import Model.Conexion;
import Model.Constantes;
import Model.Usuario;
import Model.Utilidades;
import View.Formulario;
import View.VentanaAdminPrincipal;
import View.VentanaHelp;
import View.VentanaLogin;

/**
 * @author HeartLight
 * 
 * Controlador que se encarga de las opciones que estan a la disposicion del tecnico
 * abriendo o añadiendo las ventanas/Jpanel necesarias para que el administrador navegue
 * por la aplicacion, tambien se encarga del control de los formularios para la creacion
 * de nuevos usuarios
 * 
 * @version Final
 * 
 * @see Component
 * @see VentanaAdminPrincipal
 * @see Formulario 
 * @see Usuario
 * @see VentanaHelp
 *
 */
public class ControladorAdmin  implements ActionListener,KeyListener,MouseListener{
	static public String CREARME="MEDICO";
	static public String CREARTEC="TECNICO";
	static public String OFF="OFF";
	static public String HELP="HELP";
	static public String ENVIARME="ENVIARME";
	static public String ENVIARTEC="ENVIARTEC";
	static public String CANCEL="CANCEL";
	static public String USUARIOS="USUARIOS";
	static public String BACK="BACK";
	
	private Component aux[];
	private VentanaAdminPrincipal a;
	private Formulario aux1=null;
	private Vector<Usuario> usuario;
	private VentanaHelp help;
	private Vector<Usuario> elimi;
	private Vector<String> querys = new Vector<String>();	
	
	/**
	 * @return the querys
	 */
	public Vector<String> getQuerys() {
		return querys;
	}

	/**
	 * Getter de un vector que contiene todos los usuarios
	 * @return Vector de Usuario
	 */
	public Vector<Usuario> getUsuario() {
		return usuario;
	}
	
	/**
	 * Constructor para el control y asignacion de otros controladores a VentanaAdminPrincipal
	 * @param ax VentanaAdminPrincipal a la que se le ejercerá el control
	 */
	public ControladorAdmin(VentanaAdminPrincipal ax){
		elimi=new Vector<Usuario>();
		usuario=Conexion.consultarUsuarios();
		this.a=ax;
		a.getBuscador().addKeyListener(new KeyListener(){
			public void keyTyped(KeyEvent e) {
				String aux=a.getBuscador().getText();
				if(Character.isAlphabetic(e.getKeyChar()) || Character.isDigit(e.getKeyChar())){
					aux=aux+e.getKeyChar();
				}
					actPanel(aux);
				
				
			}
			public void keyPressed(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {}
			
		});
	}
	/**
	 * Constructor para el controlador de un Formulario
	 * @param a Formulario al que se ejercerá un control
	 */
	public ControladorAdmin(Formulario a){
		this.aux1=a;
		usuario=Conexion.consultarUsuarios();
	}
	
	/**
	 * Metodo encargado de realizar una accion dependiendo del 
	 * elemento con el que interactue el usuario, en este metodo se incluyen
	 * el logout, ayuda, control de paciente ademas de los
	 *  formularios y su control.
	 */
	public void actionPerformed(ActionEvent e) {
		String cmd=e.getActionCommand().toString();
		if(cmd.equals(ControladorAdmin.OFF)){
			if(aux1!=null)
				aux1.dispose();
			if(help!=null)
				help.dispose();
			a.dispose();
			VentanaLogin ven=new VentanaLogin();
			ControladorLogin con=new ControladorLogin(ven);
			ven.asignarControlador(con);
			ven.ver();
		} else if(cmd.equals(ControladorAdmin.HELP)){
			if(help!=null)
				help.dispose();
			if(aux1!=null)
				aux1.dispose();
			
			  File fileH = new File("Resource/Usuarios/3.txt");
			  help=new VentanaHelp(3);
			  help.setAlwaysOnTop(true);
		        try(BufferedReader read = new BufferedReader(new FileReader(fileH))) {
		            
		            String line = read.readLine();
		            
		            while(line !=null){
		                help.getText().append(line+" "+"\n");
		                line =read.readLine();
		            }
		            
		        } catch (Exception ex) {
		            
		        }
		} else if(cmd.equals(CREARME)){
			if(aux1!=null)
				aux1.dispose();
			if(help!=null)
				help.dispose();
			aux1=new Formulario();
			aux1.medico(this);
			aux1.ver();
		} else if(cmd.equals(CREARTEC)){
			if(aux1!=null)
				aux1.dispose();
			if(help!=null)
				help.dispose();
			 aux1=new Formulario();
			 aux1.tecnico(this);
			 aux1.ver();
		} else if(cmd.equals(CANCEL)){
			if(aux1!=null)
				aux1.dispose();
		} else if(cmd.equals(ENVIARME)){
			boolean bien=true;
			boolean dni=true;
			if(aux1.getNombre().getText().isEmpty()){
				aux1.getNombre().setBackground(Color.RED);
				bien=false;
			} else {
				aux1.getNombre().setBackground(Color.WHITE);
			}
			if(aux1.getApellido1().getText().isEmpty()){
				aux1.getApellido1().setBackground(Color.RED);
				bien=false;
			} else {
				aux1.getApellido1().setBackground(Color.WHITE);
			}
			if(aux1.getApellido2().getText().isEmpty()){
				aux1.getApellido2().setBackground(Color.RED);
				bien=false;
			} else {
				aux1.getApellido2().setBackground(Color.WHITE);
			}
			if(aux1.getDni().getText().isEmpty()){
				aux1.getDni().setBackground(Color.RED);
				bien=false;
			} else {
				if(!aux1.getLetra().getSelectedItem().toString().toLowerCase().equals(Utilidades.letraDNI(Integer.parseInt(aux1.getDni().getText())).toLowerCase())) {
					aux1.getDni().setBackground(Color.red);
					aux1.getLetra().setBackground(Color.RED);
					dni=true;
				} else {
					aux1.getDni().setBackground(Color.WHITE);
				}
			}
			if(aux1.getLugar().getText().isEmpty()){
				aux1.getLugar().setBackground(Color.RED);
				bien=false;
			} else {
				aux1.getLugar().setBackground(Color.WHITE);
			}
			if(aux1.getTelefono().getText().isEmpty()){
				aux1.getTelefono().setBackground(Color.RED);
				bien=false;
			} else {
				aux1.getTelefono().setBackground(Color.WHITE);
			}
			if(aux1.getNumero().getText().isEmpty()){
				aux1.getNumero().setBackground(Color.RED);
				bien=false;
			} else {
				aux1.getNumero().setBackground(Color.WHITE);
			}
			String pss="";
			for(int i=0;i<aux1.getContrasena1().getPassword().length;i++) {
				pss=pss+aux1.getContrasena1().getPassword()[i];
			}
			String pss2="";
			for(int i=0;i<aux1.getContrasena2().getPassword().length;i++) {
				pss2=pss2+aux1.getContrasena2().getPassword()[i];
			}
			if(pss.equals("")){
				aux1.getContrasena1().setBackground(Color.RED);
				bien=false;
			} else {
				aux1.getContrasena1().setBackground(Color.WHITE);
			}
			if(pss.equals("")){
				aux1.getContrasena2().setBackground(Color.RED);
				bien=false;
			} else {
				aux1.getContrasena2().setBackground(Color.WHITE);
			}
			
			if(bien==true){
				if(!dni) {
					JOptionPane.showMessageDialog(null, "El dni es erroneo", "Error", JOptionPane.ERROR_MESSAGE);
				} 
				
				if(!(pss.equals(pss2))){
					aux1.getContrasena2().setBackground(Color.RED);
					aux1.getContrasena1().setBackground(Color.RED);
					JOptionPane.showMessageDialog(aux1, "Las passwords no coinciden", "error", JOptionPane.ERROR_MESSAGE);
					
				}else{
					int i=0;
					elimi=new Vector<Usuario>();
					usuario=Conexion.consultarUsuarios();
					String st=aux1.getNombre().getText()+aux1.getApellido1().getText();
					Vector<String> aux=new Vector<String>();
					for(int j=0;j<usuario.size();j++) {
						aux.add(usuario.get(i).getUser().toLowerCase());
					}
					if(aux.contains(st.toLowerCase())){
						while(aux.contains(st.toLowerCase()+i)){
							i++;
						}
						st=st+i;
					}
					
					//escribirMedico(st,con1, aux1.getNombre().getText(), aux1.getApellido1().getText(), aux1.getApellido2().getText(), aux1.getDni().getText(), aux1.getTelefono().getText(), aux1.getLugar().getText(), aux1.getNumero().getText());
					String sentencia=" insert into Medico ( dni, nColegiado, nTelefono) values ("+aux1.getDni().getText()+","+aux1.getNumero().getText()+","+aux1.getTelefono().getText()+");";
					Conexion.sentenciaSQL(sentencia);
					sentencia=" insert into Usuario(dni, rol, apellido, nombre, nick, contrasena, ubicacion) values ("+aux1.getDni().getText()+","+Constantes.MEDICO+",'"+aux1.getApellido1().getText()+" "+aux1.getApellido2().getText()+"','"+aux1.getNombre().getText()+"','"+st+"','"+pss+"','"+aux1.getLugar().getText()+"');";
					Conexion.sentenciaSQL(sentencia);
					usuario.add(new Usuario(st,"medico",pss,Integer.parseInt(aux1.getDni().getText())));
					JOptionPane.showMessageDialog(null, "Medico creado con usuario: "+st, "Creado", JOptionPane.INFORMATION_MESSAGE);
					aux1.dispose();
				}
			} else {
				if(dni) {
					JOptionPane.showMessageDialog(null, "Por favor, rellene todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "El DNI es erronea y por favor, rellene todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		} else if(cmd.equals(ENVIARTEC)){
			boolean bien=true;
			boolean dni=true;
			if(aux1.getNombre().getText().isEmpty()){
				aux1.getNombre().setBackground(Color.RED);
				bien=false;
			} else {
				aux1.getNombre().setBackground(Color.WHITE);
			}
			if(aux1.getApellido1().getText().isEmpty()){
				aux1.getApellido1().setBackground(Color.RED);
				bien=false;
			} else {
				aux1.getApellido1().setBackground(Color.WHITE);
			}
			if(aux1.getApellido2().getText().isEmpty()){
				aux1.getApellido2().setBackground(Color.RED);
				bien=false;
			} else {
				aux1.getApellido2().setBackground(Color.WHITE);
			}
			if(aux1.getDni().getText().isEmpty()){
				aux1.getDni().setBackground(Color.RED);
				bien=false;
			} else {
				if(!aux1.getLetra().getSelectedItem().toString().toLowerCase().equals(Utilidades.letraDNI(Integer.parseInt(aux1.getDni().getText())).toLowerCase())) {
					aux1.getDni().setBackground(Color.red);
					aux1.getLetra().setBackground(Color.RED);
					dni=false;
				} else {
					aux1.getDni().setBackground(Color.WHITE);
				}
			}
			if(aux1.getLugar().getText().isEmpty()){
				aux1.getLugar().setBackground(Color.RED);
				bien=false;
			} else {
				aux1.getLugar().setBackground(Color.WHITE);
			}
			String pss="";
			for(int i=0;i<aux1.getContrasena1().getPassword().length;i++) {
				pss=pss+aux1.getContrasena1().getPassword()[i];
			}
			String pss2="";
			for(int i=0;i<aux1.getContrasena2().getPassword().length;i++) {
				pss2=pss2+aux1.getContrasena2().getPassword()[i];
			}
			if(pss.equals("")){
				aux1.getContrasena1().setBackground(Color.RED);
				bien=false;
			} else {
				aux1.getContrasena1().setBackground(Color.WHITE);
			}
			if(pss.equals("")){
				aux1.getContrasena2().setBackground(Color.RED);
				bien=false;
			} else {
				aux1.getContrasena2().setBackground(Color.WHITE);
			}
			if(bien==true){
				if(!dni) {
					JOptionPane.showMessageDialog(null, "El dni es erroneo", "Error", JOptionPane.ERROR_MESSAGE);
				} 
				
				if(!(pss.equals(pss2))){
				aux1.getContrasena2().setBackground(Color.RED);
				aux1.getContrasena1().setBackground(Color.RED);
				JOptionPane.showMessageDialog(aux1, "Las passwords no coinciden", "Error", JOptionPane.ERROR_MESSAGE);
			} else{
				elimi=new Vector<Usuario>();
				usuario=Conexion.consultarUsuarios();
				int i=0;
				String st=aux1.getNombre().getText()+aux1.getApellido1().getText();
				Vector<String> aux=new Vector<String>();
				for(int j=0;j<usuario.size();j++) {
					aux.add(usuario.get(i).getUser().toLowerCase());
				}
				if(aux.contains(st.toLowerCase())){
					while(aux.contains(st.toLowerCase()+i)){
						i++;
					}
					st=st+i;
				}
				
				
				//sql
				//escribirTecnico(st,con1, aux1.getNombre().getText(), aux1.getApellido1().getText(), aux1.getApellido2().getText(), aux1.getDni().getText(), aux1.getLugar().getText());
				String sentencia=" insert into Tecnico ( dni, turno) values ("+aux1.getDni().getText()+","+Constantes.turno+");";
				Conexion.sentenciaSQL(sentencia);
				sentencia=" insert into Usuario(dni, rol, apellido, nombre, nick, contrasena, ubicacion) values ("+aux1.getDni().getText()+","+Constantes.TECNICO+",'"+aux1.getApellido1().getText()+" "+aux1.getApellido2().getText()+"','"+aux1.getNombre().getText()+"','"+st+"','"+pss+"','"+aux1.getLugar().getText()+"');";
				Conexion.sentenciaSQL(sentencia);
				usuario.add(new Usuario(st,"tecnico",pss,Integer.parseInt(aux1.getDni().getText())));
				JOptionPane.showMessageDialog(null, "Tecnico creado con usuario: "+st, "Creado", JOptionPane.INFORMATION_MESSAGE);
				aux1.dispose();
				
			}
			} else {
				if(dni) {
					JOptionPane.showMessageDialog(null, "Por favor, rellene todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "El DNI es erronea y por favor, rellene todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		} else if(cmd.equals(USUARIOS)){
			elimi=new Vector<Usuario>();
			usuario=Conexion.consultarUsuarios();
			aux=a.getCentro().getComponents();
			a.getCentro().setVisible(false);
			a.getCentro().removeAll();
			//usuario=this.obtenerUsuarios();
			Vector<Usuario>medi=new Vector<Usuario>();
			Vector<Usuario>tec=new Vector<Usuario>();
			
			for(int i=0;i<usuario.size();i++){
				if(usuario.get(i).getRol().equals("medico")){
					medi.add(usuario.get(i));
				} else {
					tec.add(usuario.get(i));
				}
			}
			//drop usuario
			a.getCentro().add(a.paneldeusuarios(this,medi,tec,elimi),BorderLayout.CENTER);
			
			a.getCentro().setVisible(true);

		} else if(cmd.equals(BACK)){
			if(!elimi.isEmpty()) {
				int resp = JOptionPane.showConfirmDialog(a, "Seguro que desea mantener los cambios? Serán permanentes", "Guardar cambios",JOptionPane.YES_NO_OPTION);
				if(resp==JOptionPane.NO_OPTION) {
					elimi.removeAllElements();
				}
			}
			while(!elimi.isEmpty()) {
				if(usuario.get(0).getRol().equals("medico")) {
//					Conexion.sentenciaSQL("delete from Medico where dni="+elimi.get(0).getDni());
					for(int i = 0; i <  querys.size();i++){
						Conexion.sentenciaSQL(querys.get(i));
						System.out.println(querys.get(i));
					}
				} else if(usuario.get(0).getRol().equals("tecnico")){
//					Conexion.sentenciaSQL("delete from tecnico where dni="+elimi.get(0).getDni());
				} else {
//					Conexion.sentenciaSQL("delete from administrador where dni="+elimi.get(0).getDni());
				}
//				Conexion.sentenciaSQL("delete from Usuario where dni="+elimi.get(0).getDni());
				usuario.remove(elimi.get(0));
				elimi.remove(0);
			}
			a.getCentro().setVisible(false);
			a.getCentro().removeAll();
			for(int i=0;i<aux.length;i++){
				a.getCentro().add(aux[i]);	
			}
			
			a.getCentro().setVisible(true);
//			String sentencia=" insert into Tecnico ( dni, turno) values ("+aux1.getDni().getText()+","+Constantes.turno+");";
//			String sentencia = "drop turno from Tecnico where Tecnico.dni = 98;";
//			Conexion.sentenciaSQL(sentencia);
		}
	}

	/**
	 * Listener que recae sobre los formularios para realizar un filtro de 
	 * caracteres, impidiendo que algunos campos de texto puedan tener numeros o letras
	 * y con algunos poder obtener un nombre de usuario
	 */
	public void keyTyped(KeyEvent e) {
		char key = (char) e.getKeyChar();
		if(e.getComponent().getName()!=null)
        if (e.getComponent().getName().equals("C") && !Character.isDigit(key)){ 
        		e.consume();
        	} else if(e.getComponent().getName().equals("N") && (Character.isDigit(key) || key==' ')){
        		e.consume();
        	} else if(key==' ' && e.getComponent().getName().equals("S")){
        		e.consume();
        	}
		
			aux1.getUser().setText(aux1.getNombre().getText().toString()+aux1.getApellido1().getText().toString());
			if(aux1.getUser().getText().isEmpty()){
				aux1.getUser().setText(" ");
			}
	}
	
/**
 * listener que simplemente se encarga de actualizar el usuario provisional
 * con un par de campos de texto.
 */
	public void keyPressed(KeyEvent e) {
		aux1.getUser().setText(aux1.getNombre().getText().toString()+aux1.getApellido1().getText().toString());
		
	}
	/**
	 * listener que simplemente se encarga de actualizar el usuario provisional
	 * con un par de campos de texto.
	 */
	public void keyReleased(KeyEvent e) {
		aux1.getUser().setText(aux1.getNombre().getText().toString()+aux1.getApellido1().getText().toString());
		e.getComponent().setBackground(Color.white);
	}
	
	
	/**
	 * listener que se encarga de la usabilidad para cuando se interactue
	 * con un campo de texto este alcance un color normal, ya que si un datos es incorrecto
	 * se tornara de color rojo
	 */
	public void mouseClicked(MouseEvent e) {
		e.getComponent().setBackground(Color.white);
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Se encarga de la actualizacion de los usuarios mostrados en 
	 * el control de usuarios de la ventanaAdminPrincipal aplicandolo un filtro
	 * en el caso de que se haya hecho una busqueda y separandolo entre medicos,
	 * tecnicos y en conjunto con estos, el administrador
	 * @param aux El dato por el que se filtraran los usuarios, viendo si contienen dicho String
	 */
	public void actPanel(String aux){
		int index=this.a.getTabbedPane().getSelectedIndex();
		a.getTabbedPane().setVisible(false);
		
		//usuario=this.obtenerUsuarios();
		Vector<Usuario>medi=new Vector<Usuario>();
		Vector<Usuario>tec=new Vector<Usuario>();
		for(int i=0;i<usuario.size();i++){
			if(usuario.get(i).getRol().equals("medico")){
				if(usuario.get(i).getUser().toLowerCase().contains(aux.toLowerCase()) || aux.equals("")){
					medi.add(usuario.get(i));
				}
			} else {
				if(usuario.get(i).getUser().toLowerCase().contains(aux.toLowerCase()) || aux.equals("")){
					tec.add(usuario.get(i));
				}
			}
		}
		
		a.actPanel(medi, tec,elimi,this);
		this.a.getTabbedPane().setSelectedIndex(index);

		a.getTabbedPane().setVisible(true);
	}

	public VentanaAdminPrincipal getA() {
		return a;
	}
	
}
