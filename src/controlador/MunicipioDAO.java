/*
 * 18 ene 2022
 * Jose V. Martí
 */
package controlador;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Interface MunicipioDAO.
 */


/**
 * The Interface MunicipioDAO.
 */

public interface MunicipioDAO {
	
	/**
	 * Lista tipo usuarios mun.
	 *
	 * @param c the c
	 * @return the array list
	 */
	public ArrayList<?> listaTipoUsuariosMun(ArrayList<String> c); 
	
	/**
	 * Lista id usuarios municipios.
	 *
	 * @param c the c
	 * @return the array list
	 */
	public ArrayList<?> listaIdUsuariosMunicipios(ArrayList<String> c);
	
	/**
	 * Adds the municipio.
	 *
	 * @param id        the id
	 * @param categoria the categoria
	 * @param idUser    the id user
	 * @param tipo      the tipo
	 */
	public void addMunicipio(String id,int categoria,String idUser,String tipo);
	
	/**
	 * Update municipio.
	 *
	 * @param id        the id
	 * @param categoria the categoria
	 * @param idUser    the id user
	 * @param tipo      the tipo
	 */
	public void updateMunicipio(String id,int categoria,String idUser,String tipo);
	
	/**
	 * Delete municipio.
	 *
	 * @param id the id
	 */
	public void deleteMunicipio(String id);
}
