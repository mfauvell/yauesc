package es.uned.yauesc.dataUned;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DataCourseTest {

	@Test
	void testCreateDataCourseIsSetCorrectly() {
		String grade = "73";
		int schoolYear = 1;
		boolean obligatory = true;
		
		DataCourse dataCourse = new DataCourse(grade, schoolYear, obligatory);
		
		assertThat(dataCourse.getGrade()).isEqualTo(grade);
		assertThat(dataCourse.getSchoolYear()).isEqualTo(schoolYear);
		assertThat(dataCourse.isObligatory()).isEqualTo(obligatory);
	}
}
