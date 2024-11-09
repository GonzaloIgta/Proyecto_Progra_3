package interfaces_graficas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import clases_de_apyo.Ejercicio_gym.MuscBrazos;
import clases_de_apyo.Ejercicio_gym.MuscPierna;
import clases_de_apyo.Ejercicio_gym.MuscTorso;
import clases_de_apyo.Ejercicio_gym.PartesDelCuerpo;



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
			
			ventana_central_MuestraRutinas.revalidate(); // FUENTE EXTERNA: revalidate y repaint sacados con ayuda de ChatGPT
          	ventana_central_MuestraRutinas.repaint();
        };
        
        
        boton_fuerza.addActionListener(listener_boton_fuerza);
		
		
		
		
		
		JButton boton_cardio = new JButton("Cardio");
		boton_cardio.setFocusable(false);
		layout_botones.add(boton_cardio);
		
		
		ActionListener listener_boton_cardio = e -> {
            ventana_central_MuestraRutinas.removeAll();

			ventana_central_MuestraRutinas.add(init_tabla_cardio());
			
			ventana_central_MuestraRutinas.revalidate(); // FUENTE EXTERNA: revalidate y repaint sacados con ayuda de ChatGPT
          	ventana_central_MuestraRutinas.repaint();
        };
        
        
        boton_cardio.addActionListener(listener_boton_cardio);
		
		JButton boton_natacion = new JButton("Natacion");
		boton_natacion.setFocusable(false);
		layout_botones.add(boton_natacion);
		
		ActionListener listener_boton_natacion = e -> {
            ventana_central_MuestraRutinas.removeAll();

			ventana_central_MuestraRutinas.add(init_tabla_natacion());
			
			ventana_central_MuestraRutinas.revalidate(); // FUENTE EXTERNA: revalidate y repaint sacados con ayuda de ChatGPT
          	ventana_central_MuestraRutinas.repaint();
        };
        
        
        boton_natacion.addActionListener(listener_boton_natacion);
		
		
		
		ventanaPrincipal_nuevaRutina.add(layout_botones, BorderLayout.NORTH);
		
		

