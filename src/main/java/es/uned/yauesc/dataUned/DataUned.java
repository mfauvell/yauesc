package es.uned.yauesc.dataUned;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.IntPredicate;

public class DataUned {
	
	private ArrayList<Integer> idCodeCourseList;
	private LinkedHashMap<Integer, Course> codeCourseMap;
	private int courseNumber;
	
	private LinkedHashMap<Integer, Grade> codeGradeMap;
	
	private LinkedHashMap<String, CentroAsociado> nameCentroAsociadoMap;
	
	private ArrayList<ExamTime> idExamTimeList;
	private LinkedHashMap<Integer, List<ExamTime>> dayListExamTimeMap;
	private int examTimeNumber;
	
	public DataUned() {
		idCodeCourseList =  new ArrayList<>();
		codeCourseMap = new LinkedHashMap<>();
		courseNumber = 0;
		
		codeGradeMap = new LinkedHashMap<>();
		
		nameCentroAsociadoMap = new LinkedHashMap<>();
		
		idExamTimeList = new ArrayList<>();
		dayListExamTimeMap = new LinkedHashMap<>();
		examTimeNumber = 0;
	}

	public void addCourse(Course firstCourse) {
		int code = firstCourse.getCode();
		idCodeCourseList.add(code);
		codeCourseMap.put(code, firstCourse);
		courseNumber++;
	}

	public Course getCourse(int id) {
		Course result = null;
		if (courseNumber >= id) {
			result = codeCourseMap.get(idCodeCourseList.get(id));
		}
		return result;
	}
	
	public int getNumberCourses() {
		return courseNumber;
	}

	public void addGrade(Grade firstGrade) {
		codeGradeMap.put(firstGrade.getCode(), firstGrade);
	}

	public Grade getGrade(int code) {
		return codeGradeMap.get(code);
	}

	public void addCentroAsociado(CentroAsociado centroAsociado) {
		nameCentroAsociadoMap.put(centroAsociado.getName(), centroAsociado);
	}

	public List<CentroAsociado> getCentroAsociadoList() {
		return new ArrayList<>(nameCentroAsociadoMap.values());
	}

	public CentroAsociado getCentroAsociado(String name) {
		return nameCentroAsociadoMap.get(name);
	}

	public void addExamTime(ExamTime examTime) {
		idExamTimeList.add(examTime);
		int day = examTime.getDay();
		if (dayListExamTimeMap.containsKey(day)) {
			dayListExamTimeMap.get(day).add(examTime);
		} else {
			List<ExamTime> examTimeDayList = new ArrayList<>();
			examTimeDayList.add(examTime);
			dayListExamTimeMap.put(day, examTimeDayList);
		}
		examTimeNumber++;
	}
	
	public List<ExamTime> getExamTimeList() {
		return idExamTimeList;
	}

	public int getNumberExamTime() {
		return examTimeNumber;
	}

	public List<ExamTime> getExamTimeDay(int day) {
		return dayListExamTimeMap.get(day);
	}

	public ExamTime getExamTime(int id) {
		ExamTime result = null;
		if (examTimeNumber >= id) {
			result = idExamTimeList.get(id);
		}
		return result;
	}

	public void addEnrolment(String nameFirstCentroAsociado, int codeFisrtCourse, int numberEnroledFirstCaFirstCourse) {
		// TODO Auto-generated method stub
		
	}

	public IntPredicate getNumberEnrolment(String nameFirstCentroAsociado, int codeFisrtCourse) {
		// TODO Auto-generated method stub
		return null;
	}
}
