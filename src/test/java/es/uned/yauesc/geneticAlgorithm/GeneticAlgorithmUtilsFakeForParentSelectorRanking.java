package es.uned.yauesc.geneticAlgorithm;

public class GeneticAlgorithmUtilsFakeForParentSelectorRanking extends GeneticAlgorithmUtils {

	private boolean flag = false;
	
	public double getProbability() {
		if (flag) {
			flag = false;
			return 0.625;
		} else {
			flag = true;
			return 0.125;
		}
	}
}
