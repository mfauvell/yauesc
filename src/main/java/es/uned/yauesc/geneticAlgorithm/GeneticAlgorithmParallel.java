package es.uned.yauesc.geneticAlgorithm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class GeneticAlgorithmParallel implements GeneticAlgorithm {
	
	private GeneticAlgorithmSingle firstGeneticAlgorithmSingle;
	private GeneticAlgorithmSingle secondGeneticAlgorithmSingle;
	private GeneticAlgorithmSingle thirdGeneticAlgorithmSingle;
	
	private int generations;
	private int migration;
	private int numberMigration;
	private int times;
	
	private Individual solution;
	
	private boolean finished;
	
	private Collection<GeneticAlgorithmObserver> observers;

	public GeneticAlgorithmParallel(GeneticAlgorithmSingle firstGeneticAlgorithmSingle,
			GeneticAlgorithmSingle secondGeneticAlgorithmSingle, GeneticAlgorithmSingle thirdGeneticAlgorithmSingle,
			int generations, int migration, int numberMigration) {
		this.firstGeneticAlgorithmSingle = firstGeneticAlgorithmSingle;
		this.secondGeneticAlgorithmSingle = secondGeneticAlgorithmSingle;
		this.thirdGeneticAlgorithmSingle = thirdGeneticAlgorithmSingle;
		
		this.generations = generations;
		this.migration = migration;
		if (migration == 0)
			times = 1;
		else
			times = (int) Math.ceil((float) generations /(float) migration);
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
			if (index == times -1) {
				int lastGenerations = generations - (index * migration);
				firstGeneticAlgorithmSingle.setGenerations(lastGenerations);
				secondGeneticAlgorithmSingle.setGenerations(lastGenerations);
				thirdGeneticAlgorithmSingle.setGenerations(lastGenerations);
			} else {
				firstGeneticAlgorithmSingle.setGenerations(migration);
				secondGeneticAlgorithmSingle.setGenerations(migration);
				thirdGeneticAlgorithmSingle.setGenerations(migration);
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
			}
			
			setSolution();
			
			Collection<Individual> firstBest = firstGeneticAlgorithmSingle.getBestIndividual(numberMigration);
			Collection<Individual> secondBest = secondGeneticAlgorithmSingle.getBestIndividual(numberMigration);
			Collection<Individual> thirdBest = thirdGeneticAlgorithmSingle.getBestIndividual(numberMigration);
			
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
		}
	}
}
