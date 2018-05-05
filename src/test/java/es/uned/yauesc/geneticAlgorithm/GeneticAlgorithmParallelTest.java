package es.uned.yauesc.geneticAlgorithm;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Test;

class GeneticAlgorithmParallelTest {

	@Test
	public void testConstructorSetInitialSolution() {
		GeneticAlgorithmSingle firstGeneticAlgorithmSingle = mock(GeneticAlgorithmSingle.class);
		GeneticAlgorithmSingle secondGeneticAlgorithmSingle = mock(GeneticAlgorithmSingle.class);
		GeneticAlgorithmSingle thirdGeneticAlgorithmSingle = mock(GeneticAlgorithmSingle.class);
		
		Individual firstSolution = mock(Individual.class);
		Individual secondSolution = mock(Individual.class);
		Individual thirdSolution = mock(Individual.class);
		
		int generations = 5;
		int migration = 2;
		int numberMigration = 1;
		
		when(firstGeneticAlgorithmSingle.getSolution()).thenReturn(firstSolution);
		when(secondGeneticAlgorithmSingle.getSolution()).thenReturn(secondSolution);
		when(thirdGeneticAlgorithmSingle.getSolution()).thenReturn(thirdSolution);
		
		when(firstSolution.compareTo(secondSolution)).thenReturn(-1);
		when(firstSolution.compareTo(thirdSolution)).thenReturn(1);
		when(secondSolution.compareTo(firstSolution)).thenReturn(1);
		when(secondSolution.compareTo(thirdSolution)).thenReturn(1);
		when(thirdSolution.compareTo(firstSolution)).thenReturn(-1);
		when(thirdSolution.compareTo(secondSolution)).thenReturn(-1);
		
		GeneticAlgorithmParallel geneticAlgorithmParallel = new GeneticAlgorithmParallel(firstGeneticAlgorithmSingle, secondGeneticAlgorithmSingle, thirdGeneticAlgorithmSingle, 
				generations, migration, numberMigration);
		
		assertThat(geneticAlgorithmParallel.getSolution()).isEqualTo(secondSolution);
	}
	
	@Test
	public void testMigrationSetToZero() {
		GeneticAlgorithmSingle firstGeneticAlgorithmSingle = mock(GeneticAlgorithmSingle.class);
		GeneticAlgorithmSingle secondGeneticAlgorithmSingle = mock(GeneticAlgorithmSingle.class);
		GeneticAlgorithmSingle thirdGeneticAlgorithmSingle = mock(GeneticAlgorithmSingle.class);
		
		Individual firstSolution = mock(Individual.class);
		Individual secondSolution = mock(Individual.class);
		Individual thirdSolution = mock(Individual.class);
		Individual individual = mock(Individual.class);
		
		Collection<Individual> bestFirst = new ArrayList<Individual>();
		bestFirst.add(individual);
		bestFirst.add(firstSolution);
		Collection<Individual> bestSecond = new ArrayList<Individual>();
		bestSecond.add(individual);
		bestSecond.add(secondSolution);
		Collection<Individual> bestThird = new ArrayList<Individual>();
		bestThird.add(individual);
		bestThird.add(thirdSolution);
		
		int generations = 3;
		int migration = 0;
		int numberMigration = 2;
		
		when(firstGeneticAlgorithmSingle.getSolution()).thenReturn(firstSolution);
		when(secondGeneticAlgorithmSingle.getSolution()).thenReturn(secondSolution);
		when(thirdGeneticAlgorithmSingle.getSolution()).thenReturn(thirdSolution);
		
		when(firstGeneticAlgorithmSingle.getBestIndividual(numberMigration)).thenReturn(bestFirst);
		when(secondGeneticAlgorithmSingle.getBestIndividual(numberMigration)).thenReturn(bestSecond);
		when(thirdGeneticAlgorithmSingle.getBestIndividual(numberMigration)).thenReturn(bestThird);
		
		when(firstGeneticAlgorithmSingle.foundOptimal()).thenReturn(false);
		when(secondGeneticAlgorithmSingle.foundOptimal()).thenReturn(false);
		when(thirdGeneticAlgorithmSingle.foundOptimal()).thenReturn(false);
		
		when(firstSolution.compareTo(secondSolution)).thenReturn(-1);
		when(firstSolution.compareTo(thirdSolution)).thenReturn(1);
		when(secondSolution.compareTo(firstSolution)).thenReturn(1);
		when(secondSolution.compareTo(thirdSolution)).thenReturn(1);
		when(thirdSolution.compareTo(firstSolution)).thenReturn(-1);
		when(thirdSolution.compareTo(secondSolution)).thenReturn(-1);
				
		GeneticAlgorithmParallel geneticAlgorithmParallel = new GeneticAlgorithmParallel(firstGeneticAlgorithmSingle, secondGeneticAlgorithmSingle, thirdGeneticAlgorithmSingle, 
				generations, migration, numberMigration);
	
		assertThat(geneticAlgorithmParallel.getSolution()).isEqualTo(secondSolution);

		geneticAlgorithmParallel.run();
		
		verify(firstGeneticAlgorithmSingle).setGenerations(generations);
		verify(firstGeneticAlgorithmSingle).run();
		verify(firstGeneticAlgorithmSingle).getBestIndividual(numberMigration);
		verify(firstGeneticAlgorithmSingle).substituteWorstIndividual(bestThird);
		verify(firstGeneticAlgorithmSingle).foundOptimal();
		
		verify(secondGeneticAlgorithmSingle).setGenerations(generations);
		verify(secondGeneticAlgorithmSingle).run();
		verify(secondGeneticAlgorithmSingle).getBestIndividual(numberMigration);
		verify(secondGeneticAlgorithmSingle).substituteWorstIndividual(bestFirst);
		verify(secondGeneticAlgorithmSingle).foundOptimal();
		
		verify(thirdGeneticAlgorithmSingle).setGenerations(generations);
		verify(thirdGeneticAlgorithmSingle).run();
		verify(thirdGeneticAlgorithmSingle).getBestIndividual(numberMigration);
		verify(thirdGeneticAlgorithmSingle).substituteWorstIndividual(bestSecond);
		verify(thirdGeneticAlgorithmSingle).foundOptimal();
		
		assertThat(geneticAlgorithmParallel.isFinished()).isTrue();
		assertThat(geneticAlgorithmParallel.getSolution()).isEqualTo(secondSolution);
	}
	
