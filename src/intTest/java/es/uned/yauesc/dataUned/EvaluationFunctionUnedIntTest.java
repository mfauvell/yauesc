package es.uned.yauesc.dataUned;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import es.uned.yauesc.geneticAlgorithm.Individual;

class EvaluationFunctionUnedIntTest {

	@Test
	void testEvaluationFunctionOneIndividual() throws FileGradeException, FileCentroAsociadoException, FileExamTimeException, FileCourseException, FileEnrolmentException {
		ClassLoader classLoader = getClass().getClassLoader();
		
		String fileGradePath = classLoader.getResource("GradeSample.csv").getPath();
		String fileCentroAsociadoPath = classLoader.getResource("CentroAsociadoSample.csv").getPath();
		String fileExamTimePath = classLoader.getResource("ExamTimeSample.csv").getPath();
		String fileCoursePath = classLoader.getResource("CourseSample.csv").getPath();
		String fileEnrolmentPath = classLoader.getResource("EnrolmentSample.csv").getPath();
		
		FitnessUned expectedFitness = DataUnedFactory.getFitnessUned(1, 26, 105);
		
		DataUned dataUned = DataUnedFactory.getDataUned();
		DataUnedParser dataUnedParser = DataUnedFactory.getUnedParser();
		
		dataUnedParser.parseData(fileGradePath, fileCentroAsociadoPath, fileExamTimePath, fileCoursePath, fileEnrolmentPath, dataUned);
		

		List<Integer> genotype = new ArrayList<>(Arrays.asList(0,0,2,1,3,2,1,1,3,2,
															   3,2,1,0,0,1,2,3,2,1,
															   3,3,2,2,1,1,0,0,1,1,
															   2,2,3,3,0,1,2,3,0,0));
		Individual firstIndividual = new Individual(genotype);
				
		List<Individual> listIndividual = new ArrayList<>();
		listIndividual.add(firstIndividual);
		
		EvaluationFunctionUned evaluationFunction = DataUnedFactory.getEvaluationFunctionUned(dataUned, 1);
		
		List<Individual> result = new ArrayList<>(evaluationFunction.evaluate(listIndividual));
		
		assertThat(result.get(0).getFitness()).isEqualTo(expectedFitness);
	}

}
