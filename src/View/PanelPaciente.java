package View;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Control.ControladorPanel;
import Model.ECG;
import Model.Paciente;
import Model.Usuario;

/**
 * La clase PanelPaciente muestra un panel en el que se introduciran
 * una serie de elementos graficos para mostrar informacion del paciente.
 * Posee cuatro constructores distintos en los cuales se crea la vista y en cada
 * uno de ellos se crea un panel para cada situacion especifica.
 * 
 * @author Heartlight
 * 
 * @version Final
 * 
 * @see ControladorPanel 
 * @see ECG
 * @see Paciente 
 * @see PacienteTecnico
 * @see Usuario
 *
 */
public class PanelPaciente extends JPanel {

	private static final long serialVersionUID = 1L;
	
	ControladorPanel cp;
	private JLabel usuario;
	private JLabel apellido;
	private JLabel dni;
	
	/**
	 * Metodo para anadir el controlador de la clase PanelPaciente
	 * que sera el ControladorPanel
	 * 
	 * @param cp ControladorPanel 
	 */
	public void addController(ControladorPanel cp){
		this.cp = cp;
	}
	
	/**
	 * Primer constructor de la clase PanelPaciente
	 * @param p Paciente 
	 * @param ecg ECG 
	 */
	 public PanelPaciente(Paciente p,ECG ecg) {
	    	setLayout(new BorderLayout(10, 0));
	    	JPanel aux=new JPanel();
	    	aux.setLayout(new BorderLayout(3,0));
	    	JLabel btnNewButton = new JLabel();
	    	aux.add(new JPanel(), BorderLayout.WEST);
	    	aux.add(btnNewButton,BorderLayout.CENTER);
			btnNewButton.setIcon(new ImageIcon("Resource/Imagenes/hombre.png"));
			btnNewButton.setOpaque(false);
			
			
			JPanel panel = new JPanel();
			panel.setOpaque(false);
			aux.add(panel, BorderLayout.EAST);
			panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
			
			JPanel panel2 = new JPanel();
			panel2.setOpaque(false);
			add(panel2, BorderLayout.EAST);
			panel2.setLayout(new GridLayout(2,1));
			panel2.add(new JLabel("Leido?          "+ ecg.isLeido()));
			panel2.add(new JLabel("Diagnosticado?  "+ ((ecg.getDiagnostico().isEmpty())?"false":"true")));
			
			usuario = new JLabel(p.getNombre());
			panel.add(usuario);
			
			apellido = new JLabel(p.getApellido());
			panel.add(apellido);
			
			dni  = new JLabel(p.getDni());
			panel.add(dni);
			panel.add(new JLabel("ECG: "+ecg.getNombre()));
			panel.add(new JLabel("Fecha de creacion: " +String.valueOf(ecg.getFecha()).substring(6, 8)+"-"+String.valueOf(ecg.getFecha()).substring(4, 6)+"-"+String.valueOf(ecg.getFecha()).substring(0, 4)));
			
			add(aux, BorderLayout.WEST);
			GraficaECG graf=new GraficaECG();
			graf.addGraphic(ecg);
			graf.initUISimple();
			JPanel aux2=new JPanel();
			
			
			JPanel gra=new JPanel();
			Fondo fon=new Fondo(gra,graf.getChart().createBufferedImage(500, 200));
			fon.setLayout(new BorderLayout());
			fon.add(new JLabel("         "),BorderLayout.CENTER);
			aux2.setLayout(new BorderLayout());
			gra.setLayout(new BorderLayout());
			//aux2.add(fon,BorderLayout.NORTH);
			gra.add(fon,BorderLayout.CENTER);
			add(gra,BorderLayout.CENTER);
			
	    }
	
	 	/**
		 * Segundo constructor de la clase PanelPaciente
		 * @param p PacienteTecnico
		 */
	    public PanelPaciente(Paciente p, String nada) {
	    	setLayout(new BorderLayout(10, 0));
	    	JPanel aux=new JPanel();
	    			aux.setLayout(new BorderLayout(3,0));
	    	JLabel btnNewButton = new JLabel();
	    	aux.add(new JPanel(), BorderLayout.WEST);
	    	aux.add(btnNewButton,BorderLayout.CENTER);
			
			btnNewButton.setIcon(new ImageIcon("Resource/Imagenes/hombre.png"));
			btnNewButton.setOpaque(false);
			
			
			JPanel panel = new JPanel();
			panel.setOpaque(false);
			add(panel, BorderLayout.CENTER);
			panel.setLayout(new GridLayout(4, 0, 0, 0));
			
			usuario = new JLabel(p.getNombre());
			panel.add(usuario);
			
			apellido = new JLabel(p.getApellido());
			panel.add(apellido);
			
			dni  = new JLabel(p.getDni());
			panel.add(dni);
			
			add(aux, BorderLayout.WEST);
			
	    }
	    
