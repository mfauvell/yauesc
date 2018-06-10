package es.uned.yauesc.dataUned;

public class DataCourse {
	
	private int gradeCode;
	private int schoolYear;
	private boolean obligatory;

	public DataCourse(int gradeCode, int schoolYear, boolean obligatory) {
		this.gradeCode = gradeCode;
		this.schoolYear = schoolYear;
		this.obligatory = obligatory;
	}

	public int getGrade() {
		return gradeCode;
	}

	public int getSchoolYear() {
		return schoolYear;
	}

	public boolean isObligatory() {
		return obligatory;
	}

}
