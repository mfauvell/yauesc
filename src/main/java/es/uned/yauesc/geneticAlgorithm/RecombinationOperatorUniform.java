package es.uned.yauesc.geneticAlgorithm;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Clase que representa al operador de recombinación uniforme del algoritmo genético, donde se realiza la mezcla
 * de dos genotipos de dos padres siguiendo una mascara binaria donde un 0 representa un padre y un 1 otro en un
 * descendiente y viceversa en el otro
 */
public class RecombinationOperatorUniform implements RecombinationOperator {
	
	private double probability;
	private GeneticAlgorithmUtils geneticAlgorithmUtils;
	
	private final static Logger LOGGER = Logger.getLogger(RecombinationOperatorUniform.class.getName());

	/**
	 * Constructor para el operador de recombinación uniforme
	 * 
	 * @param probability				probabilidad de que se produzca una recombinación
	 * @param geneticAlgorithmUtils		utilidades auxiliares de caracter estocástico
	 */
	public RecombinationOperatorUniform(double probability, GeneticAlgorithmUtils geneticAlgorithmUtils) {
		this.probability = probability;
		this.geneticAlgorithmUtils = geneticAlgorithmUtils;
	}

	@Override
	public Collection<Individual> recombine(Collection<Individual> parents) {
		ArrayList<Individual> parentsList = new ArrayList<Individual>(parents);
		List<Integer> mask = geneticAlgorithmUtils.getBinaryMask();
		int sizeParents = parentsList.size();
		int iterations = sizeParents / 2;
		Collection<Individual> result = IntStream.range(0, iterations)
				.parallel()
				.mapToObj(index -> doRecombination(parentsList.get(index * 2), parentsList.get((index * 2) + 1), mask))
				.flatMap(Collection::stream)
				.collect(Collectors.toList());
		if ((sizeParents - (iterations*2)) != 0) {
			result.add(parentsList.get(sizeParents -1));
		}
		return result;
	}

	private Collection<Individual> doRecombination(Individual firstParent, Individual secondParent, List<Integer> mask) {
		Collection<Individual> result = new ArrayList<Individual>();
		if (geneticAlgorithmUtils.getProbability() <= probability) {
			List<Integer> firstGenotype = firstParent.getGenotype();
			List<Integer> secondGenotype = secondParent.getGenotype();
			List<Integer> firstSonGenotype = new ArrayList<Integer>();
			List<Integer> secondSonGenotype = new ArrayList<Integer>();
			int size = firstGenotype.size();
			for (int i = 0; i< size; i++) {
				if (mask.get(i) == 1) {
					firstSonGenotype.add(firstGenotype.get(i));
					secondSonGenotype.add(secondGenotype.get(i));
				} else {
					firstSonGenotype.add(secondGenotype.get(i));
					secondSonGenotype.add(firstGenotype.get(i));
				}
			}
			LOGGER.log(Level.INFO, "Recombine first genotype: " + firstGenotype + " with second genotype: " + secondGenotype + " with mask: " + mask.toString());
			result.add(new Individual(firstSonGenotype));
			result.add(new Individual(secondSonGenotype));
		} else {
			result.add(firstParent);
			result.add(secondParent);
		}
		return result;
	}

}
