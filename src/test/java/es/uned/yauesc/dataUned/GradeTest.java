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
		
		Grade grade = new Grade(code, name, years);
		
		assertThat(grade.getCode()).isEqualTo(code);
		assertThat(grade.getName()).isEqualTo(name);
		assertThat(grade.getYears()).isEqualTo(years);
	}
}
