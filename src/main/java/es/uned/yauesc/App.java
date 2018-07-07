package es.uned.yauesc;

import java.io.IOException;

import es.uned.yauesc.dataUned.DataUnedController;
import es.uned.yauesc.geneticAlgorithm.GeneticAlgorithm;
import es.uned.yauesc.geneticAlgorithm.GeneticAlgorithmController;
import es.uned.yauesc.geneticAlgorithm.GeneticAlgorithmControllerObserver;
import es.uned.yauesc.geneticAlgorithm.GeneticAlgorithmObserver;
import es.uned.yauesc.geneticAlgorithm.IllegalParameterValueCheckedException;

public class App implements GeneticAlgorithmControllerObserver, GeneticAlgorithmObserver {
    
	private GeneticAlgorithmController geneticAlgorithmController;
	private DataUnedController unedDataController;
	
	public App() throws IOException, IllegalParameterValueCheckedException {
		geneticAlgorithmController = new GeneticAlgorithmController();
		unedDataController = new DataUnedController();
		runProgram();
	}

    public static void main(String[] args) throws IOException, IllegalParameterValueCheckedException {
        new App();
    }

    public void runProgram() throws IOException, IllegalParameterValueCheckedException {
    	unedDataController.setFileGradePath("./data/Grados.csv");
    	unedDataController.setFileCentroAsociadoPath("./data/centroAsociadoCapacidad.csv");
    	unedDataController.setFileExamTimePath("./data/ExamTime.csv");
    	unedDataController.setFileCoursePath("./data/AsignaturaGrados.csv");
    	unedDataController.setFileEnrolmentPath("./data/MatriculasCentroAsignatura.csv");
    	
    	unedDataController.setPercentagePresented(0.4);
    	unedDataController.setOptimalFitness(0, 0, 0);
    	unedDataController.parseData();
    	
    	geneticAlgorithmController.setGeneticAlgorithmFactory(unedDataController.getGenotypeSize(), unedDataController.getNumberValuesGen(), 
    			unedDataController.getEvaluationFunction(), unedDataController.getOptimalFitness());
    	geneticAlgorithmController.setBasicOptions(500, true);
    	
    	int[] populationOptions = {150};
    	int[] populationOptionsAge = {150, 50, 100};
    	Object[] parentSelectorOptionsOne = {"Ranking", 1.5};
    	Object[] recombinationOperatorOptionsOne = {"Uniform",0.8 };
    	Object[] mutationOperatorOptionsOne = {"Simple", 0.001};
    	Object[] survivorSelectorOptionsOne = {"SteadyState", 4};
    	
    	Object[] parentSelectorOptionsTwo = {"Tournament"};
    	Object[] recombinationOperatorOptionsTwo = {"Uniform",0.8 };
    	Object[] mutationOperatorOptionsTwo = {"Permutation", 0.1};
    	Object[] survivorSelectorOptionsTwo = {"SteadyState", 4};
    	
    	Object[] parentSelectorOptionsThree = {"Tournament"};
    	Object[] recombinationOperatorOptionsThree = {"Uniform",0.9 };
    	Object[] mutationOperatorOptionsThree = {"Permutation", 0.01};
    	Object[] survivorSelectorOptionsThree = {"RoundRobin", 5};
    	
    	
    	geneticAlgorithmController.setFirstGeneticAlgorithmSingle(populationOptionsAge, parentSelectorOptionsOne, recombinationOperatorOptionsOne, mutationOperatorOptionsOne, survivorSelectorOptionsOne);
    	geneticAlgorithmController.setSecondGeneticAlgorithmSingle(populationOptions, parentSelectorOptionsTwo, recombinationOperatorOptionsTwo, mutationOperatorOptionsTwo, survivorSelectorOptionsTwo);
    	geneticAlgorithmController.setThirdGeneticAlgorithmSingle(populationOptions, parentSelectorOptionsThree, recombinationOperatorOptionsThree, mutationOperatorOptionsThree, survivorSelectorOptionsThree);
    	geneticAlgorithmController.setMainGeneticAlgorithm(10, 10);
    	//geneticAlgorithmController.setMainGeneticAlgorithm();
    	
    	geneticAlgorithmController.registerObserver(this);
    	geneticAlgorithmController.registerObserverMainGeneticAlgorithm(this);
    	
    	System.out.println("Start execution Algorithm");
    	geneticAlgorithmController.startExecution();
    }
    
	@Override
	public void updateGeneticAlgorithmObserver(GeneticAlgorithm geneticAlgorithm) {
		System.out.println(geneticAlgorithm.getSolution().getFitness());
	}

	@Override
	public void updateGeneticAlgorithmControllerObserver(GeneticAlgorithmController geneticAlgorithmController) {
		System.out.println("Finished execution Algorithm");
		unedDataController.setUnedSchedule(geneticAlgorithmController.getSolution().getGenotype());
		System.out.println("Create Schedule CSV");
		unedDataController.createCSVAllSchedule("./data/solution.csv");
		System.out.println("Execution finished");
	}
}
