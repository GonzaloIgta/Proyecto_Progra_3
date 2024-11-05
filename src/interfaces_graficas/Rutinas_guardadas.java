package interfaces_graficas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.CellEditor;
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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import clases_de_apyo.Ejercicio;
import clases_de_apyo.Ejercicio_Natacion;
import clases_de_apyo.Ejercicio_gym;
import clases_de_apyo.Ejercicio_runing;
import clases_de_apyo.Modelo_de_datos_ejercicio;
import clases_de_apyo.Modelo_de_datos_rutinas;
import clases_de_apyo.Musculo_trabajado;
import clases_de_apyo.Rescalar_imagen;
import clases_de_apyo.Rutina;
import clases_de_apyo.estilo_natacion;

public class Rutinas_guardadas extends JFrame {
	private static final long serialVersionUID = 1L;
	private List<Rutina> rutinas;
    private Modelo_de_datos_rutinas modelo_de_datos_rutinas;
    private Modelo_de_datos_ejercicio modelo_de_datos_ejercicio=new Modelo_de_datos_ejercicio( new ArrayList<>()) ;
    private JTable tabla_rutinas;
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


    
    public Rutinas_guardadas(ArrayList<Rutina> rutinas) {
        this.rutinas = rutinas;
        
        
        
        // Para que se cierre al darle a la X
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Título de la ventana
        setTitle("DEUSTOGYM");

        
        //crear ventana principal
        JPanel ventana_principal = new JPanel();
        ventana_principal.setLayout(new BorderLayout());

        // Crear ventana donde tablas y establecer layout
        this.ventanadondetablas = new JPanel();
        ventanadondetablas.setLayout(new GridLayout());
        GridBagConstraints constantes = new GridBagConstraints();

        
        //Inicializamos las barras de la derecha
        initbarras();

        //Inicializamos la tabla
        initTables();
        
        
        // Añadimos tabla	
        constantes.gridx = 0;
        constantes.gridy = 0;
        constantes.gridwidth = 1;
        constantes.gridheight = 1;
        constantes.fill = GridBagConstraints.BOTH; // El área de texto debe estirarse en ambos sentidos.
        constantes.weighty = 1.0;
        ventanadondetablas.add(scrollPanelRutinas, constantes); // También añadido al panel principal
        constantes.weighty = 0.0; // Restauramos al valor por defecto, para no afectar a los siguientes componentes.
        

        constantes_ej.gridx = 1;
        constantes_ej.gridy = 0;
        constantes_ej.gridwidth = 1;
        constantes_ej.gridheight = 1;
        constantes_ej.fill = GridBagConstraints.BOTH; // El área de texto debe estirarse en ambos sentidos.
        constantes_ej.weighty = 1.0;
        ventanadondetablas.add(scrollPanelejercicios, constantes); // También añadido al panel principal
        scrollPanelejercicios.setBorder(new TitledBorder("Ejercicios"));
        
        
        // BARRA PROGRESO
        JPanel panel_de_abajo = new JPanel();
        panel_de_abajo.setLayout(new FlowLayout());
        for(JProgressBar barra : barras) {
        	panel_de_abajo.add(barra);
        }
        ventana_principal.add(panel_de_abajo,BorderLayout.SOUTH);
        
        
      //boton asignar
      	JButton boton_asignar = new JButton("Asignar");
      	ActionListener listener_boton_asignar = e -> {
      		new Asinarrutina(this);
			
			};
		boton_asignar.addActionListener(listener_boton_asignar);
		panel_de_abajo.add(boton_asignar);
      		
		KeyListener keylistener = new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			@Override
			public void keyPressed(KeyEvent e) {
				//CUANDO SE PRESIONE CTRL X VOLVER A LA PAGINA PRINCIPAl
				if(e.isControlDown() && e.getKeyCode()==88) {
					new Pagina_principal();
					dispose();
				}
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				
			}
			
		};
		
		
		
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


        
        
        
        
        
        
        
        

