package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JRadioButton;

import Model.ECG;
import Model.Medico;
import Model.Paciente;
import View.VentanaMedico;
import View.VentanaMedicoECG;

/**
 * 
 * Controlador que se encargará filtrar los ecg en la VentanaMedicoECG por 
 * diversos apartados y dara el llamado para que se actualice 
 * 
 * @author HeartLight
 * 
 * @version Final
 * 
 * @see VentanaMedico
 * @see VentanaMedicoECG
 * @see ECG
 * @see Medico
 *
 */
public class ControladorFiltrado implements ActionListener{
	private JRadioButton leido;
	private JRadioButton noleido;
	private JRadioButton diag;
	private JRadioButton nodiag;
	private VentanaMedico vm;
	private VentanaMedicoECG vmec;
	private Medico m;
	private Vector<ECG> auxec;
	private Vector<Paciente> auxpac;
	
	
/**
 * Constructor del controlador con todos los elementos que componen lo visual 
 * de la opcion de filtrado
 * @param leido JRadioButton que define se si quieren ver los leidos
 * @param noleido JRadioButton que define se si quieren ver los no leidos
 * @param diag JRadioButton que define se si quieren ver los diagnosticados
 * @param nodiag JRadioButton que define se si quieren ver los no diagnosticados
 * @param vm Ventana en la que se encuentra todo
 * @param m Medico al que le pertenece la instancia
 * @param vmec Panel en el que se muestran los ecg de pacientes
 * @param auxec Vector de todos los ecg de los pacientes
 * @param auxpac Vector que contiene todos los pacientes a los que le pertenecen los ecg, siendo correspondientes con la posicion
 */
	public ControladorFiltrado(JRadioButton leido, JRadioButton noleido, JRadioButton diag, JRadioButton nodiag,
			VentanaMedico vm, Medico m,VentanaMedicoECG vmec,Vector<ECG> auxec,Vector<Paciente> auxpac) {
		this.leido = leido;
		this.noleido = noleido;
		this.diag = diag;
		this.nodiag = nodiag;
		this.vm = vm;
		this.m = m;
		this.vmec=vmec;
		this.auxec=auxec;
		this.auxpac=auxpac;
	}


/**
 * Encargado de al presionar un boton vea cuales son las opciones de filtrado marcadas y dar la 
 * orden para actualizarlo
 */
	public void actionPerformed(ActionEvent e) {
		int lei=0;
		if(leido.isSelected() && !noleido.isSelected()) {
			lei=1;
		} else if(!leido.isSelected() && noleido.isSelected()) {
			lei=-1;
		}
		
		int dia=0;
		if(diag.isSelected() && !nodiag.isSelected()) {
			dia=1;
		} else if(!diag.isSelected() && nodiag.isSelected()) {
			dia=-1;
		}
		
		vmec.actPanel(vm, m, lei, dia,auxec,auxpac);
	}
}
