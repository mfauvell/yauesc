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
		ExamTime firstExamTime = mock(ExamTime.class);
		ExamTime secondExamTime = mock(ExamTime.class);
		
		when(firstExamTime.getDay()).thenReturn(1);
		when(secondExamTime.getDay()).thenReturn(2);
		
		int numberExamTime = 2;
		
		List<ExamTime> examTimeList = new ArrayList<>();
		examTimeList.add(firstExamTime);
		examTimeList.add(secondExamTime);
		
		List<ExamTime> dayOneExamTime = new ArrayList<>();
		dayOneExamTime.add(firstExamTime);
		
		List<Integer> dayList = new ArrayList<>();
		dayList.add(1);
		dayList.add(2);
		
		DataUned dataUned = new DataUned();
		
		dataUned.addExamTime(firstExamTime);
		dataUned.addExamTime(secondExamTime);
		
		assertThat(dataUned.getExamTimeList()).isEqualTo(examTimeList);
		assertThat(dataUned.getExamTimeDay(1)).isEqualTo(dayOneExamTime);
		assertThat(dataUned.getExamTimeDay(4)).isNull();
		assertThat(dataUned.getNumberExamTime()).isEqualTo(numberExamTime);
		assertThat(dataUned.getExamTime(1)).isEqualTo(secondExamTime);
		assertThat(dataUned.getExamTime(3)).isNull();
	}
	
	@Test
	public void testAddEnrolmentAndGetNumberEnrolInACourseAndACentroAsociado() {
		CentroAsociado firstCentroAsociado = mock(CentroAsociado.class);
		CentroAsociado secondCentroAsociado = mock(CentroAsociado.class);
		String nameFirstCentroAsociado = "First";
		String nameSecondCentroAsociado = "Second";
		when(firstCentroAsociado.getName()).thenReturn(nameFirstCentroAsociado);
		when(secondCentroAsociado.getName()).thenReturn(nameSecondCentroAsociado);
		
		Course firstCourse = mock(Course.class);
		Course secondCourse = mock(Course.class);
		int codeFisrtCourse = 71;
		int codeSecondCourse = 72;
		when(firstCourse.getCode()).thenReturn(codeFisrtCourse);
		when(secondCourse.getCode()).thenReturn(codeSecondCourse);
		
		int numberEnroledFirstCaFirstCourse = 10;
		int numberEnroledSecondCaSecondCourse = 13;
		
		DataUned dataUned = new DataUned();
		
		dataUned.addCentroAsociado(firstCentroAsociado);
		dataUned.addCentroAsociado(secondCentroAsociado);
		dataUned.addCourse(firstCourse);
		dataUned.addCourse(secondCourse);
		
		dataUned.addEnrolment(nameFirstCentroAsociado, codeFisrtCourse, numberEnroledFirstCaFirstCourse);
		dataUned.addEnrolment(nameSecondCentroAsociado, codeSecondCourse, numberEnroledSecondCaSecondCourse);
		
		assertThrows(IllegalArgumentException.class, () -> dataUned.addEnrolment("Error", codeFisrtCourse, numberEnroledFirstCaFirstCourse), "Here must be thrown IllegalArgumentException");
		assertThrows(IllegalArgumentException.class, () -> dataUned.addEnrolment(nameFirstCentroAsociado, -1, numberEnroledFirstCaFirstCourse), "Here must be thrown IllegalArgumentException");
		assertThat(dataUned.getNumberEnrolment(nameFirstCentroAsociado, codeFisrtCourse)).isEqualTo(numberEnroledFirstCaFirstCourse);
		assertThat(dataUned.getNumberEnrolment(nameSecondCentroAsociado, codeSecondCourse)).isEqualTo(numberEnroledSecondCaSecondCourse);
		assertThat(dataUned.getNumberEnrolment(nameFirstCentroAsociado, codeSecondCourse)).isEqualTo(0);
		assertThrows(IllegalArgumentException.class, () -> dataUned.getNumberEnrolment("Error", codeFisrtCourse), "Here must be thrown IllegalArgumentException");
		assertThrows(IllegalArgumentException.class, () -> dataUned.getNumberEnrolment(nameFirstCentroAsociado, -1), "Here must be thrown IllegalArgumentException");
	}
}
