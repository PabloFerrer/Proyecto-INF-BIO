package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import Control.ControladorMensaje;
import Control.ControladorPanel;
import Control.ControladorPanelM;
import Model.Conexion;
import Model.Constantes;
import Model.Mensaje;
import Model.Paciente;
import Model.Usuario;
import Model.Utilidades;

public class VentanaMensajes extends JFrame{
	
	private JPanel info;
	private JTextField asunto;
	private JLabel fecha;
	private JTextField emisor;
	private JTextArea texto;
	private JList<Mensaje> list;
	private JList<Mensaje> envlist;
	private Fondo fon;
	private JPanel central;
	private JButton atras;
	private JButton nuevo;
	
	public VentanaMensajes(ControladorMensaje control) {
		int x=(int) (Toolkit.getDefaultToolkit().getScreenSize().width);
		this.setAlwaysOnTop(true);
		int y=(int) (Toolkit.getDefaultToolkit().getScreenSize().height);
		this.setSize((int)(x*0.50), (int)(y*0.4));
		this.setMinimumSize(new Dimension((int)(x*0.50), (int)(y*0.40)));
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		fon=new Fondo(this,"Resource/Imagenes/fondo.jpeg");
		this.setLocation((x-this.getWidth())/2, (y-this.getHeight())/2);
		this.setLayout(new BorderLayout());

		ImageIcon img = new ImageIcon("Resource/Imagenes/Logos/logo-cardio-finito100x100.png");
		this.setIconImage(img.getImage());
		this.setLayout(new BorderLayout());
		fon.setLayout(new BorderLayout(3,1));
		JPanel aux=new JPanel();
		aux.setLayout(new BorderLayout());
		aux.setOpaque(false);
		atras=new JButton("Back");
		atras.setActionCommand(ControladorMensaje.ATRASMENS);
		atras.addActionListener(control);
		nuevo=new JButton("New Message");
		nuevo.addActionListener(control);
		nuevo.setActionCommand(ControladorMensaje.NEWMENSAJE);
		aux.add(atras,BorderLayout.WEST);
		aux.add(nuevo,BorderLayout.EAST);
		central=new JPanel();
		
		fon.add(aux,BorderLayout.NORTH);
	}
	public VentanaMensajes(ControladorPanel control) {
		int x=(int) (Toolkit.getDefaultToolkit().getScreenSize().width);
		int y=(int) (Toolkit.getDefaultToolkit().getScreenSize().height);
		this.setSize((int)(x*0.40), (int)(y*0.5));
		this.setMinimumSize(new Dimension((int)(x*0.40), (int)(y*0.5)));
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		fon=new Fondo(this,"Resource/Imagenes/fondo.jpeg");
		
		this.setLayout(new BorderLayout());

		ImageIcon img = new ImageIcon("Resource/Imagenes/Logos/logo-cardio-finito100x100.png");
		this.setIconImage(img.getImage());
		this.setLayout(new BorderLayout());
		fon.setLayout(new BorderLayout(3,1));
		JPanel aux=new JPanel();
		aux.setLayout(new BorderLayout());
		aux.setOpaque(false);
		atras=new JButton("Back");
		atras.setActionCommand(ControladorMensaje.ATRASMENS);
		atras.addActionListener(control);
		nuevo=new JButton("New Message");
		nuevo.addActionListener(control);
		nuevo.setActionCommand(ControladorMensaje.NEWMENSAJE);
		aux.add(atras,BorderLayout.WEST);
		aux.add(nuevo,BorderLayout.EAST);
		central=new JPanel();
		
		fon.add(aux,BorderLayout.NORTH);
	}
	
