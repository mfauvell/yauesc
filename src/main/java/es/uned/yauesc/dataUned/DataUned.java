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
	
	public DataUned() {
		idCodeCourseList =  new ArrayList<>();
		codeCourseMap = new LinkedHashMap<>();
		courseNumber = 0;
		
		codeGradeMap = new LinkedHashMap<>();
		
		nameCentroAsociadoMap = new LinkedHashMap<>();
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

	

}
