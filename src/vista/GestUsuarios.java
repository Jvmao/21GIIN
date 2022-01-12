/*
 * 11 ene 2022
 * Jose V. Martí
 */
package vista;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.ConDB;
import controlador.UsuariosImplDAO;
import util.ConstantsDB;
import util.ConstantsGestUsuarios;
import util.ConstantsMessage;

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
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Color;


// TODO: Auto-generated Javadoc
/**
 * The Class GestUsuarios.
 */

/**
 * The Class GestUsuarios.
 */

public class GestUsuarios extends JPanel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The content pane. */
	private JPanel contentPane;
	
	/** The lb image users. */
	private JLabel lbTitulo,lbTipoUsuario,lbImageUsers;
	
	/** The tx id usuario. */
	private JTextField txTipoUsuario,txIdUsuario;
	
	/** The j table usuarios. */
	private static JTable jTableUsuarios;
	
	/** The scroll pane. */
	private JScrollPane scrollPane;
	
	/** The btn baja. */
	private JButton btnAlta,btnMod,btnBaja;

	/** The model. */
	//Variable Tabla
	private DefaultTableModel model;

	/** The udao. */
	//Llamada clase UsuariosImplDAO
	private UsuariosImplDAO udao = new UsuariosImplDAO();

	/** The conn. */
	//Variables Conexión
	private Connection conn;

	/** The au. */
	//Variables Frames
	private AltaUsuarios au = new AltaUsuarios();
	
	/** The mou. */
	private ModUsuarios mou = new ModUsuarios();

	/** The image error. */
	//Imagen Error Mensaje
	private ImageIcon imageError = new ImageIcon(GestPresentaciones.class.getResource(ConstantsMessage.imgError));

	/**
	 * Instantiates a new gest usuarios.
	 */
	public GestUsuarios() {
		initComponents();
	}
	
	/**
	 * Inits the components.
	 */
	public void initComponents() {
		
		setBounds(100, 100, 650, 450);
		contentPane=this;
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setLayout(null);
		
		//Label Título Pantalla
		lbTitulo = new JLabel(ConstantsGestUsuarios.labelTitulo);
		lbTitulo.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
		lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitulo.setBounds(6, 6, 638, 16);
		add(lbTitulo);
		
		//Label Tipo Usuario
		lbTipoUsuario = new JLabel(ConstantsGestUsuarios.labelTipoConectado);
		lbTipoUsuario.setBounds(6, 63, 180, 16);
		add(lbTipoUsuario);
		
		//JText Id Usuario
		txIdUsuario = new JTextField();
		txIdUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		txIdUsuario.setEditable(false);
		txIdUsuario.setBounds(198, 58, 130, 26);
		add(txIdUsuario);
		txIdUsuario.setColumns(10);
		
		//JText para rellenar el tipo de usuario
		txTipoUsuario = new JTextField();
		txTipoUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		txTipoUsuario.setBounds(340, 58, 130, 26);
		txTipoUsuario.setEditable(false);
		add(txTipoUsuario);
		txTipoUsuario.setColumns(10);
		
		//Imagen usuarios
		lbImageUsers = new JLabel("");
	    lbImageUsers.setHorizontalAlignment(SwingConstants.CENTER);
	    lbImageUsers.setBounds(485, 7, 136, 109);
	    //Icono usuarios
		lbImageUsers.setIcon(new ImageIcon(GestUsuarios.class.getResource(ConstantsGestUsuarios.imgUsers)));
		add(lbImageUsers);

		
		//ScrollPane para añadir la tabla de usuarios
		scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 91, 464, 262);
		add(scrollPane);
		
		//Botón Alta Usuarios
		btnAlta = new JButton(ConstantsGestUsuarios.tituloBotonera[0].toString());
		btnAlta.setBounds(482, 128, 139, 29);
		add(btnAlta);
		btnAlta.addActionListener(new InnerActionGestUsuarios());
		
		//Botón Modificación Usuarios
		btnMod = new JButton(ConstantsGestUsuarios.tituloBotonera[1].toString());
		btnMod.setBounds(482, 228, 139, 29);
		add(btnMod);
		btnMod.addActionListener(new InnerActionGestUsuarios());
		
		//Botón Baja Usuarios
		btnBaja = new JButton(ConstantsGestUsuarios.tituloBotonera[2].toString());
		btnBaja.setBounds(482, 328, 139, 29);
		add(btnBaja);
		btnBaja.addActionListener(new InnerActionGestUsuarios());
		
		//Tabla
		model = new DefaultTableModel();
		jTableUsuarios = new JTable(model);
	    jTableUsuarios.setForeground(Color.BLACK);
	    jTableUsuarios.setBackground(Color.LIGHT_GRAY);
	    jTableUsuarios.setAutoCreateRowSorter(true);
	    jTableUsuarios.setFont(new Font("Verdana", Font.PLAIN, 12));
	    jTableUsuarios.setShowVerticalLines(true);
	    scrollPane.setViewportView(jTableUsuarios);
	    model.addColumn(ConstantsGestUsuarios.columns[0]); //ID
	    model.addColumn(ConstantsGestUsuarios.columns[1]); //Tipo Usuario
	    model.addColumn(ConstantsGestUsuarios.columns[2]); //Password
				
		//Llamamos al método para mostrar los valores en la tabla
		populateUserData();
	}
	
	
	//Definimos método para mostrar los datos almacenados en la BBDD
	/**
	 * Populate user data.
	 */
	//respecto de la tabla usuarios
	private void populateUserData() {
		try {
			//Conexión a la BBDD
			//conn = DriverManager.getConnection(ConstantsDB.server,ConstantsDB.user,ConstantsDB.pass);
			conn = ConDB.getConnection(ConstantsDB.server,ConstantsDB.user,ConstantsDB.pass);
			
			//Consulta a BBDD
		    Statement st = conn.createStatement();
		    ResultSet rs = st.executeQuery(ConstantsDB.queryListaUsuarios);
		    

		    //Obtenemos todos los valores de la BBDD y los vamos añadiendo uno a uno en la tabla
		    while (rs.next()) {
		    	String id = rs.getString(ConstantsDB.valueID);
		    	String tipo = rs.getString(ConstantsDB.valueTipo);
		    	String passw = rs.getString(ConstantsDB.valuePass);

		    	//Añadimos los valores en cada fila de la tabla dinámicamente
		        model.addRow(new Object[]{id, tipo, passw}); 
		   }
			      
		      
		} catch (SQLException e) {
			e.printStackTrace();
		}
	      
	}
	
	
	/**
	 * Sets the valor tipo usuario.
	 *
	 * @param id     the id
	 * @param nombre the nombre
	 */
	//Recogemos el valor de Tipo de Usuario desde la clase de MenuPrincipal
	public void setValorTipoUsuario (String id,String nombre){
		 txIdUsuario.setText(id);
		 txTipoUsuario.setText(nombre);
	}
	
	
	/**
	 * The Class InnerAction.
	 */
	//Definimos los actionListener de los componentes desde la inner class
	public class InnerActionGestUsuarios implements ActionListener{

		/**
		 * Action performed.
		 *
		 * @param e the e
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==btnAlta) {
				au.setVisible(true);
				
			}
			
			if(e.getSource()==btnMod) {
				//Comprobamos si se ha seleccionado alguna fila cuando se elimina un usuario.
				if(jTableUsuarios.getSelectionModel().isSelectionEmpty()==true ) {
					//Si no selecciona una fila de la tabla y presiona el botón btnBaja se muestra el siguiente mensaje
					JOptionPane.showMessageDialog(null,ConstantsMessage.msg2,ConstantsMessage.msg0,JOptionPane.ERROR_MESSAGE);
				}else {
					mou.setVisible(true);
					
					//Recogemos los valores de la fila seleccionada
					String id = jTableUsuarios.getValueAt(jTableUsuarios.getSelectedRow(), 0).toString();
					String tipo = jTableUsuarios.getValueAt(jTableUsuarios.getSelectedRow(), 1).toString();
					String pass = jTableUsuarios.getValueAt(jTableUsuarios.getSelectedRow(), 2).toString();
					
					//Pasamos los valores seleccionados al JDialog ModUsuarios
					ModUsuarios.getRowUsuarios(id, tipo, pass);
				}
			}
			
			if(e.getSource() == btnBaja) {
				//Comprobamos si se ha seleccionado alguna fila cuando se elimina un usuario.
				if(jTableUsuarios.getSelectionModel().isSelectionEmpty()==true ) {
					//Si no selecciona una fila de la tabla y presiona el botón btnBaja se muestra el siguiente mensaje
					JOptionPane.showMessageDialog(null,ConstantsMessage.msg2,ConstantsMessage.msg0,JOptionPane.ERROR_MESSAGE);
				}else {
					//Elimina al usuario de la tabla y de la BBDD
					DefaultTableModel model = (DefaultTableModel) jTableUsuarios.getModel() ;
					int col = 0;
					int rows = jTableUsuarios.getSelectedRow();
					String id = jTableUsuarios.getModel().getValueAt(rows, col).toString();
					
					int a = jTableUsuarios.getSelectedRow();
					//El usuario debe de confirmar la eliminación del dato seleccionado
					int confirm=JOptionPane.showConfirmDialog(null,ConstantsMessage.msg4) ;
					if(JOptionPane.OK_OPTION==confirm){
						model.removeRow(a); 
						udao.deleteUser(id);
					}
				}
			}
			
		}
			
	}
	
	/**
	 * Adds the row usuarios.
	 *
	 * @param dataRow the data row
	 */
	//Método para añadir una nueva fila en JTable, llamado desde la clase AltaUsuarios
	public static void addRowUsuarios (Object[] dataRow) {
		DefaultTableModel model = (DefaultTableModel) jTableUsuarios.getModel();
		model.addRow(dataRow); //Añadimos fila en la tabla de un nuevo usuario creado en AltaUsuarios
		
	} 
	
	/**
	 * Sets the row usuarios.
	 *
	 * @param id   the id
	 * @param tipo the tipo
	 * @param pass the pass
	 */
	//Método para actualizar fila de JTable desde la clase ModUsuarios
	public static void setRowUsuarios(String id,String tipo,String pass) {
		DefaultTableModel model = (DefaultTableModel) jTableUsuarios.getModel();
		int i = jTableUsuarios.getSelectedRow();
		model.setValueAt(id, i, 0);   //Actualizamos valor id
		model.setValueAt(tipo, i, 1); //Actualizamos valor tipo
		model.setValueAt(pass, i, 2); //Actualizamos valor password
	}
	
	
	/**
	 * Control gest usuarios.
	 *
	 * @param tipoUsuario the tipo usuario
	 */
	//Método para controlar las acciones que pueden llevar a cabo los diferentes usuarios en GestUsuarios
	public void controlGestUsuarios(String tipoUsuario) {
		tipoUsuario = txTipoUsuario.getText();
		//Cuando el tipo de usuario es distinto a Administrador deshabilitamos los siguientes componentes
		if(!tipoUsuario.equals("Administrador")) {
			btnAlta.setEnabled(false);			//deshabilita botón Alta
			btnMod.setEnabled(false); 			//deshabilita botón Modificar
			btnBaja.setEnabled(false);			//deshabilita botón Baja
			jTableUsuarios.setVisible(false);	//deshabilita tabla valores usuarios
			
			//Si el usuario es distinto a Administrador mostraremos el siguiente mensaje
			JOptionPane.showMessageDialog(null,ConstantsMessage.msg24,ConstantsMessage.msg0,
					  JOptionPane.PLAIN_MESSAGE,imageError);
		}
		
	}
}
