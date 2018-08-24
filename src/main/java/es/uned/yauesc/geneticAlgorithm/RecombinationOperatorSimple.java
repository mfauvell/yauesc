package es.uned.yauesc.geneticAlgorithm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Clase que representa el operador de recombinacion simple de un algoritmo genético donde se crean dos hijos a partir de dos padres mezclando
 * genotipo en un punto de corte dado.
 */
public class RecombinationOperatorSimple implements RecombinationOperator{

	private double probability;
	private GeneticAlgorithmUtils geneticAlgorithmUtils;
	
	private final static Logger LOGGER = Logger.getLogger(RecombinationOperatorSimple.class.getName());
	
	/**
	 * Constructor para el operador de recombinación simple
	 * 
	 * @param probability				probabilidad de que se produzca una recombinación
	 * @param geneticAlgorithmUtils		utilidades auxiliares de caracter estocástico
	 */
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
			for (int i = 0; i< size; i++) {
				if (i <= crossPoint) {
					firstSonGenotype.add(firstGenotype.get(i));
					secondSonGenotype.add(secondGenotype.get(i));
				} else {
					firstSonGenotype.add(secondGenotype.get(i));
					secondSonGenotype.add(firstGenotype.get(i));
				}
			}
			LOGGER.log(Level.INFO, "Recombine first genotype: " + firstGenotype + " with second genotype: " + secondGenotype + " at crosspoint: " + crossPoint);
			result.add(new Individual(firstSonGenotype));
			result.add(new Individual(secondSonGenotype));
		} else {
			result.add(firstParent);
			result.add(secondParent);
		}
		return result;
	}

}
