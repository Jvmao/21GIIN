package modelo;

import java.util.List;



/**
 * @JVMARTI
 */
public class Usuarios{
	private String idUsuario;
	private String passUsuario;
	private List<Object> tipoUsuario;
	
	//Constructor vacío
	public Usuarios() {
		
	}

	//Constructor
	public Usuarios(String idUsuario, String passUsuario, List<Object> tipoUsuario) {
		super();
		this.idUsuario = idUsuario;
		this.passUsuario = passUsuario;
		this.tipoUsuario = tipoUsuario;
	}

	//Getters and setters
	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getPassUsuario() {
		return passUsuario;
	}

	public void setPassUsuario(String passUsuario) {
		this.passUsuario = passUsuario;
	}

	public List<Object> getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(List<Object> list) {
		this.tipoUsuario = list;
	}

	//Método toString()
	@Override
	public String toString() {
		return "Usuarios [idUsuario=" + idUsuario + ", passUsuario=" + passUsuario + ", tipoUsuario=" + tipoUsuario
				+ "]";
	}
	

}
