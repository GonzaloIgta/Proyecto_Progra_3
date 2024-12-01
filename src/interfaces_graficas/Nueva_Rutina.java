package interfaces_graficas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import clases_de_apyo.Ejercicio;
import clases_de_apyo.Ejercicio_Natacion;
import clases_de_apyo.Ejercicio_cardio;
import clases_de_apyo.Ejercicio_gym;
import clases_de_apyo.Rescalar_imagen;
import clases_de_apyo.Rutina;
import clases_de_apyo.Ejercicio_Natacion.EstiloNat;
import clases_de_apyo.Ejercicio_Natacion.TipoNat;
import clases_de_apyo.Ejercicio_cardio.TipoCardio;
import clases_de_apyo.Ejercicio_gym.EjAbdominales;
import clases_de_apyo.Ejercicio_gym.EjAductor;
import clases_de_apyo.Ejercicio_gym.EjAntebrazo;
import clases_de_apyo.Ejercicio_gym.EjBiceps;
import clases_de_apyo.Ejercicio_gym.EjCuadriceps;
import clases_de_apyo.Ejercicio_gym.EjEspaldaInferior;
import clases_de_apyo.Ejercicio_gym.EjEspaldaSuperior;
import clases_de_apyo.Ejercicio_gym.EjFemoral;
import clases_de_apyo.Ejercicio_gym.EjGluteo;
import clases_de_apyo.Ejercicio_gym.EjHombro;
import clases_de_apyo.Ejercicio_gym.EjIsquiotibiales;
import clases_de_apyo.Ejercicio_gym.EjPecho;
import clases_de_apyo.Ejercicio_gym.EjTriceps;
import clases_de_apyo.Ejercicio_gym.MuscBrazos;
import clases_de_apyo.Ejercicio_gym.MuscPierna;
import clases_de_apyo.Ejercicio_gym.MuscTorso;
import clases_de_apyo.Ejercicio_gym.PartesDelCuerpo;
import clases_de_apyo.Rutina.Objetivo_de_la_sesion;
import gestorbd.GestorBD;

public class Nueva_Rutina extends JFrame {
	private static final long serialVersionUID = 1L;

	private JTable tablaEjercicios;

	private DefaultTableModel modeloDatostablaEjercicios;
	private JPanel ventana_central_MuestraRutinas;
	private Rutinas_guardadas rutinas_guardadas;
	private Rescalar_imagen rescalar = new Rescalar_imagen();
	private GestorBD gestor;

