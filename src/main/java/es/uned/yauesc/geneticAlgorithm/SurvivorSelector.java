package es.uned.yauesc.geneticAlgorithm;

import java.util.Collection;

/**
 * Interface que debe implementar un operador de selección de supervivientes
 */
public interface SurvivorSelector {

	/**
	 * Selecciona el número de supevivietnes aduecado para la población pasada añadiendo además la colección de nuevos individuos candidatos
	 * 
	 * @param population	población base para la selección de supervivientes
	 * @param offspring		colección de candidatos a formar parte de la nueva población
	 *
	 * @return	una colección de supervivientes marcado por las características de la población
	 */
	public Collection<Individual> getSurvivor(Population population, Collection<Individual> offspring);

}
