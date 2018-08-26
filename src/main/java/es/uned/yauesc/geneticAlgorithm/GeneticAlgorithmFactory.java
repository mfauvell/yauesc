package es.uned.yauesc.geneticAlgorithm;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Factoria para el paquete geneticAlgorithm
 */
public class GeneticAlgorithmFactory {

	private GeneticAlgorithmUtils geneticAlgorithmUtils;
	private GeneticAlgorithmConfig geneticAlgorithmConfig;
	private EvaluationFunction evaluationFunction;
	private Fitness optimalFitness;
	
	private final static Logger LOGGER = Logger.getLogger(GeneticAlgorithmFactory.class.getName());
	
	/**
	 * Constructor que crea una factoria para el paquete geneticAlgorithm y crea la infraestructura mínima
	 * 
	 * @param genotypeLong			la longitud del gentotipo
	 * @param numberValuesGen		el número de valores diferentes que puede tener un gen
	 * @param evaluationFunction	la función de evaluación a utilizar
	 * @param optimalFitness		la adecuación optima buscada
	 */
	public GeneticAlgorithmFactory(int genotypeLong, int numberValuesGen, EvaluationFunction evaluationFunction, Fitness optimalFitness ) {
		geneticAlgorithmConfig = new GeneticAlgorithmConfig(genotypeLong, numberValuesGen);
		geneticAlgorithmUtils = new GeneticAlgorithmUtils(geneticAlgorithmConfig);
		this.evaluationFunction = evaluationFunction;
		this.optimalFitness = optimalFitness;
	}
	
	/**
	 * Obtiene un algoritmo genético compuesto con las opciones pasadas como parámetro
	 * 
	 * @param generations					número de generaciones
	 * @param migrationGenerations			frecuencia de migración de individuos
	 * @param numberMigration				número de migrantes
	 * @param firstGeneticAlgorithmSingle	un algoritmo genético simple
	 * @param secondGeneticAlgorithmSingle	un algoritmo genético simple
	 * @param thirdGeneticAlgorithmSingle	un algoritmo genético simple
	 * 
	 * @return								un algoritmo genético compuesto
	 */
	public GeneticAlgorithm getGeneticAlgorithmParallel(int generations, int migrationGenerations, int numberMigration,
			GeneticAlgorithmSingle firstGeneticAlgorithmSingle, GeneticAlgorithmSingle secondGeneticAlgorithmSingle, GeneticAlgorithmSingle thirdGeneticAlgorithmSingle) {
		return new GeneticAlgorithmParallel(firstGeneticAlgorithmSingle, secondGeneticAlgorithmSingle, thirdGeneticAlgorithmSingle, generations, migrationGenerations, numberMigration);
	}
	
	/**
	 * Obtiene un algoritmo genético simple conlas opciones pasadas como parámetro
	 * 
	 * @param generations					número de generaciones
	 * @param populationOptions				opciones de población
	 * @param parentSelectorOptions			opciones de selección de padres
	 * @param recombinationOperatorOptions	opciones del operador de cruce
	 * @param mutationOperatorOptions		opciones del oparador de mutación
	 * @param survivorSelectorOptions		opciones de selección de supervivientes
	 * 
	 * @return								un algoritmo genético simple
	 * 
	 * @throws IllegalParameterValueCheckedException
	 */
	public GeneticAlgorithmSingle getGeneticAlgorithmSingle(int generations, int[] populationOptions, Object[] parentSelectorOptions,
			Object[] recombinationOperatorOptions, Object[] mutationOperatorOptions, Object[] survivorSelectorOptions) throws IllegalParameterValueCheckedException {
		Population population = getPopulation(populationOptions);
		
		ParentSelector parentSelector = getParentSelector(parentSelectorOptions);
		
		RecombinationOperator recombinationOperator = getRecombinationOperator(recombinationOperatorOptions);
		
		MutationOperator mutationOperator = getMutationOperator(mutationOperatorOptions);
		
		SurvivorSelector survivorSelector = getSurvivorSelector(survivorSelectorOptions);
		
		int sizeOffspring = getSizeOffspring(population, survivorSelectorOptions);
		
		LOGGER.log(Level.INFO, "Create AGS with population: " + populationOptions.toString() + 
				"\nParentSelector : " + parentSelectorOptions.toString() +
				"\nRecombinationOperator: " + recombinationOperatorOptions.toString() +
				"\nMutationOperator: "	+ mutationOperatorOptions.toString() +
				"\nSurvivorSelector: " + survivorSelectorOptions.toString());
		
		return new GeneticAlgorithmSingle(population, evaluationFunction, parentSelector, 
				recombinationOperator, mutationOperator, survivorSelector, generations, optimalFitness, sizeOffspring);
	}
	
	/**
	 * Obtiene una nueva población según las opciones pasadas como argumento
	 * 
	 * @param populationOptions		las opciones de la población
	 * 
	 * @return						una nueva población
	 * 
	 * @throws IllegalParameterValueCheckedException
	 */
	public Population getPopulation(int[] populationOptions) throws IllegalParameterValueCheckedException {
		Population population;
		if (populationOptions.length == 1) {
			population = getPopulationNormal(populationOptions[0]);
		} else {
			population = getPopulationAged(populationOptions[0], populationOptions[1], populationOptions[2]);
		}
		return population;
	}
	
	private int getSizeOffspring(Population population, Object[] survivorSelectorOptions) {
		SurvivorSelectorType type = (SurvivorSelectorType) survivorSelectorOptions[0];
		int result;
		if (type.equals(SurvivorSelectorType.AgeBased)) {
			result = population.getMinSize();
		} else if (type.equals(SurvivorSelectorType.SteadyState)) {
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
	
	private Population getPopulationNormal(int size) throws IllegalParameterValueCheckedException {
		Collection<Individual> collectionIndividual = IntStream.range(0, size)
				.parallel()
				.mapToObj(i-> getIndividual())
				.collect(Collectors.toList());
		return new Population(evaluationFunction.evaluate(collectionIndividual));
	}
	
	private Population getPopulationAged(int max, int min, int size) throws IllegalParameterValueCheckedException {
		Collection<Individual> collectionIndividual = IntStream.range(0, size)
				.parallel()
				.mapToObj(i-> getIndividual())
				.collect(Collectors.toList());
		return new Population(evaluationFunction.evaluate(collectionIndividual), max, min);
	}
	
	private MutationOperator getMutationOperator(Object[] mutationOperatorOptions) {
		MutationOperatorType type = (MutationOperatorType) mutationOperatorOptions[0];
		MutationOperator result;
		if (type.equals(MutationOperatorType.Permutation)) {
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
		ParentSelectorType type = (ParentSelectorType) parentSelectorOptions[0];
		ParentSelector result;
		if (type.equals(ParentSelectorType.Ranking)) {
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
		RecombinationOperatorType type = (RecombinationOperatorType) recombinationOperatorOptions[0];
		RecombinationOperator result;
		if (type.equals(RecombinationOperatorType.Uniform)) {
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
		SurvivorSelectorType type = (SurvivorSelectorType) survivorSelectorOptions[0];
		SurvivorSelector result;
		if (type.equals(SurvivorSelectorType.AgeBased)) {
			result = getSurvivorSelectorAge();
		} else if (type.equals(SurvivorSelectorType.RoundRobin)) {
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
