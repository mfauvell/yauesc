package es.uned.yauesc.geneticAlgorithm;

import java.util.List;
import java.util.Collection;
import java.util.stream.Collectors;

public class MutationOperatorPermutation implements MutationOperator {
	
	private double probability;
	private GeneticAlgorithmUtils geneticAlgorithmUtils;

	public MutationOperatorPermutation(double probability, GeneticAlgorithmUtils geneticAlgorithmUtils) {
		this.probability = probability;
		this.geneticAlgorithmUtils = geneticAlgorithmUtils;
	}

	@Override
	public Collection<Individual> mutate(Collection<Individual> offspring) {
		return offspring.parallelStream().
			map(individual -> doMutation(individual)).collect(Collectors.toList());
	}
	
	private Individual doMutation(Individual individual) {
		if (geneticAlgorithmUtils.getProbability() <= probability) {
			int firstPosition = geneticAlgorithmUtils.getGenPosition();
			int secondPosition;
			do {
				secondPosition = geneticAlgorithmUtils.getGenPosition();
			} while(firstPosition == secondPosition);
			List<Integer> genotype = individual.getGenotype();
			int firstGen = genotype.get(firstPosition);
			int secondGen = genotype.get(secondPosition);
			genotype.set(firstPosition, secondGen);
			genotype.set(secondPosition, firstGen);
			return new Individual(genotype);
		} else {
			return individual;
		}
	}

}
