package Control;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import View.*;

/**
 * Main de la aplicacion en el cual se inicializa la base de toda la aplicacion
 * creando la VentanaLogin desde la cual se puede acceder los demas apartados de la aplicacion
 * 
 * @author HeartLight
 * 
 * @version Final
 * 
 * @see VentanaLogin
 * @see ControladorLogin
 * @see SwingUtilities
 *
 */
public class MainProgram {
	/**
	 * Main
	 * @param args sin uso
	 */
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				VentanaLogin ven=new VentanaLogin();
				ControladorLogin con=new ControladorLogin(ven);
				ven.asignarControlador(con);
				JOptionPane.showMessageDialog(ven, "Para facilitar la revision hemos incluido un menu de atajos \r\ncon 3 usuarios predeterminados de la base de datos, solo deberan \r\nescribir master en el usuario y darle doble-click al logo");
				ven.ver();
				
			}
		});
	}
	
}  
