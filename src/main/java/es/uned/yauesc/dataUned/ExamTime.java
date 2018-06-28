package es.uned.yauesc.dataUned;

public class ExamTime implements Comparable<ExamTime> {
	
	private int day;
	private int hour;
	private String dayName;
	private String hourName;

	public ExamTime(int day, String dayName, int hour, String hourName) {
		this.day = day;
		this.hour = hour;
		this.dayName = dayName;
		this.hourName = hourName;
	}

	public int getDay() {
		return day;
	}

	public String getDayName() {
		return dayName;
	}

	public int getHour() {
		return hour;
	}

	public String getHourName() {
		return hourName;
	}
	
	@Override
	public int compareTo(ExamTime examTime) {
		if (examTime == null) {
			return 1;
		}
		if (examTime.getDay() == day) {
			if (examTime.getHour() == hour) {
				return 0;
			} else if (examTime.getHour() > hour) {
				return -1;
			}
		} else if (examTime.getDay() > day) {
			return -1;
		}
		return 1;
	}

	@Override
	public boolean equals(Object object) {
		if (object == this) {
			return true;
		}
		if (object == null) {
			return false;
		}
		if (object instanceof ExamTime) {
			ExamTime examTime = (ExamTime) object;
			return ((this.day == examTime.getDay()) && (this.dayName.equals(examTime.getDayName())) && (this.hour == examTime.getHour())
					&& (this.hourName.equals(examTime.getHourName())));
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return ((day + hour) * 23) + dayName.hashCode() + hourName.hashCode();
	}
	
	@Override
	public String toString() {
		StringBuilder examTimeString = new StringBuilder();
		examTimeString.append("ExamTime: (");
		examTimeString.append("Day: " + day);
		examTimeString.append(" DayName: " + dayName);
		examTimeString.append(" Hour: " + hour);
		examTimeString.append(" HourName: " + hourName);
		examTimeString.append(")");
		return examTimeString.toString();
	}
}
