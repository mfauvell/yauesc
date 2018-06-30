package es.uned.yauesc.dataUned;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import es.uned.yauesc.geneticAlgorithm.Individual;

class EvaluationFunctionUnedTest {

	@Test
	public void testSetAgeForFiveIndividuals() {
		Individual firstIndividual = mock(Individual.class);
		Individual secondIndividual = mock(Individual.class);
		Individual thirdIndividual = mock(Individual.class);
		Individual fourthIndividual = mock(Individual.class);
		Individual fifthIndividual = mock(Individual.class);
		
		List<Individual> offspring = new ArrayList<>();
		offspring.add(firstIndividual);
		offspring.add(secondIndividual);
		offspring.add(thirdIndividual);
		offspring.add(fourthIndividual);
		offspring.add(fifthIndividual);
		
		FitnessUned firstFitness = mock(FitnessUned.class);
		FitnessUned secondFitness = mock(FitnessUned.class);
		FitnessUned thirdFitness = mock(FitnessUned.class);
		FitnessUned fourthFitness = mock(FitnessUned.class);
		FitnessUned fifthFitness = mock(FitnessUned.class);
		
		FitnessUned maxFitness = mock(FitnessUned.class);
		FitnessUned minFitness = mock(FitnessUned.class);
		
		DataUned dataUned = mock(DataUned.class);
		
		when(firstIndividual.getFitness()).thenReturn(firstFitness);
		when(secondIndividual.getFitness()).thenReturn(secondFitness);
		when(thirdIndividual.getFitness()).thenReturn(thirdFitness);
		when(fourthIndividual.getFitness()).thenReturn(fourthFitness);
		when(fifthIndividual.getFitness()).thenReturn(fifthFitness);
		
		when(maxFitness.getFirstLevel()).thenReturn(1);
		when(maxFitness.getSecondLevel()).thenReturn(0);
		when(maxFitness.getThirdLevel()).thenReturn(50);
		
		when(minFitness.getFirstLevel()).thenReturn(11);
		when(minFitness.getSecondLevel()).thenReturn(0);
		when(minFitness.getThirdLevel()).thenReturn(100);
		
		when(firstFitness.getFirstLevel()).thenReturn(1);
		when(firstFitness.getSecondLevel()).thenReturn(40);
		when(firstFitness.getThirdLevel()).thenReturn(1);
		
		when(secondFitness.getFirstLevel()).thenReturn(2);
		when(secondFitness.getSecondLevel()).thenReturn(0);
		when(secondFitness.getThirdLevel()).thenReturn(70);
		
		when(thirdFitness.getFirstLevel()).thenReturn(4);
		when(thirdFitness.getSecondLevel()).thenReturn(0);
		when(thirdFitness.getThirdLevel()).thenReturn(61);
		
		when(fourthFitness.getFirstLevel()).thenReturn(4);
		when(fourthFitness.getSecondLevel()).thenReturn(0);
		when(fourthFitness.getThirdLevel()).thenReturn(63);
		
		when(fifthFitness.getFirstLevel()).thenReturn(8);
		when(fifthFitness.getSecondLevel()).thenReturn(297);
		when(fifthFitness.getThirdLevel()).thenReturn(700);
		
		EvaluationFunctionUned evaluationFunctionUned = new EvaluationFunctionUned(dataUned);
		
		assertThat(evaluationFunctionUned.setAge(maxFitness, minFitness, offspring)).isEqualTo(offspring);
		verify(firstIndividual).setAge(5);
		verify(secondIndividual).setAge(4);
		verify(thirdIndividual).setAge(3);
		verify(fourthIndividual).setAge(2);
		verify(fifthIndividual).setAge(1);
	}

}
