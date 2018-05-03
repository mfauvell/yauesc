package es.uned.yauesc.geneticAlgorithm;

import java.util.Collection;

public interface MutationOperator {

	public Collection<Individual> mutate(Collection<Individual> offspring);

}
