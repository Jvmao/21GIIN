package modelo;

import java.util.Date;

import controlador.ControlaFechas;

/**
 * @JVMARTI
 */
public class Presentaciones extends Evento implements ControlaFechas {
	
	//Atributos
	private Date fechaPresentacion;
	
	//Constructor Vacío
	public Presentaciones() {}

	//Constructor
	public Presentaciones(Date fechaPresentacion) {
		super();
		this.fechaPresentacion = fechaPresentacion;
	}

	//Getters and Setters
	public Date getFechaPresentacion() {
		return fechaPresentacion;
	}

	public void setFechaPresentacion(Date fechaPresentacion) {
		this.fechaPresentacion = fechaPresentacion;
	}

	//Método toString()
	@Override
	public String toString() {
		return "Presentaciones [fechaPresentacion=" + fechaPresentacion + "]";
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
