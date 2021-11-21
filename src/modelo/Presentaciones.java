package modelo;

import java.util.ArrayList;
import java.util.Date;

import controlador.ControlaFechas;


public class Presentaciones extends Convocatorias implements ControlaFechas {
	
	//Atributos
	private Date fechaPresentacion;
	
	//Constructor Vacío
	public Presentaciones() {}
	
	
	//Constructor 
	public Presentaciones(String id_convocatorias, String descPresentacion, Date fechaApertura, Date fechaCierre,
			Boolean estadoApertura, ArrayList<String> docsConvocatoria) {
		super(id_convocatorias, descPresentacion, fechaApertura, fechaCierre, estadoApertura, docsConvocatoria);
		
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
