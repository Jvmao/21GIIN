/*
 * 23 ene 2022
 * Jose V. Martí
 */
package controlador;


import java.util.ArrayList;


/**
 * The Interface UsuarioDAO.
 */
public interface UsuarioDAO {
	
	/**
	 * Check user.
	 *
	 * @param id   the id
	 * @param tipo the tipo
	 * @param pass the pass
	 * @return true, if successful
	 */
	public boolean checkUser(String id,String tipo,String pass);
	
	/**
	 * Lista tipo usuarios.
	 *
	 * @return the array list
	 */
	public ArrayList<String> listaTipoUsuarios(); 
	
	
	/**
	 * Lista id usuarios.
	 *
	 * @return the array list
	 */
	public ArrayList<String> listaIdUsuarios();
	
	/**
	 * Lista id user conv.
	 *
	 * @return the array list
	 */
	public ArrayList<String> listaIdUserConv();
	
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
