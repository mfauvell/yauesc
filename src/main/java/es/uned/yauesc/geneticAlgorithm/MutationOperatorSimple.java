package es.uned.yauesc.geneticAlgorithm;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MutationOperatorSimple implements MutationOperator {

	private double probability;
	private GeneticAlgorithmUtils geneticAlgorithmUtils;
	
	public MutationOperatorSimple(double probability, GeneticAlgorithmUtils geneticAlgorithmUtils) {
		this.probability = probability;
		this.geneticAlgorithmUtils = geneticAlgorithmUtils;
	}

	@Override
	public Collection<Individual> mutate(Collection<Individual> offspring) {
		return offspring.parallelStream().
			   map(individual -> {
				   					List<Integer> newGenotype = individual.getGenotype().parallelStream().
				   							map(gen->(geneticAlgorithmUtils.getProbability() <= probability)? geneticAlgorithmUtils.getNewGenValue() : gen).
				   							collect(Collectors.toList());
				   					if (newGenotype.equals(individual.getGenotype()))
				   						return individual;
				   					else
				   						return new Individual(newGenotype);				   				
			   					 }).
			   collect(Collectors.toList()); 
	}

}
