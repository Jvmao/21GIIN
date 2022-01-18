/*
 * 18 ene 2022
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
	 * @param idPres        the id pres
	 * @param idConv        the id conv
	 * @param idUser        the id user
	 * @param timestamp     the timestamp
	 * @param estado        the estado
	 * @param docPresentado the doc presentado
	 */
	public void addPresentacion(String idPres,String idConv,String idUser,
								String timestamp,boolean estado,ArrayList<String> docPresentado);
	
	/**
	 * Mod presentacion.
	 *
	 * @param idPres        the id pres
	 * @param idConv        the id conv
	 * @param idUser        the id user
	 * @param timestamp     the timestamp
	 * @param estado        the estado
	 * @param docPresentado the doc presentado
	 */
	public void modPresentacion(String idPres,String idConv,String idUser,
								String timestamp,boolean estado,ArrayList<String> docPresentado);
	
	/**
	 * Del presentacion.
	 *
	 * @param idPres the id pres
	 */
	public void delPresentacion(String idPres);
	
}
