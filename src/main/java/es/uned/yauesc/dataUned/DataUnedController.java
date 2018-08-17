package es.uned.yauesc.dataUned;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import es.uned.yauesc.geneticAlgorithm.EvaluationFunction;
import es.uned.yauesc.geneticAlgorithm.Fitness;

/**
 * Clase controladora que sirve de interface para el modelo dataUned
 */
public class DataUnedController {

	private DataUned dataUned;
	private DataUnedSchedule unedSchedule;
	private DataUnedParser unedParser;
	private EvaluationFunctionUned evaluationFunctionUned;
	private FitnessUned optimalFitness;
	private double percentagePresented;
	
	private int sizeGenotype;
	private int numberValuesGen;
	
	private String fileGradePath;
	private String fileCentroAsociadoPath;
	private String fileExamTimePath;
	private String fileCoursePath;
	private String fileEnrolmentPath;
	
	private final static Logger LOGGER = Logger.getLogger(DataUnedSchedule.class.getName());
	
	/**
	 * Constructor por defecto que inicializa el controlador
	 */
	public DataUnedController() {
		dataUned = DataUnedFactory.getDataUned();
		unedParser = DataUnedFactory.getUnedParser();
		sizeGenotype = -1;
		numberValuesGen = -1;
	}
	
	/**
	 * Obtiene la adecuación óptima configurada
	 * 
	 * @return	optimalFitness
	 */
	public Fitness getOptimalFitness() {
		return optimalFitness;
	}
	
	/**
	 * Obtiene la adecuación de la solución hallada.
	 * 
	 * @return	adecuación de la solución
	 */
	public FitnessUned getSolutionFitness() {
		return unedSchedule.getFitnessSolution();
	}
	
	/**
	 * Obtiene la función de evaluación correspondiente a la UNED si ha sido ya configurada.
	 * 
	 * @return	evaluationFunctionUned
	 */
	public EvaluationFunction getEvaluationFunction() {
		if (evaluationFunctionUned == null) {
			LOGGER.log(Level.SEVERE, "EvaluationFunctionUned not set yet");
			throw new UnsupportedOperationException("DataUned must be parsed first");
		}
		return evaluationFunctionUned;
	}
	
	/**
	 * Obtiene el tamaño del genotipo
	 * 
	 * @return	sizeGenotype
	 */
	public int getGenotypeSize() {
		if (sizeGenotype == -1) {
			LOGGER.log(Level.SEVERE, "Genotype size not set yet");
			throw new UnsupportedOperationException("DataUned must be parsed first");
		}
		return sizeGenotype;
	}
	
	/**
	 * Obtiene el tamaño de variedad genética
	 * 
	 * @return	numberValuesGen
	 */
	public int getNumberValuesGen() {
		if (numberValuesGen == -1) {
			LOGGER.log(Level.SEVERE, "Number values gen not set yet");
			throw new UnsupportedOperationException("DataUned must be parsed first");
		}
		return numberValuesGen;
	}
	
	/**
	 * Obtiene una lista de strings con el código de un grado y su nombre por elemento
	 * 
	 * @return	una lista de strings en que cada elemento es el código de un grado y su nombre
	 */
	public List<String> getCodeGrades() {
		return dataUned.getCodeNameGradeList();
	}
	
	/**
	 * Obtiene una lista de strings con el código de un asignatura y su nombre
	 * 
	 * @return	una lista de strings en que cada elemento es el código de una asignatura y su nombre
	 */
	public List<String> getCodeCourses() {
		return dataUned.getCodeNameCourseList();
	}
	
	/**
	 * Obtiene un string con todo el calendario hayado como solución
	 * 
	 * @return un string con el calendario solución sin filtro
	 */
	public String getAllScheduleString() {
		if (unedSchedule == null) {
			LOGGER.log(Level.SEVERE, "Uned schedule is not set yet");
			throw new UnsupportedOperationException("UnedSchedule must be created first");
		}
		return unedSchedule.getStringAllSchedule();
	}
	
	/**
	 * Obtiene un string con el calendario hayado como solución filtrado por grado
	 * 
	 * @param grade	el grado por el cual se filtra
	 * 
	 * @return	un string con el calendario solución filtrado por grado
	 */
	public String getByGradeScheduleString(String grade) {
		if (unedSchedule == null) {
			LOGGER.log(Level.SEVERE, "Uned schedule is not set yet");
			throw new UnsupportedOperationException("UnedSchedule must be created first");
		}
		return unedSchedule.getStringByGradeSchedule(grade);
	}
	
	/**
	 * Obtiene un string con el calendario hayado como solución filtrado por una lista de cursos
	 * 
	 * @param courseList	la lista de asignaturas para filtrar
	 * 
	 * @return	un string con el calendario solución filtrado por la lista de asignaturas
	 */
	public String getByCourseScheduleString(List<String> courseList) {
		if (unedSchedule == null) {
			LOGGER.log(Level.SEVERE, "Uned schedule is not set yet");
			throw new UnsupportedOperationException("UnedSchedule must be created first");
		}
		return unedSchedule.getStringByCourseSchedule(courseList);
	}
	
	/**
	 * Obtiene un archivo en el formato especificado con el calendario hayado como solución sin filtrar
	 * 
	 * @param filePath	la ruta del archivo
	 * @param format	el formato del archivo
	 */
	public void createAllSchedule(String filePath, FormatFileExtension format) {
		if (unedSchedule == null) {
			LOGGER.log(Level.SEVERE, "Uned schedule is not set yet");
			throw new UnsupportedOperationException("UnedSchedule must be created first");
		}
		if (format.equals(FormatFileExtension.CSV)) {
			unedSchedule.createCsvAllSchedule(filePath);
		} else if (format.equals(FormatFileExtension.XML)) {
			unedSchedule.createXMLAllSchedule(filePath);
		}
	}
	
