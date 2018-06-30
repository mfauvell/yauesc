package es.uned.yauesc.geneticAlgorithm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RecombinationOperatorSimple implements RecombinationOperator{

	private double probability;
	private GeneticAlgorithmUtils geneticAlgorithmUtils;
	
	public RecombinationOperatorSimple(double probability, GeneticAlgorithmUtils geneticAlgorithmUtils) {
		this.probability = probability;
		this.geneticAlgorithmUtils = geneticAlgorithmUtils;
	}

	@Override
	public Collection<Individual> recombine(Collection<Individual> parents) {
		ArrayList<Individual> parentsList = new ArrayList<Individual>(parents);
		int parentsSize = parentsList.size();
		int iterations = parentsSize / 2;
		Collection<Individual> result = IntStream.range(0, iterations)
				.parallel()
				.mapToObj(index -> doRecombination(parentsList.get(index * 2), parentsList.get((index * 2) + 1)))
				.flatMap(Collection::stream)
				.collect(Collectors.toList());
		if ((parentsSize - (iterations*2)) != 0) {
			result.add(parentsList.get(parentsSize -1));
		}
		return result;
	}
	
	private Collection<Individual> doRecombination(Individual firstParent, Individual secondParent) {
		Collection<Individual> result = new ArrayList<Individual>();
		if (geneticAlgorithmUtils.getProbability() <= probability) {
			int crossPoint = geneticAlgorithmUtils.getCrossPoint();
			List<Integer> firstGenotype = firstParent.getGenotype();
			List<Integer> secondGenotype = secondParent.getGenotype();
			List<Integer> firstSonGenotype = new ArrayList<Integer>();
			List<Integer> secondSonGenotype = new ArrayList<Integer>();
			int size = firstGenotype.size();
			//TODO inefficient, best with streams but I don't see how
			for (int i = 0; i< size; i++) {
				if (i <= crossPoint) {
					firstSonGenotype.add(firstGenotype.get(i));
					secondSonGenotype.add(secondGenotype.get(i));
				} else {
					firstSonGenotype.add(secondGenotype.get(i));
					secondSonGenotype.add(firstGenotype.get(i));
				}
			}
			result.add(new Individual(firstSonGenotype));
			result.add(new Individual(secondSonGenotype));
		} else {
			result.add(firstParent);
			result.add(secondParent);
		}
		return result;
	}

}
