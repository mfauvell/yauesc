package es.uned.yauesc.geneticAlgorithm;

public class GeneticAlgorithmUtilsFakeForParentSelectorTournament extends GeneticAlgorithmUtils {
	
	private int count = 0;
	
	@Override
	public int getRandomInt(int max) {
		int result = 0;
		if ( count == 0) {
			result = 1;
		} else if (count == 1) {
			result = 3;
		} else if (count == 2) {
			result = 3;
		} else {
			result = 0;
		}
		count = count +1;
		return result;
	}

}
