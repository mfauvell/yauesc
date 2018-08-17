package es.uned.yauesc.dataUned;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Clase que encapsula una asignatura impartida en la UNED en uno o más grados
 */
@XmlRootElement(name = "course")
@XmlType(propOrder = {"code", "name"})
public class Course {
	
	@XmlElement(name = "code")
	private String code;
	@XmlElement(name = "name")
	private String name;
	private List<DataCourse> dataCourseList;
	
	/**
	 * Constructor simple de Course
	 */
	public Course() {
		dataCourseList = new ArrayList<>();
	}

	/**
	 * Constructor de course que configura un code y un nombre
	 * 
	 * @param code	el código de la asignatura
	 * @param name	el nombre de la asignatura
	 */
	public Course(String code, String name) {
		this();
		this.code = code;
		this.name = name;
	}

	/**
	 * Obtiene el código de la asignatura
	 * 	
	 * @return	code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Obtiene el nombre de la asignatura
	 * 
	 * @return	name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Obtiene de una lista de los DataCourse que posee la asignatura
	 * 
	 * @return	dataCourseList
	 */
	public List<DataCourse> getDataCourseList() {
		return dataCourseList;
	}

	/**
	 * Añade un datacourse a la asignatura evitando que haya duplicados
	 * 
	 * @param dataCourse	el dataCourse a añadir
	 */
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
