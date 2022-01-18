/*
 * 18 ene 2022
 * Jose V. Martí
 */
package vista;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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


// TODO: Auto-generated Javadoc
/**
 * The Class GestPresentaciones.
 */
public class GestPresentaciones extends JPanel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The content pane. */
	private JPanel contentPane;
	
	/** The lb pres. */
	private JLabel lbTitulo,lbTipoUsuario,lbImagePres,lbFechaPres,lbPres;
	
	/** The tx fecha pres. */
	private JTextField txTipoUsuario,txIDUsuario,txFechaPres;
	
	/** The separator one. */
	private JSeparator separatorOne;
	
	/** The btn del pres. */
	private JButton btnAltaPres,btnModPres,btnDelPres;
	
	/** The scroll pres. */
	private JScrollPane scrollPres;

	/** The model pres. */
	//Variables Tabla
	private DefaultTableModel modelDocs,modelPres;

	/** The table pres. */
	private static JTable tablePres;
	
	/** The conn. */
	//Variables Conexión
	private Connection conn;

	/** The image error. */
	//Imagen Error Mensaje
	private ImageIcon imageError = new ImageIcon(GestPresentaciones.class.getResource(ConstantsMessage.imgError));

	/** The pdao. */
	private PresentacionImplDAO pdao = new PresentacionImplDAO();
	
	/** The ap. */
	private AltaPresentaciones ap = new AltaPresentaciones();
	
	/** The mp. */
	private ModPresentaciones mp = new ModPresentaciones();
	
	/**
	 * Instantiates a new gest presentaciones.
	 */
	public GestPresentaciones() {
		setBounds(100, 100, 650, 450);
		contentPane=this;
		setLayout(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		//Label Título
		lbTitulo = new JLabel(ConstantsGestPresentaciones.labelTituloPresentaciones);
		lbTitulo.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
		lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitulo.setBounds(6, 6, 638, 16);
		add(lbTitulo);
		
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
		lbImagePres.setBounds(483, 6, 127, 114);
	    //Icono presentaciones
		lbImagePres.setIcon(new ImageIcon(GestPresentaciones.class.getResource(ConstantsGestPresentaciones.imgPresentaciones)));
		add(lbImagePres);
		
		//Label Fecha Presentación
		lbFechaPres = new JLabel(ConstantsGestPresentaciones.labelFechaGestPres);
		lbFechaPres.setBounds(127, 87, 56, 16);
		add(lbFechaPres);
		
		//Fecha Presentación Timestamp
		txFechaPres = new JTextField();
		txFechaPres.setEditable(false);
		txFechaPres.setHorizontalAlignment(SwingConstants.CENTER);
		txFechaPres.setBounds(195, 82, 265, 26);
		add(txFechaPres);
		txFechaPres.setColumns(10);
		toTimeStamp();
		
		//Tabla para mostrar los documentos 
		modelDocs = new DefaultTableModel();
		modelDocs.addColumn(ConstantsGestPresentaciones.columnaTablaDocs);
		
		//Label presentaciones registradas
		lbPres = new JLabel(ConstantsGestPresentaciones.labelPres);
		lbPres.setHorizontalAlignment(SwingConstants.CENTER);
		lbPres.setBounds(16, 132, 573, 16);
		add(lbPres);
		
		//Scroll Tabla Presentaciones
		scrollPres = new JScrollPane();
		scrollPres.setBounds(16, 160, 568, 180);
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
		btnAltaPres.setBounds(16, 352, 143, 29);
		add(btnAltaPres);
		btnAltaPres.addActionListener(new InnerActionPresentaciones());
		
		//Botón Modificar Presentaciones
		btnModPres = new JButton(ConstantsGestPresentaciones.presBotonera[1]);
		btnModPres.setBounds(233, 352, 143, 29);
		add(btnModPres);
		btnModPres.addActionListener(new InnerActionPresentaciones());
		
		//Botón Eliminar Presentaciones
		btnDelPres = new JButton(ConstantsGestPresentaciones.presBotonera[2]);
		btnDelPres.setBounds(441, 352, 143, 29);
		add(btnDelPres);
		btnDelPres.addActionListener(new InnerActionPresentaciones());
		
		try {
			populatePresentacionData(); //Mostramos los valores de la BBDD en la tabla tablePres
		} catch (Exception e1) {
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
		    	String idConv = rs.getString(ConstantsDB.valuesIDConvPres);
		    	String idUser = rs.getString(ConstantsDB.valueIDUser);
		    	String fecha = rs.getString(ConstantsDB.valueFecha);
		    	int estado = rs.getInt(ConstantsDB.valueEstadoPres);
		    	String docs = rs.getString(ConstantsDB.valueDocsRequeridos);
		    	
		    	//Modificamos el formato de fecha de la BBDD para tener el mismo que en la pantalla
		    	SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    	Date dateValue = input.parse(fecha);
		    	SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		    	//Añadimos los valores en cada fila de la tabla dinámicamente
		        modelPres.addRow(new Object[]{idPres,idConv,idUser,output.format(dateValue),estado,docs});    
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
       SimpleDateFormat form = new SimpleDateFormat("dd/MM/yyyy");
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
			
			if(e.getSource()==btnAltaPres) {
				ap.setVisible(true);
			}
			
			
			if(e.getSource()==btnModPres) {
				//Comprobamos si se ha seleccionado alguna fila cuando se elimina un usuario.
				if(tablePres.getSelectionModel().isSelectionEmpty()==true ) {
					//Si no selecciona una fila de la tabla y presiona el botón btnMod se muestra el siguiente mensaje
					JOptionPane.showMessageDialog(null,ConstantsMessage.msg14,ConstantsMessage.msg0,
							  JOptionPane.PLAIN_MESSAGE,imageError);
				}else {
					mp.setVisible(true); //Abrimos la pantalla ModPresentaciones
					
					String idPres = tablePres.getValueAt(tablePres.getSelectedRow(), 0).toString();
					String idConv = tablePres.getValueAt(tablePres.getSelectedRow(), 1).toString();
					String idUser = tablePres.getValueAt(tablePres.getSelectedRow(), 2).toString();
					String fecha = tablePres.getValueAt(tablePres.getSelectedRow(), 3).toString();
					String estado = tablePres.getValueAt(tablePres.getSelectedRow(), 4).toString(); 
					String docs = tablePres.getValueAt(tablePres.getSelectedRow(), 5).toString();
					
					//Pasamos los valores a la pantalla ModPresentaciones
					ModPresentaciones.getRowPresentaciones(idPres,idConv,idUser,fecha,estado,docs);
					
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
	 * Adds the row pres.
	 *
	 * @param dataRow the data row
	 */
	//Añadimos una nueva fila en la tabla de convocatorias cuando la damos de alta desde la pantalla AltaConvocatorias.
	public static void addRowPres (Object[] dataRow) {
		DefaultTableModel model = (DefaultTableModel) tablePres.getModel();
		model.addRow(dataRow); //Añadimos fila en la tabla de un nuevo Municipio creado en AltaMunicipios
		
	}
	
	/**
	 * Sets the row convocatorias.
	 *
	 * @param idPres the id pres
	 * @param idConv the id conv
	 * @param idUser the id user
	 * @param fecha  the fecha
	 * @param estado the estado
	 * @param docs   the docs
	 */
	//Recogemos los valores desde ModConvocatorias y lo actualizamos en la tabla jTableConvocatorias de esta clase
	public static void setRowConvocatorias(String idPres,String idConv,String idUser,String fecha,int estado,String docs) {
		DefaultTableModel model = (DefaultTableModel) tablePres.getModel();
		int i = tablePres.getSelectedRow();
		model.setValueAt(idPres,i, 0);     //Actualizamos valor id presentación
		model.setValueAt(idConv,i, 1);	   //Actualizamos valor id convocatoria
		model.setValueAt(idUser,i, 2); 	   //Actualizamos valor id usuario
		model.setValueAt(fecha, i, 3); 	   //Actualizamos valor fecha presentación
		model.setValueAt(estado,i, 4);     //Actualizamos valor estado
		model.setValueAt(docs,	i, 5); 	   //Actualizamos valor docs

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
			/**if(checkCerrado.isSelected()==true) {
				//checkAbierto.setSelected(false);
				checkAbierto.setEnabled(false);
			}**/
		}else if(tipoUsuario.equals(ConstantsGestPresentaciones.tiposUsuariosGestPres[2])) {
			
			//Deshabilitamnos los siguientes componentes para el tipo de usuario Fiscal 
			btnAltaPres.setEnabled(false);
			btnModPres.setEnabled(false);
			btnDelPres.setEnabled(false);
			tablePres.setCellSelectionEnabled(false);
			
			
		}else if(tipoUsuario.equals(ConstantsGestPresentaciones.tiposUsuariosGestPres[3])) {
			//Deshabilitamnos los siguientes componentes para el tipo de usuario Fiscal General
			btnAltaPres.setEnabled(false);
			btnDelPres.setEnabled(false);
		}
	}

	
}
