package es.uned.yauesc.dataUned;

public class CentroAsociado {
	
	private int id;
	private String name;
	private int capacity;

	public CentroAsociado(int id, String name, int capacity) {
		this.id = id;
		this.name = name;
		this.capacity = capacity;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getCapacity() {
		return capacity;
	}

}
