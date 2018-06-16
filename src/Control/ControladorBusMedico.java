package Control;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import View.BuscadorMedico;
import View.PanelPaciente;
import View.VentanaMedico;
import Model.Medico;
import Model.MutableInt;
import Model.Paciente;
import Model.Utilidades;

/**
 * La clase ControladorBusMedico se encarga de hacer de controlador para 
 * la clase BuscadorMedico. En esta clase lo que haremos sera configurar el
 * KeyListener, en concreto KeyTyped para poder buscar pacientes en el buscador
 * a traves de un campo de texto.
 * 
 * @author Heartlight
 * 
 * @version Final
 * 
 * @see BuscadorMedico
 * @see PanelPaciente
 * @see VentanaMedico
 * @see Medico
 * @see Paciente
 * @see PacienteTecnico
 */
public class ControladorBusMedico implements  KeyListener {

	private BuscadorMedico bm;
	private VentanaMedico vm;
	private Medico m;
	
	/**
	 * Constructor de la clase ControladorBusMedico
	 * 
	 * @param bm BuscadorMedico 
	 * @param vm VentanaMedico 
	 * @param m Medico 
	 */
	public ControladorBusMedico(BuscadorMedico bm,VentanaMedico vm,Medico m){
		this.bm = bm;
		this.vm = vm;
		this.m = m;
	}
	

	/** 
	 * Metodo keyPressed propio de un keyListener
	 * @param arg0 KeyEvent 
	 */
	public void keyPressed(KeyEvent arg0) {
		
		
	}

	/** 
	 * Metodo keyReleased propio de un keyListener
	 * @param arg0 KeyEvent 
	 */
	public void keyReleased(KeyEvent arg0) {
		
	}

	/** 
	 * Metodo keyTyped propio de un keyListener en el cual
	 * a traves de una serie de concatenaciones condicionales, 
	 * permitimos que al escribir en el campo de texto se creen de nuevo
	 * los paneles que contienen a cada paciente dependiendo del conjunto
	 * de caracteres que se halla escrito mirando cada vez en "nombre",
	 * "apellido" o "DNI".
	 * 
	 * @param arg0 KeyEvent 
	 */
	public void keyTyped(KeyEvent arg0) {

		String auxi = bm.getBuscador().getText().toString();

		if (Character.isAlphabetic(arg0.getKeyChar()) || Character.isDigit(arg0.getKeyChar())) {
			auxi = auxi + arg0.getKeyChar();
		}

		String[] aux0 = auxi.split(" ");
		Utilidades.quicksortPalabras(aux0, 0, aux0.length-1);
		Vector<String> aux = new Vector<String>();
		for (int i = 0; i < aux0.length; i++) {
			if (!aux0[i].equals(" ") || !aux0[i].equals("")) {
				aux.add(aux0[i]);
			}
		}
		
		@SuppressWarnings("unchecked")
		ArrayList<Paciente> pacientes = ((ArrayList<Paciente>) this.m.getPacientes().clone());
		MutableInt nombre;
		MutableInt dni;
		MutableInt fape;
		MutableInt sape;
		
		for (int i = pacientes.size()-1; i >=0 ; i--) {
			nombre=new MutableInt(0);
			 dni=new MutableInt(0);
			 fape=new MutableInt(0);
			 sape=new MutableInt(0);
			 boolean noMatch=false;
			 int j = 0; 
			 while(j < aux.size() && !noMatch) {
			Paciente pa = pacientes.get(i);
			if (((nombre.toInteger()==0)? (pa.getNombre().toLowerCase().startsWith(aux.get(j).toLowerCase())? aumentar(nombre):false):false)
					|| ((dni.toInteger()==0)?(pa.getDni().toLowerCase().toString().startsWith(aux.get(j).toLowerCase())? aumentar(dni):false):false)
					|| ((fape.toInteger()==0)?(pa.getApellido().toLowerCase().toString().split(" ")[0].startsWith(aux.get(j).toLowerCase())? aumentar(fape):false):false)
					|| ((pa.getApellido().split(" ").length>1)?((sape.toInteger()==0)?(pa.getApellido().toLowerCase().toString().split(" ")[1].startsWith(aux.get(j).toLowerCase())? aumentar(sape):false):false):false)
							) {
			} else {
				noMatch=true;
				pacientes.remove(pa);
			}
			j++;
		}
		}
	
		bm.getRey5().setVisible(false);
		if (aux.size() != 0) {
			bm.getRey5().removeAll();
			bm.getRey5().setLayout(new BoxLayout(bm.getRey5(), BoxLayout.Y_AXIS));
				for (int i = 0; i < pacientes.size(); i++) {
						PanelPaciente pan = new PanelPaciente(pacientes.get(i));
						pan.setBorder(new LineBorder(Color.gray, 2));

						ControladorPanelM cpm = new ControladorPanelM(vm, pacientes.get(i),m);
						pan.addMouseListener(cpm);
						JPanel invi = new JPanel();
						invi.setOpaque(false);

						bm.getRey5().add(pan);
						bm.getRey5().add(invi);
					}
			
		} else {
			pacientes=this.m.getPacientes();
			bm.getRey5().removeAll();
			bm.getRey5().setLayout(new BoxLayout(bm.getRey5(), BoxLayout.Y_AXIS));
			for (int i = 0; i < this.m.getPacientes().size(); i++) {
				PanelPaciente pan = new PanelPaciente(m.getPacientes().get(i));

				pan.setBorder(new LineBorder(Color.gray, 2));
				ControladorPanelM cp = new ControladorPanelM(vm, m.getPacientes().get(i),m);
				pan.addMouseListener(cp);
				JLabel invi = new JLabel("lalalalalal");
				invi.setVisible(false);

				bm.getRey5().add(pan);
				bm.getRey5().add(invi);

			}
		}
		
		if (pacientes.size() < 10) {
			for (int i = pacientes.size(); i < 10; i++) {
				JPanel relle=new JPanel();
				relle.setLayout(new BorderLayout());
				JButton b=new JButton();
				b.setContentAreaFilled(false);
				b.setOpaque(false);
				b.setBorderPainted(false);
				relle.add(b,BorderLayout.CENTER);
				relle.setOpaque(false);
				bm.getRey5().add(relle);
			}

		}

		bm.getRey5().setVisible(true);
		
	}

	private boolean aumentar(MutableInt nombre) {
		nombre.setValue(nombre.toInteger()+1);
		return true;
	}

	
}
