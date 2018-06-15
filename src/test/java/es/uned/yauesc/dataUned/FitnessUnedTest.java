package es.uned.yauesc.dataUned;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FitnessUnedTest {


	@Test
	public void testCreateFitnessSetCorrectly() {
		int firstLevel = 1;
		int secondLevel = 6;
		int thirdLevel = 123;
		
		FitnessUned fitnessUned = new FitnessUned(firstLevel, secondLevel, thirdLevel);
		
		assertThat(fitnessUned.getFirstLevel()).isEqualTo(firstLevel);
		assertThat(fitnessUned.getSecondLevel()).isEqualTo(secondLevel);
		assertThat(fitnessUned.getThirdLevel()).isEqualTo(thirdLevel);
	}

	@Test
	public void testCompareFitness() {
		int firstFirstLevel = 0;
		int firstSecondLevel = 1;
		int firstThirdLevel = 2;
		int secondFirstLevel = 0;
		int secondSecondLevel = 1;
		int secondThirdLevel = 3;
		int thirdFirstLevel = 0;
		int thirdSecondLevel = 2;
		int thirdThirdLevel = 3;
		int fourthFirstLevel = 1;
		int fourthSecondLevel = 1;
		int fourthThirdLevel = 2;
		
		FitnessUned firstFitness = new FitnessUned(firstFirstLevel, firstSecondLevel, firstThirdLevel);
		FitnessUned secondFitness = new FitnessUned(secondFirstLevel, secondSecondLevel, secondThirdLevel);
		FitnessUned thirdFitness = new FitnessUned(thirdFirstLevel, thirdSecondLevel, thirdThirdLevel);
		FitnessUned fourthFItness = new FitnessUned(fourthFirstLevel, fourthSecondLevel, fourthThirdLevel);
		
		assertThat(firstFitness.compareTo(secondFitness)).isGreaterThan(0);
		assertThat(firstFitness.compareTo(thirdFitness)).isGreaterThan(0);
		assertThat(firstFitness.compareTo(fourthFItness)).isGreaterThan(0);
		assertThat(fourthFItness.compareTo(secondFitness)).isLessThan(0);
		assertThat(secondFitness.compareTo(secondFitness)).isEqualTo(0);
		assertThat(firstFitness.compareTo(null)).isGreaterThan(0);
	}
}
