package es.uned.yauesc.dataUned;

/**
 * Clase que encapsula una fecha de examen
 */
public class ExamTime implements Comparable<ExamTime> {
	
	private int day;
	private int hour;
	private String dayName;
	private String hourName;

	/**
	 * Constructor por defecto que asigna un día con su nombre y una hora con su nombre
	 * 
	 * @param day
	 * @param dayName
	 * @param hour
	 * @param hourName
	 */
	public ExamTime(int day, String dayName, int hour, String hourName) {
		this.day = day;
		this.hour = hour;
		this.dayName = dayName;
		this.hourName = hourName;
	}

	/**
	 * Devuelve el día numérico que al que pertenece la fecha de examen
	 * 
	 * @return day
	 */
	public int getDay() {
		return day;
	}

	/**
	 * Devuelve el texto asociado al día numérico
	 * 
	 * @return dayName
	 */
	public String getDayName() {
		return dayName;
	}

	/**
	 * Devuelve la hora numérica a la que se realizará el examen
	 * 
	 * @return	hour
	 */
	public int getHour() {
		return hour;
	}

	/**
	 * Devuelve el texto asociado a la hora numérica 
	 * 
	 * @return
	 */
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
