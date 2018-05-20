package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Vector;


import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import Control.ControladorFiltrado;
import Control.ControladorPanelM;
import Model.ECG;
import Model.Medico;
import Model.Paciente;

import javax.swing.border.LineBorder;
/**
 * @author Pablo Ferrer Luis Ferrer Diego Fernandez
 * 
 * La clase VentanaMedicoECG muestra un panel con los ultimos ECG tomados a los pacientes 
 * de un medico estos podran filtrarse por leidos o no leidos y en un principio se 
 * muestra la informacion basica del ECG, cuando pulsas en dar diagnostico esta 
 * re redirige a PanelPAciente desde donde el medico podra dar su diagnostico
 * 
 * @version Final
 * 
 * @see PanelPaciente
 *
 */
public class VentanaMedicoECG extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JScrollPane jsp;
	private JPanel rey4;
	private  JComboBox<String> comboBox;

	/**
	 * Create the frame.
	 * @param vm VentanaMedico en la cual se ubicaran todos los elementos
	 * @param m Medico al cual pertenecen todos los pacientes
	 */
	public VentanaMedicoECG(VentanaMedico vm,Medico m) {

		this.setLayout(new BorderLayout());
		this.setOpaque(false);

		JPanel pan1 = new JPanel();
		pan1.setOpaque(false);
		BorderLayout bor=new BorderLayout();
		bor.setHgap(5);
		pan1.setLayout(bor);
		JPanel p=new JPanel();
		p.setOpaque(false);
		pan1.add(p,BorderLayout.WEST);
		//PANEL QUE PONE FLOW AL CENTRO
		JPanel colocar = new JPanel();
		colocar.setLayout(new FlowLayout());
		colocar.setOpaque(false);

		//PANEL DE RELLENO PARA CENTRARR EL TABLE
		JPanel rey1 = new JPanel();
		rey1.setLayout(new FlowLayout());
		rey1.setOpaque(false);

		//PANEL QUE CONTIENE LOS PANELES DE CADA PACIENTE
		rey4 = new JPanel();
		rey4.setOpaque(false);
		rey4.setLayout(new BoxLayout(rey4,BoxLayout.Y_AXIS));

		//PANEL QUE TIENE EL BUSCADOR
		JPanel rey5 = new JPanel();
		rey5.setLayout(new GridLayout(2,1));
		rey5.setOpaque(false);

		//PANEL PARA CENTRAR BUSCADOR Y TEXTFIELD
		JPanel rey6 = new JPanel();
		rey6.setOpaque(false);
		rey6.setLayout(new BorderLayout());
		
		Vector<ECG> auxec=new Vector<ECG>();
		Vector<Paciente> auxpac=new Vector<Paciente>();
		for(int i=0;i<m.getPacientes().size();i++) {
			for(int j=0;j<m.getPacientes().get(i).getEcgs().size();j++) {
				auxec.add(m.getPacientes().get(i).getEcgs().get(j));
				auxpac.add(m.getPacientes().get(i));
			}
		}

		quicksort(auxec,auxpac,0,auxec.size()-1);

		
		for(int i= 0;i<auxec.size();i++){
			PanelPaciente pan = new PanelPaciente(auxpac.get(i),auxec.get(i));
			pan.setBorder(new LineBorder(Color.gray, 2));
			pan.addMouseListener(new ControladorPanelM(vm,auxpac.get(i), m,auxec.get(i),0));
			JPanel invi = new JPanel();
			invi.setOpaque(false);
			rey4.add(pan);	
			rey4.add(invi);
		}
		
		if(auxec.size()<10) {
			for(int i= auxec.size();i<10;i++){
				JPanel relle=new JPanel();
				relle.setLayout(new BorderLayout());
				JButton b=new JButton();
				b.setContentAreaFilled(false);
				b.setOpaque(false);
				b.setBorderPainted(false);
				relle.add(b,BorderLayout.CENTER);
				relle.setOpaque(false);
				rey4.add(relle);
			}
		}

		
		//BUSCADOR
		JLabel n = new JLabel("Filtrar por: ");
		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[]{"Ordenar por Fecha","Ordenar por NO leidos"}));
		//CREO EL SCROLL
		jsp = new JScrollPane();
		jsp.setViewportBorder(null);
		 jsp.setOpaque(false);
		 jsp.getViewport().setOpaque(false);
		 jsp.setBorder(null);
		 jsp.getViewport().setBorder(null);
		jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.setViewportView(rey4);


		//AÑADIMOS LOS OBJETOS A LOS LAYOUTS
		rey5.add(n,BorderLayout.NORTH);
		rey5.add(comboBox,BorderLayout.CENTER);
		
		//rey6.add(rey5,BorderLayout.NORTH);
		rey6.add(jsp, BorderLayout.CENTER);
		//rey6.add(rey1,BorderLayout.EAST);

		JPanel filtro=new JPanel();
		filtro.setOpaque(false);
		filtro.setLayout(new BoxLayout(filtro,BoxLayout.Y_AXIS));
		JPanel lee=new JPanel();
		lee.setOpaque(false);
		lee.setLayout(new BoxLayout(lee,BoxLayout.Y_AXIS));
		
		lee.add(new JLabel(" "));
		lee.add(new JLabel("Filtrar por vistos:"));
		lee.add(new JLabel(" "));
		JRadioButton leido=new JRadioButton("Leido");
		leido.setOpaque(false);
		
		JRadioButton noleido=new JRadioButton("No leido");
		noleido.setOpaque(false);
		lee.add(leido);
		lee.add(noleido);
		
		lee.add(new JLabel(" "));
		lee.add(new JLabel(" "));
		lee.add(new JLabel("Filtrar por diagnosticado:            "));
		lee.add(new JLabel(" "));
		JRadioButton diag=new JRadioButton("Diagnosticado");
		JRadioButton nodiag=new JRadioButton("Sin Diagnosticar");
		diag.setOpaque(false);
		nodiag.setOpaque(false);
		lee.add(diag);
		lee.add(nodiag);
		filtro.add(lee);
		lee.add(new JLabel(" "));
		lee.add(new JLabel(" "));
		JButton filtrar=new JButton("Filtrar");
		filtrar.addActionListener(new ControladorFiltrado(leido,noleido,diag,nodiag,vm,m,this,auxec,auxpac));
		filtro.add(filtrar);
		
		JButton invi=new JButton();
		invi.setContentAreaFilled(false);
		invi.setOpaque(false);
		invi.setBorderPainted(false);
		JButton invi2=new JButton();
		invi2.setContentAreaFilled(false);
		invi2.setOpaque(false);
		invi2.setBorderPainted(false);
		
		JButton invi3=new JButton();
		invi3.setContentAreaFilled(false);
		invi3.setOpaque(false);
		invi3.setBorderPainted(false);
		
		
		
		rey6.add(filtro,BorderLayout.EAST);
		this.add(rey6,BorderLayout.CENTER);
		this.add(invi,BorderLayout.NORTH);
		this.add(invi2,BorderLayout.WEST);
		this.add(invi3,BorderLayout.SOUTH);


	}
	/**
	 * poner la visibilidad a true del panel
	 */
	public void ver() {
		this.setVisible(true);
	}

