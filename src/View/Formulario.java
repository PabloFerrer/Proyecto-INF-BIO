package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Control.ControladorAdmin;
import Control.ControladorMedico;
/**
 * @author HeartLight
 * 
 * La clas Formulario engloba los tres formularios posibles de la aplicacion, encontramos
 * Formular Tecnico al que solo tendra acceso el administrador, Formulario Medico que tambien 
 * sera uncio para el administrador y Formulario Paciente que corresponde al Medico y se usuaria
 * para dar de alta a cualquier nuevo usuario(Tecnico, Medico o Paciente)
 * 
 * @version Final
 * 
 * @see VentanaAdminPrincipal
 * @see VentanaMedico
 *
 */
public class Formulario extends JFrame{
	
	
	private static final long serialVersionUID = 1L;
	private JButton enviar;
	private JButton cancel;
	private JTextField nombre;
	private JTextField apellido1;
	private JTextField apellido2;
	private JTextField dni;
	private JTextField lugar;
	private JTextField direccion;
	private JTextField ss;
	private JComboBox<String> urgencia;
	private JPasswordField contrasena1;
	private JPasswordField contrasena2;
	private JTextField telefono;
	private JTextField numero;
	private JLabel user;
	private JRadioButton rdbtnMasculino;
	private JRadioButton rdbtnFemenino;
	private ButtonGroup botones = new ButtonGroup();
	
	/**
	 * Getter para saber los escrito en el campo de texto de segundo apellido
	 * @return JTextField
	 */
	public JTextField getApellido2() {
		return apellido2;
	}
	/**
	 * Getter para saber los escrito en el campo de texto de DNI
	 * @return JTextField
	 */
	public JTextField getDni() {
		return dni;
	}
	/**
	 * Getter para saber los escrito en el campo de texto de Lugar
	 * @return JTextField
	 */
	public JTextField getLugar() {
		return lugar;
	}
	/**
	 * Getter para saber los escrito en el campo de texto de contrasena
	 * @return JPasswordField
	 */
	public JPasswordField getContrasena1() {
		return contrasena1;
	}
	/**
	 * Getter para saber los escrito en el campo de texto para confirmar la contrasena
	 * @return JPasswordField
	 */
	public JPasswordField getContrasena2() {
		return contrasena2;
	}
	/**
	 * Getter para saber los escrito en el campo de texto de telefono
	 * @return JTextField
	 */
	public JTextField getTelefono() {
		return telefono;
	}

	/**
	 * Getter para saber los escrito en el campo de texto de numero de colegiado
	 * @return JTextField
	 */
	public JTextField getNumero() {
		return numero;
	}
	/**
	 * Getter para saber los escrito en el campo de texto de nombre
	 * @return JTextField
	 */
			public JTextField getNombre() {
		return nombre;
	}

	/**
	 * Getter para saber los escrito en el campo de texto de primer apellido
	 * @return JTextField
	 */
	public JTextField getApellido1() {
		return apellido1;
	}
	/**
	 * Getter de la label en la que se guardara un nombre de usuario provisional
	 * @return JLabel
	 */
			public JLabel getUser() {
		return user;
	}
			
