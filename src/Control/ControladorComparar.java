package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Model.ECG;
import View.CompararECG;

/**
 * Controlador en el cual se hacen todas las gestion para comparar
 * ECGs
 * 
 * @author HeartLight
 * @version Final
 *
 * @see CompararECG
 */
public class ControladorComparar {
	private CompararECG a;
	private Vector<ECG>ecgs;
	/**
	 * Constructor que se encarga de la asignacion de un controlador a cada componente
	 * @param g ventana de comparar que tendrá un par de jlist y botones
	 * @param bu Boton para la asignacion de ECG
	 * @param bu2 Boton para la asignacion inversa de ECG (Eliminacion de lista de comparacion)
	 * @param bu3 Boton para abrir la ventana con los ecg seleccionados
	 * @param ecg Todos los ecg disponibles para comparar
	 */
	public ControladorComparar(CompararECG g,JButton bu,JButton bu2,JButton bu3,Vector<ECG>ecg){
		a=g;
		ecgs=ecg;
		 a.getSelec().addMouseListener(new MouseListener(){
	        	public void mouseClicked(MouseEvent e) {}
				public void mousePressed(MouseEvent e) {}
				public void mouseReleased(MouseEvent e) {}
				public void mouseEntered(MouseEvent e) {
					a.getSelec().requestFocus();
				}
				public void mouseExited(MouseEvent e) {}
	        	
	        });
	        
		 a.getAselec().addMouseListener(new MouseListener(){
	        	public void mouseClicked(MouseEvent e) {}
				public void mousePressed(MouseEvent e) {}
				public void mouseReleased(MouseEvent e) {}
				public void mouseEntered(MouseEvent e) {
					a.getAselec().requestFocus();
				}
				public void mouseExited(MouseEvent e) {}
	        	
	        });
	        a.getSelec().addListSelectionListener(new ListSelectionListener(){
				@Override
				public void valueChanged(ListSelectionEvent e) {
					if(!a.getAselec().isSelectionEmpty())
						a.getAselec().clearSelection();
				}
	        	
	        });
	        a.getAselec().addListSelectionListener(new ListSelectionListener(){
				@Override
				public void valueChanged(ListSelectionEvent e) {
					if(!a.getSelec().isSelectionEmpty())
						a.getSelec().clearSelection();
				}
	        	
	        });
	        
	        bu.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					if(!a.getAselec().isSelectionEmpty()){
						if(a.getSelec().getModel().getSize()<6) {
							a.getAselec().setVisible(false);
							a.getSelec().setVisible(false);
							 Vector<String> first=new Vector<String>();
							 for(int i=0;i<a.getAselec().getModel().getSize();i++){
								 first.add(a.getAselec().getModel().getElementAt(i));
							 }
							 Vector<String> second=new Vector<String>();
							 for(int i=0;i<a.getSelec().getModel().getSize();i++){
								 second.add(a.getSelec().getModel().getElementAt(i));
							 }
							 
							second.add(a.getAselec().getSelectedValue().toString());
							a.getSelec().setListData(second);
							first.remove(a.getAselec().getSelectedValue());
							a.getAselec().setListData(first);
							a.getAselec().clearSelection();
							
							a.getAselec().setVisible(true);
							a.getSelec().setVisible(true);
						} else {
							JOptionPane.showMessageDialog(null, "No puede seleccionar mas de 6 ECG, lo sentimos");
						}
					}
				}
	        });
	        bu2.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					if(!a.getSelec().isSelectionEmpty()){
					a.getAselec().setVisible(false);
					a.getSelec().setVisible(false);
					
					Vector<String> first=new Vector<String>();
					 for(int i=0;i<a.getSelec().getModel().getSize();i++){
						 first.add(a.getSelec().getModel().getElementAt(i));
					 }
					 Vector<String> second=new Vector<String>();
					 for(int i=0;i<a.getAselec().getModel().getSize();i++){
						 second.add(a.getAselec().getModel().getElementAt(i));
					 }
					 
					second.add(a.getSelec().getSelectedValue().toString());
					a.getAselec().setListData(second);
					first.remove(a.getSelec().getSelectedValue());
					a.getSelec().clearSelection();
					a.getSelec().setListData(first);
					
					a.getAselec().setVisible(true);
					a.getSelec().setVisible(true);
					}
				}
	        });
	        bu3.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					if(a.getSelec().getModel().getSize()!=0){
					Vector<ECG> ec=new Vector<ECG>();
					for(int i=0;i<a.getSelec().getModel().getSize();i++){
						boolean aux=true;
						int j=0;
						while(aux &&j<ecgs.size()){
							if(a.getSelec().getModel().getElementAt(i).equals(ecgs.get(j).getNombre())){
								ec.add(ecgs.get(j));
								aux=false;
							}
							j++;
						}
					}
					if(a!=null) {
						a.dispose();
					}
					a=new CompararECG();
						a.Comparacion(ec);
						a.setVisible(true);
				
				} else {
					JOptionPane.showMessageDialog(null, "Debe seleccionar por lo menos 1 ECG");
				}
				} 
	        	
	        });
	}
	
	

}