	public Nueva_Rutina(GestorBD gestor) {
		this.rutinas_guardadas =  new Rutinas_guardadas(Pagina_principal.rutinas, gestor);
		this.gestor = gestor;
		// para que se cierre al darle a la x
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// titulo de la ventana
		setTitle("Nueva Rutina");

		// Creamos la ventana y definimos distribucion
		JPanel ventana_nuevaRutinaCONTENEDORA = new JPanel(new GridLayout(2, 1, 10, 0));
		JPanel ventana_nuevaRutinaUP = new JPanel(new BorderLayout());
		JPanel ventana_nuevaRutinaDOWN = new JPanel(new BorderLayout());
		JScrollPane scrollPane = new JScrollPane(this.tablaEjercicios);

		JPanel panelboxl = new JPanel();
		panelboxl.setLayout(new BoxLayout(panelboxl, BoxLayout.Y_AXIS));

		ventana_central_MuestraRutinas = new JPanel(new BorderLayout());

		// FUENTE-EXTERNA
		// URL: (https://chuidiang.org/index.php?title=Uso_de_Layouts)
		// ADAPTADO (hemos analizado el codigo para entender como funcionaba el layout y
		// modificado para añadirle los elementos que necesitabamos)
		JPanel parteArriba = new JPanel(new BorderLayout());

		JPanel layout_botones = new JPanel(new FlowLayout());
		layout_botones.setBorder(new TitledBorder("Selecciona el tipo de entrenamiento de la Rutina: "));
		//
		JButton boton_fuerza = new JButton("Fuerza");
		boton_fuerza.setFocusable(false);
		layout_botones.add(boton_fuerza);

		ActionListener listener_boton_fuerza = e -> {
			ventana_central_MuestraRutinas.removeAll();

			ventana_central_MuestraRutinas.add(init_tabla_fuerza());

			ventana_central_MuestraRutinas.revalidate(); // FUENTE EXTERNA: revalidate y repaint sacados con ayuda de
															// ChatGPT
			ventana_central_MuestraRutinas.repaint();
		};

		boton_fuerza.addActionListener(listener_boton_fuerza);

		JButton boton_cardio = new JButton("Cardio");
		boton_cardio.setFocusable(false);
		layout_botones.add(boton_cardio);

		ActionListener listener_boton_cardio = e -> {
			ventana_central_MuestraRutinas.removeAll();

			ventana_central_MuestraRutinas.add(init_tabla_cardio());

			ventana_central_MuestraRutinas.revalidate(); // FUENTE EXTERNA: revalidate y repaint sacados con ayuda de
															// ChatGPT
			ventana_central_MuestraRutinas.repaint();
		};

		boton_cardio.addActionListener(listener_boton_cardio);

		JButton boton_natacion = new JButton("Natacion");
		boton_natacion.setFocusable(false);
		layout_botones.add(boton_natacion);

		ActionListener listener_boton_natacion = e -> {
			ventana_central_MuestraRutinas.removeAll();

			ventana_central_MuestraRutinas.add(init_tabla_natacion());

			ventana_central_MuestraRutinas.revalidate(); // FUENTE EXTERNA: revalidate y repaint sacados con ayuda de
															// ChatGPT
			ventana_central_MuestraRutinas.repaint();
		};

		boton_natacion.addActionListener(listener_boton_natacion);

		JPanel panelBotonGuardar = new JPanel(new FlowLayout());

		// Crear el botón y asignarle la imagen
		JButton boton_GuardarRutina = new JButton();
		rescalar = new Rescalar_imagen();
		rescalar.setScaledImage(boton_GuardarRutina, "/resourses/images/GuardarIcono.png", 20, 20);
		boton_GuardarRutina.setFocusable(false);

		ActionListener listener_boton_GuardarRutina = e -> {

			JPanel panel = new JPanel();
			panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
			Objetivo_de_la_sesion objetivo = Objetivo_de_la_sesion.MUSCULACION;// OBJETIVO POR DEFECTO

			JTextField nombreField = new JTextField(20);
			panel.add(new JLabel("Nombre de la Rutina:"));
			panel.add(nombreField);
			JComboBox<Objetivo_de_la_sesion> comboBox = new JComboBox<>(Objetivo_de_la_sesion.values());
			panel.add(new JLabel("Selecciona el objetivo de la sesión:"));
			panel.add(comboBox);
			int opcion = JOptionPane.showConfirmDialog(null, panel, "Crear Rutina", JOptionPane.OK_CANCEL_OPTION);
			if (opcion == JOptionPane.OK_OPTION) {
				objetivo = (Objetivo_de_la_sesion) comboBox.getSelectedItem();
			}
			if (nombreField.getText() != null && !nombreField.getText().trim().isEmpty()) { // Fuente Externa: trim
																							// elimina espacios en
																							// blanco adicionales, de
																							// modo que si el usuario
																							// ingresó solo espacios,
																							// también se considera
																							// vacío.
				ArrayList<Ejercicio> ejercicios = new ArrayList<>();
				for (int fila = 0; fila < tablaEjercicios.getRowCount(); fila++) {
					Object valor = tablaEjercicios.getValueAt(fila, 0);
					int peso = 0;

					try {
						peso = (int) tablaEjercicios.getValueAt(fila, 4);
					} catch (Exception a) {
						// no hacer nada en caso de error
					}

					if (valor instanceof PartesDelCuerpo) {
						ejercicios.add(new Ejercicio_gym(tablaEjercicios.getValueAt(fila, 2).toString(),
								"ubicacion por definir",
								Integer.valueOf(tablaEjercicios.getValueAt(fila, 5).toString()), peso));
					} else if (valor instanceof TipoNat) {
						int duracion = 0; // valor por defecto
						try {
							duracion = Integer.valueOf(tablaEjercicios.getValueAt(fila, 3).toString());
						} catch (Exception a) {
							// no hacer nada en caso de error
						}

						ejercicios.add(new Ejercicio_Natacion(valor.toString(), "ubicacion por definir",
								EstiloNat.valueOf(tablaEjercicios.getValueAt(fila, 1).toString()), duracion));
					} else {
						int duracion = 0; // valor por defecto
						try {
							duracion = Integer.valueOf(tablaEjercicios.getValueAt(fila, 3).toString());
						} catch (Exception a) {
							// no hacer nada en caso de error
						}

						ejercicios.add(new Ejercicio_cardio(valor.toString(), "ubicacion por definir", duracion));
					}
				}

				Rutina rutina_a_añadir = new Rutina(nombreField.getText(), objetivo, ejercicios);
				Pagina_principal.rutinas.add(rutina_a_añadir);
				dispose();
				rutinas_guardadas.open();
			}

		};
		panelBotonGuardar.add(boton_GuardarRutina);
		boton_GuardarRutina.addActionListener(listener_boton_GuardarRutina);

		// Crear el botón y asignarle la imagen
		JButton boton_CancelarRutina = new JButton();
		rescalar = new Rescalar_imagen();
		rescalar.setScaledImage(boton_CancelarRutina, "/resourses/images/IconoCancelar.png", 20, 20);
		boton_CancelarRutina.setFocusable(false);

		// FUENTE EXTERNA: OptionDialog implementado con ayuda de Chat
		ActionListener listener_boton_CancelarRutina = e -> {
			int respuesta = JOptionPane.showOptionDialog(null, "¿Estás seguro de que quieres eliminar la Rutina?", "",
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);

			if (respuesta == JOptionPane.YES_OPTION) {
				dispose();
				rutinas_guardadas.open();
				;
			} else {

			}

		};

		boton_CancelarRutina.addActionListener(listener_boton_CancelarRutina);
		panelBotonGuardar.add(boton_CancelarRutina);

		parteArriba.add(panelBotonGuardar, BorderLayout.WEST);
		parteArriba.add(layout_botones, BorderLayout.CENTER);
		ventana_nuevaRutinaUP.add(parteArriba, BorderLayout.NORTH);

		// crear el boton de añadir fila a ejercicios

		JButton btnAñadirFila = new JButton("+");
		btnAñadirFila.setBackground(Color.WHITE);
		btnAñadirFila.setOpaque(true);

		ventana_nuevaRutinaUP.add(btnAñadirFila, BorderLayout.SOUTH);

		ActionListener listener_boton_Añadir_Fila = e -> {
			modeloDatostablaEjercicios.addRow(new Object[] { "", "", "", null });
		};

		btnAñadirFila.addActionListener(listener_boton_Añadir_Fila);

		ventana_nuevaRutinaUP.add(ventana_central_MuestraRutinas);

		ventana_nuevaRutinaUP.setBackground(new Color(130, 195, 65));

		// añadimos el icono de la aplicacion en la parte de arriba
		ImageIcon icono = new ImageIcon(this.getClass().getResource("/resourses/images/deustoicon.png"));
		this.setIconImage(icono.getImage());

		setResizable(false);

		// FUENTE-EXTERNA
		// URL: (https://www.forosdelweb.com/f45/ajuste-automatico-jframe-853529/)
		// SIN-CAMBIOS
		// Hacer que la pantalla se habra a la mitad del tamaño
		float escalar = 0.5F; // una ventana al 50% del tamaño de la pantalla
		int ancho = (int) (Toolkit.getDefaultToolkit().getScreenSize().width * escalar);
		int alto = (int) (Toolkit.getDefaultToolkit().getScreenSize().height * escalar);
		this.setSize(ancho, alto);

		// Centrar la ventana en la pantalla
		setLocationRelativeTo(null);

		// Hacemos visible la ventana principal

		ventana_central_MuestraRutinas.add(scrollPane, BorderLayout.CENTER);

		JScrollPane scrollPane2 = new JScrollPane(panelboxl);

		// crear boton de guardar/pasar datos a rutina
		JButton btnPasarARutina = new JButton("⬇Guardar En Rutina⬇");
		ActionListener listener_boton_Pasar_Rutina = e -> {
			JLabel lablnuevo = new JLabel();

			StringBuilder sb = new StringBuilder(); // FUENTE EXTERNA CHATGP: solo el sb

			int rowCount = tablaEjercicios.getRowCount();
			int columnCount = tablaEjercicios.getColumnCount();

			int row = 0;
			while (row < rowCount) {
				int column = 0;
				while (column < columnCount) {
					Object value = tablaEjercicios.getValueAt(row, column);

					if (value != null) {
						sb.append(value.toString()).append(" ");
					}

					column++;
				}

				sb.append("<br>");

				row++;
			}

			lablnuevo.setText("<html>" + sb.toString() + "</html>"); // FUENTE EXTERNA CHATGP

			panelboxl.add(lablnuevo);

			panelboxl.revalidate();
			panelboxl.repaint();

			scrollPane2.revalidate();
			scrollPane2.repaint();

		};

		btnPasarARutina.addActionListener(listener_boton_Pasar_Rutina);

		ventana_nuevaRutinaDOWN.add(btnPasarARutina, BorderLayout.NORTH);

		ventana_nuevaRutinaDOWN.add(scrollPane2, BorderLayout.CENTER);

		// Crear un borde a cada panel

		Color colorBorde = Color.decode("#ADD8E6");
		Border borde = BorderFactory.createLineBorder(colorBorde, 1);
		ventana_nuevaRutinaDOWN.setBorder(borde);
		ventana_nuevaRutinaUP.setBorder(borde);

		ventana_nuevaRutinaCONTENEDORA.add(ventana_nuevaRutinaUP);
		ventana_nuevaRutinaCONTENEDORA.add(ventana_nuevaRutinaDOWN);

		this.add(ventana_nuevaRutinaCONTENEDORA);

	}
	// hgghfghfghf

