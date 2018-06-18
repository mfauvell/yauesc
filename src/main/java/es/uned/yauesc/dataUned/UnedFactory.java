package es.uned.yauesc.dataUned;

import java.util.List;

public final class UnedFactory {
	
	private UnedFactory() {
		
	}
	
	public static FitnessUned getFitnessUned(int firstLevel, int secondLevel, int thirdLevel) {
		return new FitnessUned(firstLevel, secondLevel, thirdLevel);
	}
	
	public static EvaluationFunctionUned getEvaluationFunctionUned(DataUned dataUned) {
		return new EvaluationFunctionUned(dataUned);
	}
	
	public static DataUned getDataUned() {
		return new DataUned();
	}
	
	public static CentroAsociado getCentroAsociado(String name, int capacity) {
		return new CentroAsociado(name, capacity);
	}
	
	public static Course getCourse(int code, String name) {
		return new Course(code, name);
	}
	
	public static DataCourse getDataCourse(int gradeCode, int schoolYear, boolean obligatory) {
		return new DataCourse(gradeCode, schoolYear, obligatory);
	}
	
	public static Grade getGrade(int code, String name, int years, List<Course> courseList) {
		return new Grade(code, name, years, courseList);
	}
	
	public static ExamTime getExamTime(int day, String dayName, int hour, String hourName) {
		return new ExamTime(day, dayName, hour, hourName);
	}

}
