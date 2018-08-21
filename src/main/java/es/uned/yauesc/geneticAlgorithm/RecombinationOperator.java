package es.uned.yauesc.geneticAlgorithm;

import java.util.Collection;

/**
 * Interface que debe implementar un operador de cruce
 */
public interface RecombinationOperator {

	/**
	 * Cruza dos de los padres tomados a parejas y obtiene dos hijos
	 * 
	 * @param parents	los padres de los cuales hacer parejas
	 * 
	 * @return	un colección de individuos compuesta de los hijos y los padres que no han conseguido reproducirse
	 */
	public Collection<Individual> recombine(Collection<Individual> parents);

}
