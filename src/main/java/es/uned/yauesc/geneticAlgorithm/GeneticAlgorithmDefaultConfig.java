package es.uned.yauesc.geneticAlgorithm;

/**
 * Configuración por defecto del algoritmo genético
 */
public class GeneticAlgorithmDefaultConfig {

	public static final boolean isParallel = true;
	public static final int generations = 500;
	public static final int generationsToMigrate = 50;
	public static final int numberMigrants = 10;
	
	//First Algorithm
	public static final ParentSelectorType parentSelectorFirst = ParentSelectorType.Ranking;
	public static final double sParameterFirst = 1.5;
	public static final RecombinationOperatorType recombinationOperatorFirst = RecombinationOperatorType.Uniform;
	public static final double probabilityRecombinationFirst = 0.9;
	public static final MutationOperatorType mutationOperatorFirst = MutationOperatorType.Simple;
	public static final double probabilityMutationFirst = 0.2;
	public static final SurvivorSelectorType survivorSelectorFirst = SurvivorSelectorType.RoundRobin;
	public static int survivorsFirst = 4;
	public static int numberBattleFirst = 5;
	public static int populationSizeFirst = 100;
	public static int maxPopulationFirst = 200;
	public static int minPopulationFirst = 100;
	
	//Second Algorithm
	public static final ParentSelectorType parentSelectorSecond = ParentSelectorType.Tournament;
	public static final double sParameterSecond = 1.5;
	public static final RecombinationOperatorType recombinationOperatorSecond = RecombinationOperatorType.Simple;
	public static final double probabilityRecombinationSecond = 0.8;
	public static final MutationOperatorType mutationOperatorSecond = MutationOperatorType.Permutation;
	public static final double probabilityMutationSecond = 0.01;
	public static final SurvivorSelectorType survivorSelectorSecond = SurvivorSelectorType.SteadyState;
	public static int survivorsSecond = 2;
	public static int numberBattleSecond = 5;
	public static int populationSizeSecond = 100;
	public static int maxPopulationSecond = 200;
	public static int minPopulationSecond = 100;
	
	//Third Algorithm
	public static final ParentSelectorType parentSelectorThird = ParentSelectorType.Tournament;
	public static final double sParameterThird = 1.5;
	public static final RecombinationOperatorType recombinationOperatorThird = RecombinationOperatorType.Uniform;
	public static final double probabilityRecombinationThird = 0.8;
	public static final MutationOperatorType mutationOperatorThird = MutationOperatorType.Permutation;
	public static final double probabilityMutationThird = 0.01;
	public static final SurvivorSelectorType survivorSelectorThird = SurvivorSelectorType.AgeBased;
	public static int survivorsThird = 4;
	public static int numberBattleThird = 5;
	public static int populationSizeThird = 100;
	public static int maxPopulationThird = 125;
	public static int minPopulationThird = 75;
}
