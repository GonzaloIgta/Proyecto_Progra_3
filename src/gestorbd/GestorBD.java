package gestorbd;

import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import clases_de_apyo.Ejercicio;
import clases_de_apyo.Ejercicio_Natacion;
import clases_de_apyo.Ejercicio_Natacion.EstiloNat;
import clases_de_apyo.Ejercicio_cardio;
import clases_de_apyo.Ejercicio_gym;
import clases_de_apyo.Rutina;
import clases_de_apyo.Rutina.Objetivo_de_la_sesion;

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
			String sqlrutina = "CREATE TABLE IF NOT EXISTS RUTINA (\n" + " NOMBRE VARCHAR(20),\n"
					+ " OBJETIVO VARCHAR(20),\n" + " PRIMARY KEY(NOMBRE, OBJETIVO)\n" + ");";

			String sqlejgym = "CREATE TABLE IF NOT EXISTS EJERCICIO_GYM (\n" + " NOMBRE VARCHAR(20),\n"
					+ " SERIES INT,\n" + " PESO INT,\n" + " PRIMARY KEY(NOMBRE, SERIES, PESO)\n" + ");";

			String sqlejcard = "CREATE TABLE IF NOT EXISTS EJERCICIO_CARDIO (\n" + " NOMBRE VARCHAR(20),\n"
					+ " DURACION INT,\n" + " PRIMARY KEY(NOMBRE, DURACION)\n" + ");";

			String sqlejnat = "CREATE TABLE IF NOT EXISTS EJERCICIO_NATACION (\n" + " NOMBRE VARCHAR(20),\n"
					+ " ESTILO_NATACION VARCHAR(20),\n" + " DURACION INT,\n"
					+ " PRIMARY KEY(NOMBRE, ESTILO_NATACION, DURACION)\n" + ");";

			String sqletienegym = "CREATE TABLE IF NOT EXISTS TIENE_GYM (\n" + " RUTINA_NOMBRE VARCHAR(20),\n"
					+ " RUTINA_OBJETIVO VARCHAR(20),\n" + " EJERCICIO_NOMBRE VARCHAR(20),\n" + " SERIES INT,\n"
					+ " PESO INT,\n" + " PRIMARY KEY(RUTINA_NOMBRE, RUTINA_OBJETIVO, EJERCICIO_NOMBRE, SERIES, PESO),\n"
					+ " FOREIGN KEY(RUTINA_NOMBRE, RUTINA_OBJETIVO) REFERENCES RUTINA(NOMBRE, OBJETIVO) ON DELETE CASCADE,\n"
					+ " FOREIGN KEY(EJERCICIO_NOMBRE, SERIES, PESO) REFERENCES EJERCICIO_GYM(NOMBRE, SERIES, PESO) ON DELETE CASCADE\n"
					+ ");";

			String sqletienecardio = "CREATE TABLE IF NOT EXISTS TIENE_CARDIO (\n" + " RUTINA_NOMBRE VARCHAR(20),\n"
					+ " RUTINA_OBJETIVO VARCHAR(20),\n" + " EJERCICIO_NOMBRE VARCHAR(20),\n" + " DURACION INT,\n"
					+ " PRIMARY KEY(RUTINA_NOMBRE, RUTINA_OBJETIVO, EJERCICIO_NOMBRE, DURACION),\n"
					+ " FOREIGN KEY(RUTINA_NOMBRE, RUTINA_OBJETIVO) REFERENCES RUTINA(NOMBRE, OBJETIVO) ON DELETE CASCADE,\n"
					+ " FOREIGN KEY(EJERCICIO_NOMBRE, DURACION) REFERENCES EJERCICIO_CARDIO(NOMBRE, DURACION) ON DELETE CASCADE\n"
					+ ");";

			String sqletienenatacion = "CREATE TABLE IF NOT EXISTS TIENE_NATACION (\n" + " RUTINA_NOMBRE VARCHAR(20),\n"
					+ " RUTINA_OBJETIVO VARCHAR(20),\n" + " EJERCICIO_NOMBRE VARCHAR(20),\n"
					+ " ESTILO_NATACION VARCHAR(20),\n" + " DURACION INT,\n"
					+ " PRIMARY KEY(RUTINA_NOMBRE, RUTINA_OBJETIVO, EJERCICIO_NOMBRE, ESTILO_NATACION, DURACION),\n"
					+ " FOREIGN KEY(RUTINA_NOMBRE, RUTINA_OBJETIVO) REFERENCES RUTINA(NOMBRE, OBJETIVO) ON DELETE CASCADE,\n"
					+ " FOREIGN KEY(EJERCICIO_NOMBRE, ESTILO_NATACION, DURACION) REFERENCES EJERCICIO_NATACION(NOMBRE, ESTILO_NATACION, DURACION) ON DELETE CASCADE\n"
					+ ");";

			String sqlRutinaSemanal = "CREATE TABLE IF NOT EXISTS RUTINA_SEMANAL (\n" + " NOMBRE VARCHAR(20),\n"
					+ " PRIMARY KEY(NOMBRE)\n" + ");";

			try (Connection con = DriverManager.getConnection(connectionString);
					PreparedStatement pStmt1 = con.prepareStatement(sqlrutina);
					PreparedStatement pStmt3 = con.prepareStatement(sqlejcard);
					PreparedStatement pStmt4 = con.prepareStatement(sqlejnat);
					PreparedStatement pStmt5 = con.prepareStatement(sqletienegym);
					PreparedStatement pStmt7 = con.prepareStatement(sqletienenatacion);
					PreparedStatement pStmt6 = con.prepareStatement(sqletienecardio);
					PreparedStatement pStmt2 = con.prepareStatement(sqlejgym);
					PreparedStatement pStmt8 = con.prepareStatement(sqlRutinaSemanal)) {

				crearTablaUsuarios();
				if (!pStmt1.execute() && !pStmt2.execute() && !pStmt3.execute() && !pStmt4.execute()
						&& !pStmt5.execute() && !pStmt6.execute() && !pStmt7.execute() && !pStmt8.execute()) {
					System.out.println("Base de datos creada con éxito.");
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
			String sqlDropRutinaSemanal = "DROP TABLE IF EXISTS RUTINA_SEMANAL;";

			try (Connection con = DriverManager.getConnection(connectionString);
					PreparedStatement pStmt1 = con.prepareStatement(sqlDropTieneNatacion);
					PreparedStatement pStmt2 = con.prepareStatement(sqlDropTieneCardio);
					PreparedStatement pStmt3 = con.prepareStatement(sqlDropTieneGym);
					PreparedStatement pStmt4 = con.prepareStatement(sqlDropEjNatacion);
					PreparedStatement pStmt5 = con.prepareStatement(sqlDropEjCardio);
					PreparedStatement pStmt6 = con.prepareStatement(sqlDropEjGym);
					PreparedStatement pStmt7 = con.prepareStatement(sqlDropRutina);
					PreparedStatement pStmt8 = con.prepareStatement(sqlDropRutinaSemanal)) {

				pStmt1.execute();
				pStmt2.execute();
				pStmt3.execute();
				pStmt4.execute();
				pStmt5.execute();
				pStmt6.execute();
				pStmt7.execute();
				pStmt8.execute();

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
			String sqlDeleteRutinaSemanal = "DELETE FROM RUTINA_SEMANAL;";

			try (Connection con = DriverManager.getConnection(connectionString);
					PreparedStatement pStmt1 = con.prepareStatement(sqlDeleteTieneNatacion);
					PreparedStatement pStmt2 = con.prepareStatement(sqlDeleteTieneCardio);
					PreparedStatement pStmt3 = con.prepareStatement(sqlDeleteTieneGym);
					PreparedStatement pStmt4 = con.prepareStatement(sqlDeleteEjNatacion);
					PreparedStatement pStmt5 = con.prepareStatement(sqlDeleteEjCardio);
					PreparedStatement pStmt6 = con.prepareStatement(sqlDeleteEjGym);
					PreparedStatement pStmt7 = con.prepareStatement(sqlDeleteRutina);
					PreparedStatement pStmt8 = con.prepareStatement(sqlDeleteRutinaSemanal)) {

				// Se ejecutan las sentencias de borrado de los datos
				pStmt1.execute();
				pStmt2.execute();
				pStmt3.execute();
				pStmt4.execute();
				pStmt5.execute();
				pStmt6.execute();
				pStmt7.execute();
				pStmt8.execute();

				System.out.println("Se han borrado los datos de todas las tablas");
			} catch (Exception ex) {
				System.out.println(String.format("Error al borrar los datos: %s", ex.getMessage()));
			}
		}
	}

	public void insertarEjercicio(Ejercicio ejercicio) {
		String sqlVerificar = "SELECT 1 FROM ";
		String sqlInsertar = "INSERT INTO ";
		String tabla = "";

		try (Connection con = DriverManager.getConnection(connectionString)) {
			PreparedStatement verificarStmt;
			PreparedStatement insertarStmt;

			if (ejercicio instanceof Ejercicio_gym) {
				Ejercicio_gym gym = (Ejercicio_gym) ejercicio;
				tabla = "EJERCICIO_GYM";
				sqlVerificar += tabla + " WHERE NOMBRE = ? AND SERIES = ? AND PESO = ?";
				sqlInsertar += tabla + " (NOMBRE, SERIES, PESO) VALUES (?, ?, ?)";

				// Verificar si el ejercicio ya existe
				verificarStmt = con.prepareStatement(sqlVerificar);
				verificarStmt.setString(1, gym.getNombre());
				verificarStmt.setInt(2, gym.getSeries());
				verificarStmt.setInt(3, gym.getPeso());
				ResultSet rs = verificarStmt.executeQuery();

				if (!rs.next()) {
					// Insertar si no existe
					insertarStmt = con.prepareStatement(sqlInsertar);
					insertarStmt.setString(1, gym.getNombre());
					insertarStmt.setInt(2, gym.getSeries());
					insertarStmt.setInt(3, gym.getPeso());
					insertarStmt.executeUpdate();
					System.out.println("Ejercicio gym insertado correctamente.");
				} else {
					System.out.println("El ejercicio gym ya existe en la base de datos.");
				}
			} else if (ejercicio instanceof Ejercicio_cardio) {
				Ejercicio_cardio cardio = (Ejercicio_cardio) ejercicio;
				tabla = "EJERCICIO_CARDIO";
				sqlVerificar += tabla + " WHERE NOMBRE = ? AND DURACION = ?";
				sqlInsertar += tabla + " (NOMBRE, DURACION) VALUES (?, ?)";

				verificarStmt = con.prepareStatement(sqlVerificar);
				verificarStmt.setString(1, cardio.getNombre());
				verificarStmt.setInt(2, cardio.getDuracion());
				ResultSet rs = verificarStmt.executeQuery();

				if (!rs.next()) {
					insertarStmt = con.prepareStatement(sqlInsertar);
					insertarStmt.setString(1, cardio.getNombre());
					insertarStmt.setInt(2, cardio.getDuracion());
					insertarStmt.executeUpdate();
					System.out.println("Ejercicio cardio insertado correctamente.");
				} else {
					System.out.println("El ejercicio cardio ya existe en la base de datos.");
				}
			} else if (ejercicio instanceof Ejercicio_Natacion) {
				Ejercicio_Natacion natacion = (Ejercicio_Natacion) ejercicio;
				tabla = "EJERCICIO_NATACION";
				sqlVerificar += tabla + " WHERE NOMBRE = ? AND ESTILO_NATACION = ? AND DURACION = ?";
				sqlInsertar += tabla + " (NOMBRE, ESTILO_NATACION, DURACION) VALUES (?, ?, ?)";

				verificarStmt = con.prepareStatement(sqlVerificar);
				verificarStmt.setString(1, natacion.getNombre());
				verificarStmt.setString(2, natacion.getEstilo().name()); // Usar el nombre del estilo como string
				verificarStmt.setInt(3, natacion.getDuracion());
				ResultSet rs = verificarStmt.executeQuery();

				if (!rs.next()) {
					insertarStmt = con.prepareStatement(sqlInsertar);
					insertarStmt.setString(1, natacion.getNombre());
					insertarStmt.setString(2, natacion.getEstilo().name());
					insertarStmt.setInt(3, natacion.getDuracion());
					insertarStmt.executeUpdate();
					System.out.println("Ejercicio de natación insertado correctamente.");
				} else {
					System.out.println("El ejercicio de natación ya existe en la base de datos.");
				}
			} else {
				System.out.println("Tipo de ejercicio desconocido.");
			}
		} catch (Exception e) {
			System.out.println("Error al insertar el ejercicio: " + e.getMessage());
		}
	}

	private void insertarRelacionEjercicio(Rutina rutina, Ejercicio ejercicio, Connection con) throws SQLException {
		String sqlInsertarRelacion = "";
		if (ejercicio instanceof Ejercicio_gym) {
			sqlInsertarRelacion = "INSERT INTO TIENE_GYM (RUTINA_NOMBRE, RUTINA_OBJETIVO, EJERCICIO_NOMBRE, SERIES, PESO) VALUES (?, ?, ?, ?, ?)";
			try (PreparedStatement stmt = con.prepareStatement(sqlInsertarRelacion)) {
				Ejercicio_gym gym = (Ejercicio_gym) ejercicio;
				stmt.setString(1, rutina.getNombre());
				stmt.setString(2, rutina.getObjetivo().toString());
				stmt.setString(3, gym.getNombre());
				stmt.setInt(4, gym.getSeries());
				stmt.setInt(5, gym.getPeso());
				stmt.executeUpdate();
			}
		} else if (ejercicio instanceof Ejercicio_cardio) {
			sqlInsertarRelacion = "INSERT INTO TIENE_CARDIO (RUTINA_NOMBRE, RUTINA_OBJETIVO, EJERCICIO_NOMBRE, DURACION) VALUES (?, ?, ?, ?)";
			try (PreparedStatement stmt = con.prepareStatement(sqlInsertarRelacion)) {
				Ejercicio_cardio cardio = (Ejercicio_cardio) ejercicio;
				stmt.setString(1, rutina.getNombre());
				stmt.setString(2, rutina.getObjetivo().toString());
				stmt.setString(3, cardio.getNombre());
				stmt.setInt(4, cardio.getDuracion());
				stmt.executeUpdate();
			}
		} else if (ejercicio instanceof Ejercicio_Natacion) {
			sqlInsertarRelacion = "INSERT INTO TIENE_NATACION (RUTINA_NOMBRE, RUTINA_OBJETIVO, EJERCICIO_NOMBRE, ESTILO_NATACION, DURACION) VALUES (?, ?, ?, ?, ?)";
			try (PreparedStatement stmt = con.prepareStatement(sqlInsertarRelacion)) {
				Ejercicio_Natacion natacion = (Ejercicio_Natacion) ejercicio;
				stmt.setString(1, rutina.getNombre());
				stmt.setString(2, rutina.getObjetivo().toString());
				stmt.setString(3, natacion.getNombre());
				stmt.setString(4, natacion.getEstilo().name());
				stmt.setInt(5, natacion.getDuracion());
				stmt.executeUpdate();
			}
		}
	}

	private void insertarRelacionEjercicio(String nombreRutina, Objetivo_de_la_sesion objetivoRutina, Ejercicio ejercicio, Connection con) {
	    String tabla = "";

	    // Determina la tabla correcta según el tipo del ejercicio
	    if (ejercicio instanceof Ejercicio_gym) {
	        tabla = "TIENE_GYM";
	    } else if (ejercicio instanceof Ejercicio_Natacion) {
	        tabla = "TIENE_NATACION";
	    } else if (ejercicio instanceof Ejercicio_cardio) {
	        tabla = "TIENE_CARDIO";
	    } else {
	        System.out.println("Tipo de ejercicio no reconocido.");
	        return; // Sale si el tipo de ejercicio no es válido
	    }

	    // Construye la consulta de inserción utilizando los valores de nombre y objetivo
	    String sql = "INSERT INTO " + tabla + " (EJERCICIO_NOMBRE, RUTINA_NOMBRE, RUTINA_OBJETIVO) VALUES (?, ?, ?);";

	    try (PreparedStatement insertarRelacionStmt = con.prepareStatement(sql)) {
	        // Asigna los valores a los parámetros
	        insertarRelacionStmt.setString(1, ejercicio.getNombre());  // Nombre del ejercicio
	        insertarRelacionStmt.setString(2, nombreRutina);           // Nombre de la rutina
	        insertarRelacionStmt.setString(3, objetivoRutina.toString());  // Objetivo de la rutina
	        // Ejecuta la actualización
	        int filasInsertadas = insertarRelacionStmt.executeUpdate();
	        if (filasInsertadas > 0) {
	            System.out.println("Relación insertada en la tabla " + tabla + ".");
	        } else {
	            System.out.println("No se insertó ninguna relación.");
	        }
	    } catch (SQLException e) {
	        System.out.println("Error al insertar la relación: " + e.getMessage());
	    }
	}


	// ESTE CREO QUE ESTA BIEN
	public List<Rutina> getTodasRutinas() {
	    List<Rutina> rutinas = new ArrayList<>();

	    String sqlRutinas = "SELECT NOMBRE, OBJETIVO FROM RUTINA";
	    String sqlEjerciciosGym = "SELECT E.NOMBRE, E.SERIES, E.PESO FROM EJERCICIO_GYM E WHERE E.NOMBRE IN " +
	                              "(SELECT T.EJERCICIO_NOMBRE FROM TIENE_GYM T WHERE T.RUTINA_NOMBRE = ? AND T.RUTINA_OBJETIVO = ?)";
	    String sqlEjerciciosCardio = "SELECT E.NOMBRE, E.DURACION FROM EJERCICIO_CARDIO E WHERE E.NOMBRE IN " +
	                                 "(SELECT T.EJERCICIO_NOMBRE FROM TIENE_CARDIO T WHERE T.RUTINA_NOMBRE = ? AND T.RUTINA_OBJETIVO = ?)";
	    String sqlEjerciciosNatacion = "SELECT E.NOMBRE, E.ESTILO_NATACION, E.DURACION FROM EJERCICIO_NATACION E WHERE E.NOMBRE IN " +
	                                   "(SELECT T.EJERCICIO_NOMBRE FROM TIENE_NATACION T WHERE T.RUTINA_NOMBRE = ? AND T.RUTINA_OBJETIVO = ?)";

	    try (Connection con = DriverManager.getConnection(connectionString);
	         PreparedStatement stmtRutinas = con.prepareStatement(sqlRutinas);
	         ResultSet rsRutinas = stmtRutinas.executeQuery()) {

	        while (rsRutinas.next()) {
	            String nombreRutina = rsRutinas.getString("NOMBRE");
	            String objetivoRutinaString = rsRutinas.getString("OBJETIVO");

	            Objetivo_de_la_sesion objetivoRutina = null;
	            try {
	                objetivoRutina = Objetivo_de_la_sesion.valueOf(objetivoRutinaString);
	            } catch (IllegalArgumentException e) {
	                System.out.println("Error: Objetivo de la rutina no válido en la base de datos: " + objetivoRutinaString);
	            }

	            Rutina rutina = new Rutina(nombreRutina, objetivoRutina);

	            // Obtener ejercicios de gimnasio asociados a la rutina
	            try (PreparedStatement stmtGym = con.prepareStatement(sqlEjerciciosGym)) {
	                stmtGym.setString(1, nombreRutina);
	                stmtGym.setString(2, objetivoRutinaString);
	                try (ResultSet rsGym = stmtGym.executeQuery()) {
	                    while (rsGym.next()) {
	                        String nombreEjercicioGym = rsGym.getString("NOMBRE");
	                        int series = rsGym.getInt("SERIES");
	                        int peso = rsGym.getInt("PESO");
	                        Ejercicio_gym ejercicioGym = new Ejercicio_gym(nombreEjercicioGym, "", series, peso);
	                        rutina.añadir_ejercicio(ejercicioGym);
	                    }
	                }
	            }

	            // Obtener ejercicios de cardio asociados a la rutina
	            try (PreparedStatement stmtCardio = con.prepareStatement(sqlEjerciciosCardio)) {
	                stmtCardio.setString(1, nombreRutina);
	                stmtCardio.setString(2, objetivoRutinaString);
	                try (ResultSet rsCardio = stmtCardio.executeQuery()) {
	                    while (rsCardio.next()) {
	                        String nombreEjercicioCardio = rsCardio.getString("NOMBRE");
	                        int duracion = rsCardio.getInt("DURACION");
	                        Ejercicio_cardio ejercicioCardio = new Ejercicio_cardio(nombreEjercicioCardio, "", duracion);
	                        rutina.añadir_ejercicio(ejercicioCardio);
	                    }
	                }
	            }

	            // Obtener ejercicios de natación asociados a la rutina
	            try (PreparedStatement stmtNatacion = con.prepareStatement(sqlEjerciciosNatacion)) {
	                stmtNatacion.setString(1, nombreRutina);
	                stmtNatacion.setString(2, objetivoRutinaString);
	                try (ResultSet rsNatacion = stmtNatacion.executeQuery()) {
	                    while (rsNatacion.next()) {
	                        String nombreEjercicioNatacion = rsNatacion.getString("NOMBRE");
	                        String estiloNatacionString = rsNatacion.getString("ESTILO_NATACION");

	                        EstiloNat estiloNatacion = null;
	                        try {
	                            estiloNatacion = EstiloNat.valueOf(estiloNatacionString);
	                        } catch (IllegalArgumentException e) {
	                            System.out.println("Error: Estilo de natación no válido en la base de datos: " + estiloNatacionString);
	                        }

	                        int duracion = rsNatacion.getInt("DURACION");
	                        Ejercicio_Natacion ejercicioNatacion = new Ejercicio_Natacion(nombreEjercicioNatacion, "", estiloNatacion, duracion);
	                        rutina.añadir_ejercicio(ejercicioNatacion);
	                    }
	                }
	            }

	            rutinas.add(rutina);
	          
	        }
	    } catch (Exception e) {
	        System.out.println("Error al obtener las rutinas: " + e.getMessage());
	    }
	    return rutinas;
	}

	public void insertarRutina(Rutina rutina) {
	    String sqlVerificarRutina = "SELECT 1 FROM RUTINA WHERE NOMBRE = ? AND OBJETIVO = ?";
	    String sqlInsertarRutina = "INSERT INTO RUTINA (NOMBRE, OBJETIVO) VALUES (?, ?)";
	    
	    try (Connection con = DriverManager.getConnection(connectionString)) {
	        con.setAutoCommit(false);  // Iniciar transacción
	        
	        // Verificar si la rutina ya existe
	        try (PreparedStatement verificarStmt = con.prepareStatement(sqlVerificarRutina)) {
	            verificarStmt.setString(1, rutina.getNombre());
	            verificarStmt.setString(2, rutina.getObjetivo().toString());
	            try (ResultSet rsRutina = verificarStmt.executeQuery()) {
	                if (!rsRutina.next()) {
	                    // Insertar rutina
	                    try (PreparedStatement insertarStmt = con.prepareStatement(sqlInsertarRutina)) {
	                        insertarStmt.setString(1, rutina.getNombre());
	                        insertarStmt.setString(2, rutina.getObjetivo().toString());
	                        insertarStmt.executeUpdate();
	                        System.out.println("Rutina insertada correctamente.");
	                    }
	                } else {
	                    System.out.println("La rutina ya existe en la base de datos.");
	                }
	            }
	            
	            // Insertar ejercicios y relaciones específicas
	            for (Ejercicio ejercicio : rutina.getLista_ejercicios()) {
	                // Insertar el ejercicio si no existe
	                insertarEjercicio(ejercicio);
	                
	                // Insertar la relación para cada ejercicio
	                insertarRelacionEjercicio(rutina.getNombre(), rutina.getObjetivo(), ejercicio, con);
	            }
	            
	            con.commit();  // Confirmar la transacción
	        } catch (SQLException e) {
	            con.rollback();  // Deshacer la transacción en caso de error
	            System.out.println("Error al insertar la rutina: " + e.getMessage());
	        }
	    } catch (Exception e) {
	    	
	        System.out.println("Error al conectar a la base de datos: " + e.getMessage());
	    }
	}

	public void crearTablaUsuarios() {
		String sqlCrearTabla = "CREATE TABLE IF NOT EXISTS usuarios (" + "usuario TEXT PRIMARY KEY, "
				+ "contraseña TEXT NOT NULL);";

		try (Connection con = DriverManager.getConnection(connectionString);
				PreparedStatement stmt = con.prepareStatement(sqlCrearTabla)) {
			stmt.execute();
			System.out.println("Tabla de usuarios creada correctamente.");
		} catch (SQLException e) {
			System.out.println("Error al crear la tabla de usuarios: " + e.getMessage());
		}
	}

	// Insertar usuario
	public boolean insertarUsuario(String usuario, String contraseña) {
		String sqlInsertar = "INSERT INTO usuarios (usuario, contraseña) VALUES (?, ?);";

		try (Connection con = DriverManager.getConnection(connectionString);
				PreparedStatement stmt = con.prepareStatement(sqlInsertar)) {
			stmt.setString(1, usuario);
			stmt.setString(2, contraseña);
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			if (e.getMessage().contains("PRIMARY")) {
				System.out.println("El usuario ya existe.");
			} else {
				System.out.println("Error al insertar el usuario: " + e.getMessage());
			}
		}
		return false;
	}

	// Verificar usuario y contraseña
	public boolean verificarUsuario(String usuario, String contraseña) {
		String sqlVerificar = "SELECT contraseña FROM usuarios WHERE usuario = ?;";

		try (Connection con = DriverManager.getConnection(connectionString);
				PreparedStatement stmt = con.prepareStatement(sqlVerificar)) {
			stmt.setString(1, usuario);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getString("contraseña").equals(contraseña);
			}
		} catch (SQLException e) {
			System.out.println("Error al verificar el usuario: " + e.getMessage());
		}
		return false;
	}
}
