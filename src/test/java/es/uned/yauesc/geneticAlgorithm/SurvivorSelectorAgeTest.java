package es.uned.yauesc.geneticAlgorithm;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Test;

class SurvivorSelectorAgeTest {

	@Test
	public void testAgedPopulationSurvivorOffspringPlusSurvivorsLessEqualMaxPopulation() {
		int populationMaxSize = 6;
		
		Fitness maxFitness = mock(Fitness.class);
		Fitness minFitness = mock(Fitness.class);
		
		Individual firstIndividual = mock(Individual.class);
		Individual secondIndividual = mock(Individual.class);
		Individual thirdIndividual = mock(Individual.class);
		Individual fourthIndividual = mock(Individual.class);
		
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
		
		when(firstIndividual.getFitness()).thenReturn(maxFitness);
		when(fourthIndividual.getFitness()).thenReturn(minFitness);
		
		when(firstIndividual.getAge()).thenReturn(2);
		when(secondIndividual.getAge()).thenReturn(0);
		
		Collection<Individual> populationIndividuals = new ArrayList<Individual>();
		populationIndividuals.add(firstIndividual);
		populationIndividuals.add(secondIndividual);
		
		Collection<Individual> offspring = new ArrayList<Individual>();
		offspring.add(thirdIndividual);
		offspring.add(fourthIndividual);
		
		Collection<Individual> newPopulation = new ArrayList<Individual>();
		newPopulation.add(firstIndividual);
		newPopulation.add(thirdIndividual);
		newPopulation.add(fourthIndividual);
		
		Population population = mock(Population.class);
		when(population.getAllIndividual()).thenReturn(populationIndividuals);
		when(population.getMaxSize()).thenReturn(populationMaxSize);
		
		EvaluationFunction evaluationFunction = mock(EvaluationFunction.class);
		when(evaluationFunction.setAge(maxFitness, minFitness, offspring)).thenReturn(offspring);
		
		SurvivorSelectorAge survivorSelectorAge = new SurvivorSelectorAge(evaluationFunction);
		
		assertThat(survivorSelectorAge.getSurvivor(population, offspring)).isEqualTo(newPopulation);
	}
	
	@Test
	public void testAgedPopulationSurvivorOffspringPlusSurvivorsGreaterMaxPopulation() {
		int populationMaxSize = 3;
		
		Fitness maxFitness = mock(Fitness.class);
		Fitness minFitness = mock(Fitness.class);
		
		Individual firstIndividual = mock(Individual.class);
		Individual secondIndividual = mock(Individual.class);
		Individual thirdIndividual = mock(Individual.class);
		Individual fourthIndividual = mock(Individual.class);
		
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
		
		when(firstIndividual.getFitness()).thenReturn(maxFitness);
		when(fourthIndividual.getFitness()).thenReturn(minFitness);
		
		when(firstIndividual.getAge()).thenReturn(2);
		when(secondIndividual.getAge()).thenReturn(1);
		
		Collection<Individual> populationIndividuals = new ArrayList<Individual>();
		populationIndividuals.add(firstIndividual);
		populationIndividuals.add(secondIndividual);
		
		Collection<Individual> offspring = new ArrayList<Individual>();
		offspring.add(thirdIndividual);
		offspring.add(fourthIndividual);
		
		Collection<Individual> newPopulation = new ArrayList<Individual>();
		newPopulation.add(firstIndividual);
		newPopulation.add(secondIndividual);
		newPopulation.add(thirdIndividual);
		
		Population population = mock(Population.class);
		when(population.getAllIndividual()).thenReturn(populationIndividuals);
		when(population.getMaxSize()).thenReturn(populationMaxSize);
		
		EvaluationFunction evaluationFunction = mock(EvaluationFunction.class);
		when(evaluationFunction.setAge(maxFitness, minFitness, offspring)).thenReturn(offspring);
		
		SurvivorSelectorAge survivorSelectorAge = new SurvivorSelectorAge(evaluationFunction);
		
		assertThat(survivorSelectorAge.getSurvivor(population, offspring)).isEqualTo(newPopulation);
	}

}
