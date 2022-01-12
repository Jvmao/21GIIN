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

import controlador.UsuariosImplDAO;
import util.ConstantsGestUsuarios;
import util.ConstantsMessage;


// TODO: Auto-generated Javadoc
/**
 * The Class ModUsuarios.
 */

/**
 * The Class ModUsuarios.
 */

public class ModUsuarios extends JDialog {
	
	/** The content panel. */
	//Variables
	private final JPanel contentPanel = new JPanel();
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The tx pass. */
	private static JTextField txIdUsuario,txPass;
	
	/** The lb pass. */
	private JLabel lbTitulo,lbIdUsuario,lbTipoUsuario,lbPass;
	
	/** The cb tipo usuario. */
	private static JComboBox<String> cbTipoUsuario;
	
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
	
	/** The s. */
	private ArrayList<String> s = new ArrayList<String>();
	

	/**
	 * Instantiates a new mod usuarios.
	 */
	public ModUsuarios() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 450, 233);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		//Label título
		lbTitulo = new JLabel(ConstantsGestUsuarios.lbModTitulo);
		lbTitulo.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 16));
		lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitulo.setBounds(6, 13, 438, 20);
		contentPanel.add(lbTitulo);
		
		//Label idUsuario
		lbIdUsuario = new JLabel(ConstantsGestUsuarios.labelIdUsuario);
		lbIdUsuario.setBounds(36, 55, 66, 16);
		contentPanel.add(lbIdUsuario);
		
		//TextField idUsuario
		txIdUsuario = new JTextField();
		txIdUsuario.setBounds(138, 50, 185, 26);
		contentPanel.add(txIdUsuario);
		txIdUsuario.setColumns(10);
		
		//Label tipoUsuario
		lbTipoUsuario = new JLabel(ConstantsGestUsuarios.labelTipoUsuario);
		lbTipoUsuario.setBounds(36, 107, 80, 16);
		contentPanel.add(lbTipoUsuario);
		
		//ComboBox tipoUsuario
		cbTipoUsuario = new JComboBox<String>();
		cbTipoUsuario.setBounds(138, 103, 185, 27);
		contentPanel.add(cbTipoUsuario);
		ArrayList<?> m = udao.listaTipoUsuarios(s); //pasamos el tipo de usuario desde la BBDD
		cbTipoUsuario.setModel(new DefaultComboBoxModel<String>(m.toArray(new String[0])));
		
		//Label password
		lbPass = new JLabel(ConstantsGestUsuarios.labelPassUsuario);
		lbPass.setBounds(36, 160, 59, 16);
		contentPanel.add(lbPass);
		
		//TextField password
		txPass = new JTextField();
		txPass.setBounds(138, 155, 185, 26);
		contentPanel.add(txPass);
		txPass.setColumns(10);
		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 233, 450, 39);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				//Botón Confirmación acción
				okButton = new JButton(ConstantsGestUsuarios.ModBotonera[0].toString());
				okButton.setActionCommand("OK");
				okButton.addActionListener(new InnerActionModUsuaios());
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				
			}
			{
				//Botón Cancelar acción
				cancelButton = new JButton(ConstantsGestUsuarios.ModBotonera[1].toString());
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new InnerActionModUsuaios());
				buttonPane.add(cancelButton);
				
			}
		}
		
		
	}
	
	/**
	 * The Class InnerAction.
	 */
	//ActionListener Botones
	public class InnerActionModUsuaios implements ActionListener{

		/**
		 * Action performed.
		 *
		 * @param e the e
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==okButton) {
				if(txIdUsuario.getText().length() != 0 && txPass.getText().length() != 0) {
					//Actualizamos usuario en la BBDD con el metodo updateUser de UsuariosImplDAO
					udao.updateUser(txIdUsuario.getText().toString(),
									cbTipoUsuario.getSelectedItem().toString(),
									txPass.getText().toString());
					
					//Pasamos los valores modificados a la tabla de GestUsuarios
					GestUsuarios.setRowUsuarios(txIdUsuario.getText().toString(), 
										cbTipoUsuario.getSelectedItem().toString(), 
										txPass.getText().toString());
					
					dispose(); //Cuando finaliza la operación de inserción cierra la ventana
					
					//Mostramos el mensaje de confirmación de que la operación se ha realizado correctamente
					JOptionPane.showMessageDialog(null,ConstantsMessage.msg9,ConstantsMessage.msg8,
												  JOptionPane.PLAIN_MESSAGE,imageOK);
				}else {
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

	/**
	 * Gets the row usuarios.
	 *
	 * @param id   the id
	 * @param tipo the tipo
	 * @param pass the pass
	 */
	//Obtenemos los valores seleccionados en la Tabla de GestUsuarios
	//para mostrarlos inicialmente en esta pantalla
	public static void getRowUsuarios(String id,String tipo,String pass) {
		txIdUsuario.setText(id);
		cbTipoUsuario.setSelectedItem(tipo);
		txPass.setText(pass);
	}

}
