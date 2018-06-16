package es.uned.yauesc.dataUned;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class CourseTest {

	@Test
	public void testCreateCourseIsSetCorrectly() {
		int code = 72;
		String name = "Course";
		DataCourse firstDataCourse = mock(DataCourse.class);
		DataCourse secondDataCourse = mock(DataCourse.class);
		
		List<DataCourse> listDataCourse = new ArrayList<>();
		listDataCourse.add(firstDataCourse);
		listDataCourse.add(secondDataCourse);
		
		Course course = new Course(code, name);
		course.addDataCourse(firstDataCourse);
		course.addDataCourse(secondDataCourse);
		
		assertThat(course.getCode()).isEqualTo(code);
		assertThat(course.getName()).isEqualTo(name);
		assertThat(course.getDataCourseList()).isEqualTo(listDataCourse);
	}

}
