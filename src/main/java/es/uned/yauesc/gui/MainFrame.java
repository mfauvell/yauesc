package es.uned.yauesc.gui;

import javax.swing.JFrame;

import es.uned.yauesc.dataUned.DataUnedController;
import es.uned.yauesc.geneticAlgorithm.GeneticAlgorithmController;

import javax.swing.JTabbedPane;
import java.awt.GridLayout;

public class MainFrame {

	private DataUnedController dataUnedController;
	private GeneticAlgorithmController geneticAlgorithmController;
	
	private GeneticAlgorithmConfigGui geneticAlgorithmConfigGui;
	private GeneticAlgorithmExecutionGui geneticAlgorithmExecutionGui;
	private DataUnedGui dataUnedGui;
	private ObtainResultGui obtainResultGui;
	
	private JFrame frame;
	private JTabbedPane tabbedPane;


	/**
	 * Create the application.
	 */
	public MainFrame(DataUnedController dataUnedController, GeneticAlgorithmController geneticAlgorithmController) {
		this.dataUnedController = dataUnedController;
		this.geneticAlgorithmController = geneticAlgorithmController;
		
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

	public void setGeneticAlgorithmConfigTab() {
		// TODO Auto-generated method stub
		geneticAlgorithmController.setGeneticAlgorithmFactory(dataUnedController.getGenotypeSize(), dataUnedController.getNumberValuesGen(), 
				dataUnedController.getEvaluationFunction(),dataUnedController.getOptimalFitness());
		
		tabbedPane.setEnabledAt(1, true);
		tabbedPane.setSelectedIndex(1);
	}
	
	public void setGeneticAlgorithmExecutionTab() {
		//TODO
		tabbedPane.setEnabledAt(2, true);
		tabbedPane.setSelectedIndex(2);
	}
	
	public void setObtainResultsTab() {
		// TODO Auto-generated method stub
		dataUnedController.setUnedSchedule(geneticAlgorithmController.getSolution().getGenotype());
		obtainResultGui.preparePanel(dataUnedController.getCodeGrades(), dataUnedController.getCodeCourses());
		tabbedPane.setEnabledAt(3, true);
		tabbedPane.setSelectedIndex(3);
	}
	
	public void resetFromGeneticAlgorithmConfig() {
		//TODO
		disableGeneticAlgorithmExecutionTab();
		disableObtainResultTab();
		geneticAlgorithmExecutionGui.initialize();
		resetFromGeneticAlgorithmExecution();
	}
	
	public void resetFromLoadData() {
		//TODO
		disableGeneticAlgorithmConfigTab();
		geneticAlgorithmConfigGui.initialize();
		resetFromGeneticAlgorithmConfig();
	}
	
	public void resetFromGeneticAlgorithmExecution() {
		//TODO
		disableObtainResultTab();
		obtainResultGui.initialize();
	}
	
	public void disableExecutionButtons() {
		geneticAlgorithmConfigGui.disableSetConfig();
		dataUnedGui.disableLoadData();
	}
	
	public void enableExecutionButtons() {
		geneticAlgorithmConfigGui.enableSetConfig();
		dataUnedGui.enableLoadData();
	}
	
	private void disableGeneticAlgorithmConfigTab() {
		tabbedPane.setEnabledAt(1, false);
	}
	
	private void disableGeneticAlgorithmExecutionTab() {
		tabbedPane.setEnabledAt(2, false);
	}
	
	private void disableObtainResultTab() {
		tabbedPane.setEnabledAt(3, false);
	}
}
