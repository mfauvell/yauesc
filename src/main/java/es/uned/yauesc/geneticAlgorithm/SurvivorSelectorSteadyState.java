package es.uned.yauesc.geneticAlgorithm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que representa el operador de selección por steady state donde se sustituye todos los individuos entre
 * generaciones excepto un número configurado de invidividuos elegidos por adecuación
 */
public class SurvivorSelectorSteadyState implements SurvivorSelector {

	private int survivors;
	
	private final static Logger LOGGER = Logger.getLogger(SurvivorSelectorSteadyState.class.getName());
	
	/**
	 * Constructor del operador de selección por steady state
	 * 
	 * @param survivors	el número de supervivientes entre generaciones
	 */
	public SurvivorSelectorSteadyState(int survivors) {
		this.survivors = survivors;
	}

	@Override
	public Collection<Individual> getSurvivor(Population population, Collection<Individual> offspring) {
		ArrayList<Individual> survivor = new ArrayList<Individual>(population.getBestIndividual(survivors));
		survivor.addAll(offspring);
		LOGGER.log(Level.INFO, "Survivors: " + survivor.toString());
		return survivor;
	}

}
