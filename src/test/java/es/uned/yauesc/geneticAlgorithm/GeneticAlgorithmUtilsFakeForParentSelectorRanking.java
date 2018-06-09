package es.uned.yauesc.geneticAlgorithm;

public class GeneticAlgorithmUtilsFakeForParentSelectorRanking extends GeneticAlgorithmUtils {

	public GeneticAlgorithmUtilsFakeForParentSelectorRanking(GeneticAlgorithmConfig geneticAlgorithmConfig) {
		super(geneticAlgorithmConfig);
	}

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
