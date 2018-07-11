package es.uned.yauesc.dataUned;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class DataUned {
	
	private ArrayList<String> idCodeCourseList;
	private LinkedHashMap<String, Course> codeCourseMap;
	private int courseNumber;
	
	private LinkedHashMap<String, Grade> codeGradeMap;
	private LinkedHashMap<String, List<String>> codeGradeListCourseCodeMap;
	
	private LinkedHashMap<String, CentroAsociado> nameCentroAsociadoMap;
	
	private ArrayList<ExamTime> idExamTimeList;
	private LinkedHashMap<Integer, List<ExamTime>> dayListExamTimeMap;
	private int examTimeNumber;
	
	private LinkedHashMap<String, LinkedHashMap<String, Integer>> centroAsociadoCourseNumberEnrolMap;
	
	public DataUned() {
		idCodeCourseList =  new ArrayList<>();
		codeCourseMap = new LinkedHashMap<>();
		courseNumber = 0;
		
		codeGradeMap = new LinkedHashMap<>();
		codeGradeListCourseCodeMap = new LinkedHashMap<>();
		
		nameCentroAsociadoMap = new LinkedHashMap<>();
		
		idExamTimeList = new ArrayList<>();
		dayListExamTimeMap = new LinkedHashMap<>();
		examTimeNumber = 0;
		
		centroAsociadoCourseNumberEnrolMap = new LinkedHashMap<>();
	}

	public void addCourse(String code, String name, String codeGrade, int  year, boolean obligatory) {
		Course course;
		if (idCodeCourseList.contains(code)) {
			course = codeCourseMap.get(code);
		} else {
			course = DataUnedFactory.getCourse(code, name);
			idCodeCourseList.add(code);
			codeCourseMap.put(code, course);
			courseNumber++;
		}
		DataCourse dataCourse = DataUnedFactory.getDataCourse(codeGrade, year, obligatory);
		if (!course.getDataCourseList().contains(dataCourse)) {
			course.addDataCourse(dataCourse);
			codeGradeListCourseCodeMap.get(codeGrade).add(code);	
		}
	}

	public Course getCourse(int id) {
		Course result = null;
		if (courseNumber > id) {
			result = codeCourseMap.get(idCodeCourseList.get(id));
		}
		return result;
	}
	
	public int getNumberCourses() {
		return courseNumber;
	}
	
	public List<String> getCodeNameCourseList(){
		List<String> result = codeCourseMap.keySet().parallelStream().map(code -> code + " - " + codeCourseMap.get(code).getName()).collect(Collectors.toList());
		Comparator<String> comparator = new Comparator<String>() {

			@Override
			public int compare(String first, String second) {
				String firstTrim = first.substring(11, first.length());
				String secondTrim = second.substring(11, second.length());
				return firstTrim.compareTo(secondTrim);
			}
			
		};
		Collections.sort(result, comparator);
		return result;
	}

	public void addGrade(String code, String name, int years) {
		Grade grade = DataUnedFactory.getGrade(code, name, years);
		codeGradeMap.put(code, grade);
		ArrayList<String> listCourses = new ArrayList<>();
		codeGradeListCourseCodeMap.put(code, listCourses);
	}

	public Grade getGrade(String code) {
		return codeGradeMap.get(code);
	}
	
	public List<String> getCodeNameGradeList() {
		List<String> result = codeGradeMap.keySet().parallelStream().map(code -> code + " - " + codeGradeMap.get(code).getName()).collect(Collectors.toList());
		Comparator<String> comparator = new Comparator<String>() {

			@Override
			public int compare(String first, String second) {
				String firstTrim = first.substring(7, first.length());
				String secondTrim = second.substring(7, second.length());
				return firstTrim.compareTo(secondTrim);
			}
			
		};
		Collections.sort(result, comparator);
		return result;
	}

	public List<Course> getAllCourseGrade(String codeGrade) {
		return codeGradeListCourseCodeMap.get(codeGrade)
				.parallelStream()
				.map(codeCourse -> codeCourseMap.get(codeCourse))
				.collect(Collectors.toList());
	}
	
	public List<Course> getCourseGradeYear(String codeGrade, int year) {
		return codeGradeListCourseCodeMap.get(codeGrade)
				.parallelStream()
				.map(codeCourse -> codeCourseMap.get(codeCourse))
				.filter(course -> !course.getDataCourseList()
						.parallelStream()
						.filter(dataCourse -> dataCourse.getGrade().equals(codeGrade))
						.filter(dataCourseFiltered -> dataCourseFiltered.getSchoolYear() == year)
						.collect(Collectors.toList()).isEmpty())
				.collect(Collectors.toList());
	}
	
	public void addCentroAsociado(String name, int capacity) {
		CentroAsociado centroAsociado = DataUnedFactory.getCentroAsociado(name, capacity);
		nameCentroAsociadoMap.put(centroAsociado.getName(), centroAsociado);
	}

	public List<CentroAsociado> getCentroAsociadoList() {
		return new ArrayList<>(nameCentroAsociadoMap.values());
	}

	public CentroAsociado getCentroAsociado(String name) {
		return nameCentroAsociadoMap.get(name);
	}

	public void addExamTime(int day, String dayText, int hour, String hourText) {
		ExamTime examTime = DataUnedFactory.getExamTime(day, dayText, hour, hourText);
		if (!idExamTimeList.contains(examTime)) {
			idExamTimeList.add(examTime);
			if (dayListExamTimeMap.containsKey(day)) {
				dayListExamTimeMap.get(day).add(examTime);
			} else {
				List<ExamTime> examTimeDayList = new ArrayList<>();
				examTimeDayList.add(examTime);
				dayListExamTimeMap.put(day, examTimeDayList);
			}
			examTimeNumber++;
		}
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
		if (examTimeNumber > id) {
			result = idExamTimeList.get(id);
		}
		return result;
	}

	public void addEnrolment(String nameCentroAsociado, String codeCourse, int numberEnroled) {
		if (codeCourseMap.containsKey(codeCourse) && nameCentroAsociadoMap.containsKey(nameCentroAsociado)) {
			if (centroAsociadoCourseNumberEnrolMap.containsKey(nameCentroAsociado)) {
				centroAsociadoCourseNumberEnrolMap.get(nameCentroAsociado).put(codeCourse, numberEnroled);
			} else {
				LinkedHashMap<String, Integer> codeCourseNumberEnroled = new LinkedHashMap<>();
				codeCourseNumberEnroled.put(codeCourse, numberEnroled);
				centroAsociadoCourseNumberEnrolMap.put(nameCentroAsociado, codeCourseNumberEnroled);
			}
		}
	}

	public int getNumberEnrolment(String nameCentroAsociado, String codeCourse) {
		if (!(codeCourseMap.containsKey(codeCourse))) {
			throw new IllegalArgumentException("Course not exist");
		}
		if (!(nameCentroAsociadoMap.containsKey(nameCentroAsociado))) {
			throw new IllegalArgumentException("CentroAsociado not exist");
		}
		int result = 0;
		if (centroAsociadoCourseNumberEnrolMap.containsKey(nameCentroAsociado)) {
			LinkedHashMap<String, Integer> codeCourseNumberEnroled = centroAsociadoCourseNumberEnrolMap.get(nameCentroAsociado);
			if (codeCourseNumberEnroled.containsKey(codeCourse)) {
				result = codeCourseNumberEnroled.get(codeCourse);
			}
		}
		return result;
	}
}
