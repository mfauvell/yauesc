package es.uned.yauesc.geneticAlgorithm;

import java.util.Collection;

public interface SurvivorSelector {

	public Collection<Individual> getSurvivor(Population population, Collection<Individual> offspring);

}
