package es.uned.yauesc.geneticAlgorithm;

public class Individual {
	
	private int[] genotype;
	private Fitness fitness;
	private boolean evaluated;
	private int age;

	public Individual(int[] genotype) {
		this.genotype = genotype;
		age = 1;
		evaluated = false;
	}

	public int[] getGenotype() {
		return genotype;
	}

	public boolean isEvaluated() {
		return evaluated;
	}

	public void setFitness(Fitness fitness) {
		this.fitness = fitness;
		evaluated = true;
	}

	public Fitness getFitness() {
		if (!evaluated) {
			throw new UnsupportedOperationException("Individual must be evaluated first");
		} else {
			return fitness;
		}
	}

	public void setAge(int age) {
		if (age < 0) {
			this.age = 0;
		} else {
			this.age = age;
		}
	}

	public int getAge() {
		return age;
	}

	public void setGenotype(int[] genotype) {
		this.genotype = genotype;
		evaluated = false;
	}

	public void decAge() {
		if (age <= 0) {
			age = 0;
		} else {
			age = age -1;
		}
	}
}
