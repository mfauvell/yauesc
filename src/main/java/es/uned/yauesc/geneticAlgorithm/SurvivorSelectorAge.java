package es.uned.yauesc.geneticAlgorithm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Clase que representa el operador de selección de supervivientes por la edad. Reduce la edad de cada individuo por la edad y
 * los que llegan a 0 son eleiminados.
 */
public class SurvivorSelectorAge implements SurvivorSelector {
	
	private EvaluationFunction evaluationFunction;

	private final static Logger LOGGER = Logger.getLogger(SurvivorSelectorAge.class.getName());
	
	/**
	 * Constructor del operardor de selección de supervivientes por edad
	 * 
	 * @param evaluationFunction	función de evaluación que configura la edad de los individuos
	 */
	public SurvivorSelectorAge(EvaluationFunction evaluationFunction) {
		this.evaluationFunction = evaluationFunction;
	}

	@Override
	public Collection<Individual> getSurvivor(Population population, Collection<Individual> offspring) {
		ArrayList<Individual> populationIndividuals = new ArrayList<Individual>(population.getAllIndividual());
		//Low all Individual one but if there are newcomers (not have age) set to five
		populationIndividuals.parallelStream().forEach(individual -> {
			if (individual.isAged()) {
				individual.setAge(individual.getAge() - 1);
			} else {
				individual.setAge(5);
			}
		});
		ArrayList<Individual> survivor = new ArrayList<Individual>(populationIndividuals
				.parallelStream()
				.filter(individual -> individual.getAge() > 0)
				.collect(Collectors.toList()));
		ArrayList<Individual> allIndividual = new ArrayList<Individual>(survivor);
		allIndividual.addAll(offspring);
		Collections.sort(allIndividual);
		Collections.reverse(allIndividual);
		Fitness maxFitness = allIndividual.get(0).getFitness();
		Fitness minFitness = allIndividual.get(allIndividual.size() - 1).getFitness();
		ArrayList<Individual> offspringAged = new ArrayList<Individual>(evaluationFunction.setAge(maxFitness, minFitness, offspring));
		if (allIndividual.size() > population.getMaxSize()) {
			int gap = population.getMaxSize() - survivor.size();
			Collections.sort(offspringAged);
			Collections.reverse(offspringAged);
			offspringAged = new ArrayList<Individual>(offspringAged.subList(0, gap));
		}
		survivor.addAll(offspringAged);
		LOGGER.log(Level.INFO, "Survivors: " + survivor.toString());
		return survivor;
	}

}
