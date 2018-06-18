package es.uned.yauesc.dataUned;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import es.uned.yauesc.geneticAlgorithm.EvaluationFunction;
import es.uned.yauesc.geneticAlgorithm.Fitness;
import es.uned.yauesc.geneticAlgorithm.Individual;

public class EvaluationFunctionUned implements EvaluationFunction {

	private DataUned dataUned;
	
	private long firstAgeDivisor;
	private long secondAgeDivisor;
	private long thirdAgeDivisor;
	private long fourthAgeDivisor;
	
	public EvaluationFunctionUned(DataUned dataUned) {
		this.dataUned = dataUned;
	}
	
	@Override
	public Collection<Individual> evaluate(Collection<Individual> individualPopulation) {
		return individualPopulation
				.parallelStream()
				.map(individual -> { 
					if (!individual.isEvaluated()) {
						individual.setFitness(calculateFitness(individual.getGenotype()));
					}
					return individual;
				})
				.collect(Collectors.toList());
	}

	private Fitness calculateFitness(List<Integer> genotype) {
		//TODO Refactor and test
		String obligatory = "Obligatory"; //Text to Obligatory courses.
		String optional = "Optional"; //Text to Obligatory courses.
		LinkedHashMap<ExamTime, List<Course>> examTimeCourseListMap = new LinkedHashMap<>();
		LinkedHashMap<ExamTime, LinkedHashMap<Integer, LinkedHashMap<Integer, LinkedHashMap<String, Integer>>>> examTimeGradeYearTypeNumber = new LinkedHashMap<>();
		LinkedHashMap<Integer, LinkedHashMap<Integer, LinkedHashMap<Integer, LinkedHashMap<String, Integer>>>> dayGradeYearTypeNumber = new LinkedHashMap<>();
		//First collect data
		for (int index = 0; index < dataUned.getNumberCourses(); index ++) {
			ExamTime examTime = dataUned.getExamTime(genotype.get(index));
			Course course = dataUned.getCourse(index);
			//Collect Data to examTimeCourseListMap and examTimeGradeYearTypeNumber
			//If examTime is not in maps yet
			if (!examTimeCourseListMap.containsKey(examTime)) {
				//ExamTimeCourseListMap
				List<Course> courseList = new ArrayList<>();
				courseList.add(course);
				examTimeCourseListMap.put(examTime, courseList);
				//examTimeGradeYearTypeNumber
				LinkedHashMap<Integer, LinkedHashMap<Integer, LinkedHashMap<String, Integer>>> gradeYearTypeNumber = new LinkedHashMap<>();
				//Create structure and fill it
				for (DataCourse dataCourse : course.getDataCourseList()) {
					LinkedHashMap<Integer, LinkedHashMap<String, Integer>> yearTypeNumber = new LinkedHashMap<>();
					LinkedHashMap<String, Integer> typeNumber = new LinkedHashMap<>();
					int year = dataCourse.getSchoolYear();
					if (dataCourse.isObligatory()) {
						typeNumber.put(obligatory, 1);
						typeNumber.put(optional, 0);
					} else {
						typeNumber.put(obligatory, 0);
						typeNumber.put(optional, 1);
					}
					yearTypeNumber.put(year, typeNumber);
					gradeYearTypeNumber.put(dataCourse.getGrade(), yearTypeNumber);
				}
				examTimeGradeYearTypeNumber.put(examTime, gradeYearTypeNumber);
			} else {
				//If examCourse is in maps already
				//examTimeCouseListMap
				examTimeCourseListMap.get(examTime).add(course);
				LinkedHashMap<Integer, LinkedHashMap<Integer, LinkedHashMap<String, Integer>>> gradeYearTypeNumber = examTimeGradeYearTypeNumber.get(examTime);
				for (DataCourse dataCourse : course.getDataCourseList()) {
					int grade = dataCourse.getGrade();
					int year = dataCourse.getSchoolYear();
					//if grade exist in map already
					if (gradeYearTypeNumber.containsKey(grade)) {
						LinkedHashMap<Integer, LinkedHashMap<String, Integer>> yearTypeNumber = gradeYearTypeNumber.get(grade);
						//If year is in maps already
						if (yearTypeNumber.containsKey(year)) {
							LinkedHashMap<String, Integer> typeNumber = yearTypeNumber.get(year);
							if (dataCourse.isObligatory()) {
								typeNumber.put(obligatory, typeNumber.get(obligatory) +1);
							} else {
								typeNumber.put(optional, typeNumber.get(optional) +1);
							}
						} else {
							//if year is not in mpas yet
							LinkedHashMap<String, Integer> typeNumber = new LinkedHashMap<>();
							if (dataCourse.isObligatory()) {
								typeNumber.put(obligatory, 1);
								typeNumber.put(optional, 0);
							} else {
								typeNumber.put(obligatory, 0);
								typeNumber.put(optional, 1);
							}
							yearTypeNumber.put(year, typeNumber);
						}
					} else {
						//if grade do not exist in maps
						LinkedHashMap<Integer, LinkedHashMap<String, Integer>> yearTypeNumber = new LinkedHashMap<>();
						LinkedHashMap<String, Integer> typeNumber = new LinkedHashMap<>();
						if (dataCourse.isObligatory()) {
							typeNumber.put(obligatory, 1);
							typeNumber.put(optional, 0);
						} else {
							typeNumber.put(obligatory, 0);
							typeNumber.put(optional, 1);
						}
						yearTypeNumber.put(year, typeNumber);
						gradeYearTypeNumber.put(dataCourse.getGrade(), yearTypeNumber);
					}
				}
			}
			//dayGradeYearTypeNumber
			int day = examTime.getDay();
			if (!dayGradeYearTypeNumber.containsKey(day)) {
				//If day is not in maps yet
				LinkedHashMap<Integer, LinkedHashMap<Integer, LinkedHashMap<String, Integer>>> gradeYearTypeNumber = new LinkedHashMap<>();
				for (DataCourse dataCourse : course.getDataCourseList()) {
					LinkedHashMap<Integer, LinkedHashMap<String, Integer>> yearTypeNumber = new LinkedHashMap<>();
					LinkedHashMap<String, Integer> typeNumber = new LinkedHashMap<>();
					int year = dataCourse.getSchoolYear();
					if (dataCourse.isObligatory()) {
						typeNumber.put(obligatory, 1);
						typeNumber.put(optional, 0);
					} else {
						typeNumber.put(obligatory, 0);
						typeNumber.put(optional, 1);
					}
					yearTypeNumber.put(year, typeNumber);
					gradeYearTypeNumber.put(dataCourse.getGrade(), yearTypeNumber);
				}
				dayGradeYearTypeNumber.put(day, gradeYearTypeNumber);
			} else {
				//if day is in maps already
				LinkedHashMap<Integer, LinkedHashMap<Integer, LinkedHashMap<String, Integer>>> gradeYearTypeNumber = dayGradeYearTypeNumber.get(day);
				for (DataCourse dataCourse : course.getDataCourseList()) {
					int grade = dataCourse.getGrade();
					int year = dataCourse.getSchoolYear();
					if (gradeYearTypeNumber.containsKey(grade)) {
						LinkedHashMap<Integer, LinkedHashMap<String, Integer>> yearTypeNumber = gradeYearTypeNumber.get(grade);
						if (yearTypeNumber.containsKey(year)) {
							LinkedHashMap<String, Integer> typeNumber = yearTypeNumber.get(year);
							if (dataCourse.isObligatory()) {
								typeNumber.put(obligatory, typeNumber.get(obligatory) +1);
							} else {
								typeNumber.put(optional, typeNumber.get(optional) +1);
							}
						} else {
							LinkedHashMap<String, Integer> typeNumber = new LinkedHashMap<>();
							if (dataCourse.isObligatory()) {
								typeNumber.put(obligatory, 1);
								typeNumber.put(optional, 0);
							} else {
								typeNumber.put(obligatory, 0);
								typeNumber.put(optional, 1);
							}
							yearTypeNumber.put(year, typeNumber);
						}
					} else {
						LinkedHashMap<Integer, LinkedHashMap<String, Integer>> yearTypeNumber = new LinkedHashMap<>();
						LinkedHashMap<String, Integer> typeNumber = new LinkedHashMap<>();
						if (dataCourse.isObligatory()) {
							typeNumber.put(obligatory, 1);
							typeNumber.put(optional, 0);
						} else {
							typeNumber.put(obligatory, 0);
							typeNumber.put(optional, 1);
						}
						yearTypeNumber.put(year, typeNumber);
						gradeYearTypeNumber.put(dataCourse.getGrade(), yearTypeNumber);
					}
				}
			}
		}
		//Calculate first level
		//In examTime capacity of CA's is less or equal than student call to it
		int firstLevel = dataUned.getExamTimeList()
				.parallelStream()
				.mapToInt(examTime -> dataUned.getCentroAsociadoList()
						.parallelStream()
						.mapToInt(centroAsociado -> {
							int examStudentNumber = examTimeCourseListMap.get(examTime)
									.parallelStream()
									.mapToInt(course -> dataUned.getNumberEnrolment(centroAsociado.getName(), course.getCode()))
									.sum();
							if (examStudentNumber > centroAsociado.getCapacity()) {
								return 1;
							}
							return 0;
						})
						.sum())
				.sum();
		//Calculate two requisites of secondLevel
		//Two courses in same examTime with one obligatory
		//Three or more optional courses in same examTime
		int secondLevel = examTimeGradeYearTypeNumber.keySet()
				.parallelStream()
				.mapToInt(examTime -> examTimeGradeYearTypeNumber.get(examTime).keySet()
						.parallelStream()
						.mapToInt(gradeCode -> examTimeGradeYearTypeNumber.get(examTime).get(gradeCode).keySet()
								.parallelStream()
								.mapToInt(year -> {
									LinkedHashMap<String, Integer> typeNumber = examTimeGradeYearTypeNumber.get(examTime).get(gradeCode).get(year);
									int result = 0;
									int optionalNumber = typeNumber.get(optional);
									int obligatoryNumber = typeNumber.get(obligatory);
									if (obligatoryNumber > 0) {
										result = ((optionalNumber + obligatoryNumber) - 1);
									}
									if (optionalNumber > 2) {
										result = result + (optionalNumber -2);
									}
									return result;
								})
								.sum())
						.sum())
				.sum();
		//Calculate two requisites of thirdLevesl
		//In same day two courses from same grade and same year with one of they obligatory
		//More than 4 optional same day
		int thirdLevel = dayGradeYearTypeNumber.keySet()
				.parallelStream()
				.mapToInt(day -> dayGradeYearTypeNumber.get(day).keySet()
						.parallelStream()
						.mapToInt(gradeCode -> dayGradeYearTypeNumber.get(day).get(gradeCode).keySet()
								.parallelStream()
								.mapToInt(year -> {
									LinkedHashMap<String, Integer> typeNumber = dayGradeYearTypeNumber.get(day).get(gradeCode).get(year);
									int result = 0;
									int optionalNumber = typeNumber.get(optional);
									int obligatoryNumber = typeNumber.get(obligatory);
									if (obligatoryNumber > 0) {
										result = (((optionalNumber + obligatoryNumber) -1 ) *2);
									}
									if (optionalNumber > 4) {
										result = (result + (optionalNumber - 4)); 
									}
									return result;
								})
								.sum())
						.sum())
				.sum();
		//Calculate last requisite of thirdlevel
		//At same examTime two obligatory courses of same grade
		thirdLevel = thirdLevel + examTimeGradeYearTypeNumber.keySet()
			.parallelStream()
			.mapToInt(examTime -> examTimeGradeYearTypeNumber.get(examTime).keySet()
					.parallelStream()
					.mapToInt(gradeCode -> {
						int allYearObligatory = examTimeGradeYearTypeNumber.get(examTime).get(gradeCode).keySet()
								.parallelStream()
								.mapToInt(year -> examTimeGradeYearTypeNumber.get(examTime).get(gradeCode).get(year).get(obligatory))
								.sum();
						if (allYearObligatory > 1) {
							return (allYearObligatory -1);
						}
						return 0;
					}).
					sum()).
			sum();
		return UnedFactory.getFitnessUned(firstLevel, secondLevel, thirdLevel);
	}

