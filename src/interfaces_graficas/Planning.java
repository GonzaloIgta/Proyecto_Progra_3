package interfaces_graficas;
	
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

	import clases_de_apyo.Rescalar_imagen;
	import clases_de_apyo.Rutina;
import gestorbd.GestorBD;
	
	public class Planning extends JFrame{
	
		private static final long serialVersionUID = 1;
	
		
		private ArrayList<Rutina> rutinasGuardadas;
		private JButton botonAgregarRutina;
		private JButton eliminarBoton;
		private GestorBD gestor;
	
		
		
		public Planning(ArrayList<Rutina> rutinasGuardadas,GestorBD gestor){
			this.gestor = gestor;
			this.rutinasGuardadas = rutinasGuardadas;
			
	        ImageIcon icono = new ImageIcon(this.getClass().getResource("/resourses/images/deustoicon.png"));
	        this.setIconImage(icono.getImage());
	        this.setTitle("Planning Semanal");
	        
			float escalar = 0.7F; // una ventana al 50% del tamaño de la pantalla
			int ancho = (int)(Toolkit.getDefaultToolkit().getScreenSize(). width*escalar);
			int alto = (int)(Toolkit.getDefaultToolkit().getScreenSize(). height*escalar);
			this.setSize(ancho,alto);
			
			this.setResizable(false);
			
	     	this.setLocationRelativeTo(null);
	     			
	     	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	     	     	
	     	JPanel panelPrincipal = new JPanel(new GridLayout(1,7));
	     	
	     	JButton botonVolver = new JButton(); 
	     	botonVolver.setFocusable(false);
	     	Rescalar_imagen rescalar = new Rescalar_imagen();
	        rescalar.setScaledImage(botonVolver, "/resourses/images/casa.png", 20, 20);
	
	     	botonVolver.addActionListener(new ActionListener() {  //listener del boton
				
				@Override
				public void actionPerformed(ActionEvent e) {
					new Pagina_principal(gestor);
					dispose();
				}
			});
	     	
	     	JPanel panelVolver = new JPanel(new FlowLayout(FlowLayout.LEFT));
	     	panelVolver.add(botonVolver); 
	     	
	     	
	     	String[] diaSemana = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
	
	     	
	     	//FUENTE EXTERNA
	     	//IAG
	     	//ADAPTADO
	     	
	     	
	     	for (String dia : diaSemana) {
	     		
	     		//Crear un panel para cada dia
	     		JPanel panelDia = new JPanel(new BorderLayout()); //Crea el panel para cada dia donde se almacenara nombre, rutinas, y botones
				JLabel labelDia = new JLabel(dia, JLabel.CENTER); //Crea el label para los nombres de los dias
	     		JPanel panelRutinas = new JPanel(new GridLayout(6,1)); //Crea panel para almacenar 5 rutinas y boton para añadir rutinas
	     		     		
	     		panelDia.setBorder(new LineBorder(Color.BLACK)); //Añade borde para separar cada dia
	
	     		panelRutinas.setBorder(new EmptyBorder(30, 0, 0, 0)); //Añade separacion vertical del panel de las rutinas (el del centro con el del north)
	     		
	     		
	     		JLabel mensajeSinRutinas = new JLabel("No hay ninguna rutina");
	     		mensajeSinRutinas.setForeground(Color.RED);  //color de letra rojo
	     		mensajeSinRutinas.setHorizontalAlignment(JLabel.CENTER); // Aseguramos que el texto este centrado
	     		mensajeSinRutinas.setVerticalAlignment(JLabel.CENTER);
	     		mensajeSinRutinas.setPreferredSize(new Dimension(200, 60)); // Establecemos un tamaño preferido para que el texto pueda envolver
	
	     		panelRutinas.add(mensajeSinRutinas);
	     		
	     		int[] contadorRutina = {0};
	     		
	     		JButton botonAgregarRutina = new JButton("Añadir Rutina");
	     		
	     		botonAgregarRutina.setFocusable(false);
	     		
	     		botonAgregarRutina.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (contadorRutina[0] < 5) {
							
							mostrarRutinasGuardadas(panelRutinas, mensajeSinRutinas, contadorRutina, botonAgregarRutina);
							
						} 
					} 
				});
	     		
	     		panelDia.add(labelDia, BorderLayout.NORTH);
	     		panelDia.add(panelRutinas, BorderLayout.CENTER);
	     		panelDia.add(botonAgregarRutina,BorderLayout.SOUTH);
	     		
	     		
	     		panelPrincipal.add(panelDia);
	     	}
	     	
	     	
	     
	     	add(panelPrincipal,BorderLayout.CENTER);
	     	add(panelVolver,BorderLayout.NORTH);
	     	
	     	
		}
	     	
	     public void mostrarRutinasGuardadas(JPanel panelRutinas, JLabel mensajeSinRutinas, int[] contadorRutina, JButton botonAgregarRutina) {
	     	    JDialog dialogoRutinas = new JDialog(this, "Seleccionar Rutina", true); // ventana modal
	     	    dialogoRutinas.setSize(500, 400);
	     	    dialogoRutinas.setLocationRelativeTo(this);
	     	    
	     	    ArrayList<Rutina> listaRutinas = obtenerRutinasGuardadas();
	
	     	    String[] columnas = {"Nombre de Rutina","Objetivo"};
	     	    
	     	    
	     	   DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0){
				private static final long serialVersionUID = -7180503917040117918L;

					@Override
	     		    public boolean isCellEditable(int row, int column) {
	     		        return column == 2;  // Solo hacer editable la columna de botones
	     		    }
	     		};	
	     	    
	     	    
	     	    for (Rutina rutina : listaRutinas) { 	   
	     	    	modeloTabla.addRow(new Object[] {rutina.getNombre(),rutina.getObjetivo() });
	     	    }
	     	    
	     
	     
	     	   	JTable tablaRutinas = new JTable(modeloTabla); 

	     	   	tablaRutinas.setFont(new Font("Arial", Font.PLAIN, 14));
	     	    
	     	    tablaRutinas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	     	    
	     	    JScrollPane scrollPane = new JScrollPane(tablaRutinas);
	     	    
	     	    dialogoRutinas.add(scrollPane,BorderLayout.CENTER);
	     	    
	     	    
	     	    JButton botonSeleccionar = new JButton("Agregar Rutina");
	     	    
	     	    botonSeleccionar.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						int filaSeleccionada = tablaRutinas.getSelectedRow();
						
		     	    	if (filaSeleccionada != -1) {
		     	    		Rutina rutinaSeleccionada = listaRutinas.get(filaSeleccionada);
		     	    		agregarRutinaAPlanning(panelRutinas, mensajeSinRutinas, rutinaSeleccionada.getNombre(), contadorRutina, botonAgregarRutina, rutinaSeleccionada);      	    		//LE PASAMOS EL PANEL DEL DIA, EL MENSAJE DE SIN RUTINAS POR SI LO TIENE QUE PONER, Y EL NOMBRE DE LA RUTINA
		     	    		dialogoRutinas.dispose();
		     	    		
		     	    	}else {
		     	    		JOptionPane.showMessageDialog(dialogoRutinas, "Seleccione una rutina para agregar","Advertencia",JOptionPane.WARNING_MESSAGE);
		     	    	}
					}
				});
	     	    
	     	    dialogoRutinas.add(botonSeleccionar,BorderLayout.SOUTH);
	     	    dialogoRutinas.setVisible(true);
	
	     	    
	    }
	     
	     private void agregarRutinaAPlanning(JPanel panelRutinas, JLabel mensajeSinRutinas,String nombreRutina, int[] contadorRutina, JButton botonAgregarRutina, Rutina rutinaSeleccionada) {
	    	 
	    	 JPanel panelRutina = new JPanel();
	    	 
	    	 JButton rutinaBoton = new JButton(nombreRutina);

	    	 rutinaBoton.addActionListener(new ActionListener() {
				
				@Override
				//FUENTE EXTERNA
				//IAG
				//ADAPTADO A MEDIDA
				
				public void actionPerformed(ActionEvent e) {
					
					//JFRAME -----------------------------------------------------------------
					JFrame frameInformacion = new JFrame("Detalles de " + nombreRutina);
			        frameInformacion.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			        frameInformacion.setSize(600, 400); // Un tamaño más amplio
			        frameInformacion.setResizable(true); // Permitir que la ventana sea redimensionable
			        
			        // Crear JPanel con BoxLayout para los componentes
			        JPanel informacionRutina = new JPanel();
			        informacionRutina.setLayout(new BoxLayout(informacionRutina, BoxLayout.Y_AXIS));
			        informacionRutina.setAlignmentX(Component.CENTER_ALIGNMENT); // Alineación horizontal del panel

			        // Crear el título con el nombre de la rutina
			        JLabel labelNombreRutina = new JLabel("Rutina: " + nombreRutina);
			        labelNombreRutina.setFont(new Font("Arial", Font.BOLD, 16));
			        labelNombreRutina.setAlignmentX(JLabel.CENTER_ALIGNMENT);  // Alineación centrada horizontalmente
			        informacionRutina.add(labelNombreRutina);

			        // Crear el objetivo de la rutina
			        JLabel labelObjetivo = new JLabel("Objetivo: " + rutinaSeleccionada.getObjetivo());
			        labelObjetivo.setFont(new Font("Arial", Font.PLAIN, 14));
			        labelObjetivo.setAlignmentX(JLabel.CENTER_ALIGNMENT);  // Alineación centrada horizontalmente

			        informacionRutina.add(labelObjetivo);

			        // Crear los ejercicios y convertirlos en JLabels
			        String ejercicios = rutinaSeleccionada.printLista_Ejercicios();
			        String[] listaEjercicios = ejercicios.split(",");  // Separar los ejercicios por COMAS

			        // Crear un JLabel para cada ejercicio y añadirlos al panel
			        for (String ejercicio : listaEjercicios) {
			            JLabel labelEjercicio = new JLabel(ejercicio);
			            labelEjercicio.setFont(new Font("Arial", Font.PLAIN, 14));
			            labelEjercicio.setAlignmentX(JLabel.CENTER_ALIGNMENT);  // Alineación centrada horizontalmente			            
			            informacionRutina.add(labelEjercicio);
			        }

			        // Añadir un espacio para separar los elementos visualmente
			        informacionRutina.add(Box.createVerticalStrut(10));

			        // Crear un JScrollPane si el contenido es grande
			        JScrollPane scrollPane = new JScrollPane(informacionRutina);
			        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);  // Habilitar desplazamiento vertical

			        // Crear un JPanel principal para centrar el contenido en la ventana
			        JPanel panelPrincipal = new JPanel();
			        panelPrincipal.setLayout(new BorderLayout());

			        // Añadir espacio flexible para centrar el contenido verticalmente
			        panelPrincipal.add(Box.createVerticalGlue(), BorderLayout.NORTH);  // Empujar hacia el centro
			        panelPrincipal.add(scrollPane, BorderLayout.CENTER); // El contenido central
			        panelPrincipal.add(Box.createVerticalGlue(), BorderLayout.SOUTH); // Empujar hacia el centro

			        // Crear el botón "Volver"
			        JButton botonVolver = new JButton("Volver");
			        botonVolver.setAlignmentX(JButton.CENTER_ALIGNMENT); // Alinear el botón en el centro horizontalmente
			        botonVolver.setPreferredSize(new Dimension(150, 40)); // Tamaño del botón

			        // Agregar el ActionListener para el botón Volver
			        botonVolver.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							frameInformacion.dispose();
						}
					});
			        // Crear un panel para colocar el botón abajo
			        JPanel panelBoton = new JPanel();
			        panelBoton.setLayout(new FlowLayout(FlowLayout.CENTER)); // Alinear el botón al centro
			        panelBoton.add(botonVolver);

			        // Añadir el panel del botón en el BorderLayout.SOUTH
			        panelPrincipal.add(panelBoton, BorderLayout.SOUTH);

			        // Agregar el JPanel principal al JFrame
			        frameInformacion.add(panelPrincipal);

			        frameInformacion.setLocationRelativeTo(null);

			        // Hacer visible la ventana
			        frameInformacion.setVisible(true);
				}
			});
	    	 
	    	 rutinaBoton.setFocusable(false);
	    	 
	    	 JButton eliminarRutina = new JButton("Eliminar");
	    	 
	    	 eliminarRutina.setFocusable(false);
	    	 
	    	 eliminarRutina.addActionListener( new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
						
					 panelRutinas.remove(panelRutina);
					 panelRutinas.revalidate();
					 panelRutinas.repaint();
					 contadorRutina[0]--;
					 
	
					if (contadorRutina[0] < 5) {
						botonAgregarRutina.setEnabled(true);
					} 
					
					
					if(contadorRutina[0] == 0 ) {
						panelRutinas.add(mensajeSinRutinas);
						panelRutinas.revalidate();
						panelRutinas.repaint();
					}
	    		 
					
				}
			});
	  
	    	 
		    	panelRutina.add(rutinaBoton);
				panelRutina.add(eliminarRutina);
				panelRutinas.add(panelRutina);
				panelRutinas.revalidate();  // Actualiza el panel para enseñar la nueva rutina
		        panelRutinas.repaint();  // Redibuja el panel
		        contadorRutina[0]++;
		        
		        
		        if (contadorRutina[0] > 0) {
		            panelRutinas.remove(mensajeSinRutinas);
		            panelRutinas.revalidate();
		            panelRutinas.repaint();
		        }

		        if (contadorRutina[0] == 5) {
		            botonAgregarRutina.setEnabled(false);
		        }
		       
	    	 
	     }
	     
	     private ArrayList<Rutina> obtenerRutinasGuardadas() {
	    	 return rutinasGuardadas;
	    	 
	     }
	     
	     
	     	
		public void open() {
	        setVisible(true);
		}
	
	}