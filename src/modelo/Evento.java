/*
 * 23 ene 2022
 * Jose V. Martí
 */
package modelo;

import java.util.Date;

/**
 * Objeto Evento
 * 
 * The Class Evento.
 */
public class Evento {
	
	/** The id evento. */
	private String idEvento;
	
	/** The desc evento. */
	private String descEvento;
	
	/** The fecha apertura. */
	private Date fechaApertura;
	
	/** The fecha cierre. */
	private Date fechaCierre;
	
	/**
	 * Instantiates a new evento.
	 */
	public Evento() {}

	/**
	 * Instantiates a new evento.
	 *
	 * @param idEvento      the id evento
	 * @param descEvento    the desc evento
	 * @param fechaApertura the fecha apertura
	 * @param fechaCierre   the fecha cierre
	 */
	public Evento(String idEvento, String descEvento, Date fechaApertura, Date fechaCierre) {
		super();
		this.idEvento = idEvento;
		this.descEvento = descEvento;
		this.fechaApertura = fechaApertura;
		this.fechaCierre = fechaCierre;
	}

	/**
	 * Gets the id evento.
	 *
	 * @return the id evento
	 */
	public String getIdEvento() {
		return idEvento;
	}

	/**
	 * Sets the id evento.
	 *
	 * @param idEvento the new id evento
	 */
	public void setIdEvento(String idEvento) {
		this.idEvento = idEvento;
	}

	/**
	 * Gets the desc evento.
	 *
	 * @return the desc evento
	 */
	public String getDescEvento() {
		return descEvento;
	}

	/**
	 * Sets the desc evento.
	 *
	 * @param descEvento the new desc evento
	 */
	public void setDescEvento(String descEvento) {
		this.descEvento = descEvento;
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
	@Override
	public String toString() {
		return "Evento [idEvento=" + idEvento + ", descEvento=" + descEvento + ", fechaApertura=" + fechaApertura
				+ ", fechaCierre=" + fechaCierre + "]";
	}


	
	
	
	
}
