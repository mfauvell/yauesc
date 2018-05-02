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
	
	@BeforeAll
	public static void setUp() {
		Individual firstIndividual = mock(Individual.class);
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
	}

	@Test
	public void constructorStaticSizeShouldSetIndividualsAndSizeOfPopulation() {
		Population population;
		try {
			population = new Population(collectionIndividuals);
			
			assertThat(population.getAllIndividual()).isEqualTo(collectionSortedIndividuals);
			assertThat(population.getSize()).isEqualTo(collectionSortedIndividuals.size());
		} catch (IllegalParameterValueCheckedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void constructorDynamicSizeShouldSetIndividualsAndSizeMaxMinOfPopulation() {
		int MAX = 6;
		int MIN = 2;
		Population population;
		try {
			population = new Population(collectionIndividuals, MAX, MIN);
			assertThat(population.getAllIndividual()).isEqualTo(collectionSortedIndividuals);
			assertThat(population.getSize()).isEqualTo(collectionSortedIndividuals.size());
			assertThat(population.getMaxSize()).isEqualTo(MAX);
			assertThat(population.getMinSize()).isEqualTo(MIN);
		} catch (IllegalParameterValueCheckedException e) {
			e.printStackTrace();
		}
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
	public void testGetThreeBestIndividuals() {
		Population population;
		try {
			population = new Population(collectionIndividuals);
			
			assertThat(population.getBestIndividual(3)).isEqualTo(collectionThreeBestIndividuals);
		} catch (IllegalParameterValueCheckedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetAllInvidiualsIfNumberBestIndividualsGreaterSize() {
		Population population;
		try {
			population = new Population(collectionIndividuals);
			
			assertThat(population.getBestIndividual(5)).isEqualTo(collectionSortedIndividuals);
		} catch (IllegalParameterValueCheckedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSubstituteAllIndividuals() {
		Population population;
		try {
			population = new Population(collectionIndividualsOther);
			
			population.substituteAllIndividual(collectionIndividuals);
			
			assertThat(population.getAllIndividual()).isEqualTo(collectionSortedIndividuals);
		} catch (IllegalParameterValueCheckedException e) {
			e.printStackTrace();
		}
	}
	
}
