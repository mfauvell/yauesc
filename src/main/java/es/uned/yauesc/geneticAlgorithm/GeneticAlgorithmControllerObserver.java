package es.uned.yauesc.geneticAlgorithm;

/**
 * Interface que deben implementar los observadores del GeneticAlgorithmController
 */
public interface GeneticAlgorithmControllerObserver {

	/**
	 * Actualización de los datos provenientes del geneticAlgorithmController pasado como parámetro
	 * 
	 * @param geneticAlgorithmController	geneticAlgorithmController del cual actualizar la información
	 */
	public void updateGeneticAlgorithmControllerObserver(GeneticAlgorithmController geneticAlgorithmController);

}