		public void VentanaMensajesTodos(Paciente p,ControladorMensaje control,Usuario us){
			System.out.println(p.getApellido());
			p.setMensajes(Conexion.consultarMensajes(p));
			atras.setEnabled(false);
			Vector<Mensaje> recibidos=(Vector<Mensaje>) p.getMensajes().clone();
			Vector<Mensaje> enviados=new Vector<Mensaje>();
			for(int i=recibidos.size()-1;i>=0;i--) {
				if(recibidos.get(i).getDniUsuario()==us.getDni()) {
					enviados.add(recibidos.get(i));
					recibidos.remove(i);
				}
			}
			 list=new JList<Mensaje>(recibidos);
			 envlist=new JList<Mensaje>(enviados);
			 envlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			 
			list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			JScrollPane scr=new JScrollPane();
			JScrollPane scr2=new JScrollPane();
			scr.setViewportView(list);
			scr2.setViewportView(envlist);
			
			DefaultListCellRenderer a=(new DefaultListCellRenderer() {
                public Component getListCellRendererComponent(JList list, Object value, int index,
                          boolean isSelected, boolean cellHasFocus) {
                     Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                     if (value instanceof Mensaje) {
                   	  Mensaje nextUser = (Mensaje) value;
                          if (nextUser.getLeido()==Constantes.LEIDO) {
                               setBackground(Color.GREEN);
                          } else {
                               setBackground(Color.RED);
                          }
                          if (isSelected) {
                               setBackground(getBackground().darker());
                          }
                     } 
                     return c;
                }

           });
			 list.setCellRenderer(a);
			envlist.setCellRenderer(a);
			list.addListSelectionListener(control);
			envlist.addListSelectionListener(control);
			
			JPanel datos=new JPanel();
			datos.setLayout(new BorderLayout());
			JPanel emisor=new JPanel();
			emisor.setLayout(new BoxLayout(emisor,BoxLayout.Y_AXIS));
			JPanel asunt=new JPanel();
			asunto=new JTextField(15);
			asunto.setEditable(false);
			asunt.setLayout(new FlowLayout());
			asunt.add(new JLabel("Asunto: "));
			asunt.add(asunto);
			info =new JPanel();
			this.emisor=new JTextField(15);
			this.emisor.setEditable(false);
			fecha=new JLabel("fecha   ");
			datos.add(fecha,BorderLayout.EAST);
			JPanel aux=new JPanel();
			aux.setLayout(new FlowLayout());
			aux.add(new JLabel("   De:    "));
			aux.add(this.emisor);
			texto=new JTextArea();
			texto.setEditable(false);
			texto.setLineWrap(true);
			
			
			emisor.add(aux);
			emisor.add(asunt);
			datos.add(emisor,BorderLayout.CENTER);
			
			info.setLayout(new BorderLayout());
			
			info.add(datos,BorderLayout.NORTH);
			info.add(texto,BorderLayout.CENTER);
			//fon.add(info,BorderLayout.EAST);
			
			central.removeAll();
			central.setVisible(false);
			central.setOpaque(false);
			central.setLayout(new BorderLayout());
			
			JPanel mens=new JPanel();
			JPanel envi=new JPanel();
			envi.setLayout(new BorderLayout());
			JPanel reci=new JPanel();
			reci.setLayout(new BorderLayout());
			envi.add(scr2,BorderLayout.CENTER);
			envi.add(new JLabel("Mensajes Enviados"),BorderLayout.NORTH);
			reci.add(scr,BorderLayout.CENTER);
			reci.add(new JLabel("Mensajes Recibidos"),BorderLayout.NORTH);
			mens.setLayout(new BorderLayout());
			mens.add(reci,BorderLayout.CENTER);
			mens.add(envi,BorderLayout.SOUTH);
			central.add(mens,BorderLayout.WEST);
			central.add(info,BorderLayout.CENTER);
			
			fon.add(central,BorderLayout.CENTER);
			central.setVisible(true);
			this.add(fon,BorderLayout.CENTER);
		}
		public JList<Mensaje> getList() {
			return list;
		}
		public void actInfo(Mensaje men) {
			String rol="";
			if(men.getRol()==Constantes.TECNICO) {
				rol="Tecnico";
			} else if(men.getRol()==Constantes.MEDICO) {
				rol="Medico";
			}
			asunto.setText(men.getAsunto());
			System.out.println(Conexion.getUserName(men.getDniUsuario())+" "+men.getDniUsuario()+Utilidades.letraDNI(men.getDniUsuario()));
			emisor.setText(men.getUsuario()+" "+men.getDniUsuario()+Utilidades.letraDNI(men.getDniUsuario()));
			texto.setText(men.getData());
			fecha.setText(rol+"    "+String.valueOf(men.getFecha()).substring(6, 8)+"-"+String.valueOf(men.getFecha()).substring(4, 6)+"-"+String.valueOf(men.getFecha()).substring(0, 4)+"     ");
			
		}

		public JPanel getCentral() {
			return central;
		}
		
		public void mensajeNuevo(ControladorMensaje control) {
			System.out.println("HOLA");
			atras.setEnabled(true);
			central.setVisible(false);
			central.removeAll();
			JPanel datos=new JPanel();
			datos.setLayout(new BorderLayout());
			JPanel emisor=new JPanel();
			emisor.setLayout(new BoxLayout(emisor,BoxLayout.Y_AXIS));
			JPanel asunt=new JPanel();
			asunto=new JTextField(15);
			asunto.setEditable(true);
			asunt.setLayout(new FlowLayout());
			asunt.add(new JLabel("Asunto: "));
			asunt.add(asunto);
			info =new JPanel();
			this.emisor=new JTextField(15);
			this.emisor.setEditable(true);
			JButton enviar=new JButton("SEND");
			enviar.addActionListener(control);
			enviar.setActionCommand(ControladorMensaje.SENDMENS);
			datos.add(enviar,BorderLayout.EAST);
			JPanel aux=new JPanel();
			aux.setLayout(new FlowLayout());
			texto=new JTextArea();
			texto.setEditable(true);
			texto.setLineWrap(true);
			
			
			emisor.add(aux);
			emisor.add(asunt);
			datos.add(emisor,BorderLayout.CENTER);
			
			info.setLayout(new BorderLayout());
			
			info.add(datos,BorderLayout.NORTH);
			info.add(texto,BorderLayout.CENTER);
			central.add(info,BorderLayout.CENTER);
			central.setVisible(true);
		}

		public JTextField getAsunto() {
			return asunto;
		}

		public JTextArea getTexto() {
			return texto;
		}
		public JList<Mensaje> getEnvlist() {
			return envlist;
		}
		public void setEnvlist(JList<Mensaje> reclist) {
			this.envlist = reclist;
		}
		
		
		
}
