/*
 * 18 ene 2022
 * Jose V. Martí
 */
package vista;

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
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.ConDB;
import controlador.ConvImplDAO;
import controlador.MunicipiosImplDAO;
import controlador.PresentacionImplDAO;
import modelo.Presentaciones;
import util.ConstantsDB;
import util.ConstantsGestPresentaciones;
import util.ConstantsMessage;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JTable;
import java.awt.Color;

// TODO: Auto-generated Javadoc
/**
 * The Class ModPresentaciones.
 */
public class ModPresentaciones extends JDialog {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The content panel. */
	private final JPanel contentPanel = new JPanel();
	
	/** The lb docs. */
	private JLabel lbIDConv,lbInicioConv,lbFinConv,lbIdPres,lbUsuario,lbFechaPres,lbEstado,lbDocs;
	
	/** The tx fin conv. */
	private JTextField txInicioConv,txFinConv;
	
	/** The tx fecha pres. */
	private static JTextField textIdPres,txFechaPres;
	
	/** The cb ID conv. */
	private static JComboBox<String> cbIDConv;
	
	/** The cb id usuario. */
	private static JComboBox<String> cbIdUsuario;
	
	/** The j combo tipo. */
	private JComboBox<String> jComboTipo;
	
	/** The j sep. */
	private JSeparator jSep;
	
	/** The check abierto. */
	private static JCheckBox checkAbierto;
	
	/** The check cerrado. */
	private static JCheckBox checkCerrado;
	
	/** The btn del doc. */
	private JButton okButton,cancelButton,btnAddDoc,btnDelDoc;
	
	/** The scroll docs. */
	private JScrollPane scrollDocs;
	
	/** The model docs. */
	private static DefaultTableModel modelDocs;
	
	/** The table docs. */
	private JTable tableDocs;
	
	/** The i D usuarios. */
	//Variable listar datos id usuario  y convocatorias desde arraylist de BBDD
	private ArrayList<String> iDUsuarios = new ArrayList<String>();
	
	/** The i D conv. */
	private ArrayList<String> iDConv = new ArrayList<String>();
	
	/** The mdao. */
	//Llamada clase UsuariosImplDAO
	private MunicipiosImplDAO mdao = new MunicipiosImplDAO();
	
	/** The cdao. */
	//Llamada clase ConvImplDAO
	private ConvImplDAO cdao = new ConvImplDAO();
	
	/** The pdao. */
	//Llamada clase PresentacionImplDAO
	private PresentacionImplDAO pdao = new PresentacionImplDAO();
	
	/** The image error. */
	//Variable ImageIcon
	private ImageIcon imageError = new ImageIcon(AltaConvocatorias.class.getResource(ConstantsMessage.imgError));
	
	/** The st. */
	//Variables BBDD
	private Statement st;
	
	/** The rs. */
	private ResultSet rs;
	
	/** The conn. */
	private Connection conn;
	
	/** The p. */
	private Presentaciones p = new Presentaciones();

	/**
	 * Instantiates a new mod presentaciones.
	 */
	public ModPresentaciones() {
		setBounds(100, 100, 434, 347);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 434, 337);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		setResizable(false); //impedimos que la ventana se pueda ampliar para evitar problemas de tamaño de campos
		
		//Label Id Convocatoria
		lbIDConv = new JLabel(ConstantsGestPresentaciones.labelIdConv);
		lbIDConv.setBounds(6, 6, 133, 16);
		contentPanel.add(lbIDConv);
		
		//Combobox Id Convocatoria
		cbIDConv = new JComboBox<String>();
		cbIDConv.setBounds(0, 25, 149, 27);
		contentPanel.add(cbIDConv);
		ArrayList<?> s = cdao.listaIDConvocatoria(iDConv); //pasamos el id de convocatorias desde la BBDD
		cbIDConv.setModel(new DefaultComboBoxModel<String>(s.toArray(new String[0]))); //listamos valores en cbIDConv
		cbIDConv.addActionListener(new InnerActionModPre());
		
		//Label Fecha Inicio convocatoria
		lbInicioConv = new JLabel(ConstantsGestPresentaciones.labelIniConv);
		lbInicioConv.setBounds(151, 6, 127, 16);
		contentPanel.add(lbInicioConv);
		
		//Campo Inicio Convocatoria
		txInicioConv = new JTextField();
		txInicioConv.setEditable(false);
		txInicioConv.setBounds(151, 24, 130, 26);
		contentPanel.add(txInicioConv);
		txInicioConv.setColumns(10);
		
