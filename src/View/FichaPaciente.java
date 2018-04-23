package View;

import Model.ECG;
import Model.Paciente;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.*;
import Control.ControladorPanelM;
import Control.GraphController;

/**
 * @author Heartlight
 * 
 * La clase FichaPaciente es un panel en el cual se adjuntaran los datos
 * del paciente en cuestion que cliquemos en buscador medico.
 * Mostrara el ECG del paciente y opciones como compararar los ECGs del paciente
 * y guardar el diagnostico realizado.
 * 
 * @version Final
 * 
 * @see ControladorPanelM
 * @see GraphController
 *
 */
public class FichaPaciente extends JPanel {

	private Logo imagen;
	private JLabel nombre;
	private JLabel ape;
	private JLabel dni;
	private JLabel ss;
	private JLabel poblacion;
	private JTabbedPane tab;
	private JButton invi;
	private JButton invi2;
	private JButton invi3;
	private JButton invi4;
	private JButton comparar;
	private JButton atras;
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor de la clase FichaPaciente en el cual se crea la vista
	 * anadiendo una serie de paneles y componentes al panel principal que posee 
	 * un borderlayout, de forma que se coloquen los elementos satisfactoriamente.
	 * Se hace uso de paneles adicionales para la colocacion de dichos elementos.
	 * 
	 * @param p Paciente 
	 */
	public FichaPaciente(Paciente p){
		
		this.setLayout(new BorderLayout());
		
		//AQUI CREO PANELES Y LES DOY CARACTERISTICAS
		tab = new JTabbedPane();
		
		JPanel pan1 = new JPanel();
		pan1.setLayout(new BorderLayout());
		pan1.setOpaque(false);
		
		JPanel pan2 = new JPanel();
		pan2.setLayout(new BoxLayout(pan2,BoxLayout.Y_AXIS));
		
		FlowLayout fl = new FlowLayout();
		fl.setAlignment(FlowLayout.CENTER);
		
		JPanel pan3 = new JPanel();
		pan3.setLayout(new BoxLayout(pan3,BoxLayout.PAGE_AXIS));
		
		JPanel pan5 = new JPanel();
		pan5.setLayout(fl);
		
		JPanel pan6 = new JPanel();
		pan6.setLayout(fl);
		
		JPanel pan7 = new JPanel();
		pan7.setLayout(fl);
		
		JPanel pan8 = new JPanel();
		pan8.setLayout(fl);
		
		JPanel pan9 = new JPanel();
		pan9.setLayout(new BorderLayout());
		
		JPanel pan10 = new JPanel();
		pan10.setLayout(new FlowLayout());
		
		JPanel pan11 = new JPanel();
		pan11.setLayout(new BorderLayout());
		
		JPanel pan12 = new JPanel();
		pan12.setLayout(new FlowLayout());
		
		JPanel pan4 = new JPanel();
		pan4.setLayout(new BorderLayout());
		
		
		
		//AQUI CREO OBJETOS
		
		imagen = new Logo(pan3,"Resource/Imagenes/hombre.png");
		imagen.centrado(true);
		
		nombre = new JLabel(p.getNombre());
		nombre.setFont(new Font("",Font.BOLD,15));
		
		ape=new JLabel(p.getApellido());
		ape.setFont(new Font("",Font.BOLD,15));
		
		
		ss = new JLabel("S.S.:     " + p.getSs());
		ss.setFont(new Font("",Font.BOLD,15));
		
		dni = new JLabel("DNI:     " + p.getDni());
		dni.setFont(new Font("",Font.BOLD,15));
		
		poblacion = new JLabel("Poblacion:   "+p.getPoblacion());
		poblacion.setFont(new Font("",Font.BOLD,15));
		
		invi= new JButton();
		invi.setOpaque(false);
		invi.setContentAreaFilled(false);
		invi.setBorderPainted(false);
		
		invi2= new JButton();
		invi2.setOpaque(false);
		invi2.setContentAreaFilled(false);
		invi2.setBorderPainted(false);

		invi3= new JButton();
		invi3.setOpaque(false);
		invi3.setContentAreaFilled(false);
		invi3.setBorderPainted(false);
		
		invi4= new JButton();
		invi4.setOpaque(false);
		invi4.setContentAreaFilled(false);
		invi4.setBorderPainted(false);
		
		for(ECG e : p.getEcgs()){
			JPanel north=new JPanel();
			north.setOpaque(false);
			north.setLayout(new FlowLayout(FlowLayout.RIGHT));
			JButton guardar=new JButton("Guardar Diagnostico");
			guardar.setActionCommand(ControladorPanelM.GUARDAR);
			
			JTextArea obser=new JTextArea();
			obser.setText(e.getDiagnostico());
			
			obser.setLineWrap(true);
			obser.setWrapStyleWord(true);
			
			guardar.addActionListener(new ControladorPanelM(e,obser));
			north.add(guardar);
			
			JPanel prueba = new JPanel(new BorderLayout(7,0));
			prueba.setOpaque(false);
			GraficaECG ecg = new GraficaECG();
			ecg.addGraphic(e);
			GraphController control=new GraphController(ecg);
			ecg.addController(control);
			ecg.initUITEC();
			JPanel info=new JPanel();
			JPanel pan13 = new JPanel();
			pan13.setLayout(new BorderLayout());
			JPanel pan14 = new JPanel();
			pan14.setOpaque(false);
			pan14.setLayout(new BorderLayout());
			pan13.add(new JLabel("Diagnostico",JLabel.CENTER),BorderLayout.NORTH);
			
			JButton invi5= new JButton();
			invi5.setOpaque(false);
			invi5.setContentAreaFilled(false);
			invi5.setBorderPainted(false);
			JButton invi6= new JButton();
			invi6.setOpaque(false);
			invi6.setContentAreaFilled(false);
			invi6.setBorderPainted(false);

			
			JPanel a=new JPanel();
			JPanel b=new JPanel();
			a.setOpaque(false);
			b.setOpaque(false);
			pan14.add(a,BorderLayout.EAST);
			pan14.add(b,BorderLayout.SOUTH);
			pan13.add(obser, BorderLayout.CENTER);
			pan14.add(pan13,BorderLayout.CENTER);
			info.setLayout(new BoxLayout(info,BoxLayout.Y_AXIS));
			info.add(new JLabel("Tecnico Responsable: "+e.getNombreTec()));
			info.add(new JLabel("Comentario del Tecnico: "+e.getComentarios()));
			info.add(new JLabel("Fecha de Realizacion:"+String.valueOf(e.getFecha()).substring(6, 8)+"-"+String.valueOf(e.getFecha()).substring(4, 6)+"-"+String.valueOf(e.getFecha()).substring(0, 4)));
			
			prueba.add(north,BorderLayout.NORTH);
			prueba.add(pan14,BorderLayout.EAST);
			prueba.add(ecg,BorderLayout.CENTER);
			prueba.add(info,BorderLayout.SOUTH);
			
			prueba.addMouseMotionListener(new ControladorPanelM(e,obser));
			tab.add(e.getNombre(), prueba);
			
		}
		
		
		
		comparar = new JButton("Comparar ECGS");
		comparar.setActionCommand(ControladorPanelM.COMPARAR);
		
		atras = new JButton("Atras");
		atras.setActionCommand(ControladorPanelM.ATRAS);
		
		//AQUI A�ADO ELEMENTOS EN CADA PANEL O PANELES A MAS PANELEs
		pan3.add(imagen);
		
		pan2.add(new JLabel("        "));
		pan2.add(new JLabel("        "));
		pan2.add(pan3);
		pan2.add(new JLabel("        "));
		pan2.add(nombre);
		pan2.add(new JLabel("        "));
		pan2.add(ape);
		pan2.add(new JLabel("        "));
		pan2.add(poblacion);
		pan2.add(new JLabel("                                                  "));
		pan2.add(dni);
		pan2.add(new JLabel("        "));
		pan2.add(ss);
		pan2.add(pan5);
		pan2.add(pan6);
		pan2.add(pan7);
		
		
										
		
		tab.setVisible(true);
		
		pan10.add(comparar);
		
		pan12.add(atras);
		
		pan11.add(pan10,BorderLayout.EAST);
		pan11.add(pan12,BorderLayout.WEST);
		
		pan9.add(tab, BorderLayout.CENTER);
		pan9.add(pan11,BorderLayout.NORTH);
		
		this.add(invi2, BorderLayout.NORTH);
		this.add(pan9,BorderLayout.CENTER);
		this.add(invi,BorderLayout.WEST);
		this.add(pan2,BorderLayout.EAST);
		this.add(invi3,BorderLayout.SOUTH);
		this.setOpaque(false);
		this.setVisible(true);
	}

	/**
	 * Getter de la ventana del TabbedPane
	 * @return JTabbedPane tab
	 */
	public JTabbedPane getTab() {
		return tab;
	}

	/**
	 * Getter de la etiqueta del nombre del paciente
	 * @return JLabel nombre
	 */
	public JLabel getNombre() {
		return nombre;
	}
	
	/**
	 * Getter de la etiqueta del DNI del paciente
	 * @return JLabel dni
	 */
	public JLabel getDni() {
		return dni;
	}
	
	/**
	 * Metodo que nos permite agregar el controlador de la clase 
	 * FichaPaciente 
	 * @param cpm ControladorPanelM 
	 */
	public void addController(ControladorPanelM cpm){
		comparar.addActionListener(cpm);
		atras.addActionListener(cpm);
		
	}
	
}