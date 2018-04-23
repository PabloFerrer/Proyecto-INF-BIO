package View;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * La clase VentanaHelp se encarga de mostrar una ventana adicional
 * en la cual se encuentra un campo de texto no modificable que contendra
 * dependiendo de que usuario sea una guia para cada ventana que se extrae
 * de un archivo de texto plano.
 * 
 * @author Heartlight
 * 
 * @version Final
 */
public class VentanaHelp extends JFrame{
	private JTextArea text;
	
	private static final long serialVersionUID = 1L;
	
	public VentanaHelp(int i){
		
	    this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(475 , 520));
        this.setSize(this.getPreferredSize());
        this.setVisible(true);

        JPanel panel = new JPanel();
        //Creamos el JTextArea y le pasamos sus caracteristicas
        text = new JTextArea(30, 50);
        text.setEditable(false);
        text.setLineWrap(true);
        text.setWrapStyleWord(false);
        text.setVisible(true);

        panel.add(text, BorderLayout.CENTER);
        this.getContentPane().add(panel, BorderLayout.CENTER);
        //Creamos el scroll y se lo anadimos al JTextArea
        JScrollPane jsp = new JScrollPane();
        jsp.setViewportView(text);
        this.add(jsp, BorderLayout.NORTH);

        this.setVisible(true);

        this.add(panel);
	}
	
	/**
	 * Getter que nos permite acceder al area de texto
	 * @return JTextArea text
	 */
	public JTextArea getText() {
		return text;
	}
	

}

