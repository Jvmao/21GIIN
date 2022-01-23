/*
 * 23 ene 2022
 * Jose V. Martí
 */
package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.util.Arrays;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controlador.ConDB;
import controlador.UsuariosImplDAO;
import modelo.Usuarios;
import util.ConstantsDB;
import util.ConstantsLogin;
import util.ConstantsMessage;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;


/**
 * The Class Login.
 */
public class Login extends JFrame {

	/** The Constant serialVersionUID. */
	//Declaración componentes
	private static final long serialVersionUID = 1L;
	
	/** The content pane. */
	private JPanel contentPane;
	
	/** The lb pass. */
	private JLabel lbUser,lbIdUser,lbPass;
	
	/** The btn salir. */
	private JButton btnStart,btnReset,btnSalir;
	
	/** The cb ID. */
	private JComboBox<String> cbTipo,cbID;
	
	/** The tx pass. */
	private JTextField txPass;

	/** The udao. */
	//Llamada clase UsuariosImplDAO
	private UsuariosImplDAO udao = new UsuariosImplDAO();

	/** The u. */
	//Instanciamos el objeto Usuarios y MenuPrincipal
	private Usuarios u = new Usuarios();
	
	/** The mn. */
	private MenuPrincipal mn = new MenuPrincipal();
	
	
	/** The id user. */
	public static String TipoUsuario,idUser;

	/** The conn. */
	//Variables Conexión
	private Connection conn;
	
	
	/**
	 * Instantiates a new login.
	 */
	/**public static void main(String[] args) {
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
	}**/

	/**
	 * Instantiates a new login.
	 */
	public Login() {
		initComponents();
	}
	
	
	/**
	 * Inits the components.
	 */
	public void initComponents() {
		setTitle(ConstantsLogin.labelTitulo);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false); //impedimos que la ventana se pueda ampliar para evitar problemas de tamaño de campos
		
		//Label Tipo Usuario
		lbUser = new JLabel(ConstantsLogin.labelTipoUsuario);
		lbUser.setBounds(6, 34, 88, 16);
		contentPane.add(lbUser);
		
		//ComboBox Tipos Usuarios
		cbTipo = new JComboBox<String>();
		cbTipo.setBounds(106, 30, 192, 27);
		contentPane.add(cbTipo);
		
		//Label ID Usuario
		lbIdUser = new JLabel(ConstantsLogin.labelIdUsuario);
		lbIdUser.setBounds(6, 93, 88, 16);
		contentPane.add(lbIdUser);
		
		//ComboBox ID Usuario
		cbID = new JComboBox<String>();
		cbID.setBounds(106, 89, 192, 27);
		contentPane.add(cbID);
		
		//Label Password
		lbPass = new JLabel(ConstantsLogin.labelPassUsuario);
		lbPass.setBounds(6, 146, 61, 16);
		contentPane.add(lbPass);
		
		//Campo Password Usuario
		txPass = new JTextField();
		txPass.setBounds(106, 141, 192, 26);
		contentPane.add(txPass);
		txPass.setColumns(10);
		txPass.addKeyListener(new innerKeyLogin());
		
		//Botón Reset
		btnReset = new JButton(ConstantsLogin.tituloBotonera[0].toString());
		btnReset.setBounds(6, 237, 117, 29);
		contentPane.add(btnReset);
		btnReset.addActionListener(new InnerAction());
		
		//Botón Inicio
		btnStart = new JButton(ConstantsLogin.tituloBotonera[1].toString());
		btnStart.setBounds(166, 237, 120, 29);
		contentPane.add(btnStart);
		btnStart.addActionListener(new InnerAction());
		
		//Botón Salir
		btnSalir = new JButton(ConstantsLogin.tituloBotonera[2].toString());
		btnSalir.setBounds(324, 237, 120, 29);
		contentPane.add(btnSalir);
		btnSalir.addActionListener(new InnerAction());//Agregamos el ActionListener desde la innerClass//

		//pasamos el tipo de usuario desde la BBDD
		cbTipo.setModel(new DefaultComboBoxModel<String>(udao.listaTipoUsuarios().toArray(new String[0])));

		//pasamos el ID de usuario desde la BBDD y Llamamos al método para listar el ID usuario en comboBox	
		cbID.setModel(new DefaultComboBoxModel<String>(udao.listaIdUsuarios().toArray(new String[0])));
		
		if(conn == null) {
			ConDB.getConnection(ConstantsDB.server,ConstantsDB.user,ConstantsDB.pass); //Conexión BBDD
			System.out.println("Aplicación Iniciada");
		}else {
			System.out.println("No es posible inciar la aplicación");
		}


			
	}
	
	
	/**
	 * The Class InnerAction.
	 */
	public class InnerAction implements ActionListener{

		/**
		 * Action performed.
		 *
		 * @param e the e
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			
			//ActionListener Botón Reset
			if(e.getSource() == btnReset) {
				//Reiniciamos componentes
				cbTipo.setSelectedIndex(0); 
				cbID.setSelectedIndex(0);
				txPass.setText("");
			}
			
			//ActionListener Botón Inicio
			if(e.getSource() == btnStart) {
				
				if (txPass.getText().length() < 3) { //el password no puede ser menor a 3 carácteres
					JOptionPane.showMessageDialog(null,ConstantsMessage.msg5,ConstantsMessage.msg0, JOptionPane.ERROR_MESSAGE); 
					restart(); //Reiniciamos campos
					
				}else {
					try {
						//Declaramos las variables
						String idUsuario = cbID.getSelectedItem().toString();
						String tipoUsuario = cbTipo.getSelectedItem().toString();
						String passUsuario = txPass.getText().toString();
						List<Object> list = Arrays.asList(cbTipo.getSelectedItem()); 

						//Llamamos al método checkuser de UsuariosImplDAO pra comprobar las credenciales desde la BBDD
						if(udao.checkUser(idUsuario, tipoUsuario, passUsuario) == true) {
							//Guardamos los valores del objeto Usuarios y lo mostramos por pantalla
						    u.setIdUsuario(cbID.getSelectedItem().toString());
						    u.setTipoUsuario(list);
						    u.setPassUsuario(txPass.getText());
						    System.out.println(u.toString());
						    
						    //Pasamos los valores estáticos
						    idUser = cbID.getSelectedItem().toString();
						    TipoUsuario = cbTipo.getSelectedItem().toString();
						    System.out.println("ID Usuario: "+idUser+" Tipo Usuario Login: "+TipoUsuario);
						    
						    mn.setVisible(true); //Abrimos ventana MenuPrincipal
						    dispose(); //Cerramos ventana Login
						    System.out.println("Datos Correctos");
						    
						}else {
							restart(); //Reiniciamos campos
						}
					} catch (NullPointerException ex) {
						ex.getMessage();
					}
				}
			
				
			}

			//ActionListener Botón Salir
			if(e.getSource() == btnSalir) {
				System.exit(0);//Salimos de la Aplicación//
			}
			
		}
		
	}
	
	/**
	 * The Class innerKeyLogin.
	 */
	public class innerKeyLogin implements KeyListener{

		/**
		 * Key typed.
		 *
		 * @param e the e
		 */
		@Override
		public void keyTyped(KeyEvent e) {
			
			if(e.getSource() == txPass) {
				//El campo passField solo admite 8 caracteres como máximo
				if(txPass.getText().length() == 8) e.consume();
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
	 * Reiniciamos componentes
	 * 
	 * Restart.
	 */
	public void restart() {
		//Reiniciamos componentes
		cbTipo.setSelectedIndex(0); 
		cbID.setSelectedIndex(0);
		txPass.setText("");
	}
	
	
}


