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

	@Override
	public boolean equals(Object object) {
		if (object == this) {
			return true;
		}
		if (object == null) {
			return false;
		}
		if (object instanceof CentroAsociado) {
			CentroAsociado centroAsociado = (CentroAsociado) object;
			return ((this.capacity == centroAsociado.getCapacity()) && (this.name.equals(centroAsociado.getName())));
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return (capacity * name.hashCode()) / 23;
	}
	
	@Override
	public String toString() {
		StringBuilder centroAsociadoString = new StringBuilder();
		centroAsociadoString.append("CentroAsociado: (");
		centroAsociadoString.append("Name = " + name);
		centroAsociadoString.append(" Capacity: " + capacity);
		centroAsociadoString.append(")");
		return centroAsociadoString.toString();
	}
}
