package View;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
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

import Control.GraphController;
import Model.ECG;

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
public class GraficaECG extends JPanel {

	private static final long serialVersionUID = 1L;

	private double mayor = 0;
	private JSpinner esca;
	private JFreeChart chart;
	private JSlider sl;
	private XYSeriesCollection dataset;

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
	}

	/**
	 * Creacion de la parte visual integrando todos los elementos en un JPanel
	 */
	public void initUITEC() {
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
		for (int i = 0; i < ecg.getPuntos().size(); i++) {
			series.add(mili, ecg.getPuntos().get(i));
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

}