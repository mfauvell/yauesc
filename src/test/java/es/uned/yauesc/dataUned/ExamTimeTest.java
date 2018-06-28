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
	
	@Test
	public void testCompareExamTime() {
		int dayFirst = 1;
		String dayFirstText = "Day First";
		int hourFirst = 1;
		String hourFirstText = "Hour First";
		int daySecond = 2;
		String daySecondText = "Day Second";
		int hourSecond = 2;
		String hourSecondText = "Hour Second";
		
		ExamTime examTimeFirst = new ExamTime(dayFirst, dayFirstText, hourFirst, hourFirstText);
		ExamTime examTimeSecond = new ExamTime(dayFirst, dayFirstText, hourSecond, hourSecondText);
		ExamTime examTimeThird = new ExamTime(daySecond, daySecondText, hourFirst, hourFirstText);
		
		assertThat(examTimeFirst.compareTo(examTimeSecond)).isLessThan(0);
		assertThat(examTimeThird.compareTo(examTimeSecond)).isGreaterThan(0);
		assertThat(examTimeSecond.compareTo(examTimeSecond)).isEqualTo(0);
		assertThat(examTimeSecond.compareTo(null)).isGreaterThan(0);
	}

}
