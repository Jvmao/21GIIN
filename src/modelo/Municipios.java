package modelo;

/**
 * @JVMARTI
 */
public class Municipios{
	
	private String idMunicipio;
	private int catMunicipio;
	private String usuarioMunicipio;
	
	//Constructor vacío
	public Municipios () {}

	//Constructor
	public Municipios(String idMunicipio, int catMunicipio, String usuarioMunicipio) {
		super();
		this.idMunicipio = idMunicipio;
		this.catMunicipio = catMunicipio;
		this.usuarioMunicipio = usuarioMunicipio;
	}

	//Getter and Setters
	public String getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(String idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	public int getCatMunicipio() {
		return catMunicipio;
	}

	public void setCatMunicipio(int catMunicipio) {
		this.catMunicipio = catMunicipio;
	}

	public String getUsuarioMunicipio() {
		return usuarioMunicipio;
	}

	public void setUsuarioMunicipio(String usuarioMunicipio) {
		this.usuarioMunicipio = usuarioMunicipio;
	}

	//Método toString()
	@Override
	public String toString() {
		return "Municipios [idMunicipio= " + idMunicipio + ", catMunicipio= " + catMunicipio + ", usuarioMunicipio= "
				+ usuarioMunicipio + "]";
	}
		
	
}
