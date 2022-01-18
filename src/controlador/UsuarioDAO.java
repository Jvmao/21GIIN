/*
 * 18 ene 2022
 * Jose V. Martí
 */
package controlador;


import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Interface UsuarioDAO.
 */
/**
 * The Interface UsuarioDAO.
 */
public interface UsuarioDAO {
	
	/**
	 * Lista tipo usuarios.
	 *
	 * @param c the c
	 * @return the array list
	 */
	public ArrayList<?> listaTipoUsuarios(ArrayList<String> c); 
	
	/**
	 * Lista id usuarios.
	 *
	 * @param c the c
	 * @return the array list
	 */
	public ArrayList<?> listaIdUsuarios(ArrayList<String> c);
	
	/**
	 * Lista id user conv.
	 *
	 * @param c the c
	 * @return the array list
	 */
	public ArrayList<?> listaIdUserConv(ArrayList<String> c);
	
	/**
	 * Adds the user.
	 *
	 * @param id       the id
	 * @param tipo     the tipo
	 * @param password the password
	 */
	public void addUser(String id,String tipo,String password);
	
	/**
	 * Update user.
	 *
	 * @param id       the id
	 * @param tipo     the tipo
	 * @param password the password
	 */
	public void updateUser(String id,String tipo,String password);
	
	/**
	 * Delete user.
	 *
	 * @param id the id
	 */
	public void deleteUser(String id);

}
