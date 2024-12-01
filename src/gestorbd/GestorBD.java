package gestorbd;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class GestorBD {

	private final String PROPERTIES_FILE = "configuration/properties.txt";

	private Properties properties;
	private String driverName;
	private String databaseFile;
	private String connectionString;

	public GestorBD() {

		try {
			properties = new Properties();
			properties.load(new FileReader(PROPERTIES_FILE));

			driverName = properties.getProperty("driver");
			databaseFile = properties.getProperty("file");
			connectionString = properties.getProperty("connection");

			Class.forName(driverName);
		} catch (Exception e) {

		}

	}

	/**
	 * Crea la base de datos y sus tablas con las configuraciones especificadas.
	 */
	public void crearBBDD() {
		if (properties.getProperty("createBBDD").equals("true")) {
			String sqlrutina = "CREATE TABLE IF NOT EXISTS RUTINA (\n" 
				    + " ID INTEGER PRIMARY KEY,\n"
				    + " NOMBRE VARCHAR(20),\n" 
				    + " OBJETIVO VARCHAR(20)\n"
				    + ");";

				String sqlejgym = "CREATE TABLE IF NOT EXISTS EJERCICIO_GYM (\n" 
				    + " ID INTEGER PRIMARY KEY,\n"
				    + " NOMBRE VARCHAR(20),\n" 
				    + " SERIES INT,\n" 
				    + " PESO INT\n"
				    + ");";

				String sqlejcard = "CREATE TABLE IF NOT EXISTS EJERCICIO_CARDIO (\n" 
				    + " ID INTEGER PRIMARY KEY,\n"
				    + " NOMBRE VARCHAR(20),\n" 
				    + " DURACION INT\n"
				    + ");";

				String sqlejnat = "CREATE TABLE IF NOT EXISTS EJERCICIO_NATACION (\n" 
				    + " ID INTEGER PRIMARY KEY,\n"
				    + " NOMBRE VARCHAR(20),\n"
				    + " ESTILO_NATACION VARCHAR(20),\n"
				    + " DURACION INT\n"
				    + ");";

				String sqletienegym = "CREATE TABLE IF NOT EXISTS TIENE_GYM (\n" 
				    + " ID_EJERCICIO INTEGER,\n"
				    + " ID_RUTINA INTEGER,\n"
				    + " PRIMARY KEY(ID_EJERCICIO, ID_RUTINA)\n"
				    + ");";

				String sqletienecardio = "CREATE TABLE IF NOT EXISTS TIENE_CARDIO (\n" 
				    + " ID_EJERCICIO INTEGER,\n"
				    + " ID_RUTINA INTEGER,\n"
				    + " PRIMARY KEY(ID_EJERCICIO, ID_RUTINA)\n"
				    + ");";

				String sqletienenatacion = "CREATE TABLE IF NOT EXISTS TIENE_NATACION (\n" 
				    + " ID_EJERCICIO INTEGER,\n"
				    + " ID_RUTINA INTEGER,\n"
				    + " PRIMARY KEY(ID_EJERCICIO, ID_RUTINA)\n"
				    + ");";
				
			try (Connection con = DriverManager.getConnection(connectionString);
					PreparedStatement pStmt1 = con.prepareStatement(sqlrutina);
					PreparedStatement pStmt3 = con.prepareStatement(sqlejcard);
					PreparedStatement pStmt4 = con.prepareStatement(sqlejnat);
					PreparedStatement pStmt5 = con.prepareStatement(sqletienegym);
					PreparedStatement pStmt7 = con.prepareStatement(sqletienenatacion);
					PreparedStatement pStmt6 = con.prepareStatement(sqletienecardio);
					PreparedStatement pStmt2 = con.prepareStatement(sqlejgym)) {

				if (!pStmt1.execute() && !pStmt2.execute() && !pStmt3.execute()
						&& !pStmt4.execute()  && !pStmt5.execute() && !pStmt6.execute() 
						&& !pStmt7.execute()) {
					System.out.println("Base de datos creada con exito creada con exito");
				}
			} catch (Exception ex) {
				System.out.println(String.format("Error al crear las tablas: %s", ex.getMessage()));
			}
		}
	}

	/**
	 * Borra las tablas y el fichero de la BBDD.
	 */
	public void borrarBBDD() {
		// Sólo se borra la BBDD si la propiedad deleteBBDD es true
		if (properties.get("deleteBBDD").equals("true")) {
			String sql1 = "DROP TABLE IF EXISTS TABLA AUN POR CREAR;"; // CAMBIAR LA SENTENCIA

			// Se abre la conexión y se crea un PreparedStatement para borrar cada tabla
			try (Connection con = DriverManager.getConnection(connectionString);
					PreparedStatement pStmt1 = con.prepareStatement(sql1)) {

				// Se ejecutan las sentencias de borrado de las tablas
				if (!pStmt1.execute()) {
					System.out.println("Se han borrado las tablas");
				}
			} catch (Exception ex) {
				System.out.println(String.format("Error al borrar las tablas: %s", ex.getMessage()));
			}

			try {
				Files.delete(Paths.get(databaseFile));
				System.out.println("Se ha borrado el fichero de la BBDD");
			} catch (Exception ex) {
				System.out.println(String.format("Error al borrar el fichero de la BBDD: %s", ex.getMessage()));
			}
		}
	}

	public void borrarDatos() {
		// Sólo se borran los datos si la propiedad cleanBBDD es true
		if (properties.get("cleanBBDD").equals("true")) {
			String sql1 = "DELETE FROM TABLA POR CREAR;";

			// Se abre la conexión y se crea un PreparedStatement para borrar los datos de
			// cada tabla
			try (Connection con = DriverManager.getConnection(connectionString);
					PreparedStatement pStmt1 = con.prepareStatement(sql1)) {

				// Se ejecutan las sentencias de borrado de las tablas
				if (!pStmt1.execute()) {
					System.out.println("Se han borrado los datos");
				}
			} catch (Exception ex) {
				System.out.println(String.format("Error al borrar los datos: %s", ex.getMessage()));
			}
		}
	}

}
