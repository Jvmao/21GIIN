/*
 * 18 ene 2022
 * Jose V. Martí
 */
package controlador;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Interface ConvDAO.
 */

/**
 * The Interface EventoDAO.
 */
public interface ConvDAO {
	
	/**
	 * Lista ID convocatoria.
	 *
	 * @param c the c
	 * @return the array list
	 */
	public ArrayList<?> listaIDConvocatoria(ArrayList<String> c);
	
	/**
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
	 * Del evento.
	 *
	 * @param idConv the id conv
	 */
	public void delEvento(String idConv);


}
