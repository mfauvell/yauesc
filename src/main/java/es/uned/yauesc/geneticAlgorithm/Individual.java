package es.uned.yauesc.geneticAlgorithm;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que representa un individuo dentro del algoritmo genético, posee un genotipo y una adecuación, también puede poseer una edad
 */
public class Individual implements Comparable<Individual>{
	
	private List<Integer> genotype;
	private Fitness fitness;
	private boolean evaluated;
	private int age;
	private boolean aged;
	
	private final static Logger LOGGER = Logger.getLogger(Individual.class.getName());

	/**
	 * Constructor que crea un individuo a partir de un genotipo dado
	 * 
	 * @param genotype	el genotipo del individuo
	 */
	public Individual(List<Integer> genotype) {
		this.genotype = genotype;
		evaluated = false;
		aged = false;
	}

	/**
	 * Obtiene el genotipo del individuo
	 * 
	 * @return	genotype
	 */
	public List<Integer> getGenotype() {
		return genotype;
	}

	/**
	 * Obtiene la adecuación del individuo
	 * 
	 * @return	fitness
	 */
	public Fitness getFitness() {
		if (!evaluated) {
			LOGGER.log(Level.SEVERE, "To getFitness must be first evaluated individual: " + this.toString());
			throw new UnsupportedOperationException("Individual must be evaluated first");
		} else {
			return fitness;
		}
	}

	/**
	 * Obtiene la edad del individuo
	 * 
	 * @return	age
	 */
	public int getAge() {
		if (!aged) {
			LOGGER.log(Level.SEVERE, "To getAge must be first aged individual: " + this.toString());
			throw new UnsupportedOperationException("Individual must be aged first");
		}
		return age;
	}

	/**
	 * Configura la adecuación del individuo
	 * 
	 * @param fitness	la edacuación a configurar
	 */
	public void setFitness(Fitness fitness) {
		this.fitness = fitness;
		evaluated = true;
	}

	/**
	 * Configura la edad del individuo
	 * 
	 * @param age	la edad a configurar
	 */
	public void setAge(int age) {
		if (age < 0) {
			this.age = 0;
		} else {
			this.age = age;
		}
		aged = true;
	}

	/**
	 * Configura el genotipo del individuo
	 * 
	 * @param genotype	el genotipo a configurar
	 */
	public void setGenotype(List<Integer> genotype) {
		this.genotype = genotype;
		evaluated = false;
	}
	
	/**
	 * Decrementa en un unidad la edad
	 */
	public void decAge() {
		if (!aged) {
			LOGGER.log(Level.SEVERE, "To getAge must be first aged individual: " + this.toString());
			throw new UnsupportedOperationException("Individual must be aged first");
		}
		if (!(age == 0)) {
			age = age -1;
		}
	}
	
	/**
	 * Devuelve true si el individuo ha sido evaluado
	 * 
	 * @return	evaluated
	 */
	public boolean isEvaluated() {
		return evaluated;
	}
	
	/**
	 * Devuelve true si el individuo tiene edad configurada
	 * 
	 * @return	aged
	 */
	public boolean isAged() {
		return aged;
	}

	@Override
	public int compareTo(Individual individual){
			if (individual == null) {
				return fitness.compareTo(null);
			}
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
