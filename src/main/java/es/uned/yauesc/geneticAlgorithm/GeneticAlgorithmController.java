package es.uned.yauesc.geneticAlgorithm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class GeneticAlgorithmController implements GeneticAlgorithmObserver {

	private GeneticAlgorithmFactory geneticAlgorithmFactory;
	
	private GeneticAlgorithm mainGeneticAlgorithm;
	private GeneticAlgorithmSingle firstGeneticAlgorithmSingle;
	private GeneticAlgorithmSingle secondGeneticAlgorithmSingle;
	private GeneticAlgorithmSingle thirdGeneticAlgorithmSingle;
	
	private boolean parallel;
	private int generations;
	
	private boolean finished;
	private Individual solution;
	
	private Collection<GeneticAlgorithmControllerObserver> observers;
	
	public GeneticAlgorithmController() {
		parallel = false;
		observers = new ArrayList<>();
	}
	
	public Individual getSolution() {
		return solution;
	}
	
	public boolean isParallel() {
		return parallel;
	}
	
	public void setGeneticAlgorithmFactory(int genotypeLong, int numberValuesGen, EvaluationFunction evaluationFunction, Fitness optimalFitness) {
		geneticAlgorithmFactory = new GeneticAlgorithmFactory(genotypeLong, numberValuesGen, evaluationFunction, optimalFitness);
	}
	
	public void setBasicOptions(int generations, boolean parallel) {
		this.generations = generations;
		this.parallel = parallel;
	}
	
	public void setFirstGeneticAlgorithmSingle(int[] populationOptions, Object[] parentSelectorOptions,
			Object[] recombinationOperatorOptions, Object[] mutationOperatorOptions, Object[] survivorSelectorOptions) throws IllegalParameterValueCheckedException {
		firstGeneticAlgorithmSingle = geneticAlgorithmFactory.getGeneticAlgorithmSingle(generations, populationOptions, parentSelectorOptions, recombinationOperatorOptions, mutationOperatorOptions, survivorSelectorOptions);
	}
	
	public void setSecondGeneticAlgorithmSingle(int[] populationOptions, Object[] parentSelectorOptions,
			Object[] recombinationOperatorOptions, Object[] mutationOperatorOptions, Object[] survivorSelectorOptions) throws IllegalParameterValueCheckedException {
		secondGeneticAlgorithmSingle = geneticAlgorithmFactory.getGeneticAlgorithmSingle(generations, populationOptions, parentSelectorOptions, recombinationOperatorOptions, mutationOperatorOptions, survivorSelectorOptions);
	}
	
	public void setThirdGeneticAlgorithmSingle(int[] populationOptions, Object[] parentSelectorOptions,
			Object[] recombinationOperatorOptions, Object[] mutationOperatorOptions, Object[] survivorSelectorOptions) throws IllegalParameterValueCheckedException {
		thirdGeneticAlgorithmSingle = geneticAlgorithmFactory.getGeneticAlgorithmSingle(generations, populationOptions, parentSelectorOptions, recombinationOperatorOptions, mutationOperatorOptions, survivorSelectorOptions);
	}
	
	public void setMainGeneticAlgorithm(int migrationGenerations, int numberMigration) {
		mainGeneticAlgorithm = geneticAlgorithmFactory.getGeneticAlgorithmParallel(generations, migrationGenerations, numberMigration, firstGeneticAlgorithmSingle, secondGeneticAlgorithmSingle, thirdGeneticAlgorithmSingle);
		mainGeneticAlgorithm.registerObserver(this);
	}
	
	public void setMainGeneticAlgorithm() {
		mainGeneticAlgorithm = firstGeneticAlgorithmSingle;
		mainGeneticAlgorithm.registerObserver(this);
	}
	
	public void registerObserverMainGeneticAlgorithm(GeneticAlgorithmObserver observer) {
		mainGeneticAlgorithm.registerObserver(observer);
	}
	
	public void registerObserverFirstGeneticAlgorithm(GeneticAlgorithmObserver observer) {
		firstGeneticAlgorithmSingle.registerObserver(observer);
	}
	
	public void registerObserverSecondGeneticAlgorithm(GeneticAlgorithmObserver observer) {
		secondGeneticAlgorithmSingle.registerObserver(observer);
	}
	
	public void registerObserverThirdGeneticAlgorithm(GeneticAlgorithmObserver observer) {
		thirdGeneticAlgorithmSingle.registerObserver(observer);
	}
	
	public void registerObserver(GeneticAlgorithmControllerObserver geneticAlgorithmControllerObserver) {
		if (!(observers.contains(geneticAlgorithmControllerObserver))) {
			observers.add(geneticAlgorithmControllerObserver);
		}
	}
	
	public void removeObserver(GeneticAlgorithmControllerObserver geneticAlgorithmControllerObserver) {
		observers.remove(geneticAlgorithmControllerObserver);	
	}

	public void notifyObservers() {
		observers.parallelStream().forEach(geneticAlgorithmControllerObserver -> geneticAlgorithmControllerObserver.updateGeneticAlgorithmControllerObserver(this));
	}

	@Override
	public void updateGeneticAlgorithmObserver(GeneticAlgorithm geneticAlgorithm) {
		solution = geneticAlgorithm.getSolution();
		finished = geneticAlgorithm.isFinished();
		if (finished) {
			notifyObservers();
		}
	}
	
	public void startExecution() {
		ExecutorService executor = Executors.newFixedThreadPool(1);
		executor.execute(mainGeneticAlgorithm);
		
		executor.shutdown();
		
		try {
			executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			System.out.println("Failed execution of algorithm");
		}
	}
	
	
}
