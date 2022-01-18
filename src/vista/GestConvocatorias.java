/*
 * 18 ene 2022
 * Jose V. Martí
 */

package vista;

import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.ConDB;
import controlador.ConvImplDAO;
import util.ConstantsDB;
import util.ConstantsGestConvocatorias;
import util.ConstantsMessage;
import javax.swing.border.LineBorder;

// TODO: Auto-generated Javadoc
/**
 * The Class GestConvocatorias.
 */

/**
 * The Class GestConvocatorias.
 */

public class GestConvocatorias extends JPanel {
	

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The content pane. */
	private JPanel contentPane;
	
	/** The lbimage convocatorias. */
	private JLabel lbTitulo,lbTipoUsuario,lbimageConvocatorias;
	
	/** The tx id usuario. */
	private JTextField txTipoUsuario,txIdUsuario;
	
	/** The j table convocatorias. */
	private static JTable jTableConvocatorias;
	
	/** The scroll pane. */
	private JScrollPane scrollPane;
	
	/** The btn baja. */
	private JButton btnAlta,btnMod,btnBaja;

	/** The model. */
	//Variable Tabla
	private DefaultTableModel model;

	/** The conn. */
	//Variables Conexión
	private Connection conn;
	
	/** The ac. */
	private AltaConvocatorias ac = new AltaConvocatorias();
	
	/** The mc. */
	private ModConvocatorias mc = new ModConvocatorias();
	
	/** The cdao. */
	private ConvImplDAO cdao = new ConvImplDAO();


	/** The image error. */
	private ImageIcon imageError = new ImageIcon(GestConvocatorias.class.getResource(ConstantsMessage.imgError));
	

	/**
	 * Instantiates a new gest convocatorias.
	 */
	public GestConvocatorias() {
		setBounds(100, 100, 650, 450);
		contentPane=this;
		setLayout(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		//Label Título
		lbTitulo = new JLabel(ConstantsGestConvocatorias.labelTitulo);
		lbTitulo.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
		lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitulo.setBounds(6, 6, 638, 16);
		add(lbTitulo);
		
		//Label Tipo Usuario
		lbTipoUsuario = new JLabel(ConstantsGestConvocatorias.labelTipoConectado);
		lbTipoUsuario.setBounds(26, 81, 160, 16);
		add(lbTipoUsuario);
		
		//JText ID Usuario
		txIdUsuario = new JTextField();
		txIdUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		txIdUsuario.setEditable(false);
		txIdUsuario.setBounds(198, 76, 130, 26);
		add(txIdUsuario);
		txIdUsuario.setColumns(10);
		
		//JText para rellenar el tipo de usuario conectado
		txTipoUsuario = new JTextField();
		txTipoUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		txTipoUsuario.setBounds(340, 76, 130, 26);
		txTipoUsuario.setEditable(false);
		add(txTipoUsuario);
		txTipoUsuario.setColumns(10);
		
		//Imagen usuarios
		lbimageConvocatorias = new JLabel("");
		lbimageConvocatorias.setHorizontalAlignment(SwingConstants.CENTER);
		lbimageConvocatorias.setBounds(482, 6, 146, 122);
	    //Icono usuarios
		lbimageConvocatorias.setIcon(new ImageIcon(GestConvocatorias.class.getResource(ConstantsGestConvocatorias.imgConvocatoria)));
		add(lbimageConvocatorias);
		
		//ScrollPane para añadir la tabla de usuarios
		scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 143, 602, 203);
		add(scrollPane);
		
		//Tabla
		model = new DefaultTableModel();
		jTableConvocatorias = new JTable(model);
		jTableConvocatorias.setBorder(new LineBorder(Color.BLACK));
		jTableConvocatorias.setForeground(Color.BLACK);
		jTableConvocatorias.setBackground(Color.LIGHT_GRAY);
		jTableConvocatorias.setAutoCreateRowSorter(true);
		jTableConvocatorias.setFont(new Font("Verdana", Font.PLAIN, 12));
		jTableConvocatorias.setShowVerticalLines(true);
	    scrollPane.setViewportView(jTableConvocatorias);
	    model.addColumn(ConstantsGestConvocatorias.tableColumns[0]); //ID Convocatoria
	    model.addColumn(ConstantsGestConvocatorias.tableColumns[1]); //ID Usario
	    model.addColumn(ConstantsGestConvocatorias.tableColumns[2]); //Descripción
	    model.addColumn(ConstantsGestConvocatorias.tableColumns[3]); //Fecha Apertura
	    model.addColumn(ConstantsGestConvocatorias.tableColumns[4]); //Fecha Cierre
	    model.addColumn(ConstantsGestConvocatorias.tableColumns[5]); //Estado
	    model.addColumn(ConstantsGestConvocatorias.tableColumns[6]); //Documentos
		
		//Botón Alta Usuarios
		btnAlta = new JButton(ConstantsGestConvocatorias.tituloBotonera[0].toString());
		btnAlta.setBounds(59, 358, 139, 29);
		add(btnAlta);
		btnAlta.addActionListener(new InnerActionConvocatorias());
		
		//Botón Modificación Usuarios
		btnMod = new JButton(ConstantsGestConvocatorias.tituloBotonera[1].toString());
		btnMod.setBounds(256, 358, 139, 29);
		add(btnMod);
		btnMod.addActionListener(new InnerActionConvocatorias());
		
		//Botón Baja Usuarios
		btnBaja = new JButton(ConstantsGestConvocatorias.tituloBotonera[2].toString());
		btnBaja.setBounds(453, 358, 139, 29);
		add(btnBaja);
		btnBaja.addActionListener(new InnerActionConvocatorias());
		

		try {
			populateConvocatoriaData(); //Listamos valores en la tabla 
		} catch (ParseException e) {
			e.printStackTrace();
		} 

	}
	
	
	