	    /**
		 * Tercer constructor de la clase PanelPaciente
		 * @param p Paciente 
		 */
	    public PanelPaciente(Paciente p) {
	    	setLayout(new BorderLayout(10, 0));
	    	JPanel aux=new JPanel();
	    			aux.setLayout(new BorderLayout(3,0));
	    	JLabel btnNewButton = new JLabel();
	    	aux.add(new JPanel(), BorderLayout.WEST);
	    	aux.add(btnNewButton,BorderLayout.CENTER);
			add(aux, BorderLayout.WEST);
			btnNewButton.setIcon(new ImageIcon("Resource/Imagenes/hombre.png"));
			btnNewButton.setOpaque(false);
			
			
			JPanel panel = new JPanel();
			panel.setOpaque(false);
			add(panel, BorderLayout.CENTER);
			panel.setLayout(new GridLayout(4, 0, 0, 0));
			
			usuario = new JLabel(p.getNombre());
			panel.add(usuario);
			
			apellido = new JLabel(p.getApellido());
			panel.add(apellido);
			
			dni  = new JLabel(p.getDni());
			panel.add(dni);
			
			
	    }
	    
	    
//	    public PanelPaciente(PacienteTecnico p,String a) {
//	    	setLayout(new BorderLayout(10, 0));
//	    	JPanel aux=new JPanel();
//	    	aux.setOpaque(false);
//	    			aux.setLayout(new BorderLayout(3,0));
//	    	JLabel btnNewButton = new JLabel();
//	    	JPanel pan=new JPanel();
//	    	pan.setOpaque(false);
//	    	aux.add(pan, BorderLayout.WEST);
//	    	aux.add(btnNewButton,BorderLayout.CENTER);
//			add(aux, BorderLayout.WEST);
//			if(!a.equals(" ") && !a.equals(""))
//				btnNewButton.setIcon(new ImageIcon(a));
//			btnNewButton.setOpaque(false);
//			
//			
//			JPanel panel = new JPanel();
//			panel.setOpaque(false);
//			add(panel, BorderLayout.CENTER);
//			panel.setLayout(new GridLayout(4, 0, 0, 0));
//			
//			usuario = new JLabel(p.getNombre());
//			panel.add(usuario);
//			
//			apellido = new JLabel(p.getApellido());
//			panel.add(apellido);
//			
//			dni  = new JLabel(p.getDni());
//			panel.add(dni);
//			
//			
//	    }
//	    
	    /**
		 * Cuarto constructor de la clase PanelPaciente
		 * @param p Usuario 
		 * @param con ControladorPanel 
		 */
	    public PanelPaciente(Usuario p, ControladorPanel con) {
	    	setLayout(new BorderLayout(10, 0));
	    	JPanel aux=new JPanel();
	    	aux.setOpaque(false);
	    			aux.setLayout(new BorderLayout(3,0));
	    	JLabel btnNewButton = new JLabel();
	    	JPanel pan=new JPanel();
	    	pan.setOpaque(false);
	    	aux.add(pan, BorderLayout.WEST);
	    	aux.add(btnNewButton,BorderLayout.CENTER);
			add(aux, BorderLayout.WEST);
			JButton b=new JButton();
			b.addActionListener(con);
			b.setOpaque(false);
			b.setContentAreaFilled(false);
			b.setBorderPainted(false);
			b.setIcon(new ImageIcon("Resource/Imagenes/delete.png"));
			add(b,BorderLayout.EAST);
			btnNewButton.setIcon(new ImageIcon("Resource/Imagenes/hombre.png"));
			btnNewButton.setOpaque(false);
			
			
			JPanel panel = new JPanel();
			panel.setOpaque(false);
			add(panel, BorderLayout.CENTER);
			panel.setLayout(new GridLayout(4, 0, 0, 0));
			
			usuario = new JLabel(p.getUser());
			panel.add(usuario);
			
			apellido = new JLabel(p.getRol());
			panel.add(apellido);
			
			dni  = new JLabel("");
			panel.add(dni);
			
			
	    }

	    /**
	     * Getter del ControladorPanel
	     * @return ControladorPanel cp
	     */
		public ControladorPanel getCp() {
			return cp;
		}
		
		/**
	     * Getter de la etiqueta de usuario
	     * @return JLabel usuario 
	     */
		public JLabel getUsuario() {
			return usuario;
		}

		/**
	     * Getter de la etiqueta de apellido
	     * @return JLabel apellido
	     */
		public JLabel getApellido() {
			return apellido;
		}

		/**
	     * Getter de la etiqueta de DNI
	     * @return JLabel dni
	     */
		public JLabel getDni() {
			return dni;
		}

	    
	    
                   
	}

