package es.uned.yauesc.dataUned;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import org.junit.jupiter.api.Test;

class UnedScheduleTest {

	@Test
	public void testGetAllScheduleCSVFile() {
		ClassLoader classLoader = getClass().getClassLoader();
		
		String newFileName = "filenew.csv";
		String newFilePath = "./src/test/resources/filenew.csv";
		String expectedFileName = "fileexpected.csv";
		
		List<Integer> solution = new ArrayList<Integer>(Arrays.asList(0,2,3,1)); 
		DataUned dataUned = mock(DataUned.class);
		
		Course courseFirst = mock(Course.class);
		Course courseSecond = mock(Course.class);
		Course courseThird = mock(Course.class);
		Course courseFourth = mock(Course.class);
		
		ExamTime examTimeFirst = mock(ExamTime.class);
		ExamTime examTimeSecond = mock(ExamTime.class);
		ExamTime examTimeThird = mock(ExamTime.class);
		ExamTime examTimeFourth = mock(ExamTime.class);
		
		List<ExamTime> examTimeList = new ArrayList<ExamTime>();
		examTimeList.add(examTimeFirst);
		examTimeList.add(examTimeSecond);
		examTimeList.add(examTimeThird);
		examTimeList.add(examTimeFourth);
		
		when(dataUned.getExamTimeList()).thenReturn(examTimeList);
		when(dataUned.getCourse(0)).thenReturn(courseFirst);
		when(dataUned.getCourse(1)).thenReturn(courseSecond);
		when(dataUned.getCourse(2)).thenReturn(courseThird);
		when(dataUned.getCourse(3)).thenReturn(courseFourth);
		when(dataUned.getExamTime(0)).thenReturn(examTimeFirst);
		when(dataUned.getExamTime(1)).thenReturn(examTimeSecond);
		when(dataUned.getExamTime(2)).thenReturn(examTimeThird);
		when(dataUned.getExamTime(3)).thenReturn(examTimeFourth);
		
		when(courseFirst.getCode()).thenReturn("10011111");
		when(courseFirst.getName()).thenReturn("First Course");
		when(courseSecond.getCode()).thenReturn("10011112");
		when(courseSecond.getName()).thenReturn("Second Course");
		when(courseThird.getCode()).thenReturn("10011113");
		when(courseThird.getName()).thenReturn("Third Course");
		when(courseFourth.getCode()).thenReturn("10011114");
		when(courseFourth.getName()).thenReturn("Fourth Course");
		
		when(examTimeFirst.getDayName()).thenReturn("Day one");
		when(examTimeFirst.getHourName()).thenReturn("Hour one");
		when(examTimeSecond.getDayName()).thenReturn("Day two");
		when(examTimeSecond.getHourName()).thenReturn("Hour two");
		when(examTimeThird.getDayName()).thenReturn("Day three");
		when(examTimeThird.getHourName()).thenReturn("Hour three");
		when(examTimeFourth.getDayName()).thenReturn("Day four");
		when(examTimeFourth.getHourName()).thenReturn("Hour four");
	
		when(examTimeFirst.compareTo(examTimeSecond)).thenReturn(-1);
		when(examTimeFirst.compareTo(examTimeThird)).thenReturn(-1);
		when(examTimeFirst.compareTo(examTimeFourth)).thenReturn(-1);
		when(examTimeSecond.compareTo(examTimeFirst)).thenReturn(1);
		when(examTimeSecond.compareTo(examTimeThird)).thenReturn(-1);
		when(examTimeSecond.compareTo(examTimeFourth)).thenReturn(-1);
		when(examTimeThird.compareTo(examTimeFirst)).thenReturn(1);
		when(examTimeThird.compareTo(examTimeSecond)).thenReturn(1);
		when(examTimeThird.compareTo(examTimeFourth)).thenReturn(-1);
		when(examTimeFourth.compareTo(examTimeFirst)).thenReturn(1);
		when(examTimeFourth.compareTo(examTimeSecond)).thenReturn(1);
		when(examTimeFourth.compareTo(examTimeThird)).thenReturn(1);
		
		
		DataUnedSchedule unedSchedule = new DataUnedSchedule(solution, dataUned);
		
		unedSchedule.createCsvAllSchedule(newFilePath);
		
		File newFile = new File(classLoader.getResource(newFileName).getPath());
		File expectedFile = new File(classLoader.getResource(expectedFileName).getPath());
		
		assertThat(newFile).hasSameContentAs(expectedFile);
	}
	
