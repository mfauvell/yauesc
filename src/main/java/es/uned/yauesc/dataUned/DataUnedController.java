package es.uned.yauesc.dataUned;

import java.io.IOException;
import java.util.List;

import es.uned.yauesc.geneticAlgorithm.EvaluationFunction;
import es.uned.yauesc.geneticAlgorithm.Fitness;

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
	
	public DataUnedController() {
		dataUned = DataUnedFactory.getDataUned();
		unedParser = DataUnedFactory.getUnedParser();
		sizeGenotype = -1;
		numberValuesGen = -1;
	}
	
	public Fitness getOptimalFitness() {
		return optimalFitness;
	}
	
	public FitnessUned getSolutionFitness() {
		return unedSchedule.getFitnessSolution();
	}
	
	public EvaluationFunction getEvaluationFunction() {
		if (evaluationFunctionUned == null) {
			throw new UnsupportedOperationException("DataUned must be parsed first");
		}
		return evaluationFunctionUned;
	}
	
	public int getGenotypeSize() {
		if (sizeGenotype == -1) {
			throw new UnsupportedOperationException("DataUned must be parsed first");
		}
		return sizeGenotype;
	}
	
	public int getNumberValuesGen() {
		if (numberValuesGen == -1) {
			throw new UnsupportedOperationException("DataUned must be parsed first");
		}
		return numberValuesGen;
	}
	
	public List<String> getCodeGrades() {
		return dataUned.getCodeNameGradeList();
	}
	
	public List<String> getCodeCourses() {
		return dataUned.getCodeNameCourseList();
	}
	
	public String getAllScheduleString() {
		return unedSchedule.getStringAllSchedule();
	}
	
	public String getByGradeScheduleString(String grade) {
		return unedSchedule.getStringByGradeSchedule(grade);
	}
	
	public String getByCourseScheduleString(List<String> courseList) {
		return unedSchedule.getStringByCourseSchedule(courseList);
	}
	
	public void createAllSchedule(String filePath, FormatFileExtension format) {
		if (unedSchedule == null) {
			throw new UnsupportedOperationException("UnedSchedule must be created first");
		}
		if (format.equals(FormatFileExtension.CSV)) {
			unedSchedule.createCsvAllSchedule(filePath);
		}
	}
	
	public void createByGradeSchedule(String filePath, String grade, FormatFileExtension format) {
		if (unedSchedule == null) {
			throw new UnsupportedOperationException("UnedSchedule must be created first");
		}
		if (format.equals(FormatFileExtension.CSV)) {
			unedSchedule.createCsvByGradeSchedule(filePath, grade);
		}
	}
	
	public void createByCourseSchedule(String filePath, List<String> courseList, FormatFileExtension format) {
		if (unedSchedule == null) {
			throw new UnsupportedOperationException("UnedSchedule must be created first");
		}
		if (format.equals(FormatFileExtension.CSV)) {
			unedSchedule.createCsvByListCourseSchedule(filePath, courseList);
		}
	}
	
	public void parseData() throws IOException {
		dataUned = unedParser.parseData(fileGradePath, fileCentroAsociadoPath, fileExamTimePath, fileCoursePath, fileEnrolmentPath, dataUned);
		setEvaluationFunction();
		sizeGenotype = dataUned.getNumberCourses();
		numberValuesGen = dataUned.getNumberExamTime();
	}
	
	public void setPercentagePresented(double percentagePresented) {
		this.percentagePresented = percentagePresented;
	}
	
	public void setUnedSchedule(List<Integer> solution, FitnessUned fitnessSolution) {
		unedSchedule = DataUnedFactory.getUnedSchedule(solution, fitnessSolution, dataUned);
	}
	
	public void setOptimalFitness(int first, int second, int third) {
		optimalFitness = DataUnedFactory.getFitnessUned(first, second, third);
	}
	
	public void setFileGradePath(String fileGradePath) {
		this.fileGradePath = fileGradePath;
	}
	
	public void setFileCentroAsociadoPath(String fileCentroAsociadoPath) {
		this.fileCentroAsociadoPath = fileCentroAsociadoPath;
	}
	
	public void setFileExamTimePath(String fileExamTimePath) {
		this.fileExamTimePath = fileExamTimePath;
	}
	
	public void setFileCoursePath(String fileCoursePath) {
		this.fileCoursePath = fileCoursePath;
	}
	
	public void setFileEnrolmentPath(String fileEnrolmentPath) {
		this.fileEnrolmentPath = fileEnrolmentPath;
	}
	
	private void setEvaluationFunction() {
		evaluationFunctionUned = DataUnedFactory.getEvaluationFunctionUned(dataUned, percentagePresented);
	}
}
