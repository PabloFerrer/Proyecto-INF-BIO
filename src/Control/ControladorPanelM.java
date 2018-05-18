package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Calendar;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Model.Conexion;
import Model.Constantes;
import Model.ECG;
import Model.Medico;
import Model.Mensaje;
import Model.Paciente;
import Model.Usuario;
import View.CompararECG;
import View.FichaPaciente;
import View.GraficaECG;
import View.VentanaHelp;
import View.VentanaMedico;
import View.VentanaMensajes;

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
public class ControladorPanelM implements MouseListener,ActionListener,MouseMotionListener,ChangeListener {

	private Paciente p;
	private Medico m;
	private ECG ecg;
	private JTextArea fi;
	private VentanaMedico vm;
	private VentanaMensajes ven;
	public static String COMPARAR ="COMPARAR";
	public static String ATRAS ="ATRAS";
	public static String MENSAJE ="MENSAJE";
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
		p.setEcgs(Conexion.consultaECG(p));
		FichaPaciente fp = new FichaPaciente(p);
		fp.addControlMensa(new ControladorMensaje(p,(Usuario)m));
		if(aux==0) {
			ecg.setLeido(true);
			Conexion.sentenciaSQL("UPDATE ECG set leido="+Constantes.LEIDO+" Where id="+ecg.getId()+";");
			int i=0;
			while(i<p.getEcgs().size() && !p.getEcgs().get(i).equals(ecg) ) {
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
			if(ven!=null) {
				ven.dispose();
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
			if(ven!=null) {
				ven.dispose();
			}
			if(aux==0) {
				vm.getBtnRevisarEcg().doClick();
			} else {
				vm.getBtnBuscarPacientes().doClick();
			}
		} else if(cmd.equals(GUARDAR)) {
			String mon=Calendar.getInstance().get(Calendar.MONTH)+"";
			if(mon.length()<2) {
				mon="0"+mon;
			}
			String day=Calendar.getInstance().get(Calendar.DATE)+"";
			if(day.length()<2) {
				day="0"+day;
			}
			if(!fi.getText().isEmpty()) {
				System.out.println(ecg.getId());
				ecg.setDiagnostico(fi.getText());
				Conexion.sentenciaSQL("Update ECG set diagnostico='"+fi.getText()+"', fechadediagnostico="+Integer.parseInt(Calendar.getInstance().get(Calendar.YEAR)+""+mon+""+day)+" where id="+ecg.getId());
			} else {
				ecg.setDiagnostico("");
				Conexion.sentenciaSQL("Update ECG set diagnostico=' ', fechadediagnostico="+Integer.parseInt(Calendar.getInstance().get(Calendar.YEAR)+""+mon+""+day)+" where id="+ecg.getId());
			}
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
		System.out.println(ecg.getId());
		Conexion.sentenciaSQL("UPDATE ECG set leido="+Constantes.LEIDO+" Where id="+ecg.getId()+";");
		
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		for(int i=0;i<vm.getCentro().getComponentCount();i++) {
			if(vm.getCentro().getComponent(i) instanceof FichaPaciente) {
				FichaPaciente aux=(FichaPaciente) (vm.getCentro().getComponent(i));
				for(int j=0;j<aux.getTab().getComponentCount();j++) {
					if(aux.getTab().getComponentAt(j) instanceof JPanel) {
						JPanel panel=(JPanel) aux.getTab().getComponentAt(j);
						for(int h=0;h<panel.getComponentCount();h++) {
							if(panel.getComponent(h) instanceof GraficaECG) {
								GraficaECG g=(GraficaECG)(panel.getComponent(h));
								g.setStop(true);
							}
						}
					}
				}
				
			}
		}
		
	}



}