	/**
	 * The Class InnerActionConvocatorias.
	 */
	//Definimos los actionListener de los componentes desde la inner class
	public class InnerActionConvocatorias implements ActionListener{

		/**
		 * Action performed.
		 *
		 * @param e the e
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnAlta) {
				try {
					ac.setVisible(true); //accedemos a la pantalla de Alta de nuevas convocatorias
				}catch(NullPointerException n) {
					n.getStackTrace();
				}
	
			}
			
			if(e.getSource()==btnMod) {
				//Comprobamos si se ha seleccionado alguna fila cuando se elimina un usuario.
				if(jTableConvocatorias.getSelectionModel().isSelectionEmpty()==true ) {
					//Si no selecciona una fila de la tabla y presiona el botón btnMod se muestra el siguiente mensaje
					JOptionPane.showMessageDialog(null,ConstantsMessage.msg14,ConstantsMessage.msg0,JOptionPane.ERROR_MESSAGE);
				}else {
					mc.setVisible(true);
					//Recogemos los valores de la fila seleccionada
					String idConv = jTableConvocatorias.getValueAt(jTableConvocatorias.getSelectedRow(), 0).toString();
					String idUser = jTableConvocatorias.getValueAt(jTableConvocatorias.getSelectedRow(), 1).toString();
					String desc = jTableConvocatorias.getValueAt(jTableConvocatorias.getSelectedRow(), 2).toString();
					String inicio = jTableConvocatorias.getValueAt(jTableConvocatorias.getSelectedRow(), 3).toString();
					String fin = jTableConvocatorias.getValueAt(jTableConvocatorias.getSelectedRow(), 4).toString();
					String estado = jTableConvocatorias.getValueAt(jTableConvocatorias.getSelectedRow(), 5).toString();
					String docs = jTableConvocatorias.getValueAt(jTableConvocatorias.getSelectedRow(), 6).toString();
					
					//Pasamos los valores recogidos en la tabla jTableConvocatorias a la pantalla ModConvocatorias
					ModConvocatorias.getRowConvocatoria(idConv,idUser, desc,inicio,fin,estado,docs);

				}

			}
			
			if(e.getSource()==btnBaja) {
				if(jTableConvocatorias.getSelectionModel().isSelectionEmpty()==true ) {
					//Si no selecciona una fila de la tabla y presiona el botón btnBaja se muestra el siguiente mensaje
					JOptionPane.showMessageDialog(null,ConstantsMessage.msg16,ConstantsMessage.msg0,JOptionPane.PLAIN_MESSAGE,imageError);
				}else {
					//Elimina al usuario de la tabla y de la BBDD
					DefaultTableModel model = (DefaultTableModel) jTableConvocatorias.getModel() ;
					int col = 0;
					int rows = jTableConvocatorias.getSelectedRow();
					String id = jTableConvocatorias.getModel().getValueAt(rows, col).toString();
					int a = jTableConvocatorias.getSelectedRow();
					
					//El usuario debe de confirmar la eliminación del dato seleccionado
					int confirm=JOptionPane.showConfirmDialog(null,ConstantsMessage.msg17) ;
					if(JOptionPane.OK_OPTION==confirm){
						model.removeRow(a); 
						cdao.delEvento(id);
					}
				}
			}
			
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
	
	
	//Definimos método para mostrar los datos almacenados en la BBDD
	/**
	 * Populate convocatoria data.
	 *
	 * @throws ParseException the parse exception
	 */
	//respecto de la tabla usuarios
	private void populateConvocatoriaData() throws ParseException {
		try {
			//Conexión a la BBDD
			//conn = DriverManager.getConnection(ConstantsDB.server,ConstantsDB.user,ConstantsDB.pass);
			conn = ConDB.getConnection(ConstantsDB.server,ConstantsDB.user,ConstantsDB.pass);
			
			//Consulta a BBDD
		    Statement st = conn.createStatement();
		    ResultSet rs = st.executeQuery(ConstantsDB.queryListaConvocatorias);
		    

		    //Obtenemos todos los valores de la BBDD y los vamos añadiendo uno a uno en la tabla
		    while (rs.next()) {
		    	String idConv = rs.getString(ConstantsDB.valueIDConvocatorias);
		    	String idUser = rs.getString(ConstantsDB.valueIDUserConv);
		    	String desc = rs.getString(ConstantsDB.valueDescPresentacion);
		    	String apertura = rs.getString(ConstantsDB.valueFechaApertura);
		    	String cierre = rs.getString(ConstantsDB.valueFechaCierre);
		    	int estado = rs.getInt(ConstantsDB.valueEstadoApertura);
		    	String docs = rs.getString(ConstantsDB.valueDocsPresentados);
		    	
		    	SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		    	
		    	Date dateValueApertura = input.parse(apertura);
		    	SimpleDateFormat outputApertura = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		    	Date dateValueCierre = input.parse(cierre);
		    	SimpleDateFormat outputCierre = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		    	

		    	//Añadimos los valores en cada fila de la tabla dinámicamente
		        model.addRow(new Object[]{idConv,
		        							idUser,
		        							desc,
		        							outputApertura.format(dateValueApertura),
		        							outputCierre.format(dateValueCierre),
		        							estado,
		        							docs}); 
		        
		   }
			      
		      
		} catch (SQLException e) {
			e.printStackTrace();
		}
	      
	}
	
	
	/**
	 * Adds the row convocatorias.
	 *
	 * @param dataRow the data row
	 */
	//Añadimos una nueva fila en la tabla de convocatorias cuando la damos de alta desde la pantalla AltaConvocatorias.
	public static void addRowConvocatorias (Object[] dataRow) {
		DefaultTableModel model = (DefaultTableModel) jTableConvocatorias.getModel();
		model.addRow(dataRow); //Añadimos fila en la tabla de un nuevo Municipio creado en AltaMunicipios
		
	}
	