//
		
		//GridBagConstraints gbc = new GridBagConstraints();
		
		
		//ventana_nueva_rutina.setLayout(new GridBagLayout());
		
		/*
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("Ejercicio");
		CrearNodos(top);
		JTree arbolSeleccionEjercicios = new JTree(top);
		
				
		
		
		
		ventanaPrincipal_nuevaRutina.add(arbolSeleccionEjercicios, BorderLayout.WEST);*/
		ventanaPrincipal_nuevaRutina.add(ventana_central_MuestraRutinas);
        
        
		ventanaPrincipal_nuevaRutina.setBackground(new Color(130, 195, 65));
		
		
		//añadimos el icono de la aplicacion en la parte de arriba
        ImageIcon icono = new ImageIcon(this.getClass().getResource("/resourses/images/deustoicon.png"));
        this.setIconImage(icono.getImage());
        
    
        
     
     	
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

	     ventana_central_MuestraRutinas.add(scrollPane, BorderLayout.CENTER);

        
		this.add(ventanaPrincipal_nuevaRutina);

	}
	//hgghfghfghf
	
	public void open() {
        setVisible(true);
	}
	private JScrollPane init_tabla_fuerza(){
        // Crear cabecera de la tabla
        Vector<String> cabeceraTabla = new Vector<String>(
            Arrays.asList("Parte Del Cuerpo","Musculo","Ejercicio", "Foto", "Series")
        );
        // Crear el modelo de datos de la tabla
        this.modeloDatosTablaRutina = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceraTabla);
		this.tablaRutina = new JTable(this.modeloDatosTablaRutina) {

			private static final long serialVersionUID = 1L;

		public boolean isCellEditable(int row, int column) {
				
				if(column==3) {
					return false;
				}
				else {
					return true;
				}
			}
		};
        this.modeloDatosTablaRutina.setRowCount(1);
        
        
        
        
        

        TableCellRenderer cellRenderer = (table, value, isSelected, hasFocus, row, column) -> {
        	JLabel resultcell = new JLabel();

        	
            if (value != null) {
            	resultcell.setText(value.toString());
            }
        	
        	
        	        	       	     	
        	return resultcell;
        	
        	
        };
        
   

        JComboBox<PartesDelCuerpo> cbParteCuerpo = new JComboBox<>(PartesDelCuerpo.values());		
        tablaRutina.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(cbParteCuerpo));
        
		
		ActionListener parteCuerpolistener = e -> {
			
			if(cbParteCuerpo.getSelectedItem() != null) {
				if(cbParteCuerpo.getSelectedItem().equals(PartesDelCuerpo.Brazos)) {
			        JComboBox<MuscBrazos> cbMusculo= new JComboBox<>(MuscBrazos.values());		
			        tablaRutina.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(cbMusculo));

				}
				else if(cbParteCuerpo.getSelectedItem().equals(PartesDelCuerpo.Torso)) {
					JComboBox<MuscTorso> cbMusculo= new JComboBox<>(MuscTorso.values());		
			        tablaRutina.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(cbMusculo));

				}
				else if(cbParteCuerpo.getSelectedItem().equals(PartesDelCuerpo.Pierna)) {
					
					JComboBox<MuscPierna> cbMusculo= new JComboBox<>(MuscPierna.values());		
			        tablaRutina.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(cbMusculo));
				}
				
			}

            
        };
 	
      cbParteCuerpo.addActionListener(parteCuerpolistener);
        	
     
      String[] series = {"1","2","3","4","5","6"};
      JComboBox<String> cbSeries = new JComboBox<>(series);		
      tablaRutina.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(cbSeries));
      
      ActionListener listenerCrearFilaNueva = e ->{
      	
      	if(cbSeries.getSelectedItem()!=null && tablaRutina.getEditingRow() == modeloDatosTablaRutina.getRowCount() - 1 ) {
      		
      		modeloDatosTablaRutina.addRow(new Object[] { "", "", "", null });      	
      		
      	}
      	
      	
      };
      
      
      cbSeries.addActionListener(listenerCrearFilaNueva);
        
        
        
        
        
        // Crear la tabla
        this.tablaRutina.setVisible(true);
        this.tablaRutina.setDefaultRenderer(Object.class, cellRenderer);
		//Se deshabilita la reordenación de columnas
		this.tablaRutina.getTableHeader().setReorderingAllowed(false);
		//Se deshabilita el redimensionado de las columna
		this.tablaRutina.getTableHeader().setResizingAllowed(false);
		//Se definen criterios de ordenación por defecto para cada columna
		this.tablaRutina.setAutoCreateRowSorter(true);
		


		this.tablaRutina.setRowHeight(26);
		
        JScrollPane scrollPane = new JScrollPane(tablaRutina);
        scrollPane.setBorder(new TitledBorder("Rutina De Fuerza: "));

        return scrollPane;
        
        };

    	private JScrollPane init_tabla_cardio(){
            // Crear cabecera de la tabla
            Vector<String> cabeceraTabla = new Vector<String>(
                Arrays.asList("Tipo Entrenamiento", "Musculo", "Foto", "Tiempo", "Ritmo")
            );
            // Crear el modelo de datos de la tabla
            this.modeloDatosTablaRutina = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceraTabla);
            this.modeloDatosTablaRutina.setRowCount(1);

            
            
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
                this.modeloDatosTablaRutina.setRowCount(1);

                // Crear la tabla y configurar celdas editables
                JTable tablaRutina = new JTable(this.modeloDatosTablaRutina);
                tablaRutina.setVisible(true);
                JScrollPane scrollPane = new JScrollPane(tablaRutina);

                return scrollPane;
                
                };
            
            
                
             //   #FUENTE EXTERNA: Hemos tomado como referencia esta URL:
             //   https://docs.oracle.com/javase/tutorial/uiswing/components/tree.html
            
                
            /*private void CrearNodos(DefaultMutableTreeNode top) {
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
          */
                
                
    }
