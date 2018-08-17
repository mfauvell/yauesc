package es.uned.yauesc.dataUned;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Clase que encapsula todo la información relevante sobre la UNED en la aplicación
 */
public class DataUned {
	
	private ArrayList<String> idCodeCourseList;
	private LinkedHashMap<String, Course> codeCourseMap;
	private int courseNumber;
	
	private LinkedHashMap<String, Grade> codeGradeMap;
	private LinkedHashMap<String, List<String>> codeGradeListCourseCodeMap;
	
	private LinkedHashMap<String, CentroAsociado> nameCentroAsociadoMap;
	
	private ArrayList<ExamTime> idExamTimeList;
	private LinkedHashMap<Integer, List<ExamTime>> dayListExamTimeMap;
	private int examTimeNumber;
	
	private LinkedHashMap<String, LinkedHashMap<String, Integer>> centroAsociadoCourseNumberEnrolMap;
	
	/**
	 * Constructro que crea una instancia de DataUned sin contenido
	 */
	public DataUned() {
		idCodeCourseList =  new ArrayList<>();
		codeCourseMap = new LinkedHashMap<>();
		courseNumber = 0;
		
		codeGradeMap = new LinkedHashMap<>();
		codeGradeListCourseCodeMap = new LinkedHashMap<>();
		
		nameCentroAsociadoMap = new LinkedHashMap<>();
		
		idExamTimeList = new ArrayList<>();
		dayListExamTimeMap = new LinkedHashMap<>();
		examTimeNumber = 0;
		
		centroAsociadoCourseNumberEnrolMap = new LinkedHashMap<>();
	}

	/**
	 * Añade asignatura y todos sus datos, si la asignatura ya existe le añade solo el DataCourse correspondiente
	 * 
	 * @param code			el código de la asignatura
	 * @param name			el nombre de la asignatura
	 * @param codeGrade		el código del grado al cual pertenece la asignatura
	 * @param year			el año al cual pertenece la asignatura
	 * @param obligatory	si la asignatura es obligatoria o no
	 */
	public void addCourse(String code, String name, String codeGrade, int  year, boolean obligatory) {
		Course course;
		if (idCodeCourseList.contains(code)) {
			course = codeCourseMap.get(code);
		} else {
			course = DataUnedFactory.getCourse(code, name);
			idCodeCourseList.add(code);
			codeCourseMap.put(code, course);
			courseNumber++;
		}
		DataCourse dataCourse = DataUnedFactory.getDataCourse(codeGrade, year, obligatory);
		if (!course.getDataCourseList().contains(dataCourse)) {
			course.addDataCourse(dataCourse);
			codeGradeListCourseCodeMap.get(codeGrade).add(code);	
		}
	}

	/**
	 * Devuelve una asignatura usando su ID
	 * 
	 * @param 	id de la asignatura
	 * 
	 * @return	la asignatura correspondiente	
	 */
	public Course getCourse(int id) {
		Course result = null;
		if (courseNumber > id) {
			result = codeCourseMap.get(idCodeCourseList.get(id));
		}
		return result;
	}
	
	/**
	 * Devuelve el total de asignaturas introducidas
	 * 
	 * @return	el número de asignaturas en el sistema
	 */
	public int getNumberCourses() {
		return courseNumber;
	}
	
	/**
	 * Obtiene una lista de código de asignatura más el nombre de todas las asignaturas incluidas en el sistema ordenadas alfabéticamente por el nombre de la asignatura
	 * 
	 * @return	una lista de string compuesta de código asignatura + nombre
	 */
	public List<String> getCodeNameCourseList(){
		List<String> result = codeCourseMap.keySet().parallelStream().map(code -> code + " - " + codeCourseMap.get(code).getName()).collect(Collectors.toList());
		Comparator<String> comparator = new Comparator<String>() {

			@Override
			public int compare(String first, String second) {
				String firstTrim = first.substring(11, first.length());
				String secondTrim = second.substring(11, second.length());
				return firstTrim.compareTo(secondTrim);
			}
			
		};
		Collections.sort(result, comparator);
		return result;
	}

