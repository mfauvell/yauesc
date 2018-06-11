package es.uned.yauesc.dataUned;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

class CourseTest {

	@Test
	public void testCreateCourseIsSetCorrectly() {
		int code = 72;
		String name = "Course";
		DataCourse dataCourse = mock(DataCourse.class);
		
		Course course = new Course(code, name, dataCourse);
		
		assertThat(course.getCode()).isEqualTo(code);
		assertThat(course.getName()).isEqualTo(name);
		assertThat(course.getDataCourse()).isEqualTo(dataCourse);
	}

}
