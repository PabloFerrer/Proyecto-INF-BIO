package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.LineBorder;

import View.DetallePaciente;
import Control.ControladorFicha;
import Control.ControladorPanel;
import Control.ControladorTecnico;
import Model.Paciente;
import Model.Tecnico;
import Model.Usuario;

/**
 * @author Heartlight
 * 
 * La clase VentanaTecnico se corresponde con el JFrame en el cual
 * se situan una serie de paneles que daran forma al frame, teniendo en 
 * el margen izquierdo una lista con todos los pacientes a los que puede 
 * acceder el tecnico, teniendo opcion a buscar entre ellos a traves de 
 * un campo de texto de forma que se acceda al paciente buscado.
 * 
 * @version Final
 * 
 * @see DetallePaciente
 * @see ControladorFicha
 * @see ControladorPanel 
 * @see ControladorTecnico
 * @see Fondo
 * @see Logo
 * @see PacienteTecnico
 * @see Usuario
 *
 */
public class VentanaTecnico extends JFrame {
	
	private static final long serialVersionUID = 1L;
	/*
	 * Declaracion de variables requeridas para la clase
	 */
	private JButton ayuda;
	private JButton logout;
	private JLabel bienvenido;
	private JLabel usuario;
	private JLabel paciente;
	private Fondo bcg ;
	private DetallePaciente ficha;
	private Tecnico tec;
	private JTextField buscador;
	private JPanel rey4;
	private JScrollPane jsp;
	
	
	public DetallePaciente getFicha() {
		return ficha;
	}

	/**
	 * Setter para la ficha del paciente 
	 * @param ficha DetallePaciente 
	 */
	public void setFicha(DetallePaciente ficha) {
		this.ficha = ficha;
	}

	
	
	 /**
	  * Getter del ScrollPane de la lista de pacientes 
	  * @return jsp JScrollPane
	  */
	public JScrollPane getJsp() {
		return jsp;
	}



	/**
	  * Setter del ScrollPane de la lista de pacientes 
	  * @param jsp JScrollPane
	  */
	public void setJsp(JScrollPane jsp) {
		this.jsp = jsp;
	}



	/**
	 * Getter del JPanel que contiene la lista de pacientes
	 * @return JPanel rey4
	 */
	public JPanel getRey4() {
		return rey4;
	}



	/**
	 * Setter del JPanel que contiene la lista de pacientes
	 * @param rey4 JPanel 
	 */
	public void setRey4(JPanel rey4) {
		this.rey4 = rey4;
	}



	/**
	 * Constructor de la clase VentanaTecnico
	 * @param au Usuario 
	 */
	public VentanaTecnico(Tecnico tec){
		this.tec=tec;
		
		
	}
	