	@Test
	public void testOneIterationRunWithOptimalFound () {
		GeneticAlgorithmSingle firstGeneticAlgorithmSingle = mock(GeneticAlgorithmSingle.class);
		GeneticAlgorithmSingle secondGeneticAlgorithmSingle = mock(GeneticAlgorithmSingle.class);
		GeneticAlgorithmSingle thirdGeneticAlgorithmSingle = mock(GeneticAlgorithmSingle.class);
		
		Individual firstSolution = mock(Individual.class);
		Individual secondSolution = mock(Individual.class);
		Individual thirdSolution = mock(Individual.class);
		Individual individual = mock(Individual.class);
		
		Collection<Individual> bestFirst = new ArrayList<Individual>();
		bestFirst.add(individual);
		bestFirst.add(firstSolution);
		Collection<Individual> bestSecond = new ArrayList<Individual>();
		bestSecond.add(individual);
		bestSecond.add(secondSolution);
		Collection<Individual> bestThird = new ArrayList<Individual>();
		bestThird.add(individual);
		bestThird.add(thirdSolution);
		
		int generations = 5;
		int migration = 2;
		int numberMigration = 2;
		
		when(firstGeneticAlgorithmSingle.getSolution()).thenReturn(firstSolution);
		when(secondGeneticAlgorithmSingle.getSolution()).thenReturn(secondSolution);
		when(thirdGeneticAlgorithmSingle.getSolution()).thenReturn(thirdSolution);
		
		when(firstGeneticAlgorithmSingle.getBestIndividual(numberMigration)).thenReturn(bestFirst);
		when(secondGeneticAlgorithmSingle.getBestIndividual(numberMigration)).thenReturn(bestSecond);
		when(thirdGeneticAlgorithmSingle.getBestIndividual(numberMigration)).thenReturn(bestThird);
		
		when(firstGeneticAlgorithmSingle.foundOptimal()).thenReturn(false);
		when(secondGeneticAlgorithmSingle.foundOptimal()).thenReturn(true);
		when(thirdGeneticAlgorithmSingle.foundOptimal()).thenReturn(false);
		
		when(firstSolution.compareTo(secondSolution)).thenReturn(-1);
		when(firstSolution.compareTo(thirdSolution)).thenReturn(1);
		when(secondSolution.compareTo(firstSolution)).thenReturn(1);
		when(secondSolution.compareTo(thirdSolution)).thenReturn(1);
		when(thirdSolution.compareTo(firstSolution)).thenReturn(-1);
		when(thirdSolution.compareTo(secondSolution)).thenReturn(-1);
				
		GeneticAlgorithmParallel geneticAlgorithmParallel = new GeneticAlgorithmParallel(firstGeneticAlgorithmSingle, secondGeneticAlgorithmSingle, thirdGeneticAlgorithmSingle, 
				generations, migration, numberMigration);
	
		assertThat(geneticAlgorithmParallel.getSolution()).isEqualTo(secondSolution);

		geneticAlgorithmParallel.run();
		
		verify(firstGeneticAlgorithmSingle).setGenerations(migration);
		verify(firstGeneticAlgorithmSingle).run();
		verify(firstGeneticAlgorithmSingle).getBestIndividual(numberMigration);
		verify(firstGeneticAlgorithmSingle).substituteWorstIndividual(bestThird);
		verify(firstGeneticAlgorithmSingle).foundOptimal();
		
		verify(secondGeneticAlgorithmSingle).setGenerations(migration);
		verify(secondGeneticAlgorithmSingle).run();
		verify(secondGeneticAlgorithmSingle).getBestIndividual(numberMigration);
		verify(secondGeneticAlgorithmSingle).substituteWorstIndividual(bestFirst);
		verify(secondGeneticAlgorithmSingle).foundOptimal();
		
		verify(thirdGeneticAlgorithmSingle).setGenerations(migration);
		verify(thirdGeneticAlgorithmSingle).run();
		verify(thirdGeneticAlgorithmSingle).getBestIndividual(numberMigration);
		verify(thirdGeneticAlgorithmSingle).substituteWorstIndividual(bestSecond);
		
		assertThat(geneticAlgorithmParallel.isFinished()).isTrue();
		assertThat(geneticAlgorithmParallel.getSolution()).isEqualTo(secondSolution);
	}
	
