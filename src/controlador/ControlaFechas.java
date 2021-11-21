package controlador;

import java.util.Date;

public interface ControlaFechas {
	
	public void compruebaFecha(Date convocatoria, Date presentacion);
	public void fechaToTimestamp(Date fecha);

}
