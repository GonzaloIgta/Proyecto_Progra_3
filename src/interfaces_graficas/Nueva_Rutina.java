package interfaces_graficas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.DefaultMutableTreeNode;

import clases_de_apyo.Rutina;


public class Nueva_Rutina extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private JTable tablaRutina;
	private DefaultTableModel modeloDatosTablaRutina;
	private JPanel ventana_central_MuestraRutinas;


	public Nueva_Rutina() {

		//para que se cierre al darle a la x
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //titulo de la ventana
        setTitle("Nueva Rutina");
        
        // Creamos la ventana y definimos distribucion
		JPanel ventanaPrincipal_nuevaRutina = new JPanel(new BorderLayout());
		JScrollPane scrollPane = new JScrollPane(this.tablaRutina);
	    ventana_central_MuestraRutinas = new JPanel(new BorderLayout());
		
		

		//FUENTE-EXTERNA
		//URL: (https://chuidiang.org/index.php?title=Uso_de_Layouts)
		//ADAPTADO (hemos analizado el codigo para entender como funcionaba el layout y modificado para añadirle los elementos que necesitabamos)

		JPanel layout_botones = new JPanel(new FlowLayout());
		layout_botones.setBorder(new TitledBorder("Selecciona el tipo de entrenamiento que quieres añadir: "));
		//
		JButton boton_fuerza = new JButton("Fuerza");
		boton_fuerza.setFocusable(false);
		layout_botones.add(boton_fuerza);
		
		
		
		ActionListener listener_boton_fuerza = e -> {
            ventana_central_MuestraRutinas.removeAll();

			ventana_central_MuestraRutinas.add(init_tabla_fuerza());
			
			ventana_central_MuestraRutinas.revalidate(); // FUENTE EXTERNA: revalidate y repaing sacados con ayuda de ChatGPT
          	ventana_central_MuestraRutinas.repaint();
        };
        
        
        boton_fuerza.addActionListener(listener_boton_fuerza);
		
		
		
		
		
		JButton boton_cardio = new JButton("Cardio");
		boton_cardio.setFocusable(false);
		layout_botones.add(boton_cardio);
		
		
		ActionListener listener_boton_cardio = e -> {
            ventana_central_MuestraRutinas.removeAll();

			ventana_central_MuestraRutinas.add(init_tabla_cardio());
			
			ventana_central_MuestraRutinas.revalidate(); // FUENTE EXTERNA: revalidate y repaing sacados con ayuda de ChatGPT
          	ventana_central_MuestraRutinas.repaint();
        };
        
        
        boton_cardio.addActionListener(listener_boton_cardio);
		
		JButton boton_natacion = new JButton("Natacion");
		boton_natacion.setFocusable(false);
		layout_botones.add(boton_natacion);
		
		ActionListener listener_boton_natacion = e -> {
            ventana_central_MuestraRutinas.removeAll();

			ventana_central_MuestraRutinas.add(init_tabla_natacion());
			
			ventana_central_MuestraRutinas.revalidate(); // FUENTE EXTERNA: revalidate y repaing sacados con ayuda de ChatGPT
          	ventana_central_MuestraRutinas.repaint();
        };
        
        
        boton_natacion.addActionListener(listener_boton_natacion);
		
		
		
		ventanaPrincipal_nuevaRutina.add(layout_botones, BorderLayout.NORTH);
		
		

//
		
		//GridBagConstraints gbc = new GridBagConstraints();
		
		
		//ventana_nueva_rutina.setLayout(new GridBagLayout());
		
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("Ejercicio");
		CrearNodos(top);
		JTree arbolSeleccionEjercicios = new JTree(top);
		
		
		
		
		
		
		ventanaPrincipal_nuevaRutina.add(arbolSeleccionEjercicios, BorderLayout.WEST);
		ventanaPrincipal_nuevaRutina.add(ventana_central_MuestraRutinas);
        
        
		ventanaPrincipal_nuevaRutina.setBackground(new Color(130, 195, 65));
		
		
		//añadimos el icono de la aplicacion en la parte de arriba
		ImageIcon icono_aplicacion = new ImageIcon("resourses/images/logotipo.png");
		
	
        
    
        
     
     	
		setResizable(false);
		
		//FUENTE-EXTERNA
				//URL: (https://www.forosdelweb.com/f45/ajuste-automatico-jframe-853529/)
				//SIN-CAMBIOS
		//Hacer que la pantalla se habra a la mitad del tamaño
		float escalar = 0.5F; // una ventana al 50% del tamaño de la pantalla
		int ancho = (int)(Toolkit.getDefaultToolkit().getScreenSize(). width*escalar);
		int alto = (int)(Toolkit.getDefaultToolkit().getScreenSize(). height*escalar);
		this.setSize(ancho,alto);

        


     	
        // Centrar la ventana en la pantalla
     	setLocationRelativeTo(null);

        // Hacemos visible la ventana principal
        setVisible(true);

	     ventana_central_MuestraRutinas.add(scrollPane, BorderLayout.CENTER);

        
		this.add(ventanaPrincipal_nuevaRutina);

	}
	
	private JScrollPane init_tabla_fuerza(){
        // Crear cabecera de la tabla
        Vector<String> cabeceraTabla = new Vector<String>(
            Arrays.asList("Tipo Entrenamiento", "Musculo", "Foto", "Series", "Repeticiones")
        );
        // Crear el modelo de datos de la tabla
        this.modeloDatosTablaRutina = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceraTabla);

        // Crear la tabla y configurar celdas editables
        JTable tablaRutina = new JTable(this.modeloDatosTablaRutina);
        tablaRutina.setVisible(true);
        JScrollPane scrollPane = new JScrollPane(tablaRutina);

        return scrollPane;
        
        };

    	private JScrollPane init_tabla_cardio(){
            // Crear cabecera de la tabla
            Vector<String> cabeceraTabla = new Vector<String>(
                Arrays.asList("Tipo Entrenamiento", "Musculo", "Foto", "Tiempo", "Ritmo")
            );
            // Crear el modelo de datos de la tabla
            this.modeloDatosTablaRutina = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceraTabla);

            // Crear la tabla y configurar celdas editables
            JTable tablaRutina = new JTable(this.modeloDatosTablaRutina);
            tablaRutina.setVisible(true);
            JScrollPane scrollPane = new JScrollPane(tablaRutina);

            return scrollPane;
            
            };
            
        	private JScrollPane init_tabla_natacion(){
                // Crear cabecera de la tabla
                Vector<String> cabeceraTabla = new Vector<String>(
                    Arrays.asList("Tipo Entrenamiento", "Musculo", "Foto", "Largos", "Ritmo")
                );
                // Crear el modelo de datos de la tabla
                this.modeloDatosTablaRutina = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceraTabla);

                // Crear la tabla y configurar celdas editables
                JTable tablaRutina = new JTable(this.modeloDatosTablaRutina);
                tablaRutina.setVisible(true);
                JScrollPane scrollPane = new JScrollPane(tablaRutina);

                return scrollPane;
                
                };
                
             //   #FUENTE EXTERNA: Hemos tomado como referencia esta URL:
             //   https://docs.oracle.com/javase/tutorial/uiswing/components/tree.html
            private void CrearNodos(DefaultMutableTreeNode top) {
            	DefaultMutableTreeNode ejercicio = null;

            	DefaultMutableTreeNode categ1 = new DefaultMutableTreeNode("Torso") ; 
            	top.add(categ1);
            	
            	ejercicio = new DefaultMutableTreeNode("Pecho");
            	categ1.add(ejercicio);
            	
            	ejercicio = new DefaultMutableTreeNode("Espalda");
            	categ1.add(ejercicio);
            	
            	ejercicio = new DefaultMutableTreeNode("Trapecios");
            	categ1.add(ejercicio);
            	
            	
            	DefaultMutableTreeNode categ2 = new DefaultMutableTreeNode("Brazos") ; 
            	top.add(categ2);

            	
            	ejercicio = new DefaultMutableTreeNode("Biceps");
            	categ2.add(ejercicio);
            	
            	ejercicio = new DefaultMutableTreeNode("Triceps");
            	categ2.add(ejercicio);
            	
            	ejercicio = new DefaultMutableTreeNode("Hombro");
            	categ2.add(ejercicio);
            	
            	ejercicio = new DefaultMutableTreeNode("Antebrazo");
            	categ2.add(ejercicio);
            	
            	DefaultMutableTreeNode categ3 = new DefaultMutableTreeNode("Piernas") ; 
            	top.add(categ3);
            	
            	
            	ejercicio = new DefaultMutableTreeNode("Cuádriceps");
            	categ3.add(ejercicio);
            	
            	ejercicio = new DefaultMutableTreeNode("Isquiotibiales");
            	categ3.add(ejercicio);
          
            	ejercicio = new DefaultMutableTreeNode("Gemelos");
            	categ3.add(ejercicio);
            	
            	ejercicio = new DefaultMutableTreeNode("Glúteos");
            	categ3.add(ejercicio);
            	



            	
            	
            	
            }
                
                
    }

