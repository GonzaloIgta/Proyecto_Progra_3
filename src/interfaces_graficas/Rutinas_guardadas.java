package interfaces_graficas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.EventObject;
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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import clases_de_apyo.AsignarRendererEditor;
//import clases_de_apyo.Dialogo_asignar;
import clases_de_apyo.Ejercicio;
import clases_de_apyo.Ejercicio_Natacion;
import clases_de_apyo.Ejercicio_gym;
import clases_de_apyo.Ejercicio_runing;
import clases_de_apyo.Modelo_de_datos_rutinas;
import clases_de_apyo.Musculo_trabajado;
import clases_de_apyo.Rescalar_imagen;
import clases_de_apyo.Rutina;
import clases_de_apyo.estilo_natacion;

public class Rutinas_guardadas extends JFrame {
	private static final long serialVersionUID = 1L;
	private List<Rutina> rutinas;
    private Modelo_de_datos_rutinas modelo_de_datos;
    private JTable tabla_rutinas;
    private JScrollPane scrollPanelRutinas;
    private ArrayList<JProgressBar> barras_gym = new ArrayList<>();
    private ArrayList<JProgressBar> barras_natacion = new ArrayList<>();
    private ArrayList<JProgressBar> barras_running = new ArrayList<>();
    private Rescalar_imagen rescalar = new Rescalar_imagen();
    
    public Rutinas_guardadas(ArrayList<Rutina> rutinas) {
        this.rutinas = rutinas;
        
        
        
        // Para que se cierre al darle a la X
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Título de la ventana
        setTitle("DEUSTOGYM");

        // Crear ventana principal y establecer layout
        JPanel ventana_principal = new JPanel();
        ventana_principal.setLayout(new GridBagLayout());
        GridBagConstraints constantes = new GridBagConstraints();

        
        //Inicializamos las barras de la derecha
        initbarras();

        //Inicializamos la tabla
        initTables();
        
        
        // Añadimos tabla	
        constantes.gridx = 0;
        constantes.gridy = 0;
        constantes.gridwidth = 2;
        constantes.gridheight = 2;
        constantes.fill = GridBagConstraints.BOTH; // El área de texto debe estirarse en ambos sentidos.
        constantes.weighty = 1.0;
        ventana_principal.add(scrollPanelRutinas, constantes); // También añadido al panel principal
        constantes.weighty = 0.0; // Restauramos al valor por defecto, para no afectar a los siguientes componentes.
      //FUENTE EXTERNA
      //IAG (ChatGPT)
      //ADAPTADO (Ia consultada para conseguir que las barras no se expandiesen de tal manera que no puediesemos ver la tabla de la izq, asi como la disposicion de barras) 
     // Añadir las progres bar de la derecha
        constantes.gridx = 2; 
        constantes.gridy = 0; 
        constantes.gridwidth = 1; 
        constantes.weightx = 0.2; 
        constantes.weighty = 1.0; // Permitir que las barras ocupen todo el espacio vertical

        // Crear un panel principal para las secciones
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new GridBagLayout());
        GridBagConstraints seccionConstantes = new GridBagConstraints();
        seccionConstantes.fill = GridBagConstraints.HORIZONTAL;
        seccionConstantes.weightx = 1.0;

        // Sección de Gimnasio
        JPanel gimnasioPanel = new JPanel();
        gimnasioPanel.setLayout(new GridBagLayout());
        gimnasioPanel.setBorder(new TitledBorder("Gimnasio"));
        GridBagConstraints barraConstantes = new GridBagConstraints();
        barraConstantes.fill = GridBagConstraints.HORIZONTAL;
        barraConstantes.weightx = 1.0; // Hacer que las barras se expandan horizontalmente
        barraConstantes.gridy = 0; // Empezar en la primera fila
        for (JProgressBar barra : barras_gym) {
            gimnasioPanel.add(barra, barraConstantes);
            barraConstantes.gridy++; 
        }

        // Sección de Natación
        JPanel natacionPanel = new JPanel();
        natacionPanel.setLayout(new GridBagLayout());
        natacionPanel.setBorder(new TitledBorder("Natación"));
        barraConstantes.gridy = 0; // Reiniciar a la primera fila
        for (JProgressBar barra : barras_natacion) { 
            natacionPanel.add(barra, barraConstantes);
            barraConstantes.gridy++; // Mover a la siguiente fila
        }

        // Sección de Running
        JPanel runningPanel = new JPanel();
        runningPanel.setLayout(new GridBagLayout());
        runningPanel.setBorder(new TitledBorder("Running"));
        barraConstantes.gridy = 0; // Reiniciar a la primera fila
        for (JProgressBar barra : barras_running) { 
            runningPanel.add(barra, barraConstantes);
            barraConstantes.gridy++; // Mover a la siguiente fila
        }

        // Añadir los paneles al panel principal
        GridBagConstraints panelConstantes = new GridBagConstraints();
        panelConstantes.gridx = 0; 
        panelConstantes.gridy = 0; 
        panelConstantes.fill = GridBagConstraints.HORIZONTAL; 
        panelConstantes.weightx = 1.0;

        panelPrincipal.add(gimnasioPanel, panelConstantes);
        panelConstantes.gridy++; // Pasar a la siguiente fila
        panelPrincipal.add(natacionPanel, panelConstantes);
        panelConstantes.gridy++; // Pasar a la siguiente fila
        panelPrincipal.add(runningPanel, panelConstantes);

        // Añadir el panel principal al panel de la ventana
        ventana_principal.add(panelPrincipal, constantes); 

        
        
        
        
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
		

        
        
        
        
        
        
        
        
