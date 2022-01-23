/*
 * 23 ene 2022
 * Jose V. Martí
 */
package modelo;

import java.util.List;



/**
 * Objeto Usuarios
 * 
 * The Class Usuarios.
 */
public class Usuarios{
	
	/** The id usuario. */
	private String idUsuario;
	
	/** The pass usuario. */
	private String passUsuario;
	
	/** The tipo usuario. */
	private List<Object> tipoUsuario;
	
	/**
	 * Instantiates a new usuarios.
	 */
	public Usuarios() {
		
	}

	/**
	 * Instantiates a new usuarios.
	 *
	 * @param idUsuario   the id usuario
	 * @param passUsuario the pass usuario
	 * @param tipoUsuario the tipo usuario
	 */
	public Usuarios(String idUsuario, String passUsuario, List<Object> tipoUsuario) {
		super();
		this.idUsuario = idUsuario;
		this.passUsuario = passUsuario;
		this.tipoUsuario = tipoUsuario;
	}

	//Getters and setters
	/**
	 * Gets the id usuario.
	 *
	 * @return the id usuario
	 */
	public String getIdUsuario() {
		return idUsuario;
	}

	/**
	 * Sets the id usuario.
	 *
	 * @param idUsuario the new id usuario
	 */
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	/**
	 * Gets the pass usuario.
	 *
	 * @return the pass usuario
	 */
	public String getPassUsuario() {
		return passUsuario;
	}

	/**
	 * Sets the pass usuario.
	 *
	 * @param passUsuario the new pass usuario
	 */
	public void setPassUsuario(String passUsuario) {
		this.passUsuario = passUsuario;
	}

	/**
	 * Gets the tipo usuario.
	 *
	 * @return the tipo usuario
	 */
	public List<Object> getTipoUsuario() {
		return tipoUsuario;
	}

	/**
	 * Sets the tipo usuario.
	 *
	 * @param list the new tipo usuario
	 */
	public void setTipoUsuario(List<Object> list) {
		this.tipoUsuario = list;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	//Método toString()
	@Override
	public String toString() {
		return "Usuarios [idUsuario=" + idUsuario + ", passUsuario=" + passUsuario + ", tipoUsuario=" + tipoUsuario
				+ "]";
	}
	

}
