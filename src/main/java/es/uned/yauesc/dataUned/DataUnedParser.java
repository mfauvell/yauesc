package es.uned.yauesc.dataUned;

import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.opencsv.*;

/**
 * Clase que encapsula la lógica de introducir los datos de la UNED en la aplicación
 */
public class DataUnedParser {
	
	CSVParser csvParser;
	
	private final static Logger LOGGER = Logger.getLogger(DataUnedParser.class.getName());
	
	/**
	 * Constructor por defecto de DataUnedParser
	 */
	public DataUnedParser() {
		csvParser = new CSVParserBuilder()
				.withSeparator(';')
				.withQuoteChar('"')
				.build();
	}

	/**
	 * Parse data from files in format CSV to dataUned
	 * 
	 * @param fileGradePath				ruta al archivo con los datos de los grados curasados en la UNED
	 * @param fileCentroAsociadoPath	ruta al archivo con los datos de los centros asociados de la UNED
	 * @param fileExamTimePath			ruta al archivo con las fechas de examenes
	 * @param fileCoursePath			ruta al archivo con los datos de las asignaturas impartidas en la UNED
	 * @param fileEnrolmentPath			ruta al archivo con los datos de matriculas en la UNED
	 * @param dataUned					instancia de la clase contenedora de los datos de la UNED
	 * 
	 * @return 	una instancia de dataUNED con los datos incluidos de la UNED
	 * 
	 * @throws FileGradeException
	 * @throws FileCentroAsociadoException
	 * @throws FileExamTimeException
	 * @throws FileCourseException
	 * @throws FileEnrolmentException
	 */
	public DataUned parseData(String fileGradePath, String fileCentroAsociadoPath, String fileExamTimePath,
			String fileCoursePath, String fileEnrolmentPath, DataUned dataUned) throws FileGradeException, 
			FileCentroAsociadoException, FileExamTimeException, FileCourseException, FileEnrolmentException {
		LOGGER.log(Level.INFO, "Started parse data");
		dataUned = parseGrade(fileGradePath, dataUned);
		dataUned = parseCentroAsociado(fileCentroAsociadoPath, dataUned);
		dataUned = parseExamTime(fileExamTimePath, dataUned);
		dataUned = parseCourse(fileCoursePath, dataUned);
		dataUned = parseEnrolment(fileEnrolmentPath, dataUned);
		LOGGER.log(Level.INFO, "Finished parse data");
		return dataUned;
	}

	private DataUned parseEnrolment(String fileEnrolmentPath, DataUned dataUned) throws FileEnrolmentException {
		String[] nextLine = null;
		try {
			CSVReader csvReader = new CSVReaderBuilder(new FileReader(fileEnrolmentPath))
				.withSkipLines(1)
				.withCSVParser(csvParser)
				.build();
			while ((nextLine = csvReader.readNext()) != null) {
				String nameCentroAsociado = nextLine[2];
				String codeCourse = nextLine[4].substring(0, 8);
				int numberEnrolment = Integer.parseInt(nextLine[6]);
				dataUned.addEnrolment(nameCentroAsociado, codeCourse, numberEnrolment);
			}
		} catch (NumberFormatException e) {
			LOGGER.log(Level.SEVERE, "Data format invalid at " + fileEnrolmentPath);
			LOGGER.log(Level.SEVERE, nextLine.toString());
			throw new FileEnrolmentException("Error on file with Enrolment data");
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "File error on: " + fileEnrolmentPath);
			throw new FileEnrolmentException("Error on file with Enrolment data");
		}
		return dataUned;
	}

	private DataUned parseCourse(String fileCoursePath, DataUned dataUned) throws FileCourseException {
		String[] nextLine = null;
		try {
			CSVReader csvReader = new CSVReaderBuilder(new FileReader(fileCoursePath))
				.withSkipLines(1)
				.withCSVParser(csvParser)
				.build();
			while ((nextLine = csvReader.readNext()) != null) {
				String code = nextLine[2];
				String name = nextLine[3];
				String gradeCode = nextLine[0];
				int year = Integer.parseInt(nextLine[4]);
				boolean obligatory = false;
				if (!nextLine[5].equals("Optativa")) {
					obligatory = true;
				}
				dataUned.addCourse(code, name, gradeCode, year, obligatory);
			}
		} catch (NumberFormatException e) {
			LOGGER.log(Level.SEVERE, "Data format invalid at " + fileCoursePath);
			LOGGER.log(Level.SEVERE, nextLine.toString());
			throw new FileCourseException("Error on file with Course data");
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "File error on: " + fileCoursePath);
			throw new FileCourseException("Error on file with Course data");
		}
		return dataUned;
	}

	private DataUned parseExamTime(String fileExamTimePath, DataUned dataUned) throws FileExamTimeException {
		String[] nextLine = null;
		try {
			CSVReader csvReader = new CSVReaderBuilder(new FileReader(fileExamTimePath))
				.withSkipLines(1)
				.withCSVParser(csvParser)
				.build();
			while ((nextLine = csvReader.readNext()) != null) {
				int day = Integer.parseInt(nextLine[0]);
				String dayText = nextLine[1];
				int hour = Integer.parseInt(nextLine[2]);
				String hourText = nextLine[3];
				dataUned.addExamTime(day, dayText, hour, hourText);
			}
		} catch (NumberFormatException e) {
			LOGGER.log(Level.SEVERE, "Data format invalid at " + fileExamTimePath);
			LOGGER.log(Level.SEVERE, nextLine.toString());
			throw new FileExamTimeException("Error on file with ExamTime data");
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "File error on: " + fileExamTimePath);
			throw new FileExamTimeException("Error on file with ExamTime data");
		}
		return dataUned;
		
	}

	private DataUned parseCentroAsociado(String fileCentroAsociadoPath, DataUned dataUned) throws FileCentroAsociadoException {
		String[] nextLine = null;
		try {
			CSVReader csvReader = new CSVReaderBuilder(new FileReader(fileCentroAsociadoPath))
				.withSkipLines(1)
				.withCSVParser(csvParser)
				.build();
			while ((nextLine = csvReader.readNext()) != null) {
				String name = nextLine[0];
				int capacity = Integer.parseInt(nextLine[7]);
				dataUned.addCentroAsociado(name, capacity);
			}
		} catch (NumberFormatException e) {
			LOGGER.log(Level.SEVERE, "Data format invalid at " + fileCentroAsociadoPath);
			LOGGER.log(Level.SEVERE, nextLine.toString());
			throw new FileCentroAsociadoException("Error on file with CentroAsociado data");
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "File error on: " + fileCentroAsociadoPath);
			throw new FileCentroAsociadoException("Error on file with CentroAsociado data");
		} 
		return dataUned;
	}

	private DataUned parseGrade(String fileGradePath, DataUned dataUned) throws FileGradeException {
		String[] nextLine = null;
		try {
			CSVReader csvReader = new CSVReaderBuilder(new FileReader(fileGradePath))
					.withSkipLines(1)
					.withCSVParser(csvParser)
					.build();
			while ((nextLine = csvReader.readNext()) != null) {
				String code = nextLine[0];
				String name = nextLine[1];
				int years = Integer.parseInt(nextLine[2]);
				dataUned.addGrade(code, name, years);
			}
		} catch (NumberFormatException e) {
			LOGGER.log(Level.SEVERE, "Data format invalid at " + fileGradePath);
			LOGGER.log(Level.SEVERE, nextLine.toString());
			throw new FileGradeException("Error on file with Grade data");
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "File error on: " + fileGradePath);
			throw new FileGradeException("Error on file with Grade data");
		}
		return dataUned;
	}
	
}
