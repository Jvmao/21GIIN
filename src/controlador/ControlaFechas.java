package controlador;

import java.util.Date;

/**
 * @JVMARTI
 */
public interface ControlaFechas {
	
	public void compruebaFecha(Date convocatoria, Date presentacion);
	public void fechaToTimestamp(Date fecha);

}