	/**
	 * Parses the fecha.
	 *
	 * @param fecha the fecha
	 * @return the string
	 * @throws ParseException the parse exception
	 */
	//Actualizamos formato de fecha para que evitar problemas de conversión en la BBDD
	public String parseFecha(String fecha) throws ParseException{
        SimpleDateFormat actualFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = actualFormat.parse(fecha);

        SimpleDateFormat newFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String nuevaFecha = newFormat.format(date).toString();
        
		return nuevaFecha;
	}
	
	/**
	 * Sets the row convocatorias.
	 *
	 * @param idConv the id conv
	 * @param idUser the id user
	 * @param desc   the desc
	 * @param inicio the inicio
	 * @param fin    the fin
	 * @param estado the estado
	 * @param docs   the docs
	 */
	//Recogemos los valores desde ModConvocatorias y lo actualizamos en la tabla jTableConvocatorias de esta clase
	public static void setRowConvocatorias(String idConv,String idUser,String desc,String inicio,String fin,int estado,ArrayList<String> docs) {
		DefaultTableModel model = (DefaultTableModel) jTableConvocatorias.getModel();
		int i = jTableConvocatorias.getSelectedRow();
		model.setValueAt(idConv,i,0);   //Actualizamos valor idConv
		model.setValueAt(idUser,i,1);	//Actualizamos valor idUser
		model.setValueAt(desc,i,2); 	//Actualizamos valor descripción
		model.setValueAt(inicio,i,3); 	//Actualizamos valor fecha inicio
		model.setValueAt(fin,i,4); 		//Actualizamos valor fecha fin
		model.setValueAt(estado,i,5); 	//Actualizamos valor estado
		model.setValueAt(docs,i,6); 	//Actualizamos valor documentos

	}
	
	/**
	 * Control gest convocatorias.
	 *
	 * @param tipoUsuario the tipo usuario
	 */
	//Método para controlar las acciones que pueden llevar a cabo los diferentes usuarios en GestConvocatorias
	public void controlGestConvocatorias(String tipoUsuario) {
		tipoUsuario = txTipoUsuario.getText().toString();
		
		//Cuando el tipo de usuario es distinto a Administrador deshabilitamos los siguientes componentes
		if(tipoUsuario.equals(ConstantsGestConvocatorias.tiposUsuariosGestConv[1])
				|| tipoUsuario.equals(ConstantsGestConvocatorias.tiposUsuariosGestConv[2])) {
			
			//Deshabilitamnos los siguientes componentes para el tipo de usuario Cuentadante y Fiscal 
			btnAlta.setEnabled(false);
			btnMod.setEnabled(false);
			btnBaja.setEnabled(false);
			jTableConvocatorias.setCellSelectionEnabled(false);
			
		}
	}
	

}
