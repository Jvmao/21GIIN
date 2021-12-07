package controlador;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

import util.Constants;

/**
 * @JVMARTI
 */
public class ConDB {
	
	private static Connection conn = null;
	//private static String server = "jdbc:sqlserver://127.0.0.1:1433;databaseName=proyecto";
	//private static String user = "sqlserver";
	//private static String pass = "-a123456";

	public static Connection getConnection() {
		try {
			conn = DriverManager.getConnection(Constants.server,Constants.user,Constants.pass);
			
			if (conn != null) {
				DatabaseMetaData dm = conn.getMetaData();
				System.out.println("Connectado a BBDD");
				System.out.println("Driver Name: "+dm.getDriverName());
				System.out.println("Driver Version: "+dm.getDriverVersion());
				System.out.println("Product Name: "+dm.getDatabaseProductName());
				System.out.println("Product Version: "+dm.getDatabaseProductVersion());
				
				System.out.println("Conectado a BBDD");
			}else {
				System.out.println("No Connectado a BBDD");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
		
	}
	

	
}
