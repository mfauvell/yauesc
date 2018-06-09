package es.uned.yauesc.geneticAlgorithm;

public class GeneticAlgorithmConfig {
	
	private int genotypeLong;
	private int numberValuesGen;

	public GeneticAlgorithmConfig(int genotypeLong, int numberValuesGen) {
		this.genotypeLong = genotypeLong;
		this.numberValuesGen = numberValuesGen;
	}
	
	public int getGenotypeLong() {
		return genotypeLong;
	}

	public int getNumberValuesGen() {
		return numberValuesGen;
	}

}
