/*
 * 23 ene 2022
 * Jose V. Martí
 */
package modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import controlador.ControlaFechas;

/**
 * Objeto Convocatorias
 * 
 * The Class Convocatorias.
 */
public class Convocatorias extends Evento implements ControlaFechas{
	
	/** The id usuario. */
	private String idUsuario;
	
	/** The estado apertura. */
	private Boolean estadoApertura;
	
	/** The documentos solicitados. */
	private ArrayList<String> documentosSolicitados;
	
	/**
	 * Instantiates a new convocatorias.
	 */
	public Convocatorias() {}
	
	/**
	 * Instantiates a new convocatorias.
	 *
	 * @param idEvento         the id evento
	 * @param descEvento       the desc evento
	 * @param fechaApertura    the fecha apertura
	 * @param fechaCierre      the fecha cierre
	 * @param idUsuario        the id usuario
	 * @param estadoApertura   the estado apertura
	 * @param docsConvocatoria the docs convocatoria
	 */
	public Convocatorias(String idEvento, String descEvento, Date fechaApertura, Date fechaCierre,
						 String idUsuario,Boolean estadoApertura, ArrayList<String> docsConvocatoria) 
	
	{
		super(idEvento,descEvento,fechaApertura,fechaCierre);
		this.idUsuario = idUsuario;
		this.estadoApertura = estadoApertura;
		this.documentosSolicitados = docsConvocatoria;
	}


	/**
	 * Gets the id usuario.
	 *
	 * @return the id usuario
	 */
	//Getters and Setters
	public String getIdUsuario() {
		return idUsuario;
	}

	/**
	 * Sets the id usuario.
	 *
	 * @param idUsuario the new id usuario
	 */
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	
	/**
	 * Gets the estado apertura.
	 *
	 * @return the estado apertura
	 */
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
		return "Convocatorias [idUsuario=" + idUsuario + ", estadoApertura=" + estadoApertura
				+ ", documentosSolicitados=" + documentosSolicitados + "]";
	}

	/**
	 * Método para comprobar que una fecha de fin no sea menor a la fecha de inicio
	 * 
	 * Comprueba fecha.
	 *
	 * @param fechaUno the fecha uno
	 * @param fechaDos the fecha dos
	 * @return true, if successful
	 */
	@Override
	public boolean compruebaFecha(String fechaUno, String fechaDos) { //comprobamos que la fecha dos no sea mayor a la fecha uno
		SimpleDateFormat formatDates = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date firstDate ;
		Date secondDate;
		
		try {
			firstDate = formatDates.parse(fechaUno);
			secondDate = formatDates.parse(fechaDos);
			
			if(firstDate.compareTo(secondDate) >= 0)
				return false;
			
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		return true;
	}



	/**
	 * Comprobamos que la fecha sigue el formato dd/MM/yyyy HH:mm
	 * 
	 * Valida fecha.
	 *
	 * @param fecha the fecha
	 * @return true, if successful
	 */
	@Override
	public boolean validaFecha(String fecha) { 
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        format.setLenient(false);
        
        try {
			format.parse(fecha);
			return true;
		} catch (ParseException e) {
			e.getMessage();
			return false;
		}
	}


}
