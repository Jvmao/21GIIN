/*
 * 11 ene 2022
 * Jose V. Martí
 */
package vista;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.ConDB;
import controlador.PresentacionImplDAO;
import util.ConstantsDB;
import util.ConstantsGestPresentaciones;
import util.ConstantsMessage;

import javax.swing.JSeparator;
import javax.swing.JTable;

import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;


/**
 * The Class GestPresentaciones.
 */
public class GestPresentaciones extends JPanel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The content pane. */
	private JPanel contentPane;
	
	/** The lb pres. */
	private JLabel lbTitulo,lbTipoUsuario,lbImagePres,lbFechaPres,lbEstado,lbDocs,lbPres;
	
	/** The tx fecha pres. */
	private JTextField txTipoUsuario,txIDUsuario,txFechaPres;
	
	/** The separator one. */
	private JSeparator separatorOne;
	
	/** The check cerrado. */
	private JCheckBox checkAbierto,checkCerrado;
	
	/** The j combo tipo. */
	private JComboBox<String> jComboTipo;
	
	/** The btn del pres. */
	private JButton btnAddDoc,btnDelDoc,btnAltaPres,btnModPres,btnDelPres;
	
	/** The scroll pres. */
	private JScrollPane scrollDocs,scrollPres;

	/** The model pres. */
	//Variables Tabla
	private DefaultTableModel modelDocs,modelPres;
	
	/** The table pres. */
	private JTable tableDocs,tablePres;

	/** The image error. */
	//Imagen Error Mensaje
	private ImageIcon imageError = new ImageIcon(GestPresentaciones.class.getResource(ConstantsMessage.imgError));

	/** The pdao. */
	private PresentacionImplDAO pdao = new PresentacionImplDAO();
	
	/** The conn. */
	//Variables Conexión
	private Connection conn;

	/**
	 * Instantiates a new gest presentaciones.
	 */
	public GestPresentaciones() {
		setBounds(100, 100, 650, 450);
		contentPane=this;
		setLayout(null);
		
		//Label Título
		lbTitulo = new JLabel(ConstantsGestPresentaciones.labelTituloPresentaciones);
		lbTitulo.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
		lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitulo.setBounds(6, 6, 638, 16);
		add(lbTitulo);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		//Label Tipo Usuario
		lbTipoUsuario = new JLabel(ConstantsGestPresentaciones.labelTipoConectado);
		lbTipoUsuario.setBounds(16, 39, 167, 16);
		add(lbTipoUsuario);
		
		//JText ID Usuario Conectado
		txIDUsuario = new JTextField();
		txIDUsuario.setEditable(false);
		txIDUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		txIDUsuario.setBounds(195, 34, 115, 26);
		add(txIDUsuario);
		txIDUsuario.setColumns(10);
		
		//JText tipo de usuario conectado
		txTipoUsuario = new JTextField();
		txTipoUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		txTipoUsuario.setBounds(317, 34, 143, 26);
		txTipoUsuario.setEditable(false);
		add(txTipoUsuario);
		txTipoUsuario.setColumns(10);
		
		//Elemento separador
		separatorOne = new JSeparator();
		separatorOne.setForeground(Color.BLACK);
		separatorOne.setBounds(6, 67, 454, 12);
		add(separatorOne);
		
		//Imagen usuarios
		lbImagePres = new JLabel("");
		lbImagePres.setHorizontalAlignment(SwingConstants.CENTER);
		lbImagePres.setBounds(504, 6, 106, 88);
	    //Icono presentaciones
		lbImagePres.setIcon(new ImageIcon(GestPresentaciones.class.getResource(ConstantsGestPresentaciones.imgPresentaciones)));
		add(lbImagePres);
		
		//Label Fecha Presentación
		lbFechaPres = new JLabel(ConstantsGestPresentaciones.labelFechaPres);
		lbFechaPres.setBounds(16, 87, 167, 16);
		add(lbFechaPres);
		
		//Fecha Presentación Timestamp
		txFechaPres = new JTextField();
		txFechaPres.setHorizontalAlignment(SwingConstants.CENTER);
		txFechaPres.setBounds(195, 82, 265, 26);
		add(txFechaPres);
		txFechaPres.setColumns(10);
		toTimeStamp(); //Añadimos método para convertir la fecha actual a timestamp
		
		//Label Estado Presentacion
		lbEstado = new JLabel(ConstantsGestPresentaciones.labelEstado);
		lbEstado.setBounds(16, 123, 167, 16);
		add(lbEstado);
		
		//Check estado abierto
		checkAbierto = new JCheckBox(ConstantsGestPresentaciones.labelTipoEstado[0]);
		checkAbierto.setSelected(true);
		checkAbierto.setBounds(195, 119, 84, 23);
		add(checkAbierto);
		checkAbierto.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(checkAbierto.isSelected()==true) {
					checkCerrado.setSelected(false);
				}
			}
			
		});
		
		//Check estado abierto
		checkCerrado = new JCheckBox(ConstantsGestPresentaciones.labelTipoEstado[1]);
		checkCerrado.setBounds(376, 120, 128, 23);
		add(checkCerrado);
		checkCerrado.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if(checkCerrado.isSelected()==true) {
					checkAbierto.setSelected(false);
				}
				
			}
			
		});
		
		//Label Documentos
		lbDocs = new JLabel(ConstantsGestPresentaciones.labelDocs);
		lbDocs.setBounds(16, 158, 167, 16);
		add(lbDocs);
		
		//Tipo de Documentos
		jComboTipo = new JComboBox<String>();
		jComboTipo.setBounds(6, 186, 171, 27);
		contentPane.add(jComboTipo);
		jComboTipo.addItem(ConstantsGestPresentaciones.tipoDocs[0].toString());
		jComboTipo.addItem(ConstantsGestPresentaciones.tipoDocs[1].toString());
		jComboTipo.addItem(ConstantsGestPresentaciones.tipoDocs[2].toString());
		jComboTipo.addItem(ConstantsGestPresentaciones.tipoDocs[3].toString());
		jComboTipo.addItem(ConstantsGestPresentaciones.tipoDocs[4].toString());
		
		//Botón para añadir documentos desde el jComboTipo a listDocs
		btnAddDoc = new JButton(ConstantsGestPresentaciones.btnAddDocs);
		btnAddDoc.setBounds(6, 212, 68, 29);
		contentPane.add(btnAddDoc);
		btnAddDoc.addActionListener(new InnerActionPresentaciones());
		
		//Botón para eliminar documentos desde el jComboTipo a listDocs
		btnDelDoc = new JButton(ConstantsGestPresentaciones.btnDelDocs);
		btnDelDoc.setBounds(109, 212, 68, 29);
		contentPane.add(btnDelDoc);
		btnDelDoc.addActionListener(new InnerActionPresentaciones());
		
		//Scroll Tabla Documentos
		scrollDocs = new JScrollPane();
		scrollDocs.setBounds(195, 154, 265, 86);
		contentPane.add(scrollDocs);
		
		//Tabla para mostrar los documentos 
		modelDocs = new DefaultTableModel();
		tableDocs = new JTable(modelDocs);
		tableDocs.setForeground(Color.BLACK);
		tableDocs.setBackground(Color.LIGHT_GRAY);
		tableDocs.setAutoCreateRowSorter(true);
		tableDocs.setFont(new Font("Verdana", Font.PLAIN, 12));
		tableDocs.setShowVerticalLines(true);
		modelDocs.addColumn(ConstantsGestPresentaciones.columnaTablaDocs);
		scrollDocs.setViewportView(tableDocs);
		
		//Label presentaciones registradas
		lbPres = new JLabel(ConstantsGestPresentaciones.labelPres);
		lbPres.setHorizontalAlignment(SwingConstants.CENTER);
		lbPres.setBounds(16, 253, 615, 16);
		add(lbPres);
		
		//Scroll Tabla Presentaciones
		scrollPres = new JScrollPane();
		scrollPres.setBounds(16, 281, 615, 100);
		contentPane.add(scrollPres);
		
		//Tabla para mostrar las presentaciones 
		modelPres = new DefaultTableModel();
		tablePres = new JTable(modelPres);
		tablePres.setForeground(Color.BLACK);
		tablePres.setBackground(Color.LIGHT_GRAY);
		tablePres.setAutoCreateRowSorter(true);
		tablePres.setFont(new Font("Verdana", Font.PLAIN, 12));
		tablePres.setShowVerticalLines(true);
		modelPres.addColumn(ConstantsGestPresentaciones.tableColumnsPres[0]);
		modelPres.addColumn(ConstantsGestPresentaciones.tableColumnsPres[1]);
		modelPres.addColumn(ConstantsGestPresentaciones.tableColumnsPres[2]);
		modelPres.addColumn(ConstantsGestPresentaciones.tableColumnsPres[3]);
		modelPres.addColumn(ConstantsGestPresentaciones.tableColumnsPres[4]);
		modelPres.addColumn(ConstantsGestPresentaciones.tableColumnsPres[5]);
		scrollPres.setViewportView(tablePres);
		
		//Botón Alta Presentaciones
		btnAltaPres = new JButton(ConstantsGestPresentaciones.presBotonera[0]);
		btnAltaPres.setBounds(488, 130, 143, 29);
		add(btnAltaPres);
		btnAltaPres.addActionListener(new InnerActionPresentaciones());
		
		//Botón Modificar Presentaciones
		btnModPres = new JButton(ConstantsGestPresentaciones.presBotonera[1]);
		btnModPres.setBounds(488, 171, 143, 29);
		add(btnModPres);
		btnModPres.addActionListener(new InnerActionPresentaciones());
		
		//Botón Eliminar Presentaciones
		btnDelPres = new JButton(ConstantsGestPresentaciones.presBotonera[2]);
		btnDelPres.setBounds(487, 212, 143, 29);
		add(btnDelPres);
		btnDelPres.addActionListener(new InnerActionPresentaciones());
		
		try {
			populatePresentacionData(); //Mostramos los valores de la BBDD en la tabla tablePres
		} catch (ParseException e1) {
			e1.printStackTrace();
		} 

	}
	
	/**
	 * Populate presentacion data.
	 *
	 * @throws ParseException the parse exception
	 */
	//mostramos los datos de la BBDD de la tabla presentaciones en la tabla
	private void populatePresentacionData() throws ParseException {
		try {
			//Conexión a la BBDD
			//conn = DriverManager.getConnection(ConstantsDB.server,ConstantsDB.user,ConstantsDB.pass);
			conn = ConDB.getConnection(ConstantsDB.server,ConstantsDB.user,ConstantsDB.pass);
			
			//Consulta a BBDD
		    Statement st = conn.createStatement();
		    ResultSet rs = st.executeQuery(ConstantsDB.queryListaPresentaciones);
		    

		    //Obtenemos todos los valores de la BBDD y los vamos añadiendo uno a uno en la tabla
		    while (rs.next()) {
		    	String idPres = rs.getString(ConstantsDB.valueIDPres);
		    	String idUser = rs.getString(ConstantsDB.valueIDUser);
		    	String userType = rs.getString(ConstantsDB.valueTypeUser);
		    	String fecha = rs.getString(ConstantsDB.valueFecha);
		    	int estado = rs.getInt(ConstantsDB.valueEstadoPres);
		    	String docs = rs.getString(ConstantsDB.valueDocsRequeridos);
		    	
		    	//Modificamos el formato de fecha de la BBDD para tener el mismo que en la pantalla
		    	SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    	Date dateValue = input.parse(fecha);
		    	SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		    	//Añadimos los valores en cada fila de la tabla dinámicamente
		        modelPres.addRow(new Object[]{idPres,idUser,userType,output.format(dateValue),estado,docs}); 
		        
		   }    
		    
		    
		      
		} catch (SQLException e) {
			e.printStackTrace();
		}
	      
	}
	
	
	/**
	 * Sets the valores usuario.
	 *
	 * @param id     the id
	 * @param nombre the nombre
	 */
	//Recogemos el valor de ID y Tipo de Usuario desde la clase de MenuPrincipal
	public void setValoresUsuario (String id,String nombre){
			txIDUsuario.setText(id);
			txTipoUsuario.setText(nombre);
	}
	
	/**
	 * To time stamp.
	 */
	//Método para convertir la fecha del sistema a timestamp
	private void toTimeStamp() {
		// obtenemos la fecha del sistema
        Date date = new Date();
        //definimos nuevo objeto Timestamp
        Timestamp ts = new Timestamp(date.getTime());
       //definimos la máscara de tiempo hasta horas y minutos
       SimpleDateFormat form = new SimpleDateFormat("dd/MM/yyyy HH:mm");
       //Pasamos el valor timestamp al campo txFechaPres
       txFechaPres.setText(form.format(ts).toString());
	}
	
	
	
	/**
	 * The Class InnerActionPresentaciones.
	 */
	public class InnerActionPresentaciones implements ActionListener{

		/**
		 * Action performed.
		 *
		 * @param e the e
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==btnAddDoc) {
				String tipoDoc = jComboTipo.getSelectedItem().toString();
				modelDocs.addRow(new Object[]{tipoDoc}); //Añadimos los valores seleccionados en la tabla
				
			}
			
			if(e.getSource()==btnDelDoc) {
				if(tableDocs.getSelectionModel().isSelectionEmpty()==true ) {
					//Si no selecciona una fila de la tabla y presiona el botón btnBaja se muestra el siguiente mensaje
					JOptionPane.showMessageDialog(null,ConstantsMessage.msg15,ConstantsMessage.msg0,
							  JOptionPane.PLAIN_MESSAGE,imageError);
				}else {
					//Eliminamos Fila seleccionada el la tabla de documentos
					int a = tableDocs.getSelectedRow();
					modelDocs.removeRow(a);
				}

			}
			
			if(e.getSource()==btnAltaPres) {
				String idUser = txIDUsuario.getText().toString();
				String typeUser = txTipoUsuario.getText().toString();
				String timeStamp = txFechaPres.getText().toString();
				
				//Valores tabla documentos
				ArrayList<String> docsValues = new ArrayList<String>();
				for (int i = 0; i < modelDocs.getRowCount(); i++){
				     docsValues.add(modelDocs.getValueAt(i, 0).toString());
				}
				
				if(docsValues.isEmpty()) {
					//Sí alguno de los campos anteriores está vacío mostramos el siguiente mensaje
					JOptionPane.showMessageDialog(null,ConstantsMessage.msg19,ConstantsMessage.msg0,
												  JOptionPane.PLAIN_MESSAGE,imageError);
				}else {
			        pdao.addPresentacion(idUser, typeUser, timeStamp, tipoEstadoDB(), docsValues); //Añadimos presentación en la BBDD
					
			        //Añadimos los valores en cada fila de la tabla dinámicamente
					int lastRow = tablePres.getRowCount()-1;
					String value = (String) tablePres.getValueAt(lastRow, 0);
					int valor = Integer.valueOf(value);
					int newId = valor+1;
					
					modelPres.addRow(new Object[]{String.valueOf(newId),idUser,typeUser,timeStamp,tipoEstadoTabla(),docsValues});
					
					restart(); //Reiniciamos los campos
				}
	
			}
			
			
			if(e.getSource()==btnModPres) {
				if(tablePres.getSelectionModel().isSelectionEmpty()==true) {
					//Si no selecciona una fila de la tabla y presiona el botón btnModPres se muestra el siguiente mensaje
					JOptionPane.showMessageDialog(null,ConstantsMessage.msg20,ConstantsMessage.msg0,
							  JOptionPane.PLAIN_MESSAGE,imageError);
				}else {
					
					String idPres = tablePres.getValueAt(tablePres.getSelectedRow(), 0).toString();
					String id = tablePres.getValueAt(tablePres.getSelectedRow(), 1).toString();
					String tipo = tablePres.getValueAt(tablePres.getSelectedRow(), 2).toString();
					String fecha = tablePres.getValueAt(tablePres.getSelectedRow(), 3).toString();
					//boolean estado = Boolean.parseBoolean(tablePres.getValueAt(tablePres.getSelectedRow(), 4).toString()); ;
					
					//Valores tabla documentos
					ArrayList<String> docsValues = new ArrayList<String>();
					for (int i = 0; i < modelPres.getRowCount(); i++){
					     docsValues.add(modelPres.getValueAt(i, 5).toString());
					}
					
					//El usuario debe de confirmar la eliminación del usuario seleccionado
					int confirm=JOptionPane.showConfirmDialog(null, "¿Desea actualizar la convocatoria seleccionada?") ;
					if(JOptionPane.OK_OPTION==confirm){
						tablePres.setModel(modelPres);
						pdao.modPresentacion(idPres,id, tipo,fecha,tipoEstadoDB(),docsValues);
					}
					
					restart(); //Reiniciamos valores de la pantalla
				
				}
			}
			
			
			if(e.getSource()==btnDelPres) {
				if(tablePres.getSelectionModel().isSelectionEmpty()==true ) {
					//Si no selecciona una fila de la tabla y presiona el botón btnBaja se muestra el siguiente mensaje
					JOptionPane.showMessageDialog(null,ConstantsMessage.msg20,ConstantsMessage.msg0,JOptionPane.ERROR_MESSAGE);
				}else {
					//Elimina al usuario de la tabla y de la BBDD
					DefaultTableModel model = (DefaultTableModel) tablePres.getModel() ;
					int col = 0;
					int rows = tablePres.getSelectedRow();
					String id = tablePres.getModel().getValueAt(rows, col).toString();
					
					int a = tablePres.getSelectedRow();
					
					//El usuario debe de confirmar la eliminación del dato seleccionado
					int confirm=JOptionPane.showConfirmDialog(null,ConstantsMessage.msg21) ;
					if(JOptionPane.OK_OPTION==confirm){
						model.removeRow(a);  		//Remueve la fila selecionada de la tabla
						pdao.delPresentacion(id);	//Elimina la fila seleccionada de la BBDD
					}
				}
			}
			
			
		}
	}
	
	/**
	 * Tipo estado DB.
	 *
	 * @return true, if successful
	 */
	//Cambiamos el tipo de valor booleano en función del tipo elemento seleccionado en el comboBox jComboTipo
	private boolean tipoEstadoDB() {
		if(checkAbierto.isSelected()==false) {
			return false;
		}
		return true;
	}
	
	/**
	 * Tipo estado tabla.
	 *
	 * @return the int
	 */
	//Cambiamos el tipo de estado a numérico para añadirlo a la tabla de GestConvocatorias
	public int tipoEstadoTabla() {
			if(checkAbierto.isSelected()==false) {
				return 0;
			}
			return 1;
	}
	
	/**
	 * Restart.
	 */
	//Método para limpiar campos una vez dada de alta una nueva convocatoria
	public void restart() {
		//Reiniciamos componentes
		toTimeStamp();
		checkAbierto.setSelected(true);
		checkCerrado.setSelected(false);
		
		
		//Limpiamos la tabla de todas las filas que puedan haber
		int rowsToRemove = modelDocs.getRowCount();
        for (int i = rowsToRemove - 1; i >= 0; i--) {
        	modelDocs.removeRow(i);
        }
	}
	
	/**
	 * Control gest presentaciones.
	 *
	 * @param tipoUsuario the tipo usuario
	 */
	//Método para controlar las acciones que pueden llevar a cabo los diferentes usuarios en GestConvocatorias
	public void controlGestPresentaciones(String tipoUsuario) {
		tipoUsuario = txTipoUsuario.getText().toString();
		
		if(tipoUsuario.equals(ConstantsGestPresentaciones.tiposUsuariosGestPres[1])) {
			//Deshabilitamnos los siguientes componentes para el tipo de usuario Cuentadante
			btnDelPres.setEnabled(false);
			
			//El usuario Cuentadante puede modificar el estado de abierto a cerrado pero no a la inversa
			if(checkCerrado.isSelected()==true) {
				//checkAbierto.setSelected(false);
				checkAbierto.setEnabled(false);
			}
		}else if(tipoUsuario.equals(ConstantsGestPresentaciones.tiposUsuariosGestPres[2])) {
			
			//Deshabilitamnos los siguientes componentes para el tipo de usuario Fiscal 
			btnAltaPres.setEnabled(false);
			btnModPres.setEnabled(false);
			btnDelPres.setEnabled(false);
			tablePres.setCellSelectionEnabled(false);
			
			
		}else if(tipoUsuario.equals(ConstantsGestPresentaciones.tiposUsuariosGestPres[3])) {
			//Deshabilitamnos los siguientes componentes para el tipo de usuario Fiscal 
			btnAltaPres.setEnabled(false);
			btnDelPres.setEnabled(false);
		}
	}

	
}