			/**
			 * Getter para saber los escrito en el campo de texto de direccion
			 * @return JTextField
			 */
			public JTextField getDireccion() {
				return direccion;
			}
			/**
			 * Getter para saber los escrito en el campo de texto de Seguridad Social
			 * @return JTextField
			 */
			public JTextField getSs() {
				return ss;
			}
			/**
			 * Getter para JComboBox que tiene el grado de urgencia de un paciente
			 * @return JComboBox
			 */
			public JComboBox<String> getUrgencia() {
				return urgencia;
			}
			/** 
			 * Constructor que le da los valores por defecto que tendran todos los formularios
			 * 
			 */
			public Formulario(){
				this.setResizable(false);
				ImageIcon img = new ImageIcon("Resource/Imagenes/Logos/logo-cardio-finito100x100.png");
				this.setIconImage(img.getImage());
				this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
			/**
			 * Este es el formulario correspondiente a los nuevos medicos, posee
			 * un control en el que en caso de que alguna de las casillas anteriores no es encu
			 * entre bien cumplimentada no dejarar crear el nuevo usuario, ademas nos ofrecera un 
			 * posible nombre de usuario.
			 * 
			 * @param control ControladorAdmin
			 * 
			 */
			public void medico(ControladorAdmin control){
				
				
				
				int x=(int) (Toolkit.getDefaultToolkit().getScreenSize().width);
				int y=(int) (Toolkit.getDefaultToolkit().getScreenSize().height);
				int porceX=34;
				int porceY=80;
				
				
				this.setLocation(x/2-(int) (x*porceX/100)/2,(int) (y/2)-(int) (y*porceY/100)/2);
				this.setSize(x*porceX/100,y*porceY/100);
				this.setMinimumSize(new Dimension(x*porceX/100,y*porceY/100));
				Fondo fon=new Fondo(this,"Resource/Imagenes/fondo.jpeg");
				BorderLayout bor=new BorderLayout();
				fon.setLayout(bor);
				JPanel aux=new JPanel();
				aux.setOpaque(false);
				aux.setLayout(new GridLayout(1,2));
				JPanel basu1=new JPanel();
				basu1.setOpaque(false);
				JPanel basu2=new JPanel();
				basu2.setOpaque(false);
				FlowLayout flow=new FlowLayout();
				flow.setAlignment(FlowLayout.RIGHT);
				FlowLayout flow2=new FlowLayout();
				flow2.setAlignment(FlowLayout.LEFT);
				basu1.setLayout(flow);
				basu2.setLayout(flow2);
				
				enviar=new JButton();
				enviar.setBorderPainted(false);
				enviar.setOpaque(false);
				enviar.setContentAreaFilled(false);
				cancel=new JButton();
				cancel.setBorderPainted(false);
				cancel.setOpaque(false);
				cancel.setContentAreaFilled(false);
				enviar.setIcon(new ImageIcon("Resource/Imagenes/send.png"));
				cancel.setIcon(new ImageIcon("Resource/Imagenes/cancel.png"));
				enviar.setActionCommand(ControladorAdmin.ENVIARME);
				cancel.setActionCommand(ControladorAdmin.CANCEL);
				enviar.addActionListener(control);
				cancel.addActionListener(control);
				bor.minimumLayoutSize(enviar);
				basu1.add(enviar);
				basu2.add(cancel);
				aux.add(basu2);
				aux.add(basu1);
				JPanel formu=new JPanel();
				FlowLayout flow3=new FlowLayout();
				flow3.setAlignment(FlowLayout.RIGHT);
				formu.setLayout(flow3);
				Font font=new Font("",Font.BOLD,20);
				
				JPanel nom=new JPanel();
				nom.setOpaque(false);
				nom.setLayout(new FlowLayout());
				nombre=new JTextField(15);
				JLabel name=new JLabel("Nombre: ");
				name.setFont(font);
				name.setForeground(Color.WHITE);
				nom.add(name);
				nom.add(nombre);
				formu.add(nom);
				
				JPanel d=new JPanel();
				d.setOpaque(false);
				d.setLayout(new FlowLayout());
				dni=new JTextField(15);
				JLabel dn=new JLabel("DNI/NIE: ");
				dn.setForeground(Color.WHITE);
				dn.setFont(font);
				d.add(dn);
				d.add(dni);
				formu.add(d);
				
				JPanel ape=new JPanel();
				ape.setOpaque(false);
				ape.setLayout(new FlowLayout());
				apellido1=new JTextField(15);
				JLabel ap=new JLabel("Primer Apellido: ");
				ap.setForeground(Color.WHITE);
				ap.setFont(font);
				ape.add(ap);
				ape.add(apellido1);
				formu.add(ape);
				
				JPanel ape2=new JPanel();
				ape2.setOpaque(false);
				ape2.setLayout(new FlowLayout());
				apellido2=new JTextField(15);
				JLabel ap2=new JLabel("Segundo Apellido: ");
				ap2.setForeground(Color.WHITE);
				ap2.setFont(font);
				ape2.add(ap2);
				ape2.add(apellido2);
				formu.add(ape2);
				
				JPanel lug=new JPanel();
				lug.setOpaque(false);
				lug.setLayout(new FlowLayout());
				lugar=new JTextField(15);
				JLabel l=new JLabel("Ubicacion: ");
				l.setForeground(Color.WHITE);
				l.setFont(font);
				lug.add(l);
				lug.add(lugar);
				formu.add(lug);
				
				JPanel tele=new JPanel();
				tele.setOpaque(false);
				tele.setLayout(new FlowLayout());
				telefono=new JTextField(15);
				telefono.setName("C");
				telefono.addKeyListener(control);
				JLabel t=new JLabel("Nº de Telefono: ");
				t.setForeground(Color.WHITE);
				t.setFont(font);
				tele.add(t);
				tele.add(telefono);
				formu.add(tele);
				
				nombre.setName("N");
				apellido1.setName("N");
				apellido2.setName("N");
				
				JPanel num=new JPanel();
				num.setOpaque(false);
				num.setLayout(new FlowLayout());
				numero=new JTextField(15);
				numero.setName("C");
				numero.addKeyListener(control);
				JLabel n=new JLabel("Numero de Colegiado: ");
				n.setForeground(Color.WHITE);
				n.setFont(font);
				num.add(n);
				num.add(numero);
				formu.add(num);
				
				JPanel con=new JPanel();
				con.setOpaque(false);
				con.setLayout(new FlowLayout());
				contrasena1=new JPasswordField(15);
				JLabel c=new JLabel("Password: ");
				c.setForeground(Color.WHITE);
				c.setFont(font);
				con.add(c);
				con.add(contrasena1);
				formu.add(con);
				
				JPanel con1=new JPanel();
				con1.setOpaque(false);
				con1.setLayout(new FlowLayout());
				contrasena2=new JPasswordField(15);
				JLabel c1=new JLabel("Password nuevamente: ");
				c1.setForeground(Color.WHITE);
				c1.setFont(font);
				con1.add(c1);
				con1.add(contrasena2);
				formu.add(con1);
				
				
				JPanel abajo=new JPanel();
				abajo.setOpaque(false);
				abajo.setLayout(new BorderLayout());
				Font font2=new Font("",Font.BOLD,22);
				JLabel u=new JLabel("Posible Usuario");
				u.setHorizontalAlignment(JLabel.CENTER);
				u.setFont(font2);
				Font font3=new Font("",Font.BOLD,50);
				user=new JLabel(" ");
				user.setFont(font3);
				user.setHorizontalAlignment(JLabel.CENTER);
				abajo.add(u,BorderLayout.NORTH);
				abajo.add(user,BorderLayout.CENTER);
				
				dni.setName("S");
				contrasena1.setName("S");
				contrasena2.setName("S");
				
				nombre.addKeyListener(control);
				apellido1.addKeyListener(control);
				apellido2.addKeyListener(control);
				dni.addKeyListener(control);
				lugar.addKeyListener(control);
				contrasena1.addKeyListener(control);
				contrasena2.addKeyListener(control);
				
				nombre.addMouseListener(control);
				apellido1.addMouseListener(control);
				apellido2.addMouseListener(control);
				dni.addMouseListener(control);
				lugar.addMouseListener(control);
				telefono.addMouseListener(control);
				numero.addMouseListener(control);
				contrasena1.addMouseListener(control);
				contrasena2.addMouseListener(control);
				
				
				
				formu.setOpaque(false);
				
				fon.add(formu,BorderLayout.CENTER);
				fon.add(aux,BorderLayout.NORTH);
				fon.add(abajo,BorderLayout.SOUTH);
				
				this.add(fon);
			}
			
			/**
			 * Este es el formulario correspondiente a los nuevos Tecnicos, posee
			 * un control en el que en caso de que alguna de las casillas anteriores no es encu
			 * entre bien cumplimentada no dejarar crear el nuevo usuario, ademas nos ofrecera un 
			 * posible nombre de usuario.
			 * 
			 * @param control ControladorAdmin
			 */
			public void tecnico(ControladorAdmin control){
				
				int x=(int) (Toolkit.getDefaultToolkit().getScreenSize().width);
				int y=(int) (Toolkit.getDefaultToolkit().getScreenSize().height);
				int porceX=36;
				int porceY=70;
				this.setLocation(x/2-(int) (x*porceX/100)/2,(int) (y/2)-(int) (y*porceY/100)/2);
				this.setSize(x*porceX/100,y*porceY/100);
				this.setMinimumSize(new Dimension(x*porceX/100,y*porceY/100));
				
				Fondo fon=new Fondo(this,"Resource/Imagenes/fondo.jpeg");
				BorderLayout bor=new BorderLayout();
				fon.setLayout(bor);
				JPanel aux=new JPanel();
				aux.setOpaque(false);
				aux.setLayout(new GridLayout(1,2));
				JPanel basu1=new JPanel();
				basu1.setOpaque(false);
				JPanel basu2=new JPanel();
				basu2.setOpaque(false);
				FlowLayout flow=new FlowLayout();
				flow.setAlignment(FlowLayout.RIGHT);
				FlowLayout flow2=new FlowLayout();
				flow2.setAlignment(FlowLayout.LEFT);
				basu1.setLayout(flow);
				basu2.setLayout(flow2);
				
				enviar=new JButton();
				enviar.setBorderPainted(false);
				enviar.setOpaque(false);
				enviar.setContentAreaFilled(false);
				cancel=new JButton();
				cancel.setBorderPainted(false);
				cancel.setOpaque(false);
				cancel.setContentAreaFilled(false);
				enviar.setIcon(new ImageIcon("Resource/Imagenes/send.png"));
				cancel.setIcon(new ImageIcon("Resource/Imagenes/cancel.png"));
				enviar.setActionCommand(ControladorAdmin.ENVIARTEC);
				enviar.addActionListener(control);
				cancel.addActionListener(control);
				cancel.setActionCommand(ControladorAdmin.CANCEL);
				bor.minimumLayoutSize(enviar);
				basu1.add(enviar);
				basu2.add(cancel);
				aux.add(basu2);
				aux.add(basu1);
				JPanel formu=new JPanel();
				FlowLayout flow3=new FlowLayout();
				flow3.setAlignment(FlowLayout.RIGHT);
				formu.setLayout(flow3);
				Font font=new Font("",Font.BOLD,20);
				
				JPanel nom=new JPanel();
				nom.setOpaque(false);
				nom.setLayout(new FlowLayout());
				nombre=new JTextField(15);
				JLabel name=new JLabel("Nombre: ");
				name.setFont(font);
				name.setForeground(Color.WHITE);
				nom.add(name);
				nom.add(nombre);
				formu.add(nom);
				
				JPanel d=new JPanel();
				d.setOpaque(false);
				d.setLayout(new FlowLayout());
				dni=new JTextField(15);
				JLabel dn=new JLabel("DNI/NIE: ");
				dn.setForeground(Color.WHITE);
				dn.setFont(font);
				d.add(dn);
				d.add(dni);
				formu.add(d);
				
				
				JPanel ape=new JPanel();
				ape.setOpaque(false);
				ape.setLayout(new FlowLayout());
				apellido1=new JTextField(15);
				JLabel ap=new JLabel("Primer Apellido: ");
				ap.setForeground(Color.WHITE);
				ap.setFont(font);
				ape.add(ap);
				ape.add(apellido1);
				formu.add(ape);
				
				JPanel ape2=new JPanel();
				ape2.setOpaque(false);
				ape2.setLayout(new FlowLayout());
				apellido2=new JTextField(15);
				JLabel ap2=new JLabel("Segundo Apellido: ");
				ap2.setForeground(Color.WHITE);
				ap2.setFont(font);
				ape2.add(ap2);
				ape2.add(apellido2);
				formu.add(ape2);
				
				JPanel lug=new JPanel();
				lug.setOpaque(false);
				lug.setLayout(new FlowLayout());
				lugar=new JTextField(15);
				JLabel l=new JLabel("Ubicacion: ");
				l.setForeground(Color.WHITE);
				l.setFont(font);
				lug.add(l);
				lug.add(lugar);
				formu.add(lug);
				
				JPanel con=new JPanel();
				con.setOpaque(false);
				con.setLayout(new FlowLayout());
				contrasena1=new JPasswordField(15);
				JLabel c=new JLabel("Password: ");
				c.setForeground(Color.WHITE);
				c.setFont(font);
				con.add(c);
				con.add(contrasena1);
				formu.add(con);
				
				JPanel con1=new JPanel();
				con1.setOpaque(false);
				con1.setLayout(new FlowLayout());
				contrasena2=new JPasswordField(15);
				JLabel c1=new JLabel("Password nuevamente: ");
				c1.setForeground(Color.WHITE);
				c1.setFont(font);
				con1.add(c1);
				con1.add(contrasena2);
				formu.add(con1);
				
				formu.setOpaque(false);
				
				nombre.setName("N");
				apellido1.setName("N");
				apellido2.setName("N");
				
				JPanel abajo=new JPanel();
				abajo.setOpaque(false);
				abajo.setLayout(new BorderLayout());
				Font font2=new Font("",Font.BOLD,22);
				JLabel u=new JLabel("Posible Usuario");
				u.setHorizontalAlignment(JLabel.CENTER);
				u.setFont(font2);
				Font font3=new Font("",Font.BOLD,50);
				user=new JLabel(" ");
				user.setFont(font3);
				user.setHorizontalAlignment(JLabel.CENTER);
				abajo.add(u,BorderLayout.NORTH);
				abajo.add(user,BorderLayout.CENTER);
				
				fon.add(formu,BorderLayout.CENTER);
				fon.add(aux,BorderLayout.NORTH);
				fon.add(abajo,BorderLayout.SOUTH);
				
				dni.setName("S");
				contrasena1.setName("S");
				contrasena2.setName("S");

				nombre.addKeyListener(control);
				apellido1.addKeyListener(control);
				apellido2.addKeyListener(control);
				dni.addKeyListener(control);
				lugar.addKeyListener(control);
				contrasena1.addKeyListener(control);
				contrasena2.addKeyListener(control);
				
				nombre.addMouseListener(control);
				apellido1.addMouseListener(control);
				apellido2.addMouseListener(control);
				dni.addMouseListener(control);
				lugar.addMouseListener(control);
				contrasena1.addMouseListener(control);
				contrasena2.addMouseListener(control);
				
				this.add(fon);
			}
			/**
			 * Este es el formulario correspondiente a los nuevos Pacientes, la cual se encontrara
			 * disponible solo en el caso de haberse autentificado como medico , posee
			 * un control en el que en caso de que alguna de las casillas anteriores no es encu
			 * entre bien cumplimentada no dejarar crear el nuevo usuario, ademas nos ofrecera un 
			 * posible nombre de usuario.
			 * 
			 * @param control ControladorMedico
			 * 
			 */
			public JPanel paciente(ControladorMedico control){
				
				JButton invi = new JButton();
				invi.setOpaque(false);
				
				JPanel panel = new JPanel();
				panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
				panel.setOpaque(false);
//				int x=(int) (Toolkit.getDefaultToolkit().getScreenSize().width);
//				int y=(int) (Toolkit.getDefaultToolkit().getScreenSize().height);
//				int porceX=36;
//				int porceY=70;
//				this.setLocation(x/2-(int) (x*porceX/100)/2,(int) (y/2)-(int) (y*porceY/100)/2);
//				this.setSize(x*porceX/100,y*porceY/100);
//				this.setMinimumSize(new Dimension(x*porceX/100,y*porceY/100));
				
				
				BorderLayout bor=new BorderLayout();
				panel.setLayout(bor);
				JPanel aux=new JPanel();
				aux.setOpaque(false);
				aux.setLayout(new GridLayout(1,2));
				JPanel basu1=new JPanel();
				basu1.setOpaque(false);
				JPanel basu2=new JPanel();
				basu2.setOpaque(false);
				FlowLayout flow=new FlowLayout();
				flow.setAlignment(FlowLayout.RIGHT);
				FlowLayout flow2=new FlowLayout();
				flow2.setAlignment(FlowLayout.LEFT);
				basu1.setLayout(flow);
				basu2.setLayout(flow2);
				
				enviar=new JButton();
				enviar.setBorderPainted(false);
				enviar.setOpaque(false);
				enviar.setContentAreaFilled(false);
				cancel=new JButton();
				cancel.setBorderPainted(false);
				cancel.setOpaque(false);
				cancel.setContentAreaFilled(false);
				enviar.setIcon(new ImageIcon("Resource/Imagenes/send.png"));
				cancel.setIcon(new ImageIcon("Resource/Imagenes/cancel.png"));
				enviar.setActionCommand(ControladorMedico.ENVIAR);
				enviar.addActionListener(control);
				cancel.addActionListener(control);
				cancel.setActionCommand(ControladorMedico.CANCEL);
				bor.minimumLayoutSize(enviar);
				basu1.add(enviar);
				basu2.add(cancel);
				aux.add(basu2);
				aux.add(basu1);
				JPanel formu=new JPanel();   
				FlowLayout flow3=new FlowLayout();
				flow3.setAlignment(FlowLayout.RIGHT);
				formu.setLayout(new BoxLayout(formu,BoxLayout.PAGE_AXIS  ));
				Font font=new Font("",Font.BOLD,20);
				
				JPanel nom=new JPanel();
				nom.setOpaque(false);
				nom.setLayout(new FlowLayout());
				nombre=new JTextField(15);
				JLabel name=new JLabel("Nombre: ");
				name.setFont(font);
				name.setForeground(Color.WHITE);
				nom.add(name);
				nom.add(nombre);
				formu.add(nom);
				
				JPanel d=new JPanel();
				d.setOpaque(false);
				d.setLayout(new FlowLayout());
				dni=new JTextField(15);
				JLabel dn=new JLabel("DNI/NIE: ");
				dn.setForeground(Color.WHITE);
				dn.setFont(font);
				d.add(dn);
				d.add(dni);
				formu.add(d);
				
				
				JPanel ape=new JPanel();
				ape.setOpaque(false);
				ape.setLayout(new FlowLayout());
				apellido1=new JTextField(15);
				JLabel ap=new JLabel("Primer Apellido: ");
				ap.setForeground(Color.WHITE);
				ap.setFont(font);
				ape.add(ap);
				ape.add(apellido1);
				formu.add(ape);
				
				JPanel ape2=new JPanel();
				ape2.setOpaque(false);
				ape2.setLayout(new FlowLayout());
				apellido2=new JTextField(15);
				JLabel ap2=new JLabel("Segundo Apellido: ");
				ap2.setForeground(Color.WHITE);
				ap2.setFont(font);
				ape2.add(ap2);
				ape2.add(apellido2);
				formu.add(ape2);
				
				JPanel lug=new JPanel();
				lug.setOpaque(false);
				lug.setLayout(new FlowLayout());
				lugar=new JTextField(15);
				JLabel l=new JLabel("Poblacion: ");
				l.setForeground(Color.WHITE);
				l.setFont(font);
				lug.add(l);
				lug.add(lugar);
				formu.add(lug);

				JPanel lug1=new JPanel();
				lug1.setOpaque(false);
				lug1.setLayout(new FlowLayout());
				ss=new JTextField(15);
				JLabel l1=new JLabel("Seguridad Social: ");
				l1.setForeground(Color.WHITE);
				l1.setFont(font);
				lug1.add(l1);
				lug1.add(ss);
				formu.add(lug1);
				
				JPanel con=new JPanel();
				con.setOpaque(false);
				con.setLayout(new FlowLayout());
				direccion = new JTextField(15);
				JLabel c=new JLabel("Direccion: ");
				c.setForeground(Color.WHITE);
				c.setFont(font);
				con.add(c);
				con.add(direccion);
				formu.add(con);

				JPanel se = new JPanel();
				se.setOpaque(false);
				se.setLayout(new FlowLayout());
				rdbtnFemenino= new JRadioButton("Femenino");
				rdbtnMasculino= new JRadioButton("Masculino");
				JLabel sex = new JLabel("Sexo: ");
				sex.setFont(font);
				sex.setForeground(Color.WHITE);
				se.add(sex);
				se.add(rdbtnFemenino);
				se.add(rdbtnMasculino);
				formu.add(se);
				
				/*JPanel c0 = new JPanel();
				c0.setOpaque(false);
				con.setLayout(new FlowLayout());
				urgencia = new JComboBox<String>();
				urgencia.setModel(new DefaultComboBoxModel<String>(new String[] {"Elija una opcion","Baja","Moderado","Urgencia"}));
				JLabel c1 = new JLabel("Elija la prioridad");
				c1.setForeground(Color.WHITE);
				c1.setFont(font);
				c0.add(c1);
				c0.add(urgencia);
				formu.add(c0);*/
				formu.setOpaque(false);
				
				nombre.setName("T");
				apellido1.setName("T");
				apellido2.setName("T");
				
				panel.add(formu,BorderLayout.CENTER);
				panel.add(aux,BorderLayout.NORTH);
				
				ss.setName("N");
				
				botones.add(rdbtnFemenino);
				botones.add(rdbtnMasculino);

				nombre.addKeyListener(control);
				apellido1.addKeyListener(control);
				apellido2.addKeyListener(control);
				dni.addKeyListener(control);
				ss.addKeyListener(control);
				//urgencia.addKeyListener(control);
				rdbtnFemenino.addKeyListener(control);
				rdbtnMasculino.addKeyListener(control);
				
				nombre.addMouseListener(control);
				apellido1.addMouseListener(control);
				apellido2.addMouseListener(control);
				dni.addMouseListener(control);
				lugar.addMouseListener(control);
				direccion.addMouseListener(control);
				ss.addMouseListener(control);
				//urgencia.addMouseListener(control);
				rdbtnFemenino.addMouseListener(control);
				rdbtnMasculino.addMouseListener(control);
				
				return panel;
			}
			/**
			 * Cambiar la visibilidad de la ventana formulario a true
			 */
			public void ver(){
				this.setVisible(true);
			}
			
			
}
