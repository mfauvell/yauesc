package es.uned.yauesc.geneticAlgorithm;

import java.util.Collection;

public interface RecombinationOperator {

	public Collection<Individual> recombine(Collection<Individual> parents);

}
