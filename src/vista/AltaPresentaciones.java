/*
 * 23 ene 2022
 * Jose V. Martí
 */
package vista;


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import controlador.ConvImplDAO;
import controlador.PresentacionImplDAO;
import controlador.UsuariosImplDAO;
import modelo.Presentaciones;
import util.ConstantsGestPresentaciones;
import util.ConstantsMessage;


/**
 * The Class AltaPresentaciones.
 */
public class AltaPresentaciones extends JDialog {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The content panel. */
	private final JPanel contentPanel = new JPanel();
	
	/** The lb docs. */
	private JLabel lbTitulo,lbIDConv,lbIdPres,lbInicioConv,lbFinConv,lbUsuario,lbFechaPres,lbEstado,lbDocs;
	
	/** The tx tipo usuario. */
	private JTextField textIdPres,txInicioConv,txFinConv,txFechaPres,txTipoUsuario;
	
	/** The check cerrado. */
	private JCheckBox checkAbierto,checkCerrado;
	
	/** The cb id usuario. */
	private JComboBox<String> cbIDConv,jComboTipo,cbIdUsuario;
	
	/** The cancel button. */
	private JButton btnAddDoc,btnDelDoc,okButton,cancelButton;
	
	/** The scroll docs. */
	private JScrollPane scrollDocs;
	
	/** The model docs. */
	private DefaultTableModel modelDocs;
	
	/** The table docs. */
	private JTable tableDocs;
	
	/** The image OK. */
	//Icono mensaje correcto
	ImageIcon imageOK = new ImageIcon(AltaUsuarios.class.getResource(ConstantsMessage.imgOK));
	
	/** The image error. */
	//Imagen Error Mensaje
	private ImageIcon imageError = new ImageIcon(GestPresentaciones.class.getResource(ConstantsMessage.imgError));
	
	
	/** The udao. */
	//Llamada clase UsuariosImplDAO
	private UsuariosImplDAO udao = new UsuariosImplDAO();
	//private MunicipiosImplDAO mdao = new MunicipiosImplDAO();

	
	/** The pdao. */
	//Llamada clase PresentacionImplDAO
	private PresentacionImplDAO pdao = new PresentacionImplDAO();
	
	/** The cdao. */
	//Llamada clase EventoImplDAO
	private ConvImplDAO cdao = new ConvImplDAO();
	
	/** The p. */
	private Presentaciones p = new Presentaciones();


	/**
	 * Instantiates a new alta presentaciones.
	 */
	public AltaPresentaciones() {
		setBounds(100, 100, 450, 399);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 0, 0);
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		setResizable(false); //impedimos que la ventana se pueda ampliar para evitar problemas de tamaño de campos
		
		
		//Label Título
		lbTitulo = new JLabel(ConstantsGestPresentaciones.labelTituloAltaPres);
		lbTitulo.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
		lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitulo.setBounds(0, 0, 450, 27);
		getContentPane().add(lbTitulo);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(null);
		
		//Label Id Convocatoria
		lbIDConv = new JLabel(ConstantsGestPresentaciones.labelIdConv);
		getContentPane().add(lbIDConv);
		lbIDConv.setBounds(16, 33, 135, 16);
		
		//Combobox Id Convocatoria
		cbIDConv = new JComboBox<String>();
		getContentPane().add(cbIDConv);
		cbIDConv.setBounds(6, 53, 145, 27);
		//ArrayList<?> s = cdao.listaIDConvocatoria(iDConv); 
		//pasamos el id de convocatorias desde la BBDD y listamos valores en cbIDConv
		cbIDConv.setModel(new DefaultComboBoxModel<String>(cdao.listaIDConvocatoria().toArray(new String[0]))); 
		
