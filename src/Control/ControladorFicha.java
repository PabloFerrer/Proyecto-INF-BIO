package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;

import com.panamahitek.ArduinoException;

import Model.Conexion;
import Model.ECG;
import Model.Lectura;
import View.DetallePaciente;
import View.VentanaTecnico;


/**
 * @author Heartlight
 * 
 * ControladorFicha es aquella clase que actua como controlador de
 * la clase DetallePaciente. Se encargara de las funcionalidades 
 * de los botones de dicha clase como son el boton de enviar, el
 * de tomar datos y el de volver atras. Para mas 
 * informacion de la funcionalidad de cada boton, revisar el manual tecnico o 
 * el apartado de ayuda de la VentanaMedico dentro de la aplicacion.
 * 
 * @version Final
 * 
 * @see DetallePaciente
 * @see VentanaTecnico
 * @see ECG
 * @see Lectura
 *
 */
public class ControladorFicha implements ActionListener {

	static public String TOMAR="TOMAR";
	static public String ENVIAR="ENVIAR";
	static public String PREVI="PREVI";
	static public String ATRAS="ATRAS";
	static public String STOP="STOP";
	private DetallePaciente d;
	private VentanaTecnico vt;
	private String arch="";
	private ECG ecg;
	
	
	/**
	 * Getter del archivo que se obtiene al tomar datos
	 * @return arch
	 */
	public String getArch() {
		return arch;
	}
	
	/**
	 * Setter del archivo de tomar datos
	 * @param arch String
	 */
	public void setArch(String arch) {
		this.arch = arch;
	}
	
	/**
	 * Constructor de la clase ControladorFicha
	 * @param d DetallePaciente
	 * @param vt VentanaTecnico
	 * 
	 */
	public ControladorFicha(DetallePaciente d,VentanaTecnico vt){
		this.d=d;
		this.vt=vt;
	}
	
	/**
	 * Metodo actionPerformed propio de un actionListener
	 */
	public void actionPerformed(ActionEvent a) {
		String cmd=a.getActionCommand().toString();
		if(cmd.equals(ControladorFicha.ATRAS)){
			try {
				if(d.getEcg().getIno()!=null)
					d.getEcg().getIno().killArduinoConnection();
			} catch (ArduinoException e) {
			}
			d.getBtnEnivar().setEnabled(false);
			ecg=null;
			d.getEcg().cleanGraph();
			d.setVisible(false);
		}else if(cmd.equals(ControladorFicha.TOMAR)){
			
			ecg=null;
			d.getEcg().cleanGraph();
//			JFileChooser file=new JFileChooser("Resource/ECG");
//			file.showOpenDialog(vt);
//			file.setVisible(true);
//			File abierto = file.getSelectedFile();
//			if(abierto!=null){
//				
//				
//				Object[] aux=Lectura.lecturaEcg(abierto,d.getP(),vt.getAu().getUser());
//				
//				
//				arch=(String) aux[0];
//				ecg=(ECG) aux[1];
//				if(!ecg.getPuntos().isEmpty()) {
//					d.getBtnEnivar().setEnabled(true);
//				}
//				d.getEcg().addGraphic(ecg);
			Thread aux=new Thread(d.getEcg());
			d.getBtnEnivar().setEnabled(false);
			d.getBtnTomarDatos().setEnabled(false);
			d.getBtnStop().setEnabled(true);
			aux.start();
			((GraphController) d.getEcg().getSl().getChangeListeners()[d.getEcg().getSl().getChangeListeners().length-1]).stateChanged(new ChangeEvent(d.getEcg().getSl()));
			
		} else if(cmd.equals(ControladorFicha.ENVIAR)){
			int resp = JOptionPane.showConfirmDialog(vt, "¿Esta seguro?", "Enviar Reporte", JOptionPane.YES_NO_OPTION);
			
			if(resp==0){
			d.getBtnEnivar().setEnabled(false);
			String mon=Calendar.getInstance().get(Calendar.MONTH)+"";
			if(mon.length()<2) {
				mon="0"+mon;
			}
			String day=Calendar.getInstance().get(Calendar.DATE)+"";
			if(day.length()<2) {
				day="0"+day;
			}
			System.out.println("HOLA"+d.getP().getDni());
			System.out.println(Integer.parseInt(d.getP().getDni().substring(0, d.getP().getDni().length()-1)));
			ecg=new ECG(Integer.parseInt(Calendar.getInstance().get(Calendar.YEAR)+""+mon+""+day),vt.getTec().getDni(),Integer.parseInt(d.getP().getDni().substring(0, d.getP().getDni().length()-1)),d.getObser().getText(),ecg.getPuntosporsec(),ecg.getPuntos(),false);
			Conexion.InsertarNuevoECG(ecg);
			JOptionPane.showMessageDialog(vt, "Envio de datos exitoso", "Exito", JOptionPane.DEFAULT_OPTION);
			vt.getFicha().getEcg().cleanGraph();
			vt.getFicha().getObser().setText("");;
			ecg=null;
			}
		} else if(cmd.equals(ControladorFicha.STOP)) {
			try {
				d.getEcg().getIno().killArduinoConnection();
				d.getBtnTomarDatos().setEnabled(true);
				d.getBtnEnivar().setEnabled(true);
				d.getBtnStop().setEnabled(false);
				if(ecg!=null && ecg.getPuntos()!=null && !ecg.getPuntos().isEmpty())
					d.getBtnEnivar().setEnabled(true);
			} catch (ArduinoException e) {
				
			}
		}
			
	}

	public ECG getEcg() {
		return ecg;
	}

	public void setEcg(ECG ecg) {
		this.ecg = ecg;
	}

	public VentanaTecnico getVt() {
		return vt;
	}
}