/**
 * Actualizar el panel que contiene los ECG usando un filtro	
 * @param vm VentanaMedico en la que se pondra todo
 * @param m Medico al que le pertenece los pacientes
 * @param leido Filtro si se veran los leidos
 * @param diag Filtro si se veran los diagnosticados
 * @param auxec Vector con todos los ecg
 * @param auxpac Vector con todos los pacientes  a los que le pertenecen esos ecg
 */
public void actPanel(VentanaMedico vm,Medico m, int leido, int diag,Vector<ECG> auxec,Vector<Paciente> auxpac) {
	rey4.setVisible(false);
	rey4.removeAll();
	
	rey4.setOpaque(false);
	rey4.setLayout(new BoxLayout(rey4,BoxLayout.Y_AXIS));

	int cont=0;
	for(int i= 0;i<auxec.size();i++){
		if(leido==0 && diag==0) {
			PanelPaciente pan = new PanelPaciente(auxpac.get(i),auxec.get(i));
			pan.setBorder(new LineBorder(Color.gray, 2));
			pan.addMouseListener(new ControladorPanelM(vm,auxpac.get(i), m,auxec.get(i),0));
			JPanel invi = new JPanel();
			invi.setOpaque(false);
			rey4.add(pan);	
			rey4.add(invi);
			cont++;
		} else  {
			if(leido==-1) {
				if(diag==-1) {
					if(auxec.get(i).isLeido()==false && auxec.get(i).getDiagnostico().isEmpty()) {
						PanelPaciente pan = new PanelPaciente(auxpac.get(i),auxec.get(i));
						pan.setBorder(new LineBorder(Color.gray, 2));
						pan.addMouseListener(new ControladorPanelM(vm,auxpac.get(i), m,auxec.get(i),0));
						JPanel invi = new JPanel();
						invi.setOpaque(false);
						rey4.add(pan);	
						rey4.add(invi);
						cont++;
					}
				} else if(diag==1){
					if(auxec.get(i).isLeido()==false && !auxec.get(i).getDiagnostico().isEmpty()) {
						PanelPaciente pan = new PanelPaciente(auxpac.get(i),auxec.get(i));
						pan.setBorder(new LineBorder(Color.gray, 2));
						pan.addMouseListener(new ControladorPanelM(vm,auxpac.get(i), m,auxec.get(i),0));
						JPanel invi = new JPanel();
						invi.setOpaque(false);
						rey4.add(pan);	
						rey4.add(invi);
						cont++;
					}
				} else {
					if(auxec.get(i).isLeido()==false) {
						PanelPaciente pan = new PanelPaciente(auxpac.get(i),auxec.get(i));
						pan.setBorder(new LineBorder(Color.gray, 2));
						pan.addMouseListener(new ControladorPanelM(vm,auxpac.get(i), m,auxec.get(i),0));
						JPanel invi = new JPanel();
						invi.setOpaque(false);
						rey4.add(pan);	
						rey4.add(invi);
						cont++;
					}
				}
				
			} else if(leido==1){
				if(diag==-1) {
					if(auxec.get(i).isLeido()==true && auxec.get(i).getDiagnostico().isEmpty()) {
						PanelPaciente pan = new PanelPaciente(auxpac.get(i),auxec.get(i));
						pan.setBorder(new LineBorder(Color.gray, 2));
						pan.addMouseListener(new ControladorPanelM(vm,auxpac.get(i), m,auxec.get(i),0));
						JPanel invi = new JPanel();
						invi.setOpaque(false);
						rey4.add(pan);	
						rey4.add(invi);
						cont++;
					}
				} else if(diag==1){
					if(auxec.get(i).isLeido()==true && !auxec.get(i).getDiagnostico().isEmpty()) {
						PanelPaciente pan = new PanelPaciente(auxpac.get(i),auxec.get(i));
						pan.setBorder(new LineBorder(Color.gray, 2));
						pan.addMouseListener(new ControladorPanelM(vm,auxpac.get(i), m,auxec.get(i),0));
						JPanel invi = new JPanel();
						invi.setOpaque(false);
						rey4.add(pan);	
						rey4.add(invi);
						cont++;
					}
				}  else {
					if(auxec.get(i).isLeido()==true) {
						PanelPaciente pan = new PanelPaciente(auxpac.get(i),auxec.get(i));
						pan.setBorder(new LineBorder(Color.gray, 2));
						pan.addMouseListener(new ControladorPanelM(vm,auxpac.get(i), m,auxec.get(i),0));
						JPanel invi = new JPanel();
						invi.setOpaque(false);
						rey4.add(pan);	
						rey4.add(invi);
						cont++;
					}
				}
			} else if(leido==0) {
				if(diag==-1) {
					if(auxec.get(i).getDiagnostico().isEmpty()) {
						PanelPaciente pan = new PanelPaciente(auxpac.get(i),auxec.get(i));
						pan.setBorder(new LineBorder(Color.gray, 2));
						pan.addMouseListener(new ControladorPanelM(vm,auxpac.get(i), m,auxec.get(i),0));
						JPanel invi = new JPanel();
						invi.setOpaque(false);
						rey4.add(pan);	
						rey4.add(invi);
						cont++;
					}
				} else if(diag==1){
					if(!auxec.get(i).getDiagnostico().isEmpty()) {
						PanelPaciente pan = new PanelPaciente(auxpac.get(i),auxec.get(i));
						pan.setBorder(new LineBorder(Color.gray, 2));
						pan.addMouseListener(new ControladorPanelM(vm,auxpac.get(i), m,auxec.get(i),0));
						JPanel invi = new JPanel();
						invi.setOpaque(false);
						rey4.add(pan);	
						rey4.add(invi);
						cont++;
					}
				}
			}
			
		}
	}
	if(cont<10) {
		for(int i= cont;i<10;i++){
			JPanel relle=new JPanel();
			relle.setLayout(new BorderLayout());
			JButton b=new JButton();
			b.setContentAreaFilled(false);
			b.setOpaque(false);
			b.setBorderPainted(false);
			relle.add(b,BorderLayout.CENTER);
			relle.setOpaque(false);
			rey4.add(relle);
		}
	}
	rey4.setVisible(true);
}

	private static int pivotear( Vector<ECG>L, Vector<Paciente>M,int ini, int fin){
		int i=ini;
		int med=(fin+ini)/2;
		ECG auxi=L.get(med);
		Paciente auxi2=M.get(med);
		M.remove(med);
		M.insertElementAt(M.get(ini), med);
		M.remove(ini);
		M.insertElementAt(auxi2, ini);
		L.remove(med);
		L.insertElementAt(L.get(ini), med);
		L.remove(ini);
		L.insertElementAt(auxi, ini);
		
		int p=L.get(ini).getFecha();
		for(int j=ini+1;j<=fin;++j){
			if(L.get(j).getFecha()<=p){
				i++;
				if(i!=j){
					ECG aux=L.get(j);
					Paciente aux2=M.get(j);
					M.remove(j);
					M.insertElementAt(M.get(i), j);
					M.remove(i);
					M.insertElementAt(aux2, i);
					L.remove(j);
					L.insertElementAt(L.get(i), j);
					L.remove(i);
					L.insertElementAt(aux, i);
				}
			} 
		} 
		ECG aux=L.get(i);
		Paciente aux2=M.get(i);
		M.remove(i);
		M.insertElementAt(M.get(ini), i);
		M.remove(ini);
		M.insertElementAt(aux2, ini);
		L.remove(i);
		L.insertElementAt(L.get(ini), i);
		L.remove(ini);
		L.insertElementAt(aux, ini);
		
		return i;
	}
	/**
	 * Metodo de ordenacion para vector de ECG
	 * @param L Vector de ECG a ordenar
	 * @param m Vector de Pacientes a ordenar
	 * @param ini indice del primer valor
	 * @param fin indice del ultimo valor
	 */
	static void quicksort( Vector<ECG> L, Vector<Paciente> m,int ini, int fin){
		if(ini<fin){
			int x=pivotear(L,m,ini,fin);
			quicksort(L,m,ini,x-1);
			quicksort(L,m,x+1,fin);
		}
	}

/**
 * Getter del panel donde estan los Paneles de Pacientes
 * @return Panel donde se ubican los ecg
 */
	public JPanel getRey4() {
		/**
		 * @return rey4
		 */
		return rey4;
	}
	
}