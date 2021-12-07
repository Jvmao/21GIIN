package vista;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Usuarios;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.Color;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @JVMARTI
 */
public class MenuPrincipal extends JFrame {

	//Declaración componentes
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mMenuList;
	private JMenuItem mUsuarios,mMunicipios,mConvocatorias,mPresentaciones,mInformacion,mSalir;
	private JLabel lbWelcome;
	
	//Almacenamos la Ventana GestUsuarios pasándola a un JPanel para este JFrame
	GestUsuarios gt = new GestUsuarios();
	GestMunicipios gm = new GestMunicipios();
	Usuarios u = new Usuarios();
	
	//Identificadores//
	final static String ViewUsuarios = "Pantalla Usuarios";
	final static String ViewMunicipios = "Pantalla Municipios";
	
	public static String PassTipo;


	/**
	 * Create the frame.
	 */
	
	public MenuPrincipal() {
		initComponents();
	}
	
	public void initComponents() {
		setTitle("MENU PRINCIPAL");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		//Con la función setLocationRelativeTo centramos la Ventana Principal en el CENTRO de la Pantalla//
		setLocationRelativeTo(null);
		
		
		lbWelcome = new JLabel("PROYECTOS DE PROGRAMACION");
		lbWelcome.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 16));
		lbWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lbWelcome, "name_8131017183937");
		
		menuBar = new JMenuBar();
		menuBar.setBackground(Color.BLACK);
		menuBar.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 14));
		menuBar.setForeground(new Color(255, 165, 0));
		setJMenuBar(menuBar);
		

		//Menú desplegable lista de opciones
		mMenuList = new JMenu("Menu");
		menuBar.add(mMenuList);
		
		//Añadir Ventanas al Contenedor//
		contentPane.add(gt , ViewUsuarios);
		mUsuarios=new JMenuItem("Usuarios");
		mMenuList.add(mUsuarios);
		mUsuarios.addActionListener(new InnerAction());
		
		contentPane.add(gm,ViewMunicipios);
		mMunicipios=new JMenuItem("Municipios");
		mMenuList.add(mMunicipios);
		mMunicipios.addActionListener(new InnerAction());
		
		mConvocatorias=new JMenuItem("Convocatorias");
		mMenuList.add(mConvocatorias);
		
		mPresentaciones = new JMenuItem("Presentaciones");
		mMenuList.add(mPresentaciones);
		
		mInformacion = new JMenuItem("Información");
		mMenuList.add(mInformacion);
		
		mSalir = new JMenuItem("Salir");
		mMenuList.add(mSalir);
		
	}
	
	//Definimos los actionListener de los componentes desde la inner class
	public class InnerAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == mUsuarios) {
				//Action Listener para Mostrarnos la Ventana Usuarios en este JFrame//
				CardLayout cardUsuarios = (CardLayout)(contentPane.getLayout());
				cardUsuarios.show(contentPane, ViewUsuarios); //Muestra la Ventana Usuarios//
				setBounds(100, 100, 650, 450);
				setLocationRelativeTo(null);
				
				PassTipo = Login.TipoUsuario;
				System.out.println("Tipo Usuario en MenuPrincipal: "+PassTipo);
				gt.setValorTipoUsuario(PassTipo);
	
			}
			
			if(e.getSource()==mMunicipios) {
				//Action Listener para Mostrarnos la Ventana Usuarios en este JFrame//
				CardLayout cardUsuarios = (CardLayout)(contentPane.getLayout());
				cardUsuarios.show(contentPane, ViewMunicipios); //Muestra la Ventana Usuarios//
				setBounds(100, 100, 650, 450);
				setLocationRelativeTo(null);
				
				PassTipo = Login.TipoUsuario;
				System.out.println("Tipo Usuario en Municipios: "+PassTipo);
				
			}
			
		}
		
	}
	
	
	

	
		
}
