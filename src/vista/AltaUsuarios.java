/*
 * 18 ene 2022
 * Jose V. Martí
 */
package vista;

import java.awt.FlowLayout;

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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import controlador.UsuariosImplDAO;
import util.ConstantsGestUsuarios;
import util.ConstantsMessage;

import java.awt.Font;

// TODO: Auto-generated Javadoc
/**
 * The Class AltaUsuarios.
 */

/**
 * The Class AltaUsuarios.
 */

public class AltaUsuarios extends JDialog {

	/** The content panel. */
	//Variables
	private final JPanel contentPanel = new JPanel();
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The tx pass. */
	private JTextField txIdUsuario,txPass;
	
	/** The lb pass. */
	private JLabel lbTitulo,lbIdUsuario,lbTipoUsuario,lbPass;
	
	/** The cb tipo usuario. */
	private JComboBox<String> cbTipoUsuario;
	
	/** The cancel button. */
	private JButton okButton,cancelButton;
	
	/** The udao. */
	//Llamada clase UsuariosImplDAO
	private UsuariosImplDAO udao = new UsuariosImplDAO();

	/** The image OK. */
	//Icono mensaje correcto
	ImageIcon imageOK = new ImageIcon(AltaUsuarios.class.getResource(ConstantsMessage.imgOK));
	
	/** The image error. */
	//Icono mensaje incorrecto
	ImageIcon imageError = new ImageIcon(AltaUsuarios.class.getResource(ConstantsMessage.imgError));
	
	/** The tipo usuarios. */
	//Variable listar datos tipo usuario desde arraylist
	private ArrayList<String> tipoUsuarios = new ArrayList<String>();



	/**
	 * Instantiates a new alta usuarios.
	 */
	public AltaUsuarios() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 450, 233);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		setResizable(false); //impedimos que la ventana se pueda ampliar para evitar problemas de tamaño de campos
		
		//Label título
		lbTitulo = new JLabel(ConstantsGestUsuarios.lbAltaTitulo);
		lbTitulo.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 16));
		lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitulo.setBounds(6, 10, 438, 20);
		contentPanel.add(lbTitulo);
		
		//Label idUsuario
		lbIdUsuario = new JLabel(ConstantsGestUsuarios.labelIdUsuario);
		lbIdUsuario.setBounds(28, 53, 95, 16);
		contentPanel.add(lbIdUsuario);
		
		//TextField idUsuario
		txIdUsuario = new JTextField();
		txIdUsuario.setBounds(135, 48, 204, 26);
		contentPanel.add(txIdUsuario);
		txIdUsuario.setColumns(10);
		
		//Label tipoUsuario
		lbTipoUsuario = new JLabel(ConstantsGestUsuarios.labelTipoUsuario);
		lbTipoUsuario.setBounds(28, 108, 95, 16);
		contentPanel.add(lbTipoUsuario);
		
		//ComboBox tipoUsuario
		cbTipoUsuario = new JComboBox<String>();
		cbTipoUsuario.setBounds(135, 104, 204, 27);
		contentPanel.add(cbTipoUsuario);
		ArrayList<?> m = udao.listaTipoUsuarios(tipoUsuarios); //pasamos el tipo de usuario desde la BBDD
		cbTipoUsuario.setModel(new DefaultComboBoxModel<String>(m.toArray(new String[0]))); //listamos valores en cbTipoUsuario
	
		
		//Label password
		lbPass = new JLabel(ConstantsGestUsuarios.labelPassUsuario);
		lbPass.setBounds(28, 167, 73, 16);
		contentPanel.add(lbPass);
		
		//TextField password
		txPass = new JTextField();
		txPass.setBounds(135, 162, 204, 26);
		contentPanel.add(txPass);
		txPass.setColumns(10);
		
		
		
		//Botones para Confirmar o Cancelar acción
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 233, 450, 39);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				//Botón confirmación acción
				okButton = new JButton(ConstantsGestUsuarios.AltaBotonera[0].toString());
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				okButton.addActionListener(new InnerActionAltaUsuarios());
				getRootPane().setDefaultButton(okButton);
			}
			{
				//Botón cancelar acción
				cancelButton = new JButton(ConstantsGestUsuarios.AltaBotonera[1].toString());
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new InnerActionAltaUsuarios());
				buttonPane.add(cancelButton);
			}
		}
	}
	

	/**
	 * The Class InnerActionAltaUsuarios.
	 */
	public class InnerActionAltaUsuarios implements ActionListener{

		/**
		 * Action performed.
		 *
		 * @param e the e
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==okButton) {
				
				//Comprobamos que los campos que tiene que rellenar el usuario no estén vacíos
				if(txIdUsuario.getText().length() != 0 && txPass.getText().length() != 0) {
					//Añadimos Usuario en la BBDD
					udao.addUser(txIdUsuario.getText(),cbTipoUsuario.getSelectedItem().toString() , 
							txPass.getText().toString());
					
					//Creamos un nuevo objeto para pasarlo a JTable de GestUsuarios
					GestUsuarios.addRowUsuarios(new Object[] {
						txIdUsuario.getText().toString(),
						cbTipoUsuario.getSelectedItem().toString(), 
						txPass.getText().toString()
					});
					
					dispose(); //Cuando finaliza la operación de inserción cierra la ventana
					restart(); //Cuando se inserta un nuevo usuario correctamente,limpiamos todos los campos
					
					JOptionPane.showMessageDialog(null,ConstantsMessage.msg7,ConstantsMessage.msg8,
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
	public void restart() {
		//Reiniciamos componentes
		txIdUsuario.setText("");
		cbTipoUsuario.setSelectedIndex(0); 
		txPass.setText("");
	}				
	

}
