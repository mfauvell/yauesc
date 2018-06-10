package es.uned.yauesc.geneticAlgorithm;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

class GeneticAlgorithmUtilsTest {

	@Test
	public void testBinaryMaskGenerator() {
		//TODO sometimes fail
		GeneticAlgorithmConfig geneticAlgorithmConfig = mock(GeneticAlgorithmConfig.class);
		when(geneticAlgorithmConfig.getGenotypeLong()).thenReturn(5);
		
		GeneticAlgorithmUtils geneticAlgorithmUtils = new GeneticAlgorithmUtils(geneticAlgorithmConfig);
		
		assertThat(geneticAlgorithmUtils.getBinaryMask()).containsOnly(0,1);
	}

}