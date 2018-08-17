package es.uned.yauesc.dataUned;

import java.util.List;

/**
 * Factoria estática para el paquete dataUned
 */
public final class DataUnedFactory {
	
	private DataUnedFactory() {
		
	}
	
	/**
	 * Obtiene una instancia de FitnessUned
	 * 
	 * @param firstLevel	primer nivel de la adecuación
	 * @param secondLevel 	secundo nivel de la adecuacion
	 * @param thirdLevel	tercer nivel de la adecuación
	 * 
	 * @return	una instancia de FitnessUned
	 */
	public static FitnessUned getFitnessUned(int firstLevel, int secondLevel, int thirdLevel) {
		return new FitnessUned(firstLevel, secondLevel, thirdLevel);
	}
	
	/**
	 * Obtiene una instancia de EvaluationFunctionUned
	 * 
	 * @param dataUned				una instancia de dataUned con los datos de la UNED
	 * @param percentagePresented	el porcentaje de presentados esperados por semana
	 * 
	 * @return	una instancia de EvaluationFunctionUned
	 */
	public static EvaluationFunctionUned getEvaluationFunctionUned(DataUned dataUned, double percentagePresented) {
		return new EvaluationFunctionUned(dataUned, percentagePresented);
	}
	
	/**
	 * Obtiene una instancia de DataUned
	 * 
	 * @return	una instancia de DataUned
	 */
	public static DataUned getDataUned() {
		return new DataUned();
	}
	
	/**
	 * Obtiene una instancia de CentroAsociado
	 * 
	 * @param name		nombre del centro asociado
	 * @param capacity	capacidad del centro asociado
	 * 
	 * @return	una instancia de CentroAsociado
	 */
	public static CentroAsociado getCentroAsociado(String name, int capacity) {
		return new CentroAsociado(name, capacity);
	}
	
	/**
	 * Obtiene una instancia de Course
	 * 
	 * @param code	código de la asignatura
	 * @param name	nombre de la asignatura
	 * 
	 * @return	una instancia de Course
	 */
	public static Course getCourse(String code, String name) {
		return new Course(code, name);
	}
	
	/**
	 * Obtiene una instancia de DataCourse
	 * 
	 * @param gradeCode		código del grado a que pertenece
	 * @param schoolYear	curso al que pertenece
	 * @param obligatory	true si es obligatoria
	 * 
	 * @return una instancia de DataCourse
	 */
	public static DataCourse getDataCourse(String gradeCode, int schoolYear, boolean obligatory) {
		return new DataCourse(gradeCode, schoolYear, obligatory);
	}
	
	/**
	 * Obtiene una instancia de Grade
	 * 
	 * @param code		código del grado
	 * @param name		nombre del grado
	 * @param years		años del grado
	 * 
	 * @return	una instancia de Grade
	 */
	public static Grade getGrade(String code, String name, int years) {
		return new Grade(code, name, years);
	}
	
	/**
	 * Obtiene una instancia de ExamTime	
	 * 
	 * @param day		el orden numérico del día del examen
	 * @param dayName	el texto que acompaña el día del examen
	 * @param hour		el orden numérico de la hora del examen
	 * @param hourName	el texto que acompaña la hora del examen
	 * 
	 * @return una instancia de Examtime
	 */
	public static ExamTime getExamTime(int day, String dayName, int hour, String hourName) {
		return new ExamTime(day, dayName, hour, hourName);
	}
	
	/**
	 * Obtiene una instancia de UnedParser
	 * 
	 * @return	una instancia de UnedParser
	 */
	public static DataUnedParser getUnedParser() {
		return new DataUnedParser();
	}
	
	/**
	 * Obtiene una instancia de DataUnedSchedule
	 * 
	 * @param solution			Genoma de la solución encontrada
	 * @param fitnessSolution	Adecuación de la solución encontrada
	 * @param dataUned			una instancia de DataUned con todos los datos necesarios cargados
	 * 
	 * @return	una instancia de DataUnedSchedule
	 */
	public static DataUnedSchedule getUnedSchedule(List<Integer> solution, FitnessUned fitnessSolution, DataUned dataUned) {
		return new DataUnedSchedule(solution, fitnessSolution, dataUned);
	}

}
