package principal;

import javax.swing.JFrame;

import vista.Login;

public class Start extends JFrame{
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {

		//Lanzamos aplicación desde clase Login
		Login login = new Login();
		login.setVisible(true);

	}

}
