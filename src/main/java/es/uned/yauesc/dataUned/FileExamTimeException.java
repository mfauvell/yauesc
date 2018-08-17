package es.uned.yauesc.dataUned;

/**
 * Excepción a lanzar cuando ocurre algún error extrayendo datos de las fechas de examen
 */
public class FileExamTimeException extends Exception {

	private static final long serialVersionUID = -499457369430875730L;

	/**
	 * Constructor por defecto con mensaje
	 * 
	 * @param message	mensaje a añadir a la excepción
	 */
	public FileExamTimeException(String message) {
		super(message);
	}
}
