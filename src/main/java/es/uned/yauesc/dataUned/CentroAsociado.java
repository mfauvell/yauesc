package es.uned.yauesc.dataUned;

public class CentroAsociado {
	
	private String name;
	private int capacity;

	public CentroAsociado(String name, int capacity) {
		this.name = name;
		this.capacity = capacity;
	}

	public String getName() {
		return name;
	}

	public int getCapacity() {
		return capacity;
	}

}
