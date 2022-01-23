/*
 * 23 ene 2022
 * Jose V. Martí
 */
package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import util.ConstantsDB;



/**
 * The Class MunicipiosImplDAO.
 */
public class MunicipiosImplDAO implements MunicipioDAO{

	/** The conn. */
	private Connection conn;
	
	/** The st. */
	private Statement st;
	
	/** The rs. */
	private ResultSet rs;
	
	/** The pst. */
	private PreparedStatement pst;
	
	
	/**
	 * Lista id usuarios municipios.
	 *
	 * @return the array list
	 */
	@Override
	public ArrayList<String> listaIdUsuariosMunicipios() {
		ArrayList<String> c = new ArrayList<String>();
		try {
			//conn = DriverManager.getConnection(ConstantsDB.server, ConstantsDB.user, ConstantsDB.pass);
			conn = ConDB.getConnection(ConstantsDB.server,ConstantsDB.user,ConstantsDB.pass);
			
			st = conn.createStatement();

			rs = st.executeQuery(ConstantsDB.queryListaIdUsuariosMunicipios);
			
			while(rs.next()) {
				//c.add(rs.getString(ConstantsDB.valueID));
				c.add(rs.getString(ConstantsDB.valueID));
			}
			conn.close();
			
		} catch (SQLException e) {
			System.out.println("Error Conexión.No se pueden mostrar los datos.");
			e.printStackTrace();
		}
		return c;
		
	}

	/**
	 * Lista tipo usuarios mun.
	 *
	 * @return the array list
	 */
	@Override
	public ArrayList<String> listaTipoUsuariosMun() {
		ArrayList<String> c = new ArrayList<String>();
		try {
			//conn = DriverManager.getConnection(ConstantsDB.server, ConstantsDB.user, ConstantsDB.pass);
			conn = ConDB.getConnection(ConstantsDB.server,ConstantsDB.user,ConstantsDB.pass);
			st = conn.createStatement();

			rs = st.executeQuery(ConstantsDB.tipoUsuarioMunicipio);
			
			while(rs.next()) {
				c.add(rs.getString(ConstantsDB.valueTipoUsuarioMunicipio));
			}
			conn.close();
			
		} catch (SQLException e) {
			System.out.println("Error Conexión.No se pueden mostrar los datos.");
			e.printStackTrace();
		}
		return c;
		
	}


	/**
	 * Añade un nuevo municipio en la BBDD
	 * 
	 * Adds the municipio.
	 *
	 * @param id        the id
	 * @param categoria the categoria
	 * @param idUser    the id user
	 * @param tipo      the tipo
	 */
	@Override
	public void addMunicipio(String id, int categoria, String idUser, String tipo) {
		try {
			//conn = DriverManager.getConnection(ConstantsDB.server, ConstantsDB.user, ConstantsDB.pass);
			conn = ConDB.getConnection(ConstantsDB.server,ConstantsDB.user,ConstantsDB.pass);
			
			if(conn != null) {
				st = conn.createStatement();
				
				String insertar = "INSERT INTO municipios (idMunicipio,catMunicipio,idUser,tipoUsuario) "
						+ "VALUES ('"+id+"','"+categoria+"','"+idUser+"','"+tipo+"')";
	
				
				pst = conn.prepareStatement(insertar);
				pst.executeUpdate();
				
				System.out.println("Creado Nuevo Municipio en BBDD con ID: "
									+id+" Categoria: "+categoria+" Tipo: "+tipo);
			}else {
				System.out.println("No es posible insertar nuevos municipios, no hay conexión con BBDD");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * Actualizamos el municipio seleccionado en la BBDD
	 * 
	 * Update municipio.
	 *
	 * @param idMun       the id mun
	 * @param catMun      the cat mun
	 * @param idUser      the id user
	 * @param tipoUsuario the tipo usuario
	 */
	@Override
	public void updateMunicipio(String idMun, int catMun,String idUser, String tipoUsuario) {
		try {
			conn = ConDB.getConnection(ConstantsDB.server,ConstantsDB.user,ConstantsDB.pass);
			
			if(conn != null) {
				st = conn.createStatement();

				String updateMunicipios = "UPDATE municipios "+
				                      	  "SET idMunicipio = ?"+
				                      	  ",catMunicipio = ?"+
				                      	  ",idUser = ?"+
				                      	  ",tipoUsuario = ?"+
				                      	  " WHERE idMunicipio = '"+idMun+"'";

				
				pst = conn.prepareStatement(updateMunicipios);
				pst.setString(1, idMun);
				pst.setInt(2, catMun);
				pst.setString(3, idUser);
				pst.setString(4, tipoUsuario);
				
				pst.executeUpdate();
				
				System.out.println("Actualizado Municipio en BBDD con ID: '"+idMun+"'");
			}else {
				System.out.println("No es posible actualizar municipios, no hay conexión con la BBDD");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * Eliminamos en municpio seleccionado en la BBDD
	 * 
	 * Delete municipio.
	 *
	 * @param id the id
	 */
	@Override
	public void deleteMunicipio(String id) {
		try {
			//conn = DriverManager.getConnection(ConstantsDB.server, ConstantsDB.user, ConstantsDB.pass);
			conn = ConDB.getConnection(ConstantsDB.server,ConstantsDB.user,ConstantsDB.pass);
			
			if(conn != null) {
				st = conn.createStatement();
				
				String eliminar = "DELETE "+
								  "FROM municipios "+
								  "WHERE idMunicipio = '"+id+"'";
				
				
				pst = conn.prepareStatement(eliminar);
				pst.executeUpdate();
				
				System.out.println("Eliminado Municipio en BBDD con ID: '"+id+"'");
			}else {
				System.out.println("No es posible eliminar municipios, no hay conexión con BBDD");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}


}
