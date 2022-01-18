/*
 * 18 ene 2022
 * Jose V. Martí
 */
package vista;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.ConDB;
import controlador.MunicipiosImplDAO;
import util.ConstantsDB;
import util.ConstantsGestMunicipios;
import util.ConstantsMessage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// TODO: Auto-generated Javadoc
/**
 * The Class GestMunicipios.
 */

/**
 * The Class GestMunicipios.
 */

public class GestMunicipios extends JPanel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The content pane. */
	private JPanel contentPane;
	
	/** The lb image users. */
	private JLabel lbTitulo,lbTipoUsuario,lbImageUsers;
	
	/** The tx id usuario. */
	private JTextField txTipoUsuario,txIdUsuario;
	
	/** The j table municipios. */
	private static JTable jTableMunicipios;
	
	/** The scroll pane. */
	private JScrollPane scrollPane;
	
	/** The btn baja. */
	private JButton btnAlta,btnMod,btnBaja;	

	/** The model. */
	//Variable Tabla
	private DefaultTableModel model;	

	/** The mdao. */
	//Llamada clase UsuariosImplDAO
	private MunicipiosImplDAO mdao = new MunicipiosImplDAO();
	
	/** The alt mun. */
	//Llamada clase AltaMunicipios
	private AltaMunicipios altMun = new AltaMunicipios();
	
	/** The mod mun. */
	//Llamada clase ModMunicipios
	private ModMunicipios modMun = new ModMunicipios();
		

	/** The conn. */
	//Variables Conexión
	private Connection conn;

	/**
	 * Instantiates a new gest municipios.
	 */
	public GestMunicipios() {
		setBounds(100, 100, 650, 450);
		contentPane=this;
		setLayout(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		//Label Título
		lbTitulo = new JLabel(ConstantsGestMunicipios.labelTitulo);
		lbTitulo.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
		lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitulo.setBounds(6, 6, 638, 16);
		add(lbTitulo);
		
		//Label Tipo Usuario
		lbTipoUsuario = new JLabel(ConstantsGestMunicipios.labelTipoConectado);
		lbTipoUsuario.setBounds(6, 63, 187, 16);
		add(lbTipoUsuario);
		
		//JText ID Usuario
		txIdUsuario = new JTextField();
		txIdUsuario.setEditable(false);
		txIdUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		txIdUsuario.setBounds(192, 58, 130, 26);
		add(txIdUsuario);
		txIdUsuario.setColumns(10);
		
		//JText para rellenar el tipo de usuario
		txTipoUsuario = new JTextField();
		txTipoUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		txTipoUsuario.setBounds(334, 58, 136, 26);
		txTipoUsuario.setEditable(false);
		add(txTipoUsuario);
		txTipoUsuario.setColumns(10);
		
		//Imagen usuarios
		lbImageUsers = new JLabel("");
	    lbImageUsers.setHorizontalAlignment(SwingConstants.CENTER);
	    lbImageUsers.setBounds(485, 7, 136, 109);
	    //Icono usuarios
		lbImageUsers.setIcon(new ImageIcon(GestUsuarios.class.getResource(ConstantsGestMunicipios.imgCity)));
		add(lbImageUsers);

		
		//ScrollPane para añadir la tabla de usuarios
		scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 91, 464, 262);
		add(scrollPane);
		
		//Botón Alta Usuarios
		btnAlta = new JButton(ConstantsGestMunicipios.tituloBotonera[0].toString());
		btnAlta.setBounds(482, 128, 139, 29);
		add(btnAlta);
		btnAlta.addActionListener(new InnerActionGestMunicipios());
		
		//Botón Modificación Usuarios
		btnMod = new JButton(ConstantsGestMunicipios.tituloBotonera[1].toString());
		btnMod.setBounds(482, 228, 139, 29);
		add(btnMod);
		btnMod.addActionListener(new InnerActionGestMunicipios());
		
		//Botón Baja Usuarios
		btnBaja = new JButton(ConstantsGestMunicipios.tituloBotonera[2].toString());
		btnBaja.setBounds(482, 328, 139, 29);
		add(btnBaja);
		btnBaja.addActionListener(new InnerActionGestMunicipios());
		
		//Tabla
		model = new DefaultTableModel();
		jTableMunicipios = new JTable(model);
	    jTableMunicipios.setForeground(Color.BLACK);
	    jTableMunicipios.setBackground(Color.LIGHT_GRAY);
	    jTableMunicipios.setAutoCreateRowSorter(true);
	    jTableMunicipios.setFont(new Font("Verdana", Font.PLAIN, 12));
	    jTableMunicipios.setShowVerticalLines(true);
	    scrollPane.setViewportView(jTableMunicipios);
	    model.addColumn(ConstantsGestMunicipios.columns[0]); //ID Municipio
	    model.addColumn(ConstantsGestMunicipios.columns[1]); //Categoria Municipio
	    model.addColumn(ConstantsGestMunicipios.columns[2]); //ID Usuario
	    model.addColumn(ConstantsGestMunicipios.columns[3]); //Tipo Usuario
	    
	    populateMunicipiosData();

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
	 * Populate municipios data.
	 */
	//Definimos método para mostrar los datos almacenados en la BBDD respecto de la tabla usuarios
	private void populateMunicipiosData() {
		try {
			//Conexión a la BBDD
			//conn = DriverManager.getConnection(ConstantsDB.server,ConstantsDB.user,ConstantsDB.pass);
			conn = ConDB.getConnection(ConstantsDB.server,ConstantsDB.user,ConstantsDB.pass);
			
			//Consulta a BBDD
		    Statement st = conn.createStatement();
		    ResultSet rs = st.executeQuery(ConstantsDB.queryListaMunicipios);
		    

		    //Obtenemos todos los valores de la BBDD y los vamos añadiendo uno a uno en la tabla
		    while (rs.next()) {
		    	String id = rs.getString(ConstantsDB.valueIDMunicipio);
		    	int categoria = rs.getInt(ConstantsDB.valueCatMunicipio);
		    	String idUser = rs.getString(ConstantsDB.valueIDUser);
		    	String tipo = rs.getString(ConstantsDB.valueTipoUsuarioMunicipio);

		    	//Añadimos los valores en cada fila de la tabla dinámicamente
		        model.addRow(new Object[]{id,categoria,idUser,tipo}); 
		   }
			      
		      
		} catch (SQLException e) {
			e.printStackTrace();
		}
	      
	}
	
	
	/**
	 * The Class InnerActionGestMunicipios.
	 */
	//Definimos los actionListener de los componentes desde la inner class
	public class InnerActionGestMunicipios implements ActionListener{

		/**
		 * Action performed.
		 *
		 * @param e the e
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==btnAlta) {
				altMun.setVisible(true);	
			}
			
			if(e.getSource()==btnMod) {
				//Comprobamos si se ha seleccionado alguna fila cuando se elimina un usuario.
				if(jTableMunicipios.getSelectionModel().isSelectionEmpty()==true ) {
					//Si no selecciona una fila de la tabla y presiona el botón btnBaja se muestra el siguiente mensaje
					JOptionPane.showMessageDialog(null,ConstantsMessage.msg14,ConstantsMessage.msg0,JOptionPane.ERROR_MESSAGE);
				}else {
					modMun.setVisible(true);
					
					//Recogemos los valores de la fila seleccionada
					String id = jTableMunicipios.getValueAt(jTableMunicipios.getSelectedRow(), 0).toString();
					int categoria = (int) jTableMunicipios.getValueAt(jTableMunicipios.getSelectedRow(), 1);
					String idUser = jTableMunicipios.getValueAt(jTableMunicipios.getSelectedRow(), 2).toString();
					String tipo = jTableMunicipios.getValueAt(jTableMunicipios.getSelectedRow(), 3).toString();
					
					//Pasamos los valores seleccionados al JDialog ModUsuarios
					ModMunicipios.getRowMunicipios(id, categoria,idUser, tipo);
				}
			}
			
			if(e.getSource() == btnBaja) {
				if(jTableMunicipios.getSelectionModel().isSelectionEmpty()==true ) {
					//Si no selecciona una fila de la tabla y presiona el botón btnBaja se muestra el siguiente mensaje
					JOptionPane.showMessageDialog(null,ConstantsMessage.msg14,ConstantsMessage.msg0,JOptionPane.ERROR_MESSAGE);
				}else {
					//Elimina al usuario de la tabla y de la BBDD
					DefaultTableModel model = (DefaultTableModel) jTableMunicipios.getModel() ;
					int col = 0;
					int rows = jTableMunicipios.getSelectedRow();
					String id = jTableMunicipios.getModel().getValueAt(rows, col).toString();
					
					int a = jTableMunicipios.getSelectedRow();
					
					//El usuario debe de confirmar la eliminación del dato seleccionado
					int confirm=JOptionPane.showConfirmDialog(null,ConstantsMessage.msg4) ;
					if(JOptionPane.OK_OPTION==confirm){
						model.removeRow(a); 
						mdao.deleteMunicipio(id);
					}
				}
			}
			
		}
			
	}
	
	/**
	 * Adds the row municipios.
	 *
	 * @param dataRow the data row
	 */
	public static void addRowMunicipios (Object[] dataRow) {
		DefaultTableModel model = (DefaultTableModel) jTableMunicipios.getModel();
		model.addRow(dataRow); //Añadimos fila en la tabla de un nuevo Municipio creado en AltaMunicipios
		
	} 
	
	/**
	 * Sets the row municipios.
	 *
	 * @param id        the id
	 * @param categoria the categoria
	 * @param idUser    the id user
	 * @param tipo      the tipo
	 */
	public static void setRowMunicipios(String id,int categoria,String idUser,String tipo) {
		DefaultTableModel model = (DefaultTableModel) jTableMunicipios.getModel();
		int i = jTableMunicipios.getSelectedRow();
		model.setValueAt(id, i, 0);   			//Actualizamos valor id
		model.setValueAt(categoria, i, 1); 		//Actualizamos valor categoria
		model.setValueAt(idUser, i, 2); 		//Actualizamos valor idUsuario cuentadante
		model.setValueAt(tipo, i, 3); 			//Actualizamos valor tipo cuentadante
	}
	
	/**
	 * Control gest municipios.
	 *
	 * @param tipoUsuario the tipo usuario
	 */
	//Método para controlar las acciones que pueden llevar a cabo los diferentes usuarios en GestMunicipios
	public void controlGestMunicipios(String tipoUsuario) {
		tipoUsuario = txTipoUsuario.getText().toString();
		
		//Cuando el tipo de usuario es distinto a Administrador deshabilitamos los siguientes componentes
		if(tipoUsuario.equals(ConstantsGestMunicipios.tiposUsuariosMun[1])) {
			//Deshabilitamnos los siguientes componentes para el tipo de usuario Cuentadante
			btnAlta.setEnabled(false);
			btnMod.setEnabled(false);
			btnBaja.setEnabled(false);
			
			//Escondemos las columnas señaladas, siendo solo posible visualizar la columna de tipo de municipio
			jTableMunicipios.removeColumn(jTableMunicipios.getColumnModel().getColumn(0));
			jTableMunicipios.removeColumn(jTableMunicipios.getColumnModel().getColumn(2));
			//jTableMunicipios.removeColumn(jTableMunicipios.getColumnModel().getColumn(3));
			
		}else if(tipoUsuario.equals(ConstantsGestMunicipios.tiposUsuariosMun[2]) || 
				tipoUsuario.equals(ConstantsGestMunicipios.tiposUsuariosMun[3])) {
			//Deshabilitamnos los siguientes componentes para el tipo de usuario Fiscal y Fiscal General
			btnAlta.setEnabled(false);
			btnMod.setEnabled(false);
			btnBaja.setEnabled(false);
			jTableMunicipios.setCellSelectionEnabled(false);
			
		}
	}
	
	

}
