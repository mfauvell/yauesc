package es.uned.yauesc.dataUned;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "schedule")
public class DataUnedSchedule {
	
	private DataUned dataUned;
	private FitnessUned fitnessSolution;
	
	private LinkedHashMap<Course, ExamTime> codeCourseExamTime;
	private LinkedHashMap<ExamTime,List<Course>> examTimeListCourse;
	
	@XmlElement(name = "day")
	private List<XMLDay> listXML;
	
	public DataUnedSchedule() {
		listXML = new ArrayList<>();
	}
	
	public DataUnedSchedule(List<Integer> solution, FitnessUned fitnessSolution, DataUned dataUned) {
		this();
		this.fitnessSolution = fitnessSolution;
		this.dataUned = dataUned;
		parseSolution(solution);
	}

	public FitnessUned getFitnessSolution() {
		return fitnessSolution;
	}
	
	public String getStringAllSchedule() {
		return createString(examTimeListCourse);
	}
	
	public String getStringByGradeSchedule(String grade) {
		return createString(filterByGrade(grade));
	}
	
	public String getStringByCourseSchedule(List<String> courseList) {
		return createString(filterByCourse(courseList));
	}
	
	public void createXMLAllSchedule(String filePath) {
		createXMLData(examTimeListCourse);
		createXmlFile(filePath);
	}
	
	public void createXMLByGradeSchedule(String filePath, String grade) {
		Map<ExamTime, List<Course>> examTimeCourseList = filterByGrade(grade);
		createXMLData(examTimeCourseList);
		createXmlFile(filePath);
	}
	
	public void createXMLByListCourseSchedule(String filePath, List<String> courseList) {
		Map<ExamTime, List<Course>> examTimeCourseList = filterByCourse(courseList);
		createXMLData(examTimeCourseList);
		createXmlFile(filePath);
	}
	
	public void createCsvAllSchedule(String filePath) {
		String content = createCsvString(examTimeListCourse);
		createFile(filePath, content);
	}
	
	public void createCsvByGradeSchedule(String filePath, String grade) {
		Map<ExamTime, List<Course>> examTimeCourseList = filterByGrade(grade);
		String content = createCsvString(examTimeCourseList);
		createFile(filePath, content);
	}	
	
	public void createCsvByListCourseSchedule(String filePath, List<String> courseList) {
		Map<ExamTime, List<Course>> examTimeCourseList = filterByCourse(courseList);
		String content = createCsvString(examTimeCourseList);
		createFile(filePath, content);
	}
	
	private String createCsvString(Map<ExamTime,List<Course>> examTimeCourseList) {
		StringBuilder content = new StringBuilder();
		content.append("\"Code\";\"Name\";\"Day\";\"Hour\"\n");
		for (ExamTime examTime : examTimeCourseList.keySet()) {
			for (Course course : examTimeCourseList.get(examTime)) {
				content.append("\"" + course.getCode() + "\";\"" + course.getName() + "\";\"" + examTime.getDayName() + "\";\"" + examTime.getHourName() + "\"\n");
			}
		}
		return content.toString();
	}
	
	private String createString(Map<ExamTime, List<Course>> examTimeCourseList) {
		StringBuilder result = new StringBuilder();
		result.append(String.format("%-10s %-10s %-10s %s\n", "Day", "Hour", "Code", "Name"));
		for (ExamTime examTime : examTimeCourseList.keySet()) {
			for (Course course : examTimeCourseList.get(examTime)) {
				result.append(String.format("%-10s %-10s %-10s %s\n", examTime.getDayName(), examTime.getHourName(), course.getCode(), course.getName()));
			}
		}
		return result.toString();
	}
	
