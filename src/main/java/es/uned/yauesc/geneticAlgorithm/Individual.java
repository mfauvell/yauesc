package es.uned.yauesc.geneticAlgorithm;

public class Individual implements Comparable<Individual>{
	
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

	public Fitness getFitness() {
		if (!evaluated) {
			throw new UnsupportedOperationException("Individual must be evaluated first");
		} else {
			return fitness;
		}
	}

	public int getAge() {
		return age;
	}

	public void setFitness(Fitness fitness) {
		this.fitness = fitness;
		evaluated = true;
	}

	public void setAge(int age) {
		if (age < 0) {
			this.age = 0;
		} else {
			this.age = age;
		}
	}

	public void setGenotype(int[] genotype) {
		this.genotype = genotype;
		evaluated = false;
	}

	public void decAge() {
		if (!(age == 0)) {
			age = age -1;
		}
	}
	
	public boolean isEvaluated() {
		return evaluated;
	}

	@Override
	public int compareTo(Individual individual){
			return fitness.compareTo(individual.getFitness());
	}
	
	@Override
	public boolean equals(Object object) {
		if (object == this) {
			return true;
		}
		if (object == null) {
			return false;
		}
		if (object instanceof Individual) {
			Individual individual = (Individual) object;
			return individual.getGenotype().equals(genotype) && (individual.getAge() == age) && individual.getFitness().equals(fitness)
					&& (individual.isEvaluated() == evaluated);
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return ((genotype.hashCode() + age) / 23) * ((evaluated? 1 : 0) + fitness.hashCode());
	}
	
	@Override
	public String toString() {
		StringBuilder individualString = new StringBuilder();
		individualString.append("Individual: (");
		individualString.append("Genotype: " + genotype.toString());
		individualString.append(" Fitness: " + fitness.toString());
		individualString.append(" Age: " + age);
		individualString.append(" Evaluated: " + evaluated + ")");
		return individualString.toString();
	}
}
