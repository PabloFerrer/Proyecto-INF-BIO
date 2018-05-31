 package Control;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Model.Administrador;
import Model.Conexion;
import Model.Lectura;
import Model.Medico;
import Model.Paciente;
import Model.Tecnico;
import Model.Usuario;
import View.VentanaAdminPrincipal;
import View.VentanaHelp;
import View.VentanaLogin;
import View.VentanaMedico;
import View.VentanaTecnico;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Controlador especifico para la VentanaLogin permitiendole al usuario
 * ver la ayuda e ingresas con su usuario y contrasena, validandola, viendo a que rol pertenece
 * y dandole paso a la ventana que le pertenece
 * @author HeartLight
 * 
 * @version Final
 * 
 * @see VentanaLogin
 * @see VentanaHelp
 * @see Usuario
 *
 */
public class ControladorLogin implements ActionListener, KeyListener,MouseListener,WindowListener{
		static public String ACCEDER="ACCEDER";
		static public String MASTERMED="MASTERMED";
		static public String MASTERTEC="MASTERTEC";
		static public String MASTERADM="MASTERADM";
		static public String SALIR="SALIR";
		static public String FORGET="FORGET";
		static public String HELP="HELP";
		private JTextField usuario,contrasena;
		private Usuario user;
		private boolean isMaster=false;
		private VentanaLogin frame;
		private VentanaHelp help;
		private int aux=0;
		
		/**
		 * Constructor del controlador para una VentanaLogin, asignando otros controladores a
		 * elementos de dicha ventana
		 * @param frame VentanaLogin a la cual se le asignaran los controladores y se controlara
		 */
	public ControladorLogin(VentanaLogin frame){
		 
			user=new Usuario("","","",0);
			this.usuario=frame.getUser();
			this.contrasena=frame.getPass();
			this.frame=frame;
			
			usuario.addMouseListener(new MouseListener(){
				public void mouseClicked(MouseEvent e) {
					usuario.setBackground(Color.white);
					contrasena.setBackground(Color.white);
					usuario.setText("");
					contrasena.setText("");
				}
				public void mousePressed(MouseEvent e) {
					contrasena.setBackground(Color.white);
					usuario.setBackground(Color.white);
					usuario.setText("");
					contrasena.setText("");
				}
				public void mouseReleased(MouseEvent e) {}
				public void mouseEntered(MouseEvent e) {}
				public void mouseExited(MouseEvent e) {}
			});
			contrasena.addMouseListener(new MouseListener(){
				public void mouseClicked(MouseEvent e) {
					contrasena.setBackground(Color.white);
					usuario.setBackground(Color.white);
					contrasena.setText("");
				}
				public void mousePressed(MouseEvent e) {
					contrasena.setBackground(Color.white);
					usuario.setBackground(Color.white);
					contrasena.setText("");
				}
				public void mouseReleased(MouseEvent e) {}
				public void mouseEntered(MouseEvent e) {}
				public void mouseExited(MouseEvent e) {}
			});
		}
	

