package es.uned.yauesc.geneticAlgorithm;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class GeneticAlgorithmSingleTest {
	
	@Test
	public void testConstructorMustEvaluatePopulationInitialAndSetInitialSolutionAndDefaultOptimalAndFinishedValues() {
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
				mutationOperator, survivorSelector, 1, optimalFitness, initialPopulation.size());
		
		verify(evaluationFunction).evaluate(initialPopulation);
		assertThat(geneticAlgorithmSingle.getSolution()).isEqualTo(firstIndividual);
		assertThat(geneticAlgorithmSingle.isFinished()).isFalse();
		assertThat(geneticAlgorithmSingle.foundOptimal()).isFalse();
	}

	@Test
	public void testRunOneIterationAndChangeSolutionToBestOfGenerationAndOptimalFoundCheckSizeOffspringSet() {
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
		when(population.getMinSize()).thenReturn(2);
		Answer<Individual> answer = new Answer<Individual>() {
			private int count = 0;
			
			public Individual answer(InvocationOnMock invocation) {
				if (count++ == 1)
					return mutatedIndividual;
				return firstIndividual;
			}
		};
		when(population.getBestIndividual()).thenAnswer(answer);
		
		when(parentSelector.selectParents(initialPopulation, 2)).thenReturn(parents);
		
		when(recombinationOperator.recombine(parents)).thenReturn(recombinatedOffspring);
		
		when(mutationOperator.mutate(recombinatedOffspring)).thenReturn(mutatedOffspring);
		
		when(survivorSelector.getSurvivor(population, evaluatedOffspring)).thenReturn(evaluatedOffspring);
		
		when(evaluationFunction.evaluate(initialPopulation)).thenReturn(initialPopulation);
		when(evaluationFunction.evaluate(mutatedOffspring)).thenReturn(evaluatedOffspring);
		
		when(mutatedIndividual.getFitness()).thenReturn(optimalFitness);
		when(mutatedIndividual.compareTo(firstIndividual)).thenReturn(1);
		
		when(optimalFitness.compareTo(optimalFitness)).thenReturn(0);
		
		GeneticAlgorithmSingle geneticAlgorithmSingle = new GeneticAlgorithmSingle(population, evaluationFunction, parentSelector, recombinationOperator, 
				mutationOperator, survivorSelector, 1, optimalFitness, 2);
		
		assertThat(geneticAlgorithmSingle.getSolution()).isEqualTo(firstIndividual);
		
		geneticAlgorithmSingle.run();
		
		verify(parentSelector).selectParents(initialPopulation, 2);
		
		verify(recombinationOperator).recombine(parents);
		
		verify(mutationOperator).mutate(recombinatedOffspring);
		
		verify(evaluationFunction).evaluate(mutatedOffspring);
		
		verify(survivorSelector).getSurvivor(population, evaluatedOffspring);
				
		assertThat(geneticAlgorithmSingle.isFinished()).isTrue();
		assertThat(geneticAlgorithmSingle.getSolution()).isEqualTo(mutatedIndividual);
		assertThat(geneticAlgorithmSingle.foundOptimal()).isTrue();
	}
	
	@Test
	public void testSetTwoGenerationsAndMakeTwoIterations() {
		Population population = mock(Population.class);
		EvaluationFunction evaluationFunction = mock(EvaluationFunction.class);
		ParentSelector parentSelector = mock(ParentSelector.class);
		RecombinationOperator recombinationOperator = mock(RecombinationOperator.class);
		MutationOperator mutationOperator = mock(MutationOperator.class);
		SurvivorSelector survivorSelector = mock(SurvivorSelector.class);
		Fitness optimalFitness = mock(Fitness.class);
		Fitness noOptimalFitness = mock(Fitness.class);
		
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
		Answer<Individual> answer = new Answer<Individual>() {
			private int count = 0;
			
			public Individual answer(InvocationOnMock invocation) {
				if (count++ == 1)
					return mutatedIndividual;
				return firstIndividual;
			}
		};
		when(population.getBestIndividual()).thenAnswer(answer);
		
		when(parentSelector.selectParents(initialPopulation, initialPopulation.size())).thenReturn(parents);
		
		when(recombinationOperator.recombine(parents)).thenReturn(recombinatedOffspring);
		
		when(mutationOperator.mutate(recombinatedOffspring)).thenReturn(mutatedOffspring);
		
		when(survivorSelector.getSurvivor(population, evaluatedOffspring)).thenReturn(evaluatedOffspring);
		
		when(evaluationFunction.evaluate(initialPopulation)).thenReturn(initialPopulation);
		when(evaluationFunction.evaluate(mutatedOffspring)).thenReturn(evaluatedOffspring);
		
		when(mutatedIndividual.getFitness()).thenReturn(noOptimalFitness);
		when(mutatedIndividual.compareTo(firstIndividual)).thenReturn(1);
		when(firstIndividual.compareTo(mutatedIndividual)).thenReturn(-1);
		
		when(optimalFitness.compareTo(optimalFitness)).thenReturn(0);
		when(noOptimalFitness.compareTo(optimalFitness)).thenReturn(-1);
		
		GeneticAlgorithmSingle geneticAlgorithmSingle = new GeneticAlgorithmSingle(population, evaluationFunction, parentSelector, recombinationOperator, 
				mutationOperator, survivorSelector, 1, optimalFitness, initialPopulation.size());
		
		assertThat(geneticAlgorithmSingle.getSolution()).isEqualTo(firstIndividual);
		
		geneticAlgorithmSingle.setGenerations(2);
		
		geneticAlgorithmSingle.run();
		
		verify(parentSelector, times(2)).selectParents(initialPopulation, initialPopulation.size());
		
		
		verify(recombinationOperator, times(2)).recombine(parents);
		
		verify(mutationOperator, times(2)).mutate(recombinatedOffspring);
		
		verify(evaluationFunction, times(2)).evaluate(mutatedOffspring);
		
		verify(survivorSelector, times(2)).getSurvivor(population, evaluatedOffspring);
				
		assertThat(geneticAlgorithmSingle.isFinished()).isTrue();
		assertThat(geneticAlgorithmSingle.getSolution()).isEqualTo(mutatedIndividual);
		assertThat(geneticAlgorithmSingle.foundOptimal()).isFalse();
	}
	
	@Test
	public void testGetThreeBestIndividuals() {
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
		
		Collection<Individual> threeBestIndividual = new ArrayList<Individual>();
		threeBestIndividual.add(firstIndividual);
		threeBestIndividual.add(secondIndividual);
		threeBestIndividual.add(thirdIndividual);
		
		
		when(population.getAllIndividual()).thenReturn(initialPopulation);
		when(population.getBestIndividual(3)).thenReturn(threeBestIndividual);
		
		GeneticAlgorithmSingle geneticAlgorithmSingle = new GeneticAlgorithmSingle(population, evaluationFunction, parentSelector, recombinationOperator, 
				mutationOperator, survivorSelector, 1, optimalFitness, initialPopulation.size());
		
		assertThat(geneticAlgorithmSingle.getBestIndividual(3)).isEqualTo(threeBestIndividual);
	}
	
	@Test
	public void testSubstituteThreeWorstIndividuals() {
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
		
		Collection<Individual> threeIndividual = new ArrayList<Individual>();
		threeIndividual.add(firstIndividual);
		threeIndividual.add(secondIndividual);
		threeIndividual.add(thirdIndividual);
		
		when(population.getAllIndividual()).thenReturn(initialPopulation);
		
		GeneticAlgorithmSingle geneticAlgorithmSingle = new GeneticAlgorithmSingle(population, evaluationFunction, parentSelector, recombinationOperator, 
				mutationOperator, survivorSelector, 1, optimalFitness, initialPopulation.size());
		
		geneticAlgorithmSingle.substituteWorstIndividual(threeIndividual);
		
		verify(population).substituteWorstIndividual(threeIndividual);
	}
	
	@Test
	public void testAddTwoObserverAndBeingNotified() {
		Population population = mock(Population.class);
		EvaluationFunction evaluationFunction = mock(EvaluationFunction.class);
		ParentSelector parentSelector = mock(ParentSelector.class);
		RecombinationOperator recombinationOperator = mock(RecombinationOperator.class);
		MutationOperator mutationOperator = mock(MutationOperator.class);
		SurvivorSelector survivorSelector = mock(SurvivorSelector.class);
		Fitness optimalFitness = mock(Fitness.class);
		GeneticAlgorithmObserver geneticAlgorithmObserverOne = mock(GeneticAlgorithmObserver.class);
		GeneticAlgorithmObserver geneticAlgorithmObserverTwo = mock(GeneticAlgorithmObserver.class);
		
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
		
		GeneticAlgorithmSingle geneticAlgorithmSingle = new GeneticAlgorithmSingle(population, evaluationFunction, parentSelector, recombinationOperator, 
				mutationOperator, survivorSelector, 1, optimalFitness, initialPopulation.size());
		
		geneticAlgorithmSingle.registerObserver(geneticAlgorithmObserverOne);
		geneticAlgorithmSingle.registerObserver(geneticAlgorithmObserverTwo);
				
		geneticAlgorithmSingle.notifyObservers();
		
		verify(geneticAlgorithmObserverOne).updateGeneticAlgorithmObserver(geneticAlgorithmSingle);
		verify(geneticAlgorithmObserverTwo).updateGeneticAlgorithmObserver(geneticAlgorithmSingle);
	}
	
	@Test
	public void testAddTwoObserverOneRemovedAndBeingNotifiedOnlyOne() {
		Population population = mock(Population.class);
		EvaluationFunction evaluationFunction = mock(EvaluationFunction.class);
		ParentSelector parentSelector = mock(ParentSelector.class);
		RecombinationOperator recombinationOperator = mock(RecombinationOperator.class);
		MutationOperator mutationOperator = mock(MutationOperator.class);
		SurvivorSelector survivorSelector = mock(SurvivorSelector.class);
		Fitness optimalFitness = mock(Fitness.class);
		GeneticAlgorithmObserver geneticAlgorithmObserverOne = mock(GeneticAlgorithmObserver.class);
		GeneticAlgorithmObserver geneticAlgorithmObserverTwo = mock(GeneticAlgorithmObserver.class);
		
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
		
		GeneticAlgorithmSingle geneticAlgorithmSingle = new GeneticAlgorithmSingle(population, evaluationFunction, parentSelector, recombinationOperator, 
				mutationOperator, survivorSelector, 1, optimalFitness, initialPopulation.size());
		
		geneticAlgorithmSingle.registerObserver(geneticAlgorithmObserverOne);
		geneticAlgorithmSingle.registerObserver(geneticAlgorithmObserverTwo);		
		geneticAlgorithmSingle.removeObserver(geneticAlgorithmObserverTwo);
		
		geneticAlgorithmSingle.notifyObservers();
		
		verify(geneticAlgorithmObserverOne).updateGeneticAlgorithmObserver(geneticAlgorithmSingle);
		verify(geneticAlgorithmObserverTwo, never()).updateGeneticAlgorithmObserver(geneticAlgorithmSingle);
	}
	
	@Test
	public void testTryRemoveNoAddedObserver() {
		Population population = mock(Population.class);
		EvaluationFunction evaluationFunction = mock(EvaluationFunction.class);
		ParentSelector parentSelector = mock(ParentSelector.class);
		RecombinationOperator recombinationOperator = mock(RecombinationOperator.class);
		MutationOperator mutationOperator = mock(MutationOperator.class);
		SurvivorSelector survivorSelector = mock(SurvivorSelector.class);
		Fitness optimalFitness = mock(Fitness.class);
		GeneticAlgorithmObserver geneticAlgorithmObserverOne = mock(GeneticAlgorithmObserver.class);
		GeneticAlgorithmObserver geneticAlgorithmObserverTwo = mock(GeneticAlgorithmObserver.class);
		
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
		
		GeneticAlgorithmSingle geneticAlgorithmSingle = new GeneticAlgorithmSingle(population, evaluationFunction, parentSelector, recombinationOperator, 
				mutationOperator, survivorSelector, 1, optimalFitness,initialPopulation.size());
		
		geneticAlgorithmSingle.registerObserver(geneticAlgorithmObserverOne);
				
		geneticAlgorithmSingle.removeObserver(geneticAlgorithmObserverTwo);
		
		geneticAlgorithmSingle.notifyObservers();
		
		verify(geneticAlgorithmObserverOne).updateGeneticAlgorithmObserver(geneticAlgorithmSingle);
		verify(geneticAlgorithmObserverTwo, never()).updateGeneticAlgorithmObserver(geneticAlgorithmSingle);
	}
	
	@Test
	public void testAddOnlyOneTimeEachObserver() {
		Population population = mock(Population.class);
		EvaluationFunction evaluationFunction = mock(EvaluationFunction.class);
		ParentSelector parentSelector = mock(ParentSelector.class);
		RecombinationOperator recombinationOperator = mock(RecombinationOperator.class);
		MutationOperator mutationOperator = mock(MutationOperator.class);
		SurvivorSelector survivorSelector = mock(SurvivorSelector.class);
		Fitness optimalFitness = mock(Fitness.class);
		GeneticAlgorithmObserver geneticAlgorithmObserverOne = mock(GeneticAlgorithmObserver.class);
		
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
		
		GeneticAlgorithmSingle geneticAlgorithmSingle = new GeneticAlgorithmSingle(population, evaluationFunction, parentSelector, recombinationOperator, 
				mutationOperator, survivorSelector, 1, optimalFitness, initialPopulation.size());
		
		geneticAlgorithmSingle.registerObserver(geneticAlgorithmObserverOne);
		geneticAlgorithmSingle.registerObserver(geneticAlgorithmObserverOne);		
		
		geneticAlgorithmSingle.notifyObservers();
		
		verify(geneticAlgorithmObserverOne).updateGeneticAlgorithmObserver(geneticAlgorithmSingle);
	}

}
