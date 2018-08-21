package es.uned.yauesc.geneticAlgorithm;

import java.util.Collection;

/**
 * Interface que debe implementar un operador de selección de padres
 */
public interface ParentSelector {

	/**
	 * Selecciona el número de padres pasado de la colección de individuos pasada como argumento
	 * 
	 * @param individualsPopulation	una colección de posibles padres
	 * @param number				el número de padres a devolver
	 * 
	 * @return	una colección de padres seleccionados
	 */
	public Collection<Individual> selectParents(Collection<Individual> individualsPopulation, int number);

}
