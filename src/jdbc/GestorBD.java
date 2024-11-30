package jdbc;


import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import clases_de_apyo.Ejercicio;
import clases_de_apyo.Rutina;
import clases_de_apyo.Rutina.Objetivo_de_la_sesion;

public class GestorBD {

	protected static final String DRIVER_NAME = "org.sqlite.JDBC";
	protected static final String DATABASE_FILE = "resources/db/database.db";
	protected static final String CONNECTION_STRING = "jdbc:sqlite:" + DATABASE_FILE;
	
	public GestorBD() {		
		try {
			//Cargar el diver SQLite
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException ex) {
			System.err.format("\n* Error al cargar el driver de BBDD: %s", ex.getMessage());
			ex.printStackTrace();
		}
	}
		
	public void crearBBDD() {
		//Se abre la conexión y se obtiene el Statement
		//Al abrir la conexión, si no existía el fichero, se crea la base de datos
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
			
	       
	        PreparedStatement stmt= con.prepareStatement ("CREATE TABLE IF NOT EXISTS RUTINA (\n"
	                   + " ID INTEGER PRIMARY KEY AUTOINCREMENT,\n"
	                   + " NOMBRE TEXT NOT NULL,\n"
	                   + " OBJETIVO TEXT NOT NULL,\n"
	                   + " LISTA_EJ ARRAY NOT NULL,\n" //se pone asi "Array"?
	                   + " NUMERO_EJ INTEGER NOT NULL,\n"
	                   + ");")) {
				stmt.execute();
	        	System.out.println("- Se ha creado la tabla Rutina");
	        
		} catch (Exception ex) {
			System.err.format("* Error al crear la BBDD: %s", ex.getMessage());
			ex.printStackTrace();			
		}
	}
	
	public void borrarBBDD() {
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
		     Statement stmt = con.createStatement()) {
			
	        String sql = "DROP TABLE IF EXISTS RUTINA";
			
	        //Se ejecuta la sentencia de creación de la tabla 
	        if (!stmt.execute(sql)) {
	        	System.out.println("\n\n- Se ha borrado la tabla Rutina");
	        }
		} catch (Exception ex) {
			System.err.format("\n* Error al borrar la BBDD: %s", ex.getMessage());
			ex.printStackTrace();			
		}
		
		try {
			//Se borra el fichero de la BBDD
			Files.delete(Paths.get(DATABASE_FILE));
			System.out.println("\n- Se ha borrado el fichero de la BBDD");
		} catch (Exception ex) {
			System.err.format("\n* Error al borrar el archivo de la BBDD: %s", ex.getMessage());
			ex.printStackTrace();						
		}
	}
	
	public void insertarDatos(Rutina... rutinas) {
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
		     Statement stmt = con.createStatement()) {
			//Se define la plantilla de la sentencia SQL
			String sql = "INSERT INTO RUTINA (NOMBRE, NUMERO_EJ) VALUES ('%s', '%d');";
			
			System.out.print("\n- Insertando rutinas...");
			
			//Se recorren las rutinas y se insertan una a una
			for (Rutina c : rutinas) {
				if (1 == stmt.executeUpdate(String.format(sql, c.getNombre()), c.getLista_ejercicios().size())) {					
					System.out.format("\n - Rutina insertada: %s", c.toString());
				} else {
					System.out.format("\n - No se ha insertado la rutina: %s", c.toString());
				}
			}			
		} catch (Exception ex) {
			System.err.format("\n* Error al insertar datos de la BBDD: %s", ex.getMessage());
			ex.printStackTrace();						
		}				
	}
	
	public List<Rutina> obtenerDatos() {
		List<Rutina> rutinas = new ArrayList<>();
		
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
		     Statement stmt = con.createStatement()) {
			String sql = "SELECT * FROM RUTINA WHERE ID >= 0";
			
			//Se ejecuta la sentencia y se obtiene el ResultSet con los resutlados
			ResultSet rs = stmt.executeQuery(sql);			
			Rutina rutina;
			
			//Se recorre el ResultSet y se crean objetos Cliente
			while (rs.next()) {
				rutina = new Rutina(rs.getString("NOMBRE"), Objetivo_de_la_sesion.valueOf(rs.getString("OBJETIVO")),  convertirArrayALista(rs.getArray("LISTA_EJ")));
				rutina.setId(rs.getInt("ID"));
				
				//Se inserta cada nuevo cliente en la lista de clientes
				rutinas.add(rutina);
			}
			
			//Se cierra el ResultSet
			rs.close();
			
			System.out.format("\n\n- Se han recuperado %d rutinas...", rutinas.size());			
		} catch (Exception ex) {
			System.err.format("\n\n* Error al obtener datos de la BBDD: %s", ex.getMessage());
			ex.printStackTrace();						
		}		
		
		return rutinas;
	}

	public void borrarDatos() {
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
		     Statement stmt = con.createStatement()) {
			//Se ejecuta la sentencia de borrado de datos
			String sql = "DELETE FROM RUTINA;";			
			int result = stmt.executeUpdate(sql);
			
			System.out.format("\n\n- Se han borrado %d rutinas", result);
		} catch (Exception ex) {
			System.err.format("\n\n* Error al borrar datos de la BBDD: %s", ex.getMessage());
			ex.printStackTrace();						
		}		
	}	
	
	// IA FUENTE EXTERNA: ChatGPT
	private ArrayList<Ejercicio> convertirArrayALista(Array sqlArray) throws SQLException {
	    if (sqlArray == null) {
	        return new ArrayList<>(); // Devuelve una lista vacía si el arreglo es nulo
	    }
	    Object[] objetos = (Object[]) sqlArray.getArray(); // Convierte el arreglo SQL a un arreglo de objetos
	    ArrayList<Ejercicio> listaEjercicios = new ArrayList<>();
	    for (Object obj : objetos) {
	        listaEjercicios.add((Ejercicio) obj); // Convierte cada objeto a tipo Ejercicio
	    }
	    return listaEjercicios;
	}
	
	
}