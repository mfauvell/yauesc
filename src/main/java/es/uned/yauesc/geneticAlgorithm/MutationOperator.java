package es.uned.yauesc.geneticAlgorithm;

import java.util.Collection;

/**
 * Interface que debe implementar un operador de mutación
 */
public interface MutationOperator {

	/**
	 * Hace la mutación de la colección de invididuos pasada como argumento y devuelve una colección con los individuos mutados y aquellos que no lo han sido
	 * 
	 * @param offspring	la colección de individuos a mutar
	 * 	
	 * @return	una colección de individuos
	 */
	public Collection<Individual> mutate(Collection<Individual> offspring);

}
