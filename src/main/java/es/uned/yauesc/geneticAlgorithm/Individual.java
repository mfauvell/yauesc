package es.uned.yauesc.geneticAlgorithm;

import java.util.List;

public class Individual implements Comparable<Individual>{
	
	private List<Integer> genotype;
	private Fitness fitness;
	private boolean evaluated;
	private int age;

	public Individual(List<Integer> genotype) {
		this.genotype = genotype;
		age = 1;
		evaluated = false;
	}

	public List<Integer> getGenotype() {
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

	public void setGenotype(List<Integer> genotype) {
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
			return individual.getGenotype().equals(genotype) && (individual.getAge() == age) && ((evaluated)? individual.getFitness().equals(fitness): true)
					&& (individual.isEvaluated() == evaluated);
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return ((genotype.hashCode() + age) / 23) * (evaluated? fitness.hashCode() : 1);
	}
	
	@Override
	public String toString() {
		StringBuilder individualString = new StringBuilder();
		individualString.append("Individual: (");
		individualString.append("Genotype: " + genotype.toString());
		if (evaluated) 
			individualString.append(" Fitness: " + fitness.toString());
		else
			individualString.append(" Fitness: Not evaluated.");
		individualString.append(" Age: " + age);
		individualString.append(" Evaluated: " + evaluated + ")");
		return individualString.toString();
	}
}
