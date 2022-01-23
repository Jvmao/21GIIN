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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import util.ConstantsDB;


/**
 * The Class PresentacionImplDAO.
 */

public class PresentacionImplDAO implements PresentacionDAO{

	/** The conn. */
	//Variables BBDD
	private Connection conn;
	
	/** The pst. */
	private PreparedStatement pst;
	
	/** The rs. */
	private ResultSet rs;
	
	/** The st. */
	private Statement st;
	

	/**
	 * Añadimos nueva presentación en la BBDD
	 * 
	 * Adds the presentacion.
	 *
	 * @param idPres        the id pres
	 * @param idConv        the id conv
	 * @param idUser        the id user
	 * @param fPres         the f pres
	 * @param estado        the estado
	 * @param docPresentado the doc presentado
	 */
	@Override
	public void addPresentacion(String idPres,String idConv,String idUser, 
								String fPres, boolean estado,ArrayList<String> docPresentado) {
		
		try {
			//conn = DriverManager.getConnection(ConstantsDB.server, ConstantsDB.user, ConstantsDB.pass);
			conn = ConDB.getConnection(ConstantsDB.server,ConstantsDB.user,ConstantsDB.pass);
			
			if(conn != null) {
				st = conn.createStatement();
				
				String insertarPres = "INSERT INTO presentaciones (idPres,idConv,idUser,fecha,estado,docs) "
									  + "VALUES ('"+idPres+"','"+idConv+"','"+idUser+"',"
									  + "CONVERT(DATETIME,'"+fPres+"',103),"
									  + "CAST('"+estado+"' AS BIT),"
									  + "'"+docPresentado+"' )";
				
				pst = conn.prepareStatement(insertarPres);
				pst.executeUpdate();
				
				System.out.println("Creada Nueva Presentación en BBDD con ID Pres."+idPres+" ID Usuario: "+idUser+
								   " y fecha: "+fPres);
			}else {
				System.out.println("No es posible insertar nuevos usuarios, no hay conexión con BBDD");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
	}

	/**
	 * Modificamos presentación seleccionada en la BBDD
	 * 
	 * Mod presentacion.
	 *
	 * @param idPres the id pres
	 * @param idConv the id conv
	 * @param idUser the id user
	 * @param fecha  the fecha
	 * @param estado the estado
	 * @param docs   the docs
	 */
	@Override
	public void modPresentacion(String idPres,String idConv,String idUser, 
								String fecha, boolean estado,ArrayList<String> docs) {
		
		try {
			//conn = DriverManager.getConnection(ConstantsDB.server, ConstantsDB.user, ConstantsDB.pass);
			conn = ConDB.getConnection(ConstantsDB.server,ConstantsDB.user,ConstantsDB.pass);
			
			if(conn != null) {
				st = conn.createStatement();

				String updatePres = "UPDATE presentaciones "+
	                      			  "SET idPres = ?"+
	                      			  ",idConv = ?"+
	                      			  ",idUser = ?"+
	                      			  ",fecha = CONVERT(DATETIME,?,103)"+
	                      			  ",estado = CAST(? AS BIT)"+
	                      			  ",docs = ?"+
	                      			  " WHERE idPres = '"+idPres+"' " ;

				pst = conn.prepareStatement(updatePres);
				pst.setString(1, idPres);
				pst.setString(2, idConv);
				pst.setString(3, idUser);
				pst.setString(4, fecha);
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
	 * Eliminamos presentación seleccionada en la BBDD
	 * 
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

	/**
	 * Obtenemos las fechas de inicio de la convocaotira seleccionada mediante una consulta en la BBDD
	 * 
	 * Fechas conv inicio.
	 *
	 * @param id the id
	 * @return the string
	 */
	@Override
	public String fechasConvInicio(String id) {
		String fInicio = null;
		try {
			conn = ConDB.getConnection(ConstantsDB.server,ConstantsDB.user,ConstantsDB.pass);
			st = conn.createStatement();
			
			String queryFechasConvocatoria = "SELECT fechaApertura "
										   + "FROM convocatorias "
										   + "WHERE idConvocatorias = '"+id+"'";
			
			rs = st.executeQuery(queryFechasConvocatoria);
			
			//Cambiamos formato fecha de BBDD
			SimpleDateFormat fromUser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			
			while(rs.next()) {
				fInicio = myFormat.format(fromUser.parse(rs.getString(ConstantsDB.valueFechaApertura)));
			}
			
		} catch (SQLException | ParseException e) {
			e.printStackTrace();
		}
		return fInicio;
	}

	/**
	 * Obtenemos las fechas de cierre de la convocaotira seleccionada mediante una consulta en la BBDD
	 * 
	 * Fecha conv fin.
	 *
	 * @param id the id
	 * @return the string
	 */
	@Override
	public String fechaConvFin(String id) {
		String fCierre = null;
		try {
			conn = ConDB.getConnection(ConstantsDB.server,ConstantsDB.user,ConstantsDB.pass);
			st = conn.createStatement();
			
			String queryFechasConvocatoria = "SELECT fechaCierre "
										   + "FROM convocatorias "
										   + "WHERE idConvocatorias = '"+id+"'";
			
			rs = st.executeQuery(queryFechasConvocatoria);
			
			//Cambiamos formato fecha de BBDD
			SimpleDateFormat fromUser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			
			while(rs.next()) {
				fCierre = myFormat.format(fromUser.parse(rs.getString(ConstantsDB.valueFechaCierre)));
			}
			
		} catch (SQLException | ParseException e) {
			e.printStackTrace();
		}
		return fCierre;
	}
	
	


}
