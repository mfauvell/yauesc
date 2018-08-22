package es.uned.yauesc.geneticAlgorithm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que representa una población de individuos que forma parate de un algoritmo genético
 */
public class Population {

	private List<Individual> individuals;
	private int size;
	private int max;
	private int min;
	private boolean aged;
	
	private final static Logger LOGGER = Logger.getLogger(Population.class.getName());
	
	/**
	 * Constructor que crea una población con el tamaño de la colección de individuos pasada y que puede variar entre el
	 * max y el min pasados
	 *  
	 * @param collectionIndividuals						los individuos iniciales de la población
	 * @param max										el tamaño máximo de la población
	 * @param min										el tamaño mínimo de la población
	 * 
	 * @throws IllegalParameterValueCheckedException
	 */
	public Population(Collection<Individual> collectionIndividuals, int max, int min) throws IllegalParameterValueCheckedException {
		int size = collectionIndividuals.size();
		if (min > size) {
			LOGGER.log(Level.SEVERE, "The new population has a size of  " + size + ", it has to be greater than: " + min);
			throw new IllegalParameterValueCheckedException("Min value must be lower or equal than population initial size.");
		} else if (max < size ) {
			LOGGER.log(Level.SEVERE, "The new population has a size of  " + size + ", it has to be lower than: " + max);
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
	
	/**
	 * Constructor que crea una población de tamaño fijo con la colección de individuos pasada como argumento 
	 * 
	 * @param collectionIndividuals						los individuos iniciales
	 * 
	 * @throws IllegalParameterValueCheckedException
	 */
	public Population(Collection<Individual> collectionIndividuals) throws IllegalParameterValueCheckedException {
		this(collectionIndividuals,collectionIndividuals.size(),collectionIndividuals.size());
		aged = false;
	}
	
	/**
	 * Devuelve el individuo con mejor adecuación de la poblacion
	 * 
	 * @return	el individuo con mejor adecuación
	 */
	public Individual getBestIndividual() {
		return individuals.get(0);
	}
	
	/**
	 * Devuelve el número de individuos de la población pasado por parámetro ordenados de mejor a peor adecuación
	 * 
	 * @param number	el número de individuos a devolver
	 * 
	 * @return	una colección de individuos
	 */
	public Collection<Individual> getBestIndividual(int number) {
		if (number > size) {
			LOGGER.log(Level.SEVERE, "Ask for " + number + " individuals, but population has only " + size + " individuals" );
			throw new IllegalArgumentException("Asked for more individuals that the population have");
		} else {
			return individuals.subList(0, number);
		}
	}

	/**
	 * Obtiene todos los individuos que forman la población
	 * 
	 * @return	individuals
	 */
	public Collection<Individual> getAllIndividual() {
		return individuals;
	}

	/**
	 * Devuelve el temaño actual de la población
	 * 
	 * @return	size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Devuelve el tamaño máximo de la población
	 * 
	 * @return	max
	 */
	public int getMaxSize() {
		return max;
	}

	/**
	 * Devuelve el tamaño mínimo de la población
	 * 
	 * @return	min
	 */
	public int getMinSize() {
		return min;
	}

	/**
	 * Sustituye todos los individuos por la colección de individuos pasada
	 * 
	 * @param newIndividuals	la nueva colección de individuos que formara la población
	 */
	public void substituteAllIndividual(Collection<Individual> newIndividuals) {
		int newSize = newIndividuals.size();
		if (min > newSize) {
			LOGGER.log(Level.SEVERE, "The new population has a size of  " + newSize + ", it has to be greater than: " + min);
			throw new IllegalArgumentException("New population can not be lower than min");
		} else if (max < newSize) {
			LOGGER.log(Level.SEVERE, "The new population has a size of  " + newSize + ", it has to be lower than: " + max);
			throw new IllegalArgumentException("New population can not be greater than max");
		} else {
			this.individuals = new ArrayList<Individual>(newIndividuals);
			size = newSize;
			sortIndividuals();
		}
	}

	/**
	 * Substituye los peores individuos de la población en igual número de los pasados por argumento 
	 * 
	 * @param newIndividuals	los nuevos individuos
	 */
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
	

	/**
	 * Comprueba si la población tiene tamaño variable.
	 * 
	 * @return true si la población es de tamaño variable
	 */
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
