/*
 * 11 ene 2022
 * Jose V. Martí
 */
package modelo;

import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class Evento.
 */
public class Evento {
	
	/** The id convocatorias. */
	//Atributos
	private String idConvocatorias;
	
	/** The desc presentacion. */
	private String descPresentacion;
	
	/** The fecha apertura. */
	private Date fechaApertura;
	
	/** The fecha cierre. */
	private Date fechaCierre;
	
	/**
	 * Instantiates a new evento.
	 */
	//Constructor Vacío
	public Evento() {}

	/**
	 * Instantiates a new evento.
	 *
	 * @param idConvocatorias  the id convocatorias
	 * @param descPresentacion the desc presentacion
	 * @param fechaApertura    the fecha apertura
	 * @param fechaCierre      the fecha cierre
	 */
	//Constructor
	public Evento(String idConvocatorias, String descPresentacion, Date fechaApertura, Date fechaCierre) {
		super();
		this.idConvocatorias = idConvocatorias;
		this.descPresentacion = descPresentacion;
		this.fechaApertura = fechaApertura;
		this.fechaCierre = fechaCierre;
	}

	/**
	 * Gets the id convocatorias.
	 *
	 * @return the id convocatorias
	 */
	//Getters and Setters
	public String getIdConvocatorias() {
		return idConvocatorias;
	}

	/**
	 * Sets the id convocatorias.
	 *
	 * @param idConvocatorias the new id convocatorias
	 */
	public void setIdConvocatorias(String idConvocatorias) {
		this.idConvocatorias = idConvocatorias;
	}

	/**
	 * Gets the desc presentacion.
	 *
	 * @return the desc presentacion
	 */
	public String getDescPresentacion() {
		return descPresentacion;
	}

	/**
	 * Sets the desc presentacion.
	 *
	 * @param descPresentacion the new desc presentacion
	 */
	public void setDescPresentacion(String descPresentacion) {
		this.descPresentacion = descPresentacion;
	}

	/**
	 * Gets the fecha apertura.
	 *
	 * @return the fecha apertura
	 */
	public Date getFechaApertura() {
		return fechaApertura;
	}

	/**
	 * Sets the fecha apertura.
	 *
	 * @param fechaApertura the new fecha apertura
	 */
	public void setFechaApertura(Date fechaApertura) {
		this.fechaApertura = fechaApertura;
	}

	/**
	 * Gets the fecha cierre.
	 *
	 * @return the fecha cierre
	 */
	public Date getFechaCierre() {
		return fechaCierre;
	}

	/**
	 * Sets the fecha cierre.
	 *
	 * @param fechaCierre the new fecha cierre
	 */
	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	//Método toString
	@Override
	public String toString() {
		return "Evento [idConvocatorias=" + idConvocatorias + ", descPresentacion=" + descPresentacion
				+ ", fechaApertura=" + fechaApertura + ", fechaCierre=" + fechaCierre + "]";
	};
	
	
	
	
	
}
