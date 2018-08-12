package es.uned.yauesc.geneticAlgorithm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Population {

	private List<Individual> individuals;
	private int size;
	private int max;
	private int min;
	private boolean aged;
	
	public Population(Collection<Individual> collectionIndividuals, int max, int min) throws IllegalParameterValueCheckedException {
		int size = collectionIndividuals.size();
		if (min > size) {
			throw new IllegalParameterValueCheckedException("Min value must be lower or equal than population initial size.");
		} else if (max < size ) {
			throw new IllegalParameterValueCheckedException("Max value must be greater or equal than population initial size.");
		} else {
			individuals = new ArrayList<Individual>(collectionIndividuals);
			sortIndividuals();
			this.size = size;
			this.max = max;
			this.min = min;
			aged = true;
		}
	}
	
	public Population(Collection<Individual> collectionIndividuals) throws IllegalParameterValueCheckedException {
		this(collectionIndividuals,collectionIndividuals.size(),collectionIndividuals.size());
		aged = false;
	}
	
	public Individual getBestIndividual() {
		return individuals.get(0);
	}
	
	public Collection<Individual> getBestIndividual(int number) {
		if (number > size) {
			throw new IllegalArgumentException("Asked for more individuals that the population have");
		} else {
			return individuals.subList(0, number);
		}
	}

	public Collection<Individual> getAllIndividual() {
		return individuals;
	}

	public int getSize() {
		return size;
	}

	public int getMaxSize() {
		return max;
	}

	public int getMinSize() {
		return min;
	}

	public void substituteAllIndividual(Collection<Individual> newIndividuals) {
		int newSize = newIndividuals.size();
		if (min > newSize) {
			throw new IllegalArgumentException("New population can not be lower than min");
		} else if (max < newSize) {
			throw new IllegalArgumentException("New population can not be greater than max");
		} else {
			this.individuals = new ArrayList<Individual>(newIndividuals);
			size = newSize;
			sortIndividuals();
		}
	}

	public void substituteWorstIndividual(Collection<Individual> newIndividuals) {
		int newSize = newIndividuals.size();
		List<Individual> futureIndividuals; 
		if ((newSize > size) && (newSize <= max)) {
			futureIndividuals = new ArrayList<Individual>();
		} else {
			futureIndividuals = new ArrayList<Individual>(individuals);
			Collections.reverse(futureIndividuals);
			futureIndividuals = futureIndividuals.subList(newSize, size);
		}
		futureIndividuals.addAll(newIndividuals);
		individuals = futureIndividuals;
		sortIndividuals();
	}
	

	public boolean isAged() {
		return aged;
	}
	
	private void sortIndividuals() {
		Collections.sort(individuals);
		Collections.reverse(individuals);
	}

	@Override
	public boolean equals(Object object) {
		if (object == this) {
			return true;
		}
		if (object == null) {
			return false;
		}
		if (object instanceof Population) {
			Population population = (Population) object;
			return population.getAllIndividual().equals(individuals) && (population.getSize() == size) 
					&& (population.getMaxSize() == max) && (population.getMinSize() == min);
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return ((individuals.hashCode() + size) / 23) * (max + min);
	}
	
	@Override
	public String toString() {
		StringBuilder individualString = new StringBuilder();
		individualString.append("Population: (");
		individualString.append("Individuals: " + individuals.toString());
		individualString.append(" Size: " + size);
		individualString.append(" MaxSize: " + max);
		individualString.append(" MinSize: " + min + ")");
		return individualString.toString();
	}
}
