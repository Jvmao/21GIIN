package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.ConDB;
import controlador.UsuariosImplDAO;
import modelo.Usuarios;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 * @JVMARTI
 */
public class Login extends JFrame {

	//Declaración componentes
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lbUser,lbIdUser,lbPass;
	private JButton btnStart,btnReset,btnSalir;
	private JComboBox<String> cbTipo,cbID;
	private JTextField txPass;

	//Llamada clase UsuariosImplDAO
	private UsuariosImplDAO udao = new UsuariosImplDAO();
	
	//Instanciamos el objeto Usuarios
	private Usuarios u = new Usuarios();
	private MenuPrincipal mn = new MenuPrincipal();
	//private GestUsuarios gt = new GestUsuarios();
	
	public static String TipoUsuario;
	
	//Variables Conexión
	Connection conn;
	
	private static String server = "jdbc:sqlserver://localhost:1433;databaseName=proyecto";
	//private static String server = "jdbc:sqlserver://127.0.0.1:1433;databaseName=proyecto";
	private static String user = "sa";
	//private static String user = "sqlserver";
	private static String pass = "sqlServer145+";
	//private static String pass = "-a123456";
	

	/**
	 * Create the frame.
	 */
	public Login() {
		initComponents();
	}
	
	
	public void initComponents() {
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
		cbTipo.setBounds(106, 30, 192, 27);
		contentPane.add(cbTipo);
		
		//Label ID Usuario
		lbIdUser = new JLabel("ID Usuario");
		lbIdUser.setBounds(6, 93, 88, 16);
		contentPane.add(lbIdUser);
		
		//ComboBox ID Usuario
		cbID = new JComboBox<String>();
		cbID.setBounds(106, 89, 192, 27);
		contentPane.add(cbID);
		
		//Label Password
		lbPass = new JLabel("Password");
		lbPass.setBounds(6, 146, 61, 16);
		contentPane.add(lbPass);
		
		//Campo Password Usuario
		txPass = new JTextField();
		txPass.setBounds(106, 141, 192, 26);
		contentPane.add(txPass);
		txPass.setColumns(10);
		txPass.addKeyListener(new InnerKey());
		
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
		

		ConDB.getConnection(); //Comprobamos Conexión BBDD
		udao.listaTipoUsuarios(cbTipo); //Llamamos al método para listar el tipo usuario en comboBox
		udao.listaIdUsuarios(cbID); //Llamamos al método para listar el tipo usuario en comboBox	
	}
	
	
	//Definimos los actionListener de los componentes desde la inner class
	public class InnerAction implements ActionListener{

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
				
				if (txPass.getText().length() < 3) {
					JOptionPane.showMessageDialog(null,"Longitud Contraseña Incorrecta. Vuelva a introducir los valores","Login", JOptionPane.WARNING_MESSAGE); 
					restart(); //Reiniciamos campos
					
				}else {
					try {
						//Establecemos conexión con BBDD
						conn = DriverManager.getConnection(server, user, pass);
						Statement st = conn.createStatement();
						
						//Definimos la consulta para comprobar que las creedenciales introducidas
						//existen en la BBDD
						String query_access = "select * from usuarios where idUsuario = '"+cbID.getSelectedItem().toString()+
								"' and tipoUsuario = '"+cbTipo.getSelectedItem().toString() +
								"' and passUsuario = '"+txPass.getText().toString()+"'";

						ResultSet rs = st.executeQuery(query_access);
						
						if(rs.next()) {
							//Guardamos los valores del objeto Usuarios
						    u.setIdUsuario(cbID.getSelectedItem().toString());
						    u.setTipoUsuario(cbTipo.getSelectedItem().toString());
						    u.setPassUsuario(Integer.parseInt(txPass.getText()));
						    System.out.println(u.toString());
						    
						    TipoUsuario = cbTipo.getSelectedItem().toString();
						    System.out.println("Tipo Usuario Login: "+TipoUsuario);
						    
						    mn.setVisible(true); //Abrimos ventana MenuPrincipal
						    dispose(); //Cerramos ventana Login
						    System.out.println("Datos Correctos");
						    
						}else {
							System.out.println("Datos Incorrectos");
							JOptionPane.showMessageDialog(null,"Datos Incorrectos. Vuelva a introducirlos.","Login", JOptionPane.WARNING_MESSAGE); 
							restart(); //Reiniciamos campos
						}
						

						
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
				}
			
				
			}

			//ActionListener Botón Salir
			if(e.getSource() == btnSalir) {
				System.exit(0);//Salimos de la Aplicación//
			}
			
		}
		
	}
	
	//Innerclass para los eventos de teclado
	public class InnerKey implements KeyListener{

		@Override
		public void keyTyped(KeyEvent e) {
			
			if(e.getSource() == txPass) {
				//El campo passField solo admite 8 caracteres como máximo
				if(txPass.getText().length() == 8) e.consume();
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
	
	//Método para reiniciar los campos seleccionados
	public void restart() {
		//Reiniciamos componentes
		cbTipo.setSelectedIndex(0); 
		cbID.setSelectedIndex(0);
		txPass.setText("");
	}
	
	
}


