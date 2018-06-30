package es.uned.yauesc.geneticAlgorithm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

public class SurvivorSelectorAge implements SurvivorSelector {
	
	private EvaluationFunction evaluationFunction;

	public SurvivorSelectorAge(EvaluationFunction evaluationFunction) {
		this.evaluationFunction = evaluationFunction;
	}

	@Override
	public Collection<Individual> getSurvivor(Population population, Collection<Individual> offspring) {
		ArrayList<Individual> populationIndividuals = new ArrayList<Individual>(population.getAllIndividual());
		//Low all Individual one but if there are newcomers (not have age) set to five
		populationIndividuals.parallelStream().forEach(individual -> {
			if (individual.isAged()) {
				individual.setAge(individual.getAge() - 1);
			} else {
				individual.setAge(5);
			}
		});
		ArrayList<Individual> survivor = new ArrayList<Individual>(populationIndividuals
				.parallelStream()
				.filter(individual -> individual.getAge() > 0)
				.collect(Collectors.toList()));
		ArrayList<Individual> allIndividual = new ArrayList<Individual>(survivor);
		allIndividual.addAll(offspring);
		Collections.sort(allIndividual);
		Collections.reverse(allIndividual);
		Fitness maxFitness = allIndividual.get(0).getFitness();
		Fitness minFitness = allIndividual.get(allIndividual.size() - 1).getFitness();
		ArrayList<Individual> offspringAged = new ArrayList<Individual>(evaluationFunction.setAge(maxFitness, minFitness, offspring));
		if (allIndividual.size() > population.getMaxSize()) {
			int gap = population.getMaxSize() - survivor.size();
			Collections.sort(offspringAged);
			Collections.reverse(offspringAged);
			offspringAged = new ArrayList<Individual>(offspringAged.subList(0, gap));
		}
		survivor.addAll(offspringAged);
		return survivor;
	}

}
