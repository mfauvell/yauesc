package es.uned.yauesc.geneticAlgorithm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Clase que representa el operador de selección de supervivientes por roundrobin donde se realizan una
 * competición con un número de combates todos contra todos y sobreviven los que más batallas venzan.
 */
public class SurvivorSelectorRoundRobin implements SurvivorSelector {

	private int battleNumber;
	private GeneticAlgorithmUtils geneticAlgorithmUtils;
	
	private final static Logger LOGGER = Logger.getLogger(SurvivorSelectorRoundRobin.class.getName());
	
	/**
	 * Constructor del operador de selección de supervivientes utilizando roundRobin
	 * 
	 * @param geneticAlgorithmUtils		utilidades auxiliares de caracter estocástico
	 * @param battleNumber				número de batallas
	 */
	public SurvivorSelectorRoundRobin(GeneticAlgorithmUtils geneticAlgorithmUtils, int battleNumber) {
		this.battleNumber = battleNumber;
		this.geneticAlgorithmUtils = geneticAlgorithmUtils;
	}

	@Override
	public Collection<Individual> getSurvivor(Population population, Collection<Individual> offspring) {
		ArrayList<Individual> fighters = new ArrayList<Individual>(population.getAllIndividual());
		fighters.addAll(offspring);
		ArrayList<Integer> fightResult =new ArrayList<Integer>(fighters
				.parallelStream()
				.mapToInt(individual ->  doFight(individual, fighters))
				.boxed()
				.collect(Collectors.toList()));
		Collection<Individual> survivor = IntStream.range(0, fightResult.size())
				.parallel()
				.boxed()
				.collect(Collectors.toMap(index-> index, index -> fightResult.get(index))).entrySet()
				.parallelStream()
				.sorted(Map.Entry.<Integer,Integer>comparingByValue().reversed())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue,newValue) -> oldValue, LinkedHashMap::new)).keySet()
				.parallelStream()
				.collect(Collectors.toList()).subList(0, population.getSize())
				.parallelStream()
				.map(index -> fighters.get(index))
				.collect(Collectors.toList());
		LOGGER.log(Level.INFO, "Survivors: " + survivor.toString());
		return survivor;
	}

	private int doFight(Individual individual, ArrayList<Individual> fighters) {
		return IntStream.range(0, battleNumber)
				.parallel()
				.map(i -> {
							Individual firstFighter = individual;
							Individual secondFighter = fighters.get(geneticAlgorithmUtils.getRandomInt(fighters.size()));
							int compare = firstFighter.compareTo(secondFighter);
							if ( compare > 0) {
								return 1;
							} else {
								return 0;
							}
						  })
				.sum();
	}

}
