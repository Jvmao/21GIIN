/*
 * 18 ene 2022
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

// TODO: Auto-generated Javadoc
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
	 * Lista ID convocatoria.
	 *
	 * @param c the c
	 * @return the array list
	 */
	public ArrayList<?> listaIDConvocatoria(ArrayList<String> c){
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
				/**String insertarEvento = "INSERT INTO convocatorias "
										+ "(idConvocatorias,idUser,descPresentacion,fechaApertura,fechaCierre,estadoApertura,docsPresentados) "
										+ "VALUES ('"+idConv+"','"+idUser+"','"+desc+"',"
												+ "CONVERT(DATETIME, '"+fechaApertura+"',103),"
												+ "CONVERT(DATETIME, '"+fechaCierre+"',103),"
											    + "CAST('"+estado+"' AS BIT),'"+docs+"')";**/
				//Postgresql
				String insertarEvento = "INSERT INTO convocatorias "
						+ "(idConvocatorias,idUser,descPresentacion,fechaApertura,fechaCierre,estadoApertura,docsPresentados) "
						+ "VALUES ('"+idConv+"','"+idUser+"','"+desc+"',"
								+ "TO_TIMESTAMP('"+fechaApertura+"','DD/MM/YYYY'),"
								+ "TO_TIMESTAMP('"+fechaCierre+"','DD/MM/YYYY'),"
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

				/**String updateEvento = "UPDATE convocatorias "+
	                      			  "SET idConvocatorias = ?"+
	                      			  ",idUser = ?"+
	                      			  ",descPresentacion = ?"+
	                      			  ",fechaApertura = CONVERT(DATETIME,?,103)"+
	                      			  ",fechaCierre = CONVERT(DATETIME,?,103)"+
	                      			  ",estadoApertura = CAST(? AS BIT)"+
	                      			  ",docsPresentados = ?"+
	                      			  " WHERE idConvocatorias = '"+idConv+"'";**/
				
				//Postgresql
				String updateEvento = "UPDATE convocatorias "+
            			  "SET idConvocatorias = ?"+
            			  ",idUser = ?"+
            			  ",descPresentacion = ?"+
            			  ",fechaApertura = TO_TIMESTAMP(DATETIME,?,DD/MM/YYYY)"+
            			  ",fechaCierre = TO_TIMESTAMP(DATETIME,?,DD/MM/YYYY)"+
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
				
				/**String eliminarConvocatoria = "DELETE "
								  			 +"FROM convocatorias "
								             +"WHERE idConvocatorias = '"+idConv+"'";**/
				//Postgresql
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
	

}
