package View;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * JPanel en el cual se pintara una imagen, ya definida como 
 * el logo, manteniendo la simetria con respecto a otro componente
 * 
 * @author HeartLight
 * 
 * @version Final
 * 
 * @see BufferedImage
 * @see JFrame
 * @see JPanel
 *
 */
public class Logo extends JPanel{

	private static final long serialVersionUID = 1L;
	private final String logo="Resource/Imagenes/Logos/logo600.png";
	private BufferedImage image;
	private int CO;
	private int CA;
	private double ang;
	private JFrame a=null;
	private JPanel b=null;
	private int porcentaje=100;
	private boolean centrado=false;
	private boolean re=true;
	private int i=0;

	/**
	 * Constructor que asigna la imagen de logo
	 * @param a JFrame el cual agarrara como referencia para el size de la imagen
	 */
    public Logo(JFrame a) {
       try {
    	   this.a=a;
          image = ImageIO.read(new File(logo));
          CO=image.getHeight();
          CA=image.getWidth();
          ang=(Math.atan((double)CO/CA));
       } catch (IOException ex) {
    	   
       }
      
    }
    /**
	 * Constructor que asigna la imagen de logo
	 * @param b JPanel el cual agarrara como referencia para el size de la imagen
	 */
    public Logo(JPanel b) {
        try {
     	   this.b=b;
           image = ImageIO.read(new File(logo));
           CO=image.getHeight();
           CA=image.getWidth();
           ang=(Math.atan((double)CO/CA));
        } catch (IOException ex) {
     	   
        }
       
     }
    /**
	 * Constructor que asigna la imagen de logo
	 * @param a JFrame el cual agarrara como referencia para el size de la imagen
	 * @param x Porcentaje que ocupara del size referencia
	 */
    public Logo(JFrame a, int x) {
        try {
        	porcentaje=x;
     	   this.a=a;
           image = ImageIO.read(new File(logo));
           CO=image.getHeight();
           CA=image.getWidth();
           ang=(Math.atan((double)CO/CA));
        } catch (IOException ex) {
     	   
        }
       
     }
    /**
   	 * Constructor que asigna la imagen de logo
   	 * @param b JPanel el cual agarrara como referencia para el size de la imagen
   	 * @param x Path de una imagen que se quiera usar
   	 */
    public Logo(JPanel b, String x) {
        try {
     	   this.b=b;
           image = ImageIO.read(new File(x));
           CO=image.getHeight();
           CA=image.getWidth();
           ang=(Math.atan((double)CO/CA));
        } catch (IOException ex) {
     	   
        }
       
     }
    /**
   	 * Constructor que asigna la imagen de logo
   	 * @param b JPanel el cual agarrara como referencia para el size de la imagen
   	 * @param x Porcentaje que ocupara del size referencia
   	 */
     public Logo(JPanel b, int x) {
         try {
      	   this.b=b;
      	   porcentaje=x;
            image = ImageIO.read(new File(logo));
            CO=image.getHeight();
            CA=image.getWidth();
            ang=(Math.atan((double)CO/CA));
         } catch (IOException ex) {
      	   
         }
        
      }
     /**
 	 * Constructor que asigna la imagen de logo
 	 */
    public Logo() {
        try {
           image = ImageIO.read(new File(logo));
           CO=image.getHeight();
           CA=image.getWidth();
           ang=(Math.atan((double)CO/CA));
        } catch (IOException ex) {
     	   
        }
       
     }
    /**
     * Asignar si quiere que la imagen se reescale
     * @param a true para que se reescale/ false para que no
     */
    public void reescalable(boolean a){
    	re=a;
    }
    
