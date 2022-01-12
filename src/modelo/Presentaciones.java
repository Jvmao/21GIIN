/*
 * 11 ene 2022
 * Jose V. Martí
 */
package modelo;

import java.util.Date;

import controlador.ControlaFechas;

// TODO: Auto-generated Javadoc
/**
 * The Class Presentaciones.
 */
public class Presentaciones extends Evento implements ControlaFechas {
	
	/** The fecha presentacion. */
	//Atributos
	private Date fechaPresentacion;
	
	/**
	 * Instantiates a new presentaciones.
	 */
	//Constructor Vacío
	public Presentaciones() {}

	/**
	 * Instantiates a new presentaciones.
	 *
	 * @param fechaPresentacion the fecha presentacion
	 */
	//Constructor
	public Presentaciones(Date fechaPresentacion) {
		super();
		this.fechaPresentacion = fechaPresentacion;
	}

	/**
	 * Gets the fecha presentacion.
	 *
	 * @return the fecha presentacion
	 */
	//Getters and Setters
	public Date getFechaPresentacion() {
		return fechaPresentacion;
	}

	/**
	 * Sets the fecha presentacion.
	 *
	 * @param fechaPresentacion the new fecha presentacion
	 */
	public void setFechaPresentacion(Date fechaPresentacion) {
		this.fechaPresentacion = fechaPresentacion;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	//Método toString()
	@Override
	public String toString() {
		return "Presentaciones [fechaPresentacion=" + fechaPresentacion + "]";
	}

	/**
	 * Comprueba fecha.
	 *
	 * @param convocatoria the convocatoria
	 * @param presentacion the presentacion
	 */
	//Métodos implementados desde interface ControlaFechas
	@Override
	public void compruebaFecha(Date convocatoria, Date presentacion) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Fecha to timestamp.
	 *
	 * @param fecha the fecha
	 */
	@Override
	public void fechaToTimestamp(Date fecha) {
		// TODO Auto-generated method stub
		
	}
	
	
	
			
	
}
