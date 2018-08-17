package es.uned.yauesc.dataUned;

/**
 * Excepción a lanzar cuando ocurre algún error extrayendo datos de las asignaturas
 */
public class FileCourseException extends Exception {

	private static final long serialVersionUID = -2980078027607508593L;

	/**
	 * Constructor por defecto con mensaje
	 * 
	 * @param message	mensaje a añadir a la excepción
	 */
	public FileCourseException(String message) {
		super(message);
	}
}