	@Test
	public void testGetByGradeCsvFile() {
ClassLoader classLoader = getClass().getClassLoader();
		
		String newFileName = "filenewgrade.csv";
		String newFilePath = "./src/test/resources/filenewgrade.csv";
		String expectedFileName = "fileexpectedgrade.csv";
		
		String grade1 = "7101";
		String grade2 = "7103";
		String grade3 = "6126";
		
		List<Integer> solution = new ArrayList<Integer>(Arrays.asList(0,2,3,1)); 
		DataUned dataUned = mock(DataUned.class);
		
		Course courseFirst = mock(Course.class);
		Course courseSecond = mock(Course.class);
		Course courseThird = mock(Course.class);
		Course courseFourth = mock(Course.class);
		
		DataCourse dataCourseFirst = mock(DataCourse.class);
		DataCourse dataCourseSecond = mock(DataCourse.class);
		DataCourse dataCourseThird = mock(DataCourse.class);
		
		List<DataCourse> listDataCourse = new ArrayList<>();
		listDataCourse.add(dataCourseFirst);
		listDataCourse.add(dataCourseSecond);
		
		List<DataCourse> listDataCourse2 = new ArrayList<>();
		listDataCourse2.add(dataCourseFirst);
		listDataCourse2.add(dataCourseThird);
		
		ExamTime examTimeFirst = mock(ExamTime.class);
		ExamTime examTimeSecond = mock(ExamTime.class);
		ExamTime examTimeThird = mock(ExamTime.class);
		ExamTime examTimeFourth = mock(ExamTime.class);
		
		List<ExamTime> examTimeList = new ArrayList<ExamTime>();
		examTimeList.add(examTimeFirst);
		examTimeList.add(examTimeSecond);
		examTimeList.add(examTimeThird);
		examTimeList.add(examTimeFourth);
		
		when(dataUned.getExamTimeList()).thenReturn(examTimeList);
		when(dataUned.getCourse(0)).thenReturn(courseFirst);
		when(dataUned.getCourse(1)).thenReturn(courseSecond);
		when(dataUned.getCourse(2)).thenReturn(courseThird);
		when(dataUned.getCourse(3)).thenReturn(courseFourth);
		when(dataUned.getExamTime(0)).thenReturn(examTimeFirst);
		when(dataUned.getExamTime(1)).thenReturn(examTimeSecond);
		when(dataUned.getExamTime(2)).thenReturn(examTimeThird);
		when(dataUned.getExamTime(3)).thenReturn(examTimeFourth);
		
		when(courseFirst.getCode()).thenReturn("10011111");
		when(courseFirst.getName()).thenReturn("First Course");
		when(courseFirst.getDataCourseList()).thenReturn(listDataCourse);
		when(courseSecond.getCode()).thenReturn("10011112");
		when(courseSecond.getName()).thenReturn("Second Course");
		when(courseSecond.getDataCourseList()).thenReturn(listDataCourse2);
		when(courseThird.getCode()).thenReturn("10011113");
		when(courseThird.getName()).thenReturn("Third Course");
		when(courseThird.getDataCourseList()).thenReturn(listDataCourse);
		when(courseFourth.getCode()).thenReturn("10011114");
		when(courseFourth.getName()).thenReturn("Fourth Course");
		when(courseFourth.getDataCourseList()).thenReturn(listDataCourse2);
		
		when(examTimeFirst.getDayName()).thenReturn("Day one");
		when(examTimeFirst.getHourName()).thenReturn("Hour one");
		when(examTimeSecond.getDayName()).thenReturn("Day two");
		when(examTimeSecond.getHourName()).thenReturn("Hour two");
		when(examTimeThird.getDayName()).thenReturn("Day three");
		when(examTimeThird.getHourName()).thenReturn("Hour three");
		when(examTimeFourth.getDayName()).thenReturn("Day four");
		when(examTimeFourth.getHourName()).thenReturn("Hour four");
	
		when(examTimeFirst.compareTo(examTimeSecond)).thenReturn(-1);
		when(examTimeFirst.compareTo(examTimeThird)).thenReturn(-1);
		when(examTimeFirst.compareTo(examTimeFourth)).thenReturn(-1);
		when(examTimeSecond.compareTo(examTimeFirst)).thenReturn(1);
		when(examTimeSecond.compareTo(examTimeThird)).thenReturn(-1);
		when(examTimeSecond.compareTo(examTimeFourth)).thenReturn(-1);
		when(examTimeThird.compareTo(examTimeFirst)).thenReturn(1);
		when(examTimeThird.compareTo(examTimeSecond)).thenReturn(1);
		when(examTimeThird.compareTo(examTimeFourth)).thenReturn(-1);
		when(examTimeFourth.compareTo(examTimeFirst)).thenReturn(1);
		when(examTimeFourth.compareTo(examTimeSecond)).thenReturn(1);
		when(examTimeFourth.compareTo(examTimeThird)).thenReturn(1);
		
		when(dataCourseFirst.getGrade()).thenReturn(grade1);
		when(dataCourseSecond.getGrade()).thenReturn(grade2);
		when(dataCourseThird.getGrade()).thenReturn(grade3);
				
		DataUnedSchedule unedSchedule = new DataUnedSchedule(solution, dataUned);
		
		unedSchedule.createCsvByGradeSchedule(newFilePath, grade2);
		
		File newFile = new File(classLoader.getResource(newFileName).getPath());
		File expectedFile = new File(classLoader.getResource(expectedFileName).getPath());
		
		assertThat(newFile).hasSameContentAs(expectedFile);
	}
	
