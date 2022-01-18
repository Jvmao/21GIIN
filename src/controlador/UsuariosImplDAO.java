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
import java.util.ArrayList;
import util.ConstantsDB;



// TODO: Auto-generated Javadoc
/**
 * The Class UsuariosImplDAO.
 */
public class UsuariosImplDAO implements UsuarioDAO{
	
	/** The conn. */
	//Vairables BBDD
	private Connection conn;
	
	/** The st. */
	private Statement st;
	
	/** The rs. */
	private ResultSet rs;
	
	/** The pst. */
	private PreparedStatement pst;


	/**
	 * Lista tipo usuarios.
	 *
	 * @param c the c
	 * @return the array list
	 */
	//Método para listar tipo de usuarios de la BBDD
	@Override
	public ArrayList<?> listaTipoUsuarios(ArrayList<String> c) {
		try {
			//jc.removeAllItems(); //primero eliminamos todos los elementos
			
			//Establecemos conexión con la BBDD
			//conn = DriverManager.getConnection(ConstantsDB.server, ConstantsDB.user, ConstantsDB.pass);
			conn = ConDB.getConnection(ConstantsDB.server,ConstantsDB.user,ConstantsDB.pass);
			st = conn.createStatement();

			//Definimos la consulta
			rs = st.executeQuery(ConstantsDB.queryType);
			
			//Añadimos los elementos en la tabla
			while(rs.next()) {
				c.add(rs.getString(ConstantsDB.valueTipo.toString()));
			}
			conn.close();
			
		} catch (SQLException e) {
			System.out.println("Error Conexión.No se pueden mostrar los datos.");
			e.printStackTrace();
		}
		return c;
	}

	/**
	 * Lista id usuarios.
	 *
	 * @param c the c
	 * @return the array list
	 */
	//Método para listar ID usuarios de la BBDD
	@Override
	public ArrayList<?> listaIdUsuarios(ArrayList<String> c) {
		try {
			//conn = DriverManager.getConnection(ConstantsDB.server, ConstantsDB.user, ConstantsDB.pass);
			conn = ConDB.getConnection(ConstantsDB.server,ConstantsDB.user,ConstantsDB.pass);
			st = conn.createStatement();
			
			rs = st.executeQuery(ConstantsDB.queryUsers);
			
			while(rs.next()) {
				c.add(rs.getString(ConstantsDB.valueID.toString()));
			}
			conn.close();
			
		} catch (SQLException e) {
			System.out.println("Error Conexión.No se pueden mostrar los datos.");
			e.printStackTrace();
		}
		return c;
	}

	/**
	 * Adds the user.
	 *
	 * @param id       the id
	 * @param tipo     the tipo
	 * @param password the password
	 */
	//Método para añadir un usuario a la BBDD
	@Override
	public void addUser(String id,String tipo,String password) {
		try {
			//conn = DriverManager.getConnection(ConstantsDB.server, ConstantsDB.user, ConstantsDB.pass);
			conn = ConDB.getConnection(ConstantsDB.server,ConstantsDB.user,ConstantsDB.pass);
			
			if(conn != null) {
				st = conn.createStatement();
				
				String insertar = "INSERT INTO usuarios (idUsuario,tipoUsuario,passUsuario) VALUES ('"+id+"','"+tipo+"','"+password+"')";
				pst = conn.prepareStatement(insertar);
				pst.executeUpdate();
				
				System.out.println("Creado Nuevo Usuario en BBDD con ID: "+id+" Tipo: "+tipo+" password: "+password);
			}else {
				System.out.println("No es posible insertar nuevos usuarios, no hay conexión con BBDD");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Update user.
	 *
	 * @param id       the id
	 * @param tipo     the tipo
	 * @param password the password
	 */
	//Método para actualizar un usuario de la BBDD
	@Override
	public void updateUser(String id,String tipo,String password) {
		try {
			//conn = DriverManager.getConnection(ConstantsDB.server, ConstantsDB.user, ConstantsDB.pass);
			conn = ConDB.getConnection(ConstantsDB.server,ConstantsDB.user,ConstantsDB.pass);
			
			if(conn != null) {
				st = conn.createStatement();

				String update = "UPDATE usuarios "+
	                      		"SET idUsuario = ?"+
	                      		",tipoUsuario = ?"+
	                      		",passUsuario = ?"+
	                      		" WHERE idUsuario = '"+id+"'";
				
				pst = conn.prepareStatement(update);
				pst.setString(1, id);
				pst.setString(2, tipo);
				pst.setString(3, password);
				pst.executeUpdate();
				
				System.out.println("Actualizado Usuario en BBDD con ID: '"+id+"'");
			}else {
				System.out.println("No es posible actualizar usuarios, no hay conexión con la BBDD");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Delete user.
	 *
	 * @param id the id
	 */
	//Método para eliminar un usuario de la BBDD
	@Override
	public void deleteUser(String id) {
		try {
			//conn = DriverManager.getConnection(ConstantsDB.server, ConstantsDB.user, ConstantsDB.pass);
			conn = ConDB.getConnection(ConstantsDB.server,ConstantsDB.user,ConstantsDB.pass);
			
			if(conn != null) {
				st = conn.createStatement();
				
				String eliminar = "DELETE "+
								  "FROM usuarios "+
								  "WHERE idUsuario = '"+id+"'";
				pst = conn.prepareStatement(eliminar);
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

	/**
	 * Lista id user conv.
	 *
	 * @param c the c
	 * @return the array list
	 */
	//Lista id usuarios enlazados con convocatorias y presentaciones, siendo solo Cuentadantes o Fiscales
	@Override
	public ArrayList<?> listaIdUserConv(ArrayList<String> c) {
		try {
			conn = ConDB.getConnection(ConstantsDB.server,ConstantsDB.user,ConstantsDB.pass);
			st = conn.createStatement();
			
			rs = st.executeQuery(ConstantsDB.queryListaIdUserConv);
			
			while(rs.next()) {
				c.add(rs.getString(ConstantsDB.valueID.toString()));
			}
			conn.close();
			
		} catch (SQLException e) {
			System.out.println("Error Conexión.No se pueden mostrar los datos.");
			e.printStackTrace();
		}
		return c;
	}



}
