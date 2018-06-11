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

}
