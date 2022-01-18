/*
 * 18 ene 2022
 * Jose V. Martí
 */
package modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import controlador.ControlaFechas;


// TODO: Auto-generated Javadoc
/**
 * The Class Presentaciones.
 */
public class Presentaciones extends Evento implements ControlaFechas {
	
	/** The id usuario. */
	private String idUsuario;
	
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
	 * @param idConvocatorias   the id convocatorias
	 * @param descPresentacion  the desc presentacion
	 * @param fechaApertura     the fecha apertura
	 * @param fechaCierre       the fecha cierre
	 * @param idUsuario         the id usuario
	 * @param fechaPresentacion the fecha presentacion
	 */
	//Constructor (heredamos los atributos de la clase principal Evento)
	public Presentaciones(String idConvocatorias, String descPresentacion, Date fechaApertura, Date fechaCierre,
						  String idUsuario,Date fechaPresentacion) 
	{
		super(idConvocatorias,descPresentacion,fechaApertura,fechaCierre);
		this.idUsuario = idUsuario;
		this.fechaPresentacion = fechaPresentacion;
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
	 * To string.
	 *
	 * @return the string
	 */
	//Método toString()
	@Override
	public String toString() {
		return "Presentaciones [idUsuario=" + idUsuario + ", fechaPresentacion=" + fechaPresentacion + "]";
	}

	/**
	 * Comprueba fecha.
	 *
	 * @param fechaUno the fecha uno
	 * @param fechaDos the fecha dos
	 * @return true, if successful
	 */
	//Métodos implementados desde interface ControlaFechas
	
	//Método para comprobar que la fecha de la presentación sea >= a la fecha de la convocatoria
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
