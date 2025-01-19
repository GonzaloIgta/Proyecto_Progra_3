
package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.StyledEditorKit.ForegroundAction;

import db.GestorBD;
import domain.Ejercicio;
import domain.Ejercicio_Natacion;
import domain.Ejercicio_cardio;
import domain.Ejercicio_gym;
import domain.ProgressCircleUI;
import domain.Rescalar_imagen;
import domain.Rutina;

public class Planning extends JFrame {

	private static final long serialVersionUID = 1;

	private ArrayList<Rutina> rutinasGuardadas;
	private JButton botonAgregarRutina;
	private JButton eliminarBoton;
	private GestorBD gestor;
	private static int idRutinaSemanal;
	private ArrayList<Rutina> todasRutinas;
	private HashMap<String, ArrayList<Rutina>> mapaRutinasPorDia;

	public Planning(ArrayList<Rutina> rutinas, GestorBD gestor, String usuario) {
		this.gestor = gestor;
		this.rutinasGuardadas = rutinas;
		this.todasRutinas = new ArrayList<Rutina>();
		this.mapaRutinasPorDia = new HashMap<String, ArrayList<Rutina>>();

		ImageIcon icono = new ImageIcon("resources/images/deustoicon.png");
		this.setIconImage(icono.getImage());
		this.setTitle("Planning Semanal");

		float escalar = 0.7F; // una ventana al 50% del tamaño de la pantalla
		int ancho = (int) (Toolkit.getDefaultToolkit().getScreenSize().width * escalar);
		int alto = (int) (Toolkit.getDefaultToolkit().getScreenSize().height * escalar);
		this.setSize(ancho, alto);

		this.setResizable(false);

		this.setLocationRelativeTo(null);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panelPrincipal = new JPanel(new GridLayout(1, 7));

		JButton botonVolver = new JButton();
		botonVolver.setFocusable(false);
		Rescalar_imagen rescalar = new Rescalar_imagen();
		rescalar.setScaledImage(botonVolver, "resources/images/casa.png", 20, 20);

		botonVolver.addActionListener(new ActionListener() { // listener del boton

			@Override
			public void actionPerformed(ActionEvent e) {
				new Pagina_principal(gestor, usuario);
				dispose();
			}
		});

		

		JPanel panelVolver = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelVolver.add(botonVolver);

		String[] diaSemana = { "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo" };

		for (String dia : diaSemana) {
			mapaRutinasPorDia.put(dia, new ArrayList<Rutina>());
		}

		// IAG: ChatGPT
		// ADAPTADO: hemos cambiado los nombres de los paneles para adaptarlos a las necesidades de nuestro proyecto.

		for (String dia : diaSemana) {

			// Crear un panel para cada dia
			JPanel panelDia = new JPanel(new BorderLayout()); // Crea el panel para cada dia donde se almacenara nombre,
																// rutinas, y botones
			JLabel labelDia = new JLabel(dia, JLabel.CENTER); // Crea el label para los nombres de los dias
			JPanel panelRutinas = new JPanel(new GridLayout(6, 1)); // Crea panel para almacenar 5 rutinas y boton para
																	// añadir rutinas

			panelDia.setBorder(new LineBorder(Color.BLACK)); // Añade borde para separar cada dia

			panelRutinas.setBorder(new EmptyBorder(30, 0, 0, 0)); // Añade separacion vertical del panel de las rutinas
																	// (el del centro con el del north)

			JLabel mensajeSinRutinas = new JLabel("No hay ninguna rutina");
			mensajeSinRutinas.setForeground(Color.RED); // color de letra rojo
			mensajeSinRutinas.setHorizontalAlignment(JLabel.CENTER); // Aseguramos que el texto este centrado
			mensajeSinRutinas.setVerticalAlignment(JLabel.CENTER);
			mensajeSinRutinas.setPreferredSize(new Dimension(200, 60)); // Establecemos un tamaño preferido para que el
																		// texto pueda envolver

			panelRutinas.add(mensajeSinRutinas);

			int[] contadorRutina = { 0 };

			JButton botonAgregarRutina = new JButton("Añadir Rutina");

			botonAgregarRutina.setFocusable(false);

			botonAgregarRutina.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (contadorRutina[0] < 5) {

						mostrarRutinasGuardadas(panelRutinas, mensajeSinRutinas, contadorRutina, botonAgregarRutina,
								todasRutinas, dia);

					}
				}
			});

			panelDia.add(labelDia, BorderLayout.NORTH);
			panelDia.add(panelRutinas, BorderLayout.CENTER);
			panelDia.add(botonAgregarRutina, BorderLayout.SOUTH);

			panelPrincipal.add(panelDia);
		}

		add(panelPrincipal, BorderLayout.CENTER);
		add(panelVolver, BorderLayout.NORTH);

	}

	public void mostrarRutinasGuardadas(JPanel panelRutinas, JLabel mensajeSinRutinas, int[] contadorRutina,
			JButton botonAgregarRutina, ArrayList<Rutina> todasRutinas, String dia) {
		JDialog dialogoRutinas = new JDialog(this, "Seleccionar Rutina", true); // ventana modal
		dialogoRutinas.setSize(500, 400);
		dialogoRutinas.setLocationRelativeTo(this);

		ArrayList<Rutina> listaRutinas = obtenerRutinasGuardadas();

		String[] columnas = { "Nombre de Rutina", "Objetivo" };

		DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0) {
			private static final long serialVersionUID = -7180503917040117918L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return column == 2; // Solo hacer editable la columna de botones
			}
		};

		for (Rutina rutina : listaRutinas) {
			modeloTabla.addRow(new Object[] { rutina.getNombre(), rutina.getObjetivo() });
		}

		JTable tablaRutinas = new JTable(modeloTabla);

		tablaRutinas.setFont(new Font("Arial", Font.PLAIN, 14));

		tablaRutinas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scrollPane = new JScrollPane(tablaRutinas);

		dialogoRutinas.add(scrollPane, BorderLayout.CENTER);

		JButton botonSeleccionar = new JButton("Agregar Rutina");

		botonSeleccionar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int filaSeleccionada = tablaRutinas.getSelectedRow();

				if (filaSeleccionada != -1) {
					Rutina rutinaSeleccionada = listaRutinas.get(filaSeleccionada);

					// Remover mensaje de "No hay ninguna rutina" si existe
					if (mensajeSinRutinas.getParent() != null) {
						panelRutinas.remove(mensajeSinRutinas);
					}

					// Crear botón con el nombre de la rutina
					JButton botonRutina = new JButton(rutinaSeleccionada.getNombre());
					botonRutina.setHorizontalAlignment(JButton.CENTER);
					botonRutina.setFocusable(false);

					// Añadir acción al botón para iniciar la rutina
					botonRutina.addActionListener(evt -> {
						iniciarRutina(Planning.this, rutinaSeleccionada, 10); // Cambia 10 por el tiempo de descanso
																				// deseado
					});

					// Agregar el botón al panel de rutinas
					panelRutinas.add(botonRutina);

					// Incrementar el contador de rutinas
					contadorRutina[0]++;

					// Desactivar el botón "Añadir Rutina" si se alcanzan las 5 rutinas
					if (contadorRutina[0] >= 5) {
						botonAgregarRutina.setEnabled(false);
					}

					// Añadir la rutina al mapa usando el método definido
					agregarRutina(dia, rutinaSeleccionada);

					// Refrescar el panel para reflejar los cambios
					panelRutinas.revalidate();
					panelRutinas.repaint();

					// Cerrar el diálogo de selección de rutina
					dialogoRutinas.dispose();
				} else {
					JOptionPane.showMessageDialog(dialogoRutinas, "Seleccione una rutina para agregar", "Advertencia",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		dialogoRutinas.add(botonSeleccionar, BorderLayout.SOUTH);
		dialogoRutinas.setVisible(true);

	}

	public void iniciarRutina(JFrame frame, Rutina rutinaSeleccionada, int tiempoDescanso) {
		JFrame ventanaRutina = new JFrame("Ejecutando Rutina: " + rutinaSeleccionada.getNombre());
		ventanaRutina.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		ventanaRutina.setSize(600, 400);
		ventanaRutina.setLayout(new BorderLayout());

		JPanel panelEjercicio = new JPanel();
		panelEjercicio.setLayout(new BoxLayout(panelEjercicio, BoxLayout.Y_AXIS));

		JLabel labelEjercicio = new JLabel("Preparado para comenzar");
		labelEjercicio.setFont(new Font("Arial", Font.BOLD, 18));
		labelEjercicio.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel fotoEjercicio = new JLabel();
		fotoEjercicio.setHorizontalAlignment(SwingConstants.CENTER);

		JProgressBar progressBar = new JProgressBar();
		//FUENTE EXTERNA
		//EXTENERNO (https://stackoverflow.com/questions/36594680/how-to-create-a-circular-progress-component-in-java-swing)
		//SIN CAMBIOS
		progressBar.setUI(new ProgressCircleUI());
		progressBar.setForeground(Color.black);
		progressBar.setValue(100);
		progressBar.setMaximum(100);

		JButton botonCompletarSerie = new JButton("Completar Serie");
		botonCompletarSerie.setEnabled(false);

		panelEjercicio.add(labelEjercicio);
		panelEjercicio.add(Box.createVerticalStrut(20)); // Espaciado
		panelEjercicio.add(fotoEjercicio);
		panelEjercicio.add(Box.createVerticalStrut(20));
		panelEjercicio.add(progressBar);
		panelEjercicio.add(Box.createVerticalStrut(20));
		panelEjercicio.add(botonCompletarSerie);

		ventanaRutina.add(panelEjercicio, BorderLayout.CENTER);

		JButton botonCancelar = new JButton("Cancelar Rutina");
		botonCancelar.addActionListener(e -> ventanaRutina.dispose());
		ventanaRutina.add(botonCancelar, BorderLayout.SOUTH);

		ventanaRutina.setLocationRelativeTo(null);
		ventanaRutina.setVisible(true);

		Rescalar_imagen rescaler = new Rescalar_imagen(); // Instancia de la clase para redimensionar las imágenes

		// Hilo para gestionar la ejecución de ejercicios
		new Thread(() -> {
			for (Ejercicio ejercicio : rutinaSeleccionada.getLista_ejercicios()) {
				SwingUtilities.invokeLater(() -> {
					botonCompletarSerie.setVisible(false);
					labelEjercicio.setText("Ejercicio: " + ejercicio.getNombre());
					if (ejercicio instanceof Ejercicio_cardio) {
						labelEjercicio.setText(labelEjercicio.getText() + " - Duración: "
								+ ((Ejercicio_cardio) ejercicio).getDuracion() + " min.");
						botonCompletarSerie.setVisible(false); // Ocultar botón para ejercicios de cardio
					} else if (ejercicio instanceof Ejercicio_Natacion) {
						labelEjercicio.setText(
								labelEjercicio.getText() + " - Estilo: " + ((Ejercicio_Natacion) ejercicio).getEstilo()
										+ " " + ((Ejercicio_Natacion) ejercicio).getDuracion() + " min");
						botonCompletarSerie.setVisible(false); // Ocultar botón para ejercicios de natación
					} else if (ejercicio instanceof Ejercicio_gym) {
						Ejercicio_gym gym = (Ejercicio_gym) ejercicio;
						labelEjercicio.setText(labelEjercicio.getText() + " - Series: " + gym.getSeries() + ", Peso: "
								+ gym.getPeso() + " kg");
						botonCompletarSerie.setVisible(true); // Mostrar botón para ejercicios de gym
						botonCompletarSerie.setEnabled(true);
					}

					String photoPath = ejercicio.getUbicacion_foto();
					if (photoPath != null && !photoPath.isEmpty()) {
						rescaler.setScaledImage(fotoEjercicio, photoPath, 300, 200); // Redimensiona la foto
					} else {
						fotoEjercicio.setIcon(null); // Si no hay foto, se elimina la imagen
					}
				});

				if (ejercicio instanceof Ejercicio_gym) {
					Ejercicio_gym gym = (Ejercicio_gym) ejercicio;
					botonCompletarSerie.setVisible(true);
					SwingUtilities.invokeLater(() -> progressBar.setValue(0)); // Restablecer la barra de progreso a 0
																				// antes de las series

					for (int serie = 0; serie <= gym.getSeries(); serie++) {
						final int serieActual = serie;
						final int totalSeries = gym.getSeries();

						SwingUtilities.invokeLater(() -> {
							labelEjercicio.setText(gym.getNombre() + " " + gym.getPeso() + " kg Serie " + serieActual
									+ " de " + totalSeries);
							// Actualizar la barra de progreso para reflejar el progreso actual
							progressBar.setValue((serieActual * 100) / totalSeries);
							progressBar.setString(serieActual + " de " + totalSeries); // Mostrar "serie actual / total
																						// series"
						});

						// Esperar a que el usuario complete la serie
						synchronized (botonCompletarSerie) {
							botonCompletarSerie.addActionListener(e -> {
								synchronized (botonCompletarSerie) {
									botonCompletarSerie.notify(); // Despertar el hilo principal para avanzar
								}
								// Después de completar la serie, actualizar la barra de progreso
								SwingUtilities
										.invokeLater(() -> progressBar.setValue((serieActual * 100) / totalSeries));
							});

							try {
								botonCompletarSerie.wait(); // Espera hasta que el usuario presione el botón
							} catch (InterruptedException e) {
								Thread.currentThread().interrupt();
								return;
							}
						}
					}
				} else {
					// Simular la duración del ejercicio (para cardio o natación)
					simularDuracion(progressBar,
							ejercicio instanceof Ejercicio_cardio ? ((Ejercicio_cardio) ejercicio).getDuracion() : 10);
				}

				// Periodo de descanso
				SwingUtilities.invokeLater(() -> labelEjercicio.setText("Descanso: " + tiempoDescanso + " seg."));
				botonCompletarSerie.setVisible(false);
				simularDuracion(progressBar, tiempoDescanso);
			}

			// Rutina completada
			SwingUtilities.invokeLater(() -> {
				labelEjercicio.setText("¡Rutina completada!");
				labelEjercicio.setHorizontalAlignment(SwingConstants.CENTER);
				progressBar.setVisible(false);
				ventanaRutina.remove(panelEjercicio);

				JPanel ventanaterminado = new JPanel(new BorderLayout());
				ventanaterminado.add(labelEjercicio, BorderLayout.NORTH);

				JLabel foto = new JLabel();
				foto.setHorizontalAlignment(SwingConstants.CENTER);
				Rescalar_imagen rescalar = new Rescalar_imagen();
				foto.setIcon(new ImageIcon("resources/images/fiesta.gif"));
				ventanaterminado.add(foto, BorderLayout.CENTER);

				ventanaRutina.add(ventanaterminado);

				botonCompletarSerie.setVisible(false); // Ocultar el botón cuando la rutina se complete

				Thread cambiafondo = new Thread(() -> {
					Color[] colores = { Color.YELLOW, Color.RED, Color.GREEN };
					while (true) {
						for (Color color : colores) {
							try {
								foto.setBackground(color);
								foto.setOpaque(true);
								Thread.sleep(500);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				});
				cambiafondo.start(); // Iniciar el hilo que cambia el color
			});

		}).start();
	}

	private void simularDuracion(JProgressBar progressBar, int duracion) {
		int intervalo = 1000;
		int pasos = duracion;
		int progresoPorPaso = 100 / pasos;

		for (int i = 0; i < pasos; i++) {
			try {
				Thread.sleep(intervalo);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				return;
			}
			final int progreso = (i + 1) * progresoPorPaso;
			SwingUtilities.invokeLater(() -> progressBar.setValue(progreso));
		}
	}

	private ArrayList<Rutina> obtenerRutinasGuardadas() {
		return rutinasGuardadas;

	}

	public void open() {
		setVisible(true);
	}

	public void agregarRutina(String dia, Rutina rutina) {
		ArrayList<Rutina> rutinas = mapaRutinasPorDia.get(dia);
		if (rutinas != null) {
			rutinas.add(rutina);
			//System.out.println("Rutina agregada a " + dia + ": " + rutina.getNombre());
		} else {
			System.out.println("Día inválido: " + dia);
		}
	}

}
