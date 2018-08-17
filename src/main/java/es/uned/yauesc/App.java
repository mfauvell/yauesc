package es.uned.yauesc;

import java.awt.EventQueue;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import es.uned.yauesc.dataUned.DataUnedController;
import es.uned.yauesc.geneticAlgorithm.GeneticAlgorithmController;
import es.uned.yauesc.gui.MainFrame;

/**
 * Clase inicial de la aplicación yauesc que crea los controladores principales.
 */
public class App {
    
	private GeneticAlgorithmController geneticAlgorithmController;
	private DataUnedController dataUnedController;
	@SuppressWarnings("unused")
	private MainFrame view;
	
	//General log
	private final static Logger ROOT_LOG = Logger.getLogger("es.uned.yauesc");
	
	//This class log
	private final static Logger LOGGER = Logger.getLogger(App.class.getName());
	
	/**
	 * Constructor que crea una nueva instancia de App
	 */
	public App() {
		setupLog();
		LOGGER.log(Level.INFO, "Aplication started");
		geneticAlgorithmController = new GeneticAlgorithmController();
		dataUnedController = new DataUnedController();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					view = new MainFrame(dataUnedController, geneticAlgorithmController);
				} catch (Exception e) {
					LOGGER.log(Level.SEVERE, "It is not possible create gui controller");
					LOGGER.log(Level.SEVERE, e.getStackTrace().toString());
					System.exit(1);
				}
			}
		});
	}

	/**
	 * Método estático que comienza la ejecución de la aplicación
	 * 
	 * @param args ninguno
	 */
    public static void main(String[] args) {
        new App();
    }

    private void setupLog() {
    	try {
			Handler fileHandler = new FileHandler("./log/yauesc.log");
			SimpleFormatter simpleFormatter = new SimpleFormatter();
			fileHandler.setFormatter(simpleFormatter);
			ROOT_LOG.addHandler(fileHandler);
			ROOT_LOG.setLevel(Level.INFO);
		} catch (SecurityException | IOException e) {
			System.out.println("It is not posible create log file");
			System.exit(1);
		}
    	
    }
}
