package es.uned.yauesc.dataUned;

import java.io.FileReader;
import java.io.IOException;

import com.opencsv.*;

public class UnedParser {
	
	CSVParser csvParser;
	
	public UnedParser() {
		csvParser = new CSVParserBuilder()
				.withSeparator(';')
				.withQuoteChar('"')
				.build();
	}

	public DataUned parseData(String fileGradePath, String fileCentroAsociadoPath, String fileExamTimePath,
			String fileCoursePath, String fileEnrolmentPath, DataUned dataUned) throws IOException {
		dataUned = parseGrade(fileGradePath, dataUned);
		dataUned = parseCentroAsociado(fileCentroAsociadoPath, dataUned);
		dataUned = parseExamTime(fileExamTimePath, dataUned);
		dataUned = parseCourse(fileCoursePath, dataUned);
		dataUned = parseEnrolment(fileEnrolmentPath, dataUned);
		return dataUned;
	}

	private DataUned parseEnrolment(String fileEnrolmentPath, DataUned dataUned) throws IOException {
		CSVReader csvReader = new CSVReaderBuilder(new FileReader(fileEnrolmentPath))
				.withSkipLines(1)
				.withCSVParser(csvParser)
				.build();
		String[] nextLine;
		while ((nextLine = csvReader.readNext()) != null) {
			String nameCentroAsociado = nextLine[2];
			String codeCourse = nextLine[4].substring(0, 8);
			int numberEnrolment = Integer.parseInt(nextLine[6]);
			dataUned.addEnrolment(nameCentroAsociado, codeCourse, numberEnrolment);
		}
		return dataUned;
	}

	private DataUned parseCourse(String fileCoursePath, DataUned dataUned) throws IOException {
		CSVReader csvReader = new CSVReaderBuilder(new FileReader(fileCoursePath))
				.withSkipLines(1)
				.withCSVParser(csvParser)
				.build();
		String[] nextLine;
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
		return dataUned;
	}

	private DataUned parseExamTime(String fileExamTimePath, DataUned dataUned) throws IOException {
		CSVReader csvReader = new CSVReaderBuilder(new FileReader(fileExamTimePath))
				.withSkipLines(1)
				.withCSVParser(csvParser)
				.build();
		String[] nextLine;
		while ((nextLine = csvReader.readNext()) != null) {
			int day = Integer.parseInt(nextLine[0]);
			String dayText = nextLine[1];
			int hour = Integer.parseInt(nextLine[2]);
			String hourText = nextLine[3];
			dataUned.addExamTime(day, dayText, hour, hourText);
		}
		return dataUned;
		
	}

	private DataUned parseCentroAsociado(String fileCentroAsociadoPath, DataUned dataUned) throws IOException {
		CSVReader csvReader = new CSVReaderBuilder(new FileReader(fileCentroAsociadoPath))
				.withSkipLines(1)
				.withCSVParser(csvParser)
				.build();
		String[] nextLine;
		while ((nextLine = csvReader.readNext()) != null) {
			String name = nextLine[0];
			int capacity = Integer.parseInt(nextLine[7]);
			dataUned.addCentroAsociado(name, capacity);
		}
		return dataUned;
	}

	private DataUned parseGrade(String fileGradePath, DataUned dataUned) throws IOException {
		CSVReader csvReader = new CSVReaderBuilder(new FileReader(fileGradePath))
				.withSkipLines(1)
				.withCSVParser(csvParser)
				.build();
		String[] nextLine;
		while ((nextLine = csvReader.readNext()) != null) {
			String code = nextLine[0];
			String name = nextLine[1];
			int years = Integer.parseInt(nextLine[2]);
			dataUned.addGrade(code, name, years);
		}
		return dataUned;
	}
	
	
}
