package es.uned.yauesc.geneticAlgorithm;

import java.util.List;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class GeneticAlgorithmFactory {

	private GeneticAlgorithmUtils geneticAlgorithmUtils;
	private GeneticAlgorithmConfig geneticAlgorithmConfig;
	private EvaluationFunction evaluationFunction;
	private Fitness optimalFitness;
	
	public GeneticAlgorithmFactory(int genotypeLong, int numberValuesGen, EvaluationFunction evaluationFunction, Fitness optimalFitness ) {
		geneticAlgorithmConfig = new GeneticAlgorithmConfig(genotypeLong, numberValuesGen);
		geneticAlgorithmUtils = new GeneticAlgorithmUtils(geneticAlgorithmConfig);
		this.evaluationFunction = evaluationFunction;
		this.optimalFitness = optimalFitness;
	}
	
	public GeneticAlgorithm getGeneticAlgorithmParallel(int generations, int migrationGenerations, int numberMigration,
			GeneticAlgorithmSingle firstGeneticAlgorithmSingle, GeneticAlgorithmSingle secondGeneticAlgorithmSingle, GeneticAlgorithmSingle thirdGeneticAlgorithmSingle) {
		return new GeneticAlgorithmParallel(firstGeneticAlgorithmSingle, secondGeneticAlgorithmSingle, thirdGeneticAlgorithmSingle, generations, migrationGenerations, numberMigration);
	}
	
	public GeneticAlgorithmSingle getGeneticAlgorithmSingle(int generations, int[] populationOptions, Object[] parentSelectorOptions,
			Object[] recombinationOperatorOptions, Object[] mutationOperatorOptions, Object[] survivorSelectorOptions) throws IllegalParameterValueCheckedException {
		Population population;
		if (populationOptions.length == 1) {
			population = getPopulation(populationOptions[0]);
		} else {
			population = getPopulation(populationOptions[0], populationOptions[1], populationOptions[2]);
		}
		
		ParentSelector parentSelector = getParentSelector(parentSelectorOptions);
		
		RecombinationOperator recombinationOperator = getRecombinationOperator(recombinationOperatorOptions);
		
		MutationOperator mutationOperator = getMutationOperator(mutationOperatorOptions);
		
		SurvivorSelector survivorSelector = getSurvivorSelector(survivorSelectorOptions);
		
		int sizeOffspring = getSizeOffspring(population, survivorSelectorOptions);
		
		return new GeneticAlgorithmSingle(population, evaluationFunction, parentSelector, 
				recombinationOperator, mutationOperator, survivorSelector, generations, optimalFitness, sizeOffspring);
	}
	
	private int getSizeOffspring(Population population, Object[] survivorSelectorOptions) {
		String type = (String) survivorSelectorOptions[0];
		int result;
		if (type.equals("Age")) {
			result = population.getMinSize();
		} else if (type.equals("SteadyState")) {
			result = population.getSize() - (int) survivorSelectorOptions[1];
		} else {
			result = population.getSize();
		}
		return result;
	}

	private Individual getIndividual() {
		List<Integer> genotype = IntStream.range(0, geneticAlgorithmConfig.getGenotypeLong())
				.parallel()
				.map(i -> geneticAlgorithmUtils.getNewGenValue())
				.boxed()
				.collect(Collectors.toList());
		return new Individual(genotype);
	}
	
	private Population getPopulation(int size) throws IllegalParameterValueCheckedException {
		Collection<Individual> collectionIndividual = IntStream.range(0, size)
				.parallel()
				.mapToObj(i-> getIndividual())
				.collect(Collectors.toList());
		return new Population(evaluationFunction.evaluate(collectionIndividual));
	}
	
	private Population getPopulation(int max, int min, int size) throws IllegalParameterValueCheckedException {
		Collection<Individual> collectionIndividual = IntStream.range(0, size)
				.parallel()
				.mapToObj(i-> getIndividual())
				.collect(Collectors.toList());
		return new Population(evaluationFunction.evaluate(collectionIndividual), max, min);
	}
	
	private MutationOperator getMutationOperator(Object[] mutationOperatorOptions) {
		String type = (String) mutationOperatorOptions[0];
		MutationOperator result;
		if (type.equals("Permutation")) {
			result = getMutationOperatorPermutation((double) mutationOperatorOptions[1]);
		} else {
			result = getMutationOperatorSimple((double) mutationOperatorOptions[1]);
		}
		return result;
	}
	
	private MutationOperator getMutationOperatorPermutation(double probabilityMutation) {
		return new MutationOperatorPermutation(probabilityMutation, geneticAlgorithmUtils);
	}
	
	private MutationOperator getMutationOperatorSimple(double probabilityMutation) {
		return new MutationOperatorSimple(probabilityMutation, geneticAlgorithmUtils);
	}
	
	private ParentSelector getParentSelector(Object[] parentSelectorOptions) {
		String type = (String) parentSelectorOptions[0];
		ParentSelector result;
		if (type.equals("Ranking")) {
			result = getParentSelectorRanking((double) parentSelectorOptions[1]);
		} else { //Default Tournament
			result = getParentSelectorTournament();
		}
		return result;
	}
	
	private ParentSelector getParentSelectorRanking(double sParameter) {
		return new ParentSelectorRanking(geneticAlgorithmUtils, sParameter);
	}
	
	private ParentSelector getParentSelectorTournament() {
		return new ParentSelectorTournament(geneticAlgorithmUtils);
	}
	
	private RecombinationOperator getRecombinationOperator(Object[] recombinationOperatorOptions) {
		String type = (String) recombinationOperatorOptions[0];
		RecombinationOperator result;
		if (type.equals("Uniform")) {
			result = getRecombinationOperatorUniform((double) recombinationOperatorOptions[1]);
		} else {
			result = getRecombinationOperatorSimple((double) recombinationOperatorOptions[1]);
		}
		return result;
	}
	
	private RecombinationOperator getRecombinationOperatorSimple(double probabilityRecombination) {
		return new RecombinationOperatorSimple(probabilityRecombination, geneticAlgorithmUtils);
	}
	
	private RecombinationOperator getRecombinationOperatorUniform(double probabilityRecombination) {
		return new RecombinationOperatorUniform(probabilityRecombination, geneticAlgorithmUtils);
	}
	
	private SurvivorSelector getSurvivorSelector(Object[] survivorSelectorOptions) {
		String type = (String) survivorSelectorOptions[0];
		SurvivorSelector result;
		if (type.equals("Age")) {
			result = getSurvivorSelectorAge();
		} else if (type.equals("RoundRobin")) {
			result = getSurvivorSelectorRoundRobin((int) survivorSelectorOptions[1]);
		} else {
			result = getSurvivorSelectorSteadyState((int) survivorSelectorOptions[1]);
		}
		return result;
	}
	
	private SurvivorSelector getSurvivorSelectorAge() {
		return new SurvivorSelectorAge(evaluationFunction);
	}
	
	private SurvivorSelector getSurvivorSelectorRoundRobin(int battleNumber) {
		return new SurvivorSelectorRoundRobin(geneticAlgorithmUtils, battleNumber);
	}
	
	private SurvivorSelector getSurvivorSelectorSteadyState(int survivor) {
		return new SurvivorSelectorSteadyState(survivor);
	}
}
