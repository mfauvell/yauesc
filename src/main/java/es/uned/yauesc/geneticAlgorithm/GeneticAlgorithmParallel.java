package es.uned.yauesc.geneticAlgorithm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que encapsula la ejecución de un algoritmo genético compuesto
 */
public class GeneticAlgorithmParallel implements GeneticAlgorithm {
	
	private GeneticAlgorithmSingle firstGeneticAlgorithmSingle;
	private GeneticAlgorithmSingle secondGeneticAlgorithmSingle;
	private GeneticAlgorithmSingle thirdGeneticAlgorithmSingle;
	
	private int generations;
	private int migrationGenerations;
	private int numberMigration;
	private int times;
	
	private Individual solution;
	
	private boolean finished;
	
	private Collection<GeneticAlgorithmObserver> observers;
	
	private final static Logger LOGGER = Logger.getLogger(GeneticAlgorithmParallel.class.getName());

	/**
	 * Consgructor por defecto un algoritmo genético compuesto
	 * 
	 * @param firstGeneticAlgorithmSingle	un algoritmo genético simple
	 * @param secondGeneticAlgorithmSingle	un algoritmo genético simple
	 * @param thirdGeneticAlgorithmSingle	un algoritmo genético simple
	 * @param generations					generaciones a realizar
	 * @param migrationGenerations			frecuencia para realizar migraciones
	 * @param numberMigration				numéro de individuos a intercambiar
	 */
	public GeneticAlgorithmParallel(GeneticAlgorithmSingle firstGeneticAlgorithmSingle,
			GeneticAlgorithmSingle secondGeneticAlgorithmSingle, GeneticAlgorithmSingle thirdGeneticAlgorithmSingle,
			int generations, int migrationGenerations, int numberMigration) {
		this.firstGeneticAlgorithmSingle = firstGeneticAlgorithmSingle;
		this.secondGeneticAlgorithmSingle = secondGeneticAlgorithmSingle;
		this.thirdGeneticAlgorithmSingle = thirdGeneticAlgorithmSingle;
		
		this.generations = generations;
		this.migrationGenerations = migrationGenerations;
		if (migrationGenerations == 0)
			times = 1;
		else
			times = (int) Math.ceil((float) generations /(float) migrationGenerations);
		this.numberMigration = numberMigration;
		
		observers = new ArrayList<GeneticAlgorithmObserver>();
		finished = false;
		setSolution();
	}

	@Override
	public void run() {
		int index = 0;
		if (times == 0)
			finished = true;
		else
			finished = false;
		while (!finished) {
			LOGGER.log(Level.INFO, "Parallel iteration: " + index);
			if (index == times -1) {
				int lastGenerations = generations - (index * migrationGenerations);
				firstGeneticAlgorithmSingle.setGenerations(lastGenerations);
				secondGeneticAlgorithmSingle.setGenerations(lastGenerations);
				thirdGeneticAlgorithmSingle.setGenerations(lastGenerations);
			} else {
				firstGeneticAlgorithmSingle.setGenerations(migrationGenerations);
				secondGeneticAlgorithmSingle.setGenerations(migrationGenerations);
				thirdGeneticAlgorithmSingle.setGenerations(migrationGenerations);
			}
			
			ExecutorService executor = Executors.newFixedThreadPool(3);
			executor.execute(firstGeneticAlgorithmSingle);
			executor.execute(secondGeneticAlgorithmSingle);
			executor.execute(thirdGeneticAlgorithmSingle);
			
			executor.shutdown();
			
			try {
				executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				System.out.println("Failed execution of algorithm");
				LOGGER.log(Level.SEVERE, "Thread interrupted");
			}
			
			setSolution();
			
			Collection<Individual> firstBest = firstGeneticAlgorithmSingle.getBestIndividual(numberMigration);
			Collection<Individual> secondBest = secondGeneticAlgorithmSingle.getBestIndividual(numberMigration);
			Collection<Individual> thirdBest = thirdGeneticAlgorithmSingle.getBestIndividual(numberMigration);
			
			LOGGER.log(Level.INFO, "First genetic algorithm migration: " + firstBest.toString());
			LOGGER.log(Level.INFO, "Second genetic algorithm migration: " + secondBest.toString());
			LOGGER.log(Level.INFO, "Third genetic algorithm migration: " + thirdBest.toString());
			
			firstGeneticAlgorithmSingle.substituteWorstIndividual(thirdBest);
			secondGeneticAlgorithmSingle.substituteWorstIndividual(firstBest);
			thirdGeneticAlgorithmSingle.substituteWorstIndividual(secondBest);
			
			if (firstGeneticAlgorithmSingle.foundOptimal() || secondGeneticAlgorithmSingle.foundOptimal() || thirdGeneticAlgorithmSingle.foundOptimal()) {
				finished = true;
			} else {
				index++;
			}
			
			if (index >= times)
				finished = true;
			
			notifyObservers();
		}
		LOGGER.log(Level.INFO, "Finished parallel execution");
	}
	
	@Override
	public void stop() {
		finished=true;
	}

	@Override
	public boolean isFinished() {
		return finished;
	}

	@Override
	public Individual getSolution() {
		return solution;
	}
	
	@Override
	public void removeSolution() {
		solution = null;
	}

	@Override
	public void registerObserver(GeneticAlgorithmObserver geneticAlgorithmObserver) {
		if (!(observers.contains(geneticAlgorithmObserver))) {
			observers.add(geneticAlgorithmObserver);
		}
	}

	@Override
	public void notifyObservers() {
		observers.parallelStream().forEach(geneticAlgorithmObserver -> geneticAlgorithmObserver.updateGeneticAlgorithmObserver(this));
	}

	@Override
	public void removeObserver(GeneticAlgorithmObserver geneticAlgorithmObserver) {
		observers.remove(geneticAlgorithmObserver);	
	}

	private void setSolution() {
		Individual firstSolution = firstGeneticAlgorithmSingle.getSolution(); 
		Individual secondSolution = secondGeneticAlgorithmSingle.getSolution(); 
		Individual thirdSolution = thirdGeneticAlgorithmSingle.getSolution(); 
		Individual candidateSolution = firstSolution;
		if (secondSolution.compareTo(candidateSolution)>0) {
			candidateSolution = secondSolution;
		} else if (thirdSolution.compareTo(candidateSolution)>0) {
			candidateSolution = thirdSolution;
		}
		if (solution == null || candidateSolution.compareTo(solution)>0) {
			solution = candidateSolution;
			LOGGER.log(Level.INFO, "Set new solution: " + solution);
		}
	}

}
