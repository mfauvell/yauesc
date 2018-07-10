package es.uned.yauesc;

import java.awt.EventQueue;
import java.io.IOException;

import es.uned.yauesc.dataUned.DataUnedController;
import es.uned.yauesc.geneticAlgorithm.GeneticAlgorithmController;
import es.uned.yauesc.geneticAlgorithm.IllegalParameterValueCheckedException;
import es.uned.yauesc.gui.MainFrame;

public class App {
    
	private GeneticAlgorithmController geneticAlgorithmController;
	private DataUnedController dataUnedController;
	@SuppressWarnings("unused")
	private MainFrame view;
	
	public App() throws IOException, IllegalParameterValueCheckedException {
		geneticAlgorithmController = new GeneticAlgorithmController();
		dataUnedController = new DataUnedController();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					view = new MainFrame(dataUnedController, geneticAlgorithmController);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

    public static void main(String[] args) throws IOException, IllegalParameterValueCheckedException {
        new App();
    }

    
}
