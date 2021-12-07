package vista;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

/**
 * @JVMARTI
 */
public class GestMunicipios extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lbTitulo;

	/**
	 * Create the panel.
	 */
	public GestMunicipios() {
		setBounds(100, 100, 650, 450);
		contentPane=this;
		setLayout(null);
		
		lbTitulo = new JLabel("Gestión Municipios");
		lbTitulo.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
		lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitulo.setBounds(6, 6, 638, 16);
		add(lbTitulo);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		

	}

}
