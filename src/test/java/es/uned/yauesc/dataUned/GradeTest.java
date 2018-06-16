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
		int otherCode = 74;		
		
		Course courseOne = mock(Course.class);
		Course courseTwo = mock(Course.class);
		
		DataCourse dataCourseOne = mock(DataCourse.class);
		DataCourse dataCourseTwo = mock(DataCourse.class);
		DataCourse dataCourseThree = mock(DataCourse.class);
		DataCourse DataCourseFour = mock(DataCourse.class);
		
		List<DataCourse> firstDataCourseList = new ArrayList<>();
		firstDataCourseList.add(dataCourseOne);
		firstDataCourseList.add(dataCourseThree);
		
		List<DataCourse> secondDataCourseList = new ArrayList<>();
		secondDataCourseList.add(dataCourseTwo);
		secondDataCourseList.add(DataCourseFour);
		
		when(courseOne.getDataCourseList()).thenReturn(firstDataCourseList);
		when(courseTwo.getDataCourseList()).thenReturn(secondDataCourseList);
		
		when(dataCourseOne.getGrade()).thenReturn(code);
		when(dataCourseTwo.getGrade()).thenReturn(code);
		when(dataCourseThree.getGrade()).thenReturn(otherCode);
		when(DataCourseFour.getGrade()).thenReturn(otherCode);
		
		when(dataCourseOne.getSchoolYear()).thenReturn(1);
		when(dataCourseTwo.getSchoolYear()).thenReturn(2);
		when(dataCourseThree.getSchoolYear()).thenReturn(2);
		when(DataCourseFour.getSchoolYear()).thenReturn(1);
		
		List<Course> courseList = new ArrayList<Course>();
		courseList.add(courseOne);
		courseList.add(courseTwo);
		
		List<Course> courseFirstYear = new ArrayList<Course>();
		courseFirstYear.add(courseOne);
		
		Grade grade = new Grade(code, name, years, courseList);
		
		assertThat(grade.getCourseYear(1)).isEqualTo(courseFirstYear);
	}
}
