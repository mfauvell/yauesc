package es.uned.yauesc.geneticAlgorithm;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Clase que proporciona un operador de mutación por permutación, donde si un individuo es marcado para mutar intercambia
 * dos genes al azar.
 */
public class MutationOperatorPermutation implements MutationOperator {
	
	private double probability;
	private GeneticAlgorithmUtils geneticAlgorithmUtils;

	private final static Logger LOGGER = Logger.getLogger(MutationOperatorPermutation.class.getName());
	
	/**
	 * Constructor para el operador de mutación por permutación
	 * 
	 * @param probability				la probabilidad de mutación
	 * @param geneticAlgorithmUtils		utilidades auxiliares de caracter estocástico
	 */
	public MutationOperatorPermutation(double probability, GeneticAlgorithmUtils geneticAlgorithmUtils) {
		this.probability = probability;
		this.geneticAlgorithmUtils = geneticAlgorithmUtils;
	}

	@Override
	public Collection<Individual> mutate(Collection<Individual> offspring) {
		return offspring.parallelStream().map(individual -> doMutation(individual)).collect(Collectors.toList());
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
			LOGGER.log(Level.INFO, "Genotype to mutate: " + genotype.toString() + " with first gen number: " + firstGen + " and second gen number: " + secondGen);
			genotype.set(firstPosition, secondGen);
			genotype.set(secondPosition, firstGen);
			return new Individual(genotype);
		} else {
			return individual;
		}
	}

}