		//Label Fecha Fin convocatoria
		lbFinConv = new JLabel(ConstantsGestPresentaciones.labelFinConv);
		lbFinConv.setBounds(290, 6, 127, 16);
		contentPanel.add(lbFinConv);
		
		//Campo Fin Convocatoria
		txFinConv = new JTextField();
		txFinConv.setEditable(false);
		txFinConv.setBounds(287, 24, 130, 26);
		contentPanel.add(txFinConv);
		txFinConv.setColumns(10);
		
		//Separador
		jSep = new JSeparator();
		jSep.setForeground(Color.BLACK);
		jSep.setBounds(6, 64, 409, 12);
		contentPanel.add(jSep);
		
		//Label Id Presentaciones
		lbIdPres = new JLabel(ConstantsGestPresentaciones.labelIdPres);
		lbIdPres.setBounds(6, 76, 115, 16);
		contentPanel.add(lbIdPres);
		
		//Campo Id Presentaciones
		textIdPres = new JTextField();
		textIdPres.setEditable(false);
		textIdPres.setHorizontalAlignment(SwingConstants.CENTER);
		textIdPres.setBounds(6, 92, 127, 26);
		contentPanel.add(textIdPres);
		textIdPres.setColumns(10);
		
		//Label Usuario
		lbUsuario = new JLabel(ConstantsGestPresentaciones.txTipoUsuarioPres);
		lbUsuario.setBounds(293, 76, 135, 16);
		contentPanel.add(lbUsuario);
		
		//ComboBox ID Usuarios 
		cbIdUsuario = new JComboBox<String>();
		contentPanel.add(cbIdUsuario);
		cbIdUsuario.setBounds(289, 93, 134, 27);
		ArrayList<?> m = mdao.listaIdUsuariosMunicipios(iDUsuarios); //pasamos el tipo de usuario desde la BBDD
		cbIdUsuario.setModel(new DefaultComboBoxModel<String>(m.toArray(new String[0]))); //listamos valores en cbIdUsuario
		
		//Label Fecha Presentación
		lbFechaPres = new JLabel(ConstantsGestPresentaciones.labelFechaPres);
		lbFechaPres.setBounds(151, 76, 127, 16);
		contentPanel.add(lbFechaPres);
		
		//Fecha Presentación Timestamp
		txFechaPres = new JTextField();
		txFechaPres.setHorizontalAlignment(SwingConstants.CENTER);
		txFechaPres.setBounds(151, 92, 133, 26);
		contentPanel.add(txFechaPres);
		txFechaPres.setColumns(10);
		txFechaPres.addKeyListener(new innerKeyModPres());
		
		//label estado
		lbEstado = new JLabel(ConstantsGestPresentaciones.labelEstado);
		lbEstado.setBounds(6, 130, 135, 16);
		contentPanel.add(lbEstado);
		
