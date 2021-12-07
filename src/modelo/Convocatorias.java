package modelo;

import java.util.ArrayList;
import java.util.Date;

import controlador.ControlaFechas;

/**
 * @JVMARTI
 */
public class Convocatorias extends Evento implements ControlaFechas{
	//Atributos
	private Boolean estadoApertura;
	private ArrayList<String> documentosSolicitados;
	
	//Constructor Vacío
	public Convocatorias() {}
	
	//Constructor
	public Convocatorias(Boolean estadoApertura, ArrayList<String> docsConvocatoria) {
		super();
		this.estadoApertura = estadoApertura;
		this.documentosSolicitados = docsConvocatoria;
	}

	//Getters and Setters
	public Boolean getEstadoApertura() {
		return estadoApertura;
	}

	public void setEstadoApertura(Boolean estadoApertura) {
		this.estadoApertura = estadoApertura;
	}

	public ArrayList<String> getDocsConvocatoria() {
		return documentosSolicitados;
	}

	public void setDocsConvocatoria(ArrayList<String> docsConvocatoria) {
		this.documentosSolicitados = docsConvocatoria;
	}

	//Método toString()
	@Override
	public String toString() {
		return "Convocatorias [estadoApertura=" + estadoApertura + ", docsConvocatoria=" + documentosSolicitados + "]";
	}

	//Métodos implementados desde interface ControlaFechas
	@Override
	public void compruebaFecha(Date convocatoria, Date presentacion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fechaToTimestamp(Date fecha) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	

}
