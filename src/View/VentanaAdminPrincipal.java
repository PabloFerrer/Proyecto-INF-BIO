package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import Control.ControladorAdmin;
import Control.ControladorPanel;
import Model.Paciente;
import Model.Usuario;
/**
 * Elemento grafico que incorpora todas las funciones/opciones que puede realizar un Administrador
 * @author HearthLight
 *
 */
public class VentanaAdminPrincipal extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JButton help;
	private JButton a;
	private JButton b;
	private JButton c1;
	private JButton log;
	private JPanel centro;
	private JPanel rey4;
	private JTextField buscador;
	private JTabbedPane tabbedPane;
	private JPanel rey5;
	
	
	/**
	 * Getter de TabbedPane
	 * @return paneles en los diferentes usuarios por rol
	 */
	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	/**
	 * Getter de panel central modificable para cualquier apartado
	 * @return Panel central que se puede modificar para cambiar de apartado
	 */
	public JPanel getCentro() {
		return centro;
	}

	/**
	 * Constructor que incluye la creacion de la UI del administrador
	 * @param usuario Administrador del cual se obtienen datos 
	 */
	public VentanaAdminPrincipal(Usuario usuario){
		ImageIcon img = new ImageIcon("Resource/Imagenes/usados/usado-cardio-finito100x100.png");
		
		this.setExtendedState( this.getExtendedState()|JFrame.MAXIMIZED_BOTH );
		
		this.setIconImage(img.getImage());
		rey4=new JPanel();
		buscador=new JTextField(15);
		//BASICO
		////////////////////////////////////////////////////////////////////////////////////////////////////
		int x=(int) (Toolkit.getDefaultToolkit().getScreenSize().width);
		int y=(int) (Toolkit.getDefaultToolkit().getScreenSize().height);
		//this.setSize(x, y);
		this.setLayout(new BorderLayout());
		this.setMinimumSize(new Dimension((int)(x*0.5),(int)(y*0.4)));
		JPanel usado=new JPanel();
		usado.setOpaque(false);
		Fondo fondo=new Fondo(this,"Resource/Imagenes/fondo.jpeg");
		fondo.setLayout(new BorderLayout());
		fondo.setOpaque(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		usado.setLayout(new BorderLayout());
		usado.setOpaque(false);
		////////////////////////////////////////////////////////////////////////////////////////////////////
		//PANEL EN EL QUE HAREMOS TODO=USADO
		usado.setLayout(new BorderLayout());
		a=new JButton("Dar de alta en el sistema a un Medico");
		b=new JButton("Dar de alta en el sistema a un Tecnico");
		c1=new JButton("Control de Usuarios");
		a.setActionCommand(ControladorAdmin.CREARME);
		b.setActionCommand(ControladorAdmin.CREARTEC);
		c1.setActionCommand(ControladorAdmin.USUARIOS);
		a.setFont(new Font("", Font.BOLD,20));
		b.setFont(new Font("", Font.BOLD,20));
		c1.setFont(new Font("", Font.BOLD,20));
		
		JPanel n=new JPanel();
		n.setOpaque(false);
		JPanel c=new JPanel();
		c.setOpaque(false);
		c.setLayout(new GridLayout(7,1));
		JPanel aux=new JPanel();
		aux.setOpaque(false);
		JPanel aux3=new JPanel();
		aux3.setOpaque(false);
		JPanel aux4=new JPanel();
		aux4.setOpaque(false);
		JPanel aux2=new JPanel();
		JButton b1=new JButton();
		b1.setContentAreaFilled(false);
		b1.setBorderPainted(false);
		b1.setOpaque(false);
		JButton b2=new JButton();
		b2.setContentAreaFilled(false);
		b2.setBorderPainted(false);
		b2.setOpaque(false);
		JButton b3=new JButton();
		b3.setContentAreaFilled(false);
		b3.setBorderPainted(false);
		b3.setOpaque(false);
		JButton b4=new JButton();
		b4.setContentAreaFilled(false);
		b4.setBorderPainted(false);
		b4.setOpaque(false);
		aux2.setOpaque(false);
		c.add(aux3);
		c.add(a);
		c.add(aux);
		c.add(b);
		c.add(aux2);
		c.add(c1);
		//c.add(aux4);
		n.add(b1);
		centro=new JPanel();
		centro.setOpaque(false);
		JButton e1=new JButton();
		e1.setContentAreaFilled(false);
		e1.setBorderPainted(false);
		e1.setOpaque(false);
		JButton w1=new JButton();
		w1.setContentAreaFilled(false);
		w1.setBorderPainted(false);
		w1.setOpaque(false);
		centro.setLayout(new BorderLayout());
		//centro.setMinimumSize(new Dimension(100,100));
		centro.add(c, BorderLayout.CENTER);
		centro.add(w1, BorderLayout.WEST);
		centro.add(e1, BorderLayout.EAST);
		
		
		JPanel p=new JPanel();
		log=new JButton();
		log.setHorizontalAlignment(SwingConstants.RIGHT);
		log.setIcon(new ImageIcon("Resource/Imagenes/off.png"));
		log.setActionCommand(ControladorAdmin.OFF);
		log.setContentAreaFilled(false);
		log.setBorderPainted(false);
		log.setOpaque(false);
		p.add(log);
		p.setOpaque(false);
		
		
		JPanel pp=new JPanel();
		help=new JButton();
		help.setActionCommand(ControladorAdmin.HELP);
		help.setIcon(new ImageIcon("Resource/Imagenes/help.png"));
		help.setContentAreaFilled(false);
		help.setBorderPainted(false);
		help.setOpaque(false);
		pp.add(help);
		pp.setOpaque(false);
		n.setLayout(new BorderLayout());
		n.add(p,BorderLayout.EAST);
		n.add(pp, BorderLayout.WEST);
		
		Menu menu=new Menu();
		menu.setOpaque(false);
		
		JLabel bien=new JLabel("Bienvenido "+usuario.getUser());
		bien.setFont(new Font("", Font.BOLD,25));
		
		JPanel ajust=new JPanel();
		ajust.setOpaque(false);
		ajust.setLayout(new BorderLayout());
		
		ajust.add(bien,BorderLayout.CENTER);
		ajust.add(new JLabel("    "),BorderLayout.NORTH);
		
		bien.setHorizontalAlignment(SwingConstants.CENTER);
		n.add(ajust, BorderLayout.CENTER);
		usado.add(n,BorderLayout.NORTH);
		usado.add(centro, BorderLayout.CENTER);
		
		JPanel ajus=new JPanel();
		ajus.setOpaque(false);
		ajus.setLayout(new BorderLayout());
		Logo logo=new Logo();
		logo.setOpaque(false);
		logo.setLayout(new BorderLayout());
		JButton invi=new JButton(" ");
		invi.setFont(new Font("",0,30));
		
		invi.setOpaque(false);
		invi.setBorderPainted(false);
		invi.setContentAreaFilled(false);
		logo.add(invi,BorderLayout.CENTER);
		ajus.add(new JLabel("    "),BorderLayout.WEST);
		ajus.add(new JLabel(" "),BorderLayout.SOUTH);
		ajus.add(logo,BorderLayout.CENTER);
		//FINAL BASICO
		////////////////////////////////////////////////////////////////////////////////////////////////////
		fondo.add(ajus, BorderLayout.SOUTH);
		fondo.add(usado, BorderLayout.CENTER);
		
		this.add(fondo,BorderLayout.CENTER);
		
	}
	/**
	 * Asignacion del controlador a elementos de la UI
	 * @param control Controlador que se encargará de todos los botones de la UI
	 */
	public void asignarControlador(ControladorAdmin control){
		a.addActionListener(control);
		b.addActionListener(control);
		c1.addActionListener(control);
		help.addActionListener(control);
		log.addActionListener(control);
	}	
	
	/**
	 * Poder visualizar la UI
	 */
	public void ver(){
		this.setVisible(true);
	}
	
	/**
	 * UI del control de usuarios
	 * @param control Controlador para la busqueda por JTextField y botones especificos
	 * @param medi estructura de datos que almacena todos los usuarios de tipo medico para mostrar en su apartado
	 * @param tec estructura de datos que almacena todos los usuarios de tipo tecnico para mostrar en su apartado
	 * @return el panel en el cual se encuentran todos los usuarios para su pronta asignacion
	 */
	public JPanel paneldeusuarios(ControladorAdmin control,Vector<Usuario>medi,Vector<Usuario>tec){
		JPanel p=new JPanel();
		p.setOpaque(false);
		JPanel p2=new JPanel();
		p2.setOpaque(false);
		p2.setLayout(new BorderLayout());
		p.setLayout(new BorderLayout());
		JPanel bu=new JPanel();
		bu.setOpaque(false);
		FlowLayout la=new FlowLayout();
		bu.setLayout(la);
		la.setAlignment(FlowLayout.LEFT);
		JButton ba=new JButton("Back");
		ba.setFont(new Font("",Font.BOLD,18));
		ba.setActionCommand(ControladorAdmin.BACK);
		ba.addActionListener(control);
		bu.add(ba);
		p2.add(bu, BorderLayout.WEST);
		FlowLayout fla=new FlowLayout();
		fla.setAlignment(FlowLayout.RIGHT);
		JPanel aux=new JPanel();
		aux.setOpaque(false);
		aux.setLayout(fla);
		aux.add(new JLabel("Buscador: "));
		aux.add(buscador);
		p2.add(aux,BorderLayout.CENTER);
		
		rey5=new JPanel();
		rey5.setOpaque(false);
		BoxLayout box5=new BoxLayout(rey5,BoxLayout.Y_AXIS);
		rey5.setLayout(box5);
		rey4.setOpaque(false);
		BoxLayout box=new BoxLayout(rey4,BoxLayout.Y_AXIS);
		rey4.setLayout(box);
		
		actPanel(medi,tec,control);
		
		
		
		 JScrollPane jsp = new JScrollPane();
		 jsp.setViewportBorder(null);
		 jsp.setOpaque(false);
		 jsp.getViewport().setOpaque(false);
		 jsp.setBorder(null);
		 jsp.getViewport().setBorder(null);
	     jsp.setViewportView(rey4);
	     
	     JScrollPane jsp2 = new JScrollPane();
		 jsp2.setViewportBorder(null);
		 jsp2.setOpaque(false);
		 jsp2.getViewport().setOpaque(false);
		 jsp2.setBorder(null);
		 jsp2.getViewport().setBorder(null);
	     jsp2.setViewportView(rey5);
	     
	     JButton invi=new JButton();
	     invi.setOpaque(false);
	     invi.setContentAreaFilled(false);
	     invi.setBorderPainted(false);
	     JButton invi2=new JButton();
	     invi2.setOpaque(false);
	     invi2.setContentAreaFilled(false);
	     invi2.setBorderPainted(false);
	     JButton invi3=new JButton();
	     invi3.setOpaque(false);
	     invi3.setContentAreaFilled(false);
	     invi3.setBorderPainted(false);
	     
	
	     tabbedPane = new JTabbedPane();
	     tabbedPane.setOpaque(false);
	     tabbedPane.addTab("Doctores", null, jsp,
	                "Does nothing");
	     tabbedPane.addTab("Tecnicos", null, jsp2,
	                "Does nothing");
		p.add(p2,BorderLayout.NORTH);
		p.add(tabbedPane,BorderLayout.CENTER);
		p.add(invi,BorderLayout.EAST);
		p.add(invi2,BorderLayout.WEST);
		p.add(invi3,BorderLayout.SOUTH);
		
		return p;
	}
	
	/**
	 * Actualizacion al iniciar la opcion para controlar los usuarios y refrescarla al hacer una busqueda
	 * @param medi estructura de datos que almacena todos los usuarios de tipo medico para mostrar en su apartado
	 * @param tec estructura de datos que almacena todos los usuarios de tipo tecnico para mostrar en su apartado
	 * @param cont Controlador para la busqueda por JTextField y botones especificos
	 */
	public void actPanel(Vector<Usuario>medi,Vector<Usuario>tec,ControladorAdmin cont){
		rey4.removeAll();
		
		rey5.removeAll();
		rey5.setOpaque(false);
		BoxLayout box5=new BoxLayout(rey5,BoxLayout.Y_AXIS);
		rey5.setLayout(box5);
		rey4.setOpaque(false);
		BoxLayout box=new BoxLayout(rey4,BoxLayout.Y_AXIS);
		rey4.setLayout(box);
		int colum=0;
		
		
		for(int i= 0;i<medi.size();i++){
			JPanel pane=new JPanel();
			pane.setOpaque(false);
			pane.setLayout(new GridLayout(1,4,5,0));
			for(int j=0;j<4;j++){
				if(i<medi.size()){
					PanelPaciente pan = new PanelPaciente(medi.get(i),new ControladorPanel(medi.get(i),cont,buscador));
					pan.setBackground(new Color(0,153,153));
					pan.setBorder(new LineBorder(new Color(0,204,204), 2));
					pane.add(pan);	
				} else {
					JPanel pan = new JPanel();
					pan.setOpaque(false);
					pan.setLayout(new BorderLayout());
					JButton but=new JButton();
					but.setContentAreaFilled(false);
					but.setBorderPainted(false);
					but.setOpaque(false);
					pan.add(but,BorderLayout.CENTER);
					pane.add(pan);
				}
				i++;
			}
			i--;
			JPanel pi=new JPanel();
			pi.setOpaque(false);
			rey4.add(pi);
			rey4.add(pane);
			colum++;
		}
		
		if(colum<10){
		for(int i= colum;i<15;i++){
			JPanel pane=new JPanel();
			pane.setOpaque(false);
			pane.setLayout(new GridLayout(1,4,5,0));
			for(int j=0;j<4;j++){
				JPanel pan = new JPanel();
				pan.setOpaque(false);
				pan.setLayout(new BorderLayout());
				JButton but=new JButton();
				but.setContentAreaFilled(false);
				but.setBorderPainted(false);
				but.setOpaque(false);
				pan.add(but,BorderLayout.CENTER);
				pane.add(pan);	
			}
			rey4.add(pane);
			colum++;
		}
		}
		
		int columtec=0;
		for(int i= 0;i<tec.size();i++){
			JPanel pane=new JPanel();
			pane.setOpaque(false);
			pane.setLayout(new GridLayout(1,4,5,0));
			for(int j=0;j<4;j++){
				if(i<tec.size()){
					PanelPaciente pan = new PanelPaciente(tec.get(i),new ControladorPanel(tec.get(i),cont,buscador));
					pan.setBackground(new Color(0,153,153));
					pan.setBorder(new LineBorder(new Color(0,204,204), 2));
					pane.add(pan);	
				} else {
					JPanel pan = new JPanel();
					pan.setOpaque(false);
					pan.setLayout(new BorderLayout());
					JButton but=new JButton();
					but.setContentAreaFilled(false);
					but.setBorderPainted(false);
					but.setOpaque(false);
					pan.add(but,BorderLayout.CENTER);
					pane.add(pan);
				}
				i++;
			}
			i--;
			JPanel pi=new JPanel();
			pi.setOpaque(false);
			rey5.add(pi);
			rey5.add(pane);
			columtec++;
		}
		
		if(columtec<10){
		for(int i= columtec;i<15;i++){
			JPanel pane=new JPanel();
			pane.setOpaque(false);
			pane.setLayout(new GridLayout(1,4,5,0));
			for(int j=0;j<4;j++){
				JPanel pan = new JPanel();
				pan.setOpaque(false);
				pan.setLayout(new BorderLayout());
				JButton but=new JButton();
				but.setContentAreaFilled(false);
				but.setBorderPainted(false);
				but.setOpaque(false);
				pan.add(but,BorderLayout.CENTER);
				pane.add(pan);	
			}
			rey5.add(pane);
			colum++;
		}
		}
	}
	/**
	 * Getter de JTextfield en el cual se realiza la busqueda para filtrar los usuarios
	 * @return el jtextfield que hace funcion de buscador
	 */
	public JTextField getBuscador() {
		return buscador;
	}
	
}