	/** 
	 * Metodo que crea la vista de la clase VentanaTecnico requiriendo la 
	 * creacion de diversos paneles y elementos graficos para ello.
	 * Se crean los paneles del buscador de pacientes a traves de un for 
	 * incluyendolos en un panel con un boxlayout vertical
	 * Se hace uso de paneles adicionales para la colocacion de dichos elementos.
	 * 
	 * @param pacientes ArrayList de PacienteTecnico
	 */
	public void crearVista(ArrayList<Paciente> pacientes,Tecnico t){
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	ImageIcon img = new ImageIcon("Resource/Imagenes/Logos/logo-cardio-finito100x100.png");
	this.setIconImage(img.getImage());
		
	int x=(int) (Toolkit.getDefaultToolkit().getScreenSize().width);
	int y=(int) (Toolkit.getDefaultToolkit().getScreenSize().height);
	
	int porceX=90;
	int porceY=80;
	
	
	this.setLocation(x/2-(int) (x*porceX/100)/2,(int) (y/2)-(int) (y*porceY/100)/2);
	this.setSize(x*porceX/100, y*porceY/100);
	this.setMinimumSize(new Dimension((int)x*60/100,(int) y*70/100));
	
	bcg = new Fondo(this,"Resource/Imagenes/fondo.jpeg");
	bcg.setLayout(new BorderLayout());
	
									//CREO PANELES DE REYENO PARA EL BORDER LAYOUT
	
	/*AQUI CREO PANELES Y OBJETOS QUE NO SIRVEN PARA NADA MAS QUE PARA REYENAR POSICIONES 
	 * DE FORMA QUE SE ME COLOQUEN LAS COSAS 
	 */
	JPanel pan1 = new JPanel();
	pan1.setOpaque(false);
	BorderLayout bor=new BorderLayout();
	bor.setHgap(5);
	pan1.setLayout(bor);
	JPanel p=new JPanel();
	p.setOpaque(false);
	pan1.add(p,BorderLayout.WEST);
	
	JPanel pan2 = new JPanel();
	pan2.setLayout(new BorderLayout());
	pan2.setOpaque(false);
	JPanel aux=new JPanel();
	aux.setOpaque(false);
	aux.setLayout(new BorderLayout());
	Logo logo=new Logo(aux);
	logo.setOpaque(false);
	logo.setLayout(new BorderLayout());
	JButton bu=new JButton();
	bu.setVisible(false);
	logo.add(bu,BorderLayout.CENTER);
	aux.add(logo);
	pan1.add(aux,BorderLayout.CENTER);
	
	//PANEL QUE PONE FLOW AL CENTRO
	JPanel colocar = new JPanel();
	colocar.setLayout(new FlowLayout());
	colocar.setOpaque(false);
	
	//ESTE PANEL TIENE EL BOTON DE AYUDA 
	JPanel rey1 = new JPanel();
	rey1.setLayout(new BorderLayout());
	rey1.setOpaque(false);
	
	//ESTE PANEL TIENE LAS LABEL DE BIENVENIDO Y EL NOMBRE
	JPanel rey2 = new JPanel();
	rey2.setLayout(new GridLayout());
	rey2.setOpaque(false);
	
	//PANEL DE REYENO ESQUINA SUPERIOR DERECHA
	JPanel rey3 = new JPanel();
	rey3.setLayout(new GridLayout(2,3));
	rey3.setOpaque(false);
	
	//PANEL QUE CONTIENE LOS PANELES DE CADA PACIENTE
	
	rey4 = new JPanel();
	rey4.setOpaque(false);
	
	rey4.setLayout(new BoxLayout(rey4,BoxLayout.Y_AXIS));
	
	
	//PANEL QUE CONTIENE EL BUSCADOR
	
	JPanel rey5 = new JPanel();
	rey5.setLayout(new GridLayout(2,1));
	rey5.setOpaque(false);
	
	//PANEL QUE CONTIENE AL SCROLLPANE QUE CONTIENE A LOS PANELES DE PACIENTES
	JPanel rey6 = new JPanel();
	rey6.setLayout(new BorderLayout());
	rey6.setOpaque(false);
	
	//CREO LA FICHA 
	ficha = new DetallePaciente(new Paciente("","","","",0));
	ficha.addController(new ControladorFicha(ficha,this));
	colocar.add(ficha);
	ficha.setVisible(false);
	
	//LABEL DE PACIENTES
	paciente = new JLabel("Buscador de pacientes");
	paciente.setFont(new Font("",Font.BOLD,19));
	
	
	//CREAR Y DAR CARACTERISTICAS A LA LABEL BIENVENIDO
	bienvenido = new JLabel("   Bienvenido");
	bienvenido.setFont(new Font("",Font.BOLD,19));
	
	//CREAR Y DAR CARACTERISTICAS A LA LABEL USUARIO
	usuario = new JLabel("Sr./Sra. " +tec.getNombre());
	//usuario.setText(vl.user.getText());
	usuario.setFont(new Font("",Font.BOLD,14));
	
	//AQUI LE DOY SUS CARACTERISTICAS AL BOTON DE AYUDA
	ayuda = new JButton();
	ayuda.setIcon(new ImageIcon("Resource/Imagenes/help.png"));
	ayuda.setActionCommand(ControladorTecnico.HELP);
	ayuda.setContentAreaFilled(false);
	ayuda.setBorderPainted(false);
	ayuda.setOpaque(false);
	
	//AQUI LE DOY SUS CARACTERISTICAS AL BOTON DE LOGOUT
	logout = new JButton();
	logout.setIcon(new ImageIcon("Resource/Imagenes/off.png"));
	logout.setActionCommand(ControladorTecnico.LOGOUT);
	logout.setContentAreaFilled(false);
	logout.setBorderPainted(false);
	logout.setOpaque(false);
	
	//CREO EL CUADRO DEL BUSCADOR
	buscador = new JTextField();
	buscador.setSize(80, 23);
	
	
	
	
	for(int i= 0;i<pacientes.size();i++){
		System.out.println(pacientes.get(i).getDni());
			PanelPaciente pan = new PanelPaciente(pacientes.get(i));
			pan.setBorder(new LineBorder(Color.gray, 2));
			ControladorPanel cp = new ControladorPanel(this,pacientes.get(i),t);
			pan.addMouseListener(cp);
			rey4.add(pan);	
		}
	if(pacientes.size()<10) {
		for(int i= pacientes.size();i<10;i++){
			JPanel pan=new JPanel();
			//pan.setOpaque(false);
			JLabel a=new JLabel(" ");
			a.setFont(new Font("",15,45));
			pan.add(a);
			rey4.add(pan);
		}
	}
	
	
	//CREO EL SCROLL
	 jsp = new JScrollPane();
	 jsp.getViewport().setOpaque(false);
	 jsp.setBackground(Color.red);
	 jsp.setOpaque(false);
	// jsp.setBorder(new LineBorder(Color.black, 5));
     jsp.setViewportView(rey4);
     rey5.add(paciente);
     rey5.add(buscador);
     rey6.add(rey5,BorderLayout.NORTH);
     rey6.add(jsp, BorderLayout.CENTER);
	
	rey3.add(new Fondo());
	rey3.add(bienvenido);
	rey3.add(logout);
	rey3.add(new Fondo());
	rey3.add(new Fondo());
	rey3.add(usuario);
	

	
	rey1.add(ayuda,BorderLayout.EAST);
	rey2.add(pan1,BorderLayout.CENTER);
	rey2.add(pan2,BorderLayout.WEST);
	rey2.add(rey3,BorderLayout.EAST);
	
	
	bcg.add(rey1,BorderLayout.SOUTH);
	bcg.add(rey2,BorderLayout.NORTH);
	bcg.add(rey6,BorderLayout.WEST);
	bcg.add(ficha,BorderLayout.CENTER);
	
	
	this.add(bcg);
	
	}
	
	public Tecnico getTec() {
		return tec;
	}



	/**
	 * Metodo que implementamos para hacer visible el panel
	 */
	public void ver(){
		this.setVisible(true);
	}
	
	/**
	 * Getter del jtextfield buscador que nos permite acceder al buscador
	 * @return buscador JTextField 
	 */
	public JTextField getBuscador() {
		return buscador;
	}
	
	/**
	 * Metodo que nos permite agregar el controlador de la clase VentanaTecnico 
	 * @param ct ControladorTecnico 
	 */
	public void addController(ControladorTecnico ct){
		ayuda.addActionListener(ct);
		logout.addActionListener(ct);
		buscador.addKeyListener(ct);
			
	}

	
}