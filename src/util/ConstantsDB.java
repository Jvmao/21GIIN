/*
 * 11 ene 2022
 * Jose V. Martí
 */
package util;


/**
 * The Class ConstantsDB.
 */
public class ConstantsDB {
	
	/** The Constant pathProperties. */
	public final static String pathProperties = "src/resources/db_conf.properties";
	
	/** The Constant s. */
	public final static String server = "db.conn.url";
	
	/** The Constant u. */
	public final static String user = "db.username";
	
	/** The Constant p. */
	public final static String pass = "db.password";
	
	/** The Constant queryUsers. */
	//Consultas Generales GestUsuarios
	public final static String queryUsers = "SELECT idUsuario FROM usuarios ORDER BY 1";
	
	/** The Constant queryType. */
	public final static String queryType = "SELECT distinct(tipoUsuario) FROM usuarios ORDER BY 1";
	
	/** The Constant valueID. */
	//Valores columnas tabla usuarios
	public final static String valueID = "idUsuario";
	
	/** The Constant valueTipo. */
	public final static String valueTipo = "tipoUsuario";
	
	/** The Constant valuePass. */
	public final static String valuePass = "passUsuario";
	
	/** The Constant queryListaUsuarios. */
	//Consulta para listar todos los usuarios en GestUsuarios
	public final static String queryListaUsuarios = "SELECT "
													+ "idUsuario,"
													+ "tipoUsuario,"
													+ "passUsuario "
													+ "FROM usuarios ORDER BY 1,2";
	
	/** The Constant idMunicipios. */
	//Consultas Generales GestMunicipios
	public final static String idMunicipios = "SELECT idMunicipio FROM municipios order by 1";
	
	/** The Constant tipoUsuarioMunicipio. */
	public final static String tipoUsuarioMunicipio = "SELECT distinct(tipoUsuario) FROM municipios ORDER BY 1";
	
	/** The Constant valueIDMunicipio. */
	//Valores columnas tabla municipios
	public final static String valueIDMunicipio = "idMunicipio";
	
	/** The Constant valueCatMunicipio. */
	public final static String valueCatMunicipio = "catMunicipio";
	
	/** The Constant valueTipoUsuarioMunicipio. */
	public final static String valueTipoUsuarioMunicipio = "tipoUsuario";
	
	/** The Constant queryListaIdUsuariosMunicipios. */
	public final static String queryListaIdUsuariosMunicipios = "SELECT idUsuario FROM usuarios WHERE tipoUsuario = 'Cuentadante'";
	
	/** The Constant queryListaMunicipios. */
	//Consulta para listar todos los usuarios en GestUsuarios
	public final static String queryListaMunicipios = "SELECT "
													+ "idMunicipio,"
													+ "catMunicipio,"
													+ "idUser,"
													+ "tipoUsuario "
													+ "FROM municipios ORDER BY 1,2";
	
	/** The Constant valueIDConvocatorias. */
	//Valores columnas tabla convocatorias
	public final static String valueIDConvocatorias = "idConvocatorias";
	
	/** The Constant valueDescPresentacion. */
	public final static String valueDescPresentacion = "descPresentacion";
	
	/** The Constant valueFechaApertura. */
	public final static String valueFechaApertura = "fechaApertura";
	
	/** The Constant valueFechaCierre. */
	public final static String valueFechaCierre = "fechaCierre";
	
	/** The Constant valueEstadoApertura. */
	public final static String valueEstadoApertura = "estadoApertura";
	
	/** The Constant valueDocsPresentados. */
	public final static String valueDocsPresentados = "docsPresentados";

	/** The Constant queryListaConvocatorias. */
	//Consulta para listar todas las convocatorias en GestConvocatoria
	/**public final static String queryListaConvocatorias = "SELECT "
													   + "idConvocatorias,"
													   + "descPresentacion,"
													   + "CONVERT(DATETIME,fechaApertura,103) AS fechaApertura,"
													   + "CONVERT(DATETIME,fechaCierre,103) AS fechaCierre,"
													   + "estadoApertura,"
													   + "docsPresentados "
													   + "FROM convocatorias ORDER BY 1";**/
	
	public final static String queryListaConvocatorias = "SELECT "
													   + "idConvocatorias,"
													   + "descPresentacion,"
													   + "fechaApertura,"
													   + "fechaCierre,"
													   + "estadoApertura,"
													   + "docsPresentados "
													   + "FROM convocatorias ORDER BY 1";
	
	/** The Constant valueIDPres. */
	//Valores columnas tabla presentaciones
	public final static String valueIDPres = "idPres";
	
	/** The Constant valueIDUser. */
	public final static String valueIDUser = "idUser";
	
	/** The Constant valueTypeUser. */
	public final static String valueTypeUser = "typeUser";
	
	/** The Constant valueFecha. */
	public final static String valueFecha = "fecha";
	
	/** The Constant valueEstadoPres. */
	public final static String valueEstadoPres = "estado";
	
	/** The Constant valueDocsRequeridos. */
	public final static String valueDocsRequeridos = "docs";

	/** The Constant queryListaPresentaciones. */
	//Consulta para listar todas las convocatorias en GestConvocatoria
	public final static String queryListaPresentaciones = "SELECT "
														+ "idPres,"
														+ "idUser,"
														+ "typeUser,"
														+ "fecha,"
														+ "estado,"
														+ "docs "
														+ "FROM presentaciones ORDER BY 1";
	
	/** The Constant queryListaEstadoConvocatorias. */
	//Listar datos de la tabla convocatorias en la pantalla de información
	public final static String queryListaEstadoConvocatorias = "SELECT "
															 + "descPresentacion,"
															 + "fechaApertura,"
															 + "fechaCierre,"
															 + "estadoApertura "
															 + "FROM convocatorias ORDER BY 2,4";
	
	/** The Constant valueDescConv. */
	//Valores columnas tabla convocatorias
	public final static String valueDescConv = "descPresentacion";
	
	/** The Constant valueApertura. */
	public final static String valueApertura = "fechaApertura";
	
	/** The Constant valueCierre. */
	public final static String valueCierre = "fechaCierre";
	
	/** The Constant valueEstadoConv. */
	public final static String valueEstadoConv = "estadoApertura";
	
	
	/** The Constant queryListaEstadoPresentaciones. */
	
	//Listar presentaciones por usuario en la pantalla de información
	public final static String queryListaEstadoPresentaciones = "SELECT "
															  + "m.idMunicipio,"
															  + "m.idUser,"
															  + "m.catMunicipio,"
															  + "p.fecha,"
															  + "p.estado,"
															  + "p.docs "
															  + "FROM presentaciones p "
															  + "JOIN municipios m "
															  + "ON p.idUser=m.idUser ORDER BY 3";
	
	/** The Constant valuesIdMunPres. */
	public final static String valuesIdMunPres = "idMunicipio";
	
	/** The Constant valuesIdUsuerPres. */
	public final static String valuesIdUsuerPres = "idUser";
	
	/** The Constant valuesCatMunPres. */
	public final static String valuesCatMunPres = "catMunicipio";
	
	/** The Constant valuesFechaPres. */
	public final static String valuesFechaPres = "fecha";
	
	/** The Constant valuesEstadoPres. */
	public final static String valuesEstadoPres = "estado";
	
	/** The Constant valuesDocsPres. */
	public final static String valuesDocsPres = "docs";
	
	

}