	public void open() {
		setVisible(true);
	}

	private JScrollPane init_tabla_fuerza() {
		// Crear cabecera de la tabla
		Vector<String> cabeceraTabla = new Vector<String>(
				Arrays.asList("Parte Del Cuerpo", "Musculo", "Ejercicio", "Foto", "Peso (kg)", "Series", ""));
		// Crear el modelo de datos de la tabla
		this.modeloDatostablaEjercicios = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceraTabla);
		this.tablaEjercicios = new JTable(this.modeloDatostablaEjercicios) {

			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {

				if (column == 3) {
					return false;
				} else {
					return true;
				}
			}
		};
		this.modeloDatostablaEjercicios.setRowCount(1);

		TableCellRenderer cellRenderer = new TableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				JLabel jlabel = new JLabel();

				// Configurar el texto y el color según el contenido y la columna
				if (value != null && !value.toString().isEmpty()) {
					jlabel.setText(value.toString());
					jlabel.setForeground(Color.BLACK); // Para asegurarse de que el texto del valor no esté en gris
				} else {
					// Placeholder según la columna
					switch (column) {
					case 0:
					case 1:
					case 2:
					case 5:
						jlabel.setText("Seleccionar ▼");
						jlabel.setForeground(Color.GRAY);
						break;
					case 4:
						jlabel.setText("Escribe peso");
						jlabel.setForeground(Color.GRAY);
						break;
					case 3:
						rescalar.setScaledImage(jlabel, "/resourses/images/falta_implementar.png", 60, 40);
						jlabel.setVerticalAlignment(SwingConstants.CENTER);
						jlabel.setHorizontalAlignment(SwingConstants.CENTER);
						break;
					default:
						jlabel.setText(""); // Celdas sin placeholder
						jlabel.setForeground(Color.BLACK);
						break;
					}
				}

