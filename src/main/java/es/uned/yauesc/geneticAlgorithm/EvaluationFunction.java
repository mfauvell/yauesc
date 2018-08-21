package es.uned.yauesc.geneticAlgorithm;

import java.util.Collection;

/**
 * Interface que permite implementar funciones de evaluación para el uso dentro de un algoritmo genético
 */
public interface EvaluationFunction {

	/**
	 * Evalua los individuos pasados como argumento y los devuelve en una nueva colección
	 * 
	 * @param individualPopulation	una colección de invidudos sin evaluar
	 * 
	 * @return	una colección de individuos evaluados
	 */
	public Collection<Individual> evaluate(Collection<Individual> individualPopulation);
	
	/**
	 * Da una edad reletativa a una máxima y minima adecuación a cada uno de los individuos pasados como argumento
	 * 
	 * @param max			máxima adecuación
	 * @param min			mínima adecuación
	 * @param offspring		colección de individuos a setear la edad
	 * 
	 * @return	una colección de individuos con la edad configurada
	 */
	public Collection<Individual> setAge(Fitness max, Fitness min, Collection<Individual> offspring);

}
