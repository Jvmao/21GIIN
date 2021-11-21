package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import java.awt.Color;

public class Login extends JFrame {


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lbUser,lbIdUser,lbPass;
	private JButton btnStart,btnReset,btnSalir;
	private JComboBox<String> cbTipo,cbID;
	private JPasswordField passField;
	
	private ArrayList<String> tiposUsuarios = new ArrayList<String>();
	private ArrayList<String> numID = new ArrayList<String>();
	

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public Login() {
		setTitle("LOGIN");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Label Tipo Usuario
		lbUser = new JLabel("Tipo Usuario");
		lbUser.setBounds(6, 34, 88, 16);
		contentPane.add(lbUser);
		
		//ComboBox Tipos Usuarios
		cbTipo = new JComboBox<String>();
		
		tiposUsuarios.add("Administrador");
		tiposUsuarios.add("Cuentadante");
		tiposUsuarios.add("Fiscal");
		tiposUsuarios.add("Fiscal General");
		
		for (int i = 0; i < tiposUsuarios.size(); i++)
				    cbTipo.addItem(tiposUsuarios.get(i));
		
		cbTipo.setBounds(106, 30, 192, 27);
		contentPane.add(cbTipo);
		
		//Label ID Usuario
		lbIdUser = new JLabel("ID Usuario");
		lbIdUser.setBounds(6, 93, 88, 16);
		contentPane.add(lbIdUser);
		
		//ComboBox ID Usuario
		cbID = new JComboBox<String>();
		numID.add("1001");
		numID.add("1002");
		numID.add("1003");
		
		for (int i = 0; i < numID.size(); i++)
			cbID.addItem(numID.get(i));
		
		cbID.setBounds(106, 89, 192, 27);
		contentPane.add(cbID);
		
		//Label Password
		lbPass = new JLabel("Password");
		lbPass.setBounds(6, 146, 61, 16);
		contentPane.add(lbPass);
		
		//JPassword Password Usuario
		passField = new JPasswordField();
		passField.setBackground(new Color(224, 255, 255));
		passField.setBounds(106, 141, 192, 26);
		contentPane.add(passField);
		passField.addKeyListener(new InnerKey());
		
		//Botón Reset
		btnReset = new JButton("Reset");
		btnReset.setBounds(6, 237, 117, 29);
		contentPane.add(btnReset);
		btnReset.addActionListener(new InnerAction());
		
		//Botón Inicio
		btnStart = new JButton("Inicio");
		btnStart.setBounds(166, 237, 120, 29);
		contentPane.add(btnStart);
		btnStart.addActionListener(new InnerAction());
		
		//Botón Salir
		btnSalir = new JButton("Salir");
		btnSalir.setBounds(324, 237, 120, 29);
		contentPane.add(btnSalir);
		btnSalir.addActionListener(new InnerAction());//Agregamos el ActionListener desde la innerClass//
		
	}
	
	
	//Definimos los actionListener de los componentes desde la inner class
	public class InnerAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == btnReset) {
				//Reiniciamos componentes
				cbTipo.setSelectedIndex(0); 
				cbID.setSelectedIndex(0);
				passField.setText(""); 
			}
			
			if(e.getSource() == btnStart) {
				 dispose(); //Cerramos ventana Login
			     new MenuPrincipal().setVisible(true); //Abrimos ventana MenuPrincipal
				
			}

			if(e.getSource() == btnSalir) {
				System.exit(0);//Salimos de la Aplicación//
			}
			
		}
		
	}
	
	//Innerclass para los eventos de teclado
	public class InnerKey implements KeyListener{

		@Override
		public void keyTyped(KeyEvent e) {
			if(e.getSource() == passField) {
				//El campo passField solo admite 8 caracteres como máximo
				if(passField.getPassword().length == 8) e.consume();			
			}
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
		}
		
	}
}


