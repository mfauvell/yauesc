package es.uned.yauesc.dataUned;

/**
 * Excepción a lanzar cuando ocurre algún error extrayendo datos de los grados
 */
public class FileGradeException extends Exception {

	private static final long serialVersionUID = 5586652119316111121L;
	
	/**
	 * Constructor por defecto con mensaje
	 * 
	 * @param message	mensaje a añadir a la excepción
	 */
	public FileGradeException(String message) {
		super(message);
	}

}
