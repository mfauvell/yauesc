package es.uned.yauesc.dataUned;

public class ExamTime {
	
	private int id;
	private int day;
	private int hour;
	private String dayName;
	private String hourName;

	public ExamTime(int id, int day, String dayName, int hour, String hourName) {
		this.id = id;
		this.day = day;
		this.hour = hour;
		this.dayName = dayName;
		this.hourName = hourName;
	}

	public int getId() {
		return id;
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

}
