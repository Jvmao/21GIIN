/*
 * 11 ene 2022
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
import controlador.EventoImplDAO;
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
	private JTextField txIdConvocatoria,txApertura,txCierre;
	
	/** The lb docs. */
	private JLabel lbTitulo,lbIdConvocatoria,lbDesc,lbApertura,lbCierre,lbEstado,lbDocs;
	
	/** The btn del doc. */
	private JButton okButton,cancelButton,btnAddDoc,btnDelDoc;
	
	/** The text desc. */
	private JTextArea textDesc;
	
	/** The scroll docs. */
	private JScrollPane scrollDesc,scrollDocs;
	
	/** The check cerrado. */
	private JCheckBox checkAbierto,checkCerrado;
	
	/** The j combo tipo. */
	private JComboBox<String> jComboTipo;
	
	/** The model. */
	//Variables Tabla
	private DefaultTableModel model;
	
	/** The table docs. */
	private JTable tableDocs;
	
	/** The d cierre. */
	//Variables fechas
	private Date dApertura,dCierre;
	
	/** The image error. */
	//Recurso imagen
	private ImageIcon imageError = new ImageIcon(AltaConvocatorias.class.getResource(ConstantsMessage.imgError));
	
	
	/** The edao. */
	//Clase EventoImplDAO
	private EventoImplDAO edao = new EventoImplDAO();

	/**
	 * Instantiates a new alta convocatorias.
	 */

	public AltaConvocatorias() {
		setBounds(100, 100, 490, 450);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		//Label título
		lbTitulo = new JLabel(ConstantsGestConvocatorias.lbAltaTitulo);
		lbTitulo.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 16));
		lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitulo.setBounds(6, 10, 478, 20);
		contentPanel.add(lbTitulo);
		
		//Label idUsuario
		lbIdConvocatoria = new JLabel(ConstantsGestConvocatorias.labelIdConvocatorias);
		lbIdConvocatoria.setBounds(28, 53, 136, 16);
		contentPanel.add(lbIdConvocatoria);
		
		//TextField idUsuario
		txIdConvocatoria = new JTextField();
		txIdConvocatoria.setBounds(164, 48, 258, 26);
		contentPanel.add(txIdConvocatoria);
		txIdConvocatoria.setColumns(10);
		
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
		
		//Label Cierre
		lbCierre = new JLabel(ConstantsGestConvocatorias.labelCierre);
		lbCierre.setBounds(28, 213, 136, 16);
		contentPanel.add(lbCierre);
		
		//TextField fecha Cierre
		txCierre = new JTextField();
		txCierre.setBounds(164, 208, 130, 26);
		contentPanel.add(txCierre);
		txCierre.setColumns(10);
		
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
			JPanel buttonPane = new JPanel();
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
					
					SimpleDateFormat formatDates = new SimpleDateFormat("dd/MM/yyyy HH:mm");
					try {
						dApertura = formatDates.parse(apertura);
						dCierre = formatDates.parse(cierre);
						
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					
					//Validaciones fechas
					if(validationDateStart()==false || validationDateEnd() == false) {
						//Sí el campo de la fecha de inicio no sigue el formato dd/mm/yyyy mostrará un mensaje de error
						JOptionPane.showMessageDialog(null,ConstantsMessage.msg18,ConstantsMessage.msg0,
													  JOptionPane.PLAIN_MESSAGE,imageError);
					}else if(dApertura.compareTo(dCierre) >= 0){
						//Fecha de cierre no puede ser inferior a la fecha de apertura
						JOptionPane.showMessageDialog(null,ConstantsMessage.msg22,ConstantsMessage.msg0,
													  JOptionPane.PLAIN_MESSAGE,imageError);
					}else {
						//Valores tabla documentos
						ArrayList<String> docsValues = new ArrayList<String>();
						for (int i = 0; i < model.getRowCount(); i++){
						     docsValues.add(model.getValueAt(i, 0).toString());
						}
						
						//Añade nuevo evento en la BBDD
						edao.addEvento(txIdConvocatoria.getText().toString(),
									   textDesc.getText().toString() ,
									   apertura, 
									   cierre, 
									   tipoEstadoDB(), 
									   docsValues);
			
						
						//Añade la convocatoria en la tabla de GestConvocatorias
						GestConvocatorias.addRowConvocatorias(new Object[] {txIdConvocatoria.getText().toString(),
																			textDesc.getText().toString(),
																			changeDateStart(),
																			changeDateEnd(),
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
	 * Validation date start.
	 *
	 * @return true, if successful
	 */
	//Métodos para comprobar que las fechas de Apertura y Cierre se han introducido correctamente
	private boolean validationDateStart() {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        format.setLenient(false);

        String dateInit = txApertura.getText().toString();
        
        try {
			format.parse(dateInit);
			return true;
		} catch (ParseException e) {
			e.getMessage();
			txApertura.setText(""); //reiniciamos campo
			
		}
		return false;
	}
	
	/**
	 * Validation date end.
	 *
	 * @return true, if successful
	 */
	private boolean validationDateEnd() {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        format.setLenient(false);

        String dateFinal = txCierre.getText().toString();
        
        try {
			format.parse(dateFinal);
			return true;
		} catch (ParseException e) {
			e.getMessage();
			txCierre.setText(""); //reiniciamos campo
			return false;
		}
	}
	
	/**
	 * Change date start.
	 *
	 * @return the string
	 */
	//Cambiar formato fecha inicio en BBDD para evitar problemas de conversión
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
	//Cambiar formato fecha cierre en BBDD para evitar problemas de conversión
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
