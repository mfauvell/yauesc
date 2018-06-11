package es.uned.yauesc.dataUned;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class GradeTest {

	@Test
	public void testCreateGradeIsSetCorrectly() {
		String name = "Grade";
		int code = 73;
		int years = 4;
		
		Course courseOne = mock(Course.class);
		Course courseTwo = mock(Course.class);
		
		List<Course> courseList = new ArrayList<Course>();
		courseList.add(courseOne);
		courseList.add(courseTwo);
		
		Grade grade = new Grade(code, name, years, courseList);
		
		assertThat(grade.getCode()).isEqualTo(code);
		assertThat(grade.getName()).isEqualTo(name);
		assertThat(grade.getYears()).isEqualTo(years);
		assertThat(grade.getAllCourses()).isEqualTo(courseList);
	}

	@Test
	public void testObtainCoursesOfYear() {
		String name = "Grade";
		int code = 73;
		int years = 4;
		
		Course courseOne = mock(Course.class);
		Course courseTwo = mock(Course.class);
		
		DataCourse dataCourseOne = mock(DataCourse.class);
		DataCourse dataCourseTwo = mock(DataCourse.class);
		
		when(courseOne.getDataCourse()).thenReturn(dataCourseOne);
		when(courseTwo.getDataCourse()).thenReturn(dataCourseTwo);
		
		when(dataCourseOne.getSchoolYear()).thenReturn(1);
		when(dataCourseTwo.getSchoolYear()).thenReturn(2);
		
		List<Course> courseList = new ArrayList<Course>();
		courseList.add(courseOne);
		courseList.add(courseTwo);
		
		List<Course> courseFirstYear = new ArrayList<Course>();
		courseFirstYear.add(courseOne);
		
		Grade grade = new Grade(code, name, years, courseList);
		
		assertThat(grade.getCourseYear(1)).isEqualTo(courseFirstYear);
	}
}
