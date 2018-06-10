package es.uned.yauesc.geneticAlgorithm;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

class GeneticAlgorithmUtilsTest {

	@Test
	public void testBinaryMaskGenerator() {
		GeneticAlgorithmConfig geneticAlgorithmConfig = mock(GeneticAlgorithmConfig.class);
		when(geneticAlgorithmConfig.getGenotypeLong()).thenReturn(5);
		
		GeneticAlgorithmUtils geneticAlgorithmUtils = new GeneticAlgorithmUtils(geneticAlgorithmConfig);
		
		assertThat(geneticAlgorithmUtils.getBinaryMask()).hasSize(5).containsAnyOf(0,1);
	}

}