package es.uned.yauesc.dataUned;

public class Grade {
	
	private String code;
	private String name;
	private int years;

	public Grade(String code, String name, int years) {
		this.code = code;
		this.name = name;
		this.years = years;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
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
			return ((this.code.equals(grade.getCode())) && (this.name.equals(grade.getName())) && (this.years == grade.getYears()));
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return (code.hashCode() + name.hashCode() * 23) / (years);
	}
	
	@Override
	public String toString() {
		StringBuilder gradeString = new StringBuilder();
		gradeString.append("Grade: (");
		gradeString.append("Code: " + code);
		gradeString.append(" Name: " + name);
		gradeString.append(")");
		return gradeString.toString();
	}
}
