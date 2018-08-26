package es.uned.yauesc.geneticAlgorithm;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Clase que proporciona utilidades estocásticas para la ejecución de los algoritmos genéticos
 */
public class GeneticAlgorithmUtils {
	
	private Random randomGenerator;
	private GeneticAlgorithmConfig geneticAlgorithmConfig;
	
	/**
	 * Constructor por defecto 
	 * 
	 * @param geneticAlgorithmConfig	la configuración básica de un individuo en el algoritmo genético
	 */
	public GeneticAlgorithmUtils(GeneticAlgorithmConfig geneticAlgorithmConfig) {
		randomGenerator = new Random();	
		this.geneticAlgorithmConfig = geneticAlgorithmConfig;
	}

	/**
	 * Obtiene una probabilidad
	 * 
	 * @return	un número entre 0 y 1 inclusives
	 */
	public double getProbability() {
		Double result = randomGenerator.nextDouble();
		return ((Math.round(result)*100000.0)/100000.0);
	}

	/**
	 * Obtiene un valor aleatorio y correcto para un gen
	 * 
	 * @return	el valor de un gen
	 */
	public int getNewGenValue() {
		return getRandomInt(geneticAlgorithmConfig.getNumberValuesGen());
	}

	/**
	 * Obtiene una posición aleatoria dentro del genotipo
	 * 
	 * @return	una posición en el genotipo
	 */
	public int getGenPosition() {
		return getRandomInt(geneticAlgorithmConfig.getGenotypeLong());
	}

	/**
	 * Obtiene un punto de cruce aleatorio en el genotipo	
	 * 
	 * @return	obtiene un punto de cruce en el genotipo
	 */
	public int getCrossPoint() {
		return getRandomInt(geneticAlgorithmConfig.getGenotypeLong() - 1);
	}

	/**
	 * Obtiene una mascara binaria aleatoria del tamaño del genotipo
	 * 
	 * @return	una mascara binaria del tamaño del genotipo
	 */
	public List<Integer> getBinaryMask() {
		return IntStream.range(0, geneticAlgorithmConfig.getGenotypeLong())
				.parallel()
				.map(i -> getRandomInt(2))
				.boxed()
				.collect(Collectors.toList());
	}

	/**
	 * Obtiene un número aleatorio entre 0 y el parámatro pasado
	 * 
	 * @param max	el limite máximo para obtener el número aleatorio
	 * 
	 * @return	un número aleatorio
	 */
	public int getRandomInt(int max) {
		return randomGenerator.nextInt(max);
	}

}
