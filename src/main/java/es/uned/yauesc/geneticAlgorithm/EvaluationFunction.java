package es.uned.yauesc.geneticAlgorithm;

import java.util.Collection;

public interface EvaluationFunction {

	public Collection<Individual> evaluate(Collection<Individual> individualPopulation);

}
