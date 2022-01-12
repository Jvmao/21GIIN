/*
 * 11 ene 2022
 * Jose V. Martí
 */
package controlador;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import util.ConstantsDB;

/**
 * The Class ConDB.
 */
public class ConDB {

	/** The conn. */
	private static Connection conn = null;

	/**
	 * Gets the connection.
	 *
	 * @param server the server
	 * @param user   the user
	 * @param pass   the pass
	 * @return the connection
	 */
	public static Connection getConnection(String server,String user,String pass) {
		try {
			
			// Creamos el objeto Properties
	        Properties props = new Properties();
	        
	        //Recogemos la ruta del fichero de configuración
	        String dbPath = ConstantsDB.pathProperties;
	        
	        //Leemos el archivo
	        FileReader readFile = new FileReader(dbPath);
	            
	        //Cargamos el archivo 
	        props.load(readFile);
	        
			
			//conn = DriverManager.getConnection(ConstantsDB.server,ConstantsDB.user,ConstantsDB.pass);
			conn = DriverManager.getConnection(props.getProperty(server),
												props.getProperty(user),
												props.getProperty(pass));
			
			/**if (conn != null) {
				System.out.println("Connectado a BBDD");
				DatabaseMetaData dm = conn.getMetaData();
				System.out.println("Driver Name: "+dm.getDriverName());
				System.out.println("Driver Version: "+dm.getDriverVersion());
				System.out.println("Product Name: "+dm.getDatabaseProductName());
				System.out.println("Product Version: "+dm.getDatabaseProductVersion());

			}else {
				System.out.println("No Connectado a BBDD");
			}**/
		} catch (SQLException e) {
			System.out.println("Error en BBDD: "+e.getMessage());
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return conn;
		
	}
	

	
}
