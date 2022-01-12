/*
 * 11 ene 2022
 * Jose V. Martí
 */
package controlador;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Interface EventoDAO.
 */

/**
 * The Interface EventoDAO.
 */
public interface EventoDAO {
	
	/**
	 * Adds the evento.
	 *
	 * @param id            the id
	 * @param desc          the desc
	 * @param fechaApertura the fecha apertura
	 * @param fechaCierre   the fecha cierre
	 * @param estado        the estado
	 * @param docs          the docs
	 */
	public void addEvento(String id,String desc,String fechaApertura,String fechaCierre,Boolean estado,ArrayList<String> docs);
	
	/**
	 * Mod evento.
	 *
	 * @param id            the id
	 * @param desc          the desc
	 * @param fechaApertura the fecha apertura
	 * @param fechaCierre   the fecha cierre
	 * @param estado        the estado
	 * @param docs          the docs
	 */
	public void modEvento(String id,String desc,String fechaApertura,String fechaCierre,Boolean estado,ArrayList<String> docs);
	
	/**
	 * Del evento.
	 *
	 * @param id the id
	 */
	public void delEvento(String id);

}
