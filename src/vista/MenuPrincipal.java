/*
 * 11 ene 2022
 * Jose V. Martí
 */
package vista;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Usuarios;
import util.ConstantsMessage;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// TODO: Auto-generated Javadoc

/**
 * The Class MenuPrincipal.
 */

/**
 * The Class MenuPrincipal.
 */
public class MenuPrincipal extends JFrame {

	/** The Constant serialVersionUID. */
	//Declaración componentes
	private static final long serialVersionUID = 1L;
	
	/** The content pane. */
	private JPanel contentPane;
	
	/** The menu bar. */
	private JMenuBar menuBar;
	
	/** The m menu list. */
	private JMenu mMenuList;
	
	/** The m salir. */
	private JMenuItem mUsuarios,mMunicipios,mConvocatorias,mPresentaciones,mInformacion,mSalir;
	
	/** The lb welcome. */
	private JLabel lbWelcome;

	/** The gt. */
	//Instanciamos los diferentes Atributos
	GestUsuarios gt = new GestUsuarios();
	
	/** The gm. */
	GestMunicipios gm = new GestMunicipios();
	
	/** The gc. */
	GestConvocatorias gc = new GestConvocatorias();
	
	/** The gp. */
	GestPresentaciones gp = new GestPresentaciones();
	
	/** The gi. */
	GestInformacion gi = new GestInformacion();
	
	/** The u. */
	Usuarios u = new Usuarios();
	

	/** The Constant ViewUsuarios. */
	//Identificadores//
	final static String ViewUsuarios = "Pantalla Usuarios";
	
	/** The Constant ViewMunicipios. */
	final static String ViewMunicipios = "Pantalla Municipios";
	
	/** The Constant ViewConvocatorias. */
	final static String ViewConvocatorias = "Pantalla Convocatorias";
	
	/** The Constant ViewPresentaciones. */
	final static String ViewPresentaciones = "Pantalla Presentaciones";
	
	/** The Constant ViewInformacion. */
	final static String ViewInformacion = "Pantalla Informacion";
	
	/** The Pass ID. */
	//Variables estáticas de tipo de usuario y ID de usuario
	public static String PassTipo,PassID;

	/** The image off. */
	//recurso imagen
	private ImageIcon imageOff = new ImageIcon(AltaUsuarios.class.getResource(ConstantsMessage.imgOff));

	/**
	 * Instantiates a new menu principal.
	 */
	
	public MenuPrincipal() {
		initComponents();
	}
	
	/**
	 * Inits the components.
	 */
	public void initComponents() {
		setTitle("PROYECTOS PROGRAMACIÓN");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		//Con la función setLocationRelativeTo centramos la Ventana Principal en el CENTRO de la Pantalla//
		setLocationRelativeTo(null);
		
		
		lbWelcome = new JLabel("BIENVENIDO");
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
		
		//Ventana Usuarios
		contentPane.add(gt,ViewUsuarios);
		mUsuarios=new JMenuItem("Usuarios");
		mMenuList.add(mUsuarios);
		mUsuarios.addActionListener(new InnerActionMenuPrincipal());
		
		//Ventana Municipios
		contentPane.add(gm,ViewMunicipios);
		mMunicipios=new JMenuItem("Municipios");
		mMenuList.add(mMunicipios);
		mMunicipios.addActionListener(new InnerActionMenuPrincipal());
		
		//Ventana Convocatorias
		contentPane.add(gc,ViewConvocatorias);
		mConvocatorias=new JMenuItem("Convocatorias");
		mMenuList.add(mConvocatorias);
		mConvocatorias.addActionListener(new InnerActionMenuPrincipal());
		
		//Ventana Presentaciones
		contentPane.add(gp,ViewPresentaciones);
		mPresentaciones = new JMenuItem("Presentaciones");
		mMenuList.add(mPresentaciones);
		mPresentaciones.addActionListener(new InnerActionMenuPrincipal());
		
		//Ventana Información
		contentPane.add(gi,ViewInformacion);
		mInformacion = new JMenuItem("Información");
		mMenuList.add(mInformacion);
		mInformacion.addActionListener(new InnerActionMenuPrincipal());
		
		mSalir = new JMenuItem("Salir");
		mMenuList.add(mSalir);
		mSalir.addActionListener(new InnerActionMenuPrincipal());
		
	}
	
	/**
	 * The Class InnerActionMenuPrincipal.
	 */
	//Definimos los actionListener de los componentes desde la inner class
	public class InnerActionMenuPrincipal implements ActionListener{