	@Test
	public void testTwoIterationRun () {
		GeneticAlgorithmSingle firstGeneticAlgorithmSingle = mock(GeneticAlgorithmSingle.class);
		GeneticAlgorithmSingle secondGeneticAlgorithmSingle = mock(GeneticAlgorithmSingle.class);
		GeneticAlgorithmSingle thirdGeneticAlgorithmSingle = mock(GeneticAlgorithmSingle.class);
		
		Individual firstSolution = mock(Individual.class);
		Individual secondSolution = mock(Individual.class);
		Individual thirdSolution = mock(Individual.class);
		Individual individual = mock(Individual.class);
		
		Collection<Individual> bestFirst = new ArrayList<Individual>();
		bestFirst.add(individual);
		bestFirst.add(firstSolution);
		Collection<Individual> bestSecond = new ArrayList<Individual>();
		bestSecond.add(individual);
		bestSecond.add(secondSolution);
		Collection<Individual> bestThird = new ArrayList<Individual>();
		bestThird.add(individual);
		bestThird.add(thirdSolution);
		
		int generations = 3;
		int migration = 2;
		int numberMigration = 2;
		
		when(firstGeneticAlgorithmSingle.getSolution()).thenReturn(firstSolution);
		when(secondGeneticAlgorithmSingle.getSolution()).thenReturn(secondSolution);
		when(thirdGeneticAlgorithmSingle.getSolution()).thenReturn(thirdSolution);
		
		when(firstGeneticAlgorithmSingle.getBestIndividual(numberMigration)).thenReturn(bestFirst);
		when(secondGeneticAlgorithmSingle.getBestIndividual(numberMigration)).thenReturn(bestSecond);
		when(thirdGeneticAlgorithmSingle.getBestIndividual(numberMigration)).thenReturn(bestThird);
		
		when(firstGeneticAlgorithmSingle.foundOptimal()).thenReturn(false);
		when(secondGeneticAlgorithmSingle.foundOptimal()).thenReturn(false);
		when(thirdGeneticAlgorithmSingle.foundOptimal()).thenReturn(false);
		
		when(firstSolution.compareTo(secondSolution)).thenReturn(-1);
		when(firstSolution.compareTo(thirdSolution)).thenReturn(1);
		when(secondSolution.compareTo(firstSolution)).thenReturn(1);
		when(secondSolution.compareTo(thirdSolution)).thenReturn(1);
		when(thirdSolution.compareTo(firstSolution)).thenReturn(-1);
		when(thirdSolution.compareTo(secondSolution)).thenReturn(-1);
				
		GeneticAlgorithmParallel geneticAlgorithmParallel = new GeneticAlgorithmParallel(firstGeneticAlgorithmSingle, secondGeneticAlgorithmSingle, thirdGeneticAlgorithmSingle, 
				generations, migration, numberMigration);
	
		assertThat(geneticAlgorithmParallel.getSolution()).isEqualTo(secondSolution);

		geneticAlgorithmParallel.run();
		
		verify(firstGeneticAlgorithmSingle).setGenerations(migration);
		verify(firstGeneticAlgorithmSingle).setGenerations(1);
		verify(firstGeneticAlgorithmSingle, times(2)).run();
		verify(firstGeneticAlgorithmSingle, times(2)).getBestIndividual(numberMigration);
		verify(firstGeneticAlgorithmSingle, times(2)).substituteWorstIndividual(bestThird);
		verify(firstGeneticAlgorithmSingle, times(2)).foundOptimal();
		
		verify(secondGeneticAlgorithmSingle).setGenerations(migration);
		verify(secondGeneticAlgorithmSingle).setGenerations(1);
		verify(secondGeneticAlgorithmSingle, times(2)).run();
		verify(secondGeneticAlgorithmSingle, times(2)).getBestIndividual(numberMigration);
		verify(secondGeneticAlgorithmSingle, times(2)).substituteWorstIndividual(bestFirst);
		verify(secondGeneticAlgorithmSingle, times(2)).foundOptimal();
		
		verify(thirdGeneticAlgorithmSingle).setGenerations(migration);
		verify(thirdGeneticAlgorithmSingle).setGenerations(1);
		verify(thirdGeneticAlgorithmSingle, times(2)).run();
		verify(thirdGeneticAlgorithmSingle, times(2)).getBestIndividual(numberMigration);
		verify(thirdGeneticAlgorithmSingle, times(2)).substituteWorstIndividual(bestSecond);
		verify(thirdGeneticAlgorithmSingle, times(2)).foundOptimal();
		
		assertThat(geneticAlgorithmParallel.isFinished()).isTrue();
		assertThat(geneticAlgorithmParallel.getSolution()).isEqualTo(secondSolution);
	}

