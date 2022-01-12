/*
 * 11 ene 2022
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
 * The Class PresentacionImplDAO.
 */

public class PresentacionImplDAO implements PresentacionDAO{

	/** The conn. */
	//Variables BBDD
	Connection conn;
	
	/** The st. */
	Statement st;
	
	/** The rs. */
	ResultSet rs;
	
	/** The pst. */
	PreparedStatement pst;
	
	

	/**
	 * Adds the presentacion.
	 *
	 * @param id            the id
	 * @param tipo          the tipo
	 * @param timestamp     the timestamp
	 * @param estado        the estado
	 * @param docPresentado the doc presentado
	 */
	@Override
	public void addPresentacion(String id, String tipo, String timestamp, boolean estado,
			ArrayList<String> docPresentado) {
		
		try {
			//conn = DriverManager.getConnection(ConstantsDB.server, ConstantsDB.user, ConstantsDB.pass);
			conn = ConDB.getConnection(ConstantsDB.server,ConstantsDB.user,ConstantsDB.pass);
			
			if(conn != null) {
				st = conn.createStatement();
				
				String insertarPres = "INSERT INTO presentaciones (idUser,typeUser,fecha,estado,docs) "
						+ "VALUES ('"+id+"','"+tipo+"',CONVERT(DATETIME,'"+timestamp+"',103),CAST('"+estado+"' AS BIT),'"+docPresentado+"' )";
				pst = conn.prepareStatement(insertarPres);
				pst.executeUpdate();
				
				System.out.println("Creada Nueva Presentación en BBDD con ID: "+id+" tipo usuario: "+tipo+" y fecha: "+timestamp);
			}else {
				System.out.println("No es posible insertar nuevos usuarios, no hay conexión con BBDD");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
	}

	/**
	 * Mod presentacion.
	 *
	 * @param idPres    the id pres
	 * @param id        the id
	 * @param tipo      the tipo
	 * @param timestamp the timestamp
	 * @param estado    the estado
	 * @param docs      the docs
	 */
	@Override
	public void modPresentacion(String idPres,String id, String tipo, String timestamp, boolean estado,
			ArrayList<String> docs) {
		
		try {
			//conn = DriverManager.getConnection(ConstantsDB.server, ConstantsDB.user, ConstantsDB.pass);
			conn = ConDB.getConnection(ConstantsDB.server,ConstantsDB.user,ConstantsDB.pass);
			
			if(conn != null) {
				st = conn.createStatement();

				String updatePres = "UPDATE presentaciones "+
	                      			  "SET idUser = ?"+
	                      			  ",typeUser = ?"+
	                      			  ",fecha = CONVERT(DATETIME,?,103)"+
	                      			  ",estado = CAST(? AS BIT)"+
	                      			  ",docs = ?"+
	                      			  " WHERE idPres = '"+idPres+"' " ;

				pst = conn.prepareStatement(updatePres);
				pst.setString(1, id);
				pst.setString(2, tipo);
				pst.setString(3, timestamp);
				pst.setBoolean(4, estado);
				
				//Pasamos los valores a un nuevo arraylist para poder actualizarlo en la BBDD
				ArrayList<String> out = new ArrayList<String>();
				String value ="";
				for(int i = 0; i < docs.size(); i++) {
					value = docs.get(i);
					out.add(value);
				}
				pst.setString(5,out.toString());
				
				
				pst.executeUpdate(); //ejecutamos update
				
				System.out.println("Actualizada Presentación en BBDD con ID: "+idPres);
			}else {
				System.out.println("No es posible actualizar convocatorias, no hay conexión con la BBDD");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	/**
	 * Del presentacion.
	 *
	 * @param idPres the id pres
	 */
	@Override
	public void delPresentacion(String idPres) {
		try {
			//conn = DriverManager.getConnection(ConstantsDB.server, ConstantsDB.user, ConstantsDB.pass);
			conn = ConDB.getConnection(ConstantsDB.server,ConstantsDB.user,ConstantsDB.pass);
			
			if(conn != null) {
				st = conn.createStatement();
				
				String eliminarPresentacion = "DELETE "
								  			 +"FROM presentaciones "
								             +"WHERE idPres = '"+idPres+"'";
				pst = conn.prepareStatement(eliminarPresentacion);
				pst.executeUpdate();
				
				System.out.println("Presentación Eliminada de BBDD con ID: '"+idPres+"'");
			}else {
				System.out.println("No es posible eliminar convocatoria, no hay conexión con BBDD");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}


}
