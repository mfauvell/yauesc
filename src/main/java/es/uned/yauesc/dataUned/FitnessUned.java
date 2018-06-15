package es.uned.yauesc.dataUned;

import es.uned.yauesc.geneticAlgorithm.Fitness;

public class FitnessUned implements Fitness {
	
	private int firstLevel;
	private int secondLevel;
	private int thirdLevel;

	public FitnessUned(int firstLevel, int secondLevel, int thirdLevel) {
		this.firstLevel = firstLevel;
		this.secondLevel = secondLevel;
		this.thirdLevel = thirdLevel;
	}

	@Override
	public int compareTo(Fitness fitness) {
		if (fitness == null) {
			return 1;
		}
		FitnessUned fitnessToCompare = (FitnessUned) fitness;
		if (fitnessToCompare.getFirstLevel() == firstLevel) {
			if (fitnessToCompare.getSecondLevel() == secondLevel) {
				if (fitnessToCompare.getThirdLevel() == thirdLevel) {
					return 0;
				} else if (fitnessToCompare.getThirdLevel() < thirdLevel) {
					return -1;
				}
			} else if (fitnessToCompare.getSecondLevel() < secondLevel) {
				return -1;
			}
		} else if (fitnessToCompare.getFirstLevel() < firstLevel) {
			return -1;
		}
		return 1;
	}

	public int getFirstLevel() {
		return firstLevel;
	}

	public int getSecondLevel() {
		return secondLevel;
	}

	public int getThirdLevel() {
		return thirdLevel;
	}

}
