package es.uned.yauesc.geneticAlgorithm;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;

class testMutationOperatorSimple {

	@Test
	public void testMutateCollectionTwoIndividualsAllGensMutated() {
		Individual firstIndividual = mock(Individual.class);
		Individual secondIndividual = mock(Individual.class);
		
		List<Integer> genotypeFirst = new ArrayList<Integer>(Arrays.asList(0, 5, 9, 3, 5));
		List<Integer> genotypeSecond = new ArrayList<Integer>(Arrays.asList(4, 3, 2, 1, 0));
		List<Integer> genotypeMutated = new ArrayList<Integer>(Arrays.asList(7, 7, 7, 7, 7));
		
		when(firstIndividual.getGenotype()).thenReturn(genotypeFirst);
		when(secondIndividual.getGenotype()).thenReturn(genotypeSecond);
		
		Collection<Individual> offspring = new ArrayList<Individual>();
		offspring.add(firstIndividual);
		offspring.add(secondIndividual);

		GeneticAlgorithmUtils geneticAlgorithmUtils = mock(GeneticAlgorithmUtils.class);
		
		when(geneticAlgorithmUtils.getProbability()).thenReturn(0.08);
		when(geneticAlgorithmUtils.getNewGenValue()).thenReturn(7);
		
		double probability = 0.10;
		
		MutationOperatorSimple mutationOperatorSimple = new MutationOperatorSimple(probability, geneticAlgorithmUtils);
		
		assertThat(mutationOperatorSimple.mutate(offspring)).hasSize(2).filteredOn(individual -> individual.getGenotype().equals(genotypeMutated)).hasSize(2);
	}
	
	@Test
	public void testMutateCollectionTwoIndividualsNoGenMutated() {
		Individual firstIndividual = mock(Individual.class);
		Individual secondIndividual = mock(Individual.class);
		
		List<Integer> genotypeFirst = new ArrayList<Integer>(Arrays.asList(0, 5, 9, 3, 5));
		List<Integer> genotypeSecond = new ArrayList<Integer>(Arrays.asList(4, 3, 2, 1, 0));
		
		when(firstIndividual.getGenotype()).thenReturn(genotypeFirst);
		when(secondIndividual.getGenotype()).thenReturn(genotypeSecond);
		
		Collection<Individual> offspring = new ArrayList<Individual>();
		offspring.add(firstIndividual);
		offspring.add(secondIndividual);

		GeneticAlgorithmUtils geneticAlgorithmUtils = mock(GeneticAlgorithmUtils.class);
		
		when(geneticAlgorithmUtils.getProbability()).thenReturn(0.5);
		
		double probability = 0.10;
		
		MutationOperatorSimple mutationOperatorSimple = new MutationOperatorSimple(probability, geneticAlgorithmUtils);
		
		assertThat(mutationOperatorSimple.mutate(offspring)).isEqualTo(offspring);
	}

}
