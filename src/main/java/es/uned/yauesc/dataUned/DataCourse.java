package es.uned.yauesc.dataUned;

public class DataCourse {
	
	private String gradeCode;
	private int schoolYear;
	private boolean obligatory;

	public DataCourse(String gradeCode, int schoolYear, boolean obligatory) {
		this.gradeCode = gradeCode;
		this.schoolYear = schoolYear;
		this.obligatory = obligatory;
	}

	public String getGrade() {
		return gradeCode;
	}

	public int getSchoolYear() {
		return schoolYear;
	}

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
