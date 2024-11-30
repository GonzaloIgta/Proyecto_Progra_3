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
    	} catch(Exception e) {
    		
    	}
        
    }

    /**
     * Crea la base de datos y sus tablas con las configuraciones especificadas.
     */
    public void crearBBDD() {
        if (properties.getProperty("createBBDD").equals("true")) {
            String sqlcreartabla1 = "CREATE TABLE IF NOT EXISTS RUTINA (\n"
	                   + " ID INTEGER PRIMARY KEY AUTOINCREMENT,\n"
	                   + " NOMBRE TEXT NOT NULL,\n"
	                   + " OBJETIVO TEXT NOT NULL,\n"
	                   + " LISTA_EJ ARRAY NOT NULL,\n" 
	                   + " NUMERO_EJ INTEGER NOT NULL,\n"
	                   + ");";

          

            try (Connection con = DriverManager.getConnection(connectionString);
                 PreparedStatement pStmt1 = con.prepareStatement(sqlcreartabla1)){

                if (!pStmt1.execute() ) {
                    System.out.println("Tabla creada con exito");
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
			String sql1 = "DROP TABLE IF EXISTS TABLA AUN POR CREAR;"; //CAMBIAR LA SENTENCIA


			// Se abre la conexión y se crea un PreparedStatement para borrar cada tabla
			try (Connection con = DriverManager.getConnection(connectionString);
					PreparedStatement pStmt1 = con.prepareStatement(sql1)){

				// Se ejecutan las sentencias de borrado de las tablas
				if (!pStmt1.execute() ) {
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
					PreparedStatement pStmt1 = con.prepareStatement(sql1)){

				// Se ejecutan las sentencias de borrado de las tablas
				if (!pStmt1.execute() ) {
					System.out.println("Se han borrado los datos");
				}
			} catch (Exception ex) {
				System.out.println(String.format("Error al borrar los datos: %s", ex.getMessage()));
			}
		}
	}

 
}
