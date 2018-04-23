package View;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * Panel pintado con un fondo rectangular redondeado
 * 
 * @author HeartLight
 * 
 * @version Final
 * 
 * @see JPanel
 *
 */
public class Menu extends JPanel{
	private boolean iz=false;
	private boolean de=false;
	private boolean esMac=false;
	private static final long serialVersionUID = 1L;

	/**
	 * Pintar el rectangulo redondeado en todo el jpanel
	 */
	protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        g.setColor(new Color(51,153,255));
	        g.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), 30,30);
	        g.setColor(new Color(51,153,255).darker());
	        if(!esMac)
	        if(iz==true) {
	        	 g.fillRoundRect(0, 0, (int) (this.getWidth()*0.1), this.getHeight(), 30,30);
	        } else if(de==true){
	        	 g.fillRoundRect((int) (this.getWidth()*0.9), 0, (int) (this.getWidth()*0.1), this.getHeight(), 30,30);
	        }
	 }
	
	public void ladoIzq() {
		iz=true;
		de=false;
		this.repaint();
	}
	public void ladoDer() {
		de=true;
		iz=false;
		this.repaint();
	}
	public void ningunExtremo() {
		de=false;
		iz=false;
		this.repaint();
		
	}
	public void esMac() {
		esMac=true;
	}

}
