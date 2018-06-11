package es.uned.yauesc.dataUned;

import java.util.List;
import java.util.stream.Collectors;

public class Grade {
	
	private int code;
	private String name;
	private int years;
	private List<Course> courseList;

	public Grade(int code, String name, int years, List<Course> courseList) {
		this.code = code;
		this.name = name;
		this.years = years;
		this.courseList = courseList;
	}

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public List<Course> getAllCourses() {
		return courseList;
	}

	public List<Course> getCourseYear(int year) {
		return courseList
				.parallelStream()
				.filter(course -> course.getDataCourse().getSchoolYear() == year)
				.collect(Collectors.toList());
	}

	public int getYears() {
		return years;
	}

}
