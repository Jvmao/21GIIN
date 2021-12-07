package controlador;


import javax.swing.JComboBox;

import modelo.Usuarios;
/**
 * @JVMARTI
 */
public interface UsuarioDAO {
	public Usuarios getUserId(String id);
	public void listaTipoUsuarios(JComboBox<String> jc); 
	public void listaIdUsuarios(JComboBox<String> jc);
	public void addUser(String id,String tipo,int password);
	public void updateUser(String idInit,String id,String tipo,int password);
	//public Usuarios updateUser();
	public void deleteUser(String id);

}
