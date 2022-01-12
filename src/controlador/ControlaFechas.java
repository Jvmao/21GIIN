/*
 * 11 ene 2022
 * Jose V. Martí
 */
package controlador;

import java.util.Date;
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
	 */
	public void compruebaFecha(Date convocatoria, Date presentacion);
	
	/**
	 * Fecha to timestamp.
	 *
	 * @param fecha the fecha
	 */
	public void fechaToTimestamp(Date fecha);

}
