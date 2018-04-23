package Control;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Model.Lectura;
import Model.Medico;
import Model.Usuario;
import View.VentanaAdminPrincipal;
import View.VentanaHelp;
import View.VentanaLogin;
import View.VentanaMedico;
import View.VentanaTecnico;

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
public class ControladorLogin implements ActionListener, KeyListener{
		static public String ACCEDER="ACCEDER";
		static public String FORGET="FORGET";
		static public String HELP="HELP";
		private JTextField a,b;
		private Usuario user;
		private VentanaLogin frame;
		private VentanaHelp help;
		
		/**
		 * Constructor del controlador para una VentanaLogin, asignando otros controladores a
		 * elementos de dicha ventana
		 * @param frame VentanaLogin a la cual se le asignaran los controladores y se controlara
		 */
	public ControladorLogin(VentanaLogin frame){
		user=new Usuario("","","");
		this.a=frame.getUser();
		this.b=frame.getPass();
		this.frame=frame;
		
		a.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent e) {
				a.setBackground(Color.white);
				b.setBackground(Color.white);
				a.setText("");
				b.setText("");
			}
			public void mousePressed(MouseEvent e) {
				b.setBackground(Color.white);
				a.setBackground(Color.white);
				a.setText("");
				b.setText("");
			}
			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
		});
		b.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent e) {
				b.setBackground(Color.white);
				a.setBackground(Color.white);
				b.setText("");
			}
			public void mousePressed(MouseEvent e) {
				b.setBackground(Color.white);
				a.setBackground(Color.white);
				b.setText("");
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
			
		}
	}
	
/**
 * Buscar el usuario introducido en la base de datos y guardarlo con su informacion si ha sido encontrado
 * @return true si el usuario existe/ false sino
 */
	private boolean verificar() {
		boolean aux = false;
		if (!user.getUser().equals(a.getText().toString())) {
			File file = null;
			file = new File("Resource/Usuarios/Users.txt");
				try (Scanner sc = new Scanner(new FileReader(file))) {
					while (sc.hasNextLine() && aux == false) {
						String auxi=sc.nextLine();
						String[] usuario=auxi.split(";");
						if (usuario[0].toString().toLowerCase().equals(a.getText().toString().toLowerCase())) {
							aux=true;
							user=new Usuario(usuario[0],usuario[1],usuario[2]);
						} 
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
		} else{
			aux=true;
		}
		return aux;
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
		if(!a.getText().isEmpty()){
			if(verificar()){
				if(user.getCon().equals(b.getText().toString())){
					if(help!=null)
						help.dispose();
					a.setText("Bienvenido "+user.getRol());
					if(user.getRol().equals("admin")){
						
						VentanaAdminPrincipal ven=new VentanaAdminPrincipal(user);
						ven.setLocation(frame.getLocation());
						ven.setSize(frame.getSize());
						ControladorAdmin con=new ControladorAdmin(ven);
						ven.asignarControlador(con);
						ven.ver();
						frame.dispose();
					}else if(user.getRol().equals("tecnico")){
						
						VentanaTecnico vt = new VentanaTecnico(user);
						ControladorTecnico ct = new ControladorTecnico(vt);
						vt.setSize(frame.getSize());
						vt.setLocation(frame.getLocation());
						vt.crearVista(ct.leeTxt());
						vt.addController(ct);
						vt.setExtendedState(vt.getExtendedState()|JFrame.MAXIMIZED_BOTH );
						vt.ver();
						frame.dispose();
					}
					else if(user.getRol().equals("medico")) {
						Medico med=Lectura.lectura_medico(user);
						VentanaMedico vm = new VentanaMedico(med);
						ControladorMedico cm = new ControladorMedico(vm,med);
						vm.crearVista();
						vm.setLocation(frame.getLocation());
						vm.setSize(frame.getSize());
						vm.setExtendedState(vm.getExtendedState()|JFrame.MAXIMIZED_BOTH );
						
						vm.addController(cm);
						vm.ver();
						frame.dispose();
					}
					a.setBackground(Color.green);
					b.setBackground(Color.green);
				} else {
					a.setText("Contraseña erronea");
					a.setBackground(Color.red);
					b.setBackground(Color.red);
				}
			} else {
				a.setText("Dicho usuario no existe");
				a.setBackground(Color.red);
				b.setBackground(Color.red);
			}
			b.setText("");
		} 
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
			darAcceso();
		}
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
