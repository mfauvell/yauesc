package es.uned.yauesc.geneticAlgorithm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que encapsula la ejecución de un algoritmo genético simple
 */
public class GeneticAlgorithmSingle implements GeneticAlgorithm {
	
	private Population population;
	private EvaluationFunction evaluationFunction;
	private ParentSelector parentSelector;
	private RecombinationOperator recombinationOperator;
	private MutationOperator mutationOperator;
	private SurvivorSelector survivorSelector;
	private int generations;
	private int sizeOffspring;
	private Fitness optimalFitness;
	private boolean finished;
	private boolean foundOptimal;
	private Individual solution;
	
	private Collection<GeneticAlgorithmObserver> observers;
	
	private final static Logger LOGGER = Logger.getLogger(GeneticAlgorithmSingle.class.getName());

	/**
	 * Constructor que crea por defecto un algoritmo genético conlos operadores pasados como argumento
	 * 
	 * @param population				la población inicial del algoritmo
	 * @param evaluationFunction		la función de evaluación a utilizar
	 * @param parentSelector			el operador selector de padres
	 * @param recombinationOperator		el operador de cruce
	 * @param mutationOperator			el operador de mutación
	 * @param survivorSelector			el selector de supervivientes
	 * @param generations				el número de generaciones a realizar
	 * @param optimalFitness			la adecaución optima para detener el agoritmo
	 * @param sizeOffspring				el tamaño de la descendencia a generar en cada iteración
	 */
	public GeneticAlgorithmSingle(Population population, EvaluationFunction evaluationFunction,
			ParentSelector parentSelector, RecombinationOperator recombinationOperator,
			MutationOperator mutationOperator, SurvivorSelector survivorSelector, int generations, Fitness optimalFitness, int sizeOffspring) {
		this.evaluationFunction = evaluationFunction;
		this.parentSelector = parentSelector;
		this.recombinationOperator = recombinationOperator;
		this.mutationOperator = mutationOperator;
		this.survivorSelector = survivorSelector;
		this.generations = generations;
		this.optimalFitness = optimalFitness;
		this.sizeOffspring = sizeOffspring;
		
		observers = new ArrayList<GeneticAlgorithmObserver>();
		
		setPopulation(population);
		
		finished = false;
		foundOptimal = false;
	}

	@Override
	public Individual getSolution() {
		return solution;
	}

	/**
	 * Obtiene los mejores individuos en el número pasado como argumento
	 * 
	 * @param number	el número de inviduos a obtener
	 * 
	 * @return	una colección de individuos
	 */
	public Collection<Individual> getBestIndividual(int number) {
		return population.getBestIndividual(number);
	}
	
	/**
	 * Configura el número de generaciones
	 * 
	 * @param number	el número de generaciones a configurar
	 */
	public void setGenerations(int number) {
		generations = number;	
	}
	
	/**
	 * Configura una nueva población en el algoritmo
	 * 
	 * @param newPopulation	la nueva población
	 */
	public void setPopulation(Population newPopulation) {
		population = newPopulation;
		
		population.substituteAllIndividual(evaluationFunction.evaluate(population.getAllIndividual()));
		
		if (population.isAged()) {
			List<Individual> individualList = new ArrayList<>(population.getAllIndividual());
			Fitness max = individualList.get(0).getFitness();
			Fitness min = individualList.get(individualList.size() -1).getFitness();
			population.substituteAllIndividual(evaluationFunction.setAge(max, min, individualList));
		}
		
		solution = population.getBestIndividual();
		
		LOGGER.log(Level.INFO, "New population set: " + population.toString());
	}
	
	@Override
	public void run() {
		int index = 0;
		if (generations == 0)
			finished = true;
		else
			finished = false;
		while (!finished) {
			
			LOGGER.log(Level.INFO, "Iteration number: " + index);
			
			Collection<Individual> parents = parentSelector.selectParents(population.getAllIndividual(), sizeOffspring);
			
			Collection<Individual> recombinatedOffspring = recombinationOperator.recombine(parents);
			
			Collection<Individual> mutatedOffspring = mutationOperator.mutate(recombinatedOffspring);
			
			Collection<Individual> offspring = evaluationFunction.evaluate(mutatedOffspring);
			
			population.substituteAllIndividual(survivorSelector.getSurvivor(population, offspring));
			
			Individual bestOfGeneration = population.getBestIndividual();
			int compareSolution = bestOfGeneration.compareTo(solution);
			if (compareSolution > 0) {
				solution = bestOfGeneration;
				LOGGER.log(Level.INFO, "Set new solution: " + solution);
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
		LOGGER.log(Level.INFO, "Finished genetic algorithm execution");
	}
	
	@Override
	public boolean isFinished() {
		return finished;
	}
	
	@Override
	public void stop() {
		finished = true;
	}

	/**
	 * Indica si se ha encontrado la solución óptima
	 * 
	 * @return	foundOptimal
	 */
	public boolean foundOptimal() {
		return foundOptimal;
	}
	
	@Override
	public void removeSolution() {
		solution = null;
	}
	
	/**
	 * Substituye los peores individuos por los pasados como argumento
	 * 
	 * @param newIndividual	los nuevos individuos a añadir
	 */
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
