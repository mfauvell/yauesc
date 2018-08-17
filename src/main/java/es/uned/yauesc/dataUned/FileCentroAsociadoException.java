package es.uned.yauesc.dataUned;

/**
 * Excepción a lanzar cuando ocurre algún error extrayendo datos de los centros asociados
 */
public class FileCentroAsociadoException extends Exception {
	
	private static final long serialVersionUID = -2946393054210644295L;

	/**
	 * Constructor por defecto con mensaje
	 * 
	 * @param message	mensaje a añadir a la excepción
	 */
	public FileCentroAsociadoException(String message) {
		super(message);
	}

}
