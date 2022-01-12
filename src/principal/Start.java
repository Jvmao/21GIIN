/*
 * 11 ene 2022
 * Jose V. Martí
 */

package principal;

import javax.swing.JFrame;

import vista.Login;

// TODO: Auto-generated Javadoc
/**
 * The Class Start.
 */

/**
 * The Class Start.
 */

public class Start extends JFrame{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {

		try {
			//Lanzamos aplicación desde clase Login
			Login login = new Login();
			login.setVisible(true);
		}catch(Exception e) {
			e.getMessage();
		}
		

	}
}
