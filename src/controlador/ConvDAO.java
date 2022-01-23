/*
 * 23 ene 2022
 * Jose V. Martí
 */
package controlador;

import java.util.ArrayList;

/**
 * The Interface ConvDAO.
 */


public interface ConvDAO {
	
	/**
	 * Lista ID convocatoria.
	 *
	 * @return the array list
	 */
	public ArrayList<String> listaIDConvocatoria();
	
	/**
	 * Añade nuevo evento en la BBDD
	 * 
	 * Adds the evento.
	 *
	 * @param idConv        the id conv
	 * @param idUser        the id user
	 * @param desc          the desc
	 * @param fechaApertura the fecha apertura
	 * @param fechaCierre   the fecha cierre
	 * @param estado        the estado
	 * @param docs          the docs
	 */
	public void addEvento(String idConv,String idUser,String desc,String fechaApertura,String fechaCierre,
						  Boolean estado,ArrayList<String> docs);
	
	/**
	 * Modifica un evento seleccionado en la BBDD
	 * 
	 * Mod evento.
	 *
	 * @param idConv        the id conv
	 * @param idUser        the id user
	 * @param desc          the desc
	 * @param fechaApertura the fecha apertura
	 * @param fechaCierre   the fecha cierre
	 * @param estado        the estado
	 * @param docs          the docs
	 */
	public void modEvento(String idConv,String idUser,String desc,String fechaApertura,String fechaCierre,
						  Boolean estado,ArrayList<String> docs);
	
	/**
	 * Elimina un evento seleccionado en la BBDD
	 * 
	 * Del evento.
	 *
	 * @param idConv the id conv
	 */
	public void delEvento(String idConv);
	
	/**
	 * ID usuarios convocatoria
	 * 
	 * Info usuarios conv.
	 *
	 * @param idConv the id conv
	 * @return the string
	 */
	public String infoUsuariosConv(String idConv);


}
