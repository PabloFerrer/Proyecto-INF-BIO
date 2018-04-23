package View;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * JPanel el cual se pintara como una imagen para ubicar en el fondo
 * u otros usos que se le quieran dar, ya que se reescala en base a otro componente
 * 
 * @author HeartLight
 *
 * @version Final 
 * 
 * @see BufferedImage
 * @see JFrame
 * @see JPanel 
 */
public class Fondo extends JPanel{

	private static final long serialVersionUID = 1L;
	private BufferedImage image=null;
	private JFrame a;
	private JPanel b;
/**
 * Constructor para que la imagen se reescale con respecto a un JFrame
 * @param a JFrame de referencia
 * @param arg Path de la imagen
 */
    public Fondo(JFrame a,String arg) {
    	this.a=a;
       try {                
          image = ImageIO.read(new File(arg));
       } catch (IOException ex) {
    	   
       }
    }
 
   
    
    public Fondo(){
    	this.setOpaque(false);
    }
    /**
     * Constructor para que la imagen se reescale con respecto a un JPanel
     * @param b JPanel de referencia
     * @param acg Path de la imagen
     */
    public Fondo(JPanel b,String acg){
     	this.b=b;
        try {                
           image = ImageIO.read(new File(acg));
        } catch (IOException ex) {
     	   
        }
    }
    /**
     * Constructor para que la imagen se reescale con respecto a un JFrame
     * @param b JFrame de referencia
     * @param acg Imagen que se pintara
     */
    public Fondo(JPanel b,Image acg){
     	this.b=b;
        image = (BufferedImage) acg;
    }
    
   /**
    * Se encarga de pintar la imagen en el JPanel con respecto al tamaño de un componente
    */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(a != null){
        this.setSize(a.getWidth(), a.getHeight());
       
        if(image!=null)
        	g.drawImage(resize(image,a.getWidth(),a.getHeight()), 0,0, this);   
        }else if(b!= null){
        	this.setSize(b.getWidth(), b.getHeight());
            
            if(image!=null)
            	g.drawImage(resize(image,b.getWidth(),b.getHeight()), 0,0, this);
        }
    }
    /**
     * Se encarga de reescalar una imagen
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
     * Se encarga de reescalar una imagen de acuerdo a un algoritmo
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
     * Se encarga de reescalar una imagen de acuerdo a un algoritmo
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