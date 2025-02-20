package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import domain.Ejercicio;
import domain.Ejercicio_Natacion;
import domain.Ejercicio_gym;
import domain.Ejercicio_cardio;
import domain.Modelo_de_datos_ejercicio;
import domain.Modelo_de_datos_rutinas;
import domain.Rescalar_imagen;
import domain.Rutina;
import db.GestorBD;
import main.Main;

public class Rutinas_guardadas extends JFrame {
	private static final long serialVersionUID = 1L;
	protected List<Rutina> rutinas;
	private Modelo_de_datos_rutinas modelo_de_datos_rutinas;
	private Modelo_de_datos_ejercicio modelo_de_datos_ejercicio = new Modelo_de_datos_ejercicio(new ArrayList<>());
	protected JTable tabla_rutinas;
	private JScrollPane scrollPanelRutinas;
	private JTable tabla_ejercicios = new JTable(modelo_de_datos_ejercicio);
	private JScrollPane scrollPanelejercicios = new JScrollPane(tabla_ejercicios);
	private JProgressBar barra_gym;
	private JProgressBar barra_natacion;
	private JProgressBar barra_running;
	private ArrayList<JProgressBar> barras;
	private Rescalar_imagen rescalar = new Rescalar_imagen();
	private JPanel ventanadondetablas;
	private GridBagConstraints constantes_ej = new GridBagConstraints();
	private Rutina rutina_seleccionada;
	private GestorBD gestor = new GestorBD();
	private String usuario;

