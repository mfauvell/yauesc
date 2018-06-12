package es.uned.yauesc.dataUned;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class DataUnedTest {

	@Test
	public void testAddCourseAndGetNumberCourses() {
		Course firstCourse = mock(Course.class);
		Course secondCourse = mock(Course.class);
		
		when(firstCourse.getCode()).thenReturn(73);
		when(secondCourse.getCode()).thenReturn(74);
		
		DataUned dataUned = new DataUned();
		
		dataUned.addCourse(firstCourse);
		dataUned.addCourse(secondCourse);
		
		assertThat(dataUned.getCourse(0)).isEqualTo(firstCourse);
		assertThat(dataUned.getCourse(1)).isEqualTo(secondCourse);
		assertThat(dataUned.getCourse(3)).isNull();
		assertThat(dataUned.getNumberCourses()).isEqualTo(2);
	}

	@Test
	public void testAddGrade() {
		Grade firstGrade = mock(Grade.class);
		Grade secondGrade = mock(Grade.class);
		
		when(firstGrade.getCode()).thenReturn(73);
		when(secondGrade.getCode()).thenReturn(74);
		
		DataUned dataUned = new DataUned();
		
		dataUned.addGrade(firstGrade);
		dataUned.addGrade(secondGrade);
		
		assertThat(dataUned.getGrade(73)).isEqualTo(firstGrade);
		assertThat(dataUned.getGrade(74)).isEqualTo(secondGrade);
		assertThat(dataUned.getGrade(79)).isNull();
	}
	
	@Test
	public void testAddCentroAsociado() {
		CentroAsociado firstCentroAsociado = mock(CentroAsociado.class);
		CentroAsociado secondCentroAsociado = mock(CentroAsociado.class);
		String firstName = "fisrt";
		String secondName = "second";
		
		when(firstCentroAsociado.getName()).thenReturn(firstName);
		when(secondCentroAsociado.getName()).thenReturn(secondName);
		
		List<CentroAsociado> centroAsociadoList = new ArrayList<>();
		centroAsociadoList.add(firstCentroAsociado);
		centroAsociadoList.add(secondCentroAsociado);
		
		DataUned dataUned = new DataUned();
		
		dataUned.addCentroAsociado(firstCentroAsociado);
		dataUned.addCentroAsociado(secondCentroAsociado);
		
		assertThat(dataUned.getCentroAsociadoList()).isEqualTo(centroAsociadoList);
		assertThat(dataUned.getCentroAsociado(firstName)).isEqualTo(firstCentroAsociado);
		assertThat(dataUned.getCentroAsociado(secondName)).isEqualTo(secondCentroAsociado);
		assertThat(dataUned.getCentroAsociado("nothing")).isNull();
	}
	
	@Test
	public void testAddExamTimeAndGetNumberExamTime() {
		
	}
}
