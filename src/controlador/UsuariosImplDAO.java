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

import javax.swing.JOptionPane;

import util.ConstantsDB;
import util.ConstantsMessage;



/**
 * The Class UsuariosImplDAO.
 */
public class UsuariosImplDAO implements UsuarioDAO{
	
	/** The conn. */
	private Connection conn;
	
	/** The st. */
	private Statement st;
	
	/** The rs. */
	private ResultSet rs;
	
	/** The pst. */
	private PreparedStatement pst;

	
	/**
	 * Comprobación credenciales usuarios en BBDD desde pantalla Login
	 * 
	 * Check user.
	 *
	 * @param id   the id
	 * @param tipo the tipo
	 * @param pass the pass
	 * @return true, if successful
	 */
	@Override
	public boolean checkUser(String id, String tipo, String pass) {
		try {
			//Establecemos conexión con BBDD
			conn = ConDB.getConnection(ConstantsDB.server,ConstantsDB.user,ConstantsDB.pass);
			Statement st = conn.createStatement();
			
			//Definimos la consulta para comprobar que las creedenciales introducidas
			//existen en la BBDD
			String queryAccess = "SELECT * FROM usuarios "
								+ "WHERE idUsuario = '"+id+
								"' AND tipoUsuario = '"+tipo +
								"' AND passUsuario = '"+pass+"'";

			ResultSet rs = st.executeQuery(queryAccess);
			
			if(rs.next()) {
			    System.out.println("Datos Correctos \nConectado: ID Usuario: "+id+" - Tipo Usuario: "+tipo);
			    return true;
			    
			}else {
				System.out.println("Datos Incorrectos");
				JOptionPane.showMessageDialog(null,ConstantsMessage.msg6,ConstantsMessage.msg0, JOptionPane.ERROR_MESSAGE); 
				return false;
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (NullPointerException ex) {
			ex.getMessage();
		}
		
		return false;
		
	}

	/**
	 * Lista tipo usuarios.
	 *
	 * @return the array list
	 */
	//Método para listar tipo de usuarios de la BBDD
	@Override
	public ArrayList<String> listaTipoUsuarios() {
		ArrayList<String> c = new ArrayList<String>();
		try {
			
			//Establecemos conexión con la BBDD
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
	 * @return the array list
	 */
	//Método para listar ID usuarios de la BBDD
	@Override
	public ArrayList<String> listaIdUsuarios() {
		ArrayList<String> c = new ArrayList<String>();
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
	 * Añadimos nuevo usuario en la BBDD
	 * 
	 * Adds the user.
	 *
	 * @param id       the id
	 * @param tipo     the tipo
	 * @param password the password
	 */
	@Override
	public void addUser(String id,String tipo,String password) {
		try {
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
	 * Actualiza usuario en la BBDD
	 * 
	 * Update user.
	 *
	 * @param id       the id
	 * @param tipo     the tipo
	 * @param password the password
	 */
	@Override
	public void updateUser(String id,String tipo,String password) {
		try {
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
	 * Elimina al usuario seleccionado en la BBDD
	 * 
	 * Delete user.
	 *
	 * @param id the id
	 */
	@Override
	public void deleteUser(String id) {
		try {
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
	 * Lista usuarios asociados a las convocatorias
	 * 
	 * Lista id user conv.
	 *
	 * @return the array list
	 */
	@Override
	public ArrayList<String> listaIdUserConv() {
		ArrayList<String> c = new ArrayList<String>();
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
