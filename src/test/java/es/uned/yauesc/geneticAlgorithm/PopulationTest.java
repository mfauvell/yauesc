package es.uned.yauesc.geneticAlgorithm;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class PopulationTest {
	
	private static Collection<Individual> collectionIndividuals;
	private static Collection<Individual> collectionThreeBestIndividuals;
	private static Collection<Individual> collectionSortedIndividuals;
	private static Collection<Individual> collectionIndividualsOther;
	private static Collection<Individual> collectionSortedIndividualsOther;
	private static Individual firstIndividual;
	
	@BeforeAll
	public static void setUp() {
		firstIndividual = mock(Individual.class);
		Individual secondIndividual = mock(Individual.class);
		Individual thirdIndividual = mock(Individual.class);
		Individual fourthIndividual = mock(Individual.class);
		Individual fifthIndividual = mock(Individual.class);
		
		
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
		
		collectionIndividuals = new ArrayList<Individual>();
		collectionIndividuals.add(firstIndividual);
		collectionIndividuals.add(secondIndividual);
		collectionIndividuals.add(thirdIndividual);
		collectionIndividuals.add(fourthIndividual);
		
		collectionThreeBestIndividuals = new ArrayList<Individual>();
		collectionThreeBestIndividuals.add(firstIndividual);
		collectionThreeBestIndividuals.add(thirdIndividual);
		collectionThreeBestIndividuals.add(secondIndividual);
		
		collectionSortedIndividuals = new ArrayList<Individual>();
		collectionSortedIndividuals.add(firstIndividual);
		collectionSortedIndividuals.add(thirdIndividual);
		collectionSortedIndividuals.add(secondIndividual);
		collectionSortedIndividuals.add(fourthIndividual);
		
		collectionIndividualsOther = new ArrayList<Individual>();
		collectionIndividualsOther.add(fifthIndividual);
		collectionIndividualsOther.add(fourthIndividual);
		collectionIndividualsOther.add(thirdIndividual);
		collectionIndividualsOther.add(secondIndividual);
		
		collectionSortedIndividualsOther = new ArrayList<Individual>();
		collectionSortedIndividualsOther.add(firstIndividual);
		collectionSortedIndividualsOther.add(firstIndividual);
		collectionSortedIndividualsOther.add(secondIndividual);
		collectionSortedIndividualsOther.add(thirdIndividual);
	}

	@Test
	public void constructorStaticSizeShouldSetIndividualsAndSizeOfPopulation() throws IllegalParameterValueCheckedException {
		Population population = new Population(collectionIndividuals);
		assertThat(population.getAllIndividual()).isEqualTo(collectionSortedIndividuals);
		assertThat(population.getSize()).isEqualTo(collectionSortedIndividuals.size());
	}
	
	@Test
	public void constructorDynamicSizeShouldSetIndividualsAndSizeMaxMinOfPopulation() throws IllegalParameterValueCheckedException {
		int MAX = 6;
		int MIN = 2;
		Population population = new Population(collectionIndividuals, MAX, MIN);
		
		assertThat(population.getAllIndividual()).isEqualTo(collectionSortedIndividuals);
		assertThat(population.getSize()).isEqualTo(collectionSortedIndividuals.size());
		assertThat(population.getMaxSize()).isEqualTo(MAX);
		assertThat(population.getMinSize()).isEqualTo(MIN);
	}
	
	@Test
	public void constructorThrowExceptionIfMinIsGreaterSize() {
		int MAX = 8;
		int MIN = 6;
		
		assertThrows(IllegalParameterValueCheckedException.class,()->{@SuppressWarnings("unused")
		Population population = new Population(collectionIndividuals, MAX, MIN); }, "Here must be threw a CheckedIllegalArgumentException");
	}
	
	@Test
	public void constructorThrowExceptionIfMaxIsLowerSize() {
		int MAX = 3;
		int MIN = 2;
		
		assertThrows(IllegalParameterValueCheckedException.class,()->{@SuppressWarnings("unused")
		Population population = new Population(collectionIndividuals, MAX, MIN); }, "Here must be threw a CheckedIllegalArgumentException");
	}
	
	@Test
	public void testGetThreeBestIndividuals() throws IllegalParameterValueCheckedException {
		Population population = new Population(collectionIndividuals);
			
		assertThat(population.getBestIndividual(3)).isEqualTo(collectionThreeBestIndividuals);
	}
	
	@Test
	public void testGetBestIndividual() throws IllegalParameterValueCheckedException {
		Population population = new Population(collectionIndividuals);
			
		assertThat(population.getBestIndividual()).isEqualTo(firstIndividual);
	}
	
	@Test
	public void testGetBestInvidiualsIfNumberBestIndividualsGreaterSizeThrowException() throws IllegalParameterValueCheckedException {
		Population population = new Population(collectionIndividuals);
		
		assertThrows(IllegalArgumentException.class,()->{population.getBestIndividual(5);},
				"Must be throw IllegalArgumentException because we ask for more individuals that individual in population");
	}
	
	@Test
	public void testSubstituteAllIndividuals() throws IllegalParameterValueCheckedException {
		Population population = new Population(collectionIndividualsOther);
			
		population.substituteAllIndividual(collectionIndividuals);
			
		assertThat(population.getAllIndividual()).isEqualTo(collectionSortedIndividuals);
	}
	
	@Test
	public void testSubstituteAllIndividualsNumberLowerThanMinThrowException () throws IllegalParameterValueCheckedException {
		int MIN = 4;
		int MAX = 5;
		Population population = new Population(collectionIndividualsOther, MAX, MIN);
		
		assertThrows(IllegalArgumentException.class, ()->{population.substituteAllIndividual(collectionThreeBestIndividuals); }, 
					"Must be throw IllegalArgumentException because new individuals are lower than MIN");
	}
	
	@Test
	public void testSubstituteAllIndividualsNumberGreaterThanMaxThrowException () throws IllegalParameterValueCheckedException {
		int MIN = 3;
		int MAX = 3;
		Population population = new Population(collectionThreeBestIndividuals, MAX, MIN);
		
		assertThrows(IllegalArgumentException.class, ()->{population.substituteAllIndividual(collectionIndividuals); }, 
					"Must be throw IllegalArgumentException because new individuals are greater than MAX");
	}
	
	@Test
	public void testSubstituteThreeWorstIndividuals() throws IllegalParameterValueCheckedException {
		Population population = new Population(collectionIndividuals);
		
		population.substituteWorstIndividual(collectionThreeBestIndividuals);
			
		assertThat(population.getAllIndividual()).isEqualTo(collectionSortedIndividualsOther);
	}
	
	@Test
	public void testSubstituteWorstIndividualsNumberGreaterThanMaxThrowException() throws IllegalParameterValueCheckedException {
		int MIN = 3;
		int MAX = 3;
		Population population = new Population(collectionThreeBestIndividuals, MAX, MIN);
		
		assertThrows(IllegalArgumentException.class, ()->{population.substituteWorstIndividual(collectionIndividuals); }, 
					"Must be throw IllegalArgumentException because new substitute individuals are greater than MAX");
	}
	
	@Test
	public void testSubstituteWorstIndividualsNumberGreaterThanSizeButLowerThanMax () throws IllegalParameterValueCheckedException {
		int MIN = 3;
		int MAX = 4;
		Population population = new Population(collectionThreeBestIndividuals, MAX, MIN);
		
		population.substituteWorstIndividual(collectionIndividuals);
		
		assertThat(population.getAllIndividual()).isEqualTo(collectionSortedIndividuals);
	}
	
}
