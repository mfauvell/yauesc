package es.uned.yauesc.geneticAlgorithm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase controladora que sirve de intarface para el paquente geneticAlgorithm 
 */
public class GeneticAlgorithmController implements GeneticAlgorithmObserver {

	private GeneticAlgorithmFactory geneticAlgorithmFactory;
	
	private GeneticAlgorithm mainGeneticAlgorithm;
	private GeneticAlgorithmSingle firstGeneticAlgorithmSingle;
	private GeneticAlgorithmSingle secondGeneticAlgorithmSingle;
	private GeneticAlgorithmSingle thirdGeneticAlgorithmSingle;
	private int[] firstGeneticAlgorithmSinglePopulationOptions;
	private int[] secondGeneticAlgorithmSinglePopulationOptions;
	private int[] thirdGeneticAlgorithmSinglePopulationOptions;
	
	private boolean parallel;
	private int generations;
	private int generationsToMigrate;
	
	private boolean finished;
	private Individual solution;
	
	private Collection<GeneticAlgorithmControllerObserver> observers;
	
	private final static Logger LOGGER = Logger.getLogger(GeneticAlgorithmController.class.getName());
	
	/**
	 * Constructor por defecto 
	 */
	public GeneticAlgorithmController() {
		parallel = false;
		observers = new ArrayList<>();
	}
	
	/**
	 * Obtiene la mejor solución encontrada por el algoritmo genético
	 * 
	 * @return	solution
	 */
	public Individual getSolution() {
		return solution;
	}
	
	/**
	 * Obtiene el número de generaciones configuradas
	 * 
	 * @return	generarions
	 */
	public int getGenerations() {
		return generations;
	}
	
	/**
	 * Obtiene la frecuencia para realizar la migración en un algoritmo genético simple
	 * 
	 * @return	generationsToMigrate
	 */
	public int getGenerarionsToMigrate() {
		int result = 0;
		if (parallel) {
			result = generationsToMigrate;
		}
		return result;
	}
	
	/**
	 * Comprueba si se ha configurado un algoritmo genético paralelo
	 * 
	 * @return	parallel
	 */
	public boolean isParallel() {
		return parallel;
	}
	
	/**
	 * Crea la factoría que permite generar el resto de los componentes
	 * 
	 * @param genotypeLong			tamaño del genotipo
	 * @param numberValuesGen		número de valores diferentes que puede tomar un gen
	 * @param evaluationFunction	función de evaluación
	 * @param optimalFitness		adecuación óptima objetivo
	 */
	public void setGeneticAlgorithmFactory(int genotypeLong, int numberValuesGen, EvaluationFunction evaluationFunction, Fitness optimalFitness) {
		geneticAlgorithmFactory = new GeneticAlgorithmFactory(genotypeLong, numberValuesGen, evaluationFunction, optimalFitness);
	}
	
	/**
	 * Configura las opciones básicas del algoritmo genético consistentes en el número de  generaciones y si
	 * es un algoritmo compuesto o simple
	 * 
	 * @param generations	número de generaciones
	 * @param parallel		true si es un algoritmo compuesto
	 */
	public void setBasicOptions(int generations, boolean parallel) {
		this.generations = generations;
		this.parallel = parallel;
	}
	
	/**
	 * Configura el algoritmo simple número uno
	 * 
	 * @param populationOptions							las opciones de población del algoritmo genético
	 * @param parentSelectorOptions						el selector de padres y sus opciones
	 * @param recombinationOperatorOptions				el operador de cruce y sus opciones
	 * @param mutationOperatorOptions					el operador de cruce y sus opciones
	 * @param survivorSelectorOptions					el selector de supervivientes y sus opciones
	 * 
	 * @throws IllegalParameterValueCheckedException
	 */
	public void setFirstGeneticAlgorithmSingle(int[] populationOptions, Object[] parentSelectorOptions,
			Object[] recombinationOperatorOptions, Object[] mutationOperatorOptions, Object[] survivorSelectorOptions) throws IllegalParameterValueCheckedException {
		firstGeneticAlgorithmSinglePopulationOptions = populationOptions;
		firstGeneticAlgorithmSingle = geneticAlgorithmFactory.getGeneticAlgorithmSingle(generations, populationOptions, parentSelectorOptions, recombinationOperatorOptions, mutationOperatorOptions, survivorSelectorOptions);
	}
	
	/**
	 * Configura el algoritmo simple número dos
	 * 
	 * @param populationOptions							las opciones de población del algoritmo genético
	 * @param parentSelectorOptions						el selector de padres y sus opciones
	 * @param recombinationOperatorOptions				el operador de cruce y sus opciones
	 * @param mutationOperatorOptions					el operador de cruce y sus opciones
	 * @param survivorSelectorOptions					el selector de supervivientes y sus opciones
	 * 
	 * @throws IllegalParameterValueCheckedException
	 */
	public void setSecondGeneticAlgorithmSingle(int[] populationOptions, Object[] parentSelectorOptions,
			Object[] recombinationOperatorOptions, Object[] mutationOperatorOptions, Object[] survivorSelectorOptions) throws IllegalParameterValueCheckedException {
		secondGeneticAlgorithmSinglePopulationOptions = populationOptions;
		secondGeneticAlgorithmSingle = geneticAlgorithmFactory.getGeneticAlgorithmSingle(generations, populationOptions, parentSelectorOptions, recombinationOperatorOptions, mutationOperatorOptions, survivorSelectorOptions);
	}
	