	/**
	 * Añade un grado al sistema
	 * 
	 * @param code		código del grado
	 * @param name		nombre del grado
	 * @param years		años que dura el grado
	 */
	public void addGrade(String code, String name, int years) {
		Grade grade = DataUnedFactory.getGrade(code, name, years);
		codeGradeMap.put(code, grade);
		ArrayList<String> listCourses = new ArrayList<>();
		codeGradeListCourseCodeMap.put(code, listCourses);
	}

	/**
	 * Obtiene un grado a partir de su código
	 * 
	 * @param code		el códido del grado a obtener
	 * 	
	 * @return	el grado el cual corresponde al código pasado
	 */
	public Grade getGrade(String code) {
		return codeGradeMap.get(code);
	}
	
	/**
	 * Obtiene una lista de código de grado más el nombre de todas los grados incluidos en el sistema ordenados alfabéticamente por el nombre del grado
	 * 
	 * @return	una lista de string compuesta de código grado + nombre
	 */
	public List<String> getCodeNameGradeList() {
		List<String> result = codeGradeMap.keySet().parallelStream().map(code -> code + " - " + codeGradeMap.get(code).getName()).collect(Collectors.toList());
		Comparator<String> comparator = new Comparator<String>() {

			@Override
			public int compare(String first, String second) {
				String firstTrim = first.substring(7, first.length());
				String secondTrim = second.substring(7, second.length());
				return firstTrim.compareTo(secondTrim);
			}
			
		};
		Collections.sort(result, comparator);
		return result;
	}

	/**
	 * Obtiene una lista con todos las asignaturas de un grado
	 * 
	 * @param codeGrade	el código del grado
	 * 
	 * @return	una lista de asignaturas
	 */
	public List<Course> getAllCourseGrade(String codeGrade) {
		return codeGradeListCourseCodeMap.get(codeGrade)
				.parallelStream()
				.map(codeCourse -> codeCourseMap.get(codeCourse))
				.collect(Collectors.toList());
	}
	
	/**
	 * Obtiene una lista con todas las asignaturas de un grado y un año concreto
	 * 
	 * @param codeGrade		el código del grado
	 * @param year			el año del grado
	 * 
	 * @return	una lista de asignaturas
	 */
	public List<Course> getCourseGradeYear(String codeGrade, int year) {
		return codeGradeListCourseCodeMap.get(codeGrade)
				.parallelStream()
				.map(codeCourse -> codeCourseMap.get(codeCourse))
				.filter(course -> !course.getDataCourseList()
						.parallelStream()
						.filter(dataCourse -> dataCourse.getGrade().equals(codeGrade))
						.filter(dataCourseFiltered -> dataCourseFiltered.getSchoolYear() == year)
						.collect(Collectors.toList()).isEmpty())
				.collect(Collectors.toList());
	}
	
	/**
	 * Añade un centro asociado al sistema
	 * 
	 * @param name		nombre del centro asociado
	 * @param capacity	capacidad del centro asociado
	 */
	public void addCentroAsociado(String name, int capacity) {
		CentroAsociado centroAsociado = DataUnedFactory.getCentroAsociado(name, capacity);
		nameCentroAsociadoMap.put(centroAsociado.getName(), centroAsociado);
	}

	/**
	 * Obtiene una lista con todos los centros asociados
	 * 
	 * @return	una lista con todos los centros asociadod
	 */
	public List<CentroAsociado> getCentroAsociadoList() {
		return new ArrayList<>(nameCentroAsociadoMap.values());
	}

	/**
	 * Obtiene un centro asociado a partir de su nombre
	 * 
	 * @param name	el nombre del centro asociado
	 * 
	 * @return	el centro asocidado búscado
	 */
	public CentroAsociado getCentroAsociado(String name) {
		return nameCentroAsociadoMap.get(name);
	}

