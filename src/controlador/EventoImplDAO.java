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
import java.util.*;
import util.ConstantsDB;

/**
 * The Class EventoImplDAO.
 */

public class EventoImplDAO implements EventoDAO{

	/** The st. */
	//Variables BBDD
	Statement st;
	
	/** The rs. */
	ResultSet rs;
	
	/** The conn. */
	Connection conn;
	
	/** The pst. */
	PreparedStatement pst;

	/**
	 * Adds the evento.
	 *
	 * @param id            the id
	 * @param desc          the desc
	 * @param fechaApertura the fecha apertura
	 * @param fechaCierre   the fecha cierre
	 * @param estado        the estado
	 * @param docs          the docs
	 */
	@Override
	public void addEvento(String id, String desc, String fechaApertura, String fechaCierre, Boolean estado,
			ArrayList<String> docs) {

		try {
			//conn = DriverManager.getConnection(ConstantsDB.server, ConstantsDB.user, ConstantsDB.pass);
			conn = ConDB.getConnection(ConstantsDB.server,ConstantsDB.user,ConstantsDB.pass);
			
			if(conn != null) {
				st = conn.createStatement();
				String insertarEvento = "INSERT INTO convocatorias "
										+ "(idConvocatorias,descPresentacion,fechaApertura,fechaCierre,estadoApertura,docsPresentados) "
										+ "VALUES ('"+id+"','"+desc+"',CONVERT(DATETIME, '"+fechaApertura+"',103),CONVERT(DATETIME, '"
										+fechaCierre+"',103),CAST('"+estado+"' AS BIT),'"+docs+"')";
				
				pst = conn.prepareStatement(insertarEvento);
				pst.executeUpdate();
				
				System.out.println("Convocatoria Creada Correctamente con id: "+id+"\n descripción: "+desc+"\n Fecha Apertura: "+fechaApertura
						+"\n Fecha Cierre: "+fechaCierre+" \n Estado: "+estado+"\n Documentos: "+docs);
				
			}else {
				System.out.println("No es posible insertar nuevas convocatorias, no hay conexión con BBDD");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Mod evento.
	 *
	 * @param id            the id
	 * @param desc          the desc
	 * @param fechaApertura the fecha apertura
	 * @param fechaCierre   the fecha cierre
	 * @param estado        the estado
	 * @param docs          the docs
	 */
	@Override
	public void modEvento(String id, String desc, String fechaApertura, String fechaCierre, Boolean estado,ArrayList<String> docs) {
		
		try {
			//conn = DriverManager.getConnection(ConstantsDB.server, ConstantsDB.user, ConstantsDB.pass);
			conn = ConDB.getConnection(ConstantsDB.server,ConstantsDB.user,ConstantsDB.pass);
			
			if(conn != null) {
				st = conn.createStatement();

				String updateEvento = "UPDATE convocatorias "+
	                      			  "SET idConvocatorias = ?"+
	                      			  ",descPresentacion = ?"+
	                      			  ",fechaApertura = CONVERT(DATETIME,?,103)"+
	                      			  ",fechaCierre = CONVERT(DATETIME,?,103)"+
	                      			  ",estadoApertura = CAST(? AS BIT)"+
	                      			  ",docsPresentados = ?"+
	                      			  " WHERE idConvocatorias = '"+id+"'";

				pst = conn.prepareStatement(updateEvento);
				pst.setString(1, id);
				pst.setString(2, desc);
				pst.setString(3, fechaApertura);
				pst.setString(4, fechaCierre);
				pst.setBoolean(5, estado);
				
				//Pasamos los valores a un nuevo arraylist para poder actualizarlo en la BBDD
				ArrayList<String> out = new ArrayList<String>();
				String value ="";
				for(int i = 0; i < docs.size(); i++) {
					value = docs.get(i);
					out.add(value);
				}
				pst.setString(6,out.toString());
				
				pst.executeUpdate(); //ejecutamos update
				
				System.out.println("Actualizada Convocatoria en BBDD con ID: '"+id+"'");
			}else {
				System.out.println("No es posible actualizar convocatorias, no hay conexión con la BBDD");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	

	/**
	 * Del evento.
	 *
	 * @param id the id
	 */
	@Override
	public void delEvento(String id) {
		try {
			//conn = DriverManager.getConnection(ConstantsDB.server, ConstantsDB.user, ConstantsDB.pass);
			conn = ConDB.getConnection(ConstantsDB.server,ConstantsDB.user,ConstantsDB.pass);
			
			if(conn != null) {
				st = conn.createStatement();
				
				String eliminarConvocatoria = "DELETE "
								  			 +"FROM convocatorias "
								             +"WHERE idConvocatorias = '"+id+"'";
				pst = conn.prepareStatement(eliminarConvocatoria);
				pst.executeUpdate();
				
				System.out.println("Convocatoria Eliminada de BBDD con ID: '"+id+"'");
			}else {
				System.out.println("No es posible eliminar convocatoria, no hay conexión con BBDD");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
