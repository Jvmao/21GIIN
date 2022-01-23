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
import java.util.*;
import util.ConstantsDB;

/**
 * The Class ConvImplDAO.
 */

public class ConvImplDAO implements ConvDAO{

	/** The st. */
	//Variables BBDD
	private Statement st;
	
	/** The rs. */
	private ResultSet rs;
	
	/** The conn. */
	private Connection conn;
	
	/** The pst. */
	private PreparedStatement pst;
	
	/**
	 * Listamos ID usuarios convocatoria
	 * 
	 * Lista ID convocatoria.
	 *
	 * @return the array list
	 */
	public ArrayList<String> listaIDConvocatoria(){
		ArrayList<String> c = new ArrayList<String>();
		try {
			//conn = DriverManager.getConnection(ConstantsDB.server, ConstantsDB.user, ConstantsDB.pass);
			conn = ConDB.getConnection(ConstantsDB.server,ConstantsDB.user,ConstantsDB.pass);
			
			st = conn.createStatement();

			rs = st.executeQuery(ConstantsDB.queryListaIdConvocatorias);
			
			while(rs.next()) {
				c.add(rs.getString(ConstantsDB.valueIDConvocatorias));
			}
			//conn.close();
			
		} catch (SQLException e) {
			System.out.println("Error Conexión.No se pueden mostrar los datos.");
			e.printStackTrace();
		}
		return c;
	}
	

	/**
	 * Añade nuevo evento en la BBDD
	 * 
	 * Adds the evento.
	 *
	 * @param idConv        the id conv
	 * @param idUser        the id user
	 * @param desc          the desc
	 * @param fechaApertura the fecha apertura
	 * @param fechaCierre   the fecha cierre
	 * @param estado        the estado
	 * @param docs          the docs
	 */
	@Override
	public void addEvento(String idConv,String idUser, String desc, String fechaApertura, String fechaCierre, Boolean estado,
			ArrayList<String> docs) {

		try {
			//conn = DriverManager.getConnection(ConstantsDB.server, ConstantsDB.user, ConstantsDB.pass);
			conn = ConDB.getConnection(ConstantsDB.server,ConstantsDB.user,ConstantsDB.pass);
			
			if(conn != null) {
				st = conn.createStatement();
				String insertarEvento = "INSERT INTO convocatorias "
										+ "(idConvocatorias,idUser,descPresentacion,fechaApertura,fechaCierre,estadoApertura,docsPresentados) "
										+ "VALUES ('"+idConv+"','"+idUser+"','"+desc+"',"
												+ "CONVERT(DATETIME, '"+fechaApertura+"',103),"
												+ "CONVERT(DATETIME, '"+fechaCierre+"',103),"
											    + "CAST('"+estado+"' AS BIT),'"+docs+"')";
				
				pst = conn.prepareStatement(insertarEvento);
				pst.executeUpdate();
				
				System.out.println("Convocatoria Creada Correctamente con idConv: "+idConv+", idUsuario: "+idUser+", descripción: "+desc+"\n Fecha Apertura: "+fechaApertura
						+"\n Fecha Cierre: "+fechaCierre+" \n Estado: "+estado+"\n Documentos: "+docs);
				
			}else {
				System.out.println("No es posible insertar nuevas convocatorias, no hay conexión con BBDD");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Modifica un evento seleccionado en la BBDD
	 * 
	 * Mod evento.
	 *
	 * @param idConv        the id conv
	 * @param idUser        the id user
	 * @param desc          the desc
	 * @param fechaApertura the fecha apertura
	 * @param fechaCierre   the fecha cierre
	 * @param estado        the estado
	 * @param docs          the docs
	 */
	@Override
	public void modEvento(String idConv,String idUser, String desc, String fechaApertura, String fechaCierre, 
						  Boolean estado,ArrayList<String> docs) {
		
		try {
			//conn = DriverManager.getConnection(ConstantsDB.server, ConstantsDB.user, ConstantsDB.pass);
			conn = ConDB.getConnection(ConstantsDB.server,ConstantsDB.user,ConstantsDB.pass);
			
			if(conn != null) {
				st = conn.createStatement();

				String updateEvento = "UPDATE convocatorias "+
	                      			  "SET idConvocatorias = ?"+
	                      			  ",idUser = ?"+
	                      			  ",descPresentacion = ?"+
	                      			  ",fechaApertura = CONVERT(DATETIME,?,103)"+
	                      			  ",fechaCierre = CONVERT(DATETIME,?,103)"+
	                      			  ",estadoApertura = CAST(? AS BIT)"+
	                      			  ",docsPresentados = ?"+
	                      			  " WHERE idConvocatorias = '"+idConv+"'";


				pst = conn.prepareStatement(updateEvento);
				pst.setString(1, idConv);
				pst.setString(2, idUser);
				pst.setString(3, desc);
				pst.setString(4, fechaApertura);
				pst.setString(5, fechaCierre);
				pst.setBoolean(6, estado);
				
				//Pasamos los valores a un nuevo arraylist para poder actualizarlo en la BBDD
				ArrayList<String> out = new ArrayList<String>();
				String value ="";
				for(int i = 0; i < docs.size(); i++) {
					value = docs.get(i);
					out.add(value);
				}
				pst.setString(7,out.toString());
				
				pst.executeUpdate(); //ejecutamos update
				
				System.out.println("Actualizada Convocatoria en BBDD con ID: '"+idConv+"'");
			}else {
				System.out.println("No es posible actualizar convocatorias, no hay conexión con la BBDD");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	

	/**
	 * Elimina un evento seleccionado en la BBDD
	 * 
	 * Del evento.
	 *
	 * @param idConv the id conv
	 */
	@Override
	public void delEvento(String idConv) {
		try {
			//conn = DriverManager.getConnection(ConstantsDB.server, ConstantsDB.user, ConstantsDB.pass);
			conn = ConDB.getConnection(ConstantsDB.server,ConstantsDB.user,ConstantsDB.pass);
			
			if(conn != null) {
				st = conn.createStatement();
				
				String eliminarConvocatoria = "DELETE "
								  			 +"FROM convocatorias "
								             +"WHERE idConvocatorias = '"+idConv+"'";

				
				pst = conn.prepareStatement(eliminarConvocatoria);
				pst.executeUpdate();
				
				System.out.println("Convocatoria Eliminada de BBDD con ID: '"+idConv+"'");
			}else {
				System.out.println("No es posible eliminar convocatoria, no hay conexión con BBDD");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	/**
	 * Info usuarios conv.
	 *
	 * @param idConv the id conv
	 * @return the string
	 */
	//Método para obtener los usuarios asociados a las convocatorias desde la consulta a la BBDD
	@Override
	public String infoUsuariosConv(String idConv) {
		String tipo = null;
		try {
			conn = ConDB.getConnection(ConstantsDB.server,ConstantsDB.user,ConstantsDB.pass);
			st = conn.createStatement();
			
			String queryInfoUsuarioConv = "SELECT tipoUsuario "
										   + "FROM usuarios "
										   + "WHERE idUsuario = '"+idConv+"' ";
			
			rs = st.executeQuery(queryInfoUsuarioConv);
			
			while(rs.next()) {
				tipo = rs.getString(ConstantsDB.valueTipo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tipo;
		
	}
	

}
