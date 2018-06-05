package es.uned.yauesc.geneticAlgorithm;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RecombinationOperatorUniform implements RecombinationOperator {
	
	private double probability;
	private GeneticAlgorithmUtils geneticAlgorithmUtils;

	public RecombinationOperatorUniform(double probability, GeneticAlgorithmUtils geneticAlgorithmUtils) {
		this.probability = probability;
		this.geneticAlgorithmUtils = geneticAlgorithmUtils;
	}

	@Override
	public Collection<Individual> recombine(Collection<Individual> parents) {
		ArrayList<Individual> parentsList = new ArrayList<Individual>(parents);
		List<Integer> mask = geneticAlgorithmUtils.getBinaryMask();
		int iterations = parentsList.size() / 2;
		return IntStream.range(0, iterations)
				.parallel()
				.mapToObj(index -> doRecombination(parentsList.get(index * 2), parentsList.get((index * 2) + 1), mask))
				.flatMap(Collection::stream)
				.collect(Collectors.toList());
	}

	private Collection<Individual> doRecombination(Individual firstParent, Individual secondParent, List<Integer> mask) {
		Collection<Individual> result = new ArrayList<Individual>();
		if (geneticAlgorithmUtils.getProbability() <= probability) {
			List<Integer> firstGenotype = firstParent.getGenotype();
			List<Integer> secondGenotype = secondParent.getGenotype();
			List<Integer> firstSonGenotype = new ArrayList<Integer>();
			List<Integer> secondSonGenotype = new ArrayList<Integer>();
			int size = firstGenotype.size();
			//TODO inefficient, best with streams but I don't see how
			for (int i = 0; i< size; i++) {
				if (mask.get(i) == 1) {
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
