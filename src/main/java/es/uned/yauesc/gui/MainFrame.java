package es.uned.yauesc.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import es.uned.yauesc.dataUned.DataUnedController;
import es.uned.yauesc.geneticAlgorithm.GeneticAlgorithmController;

import javax.swing.JTabbedPane;
import java.awt.GridLayout;

public class MainFrame {

	private DataUnedController dataUnedController;
	private GeneticAlgorithmController geneticAlgorithmController;
	
	private JFrame frame;
	private JTabbedPane tabbedPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		dataUnedController = new DataUnedController();
		geneticAlgorithmController = new GeneticAlgorithmController();
		
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Yet Another UNED Exam Schedule Creator");
		frame.setBounds(100, 100, 1050 , 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane);
		
		DataUnedGui dataUnedGui = new DataUnedGui(dataUnedController, this);
		tabbedPane.addTab("Data UNED", null, dataUnedGui, null);
		
		GeneticAlgorithmConfigGui geneticAlgorithmConfigGui = new GeneticAlgorithmConfigGui();
		tabbedPane.addTab("Genetic algorithm configuration", null, geneticAlgorithmConfigGui, null);
		
		GeneticAlgorithmExecutionGui geneticAlgorithmExecutionGui = new GeneticAlgorithmExecutionGui();
		tabbedPane.addTab("Genetic algorithm progress", null, geneticAlgorithmExecutionGui, null);
		
		ObtainResultGui obtainResultGui = new ObtainResultGui();
		tabbedPane.addTab("Export results", null, obtainResultGui, null);
		
		disableGeneticAlgorithmConfigTab();
		tabbedPane.setEnabledAt(2, false);
		tabbedPane.setEnabledAt(3, false);
	}

	public void setGeneticAlgorithmConfigTab() {
		// TODO Auto-generated method stub
		geneticAlgorithmController.setGeneticAlgorithmFactory(dataUnedController.getGenotypeSize(), dataUnedController.getNumberValuesGen(), 
				dataUnedController.getEvaluationFunction(),dataUnedController.getOptimalFitness());
		
		tabbedPane.setEnabledAt(1, true);
		tabbedPane.setSelectedIndex(1);
	}
	
	public void disableGeneticAlgorithmConfigTab() {
		tabbedPane.setEnabledAt(1, false);
	}

}
