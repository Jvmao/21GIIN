/*
 * 18 ene 2022
 * Jose V. Martí
 */
package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.ConDB;
import controlador.ConvImplDAO;
import controlador.UsuariosImplDAO;
import modelo.Convocatorias;
import util.ConstantsDB;
import util.ConstantsGestConvocatorias;
import util.ConstantsGestMunicipios;
import util.ConstantsMessage;
import javax.swing.JCheckBox;

// TODO: Auto-generated Javadoc
/**
 * The Class ModConvocatorias.
 */

/**
 * The Class ModConvocatorias.
 */

public class ModConvocatorias extends JDialog {

	/** The content panel. */
	//Variables GUI
	private final JPanel contentPanel = new JPanel();
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The tx cierre. */
	private static JTextField txIdConvocatoria,txIdUser,txApertura,txCierre;;
	
	/** The lb docs. */
	private JLabel lbTitulo,lbIdConvocatoria,lbIdUser,lbDesc,lbApertura,lbCierre,lbEstado,lbDocs;
	
	/** The btn del doc. */
	private JButton okButton,cancelButton,btnAddDoc,btnDelDoc;
	
	/** The text desc. */
	private static JTextArea textDesc;
	
	/** The scroll docs. */
	private JScrollPane scrollDesc,scrollDocs;
	
	/** The j combo tipo. */
	private static JComboBox<String> cbIdUser,jComboTipo;
	
	/** The check cerrado. */
	private static JCheckBox checkAbierto,checkCerrado;
	
	/** The model. */
	//Variables Tabla
	private static DefaultTableModel model;
	
	/** The table docs. */
	private static JTable tableDocs;
	
	/** The image error. */
	//Variable ImageIcon
	private ImageIcon imageError = new ImageIcon(AltaConvocatorias.class.getResource(ConstantsMessage.imgError));
	
	/** The i D usuarios. */
	private ArrayList<String> iDUsuarios = new ArrayList<String>();
	
	/** The udao. */
	private UsuariosImplDAO udao = new UsuariosImplDAO();
	
	/** The conv. */
	private Convocatorias conv = new Convocatorias();
	
	/** The cdao. */
	//variable clase EventoImplDAO
	private ConvImplDAO cdao = new ConvImplDAO();
	
	/** The st. */
	//Variables BBDD
	private Statement st;
	
	/** The rs. */
	private ResultSet rs;
	
	/** The conn. */
	private Connection conn;

