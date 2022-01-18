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
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
import util.ConstantsMessage;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JCheckBox;

// TODO: Auto-generated Javadoc
/**
 * The Class AltaConvocatorias.
 */

/**
 * The Class AltaConvocatorias.
 */

public class AltaConvocatorias extends JDialog {


	/** The content panel. */
	private final JPanel contentPanel = new JPanel();
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The tx cierre. */
	private JTextField txIdConvocatoria,txUserConv,txApertura,txCierre;
	
	/** The lb docs. */
	private JLabel lbTitulo,lbIdConvocatoria,lbUserConv,lbDesc,lbApertura,lbCierre,lbEstado,lbDocs;
	
	/** The btn del doc. */
	private JButton okButton,cancelButton,btnAddDoc,btnDelDoc;
	
	/** The text desc. */
	private JTextArea textDesc;
	
	/** The scroll docs. */
	private JScrollPane scrollDesc,scrollDocs;
	
	/** The check cerrado. */
	private JCheckBox checkAbierto,checkCerrado;
	
	/** The j combo tipo. */
	private JComboBox<String> cbUserConv,jComboTipo;
	
	/** The model. */
	//Variables Tabla
	private DefaultTableModel model;
	
	/** The table docs. */
	private JTable tableDocs;
	
	/** The image error. */
	//Recurso imagen
	private ImageIcon imageError = new ImageIcon(AltaConvocatorias.class.getResource(ConstantsMessage.imgError));
	
	/** The i D usuarios. */
	private ArrayList<String> iDUsuarios = new ArrayList<String>();
	
	/** The udao. */
	private UsuariosImplDAO udao = new UsuariosImplDAO();
	
	/** The conv. */
	private Convocatorias conv = new Convocatorias();
	
	/** The cdao. */
	//Clase EventoImplDAO
	private ConvImplDAO cdao = new ConvImplDAO();
	
	/** The button pane. */
	private JPanel buttonPane;
	
	/** The st. */
	//Variables BBDD
	private Statement st;
	
	/** The rs. */
	private ResultSet rs;
	
	/** The conn. */
	private Connection conn;

	/**
	 * Instantiates a new alta convocatorias.
	 */

