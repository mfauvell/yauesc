package es.uned.yauesc.dataUned;

public class Course {
	
	private int code;
	private String name;
	private DataCourse dataCourse;

	public Course(int code, String name, DataCourse dataCourse) {
		this.code = code;
		this.name = name;
		this.dataCourse = dataCourse;
	}

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public DataCourse getDataCourse() {
		return dataCourse;
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
			return ((this.code == course.getCode()) && (this.name.equals(course.getName())) && this.dataCourse.equals(course.getDataCourse()));
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return (code + name.hashCode() -23) / dataCourse.hashCode();
	}
	
	@Override
	public String toString() {
		StringBuilder courseString = new StringBuilder();
		courseString.append("Course: (");
		courseString.append("Code: " + code);
		courseString.append(" Name: " + name);
		courseString.append(dataCourse.toString());
		courseString.append(")");
		return courseString.toString();
	}
}
