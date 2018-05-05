package es.uned.yauesc.geneticAlgorithm;

public interface GeneticAlgorithm extends Runnable {

	public boolean isFinished();

	public Individual getSolution();

	public void registerObserver(GeneticAlgorithmObserver geneticAlgorithmObserver);

	public void notifyObservers();

	public void removeObserver(GeneticAlgorithmObserver geneticAlgorithmObserver);
}