package View;

import javax.swing.JPanel;


import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;

import Control.ControladorFicha;
import Control.ControladorMensaje;
import Control.ControladorPanel;
import Control.ControladorPanelM;
import Control.GraphController;
import Model.Mensaje;
import Model.Paciente;
import Model.Usuario;


/**
 * DetallePaciente es aquella clase que se encarga de mostrar la ficha
 * del paciente propia que obtenemos al acceder desde la VentanaTecnico.
 * DetallePaciente es un panel en el cual se agregan una serie de componentes
 * graficos para dar forma a la ficha de paciente que queremos obtener.
 * 
 * @author Heartlight
 *
 * @version Final
 *
 * @see ControladorFicha
 * @see GraphController
 */
public class DetallePaciente extends JPanel {
	private static final long serialVersionUID = 1L;
	private Logo lblNewLabel_1;
	private JButton btnTomarDatos;
	private JButton btnStop;
	private JButton btnEnivar;
	private JLabel lblNewLabel;
	private JLabel lblApellidos ;
	private JLabel lblObservaciones;
	private JLabel lblDni;
	private JTextPane textArea;
	private JButton button;
	private Paciente p;
	private GraficaECG ecg;
	private JTextArea obser;
	private JButton mens;
	
	
	
	
	/**
	 * Getter del boton que se encarga de enviar los datos
	 * @return btnEnivar JButton
	 */
	public JButton getBtnEnivar() {
		return btnEnivar;
	}
	/**
	 * Getter del are de texto que posee las observaciones
	 * @return obser JTextArea
	 */
	public JTextArea getObser() {
		return obser;
	}
	
	/**
	 * Setter para agregar informacion del paciente
	 * @param p PacienteTecnico
	 */
	public void setP(Paciente p) {
		this.p = p;
	}
	/**
	 * Getter para obtener la informacion del paciente
	 * @return p PacienteTecnico
	 */
	public Paciente getP() {
		return p;
	}
	
