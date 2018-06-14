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

	@Override
	public boolean equals(Object object) {
		if (object == this) {
			return true;
		}
		if (object == null) {
			return false;
		}
		if (object instanceof Grade) {
			Grade grade = (Grade) object;
			return ((this.code == grade.getCode()) && (this.name.equals(grade.getName())) && (this.years == grade.getYears())
					&& (this.courseList.equals(grade.getAllCourses())));
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return (code + name.hashCode() * 23) / (years + courseList.hashCode());
	}
	
	@Override
	public String toString() {
		StringBuilder gradeString = new StringBuilder();
		gradeString.append("Grade: (");
		gradeString.append("Code: " + code);
		gradeString.append(" Name: " + name);
		gradeString.append(" CourseList: " + courseList.toString());
		gradeString.append(")");
		return gradeString.toString();
	}
}