				jlabel.setHorizontalAlignment(JLabel.CENTER);
				return jlabel;
			}
		};

		JButton btnEliminarFila = new JButton();
		rescalar = new Rescalar_imagen();
		rescalar.setScaledImage(btnEliminarFila, "/resourses/images/IconoCancelar.png", 20, 20);

		// Crear un editor para la celda que contiene el botón
		ButtonCellEditor editor = new ButtonCellEditor(btnEliminarFila);
		tablaEjercicios.getColumnModel().getColumn(6).setCellEditor(editor); // Columna "Acción"
		tablaEjercicios.getColumnModel().getColumn(6).setCellRenderer(new ButtonCellRenderer(btnEliminarFila));

		JComboBox<PartesDelCuerpo> cbParteCuerpo = new JComboBox<>(PartesDelCuerpo.values());
		tablaEjercicios.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(cbParteCuerpo));

		JComboBox<MuscBrazos> cbMusculoInicial = new JComboBox<>(MuscBrazos.values());
		tablaEjercicios.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(cbMusculoInicial));

		JComboBox<MuscBrazos> cbEjerciciosInicial = new JComboBox<>(MuscBrazos.values());
		tablaEjercicios.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(cbEjerciciosInicial));

		ActionListener parteCuerpolistener = e -> {

			if (cbParteCuerpo.getSelectedItem() != null) {
				if (cbParteCuerpo.getSelectedItem().equals(PartesDelCuerpo.Brazos)) {
					JComboBox<MuscBrazos> cbMusculo = new JComboBox<>(MuscBrazos.values());
					tablaEjercicios.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(cbMusculo));

					cbMusculo.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {

							if (cbMusculo.getSelectedItem() != null) {
								// Verificar qué músculo fue seleccionado y asignar el ComboBox de ejercicios
								// correspondiente
								if (cbMusculo.getSelectedItem().equals(MuscBrazos.Biceps)) {
									JComboBox<EjBiceps> cbEjercicio = new JComboBox<>(EjBiceps.values());
									tablaEjercicios.getColumnModel().getColumn(2)
											.setCellEditor(new DefaultCellEditor(cbEjercicio));

								} else if (cbMusculo.getSelectedItem().equals(MuscBrazos.Triceps)) {
									JComboBox<EjTriceps> cbEjercicio = new JComboBox<>(EjTriceps.values());
									tablaEjercicios.getColumnModel().getColumn(2)
											.setCellEditor(new DefaultCellEditor(cbEjercicio));

								} else if (cbMusculo.getSelectedItem().equals(MuscBrazos.Antebrazo)) {
									JComboBox<EjAntebrazo> cbEjercicio = new JComboBox<>(EjAntebrazo.values());
									tablaEjercicios.getColumnModel().getColumn(2)
											.setCellEditor(new DefaultCellEditor(cbEjercicio));

								} else {
									JComboBox<EjHombro> cbEjercicio = new JComboBox<>(EjHombro.values());
									tablaEjercicios.getColumnModel().getColumn(2)
											.setCellEditor(new DefaultCellEditor(cbEjercicio));
								}

							}

						}
					});

				} else if (cbParteCuerpo.getSelectedItem().equals(PartesDelCuerpo.Torso)) {
					JComboBox<MuscTorso> cbMusculo = new JComboBox<>(MuscTorso.values());
					tablaEjercicios.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(cbMusculo));

					// Pecho, Abdominales, Espalda_Superior, Espalda_Inferior;

					cbMusculo.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {

							if (cbMusculo.getSelectedItem() != null) {
								// Verificar qué músculo fue seleccionado y asignar el ComboBox de ejercicios
								// correspondiente
								if (cbMusculo.getSelectedItem().equals(MuscTorso.Pecho)) {
									JComboBox<EjPecho> cbEjercicio = new JComboBox<>(EjPecho.values());
									tablaEjercicios.getColumnModel().getColumn(2)
											.setCellEditor(new DefaultCellEditor(cbEjercicio));

								} else if (cbMusculo.getSelectedItem().equals(MuscTorso.Abdominales)) {
									JComboBox<EjAbdominales> cbEjercicio = new JComboBox<>(EjAbdominales.values());
									tablaEjercicios.getColumnModel().getColumn(2)
											.setCellEditor(new DefaultCellEditor(cbEjercicio));

								} else if (cbMusculo.getSelectedItem().equals(MuscTorso.Espalda_Superior)) {
									JComboBox<EjEspaldaSuperior> cbEjercicio = new JComboBox<>(
											EjEspaldaSuperior.values());
									tablaEjercicios.getColumnModel().getColumn(2)
											.setCellEditor(new DefaultCellEditor(cbEjercicio));

								} else {
									JComboBox<EjEspaldaInferior> cbEjercicio = new JComboBox<>(
											EjEspaldaInferior.values());
									tablaEjercicios.getColumnModel().getColumn(2)
											.setCellEditor(new DefaultCellEditor(cbEjercicio));
								}

							}

						}
					});

				} else if (cbParteCuerpo.getSelectedItem().equals(PartesDelCuerpo.Pierna)) {

					JComboBox<MuscPierna> cbMusculo = new JComboBox<>(MuscPierna.values());
					tablaEjercicios.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(cbMusculo));

					cbMusculo.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {

							if (cbMusculo.getSelectedItem() != null) {
								// Verificar qué músculo fue seleccionado y asignar el ComboBox de ejercicios
								// correspondiente
								if (cbMusculo.getSelectedItem().equals(MuscPierna.Aductor)) {
									JComboBox<EjAductor> cbEjercicio = new JComboBox<>(EjAductor.values());
									tablaEjercicios.getColumnModel().getColumn(2)
											.setCellEditor(new DefaultCellEditor(cbEjercicio));

								} else if (cbMusculo.getSelectedItem().equals(MuscPierna.Cuádriceps)) {
									JComboBox<EjCuadriceps> cbEjercicio = new JComboBox<>(EjCuadriceps.values());
									tablaEjercicios.getColumnModel().getColumn(2)
											.setCellEditor(new DefaultCellEditor(cbEjercicio));

								} else if (cbMusculo.getSelectedItem().equals(MuscPierna.Femoral)) {
									JComboBox<EjFemoral> cbEjercicio = new JComboBox<>(EjFemoral.values());
									tablaEjercicios.getColumnModel().getColumn(2)
											.setCellEditor(new DefaultCellEditor(cbEjercicio));

								} else if (cbMusculo.getSelectedItem().equals(MuscPierna.Gluteo)) {
									JComboBox<EjGluteo> cbEjercicio = new JComboBox<>(EjGluteo.values());
									tablaEjercicios.getColumnModel().getColumn(2)
											.setCellEditor(new DefaultCellEditor(cbEjercicio));
								} else {
									JComboBox<EjIsquiotibiales> cbEjercicio = new JComboBox<>(
											EjIsquiotibiales.values());
									tablaEjercicios.getColumnModel().getColumn(2)
											.setCellEditor(new DefaultCellEditor(cbEjercicio));

								}

							}

						}
					});
				}

			}

		};

		cbParteCuerpo.addActionListener(parteCuerpolistener);

		String[] series = { "1", "2", "3", "4", "5", "6" };
		JComboBox<String> cbSeries = new JComboBox<>(series);
		tablaEjercicios.getColumnModel().getColumn(5).setCellEditor(new DefaultCellEditor(cbSeries));

		// Crear la tabla
		this.tablaEjercicios.setVisible(true);
		this.tablaEjercicios.setDefaultRenderer(Object.class, cellRenderer);
		// Se deshabilita la reordenación de columnas
		this.tablaEjercicios.getTableHeader().setReorderingAllowed(false);
		// Se deshabilita el redimensionado de las columna
		this.tablaEjercicios.getTableHeader().setResizingAllowed(false);
		// Se definen criterios de ordenación por defecto para cada columna
		this.tablaEjercicios.setAutoCreateRowSorter(true);

		this.tablaEjercicios.setRowHeight(45);
		tablaEjercicios.getColumnModel().getColumn(2).setPreferredWidth(200);
		tablaEjercicios.getColumnModel().getColumn(6).setPreferredWidth(70);

		JScrollPane scrollPane = new JScrollPane(tablaEjercicios);
		scrollPane.setBorder(new TitledBorder("Rutina De Fuerza: "));

		return scrollPane;

	};

	private JScrollPane init_tabla_cardio() {
		// Crear cabecera de la tabla
		Vector<String> cabeceraTabla = new Vector<String>(Arrays.asList("Tipo", "Foto", "Duración (min)", ""));

		// Crear el modelo de datos de la tabla
		this.modeloDatostablaEjercicios = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceraTabla);
		this.tablaEjercicios = new JTable(this.modeloDatostablaEjercicios) {

			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {

				if (column == 1) {
					return false;
				} else {
					return true;
				}
			}
		};
		this.modeloDatostablaEjercicios.setRowCount(1);

		TableCellRenderer cellRenderer = new TableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				JLabel resultcell = new JLabel();

				// Configurar el texto y el color según el contenido y la columna
				if (value != null && !value.toString().isEmpty()) {
					resultcell.setText(value.toString());
					resultcell.setForeground(Color.BLACK); // Para asegurarse de que el texto del valor no esté en gris
				} else {
					// Placeholder según la columna
					switch (column) {
					case 0:

						resultcell.setText("Seleccionar ▼");
						resultcell.setForeground(Color.GRAY);
						break;
					case 2:
						resultcell.setText("Escribe duración");
						resultcell.setForeground(Color.GRAY);
						break;
					case 1:
						rescalar.setScaledImage(resultcell, "/resourses/images/falta_implementar.png", 60, 40);
						resultcell.setVerticalAlignment(SwingConstants.CENTER);
						resultcell.setHorizontalAlignment(SwingConstants.CENTER);
						break;
					default:
						resultcell.setText(""); // Celdas sin placeholder
						resultcell.setForeground(Color.BLACK);
						break;
					}
				}

				resultcell.setHorizontalAlignment(JLabel.CENTER);
				return resultcell;
			}
		};

		JButton btnEliminarFila = new JButton();
		rescalar = new Rescalar_imagen();
		rescalar.setScaledImage(btnEliminarFila, "/resourses/images/IconoCancelar.png", 20, 20);
		// Crear un editor para la celda que contiene el botón
		ButtonCellEditor editor = new ButtonCellEditor(btnEliminarFila);
		tablaEjercicios.getColumnModel().getColumn(3).setCellEditor(editor); // Columna "Acción"
		tablaEjercicios.getColumnModel().getColumn(3).setCellRenderer(new ButtonCellRenderer(btnEliminarFila));

		JComboBox<TipoCardio> cbTipoCardio = new JComboBox<>(TipoCardio.values());
		tablaEjercicios.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(cbTipoCardio));

		// Crear la tabla
		this.tablaEjercicios.setVisible(true);
		this.tablaEjercicios.setDefaultRenderer(Object.class, cellRenderer);
		// Se deshabilita la reordenación de columnas
		this.tablaEjercicios.getTableHeader().setReorderingAllowed(false);
		// Se deshabilita el redimensionado de las columna
		this.tablaEjercicios.getTableHeader().setResizingAllowed(false);
		// Se definen criterios de ordenación por defecto para cada columna
		this.tablaEjercicios.setAutoCreateRowSorter(true);

		this.tablaEjercicios.setRowHeight(45);
		tablaEjercicios.getColumnModel().getColumn(3).setPreferredWidth(10);

		JScrollPane scrollPane = new JScrollPane(tablaEjercicios);
		scrollPane.setBorder(new TitledBorder("Rutina De Cardio: "));

		return scrollPane;

	};

	private JScrollPane init_tabla_natacion() {
		// Crear cabecera de la tabla
		Vector<String> cabeceraTabla = new Vector<String>(
				Arrays.asList("Tipo", "Estilo", "Foto", "Duración (min)", ""));
		// Crear el modelo de datos de la tabla
		this.modeloDatostablaEjercicios = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceraTabla);
		this.tablaEjercicios = new JTable(this.modeloDatostablaEjercicios) {

			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {

				if (column == 2) {
					return false;
				} else {
					return true;
				}
			}
		};
		this.modeloDatostablaEjercicios.setRowCount(1);

		TableCellRenderer cellRenderer = new TableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				JLabel resultcell = new JLabel();

				// Configurar el texto y el color según el contenido y la columna
				if (value != null && !value.toString().isEmpty()) {
					resultcell.setText(value.toString());
					resultcell.setForeground(Color.BLACK);
				} else {
					// Placeholder según la columna
					switch (column) {
					case 0:
					case 1:

						resultcell.setText("Seleccionar ▼");
						resultcell.setForeground(Color.GRAY);
						break;
					case 3:
						resultcell.setText("Escribe duración");
						resultcell.setForeground(Color.GRAY);
						break;
					case 2:
						rescalar.setScaledImage(resultcell, "/resourses/images/falta_implementar.png", 60, 40);
						resultcell.setVerticalAlignment(SwingConstants.CENTER);
						resultcell.setHorizontalAlignment(SwingConstants.CENTER);
						break;
					default:
						resultcell.setText(""); // Celdas sin placeholder
						resultcell.setForeground(Color.BLACK);
						break;
					}
				}

				resultcell.setHorizontalAlignment(JLabel.CENTER);
				return resultcell;
			}
		};

		JButton btnEliminarFila = new JButton();
		rescalar = new Rescalar_imagen();
		rescalar.setScaledImage(btnEliminarFila, "/resourses/images/IconoCancelar.png", 20, 20);
		// Crear un editor para la celda que contiene el botón
		ButtonCellEditor editor = new ButtonCellEditor(btnEliminarFila);
		tablaEjercicios.getColumnModel().getColumn(4).setCellEditor(editor); // Columna "Acción"
		tablaEjercicios.getColumnModel().getColumn(4).setCellRenderer(new ButtonCellRenderer(btnEliminarFila));

		JComboBox<TipoNat> cbTipoNatacion = new JComboBox<>(TipoNat.values());
		tablaEjercicios.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(cbTipoNatacion));

		JComboBox<EstiloNat> cbEstilo = new JComboBox<>(EstiloNat.values());
		tablaEjercicios.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(cbEstilo));

		// Crear la tabla
		this.tablaEjercicios.setVisible(true);
		this.tablaEjercicios.setDefaultRenderer(Object.class, cellRenderer);
		// Se deshabilita la reordenación de columnas
		this.tablaEjercicios.getTableHeader().setReorderingAllowed(false);
		// Se deshabilita el redimensionado de las columna
		this.tablaEjercicios.getTableHeader().setResizingAllowed(false);
		// Se definen criterios de ordenación por defecto para cada columna
		this.tablaEjercicios.setAutoCreateRowSorter(true);

		this.tablaEjercicios.setRowHeight(45);
		tablaEjercicios.getColumnModel().getColumn(4).setPreferredWidth(10);

		JScrollPane scrollPane = new JScrollPane(tablaEjercicios);
		scrollPane.setBorder(new TitledBorder("Rutina De Natación: "));

		return scrollPane;

	};

	// FUENTE EXTERNA: chat

	class ButtonCellEditor extends AbstractCellEditor implements TableCellEditor {

		private static final long serialVersionUID = 3486861195223346142L;
		private JButton button;

		public ButtonCellEditor(JButton button) {
			this.button = button;
		}

		@Override
		public Object getCellEditorValue() {
			return button.getText(); // Retorna el texto del botón
		}

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			// Asocia el botón a la celda y añade el ActionListener
			button.addActionListener(e -> {
				// Verifica si hay más de una fila en la tabla
				if (tablaEjercicios.getRowCount() > 1) {
					modeloDatostablaEjercicios.removeRow(row); // Elimina la fila donde está el botón
				} else {
					JOptionPane.showMessageDialog(table, "No se puede eliminar la última fila.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			});
			return button; // Devuelve el botón como componente de la celda
		}
	}

	// Renderizador para mostrar el botón en la celda
	class ButtonCellRenderer implements TableCellRenderer {
		private JButton button;

		public ButtonCellRenderer(JButton button) {
			this.button = button;
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			return button; // Muestra el botón en la celda
		}
	}

}
