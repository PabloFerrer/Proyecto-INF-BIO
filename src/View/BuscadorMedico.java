package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import Control.ControladorBusMedico;
import Control.ControladorPanelM;
import Model.Medico;

/**
 * @author Pablo Ferrer Luis Ferrer Diego Fernandez
 * 
 * La clase BuscadorPaciente es un panel que pertenece al paquete View
 * y muestra una serie de paneles en vertical a trav�s de los cuales 
 * si clicamos uno de ellos accederemos a la ficha de paciente.
 * Nos permite buscar entre todos los pacientes a traves de un campo de 
 * texto.
 * Se hace uso de paneles adicionales para la colocacion de dichos elementos.
 * 
 * @version Final
 * 
 * @see ControladorBusMedico  
 * @see ControladorPanelM
 * @see Medico
 */
public class BuscadorMedico extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JPanel pan1;
	private JPanel pan2;
	private JPanel pan3;
	private JPanel pan4;
	private JPanel pan5;
	private JPanel pan6;
	private JPanel rey2;
	private JPanel rey5;

	private JTextField buscador;
	JScrollPane jsp;
	private FichaPaciente fp;
	
	/**
	 * Getter de la ficha de paciente
	 * @return FichaPaciente
	 * @see FichaPaciente
	 */
	public FichaPaciente getFp() {
		return fp;
	}
	
	/**
	 * Constructor de la clase BuscadorMedico
	 */
	public BuscadorMedico(){
	
	}
	
	/**
	 * Metodo que nos permite crear la vista del panel de BuscadorMedico 
	 * En este metodo se crean una serie de paneles y objetos que se a�adiran
	 * al panel principal con un borderlayout de modo que quede bien cuadrado.
	 * Con un for crea los paneles que se agregan verticalmente con un boxlayout
	 * Se hace uso de paneles adicionales para la colocacion de dichos elementos.
	 * 
	 * @param m Medico 
	 * @param vm VentanaMedico 
	 */
	public void crearVista(Medico m,VentanaMedico vm){
		this.setLayout(new BorderLayout());
	
		

									//PANELES DE REYENO PARA EL BORDERLAYOUT
		//AQUI VAN PANELES CON COSAS RARAS COMO BOTONES INVISIBLES QUE SIRVEN PARA REYENAR
		//O SIMPLEMENTE PANELES PARA PONER DENTRO DE OTROS PANELES
		
		pan1 = new JPanel();
		pan1.setLayout(new BorderLayout());
		pan1.setOpaque(false);
		
		pan2 = new JPanel();
		pan2.setLayout(new BorderLayout());
		pan2.setOpaque(false);
		
		pan3 = new JPanel();
		pan3.setOpaque(false);
		
		pan4 = new JPanel();
		pan4.setLayout(new BorderLayout());
		pan4.setOpaque(false);
		
		pan5 = new JPanel();
		FlowLayout fl = new FlowLayout();
		fl.setAlignment(FlowLayout.RIGHT);
		pan5.setLayout(fl);
		pan5.setOpaque(false);
		
		pan6 = new JPanel();
		pan6.setLayout(new BorderLayout());
		pan6.setOpaque(false);
		
		jsp = new JScrollPane();
		
		
		//PANEL EN EL QUE VAN LA ETIQUETA Y EL BUSCADOR Y EL BOTON
		rey2 = new JPanel();
		rey2.setLayout(new GridLayout(1,2));
		rey2.setOpaque(false);
		
		
		//PANEL QUE TIENE LA FICHA DEL PACIENTE
		
		rey5 = new JPanel();
		rey5.setLayout(new BoxLayout(rey5,BoxLayout.Y_AXIS));
		rey5.setBorder(new LineBorder(Color.gray, 2));
		rey5.setOpaque(false);
		
		//APARTADO EN EL QUE SE CREAN OBJETOS
		JButton invisible = new JButton();
		invisible.setOpaque(false);
		invisible.setBorderPainted(false);
		invisible.setContentAreaFilled(false);
		JButton invisible2 = new JButton();
		invisible2.setOpaque(false);
		invisible2.setBorderPainted(false);
		invisible2.setContentAreaFilled(false);
		JButton invisible3 = new JButton();
		invisible3.setOpaque(false);
		invisible3.setBorderPainted(false);
		invisible3.setContentAreaFilled(false);
		JButton invisible4 = new JButton();
		invisible4.setOpaque(false);
		invisible4.setBorderPainted(false);
		invisible4.setContentAreaFilled(false);
		JButton invisible5 = new JButton();
		invisible5.setOpaque(false);
		invisible5.setBorderPainted(false);
		invisible5.setContentAreaFilled(false);
		JButton invisible6 = new JButton();
		invisible6.setOpaque(false);
		invisible6.setBorderPainted(false);
		invisible6.setContentAreaFilled(false);
		JButton invisible7 = new JButton();
		invisible7.setOpaque(false);
		invisible7.setBorderPainted(false);
		invisible7.setContentAreaFilled(false);
				
		buscador = new JTextField(17);	
		buscador.addKeyListener(new ControladorBusMedico(this,vm,m));

		
		//APARTADO EN EL QUE SE A�ADEN PANELES A MAS PANELES
		
		pan5.add(buscador);
		pan5.add(invisible5);
		
		rey2.add(pan5);
		
		for(int i= 0;i<m.getPacientes().size();i++){
			PanelPaciente pan = new PanelPaciente(m.getPacientes().get(i));
			pan.setBorder(new LineBorder(Color.gray, 2));
			ControladorPanelM cp = new ControladorPanelM(vm,m.getPacientes().get(i),m);
			pan.addMouseListener(cp);
			JPanel invi = new JPanel();
			invi.setOpaque(false);
			rey5.add(pan);	
			rey5.add(invi);
		}
		
		if(m.getPacientes().size()<10) {
			for(int i=m.getPacientes().size();i<10;i++) {
			JPanel relle=new JPanel();
			relle.setLayout(new BorderLayout());
			JButton b=new JButton();
			b.setContentAreaFilled(false);
			b.setOpaque(false);
			b.setBorderPainted(false);
			relle.add(b,BorderLayout.CENTER);
			relle.setOpaque(false);
			rey5.add(relle);
			}
		}
		
		jsp.setViewportBorder(null);
		 jsp.setOpaque(false);
		 jsp.getViewport().setOpaque(false);
		 jsp.setBorder(null);
		 jsp.getViewport().setBorder(null);
		jsp.setViewportView(rey5);
		pan4.add(rey2, BorderLayout.NORTH);
		
		pan4.add(invisible,BorderLayout.WEST);
		pan4.add(invisible2,BorderLayout.EAST);
		pan4.add(jsp,BorderLayout.CENTER);
		pan4.add(invisible7,BorderLayout.SOUTH);
		
		
		add(pan4,BorderLayout.CENTER);
		add(invisible3,BorderLayout.WEST);
		add(invisible4,BorderLayout.EAST);
		add(invisible6,BorderLayout.SOUTH);
		
		this.setOpaque(false);
	}
	
	/**
	 * Getter de los paneles que se crean horizonalmente
	 * @return JPanel rey5
	 */
	public JPanel getRey5() {
		return rey5;
	}
	
	public void addController(ControladorBusMedico cbm){

	}
	
	/**
	 * Metodo que implementamos para hacer visible el panel
	 */
	public void ver(){
		this.setVisible(true);
	}

	/**
	 * Getter del jtextfield buscador que nos permite acceder al buscador
	 * @return JTextField buscador
	 */
	public JTextField getBuscador() {
		return buscador;
	}
	
}