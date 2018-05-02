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
	
	public Population(Collection<Individual> collectionIndividuals, int max, int min) throws IllegalParameterValueCheckedException {
		int size = collectionIndividuals.size();
		if (min > size) {
			throw new IllegalParameterValueCheckedException("Min value must be lower or equal than population initial size.");
		} else if (max < size ) {
			throw new IllegalParameterValueCheckedException("Max value must be greater or equal than population initial size.");
		} else {
			individuals = new ArrayList<Individual>(collectionIndividuals);
			Collections.sort(individuals);
			Collections.reverse(individuals);
			this.size = size;
			this.max = max;
			this.min = min;
		}
	}
	
	public Population(Collection<Individual> collectionIndividuals) throws IllegalParameterValueCheckedException {
		this(collectionIndividuals,collectionIndividuals.size(),collectionIndividuals.size());
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

	public Collection<Individual> getBestIndividual(int number) {
		if (number >= size) {
			return individuals;
		} else {
			return individuals.subList(0, number);
		}
	}

	public void substituteAllIndividual(Collection<Individual> newIndividuals) {
		this.individuals = new ArrayList<Individual>(newIndividuals);
		Collections.sort(this.individuals);
		Collections.reverse(this.individuals);
	}

}
