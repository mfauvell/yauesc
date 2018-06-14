package es.uned.yauesc.dataUned;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CentroAsociadoTest {

	@Test
	public void testIfCreateNewCentroAsociadoIsSetCorretcly() {
		String name = "Place";
		int capacity = 150;
		
		CentroAsociado centroAsociado = new CentroAsociado(name, capacity);
		
		assertThat(centroAsociado.getName()).isEqualTo(name);
		assertThat(centroAsociado.getCapacity()).isEqualTo(capacity);
	}
}
