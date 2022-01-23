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
 * Objeto Presentaciones
 * 
 * The Class Presentaciones.
 */
public class Presentaciones extends Evento implements ControlaFechas {
	
	/** The id usuario. */
	private String idUsuario;
	
	/** The fecha presentacion. */
	private Date fechaPresentacion;
	
	/** The docs presentados. */
	private ArrayList<String> docsPresentados;
	
	

	/**
	 * Instantiates a new presentaciones.
	 */
	public Presentaciones() {}

	/**
	 * Instantiates a new presentaciones.
	 *
	 * @param idEvento          the id evento
	 * @param descEvento        the desc evento
	 * @param fechaApertura     the fecha apertura
	 * @param fechaCierre       the fecha cierre
	 * @param idUsuario         the id usuario
	 * @param fechaPresentacion the fecha presentacion
	 */
	public Presentaciones(String idEvento, String descEvento, Date fechaApertura, Date fechaCierre,
						  String idUsuario,Date fechaPresentacion) 
	{
		super(idEvento,descEvento,fechaApertura,fechaCierre);
		this.idUsuario = idUsuario;
		this.fechaPresentacion = fechaPresentacion;
	}

	//Getters and Setters
	
	/**
	 * Gets the id usuario.
	 *
	 * @return the id usuario
	 */
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
	 * Gets the fecha presentacion.
	 *
	 * @return the fecha presentacion
	 */
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
	 * Gets the docs presentados.
	 *
	 * @return the docs presentados
	 */
	public ArrayList<String> getDocsPresentados() {
		return docsPresentados;
	}

	/**
	 * Sets the docs presentados.
	 *
	 * @param docsPresentados the new docs presentados
	 */
	public void setDocsPresentados(ArrayList<String> docsPresentados) {
		this.docsPresentados = docsPresentados;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	
	@Override
	public String toString() {
		return "Presentaciones [idUsuario=" + idUsuario + ", fechaPresentacion=" + fechaPresentacion
				+ ", docsPresentados=" + docsPresentados + "]";
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
	public boolean compruebaFecha(String fechaUno, String fechaDos) {
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