	/**
	 * Configura el algoritmo simple número tres
	 * 
	 * @param populationOptions							las opciones de población del algoritmo genético
	 * @param parentSelectorOptions						el selector de padres y sus opciones
	 * @param recombinationOperatorOptions				el operador de cruce y sus opciones
	 * @param mutationOperatorOptions					el operador de cruce y sus opciones
	 * @param survivorSelectorOptions					el selector de supervivientes y sus opciones
	 * 
	 * @throws IllegalParameterValueCheckedException
	 */
	public void setThirdGeneticAlgorithmSingle(int[] populationOptions, Object[] parentSelectorOptions,
			Object[] recombinationOperatorOptions, Object[] mutationOperatorOptions, Object[] survivorSelectorOptions) throws IllegalParameterValueCheckedException {
		thirdGeneticAlgorithmSinglePopulationOptions = populationOptions;
		thirdGeneticAlgorithmSingle = geneticAlgorithmFactory.getGeneticAlgorithmSingle(generations, populationOptions, parentSelectorOptions, recombinationOperatorOptions, mutationOperatorOptions, survivorSelectorOptions);
	}
	
	/**
	 * 
	 * 
	 * @param migrationGenerations
	 * @param numberMigration
	 */
	public void setMainGeneticAlgorithm(int migrationGenerations, int numberMigration) {
		generationsToMigrate = migrationGenerations;
		mainGeneticAlgorithm = geneticAlgorithmFactory.getGeneticAlgorithmParallel(generations, migrationGenerations, numberMigration, firstGeneticAlgorithmSingle, secondGeneticAlgorithmSingle, thirdGeneticAlgorithmSingle);
		mainGeneticAlgorithm.registerObserver(this);
	}
	
	/**
	 * 
	 */
	public void setMainGeneticAlgorithm() {
		mainGeneticAlgorithm = firstGeneticAlgorithmSingle;
		mainGeneticAlgorithm.registerObserver(this);
	}
	
	/**
	 * 
	 * @throws IllegalParameterValueCheckedException
	 */
	public void resetGeneticAlgorithm() throws IllegalParameterValueCheckedException {
		if (parallel) {
			secondGeneticAlgorithmSingle.removeSolution();
			secondGeneticAlgorithmSingle.setPopulation(geneticAlgorithmFactory.getPopulation(secondGeneticAlgorithmSinglePopulationOptions));
			thirdGeneticAlgorithmSingle.removeSolution();
			thirdGeneticAlgorithmSingle.setPopulation(geneticAlgorithmFactory.getPopulation(thirdGeneticAlgorithmSinglePopulationOptions));
			mainGeneticAlgorithm.removeSolution();
		}
		firstGeneticAlgorithmSingle.removeSolution();
		firstGeneticAlgorithmSingle.setPopulation(geneticAlgorithmFactory.getPopulation(firstGeneticAlgorithmSinglePopulationOptions));
	}
	
	/**
	 * Registra un observador en algoritmo genético principal
	 * 
	 * @param observer	un observador que implemente geneticAlgorithmObserver
	 */
	public void registerObserverMainGeneticAlgorithm(GeneticAlgorithmObserver observer) {
		mainGeneticAlgorithm.registerObserver(observer);
	}
	
	/**
	 * Registra un observador en el algoritmo genético simple 1
	 * 
	 * @param observer	un observador que implemente geneticAlgorithmObserver
	 */
	public void registerObserverFirstGeneticAlgorithm(GeneticAlgorithmObserver observer) {
		firstGeneticAlgorithmSingle.registerObserver(observer);
	}
	
	/**
	 * Registra un observador en el algoritmo genético simple 2
	 * 
	 * @param observer	un observador que implemente geneticAlgorithmObserver
	 */
	public void registerObserverSecondGeneticAlgorithm(GeneticAlgorithmObserver observer) {
		secondGeneticAlgorithmSingle.registerObserver(observer);
	}
	
	/**
	 * Registra un observador en el algoritmo genético simple 3
	 * 
	 * @param observer	un observador que implemente geneticAlgorithmObserver
	 */
	public void registerObserverThirdGeneticAlgorithm(GeneticAlgorithmObserver observer) {
		thirdGeneticAlgorithmSingle.registerObserver(observer);
	}
	
	/**
	 * Registra un observador para el controlador 
	 * 
	 * @param geneticAlgorithmControllerObserver	un observador que implementa geneticAlgorithmControllerObserver
	 */
	public void registerObserver(GeneticAlgorithmControllerObserver geneticAlgorithmControllerObserver) {
		if (!(observers.contains(geneticAlgorithmControllerObserver))) {
			observers.add(geneticAlgorithmControllerObserver);
		}
	}
	
	/**
	 * Elimina un observador de el controlador
	 * 
	 * @param geneticAlgorithmControllerObserver un observador que implementa geneticAlgoritihmControllerObserver
	 */
	public void removeObserver(GeneticAlgorithmControllerObserver geneticAlgorithmControllerObserver) {
		observers.remove(geneticAlgorithmControllerObserver);	
	}

	/**
	 * Notiifica los observadores qeu algo ha cambiado en la ejecución del controlador
	 */
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
	
	/**
	 * Inicia la ejecución del algoritmo genético
	 */
	public void startExecution() {
		ExecutorService executor = Executors.newFixedThreadPool(1);
		executor.execute(mainGeneticAlgorithm);
		
		executor.shutdown();
		
		try {
			executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		} catch (InterruptedException e) {
			LOGGER.log(Level.SEVERE, "Thread interrupted");
			Thread.currentThread().interrupt();
			System.out.println("Failed execution of algorithm");
		}
	}

	/**
	 * Para la ejecución del algoritmo genético
	 */
	public void stopExecution() {
		mainGeneticAlgorithm.stop();
	}
		
}
