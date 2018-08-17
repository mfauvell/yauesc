package es.uned.yauesc.dataUned;

/**
 * Esta clase encapsula las caracteristicas relacionadas con un grado de una asignatura
 */
public class DataCourse {
	
	private String gradeCode;
	private int schoolYear;
	private boolean obligatory;

	/**
	 * Constructor que asigna un grado, un año de curso y si es obligatoria
	 * 
	 * @param gradeCode		código del grado al que pertenece
	 * @param schoolYear	curso al que pertenece
	 * @param obligatory	true si es obligatoria
	 */
	public DataCourse(String gradeCode, int schoolYear, boolean obligatory) {
		this.gradeCode = gradeCode;
		this.schoolYear = schoolYear;
		this.obligatory = obligatory;
	}

	/**
	 * Devuelve el código del grado al que pertenece
	 * 
	 * @return	gradeCode
	 */
	public String getGrade() {
		return gradeCode;
	}

	/**
	 * Devuelve el curso al que pertenece
	 * 
	 * @return	schoolYear
	 */
	public int getSchoolYear() {
		return schoolYear;
	}

	/**
	 * Devuelve si es obligatoria
	 * 
	 * @return obligatory
	 */
	public boolean isObligatory() {
		return obligatory;
	}

	@Override
	public boolean equals(Object object) {
		if (object == this) {
			return true;
		}
		if (object == null) {
			return false;
		}
		if (object instanceof DataCourse) {
			DataCourse dataCourse = (DataCourse) object;
			return ((this.gradeCode.equals(dataCourse.getGrade())) && (this.schoolYear == dataCourse.getSchoolYear()) && (this.obligatory == dataCourse.isObligatory()));
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return ((gradeCode.hashCode() + schoolYear )* 23) + ( obligatory? 0: 1);
	}
	
	@Override
	public String toString() {
		StringBuilder dataCourseString = new StringBuilder();
		dataCourseString.append("DataCourse: (");
		dataCourseString.append("GradeCode: " + gradeCode);
		dataCourseString.append(" SchoolYear: " + schoolYear);
		dataCourseString.append(" Obligatory: " + obligatory);
		dataCourseString.append(")");
		return dataCourseString.toString();
	}
}