		//Listener combobox Id Convocatorias para actualizar los datos cuando se selecciona otro item distinto
		cbIDConv.addActionListener(new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent e) {
				//fechasConvocatoria();
				txInicioConv.setText(pdao.fechasConvInicio(cbIDConv.getSelectedItem().toString()));
				txFinConv.setText(pdao.fechaConvFin(cbIDConv.getSelectedItem().toString()));
			}
			
		});
		
		//Label Fecha Inicio convocatoria
		lbInicioConv = new JLabel(ConstantsGestPresentaciones.labelIniConv);
		getContentPane().add(lbInicioConv);
		lbInicioConv.setBounds(163, 33, 128, 16);
		
		//Campo Fecha Inicio Convocatoria
		txInicioConv = new JTextField();
		txInicioConv.setEditable(false);
		getContentPane().add(txInicioConv);
		txInicioConv.setBounds(161, 52, 130, 26);
		txInicioConv.setColumns(10);
		
		//Label Fecha Fin convocatoria
		lbFinConv = new JLabel(ConstantsGestPresentaciones.labelFinConv);
		getContentPane().add(lbFinConv);
		lbFinConv.setBounds(295, 33, 126, 16);
		
		//Campo Fin Convocatoria
		txFinConv = new JTextField();
		txFinConv.setEditable(false);
		getContentPane().add(txFinConv);
		txFinConv.setBounds(291, 52, 130, 26);
		txFinConv.setColumns(10);
		
		
		//Label Id Presentaciones
		lbIdPres = new JLabel(ConstantsGestPresentaciones.labelIdPres);
		lbIdPres.setBounds(16, 126, 135, 16);
		getContentPane().add(lbIdPres);
		
		//Campo IdPres
		textIdPres = new JTextField();
		textIdPres.setBounds(163, 121, 258, 26);
		getContentPane().add(textIdPres);
		textIdPres.setColumns(10);
		
		//Label Usuario
		lbUsuario = new JLabel(ConstantsGestPresentaciones.labelUsuario);
		lbUsuario.setBounds(16, 91, 135, 16);
		getContentPane().add(lbUsuario);
		
		//ComboBox ID Usuario Cuentadante
		cbIdUsuario = new JComboBox<String>();
		getContentPane().add(cbIdUsuario);
		cbIdUsuario.setBounds(161, 87, 130, 27);
		//ArrayList<?> m = udao.listaIdUserConv(iDUsuarios); //pasamos el tipo de usuario desde la BBDD
		cbIdUsuario.setModel(new DefaultComboBoxModel<String>(udao.listaIdUserConv().toArray(new String[0]))); //listamos valores en cbIdUsuario
		
		//Listener combobox Id Convocatorias para actualizar los datos cuando se selecciona otro item distinto
		cbIdUsuario.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//infoUsuariosPres();
				txTipoUsuario.setText(cdao.infoUsuariosConv(cbIdUsuario.getSelectedItem().toString())); 
			}
			
		});
		
		//JText tipo de usuario cuentadante
		txTipoUsuario = new JTextField(ConstantsGestPresentaciones.txTipoUsuarioPres);
		txTipoUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		txTipoUsuario.setBounds(290, 86, 131, 26);
		txTipoUsuario.setEditable(false);
		getContentPane().add(txTipoUsuario);
		txTipoUsuario.setColumns(10);
		
		//Label Fecha Presentación
		lbFechaPres = new JLabel(ConstantsGestPresentaciones.labelFechaPres);
		lbFechaPres.setBounds(16, 164, 135, 16);
		getContentPane().add(lbFechaPres);
		
		//Fecha Presentación Timestamp
		txFechaPres = new JTextField();
		txFechaPres.setHorizontalAlignment(SwingConstants.CENTER);
		txFechaPres.setBounds(163, 159, 258, 26);
		getContentPane().add(txFechaPres);
		txFechaPres.setColumns(10);
		txFechaPres.addKeyListener(new innerKeyAltaPres());
		toTimeStamp(); //mostramos por defecto la fecha del sistema en formato timestamp
		
		//label estado
		lbEstado = new JLabel(ConstantsGestPresentaciones.labelEstado);
		lbEstado.setBounds(16, 197, 135, 16);
		getContentPane().add(lbEstado);
		
		//Check estado abierto
		checkAbierto = new JCheckBox(ConstantsGestPresentaciones.labelTipoEstado[0]);
		checkAbierto.setSelected(true);
		checkAbierto.setBounds(173, 193, 84, 23);
		getContentPane().add(checkAbierto);
		checkAbierto.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(checkAbierto.isSelected()==true) {
					checkCerrado.setSelected(false);
				}
			}
			
		});
		
		//Check estado cerrado
		checkCerrado = new JCheckBox(ConstantsGestPresentaciones.labelTipoEstado[1]);
		checkCerrado.setBounds(316, 193, 105, 23);
		getContentPane().add(checkCerrado);
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
		lbDocs.setBounds(16, 228, 149, 16);
		getContentPane().add(lbDocs);
		
		//Tipo de Documentos
		jComboTipo = new JComboBox<String>();
		getContentPane().add(jComboTipo);
		jComboTipo.setBounds(6, 250, 163, 27);
		jComboTipo.addItem(ConstantsGestPresentaciones.tipoDocs[0].toString());
		jComboTipo.addItem(ConstantsGestPresentaciones.tipoDocs[1].toString());
		jComboTipo.addItem(ConstantsGestPresentaciones.tipoDocs[2].toString());
		jComboTipo.addItem(ConstantsGestPresentaciones.tipoDocs[3].toString());
		jComboTipo.addItem(ConstantsGestPresentaciones.tipoDocs[4].toString());
		
		//Botón para añadir documentos desde el jComboTipo a listDocs
		btnAddDoc = new JButton(ConstantsGestPresentaciones.btnAddDocs);
		getContentPane().add(btnAddDoc);
		btnAddDoc.setBounds(16, 289, 68, 29);
		btnAddDoc.addActionListener(new InnerActionAltaPre());
		
		//Botón para eliminar documentos desde el jComboTipo a listDocs
		btnDelDoc = new JButton(ConstantsGestPresentaciones.btnDelDocs);
		getContentPane().add(btnDelDoc);
		btnDelDoc.setBounds(83, 289, 68, 29);
		btnDelDoc.addActionListener(new InnerActionAltaPre());
		
		//Scroll Tabla Documentos
		scrollDocs = new JScrollPane();
		getContentPane().add(scrollDocs);
		scrollDocs.setBounds(173, 227, 248, 91);
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
			buttonPane.setBounds(0, 330, 450, 39);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				okButton = new JButton(ConstantsGestPresentaciones.altaBotoneraPres[0]);
				okButton.setActionCommand(ConstantsGestPresentaciones.altaBotoneraPres[0]);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(new InnerActionAltaPre());
			}
			{
				cancelButton = new JButton(ConstantsGestPresentaciones.altaBotoneraPres[1]);
				cancelButton.setActionCommand(ConstantsGestPresentaciones.altaBotoneraPres[1]);
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new InnerActionAltaPre());
				
			}
		}
		
		//Añadimos los métodos desde PresentacionesImlDAO
		txInicioConv.setText(pdao.fechasConvInicio(cbIDConv.getSelectedItem().toString()));
		txFinConv.setText(pdao.fechaConvFin(cbIDConv.getSelectedItem().toString()));

		
		//instanciamos método infoUsuariosPres() desde la clase PresentacionImlDAO
		txTipoUsuario.setText(cdao.infoUsuariosConv(cbIdUsuario.getSelectedItem().toString())); 

	}
	
	/**
	 * The Class InnerActionAltaPre.
	 */
	public class InnerActionAltaPre implements ActionListener{

		/**
		 * Action performed.
		 *
		 * @param e the e
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==btnAddDoc) {
				String tipoDoc = jComboTipo.getSelectedItem().toString();
				
				/**for (int i = 0; i < modelDocs.getRowCount(); i++) {
					if(tipoDoc.equals(modelDocs.getValueAt(i, 0).toString()) ) {
						//Si no selecciona un elementos repetido se muestra el siguiente mensaje
						JOptionPane.showMessageDialog(null,ConstantsMessage.msg26,ConstantsMessage.msg0,
								  JOptionPane.PLAIN_MESSAGE,imageError);
					}
				}**/
				
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
			
			if(e.getSource()==okButton) {
				String idPres = textIdPres.getText().toString();
				String idConv = cbIDConv.getSelectedItem().toString();
				String idUser = cbIdUsuario.getSelectedItem().toString();
				String fechaInitConv = txInicioConv.getText().toString();
				String fechaPres = txFechaPres.getText().toString();
				
				//Valores tabla documentos
				ArrayList<String> docsValues = new ArrayList<String>();
				for (int i = 0; i < modelDocs.getRowCount(); i++){
				     docsValues.add(modelDocs.getValueAt(i, 0).toString());
				}
				
				if(p.validaFecha(fechaPres) == false){
					//Sí el formato de fecha es incorrecto mostramos el siguiente mensaje
					JOptionPane.showMessageDialog(null,ConstantsMessage.msg23,ConstantsMessage.msg0,
												  JOptionPane.PLAIN_MESSAGE,imageError);
					
				}else if(p.compruebaFecha(fechaInitConv, fechaPres) == false){
					//Fecha de presentación no puede ser inferior a la fecha de convocatoria
					JOptionPane.showMessageDialog(null,ConstantsMessage.msg25,ConstantsMessage.msg0,
												  JOptionPane.PLAIN_MESSAGE,
												  imageError);
					
				}else if(idPres.length() == 0 || docsValues.isEmpty()) {
					//Sí alguno de los campos anteriores está vacío mostramos el siguiente mensaje
					JOptionPane.showMessageDialog(null,ConstantsMessage.msg1,ConstantsMessage.msg0,
												  JOptionPane.PLAIN_MESSAGE,imageError);
				}else {
					pdao.addPresentacion(idPres,idConv,idUser, fechaPres, tipoEstadoDB(), docsValues); //Añadimos presentación en la BBDD

					//Añadimos los valores actualizados en la tabla de GestPresentaciones
					GestPresentaciones.addRowPres(new Object[] {
							idPres,
							idConv,
							idUser,
							fechaPres,
							tipoEstadoTabla(),
							docsValues
					});
					
					dispose(); //Cerramos Ventana
					restart(); //reinicia campos
					
					//Mensaje operación OK
					JOptionPane.showMessageDialog(null,ConstantsMessage.msg29,ConstantsMessage.msg8,
							JOptionPane.PLAIN_MESSAGE,imageOK);
				}
				
			}
			
			if(e.getSource()==cancelButton) {
				dispose(); //Cerramos Ventana
				restart(); //reinicia campos
			}
			
		}
		
	}
	
	/**
	 * The Class innerKeyAltaPres.
	 */
	public class innerKeyAltaPres implements KeyListener{

		/**
		 * Key typed.
		 *
		 * @param e the e
		 */
		@Override
		public void keyTyped(KeyEvent e) {
			if(e.getSource() == txFechaPres) {
				//El campo txFechaPres solo admite 16 caracteres como máximo (01/01/2022 09:00) = 16 carácteres
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
	 * Convertimos la fecha a mostrar al formato dd/MM/yyyy HH:mm
	 * 
	 * To time stamp.
	 */
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
	 * Método para devolver un valor boolean a la columna estado en la BBDD y evitar problemas de conversión 
	 * 
	 * Tipo estado DB.
	 *
	 * @return true, if successful
	 */
	private boolean tipoEstadoDB() {
		if(checkAbierto.isSelected()==false) {
			return false;
		}
		return true;
	}
	
	/**
	 * Devuelve un tipo numérico a la tabla en lugar de false o true
	 * 
	 * Tipo estado tabla.
	 *
	 * @return the int
	 */
	public int tipoEstadoTabla() {
			if(checkAbierto.isSelected()==false) {
				return 0;
			}
			return 1;
	}

	
	/**
	 * Reiniciamos componentes
	 * 
	 * Restart.
	 */
	public void restart() {
		//Reiniciamos componentes
		toTimeStamp();
		checkAbierto.setSelected(true);
		checkCerrado.setSelected(false);
		
		
		//Limpiamos la tabla de todas las filas que pueda contener
		int rowsToRemove = modelDocs.getRowCount();
        for (int i = rowsToRemove - 1; i >= 0; i--) {
        	modelDocs.removeRow(i);
        }
	}
}
