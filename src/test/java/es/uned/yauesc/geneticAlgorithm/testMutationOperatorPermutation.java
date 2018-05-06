package es.uned.yauesc.geneticAlgorithm;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;

class testMutationOperatorPermutation {

	@SuppressWarnings("unchecked")
	@Test
	public void testMutateFourIndividualAllMutated() {
		Individual firstIndividual = mock(Individual.class);
		Individual secondIndividual = mock(Individual.class);
		Individual thirdIndividual = mock(Individual.class);
		Individual fourthIndividual = mock(Individual.class);
		
		List<Integer> genotypeFirst = new ArrayList<Integer>(Arrays.asList(0, 5, 9, 3, 5));
		List<Integer> genotypeSecond = new ArrayList<Integer>(Arrays.asList(1, 3, 2, 1, 4));
		List<Integer> genotypeThird = new ArrayList<Integer>(Arrays.asList(6, 5, 9, 3, 9));
		List<Integer> genotypeFourth = new ArrayList<Integer>(Arrays.asList(3, 3, 2, 1, 8));
		
		List<Integer> mutatedGenotypeFirst = new ArrayList<Integer>(Arrays.asList(5, 5, 9, 3, 0));
		List<Integer> mutatedGenotypeSecond = new ArrayList<Integer>(Arrays.asList(4, 3, 2, 1, 1));
		List<Integer> mutatedGenotypeThird = new ArrayList<Integer>(Arrays.asList(9, 5, 9, 3, 6));
		List<Integer> mutatedGenotypeFourth = new ArrayList<Integer>(Arrays.asList(8, 3, 2, 1, 3));

		when(firstIndividual.getGenotype()).thenReturn(genotypeFirst);
		when(secondIndividual.getGenotype()).thenReturn(genotypeSecond);
		when(thirdIndividual.getGenotype()).thenReturn(genotypeThird);
		when(fourthIndividual.getGenotype()).thenReturn(genotypeFourth);
		
		Collection<Individual> offspring = new ArrayList<Individual>();
		offspring.add(firstIndividual);
		offspring.add(secondIndividual);
		offspring.add(thirdIndividual);
		offspring.add(fourthIndividual);
		
		GeneticAlgorithmUtils geneticAlgorithmUtils = new fakeGeneticAlgorithmUtils();
		
		double probability = 1;
		
		MutationOperatorPermutation mutationOperatorPermutation = new MutationOperatorPermutation(probability, geneticAlgorithmUtils);
		
		assertThat(mutationOperatorPermutation.mutate(offspring)).hasSize(4).extracting(individual->individual.getGenotype()).
			contains(mutatedGenotypeFirst, mutatedGenotypeSecond, mutatedGenotypeThird, mutatedGenotypeFourth);
	}
	
	@Test
	public void testMutateFourIndividualNoMutated() {
		Individual firstIndividual = mock(Individual.class);
		Individual secondIndividual = mock(Individual.class);
		Individual thirdIndividual = mock(Individual.class);
		Individual fourthIndividual = mock(Individual.class);
		
		List<Integer> genotypeFirst = new ArrayList<Integer>(Arrays.asList(0, 5, 9, 3, 5));
		List<Integer> genotypeSecond = new ArrayList<Integer>(Arrays.asList(1, 3, 2, 1, 4));
		List<Integer> genotypeThird = new ArrayList<Integer>(Arrays.asList(6, 5, 9, 3, 9));
		List<Integer> genotypeFourth = new ArrayList<Integer>(Arrays.asList(3, 3, 2, 1, 8));

		when(firstIndividual.getGenotype()).thenReturn(genotypeFirst);
		when(secondIndividual.getGenotype()).thenReturn(genotypeSecond);
		when(thirdIndividual.getGenotype()).thenReturn(genotypeThird);
		when(fourthIndividual.getGenotype()).thenReturn(genotypeFourth);
		
		Collection<Individual> offspring = new ArrayList<Individual>();
		offspring.add(firstIndividual);
		offspring.add(secondIndividual);
		offspring.add(thirdIndividual);
		offspring.add(fourthIndividual);
		
		GeneticAlgorithmUtils geneticAlgorithmUtils = new fakeGeneticAlgorithmUtils();
		
		double probability = 0;
		
		MutationOperatorPermutation mutationOperatorPermutation = new MutationOperatorPermutation(probability, geneticAlgorithmUtils);
		
		assertThat(mutationOperatorPermutation.mutate(offspring)).isEqualTo(offspring);
	}

}