	@Test
	public void testAddTwoObserverAndBeingNotified() {
		GeneticAlgorithmSingle firstGeneticAlgorithmSingle = mock(GeneticAlgorithmSingle.class);
		GeneticAlgorithmSingle secondGeneticAlgorithmSingle = mock(GeneticAlgorithmSingle.class);
		GeneticAlgorithmSingle thirdGeneticAlgorithmSingle = mock(GeneticAlgorithmSingle.class);
		
		Individual firstSolution = mock(Individual.class);
		Individual secondSolution = mock(Individual.class);
		Individual thirdSolution = mock(Individual.class);
		
		GeneticAlgorithmObserver geneticAlgorithmObserverOne = mock(GeneticAlgorithmObserver.class);
		GeneticAlgorithmObserver geneticAlgorithmObserverTwo = mock(GeneticAlgorithmObserver.class);
		
		int generations = 5;
		int migration = 2;
		int numberMigration = 1;
		
		when(firstGeneticAlgorithmSingle.getSolution()).thenReturn(firstSolution);
		when(secondGeneticAlgorithmSingle.getSolution()).thenReturn(secondSolution);
		when(thirdGeneticAlgorithmSingle.getSolution()).thenReturn(thirdSolution);
		
		when(firstSolution.compareTo(secondSolution)).thenReturn(-1);
		when(firstSolution.compareTo(thirdSolution)).thenReturn(1);
		when(secondSolution.compareTo(firstSolution)).thenReturn(1);
		when(secondSolution.compareTo(thirdSolution)).thenReturn(1);
		when(thirdSolution.compareTo(firstSolution)).thenReturn(-1);
		when(thirdSolution.compareTo(secondSolution)).thenReturn(-1);
		
		GeneticAlgorithmParallel geneticAlgorithmParallel = new GeneticAlgorithmParallel(firstGeneticAlgorithmSingle, secondGeneticAlgorithmSingle, thirdGeneticAlgorithmSingle, 
				generations, migration, numberMigration);
		
		geneticAlgorithmParallel.registerObserver(geneticAlgorithmObserverOne);
		geneticAlgorithmParallel.registerObserver(geneticAlgorithmObserverTwo);
				
		geneticAlgorithmParallel.notifyObservers();
		
		verify(geneticAlgorithmObserverOne).updateGeneticAlgorithmObserver(geneticAlgorithmParallel);
		verify(geneticAlgorithmObserverTwo).updateGeneticAlgorithmObserver(geneticAlgorithmParallel);
	}
	
