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
		usuario=this.obtenerUsuarios();
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
		usuario=this.obtenerUsuarios();
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
				aux1.getDni().setBackground(Color.WHITE);
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
				String con1="";
				for(int j=0;j<aux1.getContrasena1().getPassword().length;j++) {
					con1=con1+aux1.getContrasena1().getPassword()[j];
				}
				String con2="";
				for(int j=0;j<aux1.getContrasena2().getPassword().length;j++) {
					con2=con2+aux1.getContrasena2().getPassword()[j];
				}
				if(!(con1.equals(con2))){
					aux1.getContrasena2().setBackground(Color.RED);
					aux1.getContrasena1().setBackground(Color.RED);
					JOptionPane.showMessageDialog(null, "Las passwords no coinciden", "error", JOptionPane.ERROR_MESSAGE);
					
				}else{
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
					con1="";
					for(int j=0;j<aux1.getContrasena1().getPassword().length;j++) {
						con1=con1+aux1.getContrasena1().getPassword()[j];
					}
					//escribirMedico(st,con1, aux1.getNombre().getText(), aux1.getApellido1().getText(), aux1.getApellido2().getText(), aux1.getDni().getText(), aux1.getTelefono().getText(), aux1.getLugar().getText(), aux1.getNumero().getText());
					String sentencia=" insert into Medico ( dni, nColegiado, nTelefono) values ("+aux1.getDni().getText()+","+aux1.getNumero().getText()+","+aux1.getTelefono().getText()+");";
					Conexion.sentenciaSQL(sentencia);
					sentencia=" insert into Usuario(dni, rol, apellido, nombre, nick, contrasena, ubicacion) values ("+aux1.getDni().getText()+","+Constantes.MEDICO+",'"+aux1.getApellido1().getText()+" "+aux1.getApellido2().getText()+"','"+aux1.getNombre().getText()+"','"+st+"','"+con1+"','"+aux1.getLugar().getText()+"');";
					Conexion.sentenciaSQL(sentencia);
					usuario.add(new Usuario(st,"medico",con1));
					JOptionPane.showMessageDialog(null, "Medico creado con usuario: "+st, "Creado", JOptionPane.INFORMATION_MESSAGE);
					aux1.dispose();
				}
			} else {
				JOptionPane.showMessageDialog(null, "Por favor, rellene todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else if(cmd.equals(ENVIARTEC)){
			boolean bien=true;
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
				aux1.getDni().setBackground(Color.WHITE);
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
				String con1="";
				for(int j=0;j<aux1.getContrasena1().getPassword().length;j++) {
					con1=con1+aux1.getContrasena1().getPassword()[j];
				}
				String con2="";
				for(int j=0;j<aux1.getContrasena2().getPassword().length;j++) {
					con2=con2+aux1.getContrasena2().getPassword()[j];
				}
				if(!(con1.equals(con2))){
				aux1.getContrasena2().setBackground(Color.RED);
				aux1.getContrasena1().setBackground(Color.RED);
				JOptionPane.showMessageDialog(null, "Las passwords no coinciden", "Error", JOptionPane.ERROR_MESSAGE);
			} else{
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
				con1="";
				for(int j=0;j<aux1.getContrasena1().getPassword().length;j++) {
					con1=con1+aux1.getContrasena1().getPassword()[j];
				}
				con2="";
				for(int j=0;j<aux1.getContrasena2().getPassword().length;j++) {
					con2=con2+aux1.getContrasena2().getPassword()[j];
				}
				
				escribirTecnico(st,con1, aux1.getNombre().getText(), aux1.getApellido1().getText(), aux1.getApellido2().getText(), aux1.getDni().getText(), aux1.getLugar().getText());
				usuario.add(new Usuario(st,"tecnico",con1));
				JOptionPane.showMessageDialog(null, "Tecnico creado con usuario: "+st, "Creado", JOptionPane.INFORMATION_MESSAGE);
				aux1.dispose();
				
			}
			} else {
				JOptionPane.showMessageDialog(null, "Por favor, rellene todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else if(cmd.equals(USUARIOS)){
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
			
			a.getCentro().add(a.paneldeusuarios(this,medi,tec,elimi),BorderLayout.CENTER);
			
			a.getCentro().setVisible(true);

		} else if(cmd.equals(BACK)){
			for(int i=0;i<elimi.size();i++) {
				usuario.remove(elimi.get(i));
			}
			a.getCentro().setVisible(false);
			a.getCentro().removeAll();
			for(int i=0;i<aux.length;i++){
				a.getCentro().add(aux[i]);
			}
			
			a.getCentro().setVisible(true);
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
	 * Se encarga de la lectura de todos los usuarios desde su fichero correspondiente
	 * @return Un vector con todos los usuarios que pudo leer de fichero
	 */
	public Vector<Usuario> obtenerUsuarios(){
		Vector<Usuario> us=new Vector<Usuario>();
			File file = null;
			file = new File("Resource/Usuarios/Users.txt");
				try (Scanner sc = new Scanner(new FileReader(file))) {
					while (sc.hasNextLine()) {
						String auxi=sc.nextLine();
						String[] usuario=auxi.split(";");
						us.add(new Usuario(usuario[0],usuario[1],usuario[2]));
						
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
		
		return us;
	}
	/**
	 * Escritura y creacion de un nuevo medico en la base de datos
	 * @param User nombre de usuario	
	 * @param password contrasena del usuario
	 * @param nom nombre
	 * @param ape1 primero apellido
	 * @param ape2 segundo apellido
	 * @param dn DNI
	 * @param tele numero de telefono
	 * @param lugar Lugar/direccion de residencia/Trabajo
	 * @param cole Numero de colegiado
	 */
	public void escribirMedico(String User,String password,String nom,String ape1,String ape2,String dn,String tele,String lugar,String cole){
		try(FileWriter aux=new FileWriter("Resource/Usuarios/Users.txt",true)){
			aux.write(User+";medico;"+password+"\r\n");
		}catch(Exception exc){
		}
		try(FileWriter aux=new FileWriter("Resource/Medicos/"+User+".txt",true)){
			aux.write(nom+";"+ape1+" "+ape2+";"+dn+";"+tele+";"+lugar+";"+cole+"\r\n");
		}catch(Exception exc){
		}
	}
	/**
	 * Escritura y creacion de un nuevo tecnico en la base de datos
	 * @param User nombre de usuario
	 * @param password contrasena de dicho usuario
	 * @param nom nombre
	 * @param ape1 primer apellido
	 * @param ape2 segundo apellido
	 * @param dn DNI
	 * @param lugar Lugar/direccion de residencia/Trabajo
	 */
	public void escribirTecnico(String User,String password,String nom,String ape1,String ape2,String dn,String lugar){
		try(FileWriter aux=new FileWriter("Resource/Usuarios/Users.txt",true)){
			aux.write(User+";medico;"+password+"\r\n");
		}catch(Exception exc){
		}
		try(FileWriter aux=new FileWriter("Resource/Tecnicos/Tecnicos.txt",true)){
			aux.write(nom+";"+ape1+" "+ape2+";"+dn+";"+lugar+"\r\n");
		}catch(Exception exc){
		}
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
	
}