	/**
	 * Añade una fecha de examen al sistema
	 * 
	 * @param day		dia numérico de la fecha de examen
	 * @param dayText	texto relacionado con el día del examen
	 * @param hour		hora numérica de la fecha de examen
	 * @param hourText	texto relacionado con el día del examen
	 */
	public void addExamTime(int day, String dayText, int hour, String hourText) {
		ExamTime examTime = DataUnedFactory.getExamTime(day, dayText, hour, hourText);
		if (!idExamTimeList.contains(examTime)) {
			idExamTimeList.add(examTime);
			if (dayListExamTimeMap.containsKey(day)) {
				dayListExamTimeMap.get(day).add(examTime);
			} else {
				List<ExamTime> examTimeDayList = new ArrayList<>();
				examTimeDayList.add(examTime);
				dayListExamTimeMap.put(day, examTimeDayList);
			}
			examTimeNumber++;
		}
	}
	
	/**
	 * Obtiene una lista con todas las fechas de examen
	 * 
	 * @return	una lista con todas las fechas de examen
	 */
	public List<ExamTime> getExamTimeList() {
		return idExamTimeList;
	}

	/**
	 * Obtiene el número total de fehcas de examen
	 * 
	 * @return	el número total de fechas de examen
	 */
	public int getNumberExamTime() {
		return examTimeNumber;
	}

	/**
	 * Obtiene las fechas de examen disponibles el día solicitado
	 * 
	 * @param day	día del cual obtener las fechas de examen
	 * 
	 * @return	una lista de fechas de examen
	 */
	public List<ExamTime> getExamTimeDay(int day) {
		return dayListExamTimeMap.get(day);
	}

	/**
	 * Obtiene una fecha de examen por su ID
	 * 
	 * @param id	id de la fecha de examen
	 * 
	 * @return	una fecha de examen
	 */
	public ExamTime getExamTime(int id) {
		ExamTime result = null;
		if (examTimeNumber > id) {
			result = idExamTimeList.get(id);
		}
		return result;
	}

	/**
	 * Añade matrículados de una asignatura en un centro asociado
	 * 
	 * @param nameCentroAsociado	nombre del centro asociado
	 * @param codeCourse			código de la asignatura
	 * @param numberEnroled			número de matriculados
	 */
	public void addEnrolment(String nameCentroAsociado, String codeCourse, int numberEnroled) {
		if (codeCourseMap.containsKey(codeCourse) && nameCentroAsociadoMap.containsKey(nameCentroAsociado)) {
			if (centroAsociadoCourseNumberEnrolMap.containsKey(nameCentroAsociado)) {
				centroAsociadoCourseNumberEnrolMap.get(nameCentroAsociado).put(codeCourse, numberEnroled);
			} else {
				LinkedHashMap<String, Integer> codeCourseNumberEnroled = new LinkedHashMap<>();
				codeCourseNumberEnroled.put(codeCourse, numberEnroled);
				centroAsociadoCourseNumberEnrolMap.put(nameCentroAsociado, codeCourseNumberEnroled);
			}
		}
	}

	/**
	 * Obtiene el número de matriculados de una asignatura en un centro asociado
	 * 
	 * @param nameCentroAsociado	nombre del centro asociado
	 * @param codeCourse			código de la asignatura
	 * 
	 * @return	el número de matriculados
	 */
	public int getNumberEnrolment(String nameCentroAsociado, String codeCourse) {
		if (!(codeCourseMap.containsKey(codeCourse))) {
			throw new IllegalArgumentException("Course not exist");
		}
		if (!(nameCentroAsociadoMap.containsKey(nameCentroAsociado))) {
			throw new IllegalArgumentException("CentroAsociado not exist");
		}
		int result = 0;
		if (centroAsociadoCourseNumberEnrolMap.containsKey(nameCentroAsociado)) {
			LinkedHashMap<String, Integer> codeCourseNumberEnroled = centroAsociadoCourseNumberEnrolMap.get(nameCentroAsociado);
			if (codeCourseNumberEnroled.containsKey(codeCourse)) {
				result = codeCourseNumberEnroled.get(codeCourse);
			}
		}
		return result;
	}
}
