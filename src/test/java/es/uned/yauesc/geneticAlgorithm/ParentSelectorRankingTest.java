package es.uned.yauesc.geneticAlgorithm;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collection;
import org.junit.jupiter.api.Test;

class ParentSelectorRankingTest {

	@Test
	void testSelectTwoParentsOfFourIndividuals() {
		int numberParent = 2;
		double sParameter = 1.5;
		
		Individual firstIndividual = mock(Individual.class);
		Individual secondIndividual = mock(Individual.class);
		Individual thirdIndividual = mock(Individual.class);
		Individual fourthIndividual = mock(Individual.class);
		
		Collection<Individual> collectionIndividuals = new ArrayList<Individual>();
		collectionIndividuals.add(firstIndividual);
		collectionIndividuals.add(secondIndividual);
		collectionIndividuals.add(thirdIndividual);
		collectionIndividuals.add(fourthIndividual);
				
		GeneticAlgorithmUtils geneticAlgorithmUtils = new GeneticAlgorithmUtilsFakeForParentSelectorRanking();
		
		ParentSelectorRanking parentSelectorRanking = new ParentSelectorRanking(geneticAlgorithmUtils, sParameter);
		
		assertThat(parentSelectorRanking.selectParents(collectionIndividuals, numberParent)).hasSize(numberParent).contains(secondIndividual, fourthIndividual);	
	}

}