	@Test
	public void testGetByCourseCsv() {
ClassLoader classLoader = getClass().getClassLoader();
		
		String newFileName = "filenewcourse.csv";
		String newFilePath = "./src/test/resources/filenewcourse.csv";
		String expectedFileName = "fileexpectedcourse.csv";
		
		String firstCourseCode ="10011111";
		String secondCourseCode = "10011112";
		String thirdCourseCode = "10011113";
		String fourthCourseCode = "10011114";
		
		List<Integer> solution = new ArrayList<Integer>(Arrays.asList(0,2,3,1)); 
		DataUned dataUned = mock(DataUned.class);
		
		Course courseFirst = mock(Course.class);
		Course courseSecond = mock(Course.class);
		Course courseThird = mock(Course.class);
		Course courseFourth = mock(Course.class);
		
		ExamTime examTimeFirst = mock(ExamTime.class);
		ExamTime examTimeSecond = mock(ExamTime.class);
		ExamTime examTimeThird = mock(ExamTime.class);
		ExamTime examTimeFourth = mock(ExamTime.class);
		
		List<ExamTime> examTimeList = new ArrayList<ExamTime>();
		examTimeList.add(examTimeFirst);
		examTimeList.add(examTimeSecond);
		examTimeList.add(examTimeThird);
		examTimeList.add(examTimeFourth);
		
		List<String> courseCodeList = new ArrayList<>();
		courseCodeList.add(secondCourseCode);
		courseCodeList.add(thirdCourseCode);
		
		when(dataUned.getExamTimeList()).thenReturn(examTimeList);
		when(dataUned.getCourse(0)).thenReturn(courseFirst);
		when(dataUned.getCourse(1)).thenReturn(courseSecond);
		when(dataUned.getCourse(2)).thenReturn(courseThird);
		when(dataUned.getCourse(3)).thenReturn(courseFourth);
		when(dataUned.getExamTime(0)).thenReturn(examTimeFirst);
		when(dataUned.getExamTime(1)).thenReturn(examTimeSecond);
		when(dataUned.getExamTime(2)).thenReturn(examTimeThird);
		when(dataUned.getExamTime(3)).thenReturn(examTimeFourth);
		
		when(courseFirst.getCode()).thenReturn(firstCourseCode);
		when(courseFirst.getName()).thenReturn("First Course");
		when(courseSecond.getCode()).thenReturn(secondCourseCode);
		when(courseSecond.getName()).thenReturn("Second Course");
		when(courseThird.getCode()).thenReturn(thirdCourseCode);
		when(courseThird.getName()).thenReturn("Third Course");
		when(courseFourth.getCode()).thenReturn(fourthCourseCode);
		when(courseFourth.getName()).thenReturn("Fourth Course");
		
		when(examTimeFirst.getDayName()).thenReturn("Day one");
		when(examTimeFirst.getHourName()).thenReturn("Hour one");
		when(examTimeSecond.getDayName()).thenReturn("Day two");
		when(examTimeSecond.getHourName()).thenReturn("Hour two");
		when(examTimeThird.getDayName()).thenReturn("Day three");
		when(examTimeThird.getHourName()).thenReturn("Hour three");
		when(examTimeFourth.getDayName()).thenReturn("Day four");
		when(examTimeFourth.getHourName()).thenReturn("Hour four");
	
		when(examTimeFirst.compareTo(examTimeSecond)).thenReturn(-1);
		when(examTimeFirst.compareTo(examTimeThird)).thenReturn(-1);
		when(examTimeFirst.compareTo(examTimeFourth)).thenReturn(-1);
		when(examTimeSecond.compareTo(examTimeFirst)).thenReturn(1);
		when(examTimeSecond.compareTo(examTimeThird)).thenReturn(-1);
		when(examTimeSecond.compareTo(examTimeFourth)).thenReturn(-1);
		when(examTimeThird.compareTo(examTimeFirst)).thenReturn(1);
		when(examTimeThird.compareTo(examTimeSecond)).thenReturn(1);
		when(examTimeThird.compareTo(examTimeFourth)).thenReturn(-1);
		when(examTimeFourth.compareTo(examTimeFirst)).thenReturn(1);
		when(examTimeFourth.compareTo(examTimeSecond)).thenReturn(1);
		when(examTimeFourth.compareTo(examTimeThird)).thenReturn(1);
		
		DataUnedSchedule unedSchedule = new DataUnedSchedule(solution, dataUned);
		
		unedSchedule.createCsvByListCourseSchedule(newFilePath, courseCodeList);
		
		File newFile = new File(classLoader.getResource(newFileName).getPath());
		File expectedFile = new File(classLoader.getResource(expectedFileName).getPath());
		
		assertThat(newFile).hasSameContentAs(expectedFile);
	}

}
