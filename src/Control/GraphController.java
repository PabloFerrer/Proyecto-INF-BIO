package Control;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import View.GraficaECG;

/**
 * Controlador de los adicionales de la grafica en el que se pueden
 * modificar la escala del eje de las X de la grafica y poder desplazarse 
 * sobre dicho eje hasta que la grafica se acabe
 * 
 * @author HeartLight
 * 
 * @version Final
 * 
 * @see GraficaECG
 * @see JSlider
 *
 */
public class GraphController implements ChangeListener{
	private GraficaECG a;
	private int fps=5000;
	private int esca=0;
	private double maxminy[]=new double[2];
	
	/**
	 * Constructor en el que se obtiene la grafica que se va a controlar
	 * y se guarda el maximo y minimo del eje Y
	 * @param a GraficaECG que se va a controlar
	 */
	public GraphController(GraficaECG a){
		maxminy= new double[]{a.getChart().getXYPlot().getRangeAxis().getRange().getLowerBound(),a.getChart().getXYPlot().getRangeAxis().getRange().getUpperBound()};
		this.a=a;
	}
	

	/**
	 * Listener en el cual se lee cualquier cambio en los componentes
	 * JSlider y JSpinner, si existe algun cambio se actualiza la grafica
	 */
	public void stateChanged(ChangeEvent e) {
		double max=Double.MIN_VALUE;
		double min=Double.MAX_VALUE;
		int i=0;
		while(i<a.getDataset().getSeriesCount()){
			if(a.getDataset().getSeries(i).getMaxY()>max){
				max=a.getDataset().getSeries(i).getMaxY();
			}
			if(a.getDataset().getSeries(i).getMinY()<min){
				min=a.getDataset().getSeries(i).getMinY();
			}
			i++;
		}
		if(max==Double.MIN_VALUE){
			max=100;
		}
		
		if(min==Double.MAX_VALUE){
			min=-100;
		}
		maxminy= new double[]{min,max};
		
		
		esca=(int)a.getEsca().getValue();
		if(e.getSource() instanceof JSlider){
			JSlider source = (JSlider)e.getSource();
			fps = (int)source.getValue();
		}

		
	       // if(fps+esca>a.getMayor()) fps=fps-esca+1000;
	        if(fps<0)fps=0;
	        
	        a.getSl().setMaximum((a.getMayor()>4000)?(int)a.getMayor()-esca+1000:0);
	        
	        a.getChart().getXYPlot().getRangeAxis().setRange(maxminy[0]-100, maxminy[1]+100);
	        a.getChart().getXYPlot().getDomainAxis().setRange(fps, fps+esca);
	        
	        a.getSl().setValue((int) a.getChart().getXYPlot().getDomainAxis().getRange().getLowerBound());
	 
	}


	

}
