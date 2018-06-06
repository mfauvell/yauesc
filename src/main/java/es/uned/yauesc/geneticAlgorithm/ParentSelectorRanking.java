package es.uned.yauesc.geneticAlgorithm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ParentSelectorRanking implements ParentSelector {
	
	private GeneticAlgorithmUtils geneticAlgorithmUtils;
	private double sParameter;

	public ParentSelectorRanking(GeneticAlgorithmUtils geneticAlgorithmUtils, double sParameter) {
		this.geneticAlgorithmUtils = geneticAlgorithmUtils;
		this.sParameter = sParameter;
	}

	@Override
	public Collection<Individual> selectParents(Collection<Individual> individualsPopulation, int number) {
		ArrayList<Individual> individuals = new ArrayList<Individual>(individualsPopulation);
		ArrayList<Double> probabilities = new ArrayList<Double>(calculateAcumulatedProbabilities(individuals));
		return IntStream.range(0, number)
				.parallel()
				.mapToObj(i -> getParent(individuals, probabilities, geneticAlgorithmUtils.getProbability()))
				.collect(Collectors.toList());
	}
	
	private Individual getParent(ArrayList<Individual> individuals, ArrayList<Double> probabilities, double probability) {
		int left = 0;
		int right = individuals.size()-1;
		int med = 0;
		double medProbability = 0;
		while (left != right) {
			med = (left + right) / 2;
			medProbability = probabilities.get(med);
			if (Double.compare(medProbability, probability) > 0 ) {
				left = med + 1;
			} else if (Double.compare(medProbability, probability) < 0) {
				right = med;
			} else {
				return individuals.get(med+1);
			}
		}
		return individuals.get(left);
	}

	private Collection<Double> calculateAcumulatedProbabilities(Collection<Individual> individuals) {
		int size = individuals.size();
		int maxIndex = size -1;
		//First we obtain probabilities of each individual
		ArrayList<Double> probabilities = new ArrayList<Double>(IntStream.range(0, size)
				.parallel()
				.mapToDouble(i -> calculateProbability(i, maxIndex, size))
				.boxed()
				.collect(Collectors.toList()));
		//For each we sum all worst of it
		return IntStream.range(0, size)
				.parallel()
				.mapToDouble(i -> Math.round(IntStream.range(0, size)
						.parallel()
						.filter(j -> j > i)
						.mapToDouble(k -> 0.0 + probabilities.get(k))
						.sum()*1000.0) / 1000.0
				)
				.boxed()
				.collect(Collectors.toList());
	}
	
	private Double calculateProbability(Integer index, Integer maxIndex, Integer size) {
		Double result = (((2-sParameter)/size) + (((2*Math.abs(index-maxIndex))*(sParameter - 1))/(size * (size -1))));
		result = Math.round(result * 1000.0) / 1000.0;
		return result;
	}

}