	public Rutinas_guardadas(GestorBD gstor, String usuario) {
		this.usuario = usuario;
		this.gestor = gestor;
		this.setVisible(true);
		this.rutinas = gestor.getTodasRutinas(usuario);
		ImageIcon icono = new ImageIcon("resources/images/deustoicon.png");
		this.setIconImage(icono.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("DEUSTOGYM");

		JPanel ventana_principal = new JPanel();
		ventana_principal.setLayout(new BorderLayout());

		this.ventanadondetablas = new JPanel();
		ventanadondetablas.setLayout(new GridLayout());
		GridBagConstraints constantes = new GridBagConstraints();

		initbarras();
		initRutinas();

		JPanel lo_de_arriba = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		
		
		JButton home = new JButton();
		home.setFocusable(false);

		home.addActionListener(e -> {
			dispose();
			new Pagina_principal(gestor, usuario);
		});
		rescalar.setScaledImage(home, "resources/images/casa.png", 20, 20);
		lo_de_arriba.add(home);
		
		
		JButton generarRutinas = new JButton();
		generarRutinas.setFocusable(false);
		generarRutinas.setText("Generar Rutinas");
		
		generarRutinas.addActionListener(e -> {
			
			this.usuario = usuario;
			this.gestor = gestor;
			new GenerarRutinasRecursividad(Main.ejercicios, usuario, gestor, this );
			
		});
		
		
		lo_de_arriba.add(generarRutinas);
		
		
		ventana_principal.add(lo_de_arriba, BorderLayout.NORTH);
		constantes.gridx = 0;
		constantes.gridy = 0;
		constantes.gridwidth = 1;
		constantes.gridheight = 1;
		constantes.fill = GridBagConstraints.BOTH;
		constantes.weighty = 1.0;
		ventanadondetablas.add(scrollPanelRutinas, constantes);
		constantes.weighty = 0.0;

		constantes_ej.gridx = 1;
		constantes_ej.gridy = 0;
		constantes_ej.gridwidth = 1;
		constantes_ej.gridheight = 1;
		constantes_ej.fill = GridBagConstraints.BOTH;
		constantes_ej.weighty = 1.0;
		ventanadondetablas.add(scrollPanelejercicios, constantes);
		scrollPanelejercicios.setBorder(new TitledBorder("Ejercicios"));

		JPanel panel_de_abajo = new JPanel();
		panel_de_abajo.setLayout(new FlowLayout());
		for (JProgressBar barra : barras) {
			panel_de_abajo.add(barra);
		}
		ventana_principal.add(panel_de_abajo, BorderLayout.SOUTH);

		KeyListener keylistener = new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_PLUS) {
					dispose();
					new Nueva_Rutina(gestor, usuario).open();

				} else if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_E) {
					if (rutina_seleccionada != null) {
						int respuesta = JOptionPane.showOptionDialog(null,
								"¿Estás seguro de que quieres eliminar la rutina:" + rutina_seleccionada.getNombre()
										+ "?",
								"", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);

						if (respuesta == JOptionPane.YES_OPTION) {
							gestor.eliminarRutina(rutina_seleccionada);
				            rutinas = gestor.getTodasRutinas(usuario);  // Recargar las rutinas del gestor
							modelo_de_datos_rutinas.setRutinas(rutinas); 
							modelo_de_datos_rutinas.fireTableDataChanged(); 
							rutina_seleccionada = null;
							modelo_de_datos_ejercicio.setEjercicios(new ArrayList<>());
							tabla_ejercicios.revalidate();
							tabla_ejercicios.repaint();
							tabla_rutinas.revalidate();
							tabla_rutinas.repaint();
						} else {

						}
					} else {
						JOptionPane.showMessageDialog(null, "Seleccione una rutina para agregar", "Advertencia",
								JOptionPane.WARNING_MESSAGE);

					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

		};
		this.addKeyListener(keylistener);
		ventanadondetablas.addKeyListener(keylistener);
		tabla_rutinas.addKeyListener(keylistener);
		tabla_rutinas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					int selectedRow = tabla_rutinas.getSelectedRow();
					if (selectedRow >= 0) {
						iniciar_tabla_ejercicio(selectedRow);
						actualizar_barras(selectedRow);
					}
				}
			}
		});

		float escalar = 0.5F;
		int ancho = (int) (Toolkit.getDefaultToolkit().getScreenSize().width * escalar);
		int alto = (int) (Toolkit.getDefaultToolkit().getScreenSize().height * escalar);
		this.setSize(ancho, alto);
		this.setResizable(true);
		setLocationRelativeTo(null);
		ventana_principal.add(ventanadondetablas, BorderLayout.CENTER);
		this.add(ventana_principal);
		JOptionPane.showMessageDialog(null, "Presiona Ctrl + para añadir una nueva rutina \n"
				+ "    Ctrl + e para eliminar una rutina", "Información",
				JOptionPane.INFORMATION_MESSAGE);

	}

	public Rutina getRutinaSeleccionada() {
		return rutina_seleccionada;
	}

	public List<Rutina> getRutinas() {
		return rutinas;
	}

	public void setRutinas(List<Rutina> rutinas) {
		this.rutinas = rutinas;
	}

	private void initRutinas() {
		this.rutinas = gestor.getTodasRutinas(usuario);
		modelo_de_datos_rutinas = new Modelo_de_datos_rutinas(rutinas);
		JTable tablaRutinas = new JTable(modelo_de_datos_rutinas);
		this.tabla_rutinas = tablaRutinas;
		this.scrollPanelRutinas = new JScrollPane(tablaRutinas);
		scrollPanelRutinas.setBorder(new TitledBorder("Rutinas"));
		TableColumn columnaID = tabla_rutinas.getColumnModel().getColumn(2);
		columnaID.setPreferredWidth(30);
		columnaID = tabla_rutinas.getColumnModel().getColumn(1);
		columnaID.setPreferredWidth(40);

		// Definir el Renderer
		TableCellRenderer cellRenderer = new TableCellRenderer() {
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				JLabel jlabel = new JLabel();
				jlabel.setOpaque(true);
				jlabel.setVerticalAlignment(SwingConstants.CENTER);

				if (column == 1) {
					jlabel.setHorizontalAlignment(SwingConstants.CENTER);
					if (value.toString().equals("MUSCULACION")) {
						rescalar.setScaledImage(jlabel, "resources/images/musculacion.png", 40, 40);
					} else if (value.toString().equals("CARDIOVASCULAR")) {
						rescalar.setScaledImage(jlabel, "resources/images/cardio.png", 40, 40);
					} else if (value.toString().equals("PERDIDA_DE_PESO")) {
						rescalar.setScaledImage(jlabel, "resources/images/perdida_de_peso.png", 40, 40);
					} else {
						jlabel.setText("FALTA DE METER IMAGEN EN INIT TABLE");
					}
					jlabel.setToolTipText(value.toString());
				} else {
					jlabel.setText(value != null ? value.toString() : "");
				}

				if (row % 2 == 0) {
					jlabel.setBackground(new Color(250, 249, 249));
				} else {
					jlabel.setBackground(new Color(190, 227, 219));
				}

				Point mousePos = tabla_rutinas.getMousePosition();
				if (mousePos != null) {
					if (row == tabla_rutinas.rowAtPoint(mousePos)) {
						jlabel.setBackground(new Color(220, 240, 250));
					}
				}

				if (isSelected) {
					jlabel.setBackground(tabla_rutinas.getSelectionBackground());
				}

				return jlabel;
			}
		};

		tabla_rutinas.setRowHeight(40);
		tabla_rutinas.setDefaultRenderer(Object.class, cellRenderer);

		tabla_rutinas.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				tabla_rutinas.repaint();
			}
		});
	}

	private void iniciar_tabla_ejercicio(int row) {
		rutina_seleccionada = (Rutina) modelo_de_datos_rutinas.getValueAt(row, 999);
		modelo_de_datos_ejercicio.setListaEjercicios(rutina_seleccionada.getLista_ejercicios());
		tabla_ejercicios = new JTable(modelo_de_datos_ejercicio);
		modelo_de_datos_ejercicio.fireTableDataChanged();
		scrollPanelejercicios.setViewportView(tabla_ejercicios);
		tabla_ejercicios.setRowHeight(40);

		TableCellRenderer cellRenderer = new TableCellRenderer() {
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				JLabel jlabel = new JLabel();
				jlabel.setOpaque(true);
				jlabel.setVerticalAlignment(SwingConstants.CENTER);
				jlabel.setBackground(Color.WHITE);

				if (column == 2) {
					rescalar.setScaledImage(jlabel, "resources/images/falta_implementar.png", 60, 40);
					jlabel.setVerticalAlignment(SwingConstants.CENTER);
					jlabel.setHorizontalAlignment(SwingConstants.CENTER);
				} else {
					jlabel.setText((String) value);
				}

				Point mousePos = tabla_ejercicios.getMousePosition();
				if (mousePos != null) {
					int mouseRow = table.rowAtPoint(mousePos);
					if (row == mouseRow) {
						jlabel.setBackground(new Color(220, 240, 250)); // Color al pasar el mouse
					}
				}

				if (isSelected) {
					jlabel.setBackground(tabla_ejercicios.getSelectionBackground());
				}

				return jlabel;
			}
		};

		tabla_ejercicios.setDefaultRenderer(Object.class, cellRenderer);

		tabla_ejercicios.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				tabla_ejercicios.repaint();
			}
		});
	}

	private void actualizar_barras(int row) {
		barra_gym.setValue(0);
		barra_natacion.setValue(0);
		barra_running.setValue(0);

		for (int i = 0; i < rutina_seleccionada.getLista_ejercicios().size(); i++) {
			Ejercicio ejercicio = rutina_seleccionada.getLista_ejercicios().get(i);
			if (ejercicio instanceof Ejercicio_gym) {
				barra_gym.setValue(barra_gym.getValue() + 1);
			} else if (ejercicio instanceof Ejercicio_Natacion) {
				barra_natacion.setValue(barra_natacion.getValue() + 1);
			} else if (ejercicio instanceof Ejercicio_cardio) {
				barra_running.setValue(barra_running.getValue() + 1);
			}
		}
		barra_gym.setValue((barra_gym.getValue() * 100) / rutina_seleccionada.getNumeroEjercicios());
		barra_natacion.setValue((barra_natacion.getValue() * 100) / rutina_seleccionada.getNumeroEjercicios());
		barra_running.setValue((barra_running.getValue() * 100) / rutina_seleccionada.getNumeroEjercicios());
	}

	private void initbarras() {
		barra_gym = new JProgressBar(0, 100);
		barra_gym.setStringPainted(true);
		barra_gym.setForeground(new Color(90, 155, 121));
		barra_gym.setString("Ejercicios Fuerza");

		barra_natacion = new JProgressBar(0, 100);
		barra_natacion.setStringPainted(true);
		barra_natacion.setForeground(new Color(30, 144, 255));
		barra_natacion.setString("Ejercicios Natacion");

		barra_running = new JProgressBar(0, 100);
		barra_running.setStringPainted(true);
		barra_running.setForeground(new Color(238, 44, 44));
		barra_running.setString("Ejercicios Cardio");

		barras = new ArrayList<>();
		barras.add(barra_gym);
		barras.add(barra_natacion);
		barras.add(barra_running);
	}

}