/*
 * 18 ene 2022
 * Jose V. Martí
 */
package vista;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.ConDB;
import util.ConstantsDB;
import util.ConstantsGestInformacion;

// TODO: Auto-generated Javadoc
/**
 * The Class GestInformacion.
 */

/**
 * The Class GestInformacion.
 */

public class GestInformacion extends JPanel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The content pane. */
	private JPanel contentPane;
	
	/** The lb info pres. */
	private JLabel lbTitulo,lbTipoUsuario,lbImageInf,lbInfoConv,lbTotalAbiertos,lbTotalCerrados,lbInfoPres;
	
	/** The tx total cerrados. */
	private JTextField txTipoUsuario,txIDUsuario,txTotalAbiertos,txTotalCerrados;

	/** The model pres. */
	//Variables Tabla
	private DefaultTableModel modelConv,modelPres;
	
	/** The table pres. */
	private JTable tableConv,tablePres;
	
	/** The scroll pres. */
	private JScrollPane scrollConv,scrollPres;
	
	/** The conn. */
	//Variables Conexión
	private Connection conn;
	
	/**
	 * Instantiates a new gest informacion.
	 */
	public GestInformacion() {
		setBounds(100, 100, 628, 450);
		contentPane=this;
		setLayout(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		//Label Título
		lbTitulo = new JLabel(ConstantsGestInformacion.labelTituloInformacion);
		lbTitulo.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
		lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitulo.setBounds(6, 6, 638, 16);
		add(lbTitulo);
		
		//Label Tipo Usuario
		lbTipoUsuario = new JLabel(ConstantsGestInformacion.labelTipoConectado);
		lbTipoUsuario.setBounds(16, 39, 167, 16);
		add(lbTipoUsuario);
		
		//JText ID Usuario Conectado
		txIDUsuario = new JTextField();
		txIDUsuario.setEditable(false);
		txIDUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		txIDUsuario.setBounds(195, 34, 115, 26);
		add(txIDUsuario);
		txIDUsuario.setColumns(10);
		
		//JText tipo de usuario conectado
		txTipoUsuario = new JTextField();
		txTipoUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		txTipoUsuario.setBounds(317, 34, 143, 26);
		txTipoUsuario.setEditable(false);
		add(txTipoUsuario);
		txTipoUsuario.setColumns(10);
		
		lbImageInf = new JLabel("");
		lbImageInf.setHorizontalAlignment(SwingConstants.CENTER);
		lbImageInf.setBounds(505, 7, 117, 94);
		add(lbImageInf);
	    //Icono presentaciones
		lbImageInf.setIcon(new ImageIcon(ConstantsGestInformacion.class.getResource(ConstantsGestInformacion.imgInformacion)));
		
		//Label Info Convocatorias
		lbInfoConv = new JLabel(ConstantsGestInformacion.labelInfoConv);
		lbInfoConv.setBounds(16, 85, 444, 16);
		add(lbInfoConv);
		
		
		//Scroll Tabla Documentos
		scrollConv = new JScrollPane();
		scrollConv.setBounds(16, 120, 583, 86);
		contentPane.add(scrollConv);
		
		//Tabla para mostrar los documentos 
		modelConv = new DefaultTableModel();
		tableConv = new JTable(modelConv);
		tableConv.setForeground(Color.BLACK);
		tableConv.setBackground(Color.LIGHT_GRAY);
		tableConv.setAutoCreateRowSorter(true);
		tableConv.setFont(new Font("Verdana", Font.PLAIN, 12));
		tableConv.setShowVerticalLines(true);
		modelConv.addColumn(ConstantsGestInformacion.tableColumnsConv[0]);
		modelConv.addColumn(ConstantsGestInformacion.tableColumnsConv[1]);
		modelConv.addColumn(ConstantsGestInformacion.tableColumnsConv[2]);
		modelConv.addColumn(ConstantsGestInformacion.tableColumnsConv[3]);
		modelConv.addColumn(ConstantsGestInformacion.tableColumnsConv[4]);
		modelConv.addColumn(ConstantsGestInformacion.tableColumnsConv[5]);
		scrollConv.setViewportView(tableConv);
		
		//Label Total Abiertos
		lbTotalAbiertos = new JLabel(ConstantsGestInformacion.labelTotales[0]);
		lbTotalAbiertos.setBounds(16, 217, 191, 16);
		add(lbTotalAbiertos);
		
		//Campo Total Abiertos
		txTotalAbiertos = new JTextField();
		txTotalAbiertos.setEditable(false);
		txTotalAbiertos.setHorizontalAlignment(SwingConstants.CENTER);
		txTotalAbiertos.setBounds(219, 212, 41, 26);
		add(txTotalAbiertos);
		txTotalAbiertos.setColumns(10);
		
		//Label Total Cerrados
		lbTotalCerrados = new JLabel(ConstantsGestInformacion.labelTotales[1]);
		lbTotalCerrados.setBounds(317, 217, 191, 16);
		add(lbTotalCerrados);
		
		//Campo Total Cerrados
		txTotalCerrados = new JTextField();
		txTotalCerrados.setEditable(false);
		txTotalCerrados.setHorizontalAlignment(SwingConstants.CENTER);
		txTotalCerrados.setBounds(520, 212, 41, 26);
		add(txTotalCerrados);
		txTotalCerrados.setColumns(10);
		
		//Label Info Presentaciones
		lbInfoPres = new JLabel(ConstantsGestInformacion.labelInfoPres);
		lbInfoPres.setBounds(16, 263, 444, 16);
		add(lbInfoPres);
		
		//Scroll Tabla Presentaciones
		scrollPres = new JScrollPane();
		scrollPres.setBounds(16, 283, 583, 86);
		contentPane.add(scrollPres);
		
		//Tabla para mostrar los documentos 
		modelPres = new DefaultTableModel();
		tablePres = new JTable(modelPres);
		tablePres.setForeground(Color.BLACK);
		tablePres.setBackground(Color.LIGHT_GRAY);
		tablePres.setAutoCreateRowSorter(true);
		tablePres.setFont(new Font("Verdana", Font.PLAIN, 12));
		tablePres.setShowVerticalLines(true);
		modelPres.addColumn(ConstantsGestInformacion.tableColumnsPres[0]);
		modelPres.addColumn(ConstantsGestInformacion.tableColumnsPres[1]);
		modelPres.addColumn(ConstantsGestInformacion.tableColumnsPres[2]);
		modelPres.addColumn(ConstantsGestInformacion.tableColumnsPres[3]);
		modelPres.addColumn(ConstantsGestInformacion.tableColumnsPres[4]);
		modelPres.addColumn(ConstantsGestInformacion.tableColumnsPres[5]);
		scrollPres.setViewportView(tablePres);
		
		try {
			populateInfoConv();
			populateInfoPres();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		calculaTotalesEstado();
	}
	
	
	/**
	 * Populate info conv.
	 *
	 * @throws ParseException the parse exception
	 */
	//mostramos los datos de la BBDD de la tabla presentaciones en la tabla
	private void populateInfoConv() throws ParseException {
		try {
			//Conexión a la BBDD
			//conn = DriverManager.getConnection(ConstantsDB.server,ConstantsDB.user,ConstantsDB.pass);
			conn = ConDB.getConnection(ConstantsDB.server,ConstantsDB.user,ConstantsDB.pass);
			
			//Consulta a BBDD
		    Statement st = conn.createStatement();
		    ResultSet rs = st.executeQuery(ConstantsDB.queryListaEstadoConvocatorias);
		    

		    //Obtenemos todos los valores de la BBDD y los vamos añadiendo uno a uno en la tabla
		    while (rs.next()) {
		    	String idConv = rs.getString(ConstantsDB.valueIdConInfo);
		    	String idUser = rs.getString(ConstantsDB.valueIdUserInfo);
		    	String descConv = rs.getString(ConstantsDB.valueDescConv);
		    	String fApertura = rs.getString(ConstantsDB.valueApertura);
		    	String fCierre = rs.getString(ConstantsDB.valueCierre);
		    	int estado = rs.getInt(ConstantsDB.valueEstadoConv);
		    	
		    	//Modificamos el formato de fecha de la BBDD para tener el mismo que en la pantalla
		    	//SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    	SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		    	Date dateValueApertura = input.parse(fApertura);
		    	//SimpleDateFormat outputApertura = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		    	SimpleDateFormat outputApertura = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		    	
		    	Date dateValueCierre = input.parse(fCierre);
		    	//SimpleDateFormat outputCierre = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		    	SimpleDateFormat outputCierre = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		    	//Añadimos los valores en cada fila de la tabla dinámicamente
		        modelConv.addRow(new Object[]{idConv,
		        								idUser,
		        								descConv,
		        								outputApertura.format(dateValueApertura),
		        								outputCierre.format(dateValueCierre),
		        								estado});      
		   }      
		      
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Populate info pres.
	 *
	 * @throws ParseException the parse exception
	 */
	private void populateInfoPres() throws ParseException {
		try {
			//Conexión a la BBDD
			//conn = DriverManager.getConnection(ConstantsDB.server,ConstantsDB.user,ConstantsDB.pass);
			conn = ConDB.getConnection(ConstantsDB.server,ConstantsDB.user,ConstantsDB.pass);
			
			//Consulta a BBDD
		    Statement st = conn.createStatement();
		    ResultSet rs = st.executeQuery(ConstantsDB.queryListaEstadoPresentaciones);
		    

		    //Obtenemos todos los valores de la BBDD y los vamos añadiendo uno a uno en la tabla
		    while (rs.next()) {
		    	String idMun = rs.getString(ConstantsDB.valuesIdMunPres);    
		    	String idUser = rs.getString(ConstantsDB.valuesIdUsuerPres);
		    	String catMun = rs.getString(ConstantsDB.valuesCatMunPres);
		    	String fPres = rs.getString(ConstantsDB.valuesFechaPres);
		    	int estPres = rs.getInt(ConstantsDB.valuesEstadoPres);
				String docPres = rs.getString(ConstantsDB.valuesDocsPres);
		    	
				//Cambiamos formato de fecha de BBDD a formato en la aplicación
		    	SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		    	Date dateValueApertura = input.parse(fPres);
		    	SimpleDateFormat outputApertura = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		    	
		    	//Removemos los carácteres del arryaList para contar sus elementos
		    	String rgx [] = docPres.replaceAll("\\[", "").replaceAll("\\]", "").split(",");
				
		    	//Contamos los elementos del ArrayList
				int nDocs = 0;
				for(String a:rgx) {
					a.toString();
					nDocs++;
				}
		    	
				//Añadimos la fila correspondiente en la talba
		    	modelPres.addRow(new Object[] {idMun,
		    									idUser,
		    									catMun,
		    									outputApertura.format(dateValueApertura),
		    									estPres,
		    									nDocs});
		   }      
		      
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	/**
	 * Sets the valores usuario.
	 *
	 * @param id     the id
	 * @param nombre the nombre
	 */
	//Recogemos el valor de ID y Tipo de Usuario desde la clase de MenuPrincipal
	public void setValoresUsuario (String id,String nombre){
			txIDUsuario.setText(id);
			txTipoUsuario.setText(nombre);
	}
	
	/**
	 * Calcula totales estado.
	 */
	//Método para calcular el total de convocatorias abiertas y cerradas
	private void calculaTotalesEstado() {
		int totalAbiertos = 0;
		int totalCerrados = 0;
        for (int i = 0; i < tableConv.getRowCount(); i++) {
            if (tableConv.getValueAt(i, 5).equals(1)) {
            	totalAbiertos++;
            }else if(tableConv.getValueAt(i, 5).equals(0)) {
            	totalCerrados++;
            }
        }
        txTotalAbiertos.setText(String.valueOf(totalAbiertos));
        txTotalCerrados.setText(String.valueOf(totalCerrados));
        
	}
}
