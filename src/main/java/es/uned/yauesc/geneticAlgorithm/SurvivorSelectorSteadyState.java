package es.uned.yauesc.geneticAlgorithm;

import java.util.ArrayList;
import java.util.Collection;

public class SurvivorSelectorSteadyState implements SurvivorSelector {

	private int survivors;
	
	public SurvivorSelectorSteadyState(int survivors) {
		this.survivors = survivors;
	}

	@Override
	public Collection<Individual> getSurvivor(Population population, Collection<Individual> offspring) {
		ArrayList<Individual> survivorsPopulation = new ArrayList<Individual>(population.getBestIndividual(survivors));
		survivorsPopulation.addAll(offspring);
		return survivorsPopulation;
	}

}
