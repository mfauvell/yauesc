package es.uned.yauesc.dataUned;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ExamTimeTest {

	@Test
	public void testCreateExamTimeIsSetCorrectly() {
		int day = 1;
		String dayName = "Day";
		int hour = 1;
		String hourName = "Hour";
		
		ExamTime examTime = new ExamTime(day, dayName, hour, hourName);
		
		assertThat(examTime.getDay()).isEqualTo(day);
		assertThat(examTime.getDayName()).isEqualTo(dayName);
		assertThat(examTime.getHour()).isEqualTo(hour);
		assertThat(examTime.getHourName()).isEqualTo(hourName);
	}

}
