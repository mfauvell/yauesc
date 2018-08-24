package es.uned.yauesc.geneticAlgorithm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Clase que represanta un selector de padres utilizando un torneo binario entre contendientes elegidos al azar
 */
public class ParentSelectorTournament implements ParentSelector {
	
	private GeneticAlgorithmUtils geneticAlgorithmUtils;
	
	private final static Logger LOGGER = Logger.getLogger(ParentSelectorTournament.class.getName());

	/**
	 * Constructor para el selector de padres por torneo
	 * 
	 * @param geneticAlgorithmUtils	utilidades auxiliares de caracter estocástico
	 */
	public ParentSelectorTournament(GeneticAlgorithmUtils geneticAlgorithmUtils) {
		this.geneticAlgorithmUtils = geneticAlgorithmUtils;
	}

	@Override
	public Collection<Individual> selectParents(Collection<Individual> individualsPopulation, int number) {
		ArrayList<Individual> individuals = new ArrayList<Individual>(individualsPopulation);
		Collection<Individual> result = IntStream.range(0, number)
				.parallel()
				.mapToObj(i -> getParent(individuals))
				.collect(Collectors.toList());
		LOGGER.log(Level.INFO, "Parent selected: " + result.toString());
		return result;
	}

	private Individual getParent(ArrayList<Individual> individuals) {
		int max = individuals.size();
		Individual firstFighter = individuals.get(geneticAlgorithmUtils.getRandomInt(max));
		Individual secondFighter = individuals.get(geneticAlgorithmUtils.getRandomInt(max));
		int compare = firstFighter.compareTo(secondFighter);
		if ( compare < 0) {
			return secondFighter;
		} else {
			return firstFighter;
		}
	}

}
