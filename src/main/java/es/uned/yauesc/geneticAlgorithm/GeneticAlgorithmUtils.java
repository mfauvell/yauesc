package es.uned.yauesc.geneticAlgorithm;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GeneticAlgorithmUtils {
	
	private Random randomGenerator;
	private GeneticAlgorithmConfig geneticAlgorithmConfig;
	
	public GeneticAlgorithmUtils(GeneticAlgorithmConfig geneticAlgorithmConfig) {
		randomGenerator = new Random();	
		this.geneticAlgorithmConfig = geneticAlgorithmConfig;
	}

	public double getProbability() {
		Double result = randomGenerator.nextDouble();
		return ((Math.round(result)*10000.0)/10000.0);
	}

	public int getNewGenValue() {
		return getRandomInt(geneticAlgorithmConfig.getNumberValuesGen());
	}

	public int getGenPosition() {
		return getRandomInt(geneticAlgorithmConfig.getGenotypeLong());
	}

	public int getCrossPoint() {
		return getRandomInt(geneticAlgorithmConfig.getGenotypeLong() - 1);
	}

	public List<Integer> getBinaryMask() {
		return IntStream.range(0, geneticAlgorithmConfig.getGenotypeLong())
				.parallel()
				.map(i -> getRandomInt(2))
				.boxed()
				.collect(Collectors.toList());
	}

	public int getRandomInt(int max) {
		return randomGenerator.nextInt(max);
	}

}
