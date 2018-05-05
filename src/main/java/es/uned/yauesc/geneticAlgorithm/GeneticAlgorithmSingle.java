package es.uned.yauesc.geneticAlgorithm;

import java.util.ArrayList;
import java.util.Collection;

public class GeneticAlgorithmSingle implements GeneticAlgorithm {
	
	private Population population;
	private EvaluationFunction evaluationFunction;
	private ParentSelector parentSelector;
	private RecombinationOperator recombinationOperator;
	private MutationOperator mutationOperator;
	private SurvivorSelector survivorSelector;
	private int generations;
	private Fitness optimalFitness;
	private boolean finished;
	private boolean foundOptimal;
	private Individual solution;
	
	private Collection<GeneticAlgorithmObserver> observers;

	public GeneticAlgorithmSingle(Population population, EvaluationFunction evaluationFunction,
			ParentSelector parentSelector, RecombinationOperator recombinationOperator,
			MutationOperator mutationOperator, SurvivorSelector survivorSelector, int generations, Fitness optimalFitness) {
		this.population = population;
		this.evaluationFunction = evaluationFunction;
		this.parentSelector = parentSelector;
		this.recombinationOperator = recombinationOperator;
		this.mutationOperator = mutationOperator;
		this.survivorSelector = survivorSelector;
		this.generations = generations;
		this.optimalFitness = optimalFitness;
		
		observers = new ArrayList<GeneticAlgorithmObserver>();
		
		population.substituteAllIndividual(evaluationFunction.evaluate(population.getAllIndividual()));
		
		solution = population.getBestIndividual();
		
		finished = false;
		foundOptimal = false;
	}

	@Override
	public Individual getSolution() {
		return solution;
	}

	public Collection<Individual> getBestIndividual(int number) {
		return population.getBestIndividual(number);
	}
	
	public void setGenerations(int number) {
		generations = number;	
	}
	
	@Override
	public void run() {
		int index = 0;
		if (generations == 0)
			finished = true;
		else
			finished = false;
		while (!finished) {
			
			Collection<Individual> parents = parentSelector.selectParents(population.getAllIndividual(), population.getMinSize());
			
			Collection<Individual> recombinatedOffspring = recombinationOperator.recombine(parents);
			
			Collection<Individual> mutatedOffspring = mutationOperator.mutate(recombinatedOffspring);
			
			Collection<Individual> offspring = evaluationFunction.evaluate(mutatedOffspring);
			
			population.substituteAllIndividual(survivorSelector.getSurvivor(population, offspring));
			
			Individual bestOfGeneration = population.getBestIndividual();
			int compareSolution = bestOfGeneration.compareTo(solution);
			if (compareSolution > 0) {
				solution = bestOfGeneration;
			}
			
			int comparedFitness = solution.getFitness().compareTo(optimalFitness);
			if (comparedFitness >= 0 ) {
				foundOptimal = true;
				finished = true;
			} else {
				index++;
			}
			
			if (index >= generations)
				finished = true;
			
			notifyObservers();
		}
	}
	
	@Override
	public boolean isFinished() {
		return finished;
	}

	public boolean foundOptimal() {
		return foundOptimal;
	}
	
	public void substituteWorstIndividual(Collection<Individual> newIndividual) {
		population.substituteWorstIndividual(newIndividual);
	}

	@Override
	public void registerObserver(GeneticAlgorithmObserver geneticAlgorithmObserver) {
		if (!(observers.contains(geneticAlgorithmObserver))) {
			observers.add(geneticAlgorithmObserver);
		}
	}
	
	@Override
	public void removeObserver(GeneticAlgorithmObserver geneticAlgorithmObserver) {
		observers.remove(geneticAlgorithmObserver);	
	}

	@Override
	public void notifyObservers() {
		observers.parallelStream().forEach(geneticAlgorithmObserver -> geneticAlgorithmObserver.updateGeneticAlgorithmObserver(this));
	}

}
