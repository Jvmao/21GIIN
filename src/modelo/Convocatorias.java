package modelo;

import java.util.ArrayList;
import java.util.Date;

import controlador.ControlaFechas;

/**
 * @JVMARTI
 */
public class Convocatorias implements ControlaFechas{
	//Atributos
	private String idConvocatorias;
	private String descPresentacion;
	private Date fechaApertura;
	private Date fechaCierre;
	private Boolean estadoApertura;
	private ArrayList<String> docsConvocatoria;
	
	//Constructor Vacío
	public Convocatorias() {}
	
	//Constructor
	public Convocatorias(String id_convocatorias, String descPresentacion, Date fechaApertura, Date fechaCierre,
			Boolean estadoApertura, ArrayList<String> docsConvocatoria) {
		super();
		this.idConvocatorias = id_convocatorias;
		this.descPresentacion = descPresentacion;
		this.fechaApertura = fechaApertura;
		this.fechaCierre = fechaCierre;
		this.estadoApertura = estadoApertura;
		this.docsConvocatoria = docsConvocatoria;
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


	public Boolean getEstadoApertura() {
		return estadoApertura;
	}


	public void setEstadoApertura(Boolean estadoApertura) {
		this.estadoApertura = estadoApertura;
	}


	public ArrayList<String> getDocsConvocatoria() {
		return docsConvocatoria;
	}


	public void setDocsConvocatoria(ArrayList<String> docsConvocatoria) {
		this.docsConvocatoria = docsConvocatoria;
	}

	
	//Método toString()
	@Override
	public String toString() {
		return "Convocatorias [idConvocatorias= " + idConvocatorias + ", descPresentacion= " + descPresentacion
				+ ", fechaApertura= " + fechaApertura + ", fechaCierre= " + fechaCierre + ", estadoApertura= "
				+ estadoApertura + ", docsConvocatoria= " + docsConvocatoria + "]";
	}

	//Métodos Implementados
	@Override
	public void compruebaFecha(Date convocatoria, Date presentacion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fechaToTimestamp(Date fecha) {
		// TODO Auto-generated method stub
		
	}	

}