    /**
     * Encargado de pintar la imagen en el JPanel tomando en cuenta todos los elementos,
     * el size referencia, si estara centrado, los datos para mantener la simetria, si se quiere
     * reescalar o no
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(i==0 || re==true){
        if(a!=null)
        	this.setSize(a.getWidth()*porcentaje/100, a.getHeight()*porcentaje/100);
        else if(b!=null)
            this.setSize(b.getWidth()*porcentaje/100, b.getHeight()*porcentaje/100);
        i++;
        }
        if(centrado==true){
        if(CO==CA){
        	if(this.getWidth()<this.getHeight())
        		g.drawImage(resize(image,this.getWidth(),this.getWidth()),0,this.getHeight()/2-this.getWidth()/2, this); 
        	else
        		g.drawImage(resize(image,this.getHeight(),this.getHeight()),this.getWidth()/2-this.getHeight()/2, 0, this); 
        } else if((Math.tan(ang)*this.getWidth())<=this.getHeight()){
        	g.drawImage(resize(image,this.getWidth(),(int) (Math.tan(ang)*this.getWidth())), 0, (int) (this.getHeight()/2-(Math.tan(ang)*this.getWidth()/2)), this); 
        } else {
        	g.drawImage(resize(image,(int) (this.getHeight()/Math.tan(ang)),this.getHeight()), (int) (this.getWidth()/2-(this.getHeight()/Math.tan(ang)/2)), 0, this); 
        }
        } else {
        	if(CO==CA){
            	if(this.getWidth()<this.getHeight())
            		g.drawImage(resize(image,this.getWidth(),this.getWidth()),0,0, this); 
            	else
            		g.drawImage(resize(image,this.getHeight(),this.getHeight()),0, 0, this); 
            } else if((Math.tan(ang)*this.getWidth())<=this.getHeight()){
            	g.drawImage(resize(image,this.getWidth(),(int) (Math.tan(ang)*this.getWidth())), 0, 0, this); 
            } else {
            	g.drawImage(resize(image,(int) (this.getHeight()/Math.tan(ang)),this.getHeight()), 0, 0, this); 
            }
        }
    }
    
    /**
     * Asignacion si quiere que la imagen se encuentre en el centro del Jpanel
     * @param aux true para que se centre/ false para que no
     */
    public void centrado(boolean aux){
    	centrado=aux;
    }
    
    
    /**
     * Se encarga de reescalar una imagen (https://stackoverflow.com/questions/4756268/how-to-resize-the-buffered-image-n-graphics-2d-in-java)
     * @param source Imagen que se quiere reescalar
     * @param width Ancho
     * @param height Altura
     * @return Imagen reescalada
     */
    public BufferedImage resize(BufferedImage source,
            int width, int height) {
        return progressiveResize(source, width, height,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    }
    /**
     * Se encarga de reescalar una imagen de acuerdo a un algoritmo (https://stackoverflow.com/questions/4756268/how-to-resize-the-buffered-image-n-graphics-2d-in-java)
     * @param source Imagen que se quiere reescalar
     * @param width Ancho
     * @param height Altura
     * @param hint Forma de reescalado
     * @return Imagen reescalada
     */
    private static BufferedImage progressiveResize(BufferedImage source,
            int width, int height, Object hint) {
        int w = Math.max(source.getWidth()/2, width);
        int h = Math.max(source.getHeight()/2, height);
        BufferedImage img = commonResize(source, w, h, hint);
        while (w != width || h != height) {
            BufferedImage prev = img;
            w = Math.max(w/2, width);
            h = Math.max(h/2, height);
            img = commonResize(prev, w, h, hint);
            prev.flush();
        }
        return img;
    }
    /**
     * Se encarga de reescalar una imagen de acuerdo a un algoritmo (https://stackoverflow.com/questions/4756268/how-to-resize-the-buffered-image-n-graphics-2d-in-java)
     * @param source Imagen que se quiere reescalar
     * @param width Ancho
     * @param height Altura
     * @param hint Forma de reescalado
     * @return Imagen reescalada
     */
    private static BufferedImage commonResize(BufferedImage source,
            int width, int height, Object hint) {
        BufferedImage img = new BufferedImage(width, height,
                source.getType());
        Graphics2D g = img.createGraphics();
        try {
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, hint);
            g.drawImage(source, 0, 0, width, height, null);
        } finally {
            g.dispose();
        }
        return img;
    }

}
