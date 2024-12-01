package gestorbd;

import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

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
			String sqlrutina = "CREATE TABLE IF NOT EXISTS RUTINA (\n" + " ID INTEGER PRIMARY KEY,\n"
					+ " NOMBRE VARCHAR(20),\n" + " OBJETIVO VARCHAR(20)\n" + ");";

			String sqlejgym = "CREATE TABLE IF NOT EXISTS EJERCICIO_GYM (\n" + " ID INTEGER PRIMARY KEY,\n"
					+ " NOMBRE VARCHAR(20),\n" + " SERIES INT,\n" + " PESO INT\n" + ");";

			String sqlejcard = "CREATE TABLE IF NOT EXISTS EJERCICIO_CARDIO (\n" + " ID INTEGER PRIMARY KEY,\n"
					+ " NOMBRE VARCHAR(20),\n" + " DURACION INT\n" + ");";

			String sqlejnat = "CREATE TABLE IF NOT EXISTS EJERCICIO_NATACION (\n" + " ID INTEGER PRIMARY KEY,\n"
					+ " NOMBRE VARCHAR(20),\n" + " ESTILO_NATACION VARCHAR(20),\n" + " DURACION INT\n" + ");";

			String sqletienegym = "CREATE TABLE IF NOT EXISTS TIENE_GYM (\n" + " ID_EJERCICIO INTEGER,\n"
					+ " ID_RUTINA INTEGER,\n" + " PRIMARY KEY(ID_EJERCICIO, ID_RUTINA),\n"
					+ " FOREIGN KEY(ID_EJERCICIO) REFERENCES EJERCICIO_GYM(ID) ON DELETE CASCADE,\n"
					+ " FOREIGN KEY(ID_RUTINA) REFERENCES RUTINA(ID) ON DELETE CASCADE\n" + ");";

			String sqletienecardio = "CREATE TABLE IF NOT EXISTS TIENE_CARDIO (\n" + " ID_EJERCICIO INTEGER,\n"
					+ " ID_RUTINA INTEGER,\n" + " PRIMARY KEY(ID_EJERCICIO, ID_RUTINA),\n"
					+ " FOREIGN KEY(ID_EJERCICIO) REFERENCES EJERCICIO_CARDIO(ID) ON DELETE CASCADE,\n"
					+ " FOREIGN KEY(ID_RUTINA) REFERENCES RUTINA(ID) ON DELETE CASCADE\n" + ");";

			String sqletienenatacion = "CREATE TABLE IF NOT EXISTS TIENE_NATACION (\n" + " ID_EJERCICIO INTEGER,\n"
					+ " ID_RUTINA INTEGER,\n" + " PRIMARY KEY(ID_EJERCICIO, ID_RUTINA),\n"
					+ " FOREIGN KEY(ID_EJERCICIO) REFERENCES EJERCICIO_NATACION(ID) ON DELETE CASCADE,\n"
					+ " FOREIGN KEY(ID_RUTINA) REFERENCES RUTINA(ID) ON DELETE CASCADE\n" + ");";

			try (Connection con = DriverManager.getConnection(connectionString);
					PreparedStatement pStmt1 = con.prepareStatement(sqlrutina);
					PreparedStatement pStmt3 = con.prepareStatement(sqlejcard);
					PreparedStatement pStmt4 = con.prepareStatement(sqlejnat);
					PreparedStatement pStmt5 = con.prepareStatement(sqletienegym);
					PreparedStatement pStmt7 = con.prepareStatement(sqletienenatacion);
					PreparedStatement pStmt6 = con.prepareStatement(sqletienecardio);
					PreparedStatement pStmt2 = con.prepareStatement(sqlejgym)) {

				if (!pStmt1.execute() && !pStmt2.execute() && !pStmt3.execute() && !pStmt4.execute()
						&& !pStmt5.execute() && !pStmt6.execute() && !pStmt7.execute()) {
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
		if (properties.get("deleteBBDD").equals("true")) {
			String sqlDropTieneNatacion = "DROP TABLE IF EXISTS TIENE_NATACION;";
			String sqlDropTieneCardio = "DROP TABLE IF EXISTS TIENE_CARDIO;";
			String sqlDropTieneGym = "DROP TABLE IF EXISTS TIENE_GYM;";
			String sqlDropEjNatacion = "DROP TABLE IF EXISTS EJERCICIO_NATACION;";
			String sqlDropEjCardio = "DROP TABLE IF EXISTS EJERCICIO_CARDIO;";
			String sqlDropEjGym = "DROP TABLE IF EXISTS EJERCICIO_GYM;";
			String sqlDropRutina = "DROP TABLE IF EXISTS RUTINA;";

			try (Connection con = DriverManager.getConnection(connectionString);
					PreparedStatement pStmt1 = con.prepareStatement(sqlDropTieneNatacion);
					PreparedStatement pStmt2 = con.prepareStatement(sqlDropTieneCardio);
					PreparedStatement pStmt3 = con.prepareStatement(sqlDropTieneGym);
					PreparedStatement pStmt4 = con.prepareStatement(sqlDropEjNatacion);
					PreparedStatement pStmt5 = con.prepareStatement(sqlDropEjCardio);
					PreparedStatement pStmt6 = con.prepareStatement(sqlDropEjGym);
					PreparedStatement pStmt7 = con.prepareStatement(sqlDropRutina)) {

				pStmt1.execute();
				pStmt2.execute();
				pStmt3.execute();
				pStmt4.execute();
				pStmt5.execute();
				pStmt6.execute();
				pStmt7.execute();

				System.out.println("Se han borrado todas las tablas");
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
		if (properties.get("cleanBBDD").equals("true")) {
			String sqlDeleteTieneNatacion = "DELETE FROM TIENE_NATACION;";
			String sqlDeleteTieneCardio = "DELETE FROM TIENE_CARDIO;";
			String sqlDeleteTieneGym = "DELETE FROM TIENE_GYM;";
			String sqlDeleteEjNatacion = "DELETE FROM EJERCICIO_NATACION;";
			String sqlDeleteEjCardio = "DELETE FROM EJERCICIO_CARDIO;";
			String sqlDeleteEjGym = "DELETE FROM EJERCICIO_GYM;";
			String sqlDeleteRutina = "DELETE FROM RUTINA;";

			try (Connection con = DriverManager.getConnection(connectionString);
					PreparedStatement pStmt1 = con.prepareStatement(sqlDeleteTieneNatacion);
					PreparedStatement pStmt2 = con.prepareStatement(sqlDeleteTieneCardio);
					PreparedStatement pStmt3 = con.prepareStatement(sqlDeleteTieneGym);
					PreparedStatement pStmt4 = con.prepareStatement(sqlDeleteEjNatacion);
					PreparedStatement pStmt5 = con.prepareStatement(sqlDeleteEjCardio);
					PreparedStatement pStmt6 = con.prepareStatement(sqlDeleteEjGym);
					PreparedStatement pStmt7 = con.prepareStatement(sqlDeleteRutina)) {

				// Se ejecutan las sentencias de borrado de los datos
				pStmt1.execute();
				pStmt2.execute();
				pStmt3.execute();
				pStmt4.execute();
				pStmt5.execute();
				pStmt6.execute();
				pStmt7.execute();

				System.out.println("Se han borrado los datos de todas las tablas");
			} catch (Exception ex) {
				System.out.println(String.format("Error al borrar los datos: %s", ex.getMessage()));
			}
		}
	}

}
