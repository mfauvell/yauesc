package es.uned.yauesc.dataUned;

import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DataUnedIntTest {
	
	@Test
	public void testAddGradeAndObtainCodeNameGradeString() {
		String codeFirst = "73";
		String nameFirst = "First";
		int yearsFirst = 4;
		String codeSecond = "74";
		String nameSecond = "Second";
		int yearsSecond = 4;
		
		String codeNameFirst = codeFirst + " - " + nameFirst;
		String codeNameSecond = codeSecond + " - " + nameSecond;
		
		DataUned dataUned = new DataUned();
		
		dataUned.addGrade(codeFirst, nameFirst, yearsFirst);
		dataUned.addGrade(codeSecond, nameSecond, yearsSecond);
		
		assertThat(dataUned.getGrade("Error")).isNull();
		
		Grade gradeFirst = dataUned.getGrade(codeFirst);
		Grade gradeSecond = dataUned.getGrade(codeSecond);
		
		assertThat(gradeFirst.getCode()).isEqualTo(codeFirst);
		assertThat(gradeFirst.getName()).isEqualTo(nameFirst);
		assertThat(gradeFirst.getYears()).isEqualTo(yearsFirst);
		
		assertThat(gradeSecond.getCode()).isEqualTo(codeSecond);
		assertThat(gradeSecond.getName()).isEqualTo(nameSecond);
		assertThat(gradeSecond.getYears()).isEqualTo(yearsSecond);
		
		assertThat(dataUned.getCodeNameGradeList()).hasSize(2).contains(codeNameFirst, codeNameSecond);
	}

	@Test
	public void testAddCourseAndDataCourseAndGetNumberCoursesGetCodeNamberCourseAndSetCourseOnGradesThatExistsAndTestNotRepeat() {
		String codeFirstGrade = "73";
		String codeSecondGrade = "74";
		
		String codeFirstCourse = "7300";
		String codeSecondCourse = "7400";
		
		String nameFirstCourse = "First";
		String nameSecondCourse = "Second";
		
		String codeNameFirst = codeFirstCourse + " - " + nameFirstCourse;
		String codeNameSecond = codeSecondCourse + " - " + nameSecondCourse;
		
		DataUned dataUned = new DataUned();
		
		dataUned.addGrade(codeFirstGrade, "First", 4);
		dataUned.addGrade(codeSecondGrade, "Second", 4);
		
		dataUned.addCourse(codeFirstCourse, nameFirstCourse, codeFirstGrade, 2, true );
		dataUned.addCourse(codeFirstCourse, nameFirstCourse, codeSecondGrade, 2, false);
		dataUned.addCourse(codeSecondCourse, nameSecondCourse, codeSecondGrade, 2, true);
		dataUned.addCourse(codeSecondCourse, nameSecondCourse, codeFirstGrade, 2, false);
		dataUned.addCourse(codeSecondCourse, nameSecondCourse, codeSecondGrade, 2, true);
		
		Course courseFirst = dataUned.getCourse(0);
		Course courseSecond = dataUned.getCourse(1);
		
		assertThat(courseFirst.getCode()).isEqualTo("7300");
		assertThat(courseSecond.getCode()).isEqualTo("7400");
		assertThat(courseFirst.getDataCourseList()).hasSize(2);
		assertThat(courseSecond.getDataCourseList()).hasSize(2);
		assertThat(dataUned.getCourse(2)).isNull();
		assertThat(dataUned.getNumberCourses()).isEqualTo(2);
		
		assertThat(dataUned.getCodeNameCourseList()).hasSize(2).contains(codeNameFirst, codeNameSecond);
	}
	
	@Test
	public void testObtaingCoursesByGradeAndGradeAndYear() {
		String codeFirstGrade = "73";
		String codeSecondGrade = "74";
		
		String codeFirstCourse = "7300";
		String codeSecondCourse = "7400";
		
		DataUned dataUned = new DataUned();
		
		dataUned.addGrade(codeFirstGrade, "First", 4);
		dataUned.addGrade(codeSecondGrade, "Second", 4);
		
		dataUned.addCourse(codeFirstCourse, "First", codeFirstGrade, 1, true );
		dataUned.addCourse(codeFirstCourse, "First", codeSecondGrade, 2, false);
		dataUned.addCourse(codeSecondCourse, "Second", codeSecondGrade, 1, true);
		dataUned.addCourse(codeSecondCourse, "Second", codeFirstGrade, 2, false);

		assertThat(dataUned.getAllCourseGrade(codeFirstGrade)).hasSize(2).extracting(course -> course.getCode()).contains(codeFirstCourse, codeSecondCourse);
		assertThat(dataUned.getCourseGradeYear(codeFirstGrade, 1)).hasSize(1).extracting(course -> course.getCode()).contains(codeFirstCourse);
		assertThat(dataUned.getCourseGradeYear(codeSecondGrade, 2)).hasSize(1).extracting(course -> course.getCode()).contains(codeFirstCourse);
		assertThat(dataUned.getCourseGradeYear(codeSecondGrade, 1)).hasSize(1).extracting(course -> course.getCode()).contains(codeSecondCourse);
	}
	
	@Test
	public void testAddCentroAsociadoWithourDuplicates() {
		String firstName = "First";
		String secondName = "Second";
		int firstCapacity = 100;
		int secondCapacity = 200;
		
		DataUned dataUned = new DataUned();
		
		dataUned.addCentroAsociado(firstName, firstCapacity);
		dataUned.addCentroAsociado(secondName, secondCapacity);
		dataUned.addCentroAsociado(secondName, secondCapacity);
		
		assertThat(dataUned.getCentroAsociadoList()).hasSize(2).extracting(centroAsociado -> centroAsociado.getName()).contains(firstName, secondName);
		assertThat(dataUned.getCentroAsociado(firstName).getCapacity()).isEqualTo(firstCapacity);
		assertThat(dataUned.getCentroAsociado(secondName).getCapacity()).isEqualTo(secondCapacity);
		assertThat(dataUned.getCentroAsociado("error")).isNull();
	}
	
	@Test
	public void testAddExamTimeWithoutDuplicatesAndGetNumberExamTime() {
		int numberExamTime = 2;
		
		String dayTextFirst = "DayFirst";
		String dayTextSecond = "DaySecond";
		
		DataUned dataUned = new DataUned();
		
		dataUned.addExamTime(1, dayTextFirst, 1, "HourFirst");
		dataUned.addExamTime(2, dayTextSecond, 4, "HourSecond");
		dataUned.addExamTime(2, dayTextSecond, 4, "HourSecond");
		
		assertThat(dataUned.getExamTimeList()).hasSize(2).extracting(examTime-> examTime.getDayName()).contains(dayTextFirst, dayTextSecond);
		assertThat(dataUned.getExamTimeDay(1)).hasSize(1).extracting(examTime -> examTime.getDayName()).containsExactly(dayTextFirst);
		assertThat(dataUned.getExamTimeDay(4)).isNull();
		assertThat(dataUned.getNumberExamTime()).isEqualTo(numberExamTime);
		assertThat(dataUned.getExamTime(1).getDayName()).isEqualTo(dayTextSecond);
		assertThat(dataUned.getExamTime(2)).isNull();
	}
	
	@Test
	public void testAddEnrolmentWithoutDuplicatesAndGetNumberEnrolInACourseAndACentroAsociado() {
		String nameFirstCentroAsociado = "First";
		String nameSecondCentroAsociado = "Second";

		String codeGrade = "71";
	
		String codeFisrtCourse = "7100";
		String codeSecondCourse = "7200";
		
		int numberEnroledFirstCaFirstCourse = 10;
		int numberEnroledSecondCaSecondCourse = 13;
		
		DataUned dataUned = new DataUned();
		
		dataUned.addCentroAsociado(nameFirstCentroAsociado, 100);
		dataUned.addCentroAsociado(nameSecondCentroAsociado, 100);
		
		dataUned.addGrade(codeGrade, "Grade", 4);
		
		dataUned.addCourse(codeFisrtCourse, "First", codeGrade, 1, true);
		dataUned.addCourse(codeSecondCourse, "Second", codeGrade, 2, true);
		dataUned.addCourse(codeSecondCourse, "Second", codeGrade, 2, true);
		
		dataUned.addEnrolment(nameFirstCentroAsociado, codeFisrtCourse, numberEnroledFirstCaFirstCourse);
		dataUned.addEnrolment(nameSecondCentroAsociado, codeSecondCourse, numberEnroledSecondCaSecondCourse);
		
		assertThat(dataUned.getNumberEnrolment(nameFirstCentroAsociado, codeFisrtCourse)).isEqualTo(numberEnroledFirstCaFirstCourse);
		assertThat(dataUned.getNumberEnrolment(nameSecondCentroAsociado, codeSecondCourse)).isEqualTo(numberEnroledSecondCaSecondCourse);
		assertThat(dataUned.getNumberEnrolment(nameFirstCentroAsociado, codeSecondCourse)).isEqualTo(0);
		assertThrows(IllegalArgumentException.class, () -> dataUned.getNumberEnrolment("Error", codeFisrtCourse), "Here must be thrown IllegalArgumentException");
		assertThrows(IllegalArgumentException.class, () -> dataUned.getNumberEnrolment(nameFirstCentroAsociado, "Error"), "Here must be thrown IllegalArgumentException");
	}
}
