package es.uned.yauesc.geneticAlgorithm;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Test;

class SurvivorSelectorRoundRobinTest {

	@Test
	public void testTwoOffSpringTwoPopulationTwoNewPopulationRoundRobinSelection() {
		int max = 4;
		int populationSize = 2;
		int battleNumber = 2;
		
		Individual firstIndividual = mock(Individual.class);
		Individual secondIndividual = mock(Individual.class);
		Individual thirdIndividual = mock(Individual.class);
		Individual fourthIndividual = mock(Individual.class);
		
		when(firstIndividual.compareTo(secondIndividual)).thenReturn(1);
		when(firstIndividual.compareTo(thirdIndividual)).thenReturn(1);
		when(firstIndividual.compareTo(fourthIndividual)).thenReturn(1);
		when(secondIndividual.compareTo(firstIndividual)).thenReturn(-1);
		when(secondIndividual.compareTo(thirdIndividual)).thenReturn(-1);
		when(secondIndividual.compareTo(fourthIndividual)).thenReturn(1);
		when(secondIndividual.compareTo(secondIndividual)).thenReturn(0);
		when(thirdIndividual.compareTo(firstIndividual)).thenReturn(-1);
		when(thirdIndividual.compareTo(secondIndividual)).thenReturn(1);
		when(thirdIndividual.compareTo(fourthIndividual)).thenReturn(1);
		when(fourthIndividual.compareTo(firstIndividual)).thenReturn(-1);
		when(fourthIndividual.compareTo(secondIndividual)).thenReturn(-1);
		when(fourthIndividual.compareTo(thirdIndividual)).thenReturn(-1);
		
		Collection<Individual> populationIndividuals = new ArrayList<Individual>();
		populationIndividuals.add(firstIndividual);
		populationIndividuals.add(secondIndividual);
		
		Collection<Individual> offspring = new ArrayList<Individual>();
		offspring.add(thirdIndividual);
		offspring.add(fourthIndividual);
		
		Collection<Individual> newPopulation = new ArrayList<Individual>();
		newPopulation.add(firstIndividual);
		newPopulation.add(thirdIndividual);
		
		GeneticAlgorithmUtils geneticAlgorithmUtils = mock(GeneticAlgorithmUtils.class);
		when(geneticAlgorithmUtils.getRandomInt(max)).thenReturn(1);
		
		Population population = mock(Population.class);
		when(population.getAllIndividual()).thenReturn(populationIndividuals);
		when(population.getSize()).thenReturn(populationSize);
		
		SurvivorSelectorRoundRobin survivorSelectorRoundRobin = new SurvivorSelectorRoundRobin(geneticAlgorithmUtils, battleNumber);
		
		assertThat(survivorSelectorRoundRobin.getSurvivor(population, offspring)).isEqualTo(newPopulation);
	}

}
