package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JTextArea;

import Model.ECG;
import Model.Medico;
import Model.Paciente;
import View.CompararECG;
import View.FichaPaciente;
import View.VentanaHelp;
import View.VentanaMedico;

/**
 * Controlador que permite mostrar la lista de pacientes en la VentanaMedico.
 * Este se encargara de ocultar el contenido de la VentanaMedico y agregar la
 * lista de los pacientes en el centro de dicha ventana.
 * 
 * @author Heartlight
 * 
 * @version Final
 * 
 * @see ECG
 * @see Medico
 * @see Paciente
 * @see CompararECG
 * @see FichaPaciente
 * @see VentanaHelp
 * @see VentanaMedico
 *
 */
public class ControladorPanelM implements MouseListener,ActionListener,MouseMotionListener {

	private Paciente p;
	private Medico m;
	private ECG ecg;
	private JTextArea fi;
	private VentanaMedico vm;
	public static String COMPARAR ="COMPARAR";
	public static String ATRAS ="ATRAS";
	public static String GUARDAR ="GUARDAR";
	private CompararECG ce;
	private int aux=-1;
	
	/**
	 * Constructor de la clase ControladorPanelM
	 * 
	 * @param vm VentanaMedico 
	 * @param p Paciente 
	 * @param m Medico 
	 * 
	 */
	public ControladorPanelM(VentanaMedico vm, Paciente p, Medico m){
		this.vm = vm;
		this.p = p;
		this.m=m;
	}

	/**
	 * Segundo contstructor de la clase ControladorPanelM
	 * 
	 * @param ecg ECG 
	 * @param fi JTextArea 
	 */
	public ControladorPanelM(ECG ecg, JTextArea fi) {
		this.ecg = ecg;
		this.fi = fi;
	}
	
	/**
	 * Tercer constructor de la clase ControladorPanelM 
	 * 
	 * @param vm VentanaMedico 
	 * @param paciente Paciente 
	 * @param m Medico 
	 * @param ecg ECG 
	 * @param aux int 
	 */
	public ControladorPanelM(VentanaMedico vm, Paciente paciente, Medico m, ECG ecg, int aux) {
		this.vm = vm;
		this.p = paciente;
		this.m=m;
		this.ecg = ecg;
		this.aux=aux;
	}
	
	/** 
	 * Metodo mouseClicked propio de un mouseListener
	 * @param a MouseEvent 
	 */
	public void mouseClicked(MouseEvent a) {
		vm.getCentro().setVisible(false);
		vm.getCentro().removeAll();
		FichaPaciente fp = new FichaPaciente(p);
		if(aux==0) {
			ecg.setLeido(true);
			int i=0;
			while(!p.getEcgs().get(i).equals(ecg) && i<p.getEcgs().size()) {
				i++;
			}
			if(i<p.getEcgs().size())
				fp.getTab().setSelectedIndex(i);
		}
		fp.addController(new ControladorPanelM(vm,p,m,ecg,aux));
		
		vm.getCentro().add(fp);
		vm.getCentro().setVisible(true);
		
	}
	
	/** 
	 * Metodo mouseEntered propio de un mouseListener
	 * 
	 */
	public void mouseEntered(MouseEvent arg0) {
	
	}
	/** 
	 * Metodo mouseExited propio de un mouseListener
	 * 
	 */
	public void mouseExited(MouseEvent arg0) {
	
	}
	/** 
	 * Metodo mousePressed propio de un mouseListener
	 * 
	 */
	public void mousePressed(MouseEvent arg0) {
	
	}
	/** 
	 * Metodo mouseReleased propio de un mouseListener
	 * 
	 */
	public void mouseReleased(MouseEvent arg0) {
	
	}

	/**
	 * Metodo actionPerformed propio de un ActionListener
	 * @param e ActionEvent 
	 */
	public void actionPerformed(ActionEvent e) {
		String cmd=e.getActionCommand().toString();
		if(cmd.equals(COMPARAR)){
			if(ce != null){
				ce.dispose();
			}
			
			for(int i=0;i<VentanaHelp.getFrames().length;i++) {
				if(VentanaHelp.getFrames()[i] instanceof VentanaHelp)
					VentanaHelp.getFrames()[i].dispose();
			}
			ce = new CompararECG();
			ce.setAlwaysOnTop(true);
			ce.seleccion(p.getEcgs());
			ce.setVisible(true);
		}else if(cmd.equals(ATRAS)){
			if(ce != null){
				ce.dispose();
			}
			if(aux==0) {
				vm.getBtnRevisarEcg().doClick();
			} else {
				vm.getBtnBuscarPacientes().doClick();
			}
		} else if(cmd.equals(GUARDAR)) {
			if(!fi.getText().isEmpty())
				ecg.setDiagnostico(fi.getText());
			else
				ecg.setDiagnostico("");
		}
		
	}

	/** 
	 * Metodo mouseDragged propio de un mouseListener
	 * 
	 */
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/** 
	 * Metodo mouseMoved propio de un mouseListener
	 * 
	 */
	public void mouseMoved(MouseEvent e) {
		e.getComponent().removeMouseMotionListener(this);
		ecg.setLeido(true);
		
	}

}
