/*
 * 11 ene 2022
 * Jose V. Martí
 */
package modelo;

import java.util.ArrayList;
import java.util.Date;

import controlador.ControlaFechas;

// TODO: Auto-generated Javadoc
/**
 * The Class Convocatorias.
 */
public class Convocatorias extends Evento implements ControlaFechas{
	
	/** The estado apertura. */
	//Atributos
	private Boolean estadoApertura;
	
	/** The documentos solicitados. */
	private ArrayList<String> documentosSolicitados;
	
	/**
	 * Instantiates a new convocatorias.
	 */
	//Constructor Vacío
	public Convocatorias() {}
	
	/**
	 * Instantiates a new convocatorias.
	 *
	 * @param estadoApertura   the estado apertura
	 * @param docsConvocatoria the docs convocatoria
	 */
	//Constructor
	public Convocatorias(Boolean estadoApertura, ArrayList<String> docsConvocatoria) {
		super();
		this.estadoApertura = estadoApertura;
		this.documentosSolicitados = docsConvocatoria;
	}

	/**
	 * Gets the estado apertura.
	 *
	 * @return the estado apertura
	 */
	//Getters and Setters
	public Boolean getEstadoApertura() {
		return estadoApertura;
	}

	/**
	 * Sets the estado apertura.
	 *
	 * @param estadoApertura the new estado apertura
	 */
	public void setEstadoApertura(Boolean estadoApertura) {
		this.estadoApertura = estadoApertura;
	}

	/**
	 * Gets the docs convocatoria.
	 *
	 * @return the docs convocatoria
	 */
	public ArrayList<String> getDocsConvocatoria() {
		return documentosSolicitados;
	}

	/**
	 * Sets the docs convocatoria.
	 *
	 * @param docsConvocatoria the new docs convocatoria
	 */
	public void setDocsConvocatoria(ArrayList<String> docsConvocatoria) {
		this.documentosSolicitados = docsConvocatoria;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	//Método toString()
	@Override
	public String toString() {
		return "Convocatorias [estadoApertura=" + estadoApertura + ", docsConvocatoria=" + documentosSolicitados + "]";
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