		/**
		 * Action performed.
		 *
		 * @param e the e
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == mUsuarios) {
				//Action Listener para Mostrarnos la Ventana Usuarios en este JFrame//
				CardLayout cardUsuarios = (CardLayout)(contentPane.getLayout());
				cardUsuarios.show(contentPane, ViewUsuarios); //Muestra la Ventana Usuarios//
				setBounds(100, 100, 650, 450);
				setLocationRelativeTo(null);
				
				PassTipo = Login.TipoUsuario; //Recogemos el valor del tipo de usuario conectado desde Login
				PassID = Login.idUsuario;
				System.out.println("Usuario en MenuPrincipal: "+PassTipo); //Imprimimos usuario conectado
				gt.setValorTipoUsuario(PassID,PassTipo); //Pasamos el valor del tipo de usuario a GestUsuario
				
				//Pasamos el tipo de usuario a la pantalla Gestusuario 
				//para establecer las oportunas restricciones
				gt.controlGestUsuarios(PassTipo); 
	
			}
			
			if(e.getSource()==mMunicipios) {
				//Action Listener para Mostrarnos la Ventana Municipios en este JFrame//
				CardLayout cardMunicipios = (CardLayout)(contentPane.getLayout());
				cardMunicipios.show(contentPane, ViewMunicipios); //Muestra la Ventana Municipios//
				setBounds(100, 100, 650, 450);
				setLocationRelativeTo(null);
				
				PassTipo = Login.TipoUsuario; //Recogemos el valor del tipo de usuario conectado desde Login
				PassID = Login.idUsuario; 	  //Recogemos el valor del id de usuario conectado desde Login
				System.out.println("Usuario en Municipios: "+PassTipo); //Imprimimos usuario conectado
				gm.setValorTipoUsuario(PassID,PassTipo); //Pasamos el valor del tipo de usuario a GestMunicipios
				gm.controlGestMunicipios(PassTipo);
				
			}
			
			if(e.getSource()==mConvocatorias) {
				//Action Listener para Mostrarnos la Ventana Convocatorias en este JFrame//
				CardLayout cardConvocatorias = (CardLayout)(contentPane.getLayout());
				cardConvocatorias.show(contentPane, ViewConvocatorias); //Muestra la Ventana Convocatorias//
				setBounds(100, 100, 650, 450);
				setLocationRelativeTo(null);
				
				PassTipo = Login.TipoUsuario; //Recogemos el valor del tipo de usuario conectado desde Login
				PassID = Login.idUsuario;
				System.out.println("Usuario en Convocatorias: "+PassTipo); //Imprimimos usuario conectado
				gc.setValorTipoUsuario(PassID,PassTipo);
				gc.controlGestConvocatorias(PassTipo);
			}
			
			if(e.getSource()==mPresentaciones) {
				//Action Listener para Mostrarnos la Ventana Presentaciones en este JFrame//
				CardLayout cardPresentaciones = (CardLayout)(contentPane.getLayout());
				cardPresentaciones.show(contentPane, ViewPresentaciones); //Muestra la Ventana Presentaciones//
				setBounds(100, 100, 650, 450);
				setLocationRelativeTo(null);
				
				PassTipo = Login.TipoUsuario; //Recogemos el valor del tipo de usuario conectado desde Login
				PassID = Login.idUsuario; 	  //Recogemos el valor del id de usuario conectado desde Login
				System.out.println("Usuario en Presentaciones: "+PassID+" "+PassTipo); //Imprimimos usuario conectado
				gp.setValoresUsuario(PassID,PassTipo);
				gp.controlGestPresentaciones(PassTipo);
			}
			
			if(e.getSource()==mInformacion) {
				//Action Listener para Mostrarnos la Ventana Municipios en este JFrame//
				CardLayout cardPresentaciones = (CardLayout)(contentPane.getLayout());
				cardPresentaciones.show(contentPane, ViewInformacion); //Muestra la Ventana Información//
				setBounds(100, 100, 650, 450);
				setLocationRelativeTo(null);
				
				PassTipo = Login.TipoUsuario; //Recogemos el valor del tipo de usuario conectado desde Login
				PassID = Login.idUsuario;     //Recogemos el valor del id de usuario conectado desde Login
				System.out.println("Usuario en Información: "+PassID+" "+PassTipo); //Imprimimos usuario conectado
				gi.setValoresUsuario(PassID,PassTipo);
			}
			
			
			
			if(e.getSource()==mSalir) {
				//El usuario debe de confirmar si desea salir de la aplicación
	            int result = JOptionPane.showOptionDialog(
	               null,
	               ConstantsMessage.msg10, 
	               ConstantsMessage.msg11,            
	               JOptionPane.YES_NO_OPTION,
	               JOptionPane.QUESTION_MESSAGE,
	               imageOff,    
	               ConstantsMessage.optionsClose,  //titulo botones
	               ConstantsMessage.optionsClose[0] //opciones
	            );
	            
				if(result==JOptionPane.YES_OPTION){
					try {
						//mp.setVisible(false);
						dispose(); //Cerramos la aplicación
						System.out.println("Aplicación Cerrada");
					}catch(Exception ex) {
						System.out.println("Mensaje Error Cierre Aplicación: "+ex.getMessage());
					}
					
				}
			}
			
			
		}
		
	}
	
	
	

	
		
}