        // Añadir ventana_principal al JFrames
        this.setContentPane(ventana_principal); // Establece el panel principal como contenido del JFrame

        // Ajustar tamaño del JFrame
        float escalar = 0.5F;
        int ancho = (int) (Toolkit.getDefaultToolkit().getScreenSize().width * escalar);
        int alto = (int) (Toolkit.getDefaultToolkit().getScreenSize().height * escalar);
        this.setSize(ancho, alto);
        this.setResizable(true);
        setLocationRelativeTo(null);
        setVisible(true);
        
        JOptionPane informativo = new JOptionPane(	);
        informativo.showMessageDialog(null, "Control + x para volver a la pagina principal");
        
    }




    

    public List<Rutina> getRutinas() {
        return rutinas;
    }

    public void setRutinas(List<Rutina> rutinas) {
        this.rutinas = rutinas;
    }
    
    private void initTables() {
    	
        // Crear el modelo de datos y la tabla
        modelo_de_datos = new Modelo_de_datos_rutinas(rutinas); 
        JTable tablaRutinas = new JTable(modelo_de_datos);
        this.tabla_rutinas=tablaRutinas;
        this.scrollPanelRutinas = new JScrollPane(tablaRutinas);
        scrollPanelRutinas.setBorder(new TitledBorder("Rutinas"));
        TableColumn columnaID = tabla_rutinas.getColumnModel().getColumn(2);
        columnaID.setPreferredWidth(20); 
        columnaID = tabla_rutinas.getColumnModel().getColumn(3);
        columnaID.setPreferredWidth(20); 
        columnaID = tabla_rutinas.getColumnModel().getColumn(1);
        columnaID.setPreferredWidth(150); 

	  
	    TableCellRenderer cellRenderer = new TableCellRenderer() {
	        @Override
	        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            	JLabel jlabel = new JLabel();
        		jlabel.setOpaque(true);
            	jlabel.setVerticalAlignment(SwingConstants.CENTER);
            	JButton boton_asignar = new JButton("Asig");
            	boton_asignar.setSize(new Dimension(10,10));

            	
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
            		actualizar_barras(row);
            	}
            	
            	
	            return jlabel;
	        }
	    };
	    tabla_rutinas.getColumnModel().getColumn(3).setCellRenderer(new AsignarRendererEditor(this));
	    this.tabla_rutinas.setRowHeight(40);
	    this.tabla_rutinas.setDefaultRenderer(Object.class, cellRenderer);
    }
    
    private void actualizar_barras(int fila) {
        Rutina rutina = rutinas.get(fila);
        for (JProgressBar barra : barras_gym) {
            barra.setValue(0);
        }
        for (JProgressBar barra : barras_natacion) {
            barra.setValue(0);
        }
        for (JProgressBar barra : barras_running) {
            barra.setValue(0);
        }
        
        // Actualizar los valores de las barras según los ejercicios de la rutina
        for (Ejercicio ejercicio : rutina.getLista_ejercicios()) {
            if (ejercicio instanceof Ejercicio_gym) {
                Ejercicio_gym ejGym = (Ejercicio_gym) ejercicio;
                for (JProgressBar barra : barras_gym) {
                    if (barra.getString().equals(ejGym.musculo_trabajado.toString())) {
                        barra.setValue(barra.getValue() + 1);
                    }
                }
            } else if (ejercicio instanceof Ejercicio_Natacion) {
                Ejercicio_Natacion ejNat = (Ejercicio_Natacion) ejercicio;
                for (JProgressBar barra : barras_natacion) {
                    if (barra.getString().equals(ejNat.getEstilo().toString())) {
                        barra.setValue(barra.getValue() + 1);
                    }
                }
            } else if (ejercicio instanceof Ejercicio_runing) {
                for (JProgressBar barra : barras_running) {
                    barra.setValue(barra.getValue() + 1);
                }
            }
        }

        
        // Normalizar valores en porcentaje
        for (JProgressBar barra : barras_gym) {
            if (barra.getValue() != 0) {
                barra.setValue((barra.getValue() * 100) / rutina.getLista_ejercicios().size());
            }
        }
        for (JProgressBar barra : barras_natacion) {
            if (barra.getValue() != 0) {
                barra.setValue((barra.getValue() * 100) / rutina.getLista_ejercicios().size());
            }
        }
        for (JProgressBar barra : barras_running) {
            if (barra.getValue() != 0) {
                barra.setValue((barra.getValue() * 100) / rutina.getLista_ejercicios().size());
                barra.repaint();
            }
        }
    }
    
    private void initbarras() {
    	for (Musculo_trabajado musculo : Musculo_trabajado.values()) {
    		JProgressBar barra = new JProgressBar(0, 100);
        	barra.setStringPainted(true);
        	barra.setString(musculo.toString());
            barra.setPreferredSize(new java.awt.Dimension(80, 15)); // Ajustar el tamaño de las barras de progreso
            barras_gym.add(barra);
    	}

    	for(estilo_natacion estilo : estilo_natacion.values()) {
    		JProgressBar barra = new JProgressBar(0, 100);
        	barra.setStringPainted(true);
        	barra.setString(estilo.toString());
            barra.setPreferredSize(new java.awt.Dimension(80, 15)); // Ajustar el tamaño de las barras de progreso
        	barras_natacion.add(barra);
    	}
    	
    	JProgressBar barra = new JProgressBar(0, 100);
    	barra.setStringPainted(true);
    	barra.setString("Running");
        barra.setPreferredSize(new java.awt.Dimension(80, 15)); // Ajustar el tamaño de las barras de progreso
        barras_running.add(barra);
    	
    }
    
}
