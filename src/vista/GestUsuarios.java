package vista;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.UsuariosImplDAO;
import util.Constants;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;


/**
 * @JVMARTI
 */
public class GestUsuarios extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lbTitulo,lbTipoUsuario,lbID,lbTipo,lbPass;
	private JTextField txTipoUsuario;
	private JTable jTableUsuarios;
	private JScrollPane scrollPane;
	private JButton btnAlta,btnMod,btnBaja;
	private JComboBox<String> cbTipo;
	
	//Variable Tabla
	private DefaultTableModel model;
	
	//Definimos las columnas y el número de filas a mostrar en la tabla
	private String columns[] = { "ID", "TIPO USUARIO", "PASSWORD" };
	private String data[][] = new String[6][3];
	
	
	//Llamada clase UsuariosImplDAO
	private UsuariosImplDAO udao = new UsuariosImplDAO();
	

	//Variables Conexión
	private Connection conn;
	private JTextField txID;
	private JTextField txPass;
	//private static String pass = "-a123456";

	/**
	 * Create the panel.
	 */
	public GestUsuarios() {
		initComponents();
	}
	
	public void initComponents() {
		
		setBounds(100, 100, 650, 450);
		contentPane=this;
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setLayout(null);
		
		//Label Título Pantalla
		lbTitulo = new JLabel("Gestión Usuarios");
		lbTitulo.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
		lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitulo.setBounds(6, 6, 638, 16);
		add(lbTitulo);
		
		//Label Tipo Usuario
		lbTipoUsuario = new JLabel("Tipo Usuario Conectado");
		lbTipoUsuario.setBounds(6, 63, 187, 16);
		add(lbTipoUsuario);
		
		//JText para rellenar el tipo de usuario
		txTipoUsuario = new JTextField();
		txTipoUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		txTipoUsuario.setBounds(205, 58, 265, 26);
		txTipoUsuario.setEditable(false);
		add(txTipoUsuario);
		txTipoUsuario.setColumns(10);
		//txTipoUsuario.setText(Login.TipoUsuario);

		
		//Scroll Pane para añadir la tabla de usuarios
		scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 91, 464, 260);
		add(scrollPane);
		
		//Botón Alta Usuarios
		btnAlta = new JButton("ALTA");
		btnAlta.setBounds(482, 87, 139, 29);
		add(btnAlta);
		btnAlta.addActionListener(new InnerAction());
		
		//Botón Modificación Usuarios
		btnMod = new JButton("MODIFICACION");
		btnMod.setBounds(6, 363, 139, 29);
		add(btnMod);
		btnMod.addActionListener(new InnerAction());
		
		//Botón Baja Usuarios
		btnBaja = new JButton("ELIMINAR");
		btnBaja.setBounds(331, 363, 139, 29);
		add(btnBaja);
		btnBaja.addActionListener(new InnerAction());
		
		//Label ID usuario
		lbID = new JLabel("ID Usuario");
		lbID.setHorizontalAlignment(SwingConstants.CENTER);
		lbID.setBounds(482, 134, 130, 16);
		add(lbID);
		
		//Campo Texto ID Usuario
		txID = new JTextField();
		txID.setBounds(482, 162, 139, 26);
		add(txID);
		txID.setColumns(10);
		
		//Label Tipo Usuario
		lbTipo = new JLabel("Tipo Usuario");
		lbTipo.setHorizontalAlignment(SwingConstants.CENTER);
		lbTipo.setBounds(482, 200, 130, 16);
		add(lbTipo);
		
		//ComboBox Tipo Usuario
		cbTipo = new JComboBox<String>();
		cbTipo.setBounds(482, 228, 139, 27);
		add(cbTipo);
		udao.listaTipoUsuarios(cbTipo);
		
		//Label Password
		lbPass = new JLabel("Password");
		lbPass.setHorizontalAlignment(SwingConstants.CENTER);
		lbPass.setBounds(482, 277, 130, 16);
		add(lbPass);
		
		//Campo password
		txPass = new JTextField();
		txPass.setBounds(482, 305, 139, 26);
		add(txPass);
		txPass.setColumns(10);
				
		//Llamamos al método para mostrar los valores en la tabla
		populateUserData();

	}
	
	
	//Definimos método para mostrar los datos almacenados en la BBDD
	//respecto de la tabla usuarios
	private void populateUserData() {
		try {
			//Conexión a la BBDD
			conn = DriverManager.getConnection(Constants.server,Constants.user,Constants.pass);
			
			//Consulta a BBDD
			String query = "SELECT idUsuario,tipoUsuario,passUsuario FROM usuarios order by 1,2";
		    
		    Statement st = conn.createStatement();
		    ResultSet rs = st.executeQuery(query);
		    
		    int i = 0;

		    //Obtenemos todos los valores de la BBDD y los vamos añadiendo uno a uno en la tabla
		    while (rs.next()) {
		    	//String data[][] = new String[i][3];
		    	String id = rs.getString("idUsuario");
		    	String tipo = rs.getString("tipoUsuario");
		    	String passw = rs.getString("passUsuario");
		        
		        data[i][0] = id;
		        data[i][1] = tipo;
		        data[i][2] = passw;
		        i++;
		   }
			      
		      
		      
		  //Creamos la JTable para insertar los valores
	      model = new DefaultTableModel(data,columns);
	      jTableUsuarios = new JTable(model);
	      jTableUsuarios.setAutoCreateRowSorter(true);
	      jTableUsuarios.setFont(new Font("Verdana", Font.PLAIN, 12));
	      jTableUsuarios.setShowGrid(true);
	      jTableUsuarios.setShowVerticalLines(true);
	      scrollPane.setViewportView(jTableUsuarios);
		      
		} catch (SQLException e) {
			e.printStackTrace();
		}
	      
	}
	
	
	
	public void setValorTipoUsuario (String nombre){
		 //Recogemos el valor de Tipo de Usuario desde la clase de MenuPrincipal
		 txTipoUsuario.setText(nombre);
	}
	
	
	//Definimos los actionListener de los componentes desde la inner class
	public class InnerAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==btnAlta) {
				
				//Comprobamos que los campos de ID y password no estén vacíos
				if(txID.getText().length() != 0 || (txPass.getText().length() != 0)) {
					//Llamamos al método para añadir usuario a la BBDD desde la clase UsuariosImplDAO
					udao.addUser(txID.getText(),cbTipo.getSelectedItem().toString() , txPass.getText());
					
					//Rellenamos los nuevos valores en la tabla
					Object[] file = new Object[3];
					file[0]=txID.getText().toString();
					file[1]=cbTipo.getSelectedItem().toString();
					file[2]=txPass.getText().toString();
					model.addRow(file);
					
					//Una vez finalizada la operación de añadir un nuevo usuario, reiniciamos todos los campos
					txID.setText("");
					cbTipo.setSelectedIndex(0);
					txPass.setText("");
				}else {
					//Se muestra el siguiente mensaje cuando hay campo vacíos
					JOptionPane.showMessageDialog(null,"Faltan Campos por Rellenar","Mensaje Error",JOptionPane.ERROR_MESSAGE);
				}
				//Determinar tamaño jtable de manera dinamica
				
			}
			
			if(e.getSource()==btnMod) {
				//Comprobamos si se ha seleccionado alguna fila cuando se elimina un usuario.
				if(jTableUsuarios.getSelectionModel().isSelectionEmpty()==true ) {
					//Si no selecciona una fila de la tabla y presiona el botón btnBaja se muestra el siguiente mensaje
					JOptionPane.showMessageDialog(null,"Debe seleccionar un usuario de la tabla","Mensaje Error",JOptionPane.ERROR_MESSAGE);
				}else {
					//Elimina al usuario de la tabla y de la BBDD
					DefaultTableModel model = (DefaultTableModel) jTableUsuarios.getModel() ;
					int col = 0;
					int rows = jTableUsuarios.getSelectedRow();
					String idInit = jTableUsuarios.getModel().getValueAt(rows, col).toString();
					
					String id = jTableUsuarios.getValueAt(jTableUsuarios.getSelectedRow(), 0).toString();
					String tipo = jTableUsuarios.getValueAt(jTableUsuarios.getSelectedRow(), 1).toString();
					String pass = jTableUsuarios.getValueAt(jTableUsuarios.getSelectedRow(), 2).toString();
					
					//El usuario debe de confirmar la eliminación del usuario seleccionado
					int confirm=JOptionPane.showConfirmDialog(null, "¿Desea actualizar al usuario seleccionado?") ;
					if(JOptionPane.OK_OPTION==confirm){
						//model.fireTableRowsUpdated(rows, rows);
						jTableUsuarios.setModel(model);
						udao.updateUser(idInit,id, tipo,pass);
					}
				}
			}
			
			if(e.getSource() == btnBaja) {
				//Comprobamos si se ha seleccionado alguna fila cuando se elimina un usuario.
				if(jTableUsuarios.getSelectionModel().isSelectionEmpty()==true ) {
					//Si no selecciona una fila de la tabla y presiona el botón btnBaja se muestra el siguiente mensaje
					JOptionPane.showMessageDialog(null,"Debe seleccionar un usuario de la tabla","Mensaje Error",JOptionPane.ERROR_MESSAGE);
				}else {
					//Elimina al usuario de la tabla y de la BBDD
					DefaultTableModel model = (DefaultTableModel) jTableUsuarios.getModel() ;
					int col = 0;
					int rows = jTableUsuarios.getSelectedRow();
					String id = jTableUsuarios.getModel().getValueAt(rows, col).toString();
					
					int a = jTableUsuarios. getSelectedRow();
					//El usuario debe de confirmar la eliminación del usuario seleccionado
					int confirm=JOptionPane.showConfirmDialog(null, "¿Desea eliminar al usuario seleccionado?") ;
					if(JOptionPane.OK_OPTION==confirm){
						model.removeRow(a); 
						udao.deleteUser(id);
					}
				}
			}
			
		}
			
	}
	
      
}
