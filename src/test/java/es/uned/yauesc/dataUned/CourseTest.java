package es.uned.yauesc.dataUned;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

class CourseTest {

	@Test
	public void testCreateCourseIsSetCorrectly() {
		int id = 0;
		int code = 72;
		String name = "Course";
		DataCourse dataCourse = mock(DataCourse.class);
		
		Course course = new Course(id, code, name, dataCourse);
		
		assertThat(course.getId()).isEqualTo(id);
		assertThat(course.getCode()).isEqualTo(code);
		assertThat(course.getName()).isEqualTo(name);
		assertThat(course.getDataCourse()).isEqualTo(dataCourse);
	}

}
