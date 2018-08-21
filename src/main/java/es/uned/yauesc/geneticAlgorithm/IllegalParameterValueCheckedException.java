package es.uned.yauesc.geneticAlgorithm;

/**
 * Excepción que se produce cuando un parámetro tiene un valor incorrecto
 */
public class IllegalParameterValueCheckedException extends Exception {
	
	private static final long serialVersionUID = -5632078579642664881L;

	/**
	 * Constructor que le pasa un mensaje a la excepción para mostrar
	 * 
	 * @param message	el mensaje pasado
	 */
	public IllegalParameterValueCheckedException(String message) {
		super(message);
	}

}