		//Check estado abierto
		checkAbierto = new JCheckBox(ConstantsGestPresentaciones.labelTipoEstado[0]);
		checkAbierto.setSelected(true);
		checkAbierto.setBounds(151, 126, 84, 23);
		contentPanel.add(checkAbierto);
		checkAbierto.addItemListener(new ItemListener() {
			//Listener para controlar el estado del checkBox
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(checkAbierto.isSelected()==true) {
					checkCerrado.setSelected(false);
				}
			}
			
		});
		
		//Check estado cerrado
		checkCerrado = new JCheckBox(ConstantsGestPresentaciones.labelTipoEstado[1]);
		checkCerrado.setBounds(299, 126, 105, 23);
		contentPanel.add(checkCerrado);
		checkCerrado.addItemListener(new ItemListener() {
			//Listener para controlar el estado del checkBox
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(checkCerrado.isSelected()==true) {
					checkAbierto.setSelected(false);
				}
				
			}
			
		});
		
		//Label Documentos
		lbDocs = new JLabel(ConstantsGestPresentaciones.labelDocs);
		lbDocs.setBounds(6, 169, 149, 16);
		contentPanel.add(lbDocs);
		
		//Tipo de Documentos
		jComboTipo = new JComboBox<String>();
		contentPanel.add(jComboTipo);
		jComboTipo.setBounds(6, 197, 153, 27);
		jComboTipo.addItem(ConstantsGestPresentaciones.tipoDocs[0].toString());
		jComboTipo.addItem(ConstantsGestPresentaciones.tipoDocs[1].toString());
		jComboTipo.addItem(ConstantsGestPresentaciones.tipoDocs[2].toString());
		jComboTipo.addItem(ConstantsGestPresentaciones.tipoDocs[3].toString());
		jComboTipo.addItem(ConstantsGestPresentaciones.tipoDocs[4].toString());
		
		//Botón para añadir documentos desde el jComboTipo a listDocs
		btnAddDoc = new JButton(ConstantsGestPresentaciones.btnAddDocs);
		contentPanel.add(btnAddDoc);
		btnAddDoc.setBounds(10, 236, 68, 29);
		btnAddDoc.addActionListener(new InnerActionModPre());
		
		//Botón para eliminar documentos desde el jComboTipo a listDocs
		btnDelDoc = new JButton(ConstantsGestPresentaciones.btnDelDocs);
		contentPanel.add(btnDelDoc);
		btnDelDoc.setBounds(91, 236, 68, 29);
		btnDelDoc.addActionListener(new InnerActionModPre());
		
		
		//Scroll Tabla Documentos
		scrollDocs = new JScrollPane();
		contentPanel.add(scrollDocs);
		scrollDocs.setBounds(186, 174, 235, 91);
		tableDocs = new JTable(modelDocs);
		tableDocs.setForeground(Color.BLACK);
		tableDocs.setBackground(Color.LIGHT_GRAY);
		tableDocs.setAutoCreateRowSorter(true);
		tableDocs.setFont(new Font("Verdana", Font.PLAIN, 12));
		tableDocs.setShowVerticalLines(true);
		scrollDocs.setViewportView(tableDocs);
		
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
		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 277, 432, 39);
			contentPanel.add(buttonPane);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			{
				okButton = new JButton(ConstantsGestPresentaciones.altaBotoneraPres[0]);
				okButton.setActionCommand(ConstantsGestPresentaciones.altaBotoneraPres[0]);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(new InnerActionModPre());
			}
			{
				cancelButton = new JButton(ConstantsGestPresentaciones.altaBotoneraPres[1]);
				cancelButton.setActionCommand(ConstantsGestPresentaciones.altaBotoneraPres[1]);
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new InnerActionModPre());
			}
		}
		
		fechasConvocatoria(); //Añadimos método para mostrar las fechas de la convocatoria seleccionada
	}
	
	/**
	 * The Class InnerActionModPre.
	 */
	public class InnerActionModPre implements ActionListener{

		/**
		 * Action performed.
		 *
		 * @param e the e
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==btnAddDoc) {
				String tipoDoc = jComboTipo.getSelectedItem().toString();
				modelDocs.addRow(new Object[]{tipoDoc}); //Añadimos item seleccionado en la tabla de documentos
				
			}
			
			if(e.getSource()==btnDelDoc) {
				if(tableDocs.getSelectionModel().isSelectionEmpty()==true ) {
					//Si no selecciona una fila de la tabla y presiona el botón btnBaja se muestra el siguiente mensaje
					JOptionPane.showMessageDialog(null,ConstantsMessage.msg15,ConstantsMessage.msg0,
							  JOptionPane.PLAIN_MESSAGE,imageError);
				}else {
					//Eliminamos Fila seleccionada en la tabla de documentos
					int a = tableDocs.getSelectedRow();
					modelDocs.removeRow(a);
				}

			}
			
			if(e.getSource()==cbIDConv) {
				fechasConvocatoria(); //actualizamos valores si cambiamos de selección
			}
			
			if(e.getSource()==okButton) {
				String fechaInicioConv = txInicioConv.getText().toString();
				String fechaPres = txFechaPres.getText().toString();
				
				
				if(p.validaFecha(fechaPres) == false){
					//Sí el formato de fecha es incorrecto mostramos el siguiente mensaje
					JOptionPane.showMessageDialog(null,ConstantsMessage.msg23,ConstantsMessage.msg0,
												  JOptionPane.PLAIN_MESSAGE,imageError);
					
				}else if(p.compruebaFecha(fechaInicioConv, fechaPres) == false) {
					//Mensaje que se muestra cuando el formato de fecha es incorrectp
					JOptionPane.showMessageDialog(null,ConstantsMessage.msg18,ConstantsMessage.msg0,
							  JOptionPane.PLAIN_MESSAGE,imageError);
					
				}else if(txFechaPres.getText().length() == 0) {
					//Sí el campo de la fecha está vacío 
					JOptionPane.showMessageDialog(null,ConstantsMessage.msg1,ConstantsMessage.msg0,
												  JOptionPane.PLAIN_MESSAGE,imageError);
					
				}else {
					//Valores tabla documentos
					ArrayList<String> docsValues = new ArrayList<String>();
					for (int i = 0; i < modelDocs.getRowCount(); i++){
					     docsValues.add(modelDocs.getValueAt(i, 0).toString());
					}
					
					//Comprobamos que los documentos a presentar no estén vacíos
					if(docsValues.isEmpty()) {
						JOptionPane.showMessageDialog(null,ConstantsMessage.msg19,ConstantsMessage.msg0,
								  JOptionPane.PLAIN_MESSAGE,imageError);
					}else {
						//Actualizamos valores en la BBDD desde la tabla presentaciones
						pdao.modPresentacion(textIdPres.getText().toString(), 
											 cbIDConv.getSelectedItem().toString(),
											 cbIdUsuario.getSelectedItem().toString(),
											 txFechaPres.getText().toString(), 
											 tipoEstadoDB(), 
											 docsValues);
						
						//Actualizamos la tabla GestPresentaciones pasándole los nuevos valores
						GestPresentaciones.setRowConvocatorias(textIdPres.getText().toString(), 
											 cbIDConv.getSelectedItem().toString(),
											 cbIdUsuario.getSelectedItem().toString(),
											 txFechaPres.getText().toString(), 
											 tipoEstadoTabla(), 
											 docsValues.toString());
						
						dispose(); //cerramos la ventana
						restart(); //reiniciamos los campos
					}

				}
			}
			
			if(e.getSource()==cancelButton) {
				dispose(); //cerramos la ventana
				restart(); //reiniciamos los campos
			}
			
		}
		
	}
	
	/**
	 * The Class innerKeyModPres.
	 */
	public class innerKeyModPres implements KeyListener{

		/**
		 * Key typed.
		 *
		 * @param e the e
		 */
		@Override
		public void keyTyped(KeyEvent e) {
			if(e.getSource() == txFechaPres) {
				//El campo passField solo admite 16 caracteres como máximo (01/01/2022 09:00) = 16 carácteres
				if(txFechaPres.getText().length() == 16) e.consume();
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
	 * Gets the row presentaciones.
	 *
	 * @param idPres    the id pres
	 * @param idConv    the id conv
	 * @param idUser    the id user
	 * @param fechaPres the fecha pres
	 * @param estado    the estado
	 * @param docs      the docs
	 */
	//Obtenemos los datos seleccionados en la pantalla GestPresentaciones
	public static void getRowPresentaciones(String idPres,String idConv,String idUser,String fechaPres,String estado,String docs) {
		textIdPres.setText(idPres);
		cbIDConv.setSelectedItem(idConv);
		cbIdUsuario.setSelectedItem(idUser);
		txFechaPres.setText(fechaPres);
		
		
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
			modelDocs.addRow(new Object[] {a});
		}
		
	}
	
	/**
	 * Fechas convocatoria.
	 */
	//Obtenemos las fechas de inicio y cierre de la convocaotira seleccionada mediante una consulta en la BBDD
	private void fechasConvocatoria() {
		String idConv = cbIDConv.getSelectedItem().toString();
		
		try {
			conn = ConDB.getConnection(ConstantsDB.server,ConstantsDB.user,ConstantsDB.pass);
			st = conn.createStatement();
			
			String queryFechasConvocatoria = "SELECT fechaApertura,fechaCierre "
										   + "FROM convocatorias "
										   + "WHERE idConvocatorias = '"+idConv+"'";
			
			rs = st.executeQuery(queryFechasConvocatoria);
			
			SimpleDateFormat fromUser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			
			while(rs.next()) {
				txInicioConv.setText(myFormat.format(fromUser.parse(rs.getString(ConstantsDB.valueFechaApertura))));
				txFinConv.setText(myFormat.format(fromUser.parse(rs.getString(ConstantsDB.valueFechaCierre))));
			}
			
		} catch (SQLException | ParseException e) {
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

	
	/**
	 * Restart.
	 */
	//Método para limpiar campos una vez modificada la convocatoria
	public void restart() {
		//Reiniciamos componentes
		checkAbierto.setSelected(true);
		checkCerrado.setSelected(false);
		
		cbIDConv.setSelectedIndex(0);
		cbIdUsuario.setSelectedIndex(0);	
		
		//Limpiamos la tabla de todas las filas que puedan haber
		int rowsToRemove = modelDocs.getRowCount();
        for (int i = rowsToRemove - 1; i >= 0; i--) {
            modelDocs.removeRow(i);
        }
	}
}
