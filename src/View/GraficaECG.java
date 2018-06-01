package View;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;

import Control.ControladorFicha;
import Control.GraphController;
import Model.ECG;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

/**
 * Panel que almacena la grafica de ecg con sus adicionales para poder
 * observarlo en su totalidad
 * 
 * @author HeartLight
 * 
 * @version Final
 * 
 * @see JSpinner
 * @see JFreeChart
 * @see JSlider
 * @see XYSeriesCollection
 * @see XYPlot
 *
 */
public class GraficaECG extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;
	private PanamaHitek_Arduino ino = new PanamaHitek_Arduino();
	private double mayor = 0;
	private JSpinner esca;
	private int num=0;
	private JFreeChart chart;
	private JSlider sl;
	private XYSeriesCollection dataset;
	private JButton rec;
	private boolean stop=false;
	private boolean pause=false;
	private boolean running=false;
	private boolean ecgready=false;
	private ControladorFicha controlficha;
	
	public boolean isPause() {
		return pause;
	}

	public void setPause(boolean pause) {
		this.pause = pause;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	private JButton butstop;

	/**
	 * Setter del mayor punto en las x de la grafica
	 * 
	 * @param mayor
	 *            X mas lejana del 0
	 */
	public void setMayor(double mayor) {
		this.mayor = mayor;
	}

	/**
	 * Getter del dataset de la grafica donde se guardan todas las series de puntos
	 * 
	 * @return dataset XYSeriesCollection
	 */
	public XYSeriesCollection getDataset() {
		return dataset;
	}

	/**
	 * Getter del Spinner en el que se define la escala de la X que se muestra
	 * 
	 * @return JSpinner
	 */
	public JSpinner getEsca() {
		return esca;
	}

	/**
	 * Getter del mayor valor en la X
	 * 
	 * @return mayor valor X
	 */
	public double getMayor() {
		return mayor;
	}

	/**
	 * Getter de la Grafica 
	 * @return JFreeChart
	 */
	public JFreeChart getChart() {
		return chart;
	}

	/**
	 * Constructor que inicializa todos los objetos necesarios para la grafica
	 */
	public GraficaECG() {
		dataset = new XYSeriesCollection();
		chart = ChartFactory.createXYLineChart("ECG", "msec", "mV", dataset, PlotOrientation.VERTICAL, true, true,
				false);
		SpinnerNumberModel spin = new SpinnerNumberModel();
		spin.setStepSize(100);
		spin.setMinimum(100);
		spin.setMaximum(20000);
		esca = new JSpinner(spin);
		sl = new JSlider(JSlider.HORIZONTAL);
		butstop=new JButton();
		butstop.setContentAreaFilled(false);
		butstop.setOpaque(false);
		butstop.setBorderPainted(false);
		butstop.setEnabled(false);
		rec=new JButton();
		rec.setActionCommand(GraphController.RUN);
		rec.setContentAreaFilled(false);
		rec.setOpaque(false);
		rec.setBorderPainted(false);

		butstop.setActionCommand(GraphController.STOP);
	}
	
	public void addControllerTec(ControladorFicha controlficha) {

		this.controlficha=controlficha;
	}
	public void initUITEC() {
		ecgready=false;
		JPanel aux = new JPanel();
		createChart();

		aux.setLayout(new FlowLayout());
		setLayout(new BorderLayout());

		((JSpinner.DefaultEditor) esca.getEditor()).getTextField().setEditable(false);

		esca.setValue(5000);

		sl.setValue(0);
		sl.setMinimum(0);
		sl.setMaximum((mayor > 4000) ? (int) mayor - 4000 : 0);

		ChartPanel chartPanel = new ChartPanel(chart, false, false, false, true, true);

		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

		chart.getXYPlot().getDomainAxis().setRange(0, 5000);
		chartPanel.setBackground(Color.white);
		add(chartPanel, BorderLayout.CENTER);
		aux.add(sl);
		aux.add(esca);
		aux.add(new JLabel("msec"));
		add(aux, BorderLayout.SOUTH);
	}
	public boolean isStop() {
		return stop;
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}

	/**
	 * Creacion de la parte visual integrando todos los elementos en un JPanel
	 */
	public void initUIMED() {
		ecgready=true;
		JPanel aux = new JPanel();
		createChart();

		aux.setLayout(new FlowLayout());
		setLayout(new BorderLayout());

		((JSpinner.DefaultEditor) esca.getEditor()).getTextField().setEditable(false);

		esca.setValue(5000);

		rec.setIcon(new ImageIcon("Resource/Imagenes/playpause.png"));
		butstop.setIcon(new ImageIcon("Resource/Imagenes/stop.png"));
		sl.setValue(0);
		sl.setMinimum(0);
		sl.setMaximum((mayor > 4000) ? (int) mayor - 4000 : 0);

		ChartPanel chartPanel = new ChartPanel(chart, false, false, false, true, true);

		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

		chart.getXYPlot().getDomainAxis().setRange(0, 5000);
		chartPanel.setBackground(Color.white);
		add(chartPanel, BorderLayout.CENTER);
		

		aux.add(sl);
		aux.add(esca);
		aux.add(new JLabel("msec"));
		aux.add(rec);
		aux.add(butstop);
		add(aux, BorderLayout.SOUTH);
	}
	/**
	 * Creacion de una grafica visualmente mas sencilla para miniaturas
	 */
	public void initUISimple() {
		createSimpleChart();

		setLayout(new BorderLayout());

		ChartPanel chartPanel = new ChartPanel(chart, false, false, false, true, true);

		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

		chart.getXYPlot().getDomainAxis().setRange(0, 10000);
		chartPanel.setOpaque(false);
		add(chartPanel, BorderLayout.CENTER);
	}
/**
 * Asignacion de controladores a los elementos con los que se interactua en la grafica
 * @param control GraphController que modifica la visualizacion de la grafica
 */
	public void addController(GraphController control) {
		esca.addChangeListener(control);
		sl.addChangeListener(control);
		rec.addActionListener(control);
		butstop.addActionListener(control);
	}
/**
 * Getter del slider en el cual se puede seleccionar la escala
 * @return JSlider para escalar la X de la grafica
 */
	public JSlider getSl() {
		return sl;
	}
/**
 * Agregar un ecg a la grafica con su serie de puntos
 * @param ecg ECG que se va a agregar a la grafica
 */
	public void addGraphic(ECG ecg) {

		XYSeries series = new XYSeries(ecg.getNombre());
		double mili = 0;
		String[] aux=ecg.getPuntos().split(";");
		
		for (int i = 0; i < aux.length; i++) {
			System.out.println(ecg.getId()+" "+aux[i]);
			series.add(mili, Double.parseDouble(aux[i]));
			mili += 1000 / ecg.getPuntosporsec();
		}

		if (mili > mayor) {
			mayor = mili;
		}

		dataset.addSeries(series);
	}
/**
 * Creacion de la grafica cruda en la que podemos definir colores y se agrega el dataset, sin adicionales
 */
	public void createChart() {

		chart = ChartFactory.createXYLineChart("ECG", "msec", "mV", dataset, PlotOrientation.VERTICAL, true, true,
				false);

		XYPlot plot = chart.getXYPlot();

		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		renderer.setBaseShapesVisible(false);
		renderer.setSeriesPaint(0, Color.RED);
		renderer.setSeriesStroke(0, new BasicStroke(2.0f));

		plot.setRenderer(renderer);
		plot.setBackgroundPaint(Color.white);

		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.BLACK);

		plot.setDomainGridlinesVisible(true);
		plot.setDomainGridlinePaint(Color.BLACK);

		chart.getLegend().setFrame(BlockBorder.NONE);

		chart.setTitle(new TextTitle("ECG", new Font("Serif", java.awt.Font.BOLD, 18)));
	}

	/**
	 * Creacion de la grafica cruda sin adicionales, evitando agregarle las lineas de ejes 
	 * para que sea lo mas sencilla posible para miniaturas
	 */
	public void createSimpleChart() {

		chart = ChartFactory.createXYLineChart("", "", "", dataset, PlotOrientation.VERTICAL, false, false, false);

		XYPlot plot = chart.getXYPlot();

		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		renderer.setBaseShapesVisible(false);
		renderer.setSeriesPaint(0, Color.RED);
		renderer.setSeriesStroke(0, new BasicStroke(2.0f));

		plot.setRenderer(renderer);
		plot.setBackgroundPaint(Color.white);
		plot.setOutlineVisible(false);
		plot.getRangeAxis().setVisible(false);
		plot.getDomainAxis().setVisible(false);
	}
