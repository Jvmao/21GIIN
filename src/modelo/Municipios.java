/*
 * 11 ene 2022
 * Jose V. Martí
 */
package modelo;

// TODO: Auto-generated Javadoc
/**
 * The Class Municipios.
 */
public class Municipios extends Usuarios{
	
	/** The id municipio. */
	private String idMunicipio;
	
	/** The categoria municipio. */
	private int categoriaMunicipio;
	
	/**
	 * Instantiates a new municipios.
	 */
	//Constructor vacío
	public Municipios () {}

	/**
	 * Instantiates a new municipios.
	 *
	 * @param idMunicipio        the id municipio
	 * @param categoriaMunicipio the categoria municipio
	 */
	//Constructor
	public Municipios(String idMunicipio, int categoriaMunicipio) {
		super();
		this.idMunicipio = idMunicipio;
		this.categoriaMunicipio = categoriaMunicipio;
	}

	/**
	 * Gets the id municipio.
	 *
	 * @return the id municipio
	 */
	//Getters and setters
	public String getIdMunicipio() {
		return idMunicipio;
	}

	/**
	 * Sets the id municipio.
	 *
	 * @param idMunicipio the new id municipio
	 */
	public void setIdMunicipio(String idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	/**
	 * Gets the categoria municipio.
	 *
	 * @return the categoria municipio
	 */
	public int getCategoriaMunicipio() {
		return categoriaMunicipio;
	}

	/**
	 * Sets the categoria municipio.
	 *
	 * @param categoriaMunicipio the new categoria municipio
	 */
	public void setCategoriaMunicipio(int categoriaMunicipio) {
		this.categoriaMunicipio = categoriaMunicipio;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Municipios [idMunicipio=" + idMunicipio + ", categoriaMunicipio=" + categoriaMunicipio
				+ ", getTipoUsuario()=" + getTipoUsuario() + "]";
	}
		
	
}
