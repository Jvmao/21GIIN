/*
 * 23 ene 2022
 * Jose V. Martí
 */
package controlador;

import java.util.ArrayList;


/**
 * The Interface MunicipioDAO.
 */

public interface MunicipioDAO {
	
	/**
	 * Lista tipo usuarios mun.
	 *
	 * @return the array list
	 */
	public ArrayList<String> listaTipoUsuariosMun(); 
	
	/**
	 * Lista id usuarios municipios.
	 *
	 * @return the array list
	 */
	public ArrayList<String> listaIdUsuariosMunicipios();
	
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
