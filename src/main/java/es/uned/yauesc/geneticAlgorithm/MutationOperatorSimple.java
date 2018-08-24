package es.uned.yauesc.geneticAlgorithm;

import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Clase que proporciona un operador de mutación que intenta mutar cada gen de cada individuo con una probabilidad dada
 */
public class MutationOperatorSimple implements MutationOperator {

	private double probability;
	private GeneticAlgorithmUtils geneticAlgorithmUtils;
	
	private final static Logger LOGGER = Logger.getLogger(MutationOperatorSimple.class.getName());
	
	/**
	 * Constructor para el operador de mutación simple
	 * 
	 * @param probability				la probabilidad de mutación
	 * @param geneticAlgorithmUtils		utilidades auxiliares de caracter estocástico
	 */
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
				   					LOGGER.log(Level.INFO, "Genotype to mutate: " + individual.getGenotype().toString() + " mutated to: " + newGenotype);
				   					if (newGenotype.equals(individual.getGenotype()))
				   						return individual;
				   					else
				   						return new Individual(newGenotype);				   				
			   					 }).
			   collect(Collectors.toList()); 
	}

}
