package es.uned.yauesc.geneticAlgorithm;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Test;

public class GeneticAlgorithmSingleTest {
	
	@Test
	public void testConstructorMustEvaluatePopulationInitial() {
		Population population = mock(Population.class);
		EvaluationFunction evaluationFunction = mock(EvaluationFunction.class);
		ParentSelector parentSelector = mock(ParentSelector.class);
		RecombinationOperator recombinationOperator = mock(RecombinationOperator.class);
		MutationOperator mutationOperator = mock(MutationOperator.class);
		SurvivorSelector survivorSelector = mock(SurvivorSelector.class);
		Fitness optimalFitness = mock(Fitness.class);
		
		Individual firstIndividual = mock(Individual.class);
		Individual secondIndividual = mock(Individual.class);
		Individual thirdIndividual = mock(Individual.class);
		Individual fourthIndividual = mock(Individual.class);
		
		Collection<Individual> initialPopulation = new ArrayList<Individual>();
		initialPopulation.add(firstIndividual);
		initialPopulation.add(secondIndividual);
		initialPopulation.add(thirdIndividual);
		initialPopulation.add(fourthIndividual);
		
		when(population.getAllIndividual()).thenReturn(initialPopulation);
		
		@SuppressWarnings("unused")
		GeneticAlgorithmSingle geneticAlgorithmSingle = new GeneticAlgorithmSingle(population, evaluationFunction, parentSelector, recombinationOperator, 
				mutationOperator, survivorSelector, 1, optimalFitness);
		
		verify(evaluationFunction).evaluate(initialPopulation);
	}

	@Test
	public void testRunOneIteration() {
		Population population = mock(Population.class);
		EvaluationFunction evaluationFunction = mock(EvaluationFunction.class);
		ParentSelector parentSelector = mock(ParentSelector.class);
		RecombinationOperator recombinationOperator = mock(RecombinationOperator.class);
		MutationOperator mutationOperator = mock(MutationOperator.class);
		SurvivorSelector survivorSelector = mock(SurvivorSelector.class);
		Fitness optimalFitness = mock(Fitness.class);
		
		Individual firstIndividual = mock(Individual.class);
		Individual secondIndividual = mock(Individual.class);
		Individual thirdIndividual = mock(Individual.class);
		Individual fourthIndividual = mock(Individual.class);
		Individual firstSonIndividual = mock(Individual.class);
		Individual secondSonIndividual = mock(Individual.class);
		Individual mutatedIndividual = mock(Individual.class);
		
		Collection<Individual> initialPopulation = new ArrayList<Individual>();
		initialPopulation.add(firstIndividual);
		initialPopulation.add(secondIndividual);
		initialPopulation.add(thirdIndividual);
		initialPopulation.add(fourthIndividual);
		
		Collection<Individual> parents = new ArrayList<Individual>();
		parents.add(firstIndividual);
		parents.add(firstIndividual);
		parents.add(thirdIndividual);
		parents.add(secondIndividual);
		
		Collection<Individual> recombinatedOffspring = new ArrayList<Individual>();
		recombinatedOffspring.add(firstSonIndividual);
		recombinatedOffspring.add(secondSonIndividual);
		recombinatedOffspring.add(thirdIndividual);
		recombinatedOffspring.add(secondIndividual);
		
		Collection<Individual> mutatedOffspring = new ArrayList<Individual>();
		mutatedOffspring.add(firstSonIndividual);
		mutatedOffspring.add(secondSonIndividual);
		mutatedOffspring.add(thirdIndividual);
		mutatedOffspring.add(mutatedIndividual);
		
		Collection<Individual> evaluatedOffspring = new ArrayList<Individual>(initialPopulation);
		
		when(population.getAllIndividual()).thenReturn(initialPopulation);
		when(population.getSize()).thenReturn(initialPopulation.size());
		when(population.getMinSize()).thenReturn(initialPopulation.size());
		when(population.getBestIndividual()).thenReturn(mutatedIndividual);
		
		when(parentSelector.selectParents(initialPopulation, initialPopulation.size())).thenReturn(parents);
		
		when(recombinationOperator.recombine(parents)).thenReturn(recombinatedOffspring);
		
		when(mutationOperator.mutate(recombinatedOffspring)).thenReturn(mutatedOffspring);
		
		when(survivorSelector.getSurvivor(population, evaluatedOffspring)).thenReturn(evaluatedOffspring);
		
		when(evaluationFunction.evaluate(initialPopulation)).thenReturn(initialPopulation);
		when(evaluationFunction.evaluate(mutatedOffspring)).thenReturn(evaluatedOffspring);
		
		when(mutatedIndividual.getFitness()).thenReturn(optimalFitness);
		
		GeneticAlgorithmSingle geneticAlgorithmSingle = new GeneticAlgorithmSingle(population, evaluationFunction, parentSelector, recombinationOperator, 
				mutationOperator, survivorSelector, 1, optimalFitness);
		
		geneticAlgorithmSingle.run();
		
		verify(parentSelector).selectParents(initialPopulation, initialPopulation.size());
		
		verify(recombinationOperator).recombine(parents);
		
		verify(mutationOperator).mutate(recombinatedOffspring);
		
		verify(evaluationFunction).evaluate(mutatedOffspring);
		
		verify(survivorSelector).getSurvivor(population, evaluatedOffspring);
				
		assertThat(geneticAlgorithmSingle.isFinished()).isTrue();
		
	}
	
	@Test
	public void testGetBestSolution() {
		Population population = mock(Population.class);
		EvaluationFunction evaluationFunction = mock(EvaluationFunction.class);
		ParentSelector parentSelector = mock(ParentSelector.class);
		RecombinationOperator recombinationOperator = mock(RecombinationOperator.class);
		MutationOperator mutationOperator = mock(MutationOperator.class);
		SurvivorSelector survivorSelector = mock(SurvivorSelector.class);
		Fitness optimalFitness = mock(Fitness.class);
		
		Individual firstIndividual = mock(Individual.class);
		Individual secondIndividual = mock(Individual.class);
		Individual thirdIndividual = mock(Individual.class);
		Individual fourthIndividual = mock(Individual.class);
		
		Collection<Individual> initialPopulation = new ArrayList<Individual>();
		initialPopulation.add(firstIndividual);
		initialPopulation.add(secondIndividual);
		initialPopulation.add(thirdIndividual);
		initialPopulation.add(fourthIndividual);
		
		when(population.getAllIndividual()).thenReturn(initialPopulation);
		when(population.getBestIndividual()).thenReturn(firstIndividual);
		
		GeneticAlgorithmSingle geneticAlgorithmSingle = new GeneticAlgorithmSingle(population, evaluationFunction, parentSelector, recombinationOperator, 
				mutationOperator, survivorSelector, 1, optimalFitness);
		
		assertThat(geneticAlgorithmSingle.getSolution()).isEqualTo(firstIndividual);
	}

}
