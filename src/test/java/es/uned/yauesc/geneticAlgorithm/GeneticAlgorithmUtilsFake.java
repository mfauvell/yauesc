package es.uned.yauesc.geneticAlgorithm;

public class GeneticAlgorithmUtilsFake extends GeneticAlgorithmUtils {
	
	private boolean flag = false;
	
	public double getProbability() {
		return 0.15;
	}
	
	public int getGenPosition() {
		if (flag) {
			flag = false;
			return 4;
		} else {
			flag = true;
			return 0;
		}
	}
}
