/*
 * 18 ene 2022
 * Jose V. Martí
 */
package controlador;

// TODO: Auto-generated Javadoc

/**
 * The Interface ControlaFechas.
 */

/**
 * The Interface ControlaFechas.
 */
public interface ControlaFechas {
	
	/**
	 * Comprueba fecha.
	 *
	 * @param convocatoria the convocatoria
	 * @param presentacion the presentacion
	 * @return true, if successful
	 */
	public boolean compruebaFecha(String convocatoria, String presentacion);
	
	/**
	 * Valida fecha.
	 *
	 * @param fecha the fecha
	 * @return true, if successful
	 */
	public boolean validaFecha(String fecha);

}
