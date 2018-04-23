package Control;

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
				ven.ver();
			}
		});
	}
	
}  
