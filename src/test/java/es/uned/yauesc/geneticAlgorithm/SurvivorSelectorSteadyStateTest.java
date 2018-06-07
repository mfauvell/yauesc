package es.uned.yauesc.geneticAlgorithm;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Test;

class SurvivorSelectorSteadyStateTest {

	@Test
	void testPopulationOfFourOffspringOfTwoSelectorSurvivorSteadyState() {
		int survivors = 2;
		
		Individual firstIndividual = mock(Individual.class);
		Individual secondIndividual = mock(Individual.class);
		Individual thirdIndividual = mock(Individual.class);
		Individual fourthIndividual = mock(Individual.class);
		
		Collection<Individual> bestIndividualPopulation = new ArrayList<Individual>();
		bestIndividualPopulation.add(firstIndividual);
		bestIndividualPopulation.add(secondIndividual);
		
		Collection<Individual> offspring = new ArrayList<Individual>();
		offspring.add(thirdIndividual);
		offspring.add(fourthIndividual);
		
		Collection<Individual> newIndividualPopulation = new ArrayList<Individual>();
		newIndividualPopulation.add(firstIndividual);
		newIndividualPopulation.add(secondIndividual);
		newIndividualPopulation.add(thirdIndividual);
		newIndividualPopulation.add(fourthIndividual);
		
		Population population = mock(Population.class);
		when(population.getBestIndividual(survivors)).thenReturn(bestIndividualPopulation);
		
		SurvivorSelectorSteadyState survivorSelectorSteadyState = new SurvivorSelectorSteadyState(survivors);
		
		assertThat(survivorSelectorSteadyState.getSurvivor(population, offspring)).isEqualTo(newIndividualPopulation);
	}

}
