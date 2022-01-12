/*
 * 11 ene 2022
 * Jose V. Martí
 */
package controlador;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Interface PresentacionDAO.
 */

/**
 * The Interface PresentacionDAO.
 */

public interface PresentacionDAO {
	
	/**
	 * Adds the presentacion.
	 *
	 * @param idUser        the id user
	 * @param tipo          the tipo
	 * @param timestamp     the timestamp
	 * @param estado        the estado
	 * @param docPresentado the doc presentado
	 */
	public void addPresentacion(String idUser,String tipo,String timestamp,boolean estado,ArrayList<String> docPresentado);
	
	/**
	 * Mod presentacion.
	 *
	 * @param idPres        the id pres
	 * @param id            the id
	 * @param tipo          the tipo
	 * @param timestamp     the timestamp
	 * @param estado        the estado
	 * @param docPresentado the doc presentado
	 */
	public void modPresentacion(String idPres,String id,String tipo,String timestamp,boolean estado,ArrayList<String> docPresentado);
	
	/**
	 * Del presentacion.
	 *
	 * @param idPres the id pres
	 */
	public void delPresentacion(String idPres);
	
}
