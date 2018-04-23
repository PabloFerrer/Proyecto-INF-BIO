package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;

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
			d.getBtnEnivar().setEnabled(false);
			ecg=null;
			d.getEcg().cleanGraph();
			d.setVisible(false);
		}else if(cmd.equals(ControladorFicha.TOMAR)){
			
			ecg=null;
			d.getEcg().cleanGraph();
			JFileChooser file=new JFileChooser();
			file.showOpenDialog(vt);
			file.setVisible(true);
			File abierto = file.getSelectedFile();
			if(abierto!=null){
				
				
				Object[] aux=Lectura.lecturaEcg(abierto,d.getP(),vt.getAu().getUser());
				
				
				arch=(String) aux[0];
				ecg=(ECG) aux[1];
				if(!ecg.getPuntos().isEmpty()) {
					d.getBtnEnivar().setEnabled(true);
				}
				d.getEcg().addGraphic(ecg);
				((GraphController) d.getEcg().getSl().getChangeListeners()[d.getEcg().getSl().getChangeListeners().length-1]).stateChanged(new ChangeEvent(d.getEcg().getSl()));
			}
		} else if(cmd.equals(ControladorFicha.ENVIAR)){
			int resp = JOptionPane.showConfirmDialog(vt, "¿Esta seguro?", "Enviar Reporte", JOptionPane.YES_NO_OPTION);
			
			if(resp==0){
			d.getBtnEnivar().setEnabled(false);
			
			if(!arch.equals("")){
			try(FileWriter wr=new FileWriter("Resource/Pacientes/"+d.getP().getId()+".txt",true)){
				wr.write(arch+"\r\n");
				try(FileWriter wr2=new FileWriter("Resource/ECG/"+arch+".txt",true)){
					wr2.write(ecg.getPuntosporsec()+"\r\n");
					for(int i=0;i<ecg.getPuntos().size();i++){
						wr2.write(ecg.getPuntos().get(i)+";");
					}
					String str=d.getObser().getText().replaceAll("\r", ";");
					str=str.replaceAll("\n", ";");
					wr2.write("\r\n"+str+"\r\n");
				}catch(Exception e){
					
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			}
			JOptionPane.showMessageDialog(vt, "Envio de datos exitoso", "Exito", JOptionPane.DEFAULT_OPTION);
			vt.getFicha().getEcg().cleanGraph();
			vt.getFicha().getObser().setText("");;
			ecg=null;
			}
		}
			
	}
}