/**
 * Limpiador de la grafica, eliminando todos los puntos y volviendo a como es por defecto
 */
	public void cleanGraph() {
		dataset.removeAllSeries();
		mayor = 5000;
	}
/**
 * Setter del dataset que a su vez selecciona la mayor X
 * @param dataset Nuevo Dataset
 */
	public void setDataset(XYSeriesCollection dataset) {
		for (int i = 0; i < dataset.getSeriesCount(); i++) {
			if (mayor < dataset.getSeries(i).getMaxX()) {
				mayor = dataset.getSeries(i).getMaxX();
			}
		}
		this.dataset = dataset;
	}

	@Override
	public void run() {
		if (ecgready) {
			//SIMULACION DE GRAFICO ANIMADO YA EXISTENTE, EN LA VENTANA MEDICO
			if (dataset.getSeriesCount() > 0) {
				butstop.setEnabled(true);
				sl.setEnabled(false);
				try {
					XYSeriesCollection aux = (XYSeriesCollection) dataset.clone();
					dataset.removeAllSeries();
					dataset.addSeries(new XYSeries(aux.getSeriesKey(0)));

					int j = 0;
					while (j < aux.getSeries(0).getItemCount() && !stop && this.isDisplayable()) {
						System.out.println("HILO ECG");
						int i = 0;
						while (pause && !stop && this.isDisplayable()) {
							System.out.println("PAUSA");
							if (i == 0) {
								sl.setEnabled(true);
								i++;
							}
							try {
								Thread.sleep(10);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						sl.setEnabled(false);
						dataset.getSeries(0).add(aux.getSeries(0).getX(j), aux.getSeries(0).getY(j));
						if (aux.getSeries(0).getX(j).intValue() > (int) esca.getValue() / 2) {
							sl.setValue(aux.getSeries(0).getX(j).intValue() - ((int) esca.getValue() / 2));
							chart.getXYPlot().getDomainAxis().setRange(
									aux.getSeries(0).getX(j).intValue() - ((int) esca.getValue() / 2),
									aux.getSeries(0).getX(j).intValue() + ((int) esca.getValue() / 2));
						}
						if (stop == false) {
							try {
								long time = ((long) (aux.getSeries(0).getMaxX() / aux.getSeries(0).getItemCount()));
								Thread.sleep(time);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						j++;
					}
					if (stop) {
						dataset.removeAllSeries();
						dataset.addSeries(aux.getSeries(0));
					}
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				running = false;
				pause = false;
				stop = false;
				butstop.setEnabled(false);
				sl.setEnabled(true);
			}
		} else {
			//LECTURA DEL TECNICO CONCURRENTE POSTERIORMENTE
			num=0;
			cleanGraph();
			mayor=Integer.parseInt(esca.getValue().toString());
			try {
				if(dataset.getSeries().isEmpty()) {
					dataset.addSeries(new XYSeries("ECG"));
				} 
				ino.arduinoRXTX(ino.getSerialPorts().get(0), 57600, new SerialPortEventListener() {
					public void serialEvent(SerialPortEvent arg0) {
						 try {
							  if(controlficha.getEcg()==null) {
								  controlficha.setEcg(new ECG(60,"","0"));
							  }
							// Se imprime el mensaje recibido en la consola
							if (ino.isMessageAvailable()) {
								try {
								Double aux = Double.parseDouble(ino.printMessage());
										controlficha.getEcg().setPuntos(controlficha.getEcg().getPuntos() + ";" + aux);
										addPunto(aux, num);
										if (num > Integer.parseInt(esca.getValue().toString()) / 2) {
											mayor = num;
											sl.setMaximum((getMayor()>4000)?(int)getMayor()-(int)esca.getValue()/2:0);
											sl.setValue(num);
										}
										
										num += 1000 /controlficha.getEcg().getPuntosporsec();
								
							}catch(NumberFormatException exc) {
								
							}
							}
						} catch (SerialPortException | ArduinoException ex) {

						}
					}
					
				});
				try {
					ino.sendData("0");
				} catch (SerialPortException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} catch (ArduinoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch(IndexOutOfBoundsException e) {
				JOptionPane.showMessageDialog(controlficha.getVt(), "Ha habido un problema conectando con el sensor", "Error", JOptionPane.WARNING_MESSAGE);
				controlficha.getVt().getFicha().getBtnStop().setEnabled(false);
				controlficha.getVt().getFicha().getBtnEnivar().setEnabled(true);
			}
		}
	}
	
	public void addPunto(double d,int x) {
		if(dataset.getSeriesCount()>0) {
		XYSeries aux=(XYSeries) dataset.getSeries().get(dataset.getSeriesCount()-1);
		aux.add(x, d);
		}
	}



	public PanamaHitek_Arduino getIno() {
		return ino;
	}
}