	/**
	 * Obtiene un archivo en el formato especificado con el calendario hayado filtrado por grado
	 * 
	 * @param filePath	la ruta del archivo
	 * @param grade		el grado para realizar el filtro
	 * @param format	el formato del archivo
	 */
	public void createByGradeSchedule(String filePath, String grade, FormatFileExtension format) {
		if (unedSchedule == null) {
			LOGGER.log(Level.SEVERE, "Uned schedule is not set yet");
			throw new UnsupportedOperationException("UnedSchedule must be created first");
		}
		if (format.equals(FormatFileExtension.CSV)) {
			unedSchedule.createCsvByGradeSchedule(filePath, grade);
		} else if (format.equals(FormatFileExtension.XML)) {
			unedSchedule.createXMLByGradeSchedule(filePath, grade);
		}
	}
	
	/**
	 * Obtiene un archivo en el formato especificado con el calendario hayado filtrado por una lista de asignaturas
	 * 
	 * @param filePath		la ruta del archivo
	 * @param courseList	la lista de asignaturas para realizar el filtro
	 * @param format		el formato del archivo
	 */
	public void createByCourseSchedule(String filePath, List<String> courseList, FormatFileExtension format) {
		if (unedSchedule == null) {
			LOGGER.log(Level.SEVERE, "Uned schedule is not set yet");
			throw new UnsupportedOperationException("UnedSchedule must be created first");
		}
		if (format.equals(FormatFileExtension.CSV)) {
			unedSchedule.createCsvByListCourseSchedule(filePath, courseList);
		} else if (format.equals(FormatFileExtension.XML)) {
			unedSchedule.createXMLByListCourseSchedule(filePath, courseList);
		}
	}
	
	/**
	 * Realiza la carga de datos de la UNED en el sistema
	 * 
	 * @throws FileGradeException
	 * @throws FileCentroAsociadoException
	 * @throws FileExamTimeException
	 * @throws FileCourseException
	 * @throws FileEnrolmentException
	 */
	public void parseData() throws FileGradeException, FileCentroAsociadoException, FileExamTimeException, FileCourseException, FileEnrolmentException {
		dataUned = unedParser.parseData(fileGradePath, fileCentroAsociadoPath, fileExamTimePath, fileCoursePath, fileEnrolmentPath, dataUned);
		setEvaluationFunction();
		sizeGenotype = dataUned.getNumberCourses();
		numberValuesGen = dataUned.getNumberExamTime();
	}
	
	/**
	 * Introduce el porcentaje (tanto por uno) de presentados esperado
	 * 
	 * @param percentagePresented	el porcentaje esperado (tanto por uno)
	 */
	public void setPercentagePresented(double percentagePresented) {
		this.percentagePresented = percentagePresented;
	}
	
	/**
	 * Configura DataUnedSchedule con una solución y lo deja disponible
	 * 
	 * @param solution			genotipo de la solución
	 * @param fitnessSolution	adecuación de la solución
	 */
	public void setUnedSchedule(List<Integer> solution, FitnessUned fitnessSolution) {
		unedSchedule = DataUnedFactory.getUnedSchedule(solution, fitnessSolution, dataUned);
	}
	
	/**
	 * Introduce la adecuación óptima en el sistema
	 * 
	 * @param first		el primer nivel de la adecuación
	 * @param second	el segundo nivel de la adecuación
	 * @param third		el tercer nivel de la adecuación
	 */
	public void setOptimalFitness(int first, int second, int third) {
		optimalFitness = DataUnedFactory.getFitnessUned(first, second, third);
	}
	
	/**
	 * Configura la ruta al archivo con los datos sobre los grados UNED
	 * 
	 * @param fileGradePath	la ruta al archivo
	 */
	public void setFileGradePath(String fileGradePath) {
		this.fileGradePath = fileGradePath;
	}
	
	/**
	 * Configura la ruta al archivo con los datos sobre los centros asociados UNED
	 * 
	 * @param fileCentroAsociadoPath la ruta del archivo
	 */
	public void setFileCentroAsociadoPath(String fileCentroAsociadoPath) {
		this.fileCentroAsociadoPath = fileCentroAsociadoPath;
	}
	
	/**
	 * Configura la ruta al archivo con las fechas de examen
	 * 
	 * @param fileExamTimePath la ruta del archivo
	 */
	public void setFileExamTimePath(String fileExamTimePath) {
		this.fileExamTimePath = fileExamTimePath;
	}
	
	/**
	 * Configura la ruta al archivo con los datos sobre asignaturas UNED
	 * 
	 * @param fileCoursePath la ruta del archivo
	 */
	public void setFileCoursePath(String fileCoursePath) {
		this.fileCoursePath = fileCoursePath;
	}
	
	/**
	 * Configura la ruta del archivo con los datos sobre matriculas en la UNED
	 * 
	 * @param fileEnrolmentPath	la ruta del archivo
	 */
	public void setFileEnrolmentPath(String fileEnrolmentPath) {
		this.fileEnrolmentPath = fileEnrolmentPath;
	}
	
	private void setEvaluationFunction() {
		evaluationFunctionUned = DataUnedFactory.getEvaluationFunctionUned(dataUned, percentagePresented);
	}
}
