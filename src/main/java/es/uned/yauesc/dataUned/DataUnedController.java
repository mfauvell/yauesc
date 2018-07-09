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
	
	public void createCSVAllSchedule(String filePath) {
		if (unedSchedule == null) {
			throw new UnsupportedOperationException("UnedSchedule must be created first");
		}
		unedSchedule.createCsvAllSchedule(filePath);
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
	
	public void setUnedSchedule(List<Integer> solution) {
		unedSchedule = DataUnedFactory.getUnedSchedule(solution, dataUned);
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
