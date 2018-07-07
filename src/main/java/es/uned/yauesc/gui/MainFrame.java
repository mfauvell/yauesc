package es.uned.yauesc.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import es.uned.yauesc.dataUned.UnedDataController;
import javax.swing.JTabbedPane;
import java.awt.GridLayout;

public class MainFrame {

	private JFrame frame;

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
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1050 , 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane);
		
		DataUnedGui dataUnedGui = new DataUnedGui(new UnedDataController());
		tabbedPane.addTab("Data UNED", null, dataUnedGui, null);
		
		GeneticAlgorithmConfigGui geneticAlgorithmConfigGui = new GeneticAlgorithmConfigGui();
		tabbedPane.addTab("Genetic algorithm configuration", null, geneticAlgorithmConfigGui, null);
		tabbedPane.setEnabledAt(1, true);
		
		GeneticAlgorithmExecutionGui geneticAlgorithmExecutionGui = new GeneticAlgorithmExecutionGui();
		tabbedPane.addTab("Genetic algorithm progress", null, geneticAlgorithmExecutionGui, null);
		
		ObtainResultGui obtainResultGui = new ObtainResultGui();
		tabbedPane.addTab("Export results", null, obtainResultGui, null);
		
	}

}