        // Ajustar tamaño del JFrame
        float escalar = 0.5F;
        int ancho = (int) (Toolkit.getDefaultToolkit().getScreenSize().width * escalar);
        int alto = (int) (Toolkit.getDefaultToolkit().getScreenSize().height * escalar);
        this.setSize(ancho, alto);
        this.setResizable(true);
        setLocationRelativeTo(null);
        ventana_principal.add(ventanadondetablas,BorderLayout.CENTER);
        this.add(ventana_principal);
        
    }


    public void open() {
        setVisible(true);
        JOptionPane.showMessageDialog(null, "Control + x para volver a la pagina principal");


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
    
    private void initTables() {
    	
        // Crear el modelo de datos y la tabla
        modelo_de_datos_rutinas = new Modelo_de_datos_rutinas(rutinas); 
        JTable tablaRutinas = new JTable(modelo_de_datos_rutinas);
        this.tabla_rutinas=tablaRutinas;
        this.scrollPanelRutinas = new JScrollPane(tablaRutinas);
        scrollPanelRutinas.setBorder(new TitledBorder("Rutinas"));
        TableColumn columnaID = tabla_rutinas.getColumnModel().getColumn(2);
        columnaID.setPreferredWidth(20); 
        columnaID = tabla_rutinas.getColumnModel().getColumn(1);
        columnaID.setPreferredWidth(150); 
        
        

	  
	    TableCellRenderer cellRenderer = new TableCellRenderer() {
	        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            	JLabel jlabel = new JLabel();
        		jlabel.setOpaque(true);
            	jlabel.setVerticalAlignment(SwingConstants.CENTER);
            	
            	
            	//poner iconos en vez de texto
	            if (column==1 ) {
	            	jlabel.setHorizontalAlignment(SwingConstants.CENTER); 
	            	if(value.toString().equals("MUSCULACION")) {
	            		rescalar.setScaledImage(jlabel, "/resourses/images/musculacion.png", 40, 40);
	            	} else if(value.toString().equals("CARDIOVASCULAR")){
	            		rescalar.setScaledImage(jlabel, "/resourses/images/cardio.png", 40, 40);
	            	} else if(value.toString().equals("PERDIDA_DE_PESO")){
	            		rescalar.setScaledImage(jlabel, "/resourses/images/perdida_de_peso.png", 40, 40);
	            	}else {
	            		jlabel.setText("FALTA DE METER IMAGEN EN INIT  TABLE");
	            	}
        			jlabel.setToolTipText(value.toString());
        		      	 
	            }else {
	            }
	            	jlabel.setText(value.toString());
	            
	            	
	            	
	            
	            
	           
	            
	             //cambiar fonde dependiendo de fila par o impar
            	if(row %2 ==0) {
	            	jlabel.setBackground(new Color(250, 249, 249));
            	} else {
            		jlabel.setBackground(new Color(190, 227, 219));
            	}
            	
            	
            	

            	if(isSelected) {
            		jlabel.setBackground(tabla_rutinas.getSelectionBackground());
            	}
            	
            	
	            return jlabel;
	        }

			
	    };
	    this.tabla_rutinas.setRowHeight(40);
	    this.tabla_rutinas.setDefaultRenderer(Object.class, cellRenderer);
    }
    private void iniciar_tabla_ejercicio(int row) {
    	rutina_seleccionada = (Rutina) modelo_de_datos_rutinas.getValueAt(row, 999);
        modelo_de_datos_ejercicio.setListaEjercicios(rutina_seleccionada.getLista_ejercicios());
    	tabla_ejercicios = new JTable(modelo_de_datos_ejercicio);
        modelo_de_datos_ejercicio.fireTableDataChanged(); //IA (CHAT GPT)
         scrollPanelejercicios.setBorder(new TitledBorder("Ejercicios"));
         TableCellRenderer cellRenderer = new TableCellRenderer() {
 	        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
             	JLabel jlabel = new JLabel();
         		jlabel.setOpaque(true);
             	jlabel.setVerticalAlignment(SwingConstants.CENTER);
             	
             	if (column==2 ) {              	//poner iconos en vez de texto
 	            	jlabel.setText("Aun no hemos definido todos los ejercicios por lo que aun no hay foto");
         		      	 
 	            }else {
 	            }
 	            	jlabel.setText(value.toString());
 	            

             	
             	
             	

             	if(isSelected) {
             		jlabel.setBackground(tabla_ejercicios.getSelectionBackground());
             	}
             	
             	
 	            return jlabel;
 	        }

 			
 	    };
 	    

 	    if (ventanadondetablas.isAncestorOf(scrollPanelejercicios)) {
 	        ventanadondetablas.remove(scrollPanelejercicios);
 	    }
 	    scrollPanelejercicios.setViewportView(tabla_ejercicios);
 	    ventanadondetablas.add(scrollPanelejercicios, constantes_ej);
 	    
 	    ventanadondetablas.revalidate();
 	    ventanadondetablas.repaint();

 	   
		
	}
    
    	
    
    private void actualizar_barras(int fila) {
        Rutina rutina = rutinas.get(fila);
        for (JProgressBar barra : barras) {
            barra.setValue(0);
        }
        
        // Actualizar los valores de las barras según los ejercicios de la rutina
        for (Ejercicio ejercicio : rutina.getLista_ejercicios()) {
            if (ejercicio instanceof Ejercicio_gym) {
                   this.barra_gym.setValue(barra_gym.getValue() + 1);   
            } else if (ejercicio instanceof Ejercicio_Natacion) {
                this.barra_natacion.setValue(barra_natacion.getValue() + 1);   
            } else if (ejercicio instanceof Ejercicio_runing) {
                this.barra_running.setValue(barra_running.getValue() + 1);   
            }
        }
		

        
        // Normalizar valores en porcentaje
        for (JProgressBar barra : barras) {
            if (barra.getValue() != 0) {
                barra.setValue((barra.getValue() * 100) / rutina.getLista_ejercicios().size());
            }
        }
    }
    
    private void initbarras() {
    	barras=new ArrayList<>();
    	JProgressBar barra_gym = new JProgressBar(0,100);
    	barra_gym.setString("GYM");
    	barra_gym.setStringPainted(true);
    	JProgressBar barra_natacion = new JProgressBar(0,100);
    	barra_natacion.setString("NATACION");
    	barra_natacion.setStringPainted(true);
    	JProgressBar barra_running = new JProgressBar(0,100);
    	barra_running.setString("RUNNING");
    	barra_running.setStringPainted(true);
    	this.barra_gym=barra_gym;
    	this.barra_natacion = barra_natacion;
    	this.barra_running = barra_running;
    	this.barras.add(barra_gym);
    	this.barras.add(barra_natacion);
    	this.barras.add(barra_running);
        }
}
