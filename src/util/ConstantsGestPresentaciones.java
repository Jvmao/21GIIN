/*
 * 23 ene 2022
 * Jose V. Martí
 */
package util;

/**
 * Strings constantes referentes a las pantallas de Presentaciones
 * 
 * The Class ConstantsGestPresentaciones.
 */
public class ConstantsGestPresentaciones {
	
	/** The Constant labelTituloPresentaciones. */
	public final static String labelTituloPresentaciones = "Gestión Presentaciones";
	
	/** The Constant labelTipoConectado. */
	public final static String labelTipoConectado = "Tipo Usuario Conectado";
	
	/** The Constant labelFechaGestPres. */
	public final static String labelFechaGestPres = "Fecha";
	
	/** The Constant labelFechaPres. */
	public final static String labelFechaPres = "Fecha Presentación";
	
	/** The Constant labelEstado. */
	public final static String labelEstado = "Estado Apertura";
	
	/** The Constant labelTipoEstado. */
	public final static String labelTipoEstado[] = {"Abierto","Cerrado"};
	
	/** The Constant labelDocs. */
	public final static String labelDocs = "Documentos Aportados";
	
	/** The Constant tipoDocs. */
	public final static String tipoDocs[]= {"Libro Diario","Libro Mayor","Balance Sumas y Saldos","Reg. Ing. Caja","Reg. Mov. Bancos","Otros"};
	
	/** The Constant btnAddDocs. */
	public final static String btnAddDocs = "+";
	
	/** The Constant btnDelDocs. */
	public final static String btnDelDocs = "-";
	
	/** The Constant labelPres. */
	public final static String labelPres = "Presentaciones Registradas";
	
	/** The Constant presBotonera. */
	public final static String presBotonera[]= {"Alta","Modificación","Eliminar"};
	
	/** The Constant columnaTablaDocs. */
	public final static String columnaTablaDocs = "Documentos";
	
	/** The Constant tableColumnsPres. */
	public final static String tableColumnsPres[] = { "ID Pres.","Conv. Asociada","ID Usuario","Fecha Presentación","Estado","Docs. Aportados" };
	
	/** The Constant tiposUsuariosGestPres. */
	public final static String tiposUsuariosGestPres [] = {"Administrador","Cuentadante","Fiscal","Fiscal General"};
	
	/** The Constant imgPresentaciones. */
	//Variables ruta imagenes de GestConvocatorias
	public final static String imgPresentaciones = "/imagenes/pres_64.png";
	
	/** The Constant labelTituloAltaPres. */
	//Constantes Alta Presentaciones
	public final static String labelTituloAltaPres = "Alta Presentaciones";
	
	/** The Constant labelUsuario. */
	public final static String labelUsuario="Usuario";
	
	/** The Constant labelIdPres. */
	public final static String labelIdPres ="ID Presentaciones";
	
	/** The Constant txTipoUsuarioPres. */
	public final static String txTipoUsuarioPres = "Cuentadante";
	
	/** The Constant altaBotoneraPres. */
	public final static String altaBotoneraPres[] = {"Confirmar","Cancelar"};
	
	/** The Constant labelIdConv. */
	//Constantes Modificación Presentaciones
	public final static String labelIdConv = "ID Conv. Asociada";
	
	/** The Constant labelIniConv. */
	public final static String labelIniConv = "Inicio Conv.";
	
	/** The Constant labelFinConv. */
	public final static String labelFinConv = "Fin Conv.";
	
}
