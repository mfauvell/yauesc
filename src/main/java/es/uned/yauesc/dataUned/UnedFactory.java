package es.uned.yauesc.dataUned;

import java.util.List;

public final class UnedFactory {
	
	private UnedFactory() {
		
	}
	
	public static FitnessUned getFitnessUned(int firstLevel, int secondLevel, int thirdLevel) {
		return new FitnessUned(firstLevel, secondLevel, thirdLevel);
	}
	
	public static EvaluationFunctionUned getEvaluationFunctionUned(DataUned dataUned, double percentagePresented) {
		return new EvaluationFunctionUned(dataUned, percentagePresented);
	}
	
	public static DataUned getDataUned() {
		return new DataUned();
	}
	
	public static CentroAsociado getCentroAsociado(String name, int capacity) {
		return new CentroAsociado(name, capacity);
	}
	
	public static Course getCourse(String code, String name) {
		return new Course(code, name);
	}
	
	public static DataCourse getDataCourse(String gradeCode, int schoolYear, boolean obligatory) {
		return new DataCourse(gradeCode, schoolYear, obligatory);
	}
	
	public static Grade getGrade(String code, String name, int years) {
		return new Grade(code, name, years);
	}
	
	public static ExamTime getExamTime(int day, String dayName, int hour, String hourName) {
		return new ExamTime(day, dayName, hour, hourName);
	}
	
	public static UnedParser getUnedParser() {
		return new UnedParser();
	}
	
	public static UnedSchedule getUnedSchedule(List<Integer> solution, DataUned dataUned) {
		return new UnedSchedule(solution, dataUned);
	}

}