	@Test
	public void testAddTwoObserverOneRemovedAndBeingNotifiedOnlyOne() {
		GeneticAlgorithmSingle firstGeneticAlgorithmSingle = mock(GeneticAlgorithmSingle.class);
		GeneticAlgorithmSingle secondGeneticAlgorithmSingle = mock(GeneticAlgorithmSingle.class);
		GeneticAlgorithmSingle thirdGeneticAlgorithmSingle = mock(GeneticAlgorithmSingle.class);
		
		Individual firstSolution = mock(Individual.class);
		Individual secondSolution = mock(Individual.class);
		Individual thirdSolution = mock(Individual.class);
		
		GeneticAlgorithmObserver geneticAlgorithmObserverOne = mock(GeneticAlgorithmObserver.class);
		GeneticAlgorithmObserver geneticAlgorithmObserverTwo = mock(GeneticAlgorithmObserver.class);
		
		int generations = 5;
		int migration = 2;
		int numberMigration = 1;
		
		when(firstGeneticAlgorithmSingle.getSolution()).thenReturn(firstSolution);
		when(secondGeneticAlgorithmSingle.getSolution()).thenReturn(secondSolution);
		when(thirdGeneticAlgorithmSingle.getSolution()).thenReturn(thirdSolution);
		
		when(firstSolution.compareTo(secondSolution)).thenReturn(-1);
		when(firstSolution.compareTo(thirdSolution)).thenReturn(1);
		when(secondSolution.compareTo(firstSolution)).thenReturn(1);
		when(secondSolution.compareTo(thirdSolution)).thenReturn(1);
		when(thirdSolution.compareTo(firstSolution)).thenReturn(-1);
		when(thirdSolution.compareTo(secondSolution)).thenReturn(-1);
		
		GeneticAlgorithmParallel geneticAlgorithmParallel = new GeneticAlgorithmParallel(firstGeneticAlgorithmSingle, secondGeneticAlgorithmSingle, thirdGeneticAlgorithmSingle, 
				generations, migration, numberMigration);
		
		geneticAlgorithmParallel.registerObserver(geneticAlgorithmObserverOne);
		geneticAlgorithmParallel.registerObserver(geneticAlgorithmObserverTwo);
		
		geneticAlgorithmParallel.removeObserver(geneticAlgorithmObserverTwo);
				
		geneticAlgorithmParallel.notifyObservers();
		
		verify(geneticAlgorithmObserverOne).updateGeneticAlgorithmObserver(geneticAlgorithmParallel);
		verify(geneticAlgorithmObserverTwo, never()).updateGeneticAlgorithmObserver(geneticAlgorithmParallel);
	}
	
	@Test
	public void testAddOnlyOneTimeEachObserver() {
		GeneticAlgorithmSingle firstGeneticAlgorithmSingle = mock(GeneticAlgorithmSingle.class);
		GeneticAlgorithmSingle secondGeneticAlgorithmSingle = mock(GeneticAlgorithmSingle.class);
		GeneticAlgorithmSingle thirdGeneticAlgorithmSingle = mock(GeneticAlgorithmSingle.class);
		
		Individual firstSolution = mock(Individual.class);
		Individual secondSolution = mock(Individual.class);
		Individual thirdSolution = mock(Individual.class);
		
		GeneticAlgorithmObserver geneticAlgorithmObserverOne = mock(GeneticAlgorithmObserver.class);
		
		int generations = 5;
		int migration = 2;
		int numberMigration = 1;
		
		when(firstGeneticAlgorithmSingle.getSolution()).thenReturn(firstSolution);
		when(secondGeneticAlgorithmSingle.getSolution()).thenReturn(secondSolution);
		when(thirdGeneticAlgorithmSingle.getSolution()).thenReturn(thirdSolution);
		
		when(firstSolution.compareTo(secondSolution)).thenReturn(-1);
		when(firstSolution.compareTo(thirdSolution)).thenReturn(1);
		when(secondSolution.compareTo(firstSolution)).thenReturn(1);
		when(secondSolution.compareTo(thirdSolution)).thenReturn(1);
		when(thirdSolution.compareTo(firstSolution)).thenReturn(-1);
		when(thirdSolution.compareTo(secondSolution)).thenReturn(-1);
		
		GeneticAlgorithmParallel geneticAlgorithmParallel = new GeneticAlgorithmParallel(firstGeneticAlgorithmSingle, secondGeneticAlgorithmSingle, thirdGeneticAlgorithmSingle, 
				generations, migration, numberMigration);
		
		geneticAlgorithmParallel.registerObserver(geneticAlgorithmObserverOne);
		geneticAlgorithmParallel.registerObserver(geneticAlgorithmObserverOne);
				
		geneticAlgorithmParallel.notifyObservers();
		
		verify(geneticAlgorithmObserverOne).updateGeneticAlgorithmObserver(geneticAlgorithmParallel);
	}
}
