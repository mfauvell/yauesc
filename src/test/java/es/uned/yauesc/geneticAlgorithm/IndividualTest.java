package es.uned.yauesc.geneticAlgorithm;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

class IndividualTest {

	private static final int[] GENOTYPE = {0,1,2,3,4,5}; 
	private static final int[] OTHERGENOTYPE = {6,7,8,9,10,11}; 
	
	@Test
	public void constructorShouldSetGenotypeAndSetEvaluatedToFalseAndAgeDefaultValorToOne() {
		Individual individual = new Individual(GENOTYPE);
		
		assertThat(individual.getGenotype()).isEqualTo(GENOTYPE);
		assertThat(individual.isEvaluated()).isFalse();
		assertThat(individual.getAge()).isEqualTo(1);
	}
	
	@Test
	public void testAddGenetypeAndEvaluatedMustBeFalse() {
		Individual individual = new Individual(GENOTYPE);
		Fitness fitness = mock(Fitness.class);
		
		individual.setFitness(fitness);
		individual.setGenotype(OTHERGENOTYPE);
		
		assertThat(individual.getGenotype()).isEqualTo(OTHERGENOTYPE);
		assertThat(individual.isEvaluated()).isFalse();
	}
	
	@Test
	public void testAddFitnessAndEvaluatesIsSetToTrue() {
		Individual individual = new Individual(GENOTYPE);
		Fitness fitness = mock(Fitness.class);
		
		individual.setFitness(fitness);
		
		assertThat(individual.getFitness()).isEqualTo(fitness);
		assertThat(individual.isEvaluated()).isTrue();
	}
	
	@Test
	public void testAddAge() {
		Individual individual = new Individual(GENOTYPE);
		
		individual.setAge(5);
		
		assertThat(individual.getAge()).isEqualTo(5);
	}

	@Test
	public void testDecrementAge() {
		Individual individual = new Individual(GENOTYPE);
		
		individual.setAge(5);
		individual.decAge();
		
		assertThat(individual.getAge()).isEqualTo(4);
	}
	
	@Test
	public void testAgeNotNegative() {
		Individual individual = new Individual(GENOTYPE);
		
		individual.setAge(-1);
		
		assertThat(individual.getAge()).isEqualTo(0);
		
		individual.setAge(0);
		individual.decAge();
		
		assertThat(individual.getAge()).isEqualTo(0);
	}
	
	@Test
	public void testExcpetionIfGetFitnessAndIsNotEvaluated() {
		Individual individual = new Individual(GENOTYPE);
		
		assertThrows(UnsupportedOperationException.class,()->{individual.getFitness();},"Here should be throw a UnsupportedOperationException because evaluated is false");
	}
	
	@Test
	public void testCompareTwoIndividuals() {
		Individual bestIndividual = new Individual(GENOTYPE);
		Individual worstIndividual = new Individual(OTHERGENOTYPE);
		Individual equealBestIndividual = new Individual(GENOTYPE);
		
		Fitness bestFitness = mock(Fitness.class);
		Fitness worstFitness = mock(Fitness.class);
		
		when(bestFitness.compareTo(worstFitness)).thenReturn(1);
		when(bestFitness.compareTo(bestFitness)).thenReturn(0);
		when(worstFitness.compareTo(bestFitness)).thenReturn(-1);
		
		bestIndividual.setFitness(bestFitness);
		worstIndividual.setFitness(worstFitness);
		equealBestIndividual.setFitness(bestFitness);
		
		assertThat(bestIndividual.compareTo(worstIndividual)).isGreaterThan(0);
		assertThat(bestIndividual.compareTo(equealBestIndividual)).isEqualTo(0);
		assertThat(worstIndividual.compareTo(bestIndividual)).isLessThan(0);
	}
	
}
