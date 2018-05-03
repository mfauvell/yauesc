package es.uned.yauesc.geneticAlgorithm;

public interface GeneticAlgorithm extends Runnable {

	public boolean isFinished();

	public Individual getSolution();

}
