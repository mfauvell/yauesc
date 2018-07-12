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

public class DataUnedSchedule {
	
	private DataUned dataUned;
	private FitnessUned fitnessSolution;
	
	private LinkedHashMap<Course, ExamTime> codeCourseExamTime;
	private LinkedHashMap<ExamTime,List<Course>> examTimeListCourse;
	
	public DataUnedSchedule(List<Integer> solution, FitnessUned fitnessSolution, DataUned dataUned) {
		this.fitnessSolution = fitnessSolution;
		this.dataUned = dataUned;
		parseSolution(solution);
	}

	public void createCsvAllSchedule(String filePath) {
		String content = createCsvString(examTimeListCourse);
		createFile(filePath, content);
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
	
	public FitnessUned getFitnessSolution() {
		return fitnessSolution;
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
			//e.printStackTrace();
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					System.out.print("I/O error, check your data\n");
					System.exit(1);
					//e.printStackTrace();
				}
			}
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

	

	
}
