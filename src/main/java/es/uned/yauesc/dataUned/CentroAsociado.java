package es.uned.yauesc.dataUned;

/**
 * Clase que encapsula un centro asociado de la UNED
 */
public class CentroAsociado {
	
	private String name;
	private int capacity;

	/**
	 * Constructor por defecto de la clase centro asociado, construye un centro asociado con una capacidad y un nombre
	 * 
	 * @param name		nombre del centro asociado
	 * @param capacity	capacidad del centro asociado
	 */
	public CentroAsociado(String name, int capacity) {
		this.name = name;
		this.capacity = capacity;
	}

	/**
	 * Devuelve el nombre del centro asociado
	 * 
	 * @return	name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Devuelve la capacidad del centro asociado
	 * 
	 * @return	capacity
	 */
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