	private Map<ExamTime, List<Course>> filterByCourse(List<String> courseList){
		List<String> courseListClean = courseList.parallelStream().map(course -> course.substring(0, 8)).collect(Collectors.toList());
		return examTimeListCourse.keySet()
				.parallelStream()
				.collect(Collectors.toMap(it -> it, it -> new ArrayList<>(examTimeListCourse.get(it)
						.parallelStream()
						.filter(course -> courseListClean.contains(course.getCode()))
						.collect(Collectors.toList())
						))
				)
				.entrySet()
				.parallelStream()
				.filter(it -> !it.getValue().isEmpty())
				.sorted(Map.Entry.comparingByKey())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue,newValue) -> oldValue, LinkedHashMap::new));
	}
	
	private Map<ExamTime, List<Course>> filterByGrade(String grade){
		String gradeClean = grade.substring(0, 4);
		
		return examTimeListCourse.keySet()
				.parallelStream()
				.collect(Collectors.toMap(it -> it, it -> new ArrayList<>(examTimeListCourse.get(it)
						.parallelStream()
						.filter(course -> {
							int result = course.getDataCourseList().parallelStream().mapToInt(dataCourse ->  { 
								if (dataCourse.getGrade().equals(gradeClean)) {
									return 1;
								} else {
									return 0;
								}
							}).sum();
							if (result > 0) {
								return true;
							} else {
								return false;
							}
						})
						.collect(Collectors.toList())
						))
				)
				.entrySet()
				.parallelStream()
				.filter(it -> !it.getValue().isEmpty())
				.sorted(Map.Entry.comparingByKey())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue,newValue) -> oldValue, LinkedHashMap::new));
	}
	
	private void createFile(String filePath, String content) {
		File file = null;
		FileWriter fw = null;
		
		try {
			file = new File(filePath);
			if (file.exists()) {
				file.delete();
			}
			fw = new FileWriter(file);
			fw.write(content);
		} catch (IOException e) {
			System.out.print("I/O error, check your data\n");
			System.exit(1);
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					System.out.print("I/O error, check your data\n");
					System.exit(1);
				}
			}
		}
	}
	
	private void createXmlFile(String filePath) {
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(this.getClass());
			Marshaller marshall = context.createMarshaller();
			marshall.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshall.marshal(this, new File(filePath));
		} catch (JAXBException e) {
			System.out.print("I/O error, check your data\n");
			System.exit(1);
		}
	}
	
	private void parseSolution(List<Integer> solution) {
		codeCourseExamTime = new LinkedHashMap<>(IntStream.range(0, solution.size())
				.parallel()
				.boxed()
				.collect(Collectors.toMap(it -> dataUned.getCourse(it), it -> dataUned.getExamTime(solution.get(it)))));
		examTimeListCourse = dataUned.getExamTimeList()
				.parallelStream()
				.collect(Collectors.toMap(it -> it, it -> new ArrayList<Course>(codeCourseExamTime.keySet()
						.parallelStream()
						.filter(course -> codeCourseExamTime.get(course).equals(it))
						.collect(Collectors.toList())))).entrySet()
				.parallelStream()
				.sorted(Map.Entry.comparingByKey())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue,newValue) -> oldValue, LinkedHashMap::new));
	}
	
	private void createXMLData(Map<ExamTime, List<Course>> examTimeCourseList) {
		listXML = new ArrayList<>();
		for (ExamTime examTime : examTimeCourseList.keySet()) {
			int day = examTime.getDay();
			int hour = examTime.getHour();
			List<XMLDay> dayXMLList = listXML.parallelStream().filter(it -> it.getDay() == day).collect(Collectors.toList());
			if (dayXMLList.isEmpty()) {
				XMLDay xmlDay = new XMLDay(day, examTime.getDayName());
				XMLHour xmlHour = (new XMLHour(hour, examTime.getHourName()));
				xmlHour.addListCourse(examTimeCourseList.get(examTime));
				xmlDay.addHour(xmlHour);
				listXML.add(xmlDay);
			} else {
				XMLDay xmlDay = dayXMLList.get(0);
				List<XMLHour> hourXMLList = xmlDay.getHourList().parallelStream().filter(it -> it.getHour() == hour).collect(Collectors.toList());
				if (hourXMLList.isEmpty()) {
					XMLHour xmlHour = (new XMLHour(hour, examTime.getHourName()));
					xmlHour.addListCourse(examTimeCourseList.get(examTime));
					xmlDay.addHour(xmlHour);
				} else {
					hourXMLList.get(0).addListCourse(examTimeCourseList.get(examTime));
				}
			}
		}
	}

	@XmlRootElement(name = "day")
	@XmlType(propOrder = {"day", "name", "hours"})
	private static class XMLDay {
		
		@XmlElement(name = "id")
		private int day;
		@XmlElement(name = "name")
		private String name;
		@XmlElementWrapper(name = "hourList")
		@XmlElement(name = "hour")
		private List<XMLHour> hours;
		
		public XMLDay() {
			hours = new ArrayList<>();
		}
		
		public XMLDay(int day, String name) {
			this();
			this.day = day;
			this.name = name;
		}
		
		public int getDay() {
			return day;
		}
		
		public List<XMLHour> getHourList() {
			return hours;
		}
		
		public void addHour(XMLHour hour) {
			if (!hours.contains(hour)) {
				hours.add(hour);
			}
		}
	}

	@XmlRootElement(name = "hour")
	@XmlType(propOrder = {"hour", "name", "courses"})
	private static class XMLHour {
		
		@XmlElement(name = "id")
		private int hour;
		@XmlElement(name = "name")
		private String name;
		@XmlElementWrapper(name = "examList")
		@XmlElement(name = "course")
		private List<Course> courses;
		
		public XMLHour() {
			courses = new ArrayList<>();
		}
		
		public XMLHour(int hour, String name) {
			this();
			this.hour = hour;
			this.name = name;
		}
		
		public int getHour() {
			return hour;
		}
		
		public void addListCourse(List<Course> courseList) {
			courses.addAll(courseList);
		}
	}
	
}
