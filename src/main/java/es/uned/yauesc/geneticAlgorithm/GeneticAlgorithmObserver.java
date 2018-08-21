package es.uned.yauesc.geneticAlgorithm;

/**
 * Interface que deben implementar las clases que quieran observar un geneticAlgorithm
 */
public interface GeneticAlgorithmObserver {

	/**
	 * Actualiza los datos procedentes del geneticAlgorithm suministrado como parámetro
	 * 
	 * @param geneticAlgorithm	geneticAlgorithm observado
	 */
	public void updateGeneticAlgorithmObserver(GeneticAlgorithm geneticAlgorithm);

}