	/**
	 * Instantiates a new mod convocatorias.
	 */
	public ModConvocatorias() {
		setBounds(100, 100, 491, 460);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setResizable(false); //impedimos que la ventana se pueda ampliar para evitar problemas de tamaño de campos
		
		//Label título
		lbTitulo = new JLabel(ConstantsGestConvocatorias.lbtituloModConv);
		lbTitulo.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 16));
		lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitulo.setBounds(6, 10, 478, 20);
		contentPanel.add(lbTitulo);
		
		//Label idConvocatoria
		lbIdConvocatoria = new JLabel(ConstantsGestConvocatorias.labelIdConvocatorias);
		lbIdConvocatoria.setBounds(16, 36, 124, 16);
		contentPanel.add(lbIdConvocatoria);
		
		//TextField idUsuario
		txIdConvocatoria = new JTextField();
		txIdConvocatoria.setBounds(164, 31, 258, 26);
		contentPanel.add(txIdConvocatoria);
		txIdConvocatoria.setColumns(10);
		
		//Label id usuario
		lbIdUser = new JLabel(ConstantsGestConvocatorias.labelUsuarioConvocatorias);
		lbIdUser.setBounds(16, 64, 148, 16);
		contentPanel.add(lbIdUser);
		
		//Combobox id usuario
		cbIdUser = new JComboBox<String>();
		cbIdUser.setBounds(164, 60, 118, 27);
		contentPanel.add(cbIdUser);
		ArrayList<?> m = udao.listaIdUserConv(iDUsuarios); //pasamos el tipo de usuario desde la BBDD
		cbIdUser.setModel(new DefaultComboBoxModel<String>(m.toArray(new String[0]))); //listamos valores en cbIdUsuario
		
		//Listener combobox Id Convocatorias para actualizar los datos cuando se selecciona otro item distinto
		cbIdUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				infoUsuario();
			}
			
		});
		
		//Campo tipo usuario
		txIdUser = new JTextField();
		txIdUser.setEditable(false);
		txIdUser.setHorizontalAlignment(SwingConstants.CENTER);
		txIdUser.setBounds(292, 59, 130, 26);
		contentPanel.add(txIdUser);
		txIdUser.setColumns(10);
		
		//Label Descripción
		lbDesc = new JLabel(ConstantsGestConvocatorias.labelDescripcion);
		lbDesc.setBounds(16, 92, 136, 16);
		contentPanel.add(lbDesc);
		//Scroll Pane Desccirpción
		scrollDesc = new JScrollPane();
		scrollDesc.setBounds(164, 93, 258,72);
		contentPanel.add(scrollDesc);
		//Text Area Descricpión
		textDesc = new JTextArea();
		scrollDesc.setViewportView(textDesc);
		
		//Label Apertura
		lbApertura = new JLabel(ConstantsGestConvocatorias.labelApertura);
		lbApertura.setBounds(28, 182, 124, 16);
		contentPanel.add(lbApertura);
		
		//TextField fecha Apertura
		txApertura = new JTextField();
		txApertura.setBounds(164, 177, 136, 26);
		contentPanel.add(txApertura);
		txApertura.setColumns(10);
		txApertura.addKeyListener(new innerKeyModConv());
		
		//Label Cierre
		lbCierre = new JLabel(ConstantsGestConvocatorias.labelCierre);
		lbCierre.setBounds(28, 210, 124, 16);
		contentPanel.add(lbCierre);
		
		//TextField fecha Cierre
		txCierre = new JTextField();
		txCierre.setBounds(164, 205, 136, 26);
		contentPanel.add(txCierre);
		txCierre.setColumns(10);
		txCierre.addKeyListener(new innerKeyModConv());
		
		//Label Estado
		lbEstado = new JLabel(ConstantsGestConvocatorias.labelEstado);
		lbEstado.setBounds(28, 244, 118, 16);
		contentPanel.add(lbEstado);
		
		checkAbierto = new JCheckBox(ConstantsGestConvocatorias.tipoEstado[0].toString());
		checkAbierto.setSelected(true);
		checkAbierto.setBounds(164, 240, 128, 23);
		contentPanel.add(checkAbierto);
		checkAbierto.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(checkAbierto.isSelected()==true) {
					checkCerrado.setSelected(false);
				}
			}
			
		});
		
		checkCerrado = new JCheckBox(ConstantsGestConvocatorias.tipoEstado[1].toString());
		checkCerrado.setBounds(294, 238, 128, 23);
		contentPanel.add(checkCerrado);
		checkCerrado.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(checkCerrado.isSelected()==true) {
					checkAbierto.setSelected(false);
				}	
			}
			
		});
		
		
		//Label Documentos
		lbDocs = new JLabel(ConstantsGestConvocatorias.labelDocs);
		lbDocs.setBounds(28, 275, 171, 16);
		contentPanel.add(lbDocs);
		
		//ComboBox Tipo Documentos
		jComboTipo = new JComboBox<String>();
		jComboTipo.setBounds(28, 303, 148, 27);
		contentPanel.add(jComboTipo);
		jComboTipo.addItem(ConstantsGestConvocatorias.tipoDocs[0].toString());
		jComboTipo.addItem(ConstantsGestConvocatorias.tipoDocs[1].toString());
		jComboTipo.addItem(ConstantsGestConvocatorias.tipoDocs[2].toString());
		jComboTipo.addItem(ConstantsGestConvocatorias.tipoDocs[3].toString());
		jComboTipo.addItem(ConstantsGestConvocatorias.tipoDocs[4].toString());
		
		//Botón para añadir documentos desde el jComboTipo a listDocs
		btnAddDoc = new JButton(ConstantsGestConvocatorias.btnAddDocs);
		btnAddDoc.setBounds(28, 342, 68, 29);
		contentPanel.add(btnAddDoc);
		btnAddDoc.addActionListener(new InnerActionModConvocatorias());
		
		//Botón Eliminar Documento
		btnDelDoc = new JButton(ConstantsGestConvocatorias.btnDelDocs);
		btnDelDoc.setBounds(108, 342, 68, 29);
		contentPanel.add(btnDelDoc);
		btnDelDoc.addActionListener(new InnerActionModConvocatorias());
		
		//Scroll Tabla Documentos
		scrollDocs = new JScrollPane();
		scrollDocs.setBounds(226, 285, 196, 86);
		contentPanel.add(scrollDocs);
		
		//Tabla para añadir los documentos a presentar
		model = new DefaultTableModel();
		tableDocs = new JTable(model);
		tableDocs.setForeground(Color.BLACK);
		tableDocs.setBackground(Color.LIGHT_GRAY);
		tableDocs.setAutoCreateRowSorter(true);
		tableDocs.setFont(new Font("Verdana", Font.PLAIN, 12));
		tableDocs.setShowVerticalLines(true);
		model.addColumn(ConstantsGestConvocatorias.columnaTabla);
		scrollDocs.setViewportView(tableDocs);

		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton(ConstantsGestMunicipios.ModBotonera[0]);
				okButton.setActionCommand(ConstantsGestMunicipios.ModBotonera[0]);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(new InnerActionModConvocatorias());
			}
			{
				cancelButton = new JButton(ConstantsGestMunicipios.ModBotonera[1]);
				cancelButton.setActionCommand(ConstantsGestMunicipios.ModBotonera[1]);
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new InnerActionModConvocatorias());
			}
		}
		
		infoUsuario();
	}
	
	/**
	 * The Class InnerActionModConvocatorias.
	 */
	public class InnerActionModConvocatorias implements ActionListener{

		/**
		 * Action performed.
		 *
		 * @param e the e
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==btnAddDoc) {
				String tipoDoc = jComboTipo.getSelectedItem().toString();
				model.addRow(new Object[]{tipoDoc});
				
			}
			
			if(e.getSource()==btnDelDoc) {
				if(tableDocs.getSelectionModel().isSelectionEmpty()==true ) {
					//Si no selecciona una fila de la tabla y presiona el botón btnBaja se muestra el siguiente mensaje
					JOptionPane.showMessageDialog(null,ConstantsMessage.msg15,ConstantsMessage.msg0,
							  JOptionPane.PLAIN_MESSAGE,imageError);
				}else {
					//Eliminamos Fila seleccionada el la tabla de documentos
					int a = tableDocs.getSelectedRow();
					model.removeRow(a);
				}

			}
			
			if(e.getSource()==okButton) {
				if(txIdConvocatoria.getText().length() !=0 && textDesc.getText().length() !=0 
						&& txApertura.getText().length() !=0 && 
						txCierre.getText().length() !=0 && tableDocs.getModel().getRowCount() != 0) {
					
					
					//Valores fechas
					String apertura = txApertura.getText().toString();
					String cierre = txCierre.getText().toString();
					
					
					//Validaciones fechas
					if(conv.validaFecha(apertura) == false || conv.validaFecha(cierre) == false) {
						//Sí el campo de la fecha de inicio no sigue el formato dd/mm/yyyy mostrará un mensaje de error
						JOptionPane.showMessageDialog(null,ConstantsMessage.msg18,ConstantsMessage.msg0,
													  JOptionPane.PLAIN_MESSAGE,imageError);
					}else if(conv.compruebaFecha(apertura, cierre) == false) {
						//Fecha de presentación no puede ser inferior a la fecha de convocatoria
						JOptionPane.showMessageDialog(null,ConstantsMessage.msg27,ConstantsMessage.msg0,
													  JOptionPane.PLAIN_MESSAGE,
													  imageError);
					}else {
						//Valores tabla documentos
						ArrayList<String> docsValues = new ArrayList<String>();
						for (int i = 0; i < model.getRowCount(); i++){
						     docsValues.add(model.getValueAt(i, 0).toString());
						}
						
						
						//Modifica los datos en la BBDD en la tabla convocatorias
						cdao.modEvento(txIdConvocatoria.getText().toString(),
									   cbIdUser.getSelectedItem().toString(),
									   textDesc.getText().toString() ,
									   apertura, 
									   cierre, 
									   tipoEstadoDB(),
									   docsValues);
						
						//Pasa los datos actualizados a la pantalla GestConvocatorias
						GestConvocatorias.setRowConvocatorias(txIdConvocatoria.getText().toString(),  
															  cbIdUser.getSelectedItem().toString(),
															  textDesc.getText().toString(), 
															  apertura, 
															  cierre,
															  tipoEstadoTabla(), 
															  docsValues);
						
						
						dispose(); //cierra pantalla
						restart(); //reinicia campos
					}
	
					
				}else {
					//Sí alguno de los campos anteriores está vacío mostramos el siguiente mensaje
					JOptionPane.showMessageDialog(null,ConstantsMessage.msg23,ConstantsMessage.msg0,
												  JOptionPane.PLAIN_MESSAGE,imageError);
				}
				
			}
			
			if(e.getSource()==cancelButton) {
				dispose(); //Cerramos Ventana
				restart(); //reinicia campos
			}
			
		}
		
	}
	
	/**
	 * The Class innerKeyModConv.
	 */
	public class innerKeyModConv implements KeyListener{

		/**
		 * Key typed.
		 *
		 * @param e the e
		 */
		@Override
		public void keyTyped(KeyEvent e) {
			if(e.getSource() == txApertura) {
				//El campo passField solo admite 16 caracteres como máximo (01/01/2022 09:00) = 16 carácteres
				if(txApertura.getText().length() == 16) e.consume();
			}
			
			if(e.getSource() == txCierre) {
				if(txCierre.getText().length() == 16) e.consume();
			}
			
		}

		/**
		 * Key pressed.
		 *
		 * @param e the e
		 */
		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		/**
		 * Key released.
		 *
		 * @param e the e
		 */
		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	/**
	 * Info usuario.
	 */
	//Método para obtener los usuarios asociados a las convocatorias
	private void infoUsuario() {
		String idConv = cbIdUser.getSelectedItem().toString();
		try {
			conn = ConDB.getConnection(ConstantsDB.server,ConstantsDB.user,ConstantsDB.pass);
			st = conn.createStatement();
			
			String queryInfoUsuarioConv = "SELECT tipoUsuario "
										   + "FROM usuarios "
										   + "WHERE idUsuario = '"+idConv+"' ";
			
			rs = st.executeQuery(queryInfoUsuarioConv);
			
			
			while(rs.next()) {
				txIdUser.setText(rs.getString(ConstantsDB.valueTipo));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	};
	
	
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
	
	//Conseguimos los valores desde GestConvocatorias para mostrarlos inicialmente en esta pantalla
	/**
	 * Gets the row convocatoria.
	 *
	 * @param idConv  the id conv
	 * @param idUser  the id user
	 * @param desc    the desc
	 * @param fInicio the f inicio
	 * @param fFin    the f fin
	 * @param estado  the estado
	 * @param docs    the docs
	 */
	//public static void getRowConvocatoria(String id,String desc,String fInicio,String fFin,String estado,ArrayList<String> docs) {
	public static void getRowConvocatoria(String idConv,String idUser,String desc,String fInicio,String fFin,
										  String estado,String docs) {
		txIdConvocatoria.setText(idConv);
		cbIdUser.setSelectedItem(idUser);
		textDesc.setText(desc);
		txApertura.setText(fInicio);
		txCierre.setText(fFin);
		
		//En función del valor obtenido el checkBox pasará a ser abierto o cerrado
		if(estado.equals("1")) {
			checkAbierto.setSelected(true);
			checkCerrado.setSelected(false);
		}else if(estado.equals("0")) {
			checkAbierto.setSelected(false);
			checkCerrado.setSelected(true);
		}
		
		//Eliminamos los carácteres del Array para pasar cada valor a la tabla de manera separada
		String rx [] = docs.replaceAll("\\[", "").replaceAll("\\]", "").split(", ");
		for (String a : rx) {
			model.addRow(new Object[] {a});
		}
		
	}
	

	/**
	 * Change date start.
	 *
	 * @return the string
	 */
	//Cambiar formato fecha inicio
	public String changeDateStart() {
		SimpleDateFormat fromUser = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		String start = null;
		String reformattedApertura = null;
		try {
			start = txApertura.getText().toString();
		    reformattedApertura = myFormat.format(fromUser.parse(start));
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		return reformattedApertura;
	}
	
	/**
	 * Change date end.
	 *
	 * @return the string
	 */
	//Cambiar formato fecha cierre
	public String changeDateEnd() {
		SimpleDateFormat fromUser = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		String end = null;
		String reformattedCierre = null;
		
		try {
			end = txCierre.getText().toString();
		    reformattedCierre = myFormat.format(fromUser.parse(end));
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		return reformattedCierre;
	}
	
	
	/**
	 * Restart.
	 */
	//Método para limpiar campos una vez modificada la convocatoria
	public void restart() {
		//Reiniciamos componentes
		txIdConvocatoria.setText("");
		textDesc.setText("");
		txApertura.setText("");
		txCierre.setText("");
		checkAbierto.setSelected(true);
		checkAbierto.setSelected(false);
		jComboTipo.setSelectedIndex(0);
		
		//Limpiamos la tabla de todas las filas que puedan haber
		int rowsToRemove = model.getRowCount();
        for (int i = rowsToRemove - 1; i >= 0; i--) {
            model.removeRow(i);
        }
	}
}
