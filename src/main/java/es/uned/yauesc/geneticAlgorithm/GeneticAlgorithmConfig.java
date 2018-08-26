package es.uned.yauesc.geneticAlgorithm;

/**
 * Clase que encapsula las configuraciones básicas necesarias para la creación de individuos
 */
public class GeneticAlgorithmConfig {
	
	private int genotypeLong;
	private int numberValuesGen;

	/**
	 * Constructor por defecto
	 * 
	 * @param genotypeLong		el tamaño del genotipo de los individuos
	 * @param numberValuesGen	el número de valores diferentes que puede tomar cada gen
	 */
	public GeneticAlgorithmConfig(int genotypeLong, int numberValuesGen) {
		this.genotypeLong = genotypeLong;
		this.numberValuesGen = numberValuesGen;
	}
	
	/**
	 * Obtiene el tamaño del genotipo
	 * 
	 * @return	genotypeLong
	 */
	public int getGenotypeLong() {
		return genotypeLong;
	}

	/**
	 * Obtiene el número de valores diferentes que puede tener un gen
	 * 
	 * @return	numberValuesGen
	 */
	public int getNumberValuesGen() {
		return numberValuesGen;
	}

}
