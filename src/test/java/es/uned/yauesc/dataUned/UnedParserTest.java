package es.uned.yauesc.dataUned;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class UnedParserTest {

	@Test
	public void testObtainDataFromFiles() throws IOException {
		ClassLoader classLoader = getClass().getClassLoader();
		
		String fileGradePath = classLoader.getResource("GradosSample.csv").getPath();
		String fileCentroAsociadoPath = classLoader.getResource("centroAsociadoCapacidadSample.csv").getPath();
		String fileExamTimePath = classLoader.getResource("ExamTimeSample.csv").getPath();
		String fileCoursePath = classLoader.getResource("AsignaturaGradosSample.csv").getPath();
		String fileEnrolmentPath = classLoader.getResource("MatriculasCentroAsignaturaSample.csv").getPath();
		
		DataUned dataUned = mock(DataUned.class);
		
		DataUnedParser unedParser = new DataUnedParser();
		
		assertThat(unedParser.parseData(fileGradePath, fileCentroAsociadoPath, fileExamTimePath, fileCoursePath, fileEnrolmentPath, dataUned)).isEqualTo(dataUned);
		
		verify(dataUned).addGrade("1000", "Grade One", 4);
		verify(dataUned).addGrade("1002", "Grade Two", 4);
		
		verify(dataUned).addCentroAsociado("City 1", 303);
		verify(dataUned).addCentroAsociado("City 2", 136);
		
		verify(dataUned).addExamTime(1, "Monday", 1, "09:00:00");
		verify(dataUned).addExamTime(1, "Monday", 2, "11:30:00");
		verify(dataUned).addExamTime(2, "Tuesday", 1, "09:00:00");
		verify(dataUned).addExamTime(2, "Tuesday", 2, "11:30:00");
		
		verify(dataUned).addCourse("10011111", "Course One", "1000", 1, true);
		verify(dataUned).addCourse("10011112", "Course Two", "1000", 3, true);
		verify(dataUned).addCourse("1002565-", "Course Three", "1002", 2, true);
		verify(dataUned).addCourse("10020001", "Course Four", "1002", 4, false);
		
		verify(dataUned).addEnrolment("City 1", "10011111", 8);
		verify(dataUned).addEnrolment("City 1", "1002565-", 15);
		verify(dataUned).addEnrolment("City 2", "10011111", 4);
		verify(dataUned).addEnrolment("City 2", "10020001", 23);
	}

}
