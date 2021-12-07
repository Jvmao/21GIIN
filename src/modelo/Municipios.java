package modelo;

/**
 * @JVMARTI
 */
public class Municipios extends Usuarios{
	
	private String idMunicipio;
	private int categoriaMunicipio;
	
	//Constructor vacío
	public Municipios () {}

	//Constructor
	public Municipios(String idMunicipio, int categoriaMunicipio) {
		super();
		this.idMunicipio = idMunicipio;
		this.categoriaMunicipio = categoriaMunicipio;
	}

	//Getters and setters
	public String getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(String idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	public int getCategoriaMunicipio() {
		return categoriaMunicipio;
	}

	public void setCategoriaMunicipio(int categoriaMunicipio) {
		this.categoriaMunicipio = categoriaMunicipio;
	}

	@Override
	public String toString() {
		return "Municipios [idMunicipio=" + idMunicipio + ", categoriaMunicipio=" + categoriaMunicipio
				+ ", getTipoUsuario()=" + getTipoUsuario() + "]";
	}
		
	
}
