/*
 * 11 ene 2022
 * Jose V. Martí
 */
package vista;


import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import controlador.MunicipiosImplDAO;
import util.ConstantsGestMunicipios;
import util.ConstantsMessage;

// TODO: Auto-generated Javadoc
/**
 * The Class ModMunicipios.
 */

/**
 * The Class ModMunicipios.
 */
public class ModMunicipios extends JDialog {

	/** The content panel. */
	private final JPanel contentPanel = new JPanel();
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The tx tipo usuario. */
	private static JTextField txIdUsuario,txCat,txTipoUsuario;
	
	/** The lb id user. */
	private JLabel lbTitulo,lbIdUsuario,lbTipoUsuario,lbCat,lbIdUser;
	
	/** The cb id user. */
	private static JComboBox<String> cbIdUser;
	
	/** The cancel button. */
	private JButton okButton,cancelButton;

	/** The mdao. */
	//Llamada clase UsuariosImplDAO
	private MunicipiosImplDAO mdao = new MunicipiosImplDAO();

	/** The image OK. */
	//Icono mensaje correcto
	ImageIcon imageOK = new ImageIcon(AltaUsuarios.class.getResource(ConstantsMessage.imgOK));
	
	/** The image error. */
	//Recurso imagen
	ImageIcon imageError = new ImageIcon(AltaUsuarios.class.getResource(ConstantsMessage.imgError));

	/** The i D usuarios. */
	//Variable listar datos id usuario desde arraylist
	private ArrayList<String> iDUsuarios = new ArrayList<String>();
	

	/**
	 * Instantiates a new mod municipios.
	 */
	public ModMunicipios() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 450, 233);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		
		//Label título
		lbTitulo = new JLabel(ConstantsGestMunicipios.lbModTitulo);
		lbTitulo.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 16));
		lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitulo.setBounds(6, 6, 438, 20);
		contentPanel.add(lbTitulo);
		
		//Label idUsuario
		lbIdUsuario = new JLabel(ConstantsGestMunicipios.labelIdMunicipio);
		lbIdUsuario.setBounds(40, 60, 80, 16);
		contentPanel.add(lbIdUsuario);
		
		//TextField idUsuario
		txIdUsuario = new JTextField();
		txIdUsuario.setBounds(132, 55, 193, 26);
		contentPanel.add(txIdUsuario);
		txIdUsuario.setColumns(10);
		
		//Label categoria
		lbCat = new JLabel(ConstantsGestMunicipios.labelCatMunicipio);
		lbCat.setBounds(40, 109, 60, 16);
		contentPanel.add(lbCat);
		
		//TextField categoria
		txCat = new JTextField();
		txCat.setBounds(132, 104, 193, 26);
		contentPanel.add(txCat);
		txCat.setColumns(10);
		
		//Label ID Usuario
		lbIdUser = new JLabel(ConstantsGestMunicipios.labelIDUsuario);
		lbIdUser.setBounds(40, 151, 80, 16);
		contentPanel.add(lbIdUser);
		
		//ComboBox ID Usuario
		cbIdUser = new JComboBox<String>();
		cbIdUser.setBounds(132, 147, 193, 27);
		contentPanel.add(cbIdUser);
		ArrayList<?> m = mdao.listaIdUsuariosMunicipios(iDUsuarios); //pasamos el tipo de usuario desde la BBDD
		cbIdUser.setModel(new DefaultComboBoxModel<String>(m.toArray(new String[0]))); //listamos valores en cbIdUser
		
		//Label tipoUsuario
		lbTipoUsuario = new JLabel(ConstantsGestMunicipios.labelTipoUsuario);
		lbTipoUsuario.setBounds(40, 190, 80, 16);
		contentPanel.add(lbTipoUsuario);
		
		//Tipo usuario solo puede ser cuentadante en este caso
		txTipoUsuario = new JTextField();
		txTipoUsuario.setEditable(false);
		txTipoUsuario.setBounds(132, 186, 193, 26);
		contentPanel.add(txTipoUsuario);
		txTipoUsuario.setColumns(10);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 233, 450, 39);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				okButton = new JButton(ConstantsGestMunicipios.ModBotonera[0].toString());
				okButton.setActionCommand(ConstantsGestMunicipios.ModBotonera[0].toString());
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(new InnerActionModMunicipios());
			}
			{
				cancelButton = new JButton(ConstantsGestMunicipios.ModBotonera[1].toString());
				cancelButton.setActionCommand(ConstantsGestMunicipios.ModBotonera[1].toString());
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new InnerActionModMunicipios());
			}
		}
	}
	
	/**
	 * The Class InnerActionModMunicipios.
	 */
	//ActionListener Botones
	public class InnerActionModMunicipios implements ActionListener{

		/**
		 * Action performed.
		 *
		 * @param e the e
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==okButton) {
				if(txIdUsuario.getText().length() != 0 && txCat.getText().length() != 0) {
					//Actualizamos municipio en la BBDD con el metodo updateMunicipio de MunicipiosImplDAO
					mdao.updateMunicipio(txIdUsuario.getText(),
										 Integer.parseInt(txCat.getText()),
										 cbIdUser.getSelectedItem().toString(),
										 txTipoUsuario.getText().toString());
					
					//Pasamos los valores modificados a la tabla de ModUsuarios
					GestMunicipios.setRowMunicipios(txIdUsuario.getText(),
													Integer.parseInt(txCat.getText()),
													cbIdUser.getSelectedItem().toString(),
													txTipoUsuario.getText().toString());
					
					
					dispose(); //Cuando finaliza la operación de modificación cierra la ventana
					
					//Mostramos el mensaje de confirmación de que la operación se ha realizado correctamente
					JOptionPane.showMessageDialog(null,ConstantsMessage.msg13,ConstantsMessage.msg8,
												  JOptionPane.PLAIN_MESSAGE,imageOK);
				}else{
					//Sí alguno de los campos anteriores está vacío mostramos el siguiente mensaje error
					JOptionPane.showMessageDialog(null,ConstantsMessage.msg1,ConstantsMessage.msg0,
												  JOptionPane.PLAIN_MESSAGE,imageError);
				}
			}
			
			
			if(e.getSource()==cancelButton) {
				dispose(); //Cerramos Ventana
			}
			
		}
		
	}
	
	//Obtenemos los valores seleccionados en la Tabla de GestUsuarios 
	/**
	 * Gets the row municipios.
	 *
	 * @param id        the id
	 * @param categoria the categoria
	 * @param iduser    the iduser
	 * @param tipo      the tipo
	 */
	//para mostrarlos inicialmente en esta pantalla
	public static void getRowMunicipios(String id,int categoria,String iduser,String tipo) {
		txIdUsuario.setText(id);
		txCat.setText(String.valueOf(categoria));
		cbIdUser.setSelectedItem(iduser);
		txTipoUsuario.setText(tipo);
	}

}
