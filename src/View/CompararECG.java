package View;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import Control.ControladorComparar;
import Control.GraphController;
import Model.ECG;
/**
 * Ventana para la seleccion de ECG relacionados con un paciente
 * para poder posteriormente compararlos en la misma ventana
 * @author HeartLight
 * 
 * @version Final
 * 
 * @see JList
 * @see ECG
 * @see GraficaECG
 *
 */
public class CompararECG extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JList<String> aselec;
	private JList<String> selec;
	private JPanel all;
	
	/**
	 * Primera subventana en la que a traves de dos jlist se pueden seleccionar
	 * los ecg que se desean comparar
	 * @param ecg Todos los ecg disponible para la comparacion
	 */
	public void seleccion(Vector<ECG> ecg){
		this.setResizable(false);
		all=new JPanel();
		int x=(int) (Toolkit.getDefaultToolkit().getScreenSize().width);
		int y=(int) (Toolkit.getDefaultToolkit().getScreenSize().height);
		double porcex=0.25;
		double porcey=0.30;
		this.setSize((int)(x*porcex), (int)(y*porcey));
		this.setLocation((int)((x/2)-(x*porcex/2)), (int)((y/2)-(y*porcey/2)));
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		all.setLayout(new BorderLayout());
		this.setLayout(new BorderLayout());
        JPanel usado=new JPanel();
        usado.setLayout(new GridLayout(1,3));
        JPanel pan3=new JPanel();
        pan3.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        JPanel pan2=new JPanel();
        pan2.setLayout(new BorderLayout());
        JPanel pan=new JPanel();
        pan.setLayout(new BoxLayout(pan,BoxLayout.Y_AXIS));
        
        Vector<String> aux=new Vector<String>();
       for(int i=0;i<ecg.size();i++){
    	   aux.add(ecg.get(i).getNombre());
       }
        Vector<String> aux2=new Vector<String>();
        aselec=new JList<String>(aux);
        selec=new JList<String>(aux2);
        selec.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        aselec.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JButton bu=new JButton("Insertar>>>");
        
        JButton bu2=new JButton("<<<Eliminar");
        JButton bu3=new JButton("Comparar");
        

        new ControladorComparar(this,bu,bu2,bu3,ecg);
       
        JButton invi=new JButton();
        invi.setContentAreaFilled(false);
        invi.setBorderPainted(false);
        invi.setOpaque(false);
        JButton invi2=new JButton();
        invi2.setContentAreaFilled(false);
        invi2.setBorderPainted(false);
        invi2.setOpaque(false);
        JPanel invi3=new JPanel();
        invi3.setOpaque(false);
        JPanel invi4=new JPanel();
        invi4.setOpaque(false);
        
        JScrollPane jsp=new JScrollPane();
        jsp.setViewportView(selec);
        JScrollPane jsp2=new JScrollPane();
        jsp2.setViewportView(aselec);
        
        pan.add(bu);
        pan.add(bu2);
        pan.add(bu3);
        pan3.add(pan,gbc);
        pan2.add(pan3, BorderLayout.CENTER);
        usado.add(jsp2);
        usado.add(pan2);
        usado.add(jsp);
        all.add(usado,BorderLayout.CENTER);
        all.add(invi,BorderLayout.SOUTH);
        all.add(invi2,BorderLayout.NORTH);
        all.add(invi3,BorderLayout.EAST);
        all.add(invi4,BorderLayout.WEST);
        this.add(all,BorderLayout.CENTER);
	}
	/**
	 * Getter de JList
	 * @return la JList en donde se encuentra los ecg que aun no estan seleccionados para comparar
	 */
		public JList<String> getAselec() {
		return aselec;
	}
/**
 * Getter de JList
 * @return la JList en donde se encuentran los ecg seleccionados para comparar
 */
	public JList<String> getSelec() {
		return selec;
	}
/**
 * Segunda subventana en la que se plasman los ECG seleccionado que se desean comparar
 * @param ecg ecgs que se van a mostrar para compararlos
 */
		public void Comparacion(Vector<ECG> ecg){
			 
			this.setResizable(true);
			this.getContentPane().removeAll();
			all=new JPanel();
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			all.setLayout(new BorderLayout());
			JPanel aux=new JPanel();
			int x=(int) (Toolkit.getDefaultToolkit().getScreenSize().width);
			int y=(int) (Toolkit.getDefaultToolkit().getScreenSize().height);
			double porcex=0.85;
			double porcey=0.5;
			if(ecg.size()>4) {
				this.setExtendedState( this.getExtendedState()|JFrame.MAXIMIZED_BOTH );
			} else if(ecg.size()>2) {
				porcex=0.85;
				porcey=0.75;
			}
			this.setSize((int)(x*porcex), (int)(y*porcey));
			this.setLocation((int)((x/2)-(x*porcex/2)), (int)((y/2)-(y*porcey/2)));
			this.setMinimumSize(new Dimension((int)(x*0.85), (int)(y*0.5)));
			this.setLayout(new BorderLayout());
			aux.setLayout(new BoxLayout(aux,BoxLayout.Y_AXIS));
			System.out.println(ecg.size());
			for(int i=0;i<ecg.size();i++){
				JPanel pan=new JPanel();
				if((ecg.size()-i)%2==0){
					pan.setLayout(new GridLayout(1,2));
					GraficaECG g=new GraficaECG();
					g.initUITEC();
					g.addGraphic(ecg.get(i));
					g.addController(new GraphController(g));
					pan.add(g);
					i++;
					g=new GraficaECG();
					g.initUITEC();
					g.addGraphic(ecg.get(i));
					g.addController(new GraphController(g));
					pan.add(g);
				} else {
					pan.setLayout(new GridLayout(1,1));
					GraficaECG g=new GraficaECG();
					g.addController(new GraphController(g));
					g.addGraphic(ecg.get(i));
					g.initUITEC();
					pan.add(g);
				}
				aux.add(pan);
			}
			
			all.add(aux,BorderLayout.CENTER);
			this.add(all,BorderLayout.CENTER);
		}
		
}
