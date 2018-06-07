package es.uned.yauesc.geneticAlgorithm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ParentSelectorTournament implements ParentSelector {
	
	private GeneticAlgorithmUtils geneticAlgorithmUtils;

	public ParentSelectorTournament(GeneticAlgorithmUtils geneticAlgorithmUtils) {
		this.geneticAlgorithmUtils = geneticAlgorithmUtils;
	}

	@Override
	public Collection<Individual> selectParents(Collection<Individual> individualsPopulation, int number) {
		ArrayList<Individual> individuals = new ArrayList<Individual>(individualsPopulation);
		return IntStream.range(0, number)
				.parallel()
				.mapToObj(i -> getParent(individuals))
				.collect(Collectors.toList());
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
