package es.uned.yauesc.geneticAlgorithm;

import java.util.Collection;

public interface ParentSelector {

	public Collection<Individual> selectParents(Collection<Individual> individualsPopulation, int number);

}
