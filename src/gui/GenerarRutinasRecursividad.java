package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import db.GestorBD;
import domain.Ejercicio;
import domain.Ejercicio_Natacion;
import domain.Ejercicio_cardio;
import domain.Ejercicio_gym;
import domain.Rutina;
import domain.Rutina.Objetivo_de_la_sesion;

import java.util.LinkedHashMap;

public class GenerarRutinasRecursividad extends JFrame {
	
	/**
	 * 
	 */
	
    private String selectedValue = "Ejercicio_cardio";


	private Ejercicio ej;
	private static final long serialVersionUID = 1L;
	private List<Ejercicio> listaEjercicios;
	private List<List<Ejercicio>> combinacionEjercicios;
	private GestorBD gestor = new GestorBD();
	private String usuario;
	private JFrame ventanaAnterior; 
	public GenerarRutinasRecursividad(List<Ejercicio >listaEjercicios, String usuario, GestorBD gestor, JFrame ventana) {
		
		ventanaAnterior=ventana;
		this.usuario = usuario;
		this.gestor = gestor;
		this.listaEjercicios = new ArrayList<>(listaEjercicios);
	
		JPanel panelGenerarRutinas = new JPanel(new BorderLayout());

		JPanel panelUP = new JPanel(new FlowLayout());
		
		panelUP.setPreferredSize(new Dimension(800, 75)); // Ajusta el ancho y alto
        
        
		panelUP.add(new JLabel("NumEjercicios"));

		 // Crear el JSpinner con un modelo de números
        SpinnerNumberModel modelEjercicio = new SpinnerNumberModel(0, 0, 10, 1); // valor inicial, mínimo, máximo, incremento
        JSpinner spinnerEjercicio = new JSpinner(modelEjercicio);
        spinnerEjercicio.setBounds(100, 50, 100, 30); // Posición y tamaño

        
        panelUP.add(spinnerEjercicio);
        
		panelUP.add(new JLabel("Num MAX Rutinas"));

		 // Crear el JSpinner con un modelo de números
        SpinnerNumberModel modelEjercicio2= new SpinnerNumberModel(0, 0, 12, 1); // valor inicial, mínimo, máximo, incremento

       JSpinner spinnerRutinas = new JSpinner(modelEjercicio2);
       spinnerRutinas.setBounds(100, 50, 100, 30); // Posición y tamaño

       
       panelUP.add(spinnerRutinas);
       
        
        JButton generar = new JButton();
        generar.setText("Generar");
		
        
        //IA
        // Variable para guardar el valor del spinner
        final int[] spinnerValue = {0}; // Usamos un array para que sea efectivamente final

        // Agregar un ChangeListener al spinner
        spinnerEjercicio.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                spinnerValue[0] = (int) spinnerEjercicio.getValue(); // Actualizar la variable
            }
        });
        
        final int[] spinnerValue2 = {0}; // Usamos un array para que sea efectivamente final

        // Agregar un ChangeListener al spinner
        spinnerRutinas.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
            	spinnerValue2[0] = (int) spinnerRutinas.getValue(); // Actualizar la variable
            }
        });
        
        
        
  
		JComboBox<Ejercicio.tipoEjercicio> comboBoxTipoEj = new JComboBox<>(Ejercicio.tipoEjercicio.values());
		panelUP.add(comboBoxTipoEj);

	
        

        panelUP.add(generar);

        panelUP.add(new JSeparator());
        panelUP.add(new JLabel("Seleccione la Rutina que quiere añadir a sus rutinas: "));

        
        panelGenerarRutinas.add(panelUP, BorderLayout.NORTH);
        


        
		
        JPanel panelDWN = new JPanel();
        panelDWN.setLayout(new BoxLayout(panelDWN, BoxLayout.Y_AXIS)); // Configurar el BoxLayout vertical (Y_AXIS)

		JScrollPane scrollPane = new JScrollPane(panelDWN);

		panelGenerarRutinas.add(scrollPane, BorderLayout.CENTER);

        
		//IA
        // Variable para almacenar el valor seleccionado

        // Agregar un ItemListener para capturar los cambios de selección
        comboBoxTipoEj.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    // Guardar el valor seleccionado en la variable
                    selectedValue = String.valueOf(comboBoxTipoEj.getSelectedItem());
                    // Mostrar el valor en la consola (para ver el cambio)
                }
            }
        });
        

        generar.addActionListener(e -> {


        	panelDWN.removeAll();
        	panelDWN.repaint();
        	panelDWN.revalidate();
        	// Obtener combinacionEjercicios
        	combinacionEjercicios = new ArrayList<>(generarRutinas(spinnerValue[0], listaEjercicios, spinnerValue2[0], selectedValue));

        	
        	if(combinacionEjercicios.isEmpty() || spinnerValue[0]==0 || spinnerValue2[0]==0) {
        		
        		JOptionPane.showMessageDialog(
					    null, // Componente padre
					    "No se ha podido crear ninguna rutina con estos parámetros, por favor prueba a introducir otros valores.", // Mensaje
					    "Advertencia", // Título del cuadro
					    JOptionPane.WARNING_MESSAGE // Tipo de mensaje
					);
        	}else {
        		
            	// Crear un mapa donde el Key sea "RutinaAL" seguido de un contador y el Value sea la lista de ejercicios
            	Map<String, List<Ejercicio>> mapaRutinas = new LinkedHashMap<>();

            	// Recorrer la lista de listas y agregar al mapa
            	for (int i = 0; i < combinacionEjercicios.size(); i++) {
            	    // Crear la clave "RutinaAL" + contador
            	    String key = "RutinaAL" + (i + 1); // "RutinaAL1", "RutinaAL2", etc.
            	    
            	    // Obtener la lista correspondiente al índice
            	    List<Ejercicio> value = combinacionEjercicios.get(i);
            	    
            	    // Agregar la clave y el valor al mapa
            	    mapaRutinas.put(key, value);
            	}



            	for(Map.Entry<String, List<Ejercicio>> entry : mapaRutinas.entrySet()) {
            		JButton btn = new JButton();
            		
            		// Crear un ActionListener para los botones
            		ActionListener buttonActionListener = new ActionListener() {
            		    @Override
            		    public void actionPerformed(ActionEvent e) {
            		        // Obtener el botón que fue presionado
            		        JButton btn = (JButton) e.getSource();

            		        // Obtener el texto del botón que corresponde a la rutina: "RutinaAL1: [Bici...]"
            		        String textButton = btn.getText();  
            		        
            		        // Extraer solo el "Key" antes del ":"
            		        String key = textButton.split(":")[0].trim(); // Esto obtiene "RutinaAL1", "RutinaAL2", etc.

            		        List<Ejercicio> listaEjerciciosDeLaRutina = mapaRutinas.get(key);

            		        if (listaEjerciciosDeLaRutina != null) {
            		            Random random = new Random();
            		            int randomNumber = random.nextInt(3); // Asumimos que los objetivos son 3 tipos
            		            ArrayList<Ejercicio>listaEjerciciosRutina = new ArrayList<>(listaEjerciciosDeLaRutina);
            		            Rutina nuevaRutina = new Rutina(key, Objetivo_de_la_sesion.values()[randomNumber], listaEjerciciosRutina);

            		            
            		            
            		            if(!gestor.existeNombreRutina(nuevaRutina.getNombre())) {
            		            	
                					gestor.insertarRutina(nuevaRutina,usuario);

                					ventanaAnterior.dispose();

                					dispose();
                					new Rutinas_guardadas(gestor,usuario);

            		            }else {
            		            	

            		            	JPanel panelCambiarNombre = new JPanel();
            		            	JTextField nombreField = new JTextField(20);
            		            	nombreField.setText("RutinaAL");
            		            	panelCambiarNombre.add(new JLabel("Nombre de la nueva rutina: "));
            		            	panelCambiarNombre.add(nombreField);

            		            	int respuesta = JOptionPane.showOptionDialog(
            		            	    null, // Componente padre
            		            	    panelCambiarNombre, // Panel con contenido
            		            	    "Hay otra rutina con el mismo nombre", // Título del cuadro
            		            	    JOptionPane.OK_CANCEL_OPTION, // Opciones OK y Cancel
            		            	    JOptionPane.WARNING_MESSAGE, // Tipo de mensaje
            		            	    null, // Icono (null para usar el predeterminado)
            		            	    new Object[]{"Aceptar", "Cancelar"}, // Botones personalizados
            		            	    "Aceptar" // Botón seleccionado por defecto
            		            	);

            		            	if (respuesta == JOptionPane.OK_OPTION && !nombreField.getText().trim().equals("") && !gestor.existeNombreRutina(nombreField.getText())) {
            		            	    nuevaRutina.setNombre(nombreField.getText());
            		            	    gestor.insertarRutina(nuevaRutina, usuario);

            		            	    dispose();
            		            	    ventanaAnterior.dispose();
            		            	    new Rutinas_guardadas(gestor, usuario);

            		            	} else if (respuesta == JOptionPane.OK_OPTION) {
            		            	    JOptionPane.showMessageDialog(
            		            	        null, // Referencia al componente padre
            		            	        "El nombre de la rutina ya existe o ha introducido un nombre incorrecto", // Mensaje
            		            	        "Error", // Título del cuadro de diálogo
            		            	        JOptionPane.ERROR_MESSAGE // Tipo de mensaje
            		            	    );
            		            	}

            		            }



            					
            					
            		        } else {
            		            // Si no se encuentra la lista de ejercicios, mostrar un mensaje de error
            		            System.out.println("No se encontraron ejercicios para la rutina: " + key);
            		        }
            		    }
            		};


            	    
            		String nombreLabel ="";
            		
            		for(Ejercicio ej : entry.getValue()) {
            			
            			//System.out.println(ej.getNombre());

            			nombreLabel =entry.getKey() + ": " + entry.getValue();
            		}
            		

            		btn.addActionListener(buttonActionListener);
            		btn.setText(nombreLabel);
            		btn.setVisible(true);
            		panelDWN.add(btn);
            		panelDWN.revalidate(); // Revalidar el panel después de agregar los nuevos componentes
                    panelDWN.repaint(); // Repintar para asegurar que se muestre todo
            		this.repaint();
    	
            	}
    		
        	}
        	
});
        

    
    


		    
        
        
        
        
		
		
		this.add(panelGenerarRutinas);
		this.setSize(400, 400);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("GenerarRutinas");
        setLocationRelativeTo(null);
        setVisible(true);
	}
	
	
	
	private List<List<Ejercicio>> generarRutinas(int NumEjercicios, List<Ejercicio> listaEjercicios, int NumMaxRutinas, String tipoEj){
		List<List<Ejercicio>> listaRutinas = new ArrayList<>();
		
		
		generarRutinas(listaRutinas, listaEjercicios, new ArrayList<>(), NumEjercicios, NumMaxRutinas, tipoEj);
		
		return listaRutinas;
		
		
	}
	
	private void generarRutinas(List<List<Ejercicio>> result, List<Ejercicio> todosEjercicios, List<Ejercicio> listaAux, int NumEjercicios, int NumMaxRutinas, String tipoEj){

		//1.Caso Base: cantidad de rutinas en result == NumMaxRutinas
		
		if(result.size()==NumMaxRutinas) {
			return;
		}
		

		//2.Caso Base: cantidad de ejercicios en listaAux == numEjercicios
		
		if(listaAux.size()==NumEjercicios) {
			
			List<Ejercicio> listaEj = new ArrayList<>(listaAux);
			Collections.sort(listaEj);
			
			if(!result.contains(listaEj)) {
				
				result.add(listaEj);
				
			}
			
		}else {
			
			for(Ejercicio ej : todosEjercicios) {
				
				
					if(!listaAux.contains(ej) && ej.getClass().getSimpleName().equals(tipoEj)) {
						listaAux.add(ej);
						generarRutinas(result, todosEjercicios, listaAux, NumEjercicios, NumMaxRutinas, tipoEj);

						listaAux.remove(listaAux.size()-1);
						
					}

					
					
				}
				
				
			}
			
			
		}
		
		
		
	}
	
	