	/**
	 * Listener que se activara cuando el usuario interactue
	 * con algun componente de la VentanaLogin y se realizara una 
	 * accion dependiendo de cual sea (Acceder,ayuda,logout,olvidarcontrasena)
	 */
	public void actionPerformed(ActionEvent e) {
		String cmd=e.getActionCommand().toString();
		
		if(cmd.equals(ControladorLogin.ACCEDER)){
			if(help!=null)
				help.dispose();
			if(!usuario.getText().toLowerCase().equals(user.getUser().toLowerCase())) {
				user=Conexion.consultaLogin(usuario.getText(), contrasena.getText());
			}
			darAcceso();
		} else if(cmd.equals(ControladorLogin.FORGET)){
				JOptionPane.showMessageDialog(frame, "Esta opcion se encontrará disponible en proximas versiones.\n(Para apegarse al formato de fichero del resto)","",JOptionPane.INFORMATION_MESSAGE);
		} else if(cmd.equals(ControladorLogin.HELP)){
			if(help!=null)
				help.dispose();
			  File fileH = new File("Resource/Usuarios/1.txt");
			  help=new VentanaHelp(3);
			  help.setAlwaysOnTop(true);
		        try( BufferedReader read = new BufferedReader(new FileReader(fileH))) {
		           
		            String line = read.readLine();
		            
		            while(line !=null){
		                help.getText().append(line+" "+"\n");
		                line =read.readLine();
		            }
		            
		        } catch (Exception ex) {
		            
		        }
			
		} else if(cmd.equals(MASTERADM)) {
			for(int i=0;i<JFrame.getFrames().length;i++) {
				if(!(JFrame.getFrames()[i] instanceof VentanaLogin))
					JFrame.getFrames()[i].dispose();
			}
			frame.relocate();
			isMaster=true;
			usuario.setText("lusy");
			contrasena.setText("1");
			user=Conexion.consultaLogin(usuario.getText(), contrasena.getText());
			darAcceso();
		} else if(cmd.equals(MASTERTEC)) {
			for(int i=0;i<JFrame.getFrames().length;i++) {
				if(!(JFrame.getFrames()[i] instanceof VentanaLogin))
					JFrame.getFrames()[i].dispose();
			}
			frame.relocate();
			isMaster=true;
			usuario.setText("pery");
			contrasena.setText("3");
			user=Conexion.consultaLogin(usuario.getText(), contrasena.getText());
			darAcceso();
		} else if(cmd.equals(MASTERMED)) {
			for(int i=0;i<JFrame.getFrames().length;i++) {
				if(!(JFrame.getFrames()[i] instanceof VentanaLogin))
				JFrame.getFrames()[i].dispose();
			}
			frame.relocate();
			isMaster=true;
			usuario.setText("borgy");
			contrasena.setText("2");
			user=Conexion.consultaLogin(usuario.getText(), contrasena.getText());
			darAcceso();
		} else if(cmd.equals(SALIR)) {
			for(int i=0;i<JFrame.getFrames().length;i++) {
				JFrame.getFrames()[i].dispose();
			}
			VentanaLogin ven=new VentanaLogin();
			ControladorLogin con=new ControladorLogin(ven);
			ven.asignarControlador(con);
			ven.ver();
		}
	}
	


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
/**
 * Decide dependiendo del usuario y si la contrasena es valida, dar acceso y 
 * a que rol pertenece para saber la ventana que abre
 */
	public void darAcceso(){
			if(user.getUser()!=null){
				if(user.getCon().equals(contrasena.getText().toString())){
					if(help!=null)
						help.dispose();
					usuario.setText("Bienvenido "+user.getRol());
					frame.getLogo().stop();
					if(user.getRol().equals("admin")){
						Administrador ad = Conexion.obtenerAdmin(user);
						VentanaAdminPrincipal ven=new VentanaAdminPrincipal(ad);
						ven.setLocation(frame.getLocation());
						ven.setSize(frame.getSize());
						ControladorAdmin con=new ControladorAdmin(ven);
						ven.asignarControlador(con);
						ven.ver();
					}else if(user.getRol().equals("tecnico")){
						Tecnico te = Conexion.obtenerTecnico(user);
						VentanaTecnico vt = new VentanaTecnico(te);
						ArrayList<Paciente> pac=Conexion.consultaPacTec();
						ControladorTecnico ct = new ControladorTecnico(vt,user,pac);
						vt.setSize(frame.getSize());
						vt.setLocation(frame.getLocation());
						vt.crearVista(pac,te);
						vt.addController(ct);
						vt.setExtendedState(vt.getExtendedState()|JFrame.MAXIMIZED_BOTH );
						vt.ver();
					}
					else if(user.getRol().equals("medico")) {
						Medico med=Conexion.consultaMed(user);
						med.setPacientes(Conexion.consultaPacMed(med));
						VentanaMedico vm = new VentanaMedico(med);
						ControladorMedico cm = new ControladorMedico(vm,med);
						vm.crearVista();
						vm.setLocation(frame.getLocation());
						vm.setSize(frame.getSize());
						vm.setExtendedState(vm.getExtendedState()|JFrame.MAXIMIZED_BOTH );
						
						vm.addController(cm);
						vm.ver();
					}
					if(!isMaster) {
						frame.dispose();
					}
					usuario.setBackground(Color.green);
					contrasena.setBackground(Color.green);
				} else {
					
					usuario.setText("Contraseña erronea");
					usuario.setBackground(Color.red);
					contrasena.setBackground(Color.red);
				}
			} else {
				usuario.setText("Dicho usuario no existe");
				usuario.setBackground(Color.red);
				contrasena.setBackground(Color.red);
			}
			contrasena.setText("");
		
	}
	/**
	 * Getter del usuario que se ha buscado
	 * @return Usuario
	 */
	public Usuario getUser() {
		return user;
	}


	/**
	 * Se encarga de que al presionar el boton de enter de acceso
	 * al usuario
	 */
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_ENTER) {
			if(!usuario.getText().toLowerCase().equals(user.getUser().toLowerCase())) {
				user=Conexion.consultaLogin(usuario.getText(), contrasena.getText());
			}
			darAcceso();
		}
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount()==2) {
			if(usuario.getText().toLowerCase().equals("master")) {
				System.out.println("HOLA");
				frame.loginMaster(this);
			}
		}
		
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

	public boolean isMaster() {
		return isMaster;
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
