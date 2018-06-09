package es.uned.yauesc.geneticAlgorithm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SurvivorSelectorRoundRobin implements SurvivorSelector {

	private int battleNumber;
	private GeneticAlgorithmUtils geneticAlgorithmUtils;
	
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
		return IntStream.range(0, fightResult.size())
				.parallel()
				.boxed()
				.collect(Collectors.toMap(it -> it, it -> fightResult.get(it))).entrySet()
				.parallelStream()
				.sorted(Map.Entry.<Integer,Integer>comparingByValue().reversed())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue,newValue) -> oldValue, LinkedHashMap::new)).keySet()
				.parallelStream()
				.collect(Collectors.toList()).subList(0, population.getSize())
				.parallelStream()
				.map(index -> fighters.get(index))
				.collect(Collectors.toList());
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
