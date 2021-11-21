package vista;

//import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.Color;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class MenuPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mMenuList;
	private JMenuItem mMunicipios,mConvocatorias,mPresentaciones,mInformacion,mSalir;
	private JLabel lbWelcome;

	/**
	 * Launch the application.
	 */
	/**public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal frame = new MenuPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}**/

	/**
	 * Create the frame.
	 */
	public MenuPrincipal() {
		setTitle("MENU PRINCIPAL");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		lbWelcome = new JLabel("PROYECTOS DE PROGRAMACION");
		lbWelcome.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 16));
		lbWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lbWelcome, "name_8131017183937");
		
		menuBar = new JMenuBar();
		menuBar.setBackground(Color.BLACK);
		menuBar.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 14));
		menuBar.setForeground(new Color(255, 165, 0));
		setJMenuBar(menuBar);
		

		
		mMenuList = new JMenu("Menu");
		menuBar.add(mMenuList);
		
		mMunicipios=new JMenuItem("Municipios");
		mMenuList.add(mMunicipios);
		
		mConvocatorias=new JMenuItem("Convocatorias");
		mMenuList.add(mConvocatorias);
		
		mPresentaciones = new JMenuItem("Presentaciones");
		mMenuList.add(mPresentaciones);
		
		mInformacion = new JMenuItem("Información");
		mMenuList.add(mInformacion);
		
		mSalir = new JMenuItem("Salir");
		mMenuList.add(mSalir);

	}

	
}
