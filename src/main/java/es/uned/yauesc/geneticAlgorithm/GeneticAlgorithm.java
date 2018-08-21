package es.uned.yauesc.geneticAlgorithm;

/**
 * Interface que aglutina que es un algorítmo genético
 */
public interface GeneticAlgorithm extends Runnable {

	/**
	 * Comprueba si está terminada la ejecución del algoritmo genético
	 * 
	 * @return	true si esta terminada
	 */
	public boolean isFinished();
	
	/**
	 * Para la ejecución del algoritmo genético
	 */
	public void stop();

	/**
	 * Obtiene la mejor solución encontrada hsta el momento
	 * 
	 * @return	el individuo solución
	 */
	public Individual getSolution();
	
	/**
	 * Borra la solución encontradad
	 */
	public void removeSolution();

	/**
	 * Registra un observador para la actividad del algoritmo genético
	 * 
	 * @param geneticAlgorithmObserver	el observador para ser añadido
	 */
	public void registerObserver(GeneticAlgorithmObserver geneticAlgorithmObserver);

	/**
	 * Notifica a los observadores de un cambio en el algoritmo genético
	 */
	public void notifyObservers();

	/**
	 * Elimina un observador del algoritmo genético
	 * 
	 * @param geneticAlgorithmObserver	el observador a eliminar
	 */
	public void removeObserver(GeneticAlgorithmObserver geneticAlgorithmObserver);
	
}