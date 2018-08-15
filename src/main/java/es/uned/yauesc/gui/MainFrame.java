package es.uned.yauesc.gui;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import es.uned.yauesc.dataUned.DataUnedController;
import es.uned.yauesc.dataUned.FitnessUned;
import es.uned.yauesc.geneticAlgorithm.GeneticAlgorithmController;

import javax.swing.JTabbedPane;
import java.awt.GridLayout;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que actua de controlador y JFrame principal de la aplicación.
 */
public class MainFrame {

	private DataUnedController dataUnedController;
	private GeneticAlgorithmController geneticAlgorithmController;
	
	private GeneticAlgorithmConfigGui geneticAlgorithmConfigGui;
	private GeneticAlgorithmExecutionGui geneticAlgorithmExecutionGui;
	private DataUnedGui dataUnedGui;
	private ObtainResultGui obtainResultGui;
	
	private JFrame frame;
	private JTabbedPane tabbedPane;

	private final static Logger LOGGER = Logger.getLogger(MainFrame.class.getName());

	/**
	 * Crea el JFrame principal de la aplicación
	 */
	public MainFrame(DataUnedController dataUnedController, GeneticAlgorithmController geneticAlgorithmController) {
		this.dataUnedController = dataUnedController;
		this.geneticAlgorithmController = geneticAlgorithmController;
		LOGGER.log(Level.INFO, "Create main frame");
		initialize();
	}

	/**
	 * Inicializa el contenido del frame y crea los diferentes paneles.
	 */
	private void initialize() {
		frame = new JFrame();
		ImageIcon icon = new ImageIcon("./images/logo.png");
		frame.setIconImage(icon.getImage());
		frame.setTitle("Yet Another UNED Exam Schedule Creator");
		frame.setBounds(100, 100, 1050 , 705);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		frame.setVisible(true);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane);
		
		dataUnedGui = new DataUnedGui(dataUnedController, this);
		tabbedPane.addTab("Data UNED", null, dataUnedGui, null);
		
		geneticAlgorithmConfigGui = new GeneticAlgorithmConfigGui(geneticAlgorithmController, this);
		tabbedPane.addTab("Genetic algorithm configuration", null, geneticAlgorithmConfigGui, null);
		
		geneticAlgorithmExecutionGui = new GeneticAlgorithmExecutionGui(geneticAlgorithmController, this);
		tabbedPane.addTab("Genetic algorithm progress", null, geneticAlgorithmExecutionGui, null);
		
		obtainResultGui = new ObtainResultGui(dataUnedController);
		tabbedPane.addTab("Export results", null, obtainResultGui, null);
		
		disableGeneticAlgorithmConfigTab();
		disableGeneticAlgorithmExecutionTab();
		disableObtainResultTab();
	}

	/**
	 * Activa el panel de configuración del algoritmo genético
	 */
	public void setGeneticAlgorithmConfigTab() {
		geneticAlgorithmController.setGeneticAlgorithmFactory(dataUnedController.getGenotypeSize(), dataUnedController.getNumberValuesGen(), 
				dataUnedController.getEvaluationFunction(),dataUnedController.getOptimalFitness());
		LOGGER.log(Level.INFO, "Activate genetic algorith config gui");
		tabbedPane.setEnabledAt(1, true);
		tabbedPane.setSelectedIndex(1);
	}
	
	/**
	 * Activa el panel de ejecución del algoritmo genético
	 */
	public void setGeneticAlgorithmExecutionTab() {
		LOGGER.log(Level.INFO, "Activate genetic algorith execution gui");
		tabbedPane.setEnabledAt(2, true);
		tabbedPane.setSelectedIndex(2);
	}
	
	/**
	 * Activa y prepara el panel de obtención de resultados
	 */
	public void setObtainResultsTab() {
		LOGGER.log(Level.INFO, "Activate obtain result gui");
		dataUnedController.setUnedSchedule(geneticAlgorithmController.getSolution().getGenotype(), (FitnessUned) geneticAlgorithmController.getSolution().getFitness());
		obtainResultGui.preparePanel(dataUnedController.getCodeGrades(), dataUnedController.getCodeCourses());
		tabbedPane.setEnabledAt(3, true);
		tabbedPane.setSelectedIndex(3);
	}
	
	/**
	 * Resetea las pestañas desde la pestaña de configuración
	 */
	public void resetFromGeneticAlgorithmConfig() {
		LOGGER.log(Level.INFO, "Reset from genetic algorithm config gui");
		disableGeneticAlgorithmExecutionTab();
		disableObtainResultTab();
		geneticAlgorithmExecutionGui.initialize();
		resetFromGeneticAlgorithmExecution();
	}
	
	/**
	 * Resetea desde la pestaña de carga de datos
	 */
	public void resetFromLoadData() {
		LOGGER.log(Level.INFO, "Reset from data uned gui");
		disableGeneticAlgorithmConfigTab();
		geneticAlgorithmConfigGui.initialize();
		resetFromGeneticAlgorithmConfig();
	}
	
	/**
	 * Resetea desde la pestaña de ejecución del algoritmo genético
	 */
	public void resetFromGeneticAlgorithmExecution() {
		LOGGER.log(Level.INFO, "Reset from genetic algorithm execution gui");
		disableObtainResultTab();
		obtainResultGui.initialize();
	}
	
	/**
	 * Descativa los botones que inician ejecuciones
	 */
	public void disableExecutionButtons() {
		LOGGER.log(Level.INFO, "Disable execution buttons");
		geneticAlgorithmConfigGui.disableSetConfig();
		dataUnedGui.disableLoadData();
	}
	
	/**
	 * Activa los botones de ejecución
	 */
	public void enableExecutionButtons() {
		LOGGER.log(Level.INFO, "Activate execution buttons");
		geneticAlgorithmConfigGui.enableSetConfig();
		dataUnedGui.enableLoadData();
	}
	
	private void disableGeneticAlgorithmConfigTab() {
		LOGGER.log(Level.INFO, "Disable genetic algorithm config tab");
		tabbedPane.setEnabledAt(1, false);
	}
	
	private void disableGeneticAlgorithmExecutionTab() {
		LOGGER.log(Level.INFO, "Disable genetic algorithm execution tab");
		tabbedPane.setEnabledAt(2, false);
	}
	
	private void disableObtainResultTab() {
		LOGGER.log(Level.INFO, "Disable obtain result tab");
		tabbedPane.setEnabledAt(3, false);
	}
}
