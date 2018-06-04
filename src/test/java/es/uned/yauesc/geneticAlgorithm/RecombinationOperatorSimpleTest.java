package es.uned.yauesc.geneticAlgorithm;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;

class RecombinationOperatorSimpleTest {

	@SuppressWarnings("unchecked")
	@Test
	public void testRecombinationToAllPairsAllMade() {
		Individual firstIndividual = mock(Individual.class);
		Individual secondIndividual = mock(Individual.class);
		Individual thirdIndividual = mock(Individual.class);
		Individual fourthIndividual = mock(Individual.class);
		
		List<Integer> genotypeFirst = new ArrayList<Integer>(Arrays.asList(0, 5, 9, 3, 5));
		List<Integer> genotypeSecond = new ArrayList<Integer>(Arrays.asList(4, 3, 2, 1, 0));
		List<Integer> genotypeThird = new ArrayList<Integer>(Arrays.asList(3, 4, 8, 9, 2));
		List<Integer> genotypeFourth = new ArrayList<Integer>(Arrays.asList(6, 9, 7, 7, 2));
		
		List<Integer> genotypeFirstSon = new ArrayList<Integer>(Arrays.asList(0, 5, 9, 1, 0));
		List<Integer> genotypeSecondSon = new ArrayList<Integer>(Arrays.asList(4, 3, 2, 3, 5));
		List<Integer> genotypeThirdSon = new ArrayList<Integer>(Arrays.asList(3, 4, 8, 7, 2));
		List<Integer> genotypeFourthSon = new ArrayList<Integer>(Arrays.asList(6, 9, 7, 9, 2));
		
		when(firstIndividual.getGenotype()).thenReturn(genotypeFirst);
		when(secondIndividual.getGenotype()).thenReturn(genotypeSecond);
		when(thirdIndividual.getGenotype()).thenReturn(genotypeThird);
		when(fourthIndividual.getGenotype()).thenReturn(genotypeFourth);
		
		Collection<Individual> parents = new ArrayList<Individual>();
		parents.add(firstIndividual);
		parents.add(secondIndividual);
		parents.add(thirdIndividual);
		parents.add(fourthIndividual);
		
        GeneticAlgorithmUtils geneticAlgorithmUtils = new GeneticAlgorithmUtilsFake();
		
		double probability = 1;
		
		RecombinationOperatorSimple recombinationOperatorSimple = new RecombinationOperatorSimple(probability, geneticAlgorithmUtils);
		
		assertThat(recombinationOperatorSimple.recombine(parents)).hasSize(4).extracting(individual->individual.getGenotype()).
			contains(genotypeFirstSon, genotypeSecondSon, genotypeThirdSon, genotypeFourthSon);
	}

	@Test
	public void testRecombinationToAllPairsNoneMade() {
		Individual firstIndividual = mock(Individual.class);
		Individual secondIndividual = mock(Individual.class);
		Individual thirdIndividual = mock(Individual.class);
		Individual fourthIndividual = mock(Individual.class);
		
		List<Integer> genotypeFirst = new ArrayList<Integer>(Arrays.asList(0, 5, 9, 3, 5));
		List<Integer> genotypeSecond = new ArrayList<Integer>(Arrays.asList(4, 3, 2, 1, 0));
		List<Integer> genotypeThird = new ArrayList<Integer>(Arrays.asList(3, 4, 8, 9, 2));
		List<Integer> genotypeFourth = new ArrayList<Integer>(Arrays.asList(6, 9, 7, 7, 2));
		
		when(firstIndividual.getGenotype()).thenReturn(genotypeFirst);
		when(secondIndividual.getGenotype()).thenReturn(genotypeSecond);
		when(thirdIndividual.getGenotype()).thenReturn(genotypeThird);
		when(fourthIndividual.getGenotype()).thenReturn(genotypeFourth);
		
		Collection<Individual> parents = new ArrayList<Individual>();
		parents.add(firstIndividual);
		parents.add(secondIndividual);
		parents.add(thirdIndividual);
		parents.add(fourthIndividual);
		
        GeneticAlgorithmUtils geneticAlgorithmUtils = new GeneticAlgorithmUtilsFake();
		
		double probability = 0;
		
		RecombinationOperatorSimple recombinationOperatorSimple = new RecombinationOperatorSimple(probability, geneticAlgorithmUtils);
		
		assertThat(recombinationOperatorSimple.recombine(parents)).isEqualTo(parents);
	}
	
}