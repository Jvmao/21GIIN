package modelo;

import java.util.Date;

public class Evento {
	//Atributos
	private String idConvocatorias;
	private String descPresentacion;
	private Date fechaApertura;
	private Date fechaCierre;
	
	//Constructor Vacío
	public Evento() {}

	//Constructor
	public Evento(String idConvocatorias, String descPresentacion, Date fechaApertura, Date fechaCierre) {
		super();
		this.idConvocatorias = idConvocatorias;
		this.descPresentacion = descPresentacion;
		this.fechaApertura = fechaApertura;
		this.fechaCierre = fechaCierre;
	}

	//Getters and Setters
	public String getIdConvocatorias() {
		return idConvocatorias;
	}

	public void setIdConvocatorias(String idConvocatorias) {
		this.idConvocatorias = idConvocatorias;
	}

	public String getDescPresentacion() {
		return descPresentacion;
	}

	public void setDescPresentacion(String descPresentacion) {
		this.descPresentacion = descPresentacion;
	}

	public Date getFechaApertura() {
		return fechaApertura;
	}

	public void setFechaApertura(Date fechaApertura) {
		this.fechaApertura = fechaApertura;
	}

	public Date getFechaCierre() {
		return fechaCierre;
	}

	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	//Método toString
	@Override
	public String toString() {
		return "Evento [idConvocatorias=" + idConvocatorias + ", descPresentacion=" + descPresentacion
				+ ", fechaApertura=" + fechaApertura + ", fechaCierre=" + fechaCierre + "]";
	};
	
	
	
	
	
}