	/**
	 * Getter de la etiqueta de apellidos
	 * @return lblApellidos JLabel
	 */
	public JLabel getLblApellidos() {
		return lblApellidos;
	}
	/**
	 * Getter de la etiqueta que contiene la imagen
	 * @return lblNewLabel JLabel
	 */
	public JLabel getLblNewLabel() {
		return lblNewLabel;
	}
	/**
	 * Getter de la etiqueta del DNI
	 * @return lblDni JLabel
	 */
	public JLabel getLblDni() {
		return lblDni;
	}
	

	
	/**
	 * Constructor de la clase DetallePaciente en el cual
	 * se crea la vista de dicha clase a traves de layouts y 
	 * elementos graficos.
	 * 
	 * @param p PacienteTecnico
	 */
	public DetallePaciente(Paciente p) {
		this.p=p;
		this.setOpaque(false);
		Font font=new Font("",Font.BOLD,15);
		//Fondo fon = new Fondo(this,fondo2);
		//this.add(fon);
		obser=new JTextArea();
		mens=new JButton("Mensajes");
		mens.setActionCommand(ControladorMensaje.MENSAJE);
		obser.setLineWrap(true);
		obser.setWrapStyleWord(true);
		button = new JButton("<- Atras");
		button.setActionCommand(ControladorFicha.ATRAS);
		JPanel fo=new JPanel();
		if(p.getFoto()!=null) {
			lblNewLabel_1=new Logo(fo,p.getFoto());
		} else {
			lblNewLabel_1=new Logo(fo,"Resource/Imagenes/Hombre.png");
		}
		int h=110;
    	double w= 85;
    	lblNewLabel_1.centrado(true);
    	lblNewLabel_1.setPreferredSize(new Dimension((int) w,h));
    	lblNewLabel_1.setBackground(Color.WHITE);
		lblNewLabel_1.setLayout(new BorderLayout());
		fo.setLayout(new BorderLayout());
		
		btnTomarDatos = new JButton("Tomar datos");
		btnTomarDatos.setActionCommand(ControladorFicha.TOMAR);
		btnStop=new JButton("STOP");
		btnStop.setEnabled(false);
		btnStop.setActionCommand(ControladorFicha.STOP);
		btnEnivar = new JButton("Enviar");
		btnEnivar.setActionCommand(ControladorFicha.ENVIAR);
		lblNewLabel = new JLabel(p.getNombre());
		lblApellidos = new JLabel(p.getApellido());
		lblDni = new JLabel(p.getDni() );
		lblNewLabel.setFont(font);
		lblApellidos.setFont(font);
		lblDni.setFont(font);
		lblObservaciones = new JLabel("Observaciones:   ");
		textArea = new JTextPane();  

	    JLabel l=new JLabel(" ");
	    l.setFont(new Font("",Font.BOLD,50));
		
		JPanel aux=new JPanel();
		aux.setOpaque(false);
		BorderLayout bor=new BorderLayout();
		bor.minimumLayoutSize(l);
		this.setLayout(bor);
		JPanel up=new Fondo(aux,"Resource/Imagenes/FondoPanel.jpeg");
		//up.setOpaque(false);
		//up.setBackground(Color.LIGHT_GRAY);
		up.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		JPanel iz=new JPanel();
		
		
		aux.setLayout(new BorderLayout());
		up.setLayout(new BorderLayout());
		FlowLayout izl=new FlowLayout();
		iz.setOpaque(false);
		izl.setAlignment(FlowLayout.LEFT);
		
		iz.setLayout(izl);
		
		
		JPanel datos=new JPanel();
		datos.setOpaque(false);
		datos.setLayout(new BoxLayout(datos, BoxLayout.Y_AXIS));
		datos.add(lblNewLabel);
		datos.add(lblApellidos);
		datos.add(lblDni);
		
		
		
		
		fo.add(lblNewLabel_1);
		iz.add(fo);
		iz.add(datos);
		
		
		
		up.add(iz,BorderLayout.WEST);
		up.add(btnEnivar,BorderLayout.EAST);
		
		JPanel panel=new JPanel();
		BorderLayout borde=new BorderLayout();
		borde.setHgap(5);
		panel.setLayout(borde);
		//panel.setOpaque(false);
	    ecg = new GraficaECG();
	    
	    GraphController control=new GraphController(ecg);
	    ecg.addController(control);
	    ecg.initUITEC();
	    
	    JPanel down=new JPanel();
	    BorderLayout borde2=new BorderLayout();
		borde2.setHgap(1);
	    down.setLayout(new BoxLayout(down,BoxLayout.PAGE_AXIS));
	    JPanel datosva=new JPanel();
	    datosva.setLayout(new FlowLayout());
	    down.add(obser);
	    JPanel auxi=new JPanel();
	    JButton invi2=new JButton();
	    invi2.setOpaque(false);
	    invi2.setBorderPainted(false);
	    invi2.setContentAreaFilled(false);
	    auxi.setLayout(borde2);
	    auxi.add(obser,BorderLayout.CENTER);
	    auxi.add(invi2,BorderLayout.SOUTH);
	    auxi.add(new JPanel(),BorderLayout.EAST);
	    obser.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
	    down.add(auxi);
	    
	    
	    BorderLayout borde3=new BorderLayout();
	    borde3.setHgap(25);
	    JPanel bu=new JPanel();
	    bu.setLayout(borde3);
	     FlowLayout fl2=new FlowLayout();
	     fl2.setAlignment(FlowLayout.LEFT);
	     JPanel pa=new JPanel();
	    pa.setLayout(fl2);
	    JPanel boton=new JPanel();
	    boton.setLayout(new BorderLayout());
	    JPanel aux2=new JPanel();
	    aux2.setLayout(new FlowLayout());
	    aux2.add(btnTomarDatos);
	    aux2.add(btnStop);
	    boton.add(aux2, BorderLayout.SOUTH);
	    boton.add(button, BorderLayout.NORTH);
	    pa.add(boton);
	    
	    JButton invi4=new JButton();
	    invi4.setOpaque(false);
	    invi4.setBorderPainted(false);
	    invi4.setContentAreaFilled(false);
	    
	    FlowLayout fl3=new FlowLayout();
	     fl3.setAlignment(FlowLayout.RIGHT);
	     JPanel basu=new JPanel();
	     basu.setLayout(new BorderLayout());
	     JPanel pa2=new JPanel();
	    pa2.setLayout(fl3);
	    basu.add(invi4,BorderLayout.CENTER);
	    basu.add(lblObservaciones,BorderLayout.SOUTH);
	    pa2.add(basu);
	    
	    FlowLayout fl4=new FlowLayout();
	     fl4.setAlignment(FlowLayout.RIGHT);
	    JPanel pul=new JPanel();
	    pul.setLayout(fl4);
	    pul.add(mens);
	    bu.add(pul,BorderLayout.CENTER);
	    bu.add(pa,BorderLayout.WEST);
	    bu.add(pa2,BorderLayout.EAST);
	    
	    
	    JButton invi=new JButton();
	    invi.setOpaque(false);
	    invi.setBorderPainted(false);
	    invi.setContentAreaFilled(false);
	  //  down.add(datosva);
	    panel.add(bu,BorderLayout.NORTH);
	    panel.add(ecg,BorderLayout.CENTER);
	    panel.add(invi,BorderLayout.SOUTH);
	    aux.add(up,BorderLayout.CENTER);

	    panel.add(down,BorderLayout.EAST);
	    this.add(aux,BorderLayout.NORTH);
	    this.add(panel,BorderLayout.CENTER);
	    
		
	}
	
	/**
	 * Getter de la grafica de ECGs
	 * @return ecg GraficaECG
	 */
	public GraficaECG getEcg() {
		return ecg;
	}
	
	/**
	 * Metodo que anade el controlador ControladorFicha
	 * a la clase DetallePaciente 
	 * @param cf ControladorFicha 
	 */
	public void addController(ControladorFicha cf){
		button.addActionListener(cf);
		btnTomarDatos.addActionListener(cf);
		btnEnivar.addActionListener(cf);
		ecg.addControllerTec(cf);
		btnStop.addActionListener(cf);
	}
	
	/**
	 * Getter del textArea
	 * @return textArea JTextPane
	 */
	public JTextPane getTextArea() {
		return textArea;
	}
	/**
	 * Setter del textArea
	 * @param textArea JTextPane
	 */
	public void setTextArea(JTextPane textArea) {
		this.textArea = textArea;
	}
	public void MensajeCont(ControladorMensaje me) {
		mens.addActionListener(me);
	}
	public void actUsuarioPaciente(Usuario us,Paciente p) {
		for(int i=0;i<mens.getActionListeners().length;i++) {
			if(mens.getActionListeners()[i] instanceof ControladorMensaje) {
				ControladorMensaje con=(ControladorMensaje)(mens.getActionListeners()[i]);
				con.setP(p);
				con.setUs(us);
			}
		}
	}
	public JButton getBtnStop() {
		return btnStop;
	}
	public void setBtnStop(JButton btnStop) {
		this.btnStop = btnStop;
	}
	public JButton getBtnTomarDatos() {
		return btnTomarDatos;
	}
	/**
	 * @return the lblNewLabel_1 / FOTO
	 */
	public Logo getLblNewLabel_1() {
		return lblNewLabel_1;
	}



}