	@Override
	public Collection<Individual> setAge(Fitness max, Fitness min, Collection<Individual> offspring) {
		setDivisorAge((FitnessUned) max, (FitnessUned) min);
		offspring.parallelStream()
			.forEach(individual -> {
				int age = getAge((FitnessUned) individual.getFitness());
				individual.setAge(age);
		});
		return offspring;
	}
	
	private long convertFitness(FitnessUned fitness) {
		return ((fitness.getFirstLevel()* 1000000) + (fitness.getSecondLevel()*1000) + fitness.getThirdLevel());
	}

	private void setDivisorAge(FitnessUned maxFitness, FitnessUned minFitness) {
		long max = convertFitness(maxFitness);
		long min = convertFitness(minFitness);
		long difference = ((min - max) / 5);
		firstAgeDivisor = max + difference;
		secondAgeDivisor = firstAgeDivisor + difference;
		thirdAgeDivisor = secondAgeDivisor + difference;
		fourthAgeDivisor = thirdAgeDivisor + difference;
	}
	
	private int getAge(FitnessUned fitness) {
		int result = 1;
		long fitnessConvert = convertFitness(fitness);
		if (fitnessConvert < firstAgeDivisor) {
			result = 5;
		} else if (fitnessConvert < secondAgeDivisor) {
			result = 4;
		} else if (fitnessConvert < thirdAgeDivisor) {
			result = 3;
		} else if (fitnessConvert < fourthAgeDivisor) {
			result = 2;
		}
		return result;
	}
}
