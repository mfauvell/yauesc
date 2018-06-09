package es.uned.yauesc.geneticAlgorithm;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Test;

class ParentSelectorTournamentTest {

	@Test
	public void testSelectTwoParentsOfFourIndividuals() {
		int numberParent = 2;
		
		Individual firstIndividual = mock(Individual.class);
		Individual secondIndividual = mock(Individual.class);
		Individual thirdIndividual = mock(Individual.class);
		Individual fourthIndividual = mock(Individual.class);
		
		Collection<Individual> collectionIndividuals = new ArrayList<Individual>();
		collectionIndividuals.add(firstIndividual);
		collectionIndividuals.add(secondIndividual);
		collectionIndividuals.add(thirdIndividual);
		collectionIndividuals.add(fourthIndividual);
		
		when(firstIndividual.compareTo(secondIndividual)).thenReturn(1);
		when(firstIndividual.compareTo(thirdIndividual)).thenReturn(1);
		when(firstIndividual.compareTo(fourthIndividual)).thenReturn(1);
		when(secondIndividual.compareTo(firstIndividual)).thenReturn(-1);
		when(secondIndividual.compareTo(thirdIndividual)).thenReturn(0);
		when(secondIndividual.compareTo(fourthIndividual)).thenReturn(1);
		when(thirdIndividual.compareTo(firstIndividual)).thenReturn(-1);
		when(thirdIndividual.compareTo(secondIndividual)).thenReturn(0);
		when(thirdIndividual.compareTo(fourthIndividual)).thenReturn(1);
		when(fourthIndividual.compareTo(firstIndividual)).thenReturn(-1);
		when(fourthIndividual.compareTo(secondIndividual)).thenReturn(-1);
		when(fourthIndividual.compareTo(thirdIndividual)).thenReturn(-1);
				
		GeneticAlgorithmConfig geneticAlgorithmConfig = mock(GeneticAlgorithmConfig.class);
		GeneticAlgorithmUtils geneticAlgorithmUtils = new GeneticAlgorithmUtilsFakeForParentSelectorTournament(geneticAlgorithmConfig);
		
		ParentSelectorTournament parentSelectorTournament = new ParentSelectorTournament(geneticAlgorithmUtils);
		
		assertThat(parentSelectorTournament.selectParents(collectionIndividuals, numberParent)).hasSize(numberParent).contains(secondIndividual, firstIndividual);
	}

}
