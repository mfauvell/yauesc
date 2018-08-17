package es.uned.yauesc.dataUned;

/**
 * Excepción a lanzar cuando ocurre algún error extrayendo datos de las matrículas
 */
public class FileEnrolmentException extends Exception {

	private static final long serialVersionUID = 8284854753837396730L;

	/**
	 * Constructor por defecto con mensaje
	 * 
	 * @param message	mensaje a añadir a la excepción
	 */
	public FileEnrolmentException(String message) {
		super(message);
	}

}
