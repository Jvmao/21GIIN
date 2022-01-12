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
 * The Class AltaMunicipios.
 */

/**
 * The Class AltaMunicipios.
 */

public class AltaMunicipios extends JDialog {

	/** The content panel. */
	private final JPanel contentPanel = new JPanel();
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The tx tipo usuario. */
	private JTextField txIdUsuario,txCat,txTipoUsuario;
	
	/** The lb id usuario. */
	private JLabel lbTitulo,lbIdMunicipio,lbTipoUsuario,lbCategoria,lbIdUsuario;
	
	/** The cb id usuario. */
	private JComboBox<String> cbIdUsuario;
	
	/** The cancel button. */
	private JButton okButton,cancelButton;

	/** The mdao. */
	//Llamada clase UsuariosImplDAO
	private MunicipiosImplDAO mdao = new MunicipiosImplDAO();
	
	/** The i D usuarios. */
	//Variable listar datos id usuario desde arraylist
	private ArrayList<String> iDUsuarios = new ArrayList<String>();

	/** The image OK. */
	//Icono mensaje correcto
	ImageIcon imageOK = new ImageIcon(AltaUsuarios.class.getResource(ConstantsMessage.imgOK));
	
	/** The image error. */
	//Icono mensaje incorrecto
	ImageIcon imageError = new ImageIcon(AltaUsuarios.class.getResource(ConstantsMessage.imgError));

	/**
	 * Instantiates a new alta municipios.
	 */
	public AltaMunicipios() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 450, 233);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		
		//Label título
		lbTitulo = new JLabel(ConstantsGestMunicipios.lbAltaTitulo);
		lbTitulo.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 16));
		lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitulo.setBounds(6, 6, 438, 20);
		contentPanel.add(lbTitulo);
		
		//Label idMunicipios
		lbIdMunicipio = new JLabel(ConstantsGestMunicipios.labelIdMunicipio);
		lbIdMunicipio.setBounds(56, 46, 80, 16);
		contentPanel.add(lbIdMunicipio);
		
		//TextField idMunicipios
		txIdUsuario = new JTextField();
		txIdUsuario.setBounds(155, 41, 164, 26);
		contentPanel.add(txIdUsuario);
		txIdUsuario.setColumns(10);
		
		//Label categoria
		lbCategoria = new JLabel(ConstantsGestMunicipios.labelCatMunicipio);
		lbCategoria.setBounds(56, 94, 80, 16);
		contentPanel.add(lbCategoria);
		
		//TextField categoria
		txCat = new JTextField();
		txCat.setBounds(155, 89, 164, 26);
		contentPanel.add(txCat);
		txCat.setColumns(10);
		
		//Label ID Usuario
		lbIdUsuario = new JLabel(ConstantsGestMunicipios.labelIDUsuario);
		lbIdUsuario.setBounds(56, 142, 80, 16);
		contentPanel.add(lbIdUsuario);
		
		//ComboBox ID Usuario Cuentadante
		cbIdUsuario = new JComboBox<String>();
		cbIdUsuario.setBounds(155, 137, 164, 27);
		contentPanel.add(cbIdUsuario);
		ArrayList<?> m = mdao.listaIdUsuariosMunicipios(iDUsuarios); //pasamos el tipo de usuario desde la BBDD
		cbIdUsuario.setModel(new DefaultComboBoxModel<String>(m.toArray(new String[0]))); //listamos valores en cbIdUsuario
		
		//Label tipoUsuario
		lbTipoUsuario = new JLabel(ConstantsGestMunicipios.labelTipoUsuario);
		lbTipoUsuario.setBounds(56, 190, 80, 16);
		contentPanel.add(lbTipoUsuario);
		
		//Tipo Usuario siempre debe de ser Cuentadante
		txTipoUsuario = new JTextField(ConstantsGestMunicipios.txTipoUsuarioMunicipios);
		txTipoUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		txTipoUsuario.setEditable(false);
		txTipoUsuario.setBounds(155, 185, 164, 26);
		contentPanel.add(txTipoUsuario);
		txTipoUsuario.setColumns(10);
		

		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 233, 450, 39);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				okButton = new JButton(ConstantsGestMunicipios.AltaBotonera[0].toString());
				okButton.setActionCommand(ConstantsGestMunicipios.AltaBotonera[0].toString());
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(new InnerActionAltaMunicipios());
			}
			{
				cancelButton = new JButton(ConstantsGestMunicipios.AltaBotonera[1].toString());
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new InnerActionAltaMunicipios());
			}
		}
	}
	
	/**
	 * The Class InnerActionAltaMunicipios.
	 */
	public class InnerActionAltaMunicipios implements ActionListener{

		/**
		 * Action performed.
		 *
		 * @param e the e
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==okButton) {
				if(txIdUsuario.getText().length() != 0 && txCat.getText().length() != 0) {
					//Añadimos un nuevo Municipio en la BBDD mediante el método addMunicipio de la clase MunicipiosImplDAO
					mdao.addMunicipio(txIdUsuario.getText(),
									  Integer.parseInt(txCat.getText()),
									  cbIdUsuario.getSelectedItem().toString(),
									  txTipoUsuario.getText());
					
					//Creamos un nuevo objeto para pasarlo a JTable de GestUsuarios
					GestMunicipios.addRowMunicipios(new Object[] {
						txIdUsuario.getText().toString(),
						Integer.parseInt(txCat.getText()),
						cbIdUsuario.getSelectedItem().toString(),
						txTipoUsuario.getText().toString()
					});
					
					dispose(); //Cuando finaliza la operación de inserción cierra la ventana
					restart(); //Cuando se inserta un nuevo usuario correctamente,limpiamos todos los campos
					
					JOptionPane.showMessageDialog(null,ConstantsMessage.msg12,ConstantsMessage.msg8,
												  JOptionPane.PLAIN_MESSAGE,imageOK);
					
				}else {
					//Sí alguno de los campos anteriores está vacío mostramos el siguiente mensaje
					JOptionPane.showMessageDialog(null,ConstantsMessage.msg1,ConstantsMessage.msg0,
												  JOptionPane.PLAIN_MESSAGE,imageError);
				}
			}
			
			if(e.getSource()==cancelButton) {
				dispose(); //Cerramos Ventana
				restart(); //Limpiamos todo los campos
			}
		
		}
		
	}
	
	/**
	 * Restart.
	 */
	//Método para reiniciar los campos seleccionados
	public void restart() {
		//Reiniciamos componentes
		txIdUsuario.setText("");
		txCat.setText("");
	}
}
