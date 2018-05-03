package es.uned.yauesc.geneticAlgorithm;

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
		
		population.substituteAllIndividual(evaluationFunction.evaluate(population.getAllIndividual()));
		finished = false;
	}

	@Override
	public void run() {
		int index = 0;
		while (!finished && index < generations ) {
			
			Collection<Individual> parents = parentSelector.selectParents(population.getAllIndividual(), population.getMinSize());
			
			Collection<Individual> recombinatedOffspring = recombinationOperator.recombine(parents);
			
			Collection<Individual> mutatedOffspring = mutationOperator.mutate(recombinatedOffspring);
			
			Collection<Individual> offspring = evaluationFunction.evaluate(mutatedOffspring);
			
			population.substituteAllIndividual(survivorSelector.getSurvivor(population, offspring));
			
			int comparedFitness = this.getSolution().getFitness().compareTo(optimalFitness);
			if (comparedFitness >= 0 ) {
				finished = true;
			} else {
				index++;
			}
		}
	}

	@Override
	public boolean isFinished() {
		return finished;
	}

	@Override
	public Individual getSolution() {
		return population.getBestIndividual();
	}

}
