package es.uned.yauesc.dataUned;

public class Course {
	
	private int id;
	private int code;
	private String name;
	private DataCourse dataCourse;

	public Course(int id, int code, String name, DataCourse dataCourse) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.dataCourse = dataCourse;
	}

	public int getId() {
		return id;
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
