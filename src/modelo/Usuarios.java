package modelo;


public class Usuarios {
	private String idUsuario;
	private int passUsuario;
	private String tipoUsuario;
	
	//Constructor vacío
	public Usuarios() {
		
	}

	//Constructor
	public Usuarios(String idUsuario, int passUsuario, String tipoUsuario) {
		super();
		this.idUsuario = idUsuario;
		this.passUsuario = passUsuario;
		this.tipoUsuario = tipoUsuario;
	}

	//Getter and Setters
	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getPassUsuario() {
		return passUsuario;
	}

	public void setPassUsuario(int passUsuario) {
		this.passUsuario = passUsuario;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	//Método toString()
	@Override
	public String toString() {
		return "Usuarios [idUsuario= " + idUsuario + ", passUsuario= " + passUsuario + ", tipoUsuario= " + tipoUsuario
				+ "]";
	}	

}
