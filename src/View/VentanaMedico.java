package View;

import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.*;

import Control.ControladorMedico;
import Model.Medico;
import Pruebas.MemoCalendar;

public class VentanaMedico extends JFrame{
	/**
	 * @author Pablo Ferrer Luis Ferrer Diego Fernandez
	 * 
	 * La clase VentanaMedico es la ventana principal del usuario Medico
	 * desde ella puede crear un nuevo paciente, puede observar los ultimos ecg
	 * y ademas puede ver todos sus pacientes a parte de generar diagnosticos para cada
	 * uno
	 * 
	 * @version Final
	 * 
	 * @see VentanaMedicoECG
	 * @see BuscadorMedico
	 * @see Formulario
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Medico med;
	private JButton log;
	private JButton help;
	private JButton btnDardeAlta;
	private JButton btnRevisarEcg;
	private JButton btnBuscarPacientes;
	private JButton btnInicio;
	private JPanel centro;
	private Menu menu;
	
	
	public Menu getMenu() {
		return menu;
	}

	public JButton getBtnDardeAlta() {
		return btnDardeAlta;
	}

	public JButton getBtnInicio() {
		return btnInicio;
	}

	public JButton getBtnRevisarEcg() {
		return btnRevisarEcg;
	}

	public void setBtnRevisarEcg(JButton btnRevisarEcg) {
		this.btnRevisarEcg = btnRevisarEcg;
	}

	public JButton getBtnBuscarPacientes() {
		return btnBuscarPacientes;
	}

	public VentanaMedico(Medico au){
		this.med=au;
	}

	public void crearVista(MemoCalendar memo){
		//BASICO
		////////////////////////////////////////////////////////////////////////////////////////////////////
		
		ImageIcon img = new ImageIcon("Resource/Imagenes/usados/usado-cardio-finito100x100.png");
		this.setIconImage(img.getImage());
		int x=(int) (Toolkit.getDefaultToolkit().getScreenSize().width);
		int y=(int) (Toolkit.getDefaultToolkit().getScreenSize().height);
		this.setExtendedState( this.getExtendedState()|JFrame.MAXIMIZED_BOTH );
		
		this.setMinimumSize(new Dimension((int)(x*0.5),(int)(y*0.4)));
		this.setLayout(new BorderLayout());
		JPanel usado=new JPanel();
		usado.setSize(x, y);
		usado.setOpaque(false);
		Fondo fondo=new Fondo(this,"Resource/Imagenes/fondo.jpeg");
		fondo.setLayout(new BorderLayout());
		fondo.setSize(x, y);
		fondo.setOpaque(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		usado.setLayout(new BorderLayout());
		usado.setSize(x,y);
		usado.setOpaque(false);
		////////////////////////////////////////////////////////////////////////////////////////////////////
		//PANEL EN EL QUE HAREMOS TODO=USADO
		menu=new Menu();
		Font f=new Font("",Font.BOLD,25);
		usado.setLayout(new BorderLayout());
		btnInicio=new JButton("Inicio");
		btnInicio.setForeground(Color.white);
		btnInicio.setActionCommand(ControladorMedico.INICIO);
		btnInicio.setFont(f);
		btnBuscarPacientes=new JButton("Pacientes");
		btnBuscarPacientes.setForeground(Color.white);
		btnBuscarPacientes.setFont(f);
		btnBuscarPacientes.setActionCommand(ControladorMedico.PAC);
		btnDardeAlta=new JButton("Crear");
		btnDardeAlta.setForeground(Color.white);
		btnDardeAlta.setActionCommand(ControladorMedico.ALTA);
		btnDardeAlta.setFont(f);
		btnRevisarEcg=new JButton("Revisar ECG");
		btnRevisarEcg.setForeground(Color.white);
		btnRevisarEcg.setActionCommand(ControladorMedico.ECG);
		btnRevisarEcg.setFont(f);
		
		btnInicio.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 3, new Color(0,133,255)));
		btnRevisarEcg.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 3, new Color(0,133,255)));
		btnBuscarPacientes.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 3, new Color(0,133,255)));
		
         
//		if(System.getProperty("os.name").toString().toLowerCase().contains("mac")){
//			
//			menu.esMac();
//		} 
         btnRevisarEcg.setContentAreaFilled(false);
		btnDardeAlta.setContentAreaFilled(false);
		btnBuscarPacientes.setContentAreaFilled(false);
		btnInicio.setContentAreaFilled(false);
			
		btnRevisarEcg.setOpaque(true);
		btnDardeAlta.setOpaque(true);
		btnBuscarPacientes.setOpaque(true);
		btnInicio.setOpaque(true);
		
		btnRevisarEcg.setBorderPainted(false);
		btnDardeAlta.setBorderPainted(false);
		btnBuscarPacientes.setBorderPainted(false);
		btnInicio.setBorderPainted(false);
		
		btnRevisarEcg.setBackground(new Color(51,153,255));
		
		
		btnDardeAlta.setBackground(new Color(51,153,255));
		
		btnBuscarPacientes.setBackground(new Color(51,153,255));
		
		btnInicio.setBackground(new Color(51,153,255).darker());
		menu.ladoIzq();
		
		
		JPanel n=new JPanel();
		n.setOpaque(false);
		
		
		
		centro=new JPanel();
		centro.setOpaque(false);
		centro.setLayout(new BorderLayout());
		centro.add(memo);
		//centro.setMinimumSize(new Dimension(100,100));
		
		
		JPanel p=new JPanel();
		log=new JButton();
		log.setActionCommand(ControladorMedico.LOGOUT);
		log.setHorizontalAlignment(SwingConstants.RIGHT);
		log.setIcon(new ImageIcon("Resource/Imagenes/off.png"));
		log.setContentAreaFilled(false);
		log.setBorderPainted(false);
		log.setOpaque(false);
		p.add(log);
		p.setOpaque(false);
		
		
		JPanel pp=new JPanel();
		help=new JButton();
		help.setActionCommand(ControladorMedico.HELP);
		help.setIcon(new ImageIcon("Resource/Imagenes/help.png"));
		help.setContentAreaFilled(false);
		help.setBorderPainted(false);
		help.setOpaque(false);
		pp.add(help);
		pp.setOpaque(false);
		n.setLayout(new BorderLayout(0,10));
		n.add(p,BorderLayout.EAST);
		n.add(pp, BorderLayout.WEST);
	
		
		JPanel aux2=new JPanel();
		menu.setLayout(new BorderLayout());
		aux2.setOpaque(false);
		aux2.setLayout(new GridLayout(1,4,5,0));
		aux2.add(btnInicio);
		aux2.add(btnBuscarPacientes);
		aux2.add(btnRevisarEcg);
		aux2.add(btnDardeAlta);
		menu.add(new JLabel("     "),BorderLayout.EAST);
		menu.add(aux2,BorderLayout.CENTER);
		menu.add(new JLabel("     "),BorderLayout.WEST);
		menu.setOpaque(false);
		
		JLabel bien=new JLabel("Bienvenido Dr./Dra. "+med.getNombre()+" "+med.getApellido());
		bien.setFont(new Font("", Font.BOLD,15));
		
		JPanel ajust=new JPanel();
		ajust.setOpaque(false);
		ajust.setLayout(new BorderLayout());
		JLabel lab=new JLabel("    ");
		lab.setFont(new Font("", Font.BOLD,8));
		ajust.add(bien,BorderLayout.CENTER);
		ajust.add(lab,BorderLayout.NORTH);
		
		n.add(menu,BorderLayout.CENTER);
		bien.setHorizontalAlignment(SwingConstants.CENTER);
		n.add(ajust, BorderLayout.NORTH);
		usado.add(n,BorderLayout.NORTH);
		usado.add(centro, BorderLayout.CENTER);
		JPanel ajus=new JPanel();
		ajus.setOpaque(false);
		ajus.setLayout(new BorderLayout());
		Logo aux=new Logo();
		aux.setOpaque(false);
		aux.setLayout(new BorderLayout());
		JButton invi=new JButton(" ");
		invi.setFont(new Font("",0,30));
		
		invi.setOpaque(false);
		invi.setBorderPainted(false);
		invi.setContentAreaFilled(false);
		aux.add(invi,BorderLayout.CENTER);
		ajus.add(new JLabel("    "),BorderLayout.WEST);
		ajus.add(new JLabel(" "),BorderLayout.SOUTH);
		ajus.add(aux,BorderLayout.CENTER);
		//FINAL BASICO
		////////////////////////////////////////////////////////////////////////////////////////////////////
		fondo.add(ajus, BorderLayout.SOUTH);
		fondo.add(usado,BorderLayout.CENTER);
	    
		this.add(fondo,BorderLayout.CENTER);
	}
	public JPanel getCentro() {
		/**
		 * Nos devuelve el Panel que se encuentrra en el centro con el que 
		 * jugams entre poner visible y no y reemplazar por el resto de 
		 * paneles para ofrecer las distintas opciones.
		 * 
		 * @return centro
		 */
		return centro;
	}

	public void ver(){
		this.setVisible(true);
	}
	public void addController(ControladorMedico cm){
		btnInicio.addActionListener(cm);
		btnDardeAlta.addActionListener(cm);
		btnRevisarEcg.addActionListener(cm);
		btnBuscarPacientes.addActionListener(cm);
		log.addActionListener(cm);
		help.addActionListener(cm);
		
		
	}
	public void cleanButton(){
		btnInicio.setBackground(new Color(51,153,255));
		btnRevisarEcg.setBackground(new Color(51,153,255));
		btnDardeAlta.setBackground(new Color(51,153,255));
		btnBuscarPacientes.setBackground(new Color(51,153,255));
	}
}
