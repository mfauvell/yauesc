package es.uned.yauesc.dataUned;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "course")
@XmlType(propOrder = {"code", "name"})
public class Course {
	
	@XmlElement(name = "code")
	private String code;
	@XmlElement(name = "name")
	private String name;
	private List<DataCourse> dataCourseList;
	
	public Course() {
		
	}

	public Course(String code, String name) {
		this.code = code;
		this.name = name;
		dataCourseList = new ArrayList<>();
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public List<DataCourse> getDataCourseList() {
		return dataCourseList;
	}

	public void addDataCourse(DataCourse dataCourse) {
		if (!dataCourseList.contains(dataCourse)) {
			dataCourseList.add(dataCourse);
		}
	}
	
	@Override
	public boolean equals(Object object) {
		if (object == this) {
			return true;
		}
		if (object == null) {
			return false;
		}
		if (object instanceof Course) {
			Course course = (Course) object;
			return ((this.code.equals(course.getCode())) && (this.name.equals(course.getName())) && this.dataCourseList.equals(course.getDataCourseList()));
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return (code.hashCode() + name.hashCode() -23) / dataCourseList.hashCode();
	}
	
	@Override
	public String toString() {
		StringBuilder courseString = new StringBuilder();
		courseString.append("Course: (");
		courseString.append("Code: " + code);
		courseString.append(" Name: " + name);
		courseString.append(dataCourseList.toString());
		courseString.append(")");
		return courseString.toString();
	}
}
