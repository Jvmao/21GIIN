package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JComboBox;

import modelo.Usuarios;

/**
 * @JVMARTI
 */
public class UsuariosImplDAO implements UsuarioDAO{
	
	Connection conn;
	Statement st;
	
	//private Usuarios u = new Usuarios();
	
	private static String server = "jdbc:sqlserver://localhost:1433;databaseName=proyecto";
	//private static String server = "jdbc:sqlserver://127.0.0.1:1433;databaseName=proyecto";
	private static String user = "sa";
	//private static String user = "sqlserver";
	private static String pass = "sqlServer145+";
	//private static String pass = "-a123456";

	@Override
	public Usuarios getUserId(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void listaTipoUsuarios(JComboBox<String> jc) {
		try {
			conn = DriverManager.getConnection(server, user, pass);
			Statement st = conn.createStatement();
			String query_users = "select distinct(tipoUsuario) from usuarios order by 1";
			ResultSet rs = st.executeQuery(query_users);
			
			while(rs.next()) {
				jc.addItem(rs.getString("tipoUsuario"));
			}
			conn.close();
			
		} catch (SQLException e) {
			System.out.println("Error Conexión.No se pueden mostrar los datos.");
			e.printStackTrace();
		}
	}

	@Override
	public void listaIdUsuarios(JComboBox<String> jc) {
		try {
			conn = DriverManager.getConnection(server, user, pass);
			Statement st = conn.createStatement();
			String query_users = "select idUsuario from usuarios order by 1";
			ResultSet rs = st.executeQuery(query_users);
			
			while(rs.next()) {
				jc.addItem(rs.getString("idUsuario"));
			}
			conn.close();
			
		} catch (SQLException e) {
			System.out.println("Error Conexión.No se pueden mostrar los datos.");
			e.printStackTrace();
		}
	}

	@Override
	public void addUser(String id,String tipo,int password) {
		try {
			conn = DriverManager.getConnection(server, user, pass);
			
			if(conn != null) {
				st = conn.createStatement();
				
				String insertar = "INSERT INTO usuarios (idUsuario,tipoUsuario,passUsuario) VALUES ('"+id+"','"+tipo+"','"+password+"')";
				PreparedStatement pst = conn.prepareStatement(insertar);
				pst.executeUpdate();
				
				System.out.println("Creado Nuevo Usuario en BBDD con ID: "+id+" Tipo: "+tipo+" password: "+password);
			}else {
				System.out.println("No es posible insertar nuevos usuarios, no hay conexión con BBDD");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateUser(String idInit,String id,String tipo,int password) {
		try {
			conn = DriverManager.getConnection(server, user, pass);
			
			if(conn != null) {
				st = conn.createStatement();

				String update = "UPDATE usuarios "+
	                       "set idUsuario = ?"+
	                       ",tipoUsuario = ?"+
	                       ",passUsuario = ?"+
		    		       " where idUsuario = '"+idInit+"'";
				
				PreparedStatement pst = conn.prepareStatement(update);
				pst.setString(1, id);
				pst.setString(2, tipo);
				pst.setInt(3, password);
				pst.executeUpdate();
				
				System.out.println("Actualizado Usuario en BBDD con ID: '"+id+"'");
			}else {
				System.out.println("No es posible actualizar usuarios, no hay conexión con BBDD");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void deleteUser(String id) {
		try {
			conn = DriverManager.getConnection(server, user, pass);
			
			if(conn != null) {
				st = conn.createStatement();
				
				String eliminar = "DELETE from usuarios where idUsuario = '"+id+"'";
				PreparedStatement pst = conn.prepareStatement(eliminar);
				pst.executeUpdate();
				
				System.out.println("Eliminado Usuario en BBDD con ID: '"+id+"'");
			}else {
				System.out.println("No es posible eliminar usuarios, no hay conexión con BBDD");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}
