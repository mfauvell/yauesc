package es.uned.yauesc.dataUned;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DataUnedScheduleIntTest {

	@Test
	void testGetAllScheduleXMLFile() {
		ClassLoader classLoader = getClass().getClassLoader();
		
		String newFileNameXML = "filenew.xml";
		String newFilePathXML = "./src/intTest/resources/filenew.xml";
		String expectedFileNameXML = "fileexpected.xml";
		
		DataUned dataUned = DataUnedFactory.getDataUned();
		List<Integer> solution = new ArrayList<Integer>(Arrays.asList(0,2,3,1)); 
		FitnessUned solutionFitness = DataUnedFactory.getFitnessUned(0, 0, 0);
		
		dataUned.addGrade("7101", "Grade", 4);
		
		dataUned.addExamTime(1, "Day one", 1, "Hour one");
		dataUned.addExamTime(2, "Day two", 2, "Hour two");
		dataUned.addExamTime(3, "Day three", 3, "Hour three");
		dataUned.addExamTime(4, "Day four", 4, "Hour four");
		
		dataUned.addCourse("10011111", "First Course", "7101", 1, true);
		dataUned.addCourse("10011112", "Second Course", "7101", 2, true);
		dataUned.addCourse("10011113", "Third Course", "7101", 3, true);
		dataUned.addCourse("10011114", "Fourth Course", "7101", 4, true);
		
		DataUnedSchedule unedSchedule = new DataUnedSchedule(solution, solutionFitness, dataUned);
		
		unedSchedule.createXMLAllSchedule(newFilePathXML);
		
		File newFileXML = new File(classLoader.getResource(newFileNameXML).getPath());
		File expectedFileXML = new File(classLoader.getResource(expectedFileNameXML).getPath());
		
		assertThat(newFileXML).hasSameContentAs(expectedFileXML);
	}

	@Test
	void testGetByGradeScheduleXMLFile() {
		ClassLoader classLoader = getClass().getClassLoader();
		
		String newFileNameXML = "filenewGrade.xml";
		String newFilePathXML = "./src/intTest/resources/filenewGrade.xml";
		String expectedFileNameXML = "fileexpectedGrade.xml";
		
		DataUned dataUned = DataUnedFactory.getDataUned();
		List<Integer> solution = new ArrayList<Integer>(Arrays.asList(0,2,3,1)); 
		FitnessUned solutionFitness = DataUnedFactory.getFitnessUned(0, 0, 0);
		
		dataUned.addGrade("7101", "Grade", 4);
		dataUned.addGrade("7102", "Grade two", 4);
		dataUned.addGrade("7103", "Grade three", 4);
		
		dataUned.addExamTime(1, "Day one", 1, "Hour one");
		dataUned.addExamTime(2, "Day two", 2, "Hour two");
		dataUned.addExamTime(3, "Day three", 3, "Hour three");
		dataUned.addExamTime(4, "Day four", 4, "Hour four");
		
		dataUned.addCourse("10011111", "First Course", "7101", 1, true);
		dataUned.addCourse("10011111", "First Course", "7102", 1, true);
		dataUned.addCourse("10011112", "Second Course", "7101", 2, true);
		dataUned.addCourse("10011112", "Second Course", "7103", 2, true);
		dataUned.addCourse("10011113", "Third Course", "7101", 3, true);
		dataUned.addCourse("10011113", "Third Course", "7102", 3, true);
		dataUned.addCourse("10011114", "Fourth Course", "7101", 4, true);
		dataUned.addCourse("10011114", "Fourth Course", "7103", 4, true);
		
		DataUnedSchedule unedSchedule = new DataUnedSchedule(solution, solutionFitness, dataUned);
		
		unedSchedule.createXMLByGradeSchedule(newFilePathXML, "7102");
		
		File newFileXML = new File(classLoader.getResource(newFileNameXML).getPath());
		File expectedFileXML = new File(classLoader.getResource(expectedFileNameXML).getPath());
		
		assertThat(newFileXML).hasSameContentAs(expectedFileXML);
	}
	
	@Test
	void testGetByCourseScheduleXMLFile() {
		ClassLoader classLoader = getClass().getClassLoader();
		
		String newFileNameXML = "filenewCourse.xml";
		String newFilePathXML = "./src/intTest/resources/filenewCourse.xml";
		String expectedFileNameXML = "fileexpectedCourse.xml";
		
		DataUned dataUned = DataUnedFactory.getDataUned();
		List<Integer> solution = new ArrayList<Integer>(Arrays.asList(0,2,3,1)); 
		FitnessUned solutionFitness = DataUnedFactory.getFitnessUned(0, 0, 0);
		
		dataUned.addGrade("7101", "Grade", 4);
		dataUned.addGrade("7102", "Grade two", 4);
		dataUned.addGrade("7103", "Grade three", 4);
		
		dataUned.addExamTime(1, "Day one", 1, "Hour one");
		dataUned.addExamTime(2, "Day two", 2, "Hour two");
		dataUned.addExamTime(3, "Day three", 3, "Hour three");
		dataUned.addExamTime(4, "Day four", 4, "Hour four");
		
		dataUned.addCourse("10011111", "First Course", "7101", 1, true);
		dataUned.addCourse("10011111", "First Course", "7102", 1, true);
		dataUned.addCourse("10011112", "Second Course", "7101", 2, true);
		dataUned.addCourse("10011112", "Second Course", "7103", 2, true);
		dataUned.addCourse("10011113", "Third Course", "7101", 3, true);
		dataUned.addCourse("10011113", "Third Course", "7102", 3, true);
		dataUned.addCourse("10011114", "Fourth Course", "7101", 4, true);
		dataUned.addCourse("10011114", "Fourth Course", "7103", 4, true);
		
		List<String> listCourse = new ArrayList<>();
		listCourse.add("10011112");
		listCourse.add("10011113");
		
		DataUnedSchedule unedSchedule = new DataUnedSchedule(solution, solutionFitness, dataUned);
		
		unedSchedule.createXMLByListCourseSchedule(newFilePathXML, listCourse);
		
		File newFileXML = new File(classLoader.getResource(newFileNameXML).getPath());
		File expectedFileXML = new File(classLoader.getResource(expectedFileNameXML).getPath());
		
		assertThat(newFileXML).hasSameContentAs(expectedFileXML);
	}
}