	public AltaConvocatorias() {
		setBounds(100, 100, 490, 450);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setResizable(false); //impedimos que la ventana se pueda ampliar para evitar problemas de tamaño de campos
		
		//Label título
		lbTitulo = new JLabel(ConstantsGestConvocatorias.lbAltaTitulo);
		lbTitulo.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 16));
		lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitulo.setBounds(6, 10, 478, 20);
		contentPanel.add(lbTitulo);
		
		//Label id convocatoria
		lbIdConvocatoria = new JLabel(ConstantsGestConvocatorias.labelIdConvocatorias);
		lbIdConvocatoria.setBounds(28, 35, 136, 16);
		contentPanel.add(lbIdConvocatoria);
		
		//TextField id convocatoria
		txIdConvocatoria = new JTextField();
		txIdConvocatoria.setBounds(164, 30, 258, 26);
		contentPanel.add(txIdConvocatoria);
		txIdConvocatoria.setColumns(10);
		
		//Label idUsuario
		lbUserConv = new JLabel(ConstantsGestConvocatorias.labelUsuarioConvocatorias);
		lbUserConv.setBounds(28, 63, 136, 16);
		contentPanel.add(lbUserConv);
		
		//Combobox Id Usuario convocatoria
		cbUserConv = new JComboBox<String>();
		cbUserConv.setBounds(164, 59, 108, 27);
		contentPanel.add(cbUserConv);
		ArrayList<?> m = udao.listaIdUserConv(iDUsuarios); //pasamos el tipo de usuario desde la BBDD
		cbUserConv.setModel(new DefaultComboBoxModel<String>(m.toArray(new String[0]))); //listamos valores en cbIdUsuario
		
		//Listener combobox Id Convocatorias para actualizar los datos cuando se selecciona otro item distinto
		cbUserConv.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				infoUsuariosConv();
			}
			
		});
		
		//Campo tipo de usuario
		txUserConv = new JTextField();
		txUserConv.setHorizontalAlignment(SwingConstants.CENTER);
		txUserConv.setEditable(false);
		txUserConv.setBounds(292, 58, 130, 26);
		contentPanel.add(txUserConv);
		txUserConv.setColumns(10);
		
		//Label Descripción
		lbDesc = new JLabel(ConstantsGestConvocatorias.labelDescripcion);
		lbDesc.setBounds(28, 93, 136, 16);
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
		lbApertura.setBounds(28, 185, 136, 16);
		contentPanel.add(lbApertura);
		
		//TextField fecha Apertura
		txApertura = new JTextField();
		txApertura.setBounds(164, 177, 130, 26);
		contentPanel.add(txApertura);
		txApertura.setColumns(10);
		txApertura.addKeyListener(new innerKeyAltaConv());
		
		//Label Cierre
		lbCierre = new JLabel(ConstantsGestConvocatorias.labelCierre);
		lbCierre.setBounds(28, 213, 136, 16);
		contentPanel.add(lbCierre);
		
		//TextField fecha Cierre
		txCierre = new JTextField();
		txCierre.setBounds(164, 208, 130, 26);
		contentPanel.add(txCierre);
		txCierre.setColumns(10);
		txCierre.addKeyListener(new innerKeyAltaConv());
		
		//Label Cierre
		lbEstado = new JLabel(ConstantsGestConvocatorias.labelEstado);
		lbEstado.setBounds(28, 241, 118, 16);
		contentPanel.add(lbEstado);
		
		//CheckBox Abierto
		checkAbierto = new JCheckBox(ConstantsGestConvocatorias.tipoEstado[0].toString());
		checkAbierto.setSelected(true);
		checkAbierto.setBounds(164, 237, 128, 23);
		contentPanel.add(checkAbierto);
		checkAbierto.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(checkAbierto.isSelected()==true) {
					checkCerrado.setSelected(false);
				}
			}
			
		});
		
		//CheckBox Cerrado
		checkCerrado = new JCheckBox(ConstantsGestConvocatorias.tipoEstado[1].toString());
		checkCerrado.setBounds(294, 237, 128, 23);
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
		lbDocs.setBounds(28, 269, 171, 16);
		contentPanel.add(lbDocs);
		
		jComboTipo = new JComboBox<String>();
		jComboTipo.setBounds(28, 294, 171, 27);
		contentPanel.add(jComboTipo);
		jComboTipo.addItem(ConstantsGestConvocatorias.tipoDocs[0].toString());
		jComboTipo.addItem(ConstantsGestConvocatorias.tipoDocs[1].toString());
		jComboTipo.addItem(ConstantsGestConvocatorias.tipoDocs[2].toString());
		jComboTipo.addItem(ConstantsGestConvocatorias.tipoDocs[3].toString());
		jComboTipo.addItem(ConstantsGestConvocatorias.tipoDocs[4].toString());
		
		//Botón para añadir documentos desde el jComboTipo a listDocs
		btnAddDoc = new JButton(ConstantsGestConvocatorias.btnAddDocs);
		btnAddDoc.setBounds(28, 333, 68, 29);
		contentPanel.add(btnAddDoc);
		btnAddDoc.addActionListener(new InnerActionAltaConvocatorias());
		
		btnDelDoc = new JButton(ConstantsGestConvocatorias.btnDelDocs);
		btnDelDoc.setBounds(131, 333, 68, 29);
		contentPanel.add(btnDelDoc);
		btnDelDoc.addActionListener(new InnerActionAltaConvocatorias());
		
		//Scroll Tabla Documentos
		scrollDocs = new JScrollPane();
		scrollDocs.setBounds(226, 276, 196, 86);
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
			buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton(ConstantsGestConvocatorias.AltaBotonera[0].toString());
				okButton.setActionCommand(ConstantsGestConvocatorias.AltaBotonera[0].toString());
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(new InnerActionAltaConvocatorias());
			}
			{
				cancelButton = new JButton(ConstantsGestConvocatorias.AltaBotonera[1].toString());
				cancelButton.setActionCommand(ConstantsGestConvocatorias.AltaBotonera[1].toString());
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new InnerActionAltaConvocatorias());
			}
		}
		
		infoUsuariosConv(); //instanciamos método infoUsuariosConv()
	}
	
	/**
	 * The Class InnerActionAltaConvocatorias.
	 */
	public class InnerActionAltaConvocatorias implements ActionListener{

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
						&& txApertura.getText().length() !=0 && txCierre.getText().length() !=0 && tableDocs.getModel().getRowCount() != 0) {
					
					
					String apertura = txApertura.getText().toString();
					String cierre = txCierre.getText().toString();
					
					
					//Validaciones fechas
					if(conv.validaFecha(apertura) == false || conv.validaFecha(cierre) == false) {
						//Sí el campo de la fecha de inicio o fin no sigue el formato dd/mm/yyyy mostrará un mensaje de error
						JOptionPane.showMessageDialog(null,ConstantsMessage.msg18,ConstantsMessage.msg0,
													  JOptionPane.PLAIN_MESSAGE,imageError);
					}else if(conv.compruebaFecha(apertura, cierre) == false){
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
						
						//Añade nuevo evento en la BBDD
						cdao.addEvento(txIdConvocatoria.getText().toString(),
									   cbUserConv.getSelectedItem().toString(),
									   textDesc.getText().toString() ,
									   apertura, 
									   cierre, 
									   tipoEstadoDB(), 
									   docsValues);
			
						
						//Añade la convocatoria en la tabla de GestConvocatorias
						GestConvocatorias.addRowConvocatorias(new Object[] {txIdConvocatoria.getText().toString(),
																			cbUserConv.getSelectedItem().toString(),
																			textDesc.getText().toString(),
																			apertura,
																			cierre,
																			tipoEstadoTabla(),
																			docsValues});
						
						dispose(); //cierra pantalla
						restart(); //reinicia campos
					}
					
				
				}else {
					//Sí alguno de los campos anteriores está vacío mostramos el siguiente mensaje
					JOptionPane.showMessageDialog(null,ConstantsMessage.msg1,ConstantsMessage.msg0,
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
	 * The Class innerKeyAltaConv.
	 */
	public class innerKeyAltaConv implements KeyListener{

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
	 * Info usuarios conv.
	 */
	//Método para obtener los usuarios asociados a las convocatorias desde la consulta a la BBDD
	private void infoUsuariosConv() {
		String idConv = cbUserConv.getSelectedItem().toString();
		try {
			conn = ConDB.getConnection(ConstantsDB.server,ConstantsDB.user,ConstantsDB.pass);
			st = conn.createStatement();
			
			String queryInfoUsuarioConv = "SELECT tipoUsuario "
										   + "FROM usuarios "
										   + "WHERE idUsuario = '"+idConv+"' ";
			
			rs = st.executeQuery(queryInfoUsuarioConv);
			
			
			while(rs.next()) {
				txUserConv.setText(rs.getString(ConstantsDB.valueTipo));
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
			
	
	/**
	 * Restart.
	 */
	//Método para limpiar campos una vez dada de alta una nueva convocatoria
	public void restart() {
		//Reiniciamos componentes
		txIdConvocatoria.setText("");
		textDesc.setText("");
		txApertura.setText("");
		txCierre.setText("");
		jComboTipo.setSelectedIndex(0);
		
		//Limpiamos la tabla de todas las filas que puedan haber
		int rowsToRemove = model.getRowCount();
        for (int i = rowsToRemove - 1; i >= 0; i--) {
            model.removeRow(i);
        }
	}
}
