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
		
		panelUP.setPreferredSize(new Dimension(800, 75)); 
        
        
		panelUP.add(new JLabel("NumEjercicios"));

		//IAG: ChatGOT
		//SIN CAMBIOS: No sabíamos como funcionaba el modelo de spinner
        SpinnerNumberModel modelEjercicio = new SpinnerNumberModel(0, 0, 10, 1); 
        JSpinner spinnerEjercicio = new JSpinner(modelEjercicio);
        spinnerEjercicio.setBounds(100, 50, 100, 30); 

        
        panelUP.add(spinnerEjercicio);
        
		panelUP.add(new JLabel("Num MAX Rutinas"));

        SpinnerNumberModel modelEjercicio2= new SpinnerNumberModel(0, 0, 12, 1); 

       JSpinner spinnerRutinas = new JSpinner(modelEjercicio2);
       spinnerRutinas.setBounds(100, 50, 100, 30); 

       
       panelUP.add(spinnerRutinas);
       
        
        JButton generar = new JButton();
        generar.setText("Generar");
		
        
        //IAG: ChatGPT
        //ADAPTADO: no sabíamos exactamente como funcionaba un spinner
        
        final int[] spinnerValue = {0}; // Usamos un array para que sea final 

        spinnerEjercicio.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                spinnerValue[0] = (int) spinnerEjercicio.getValue(); 
            }
        });
        
        final int[] spinnerValue2 = {0}; 

        spinnerRutinas.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
            	spinnerValue2[0] = (int) spinnerRutinas.getValue(); 
            }
        });
        
        
        
  
		JComboBox<Ejercicio.tipoEjercicio> comboBoxTipoEj = new JComboBox<>(Ejercicio.tipoEjercicio.values());
		panelUP.add(comboBoxTipoEj);

	
        

        panelUP.add(generar);

        panelUP.add(new JSeparator());
        panelUP.add(new JLabel("Seleccione la Rutina que quiere añadir a sus rutinas: "));

        
        panelGenerarRutinas.add(panelUP, BorderLayout.NORTH);
        


        
		
        JPanel panelDWN = new JPanel();
        
        
        //IAG: IA
        //SIN CAMBIOS
        panelDWN.setLayout(new BoxLayout(panelDWN, BoxLayout.Y_AXIS)); // Configurar el BoxLayout vertical (Y_AXIS)

		JScrollPane scrollPane = new JScrollPane(panelDWN);

		panelGenerarRutinas.add(scrollPane, BorderLayout.CENTER);

        
		//IAG: ChatGPT
        // SIN CAMBIOS

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
					    null, 
					    "No se ha podido crear ninguna rutina con estos parámetros, por favor prueba a introducir otros valores.", 
					    "Advertencia", 
					    JOptionPane.WARNING_MESSAGE 
					);
        	}else {
        		
            	Map<String, List<Ejercicio>> mapaRutinas = new LinkedHashMap<>();

            	for (int i = 0; i < combinacionEjercicios.size(); i++) {
            	    String key = "RutinaComb" + (i + 1);
            	    
            	    List<Ejercicio> value = combinacionEjercicios.get(i);
            	    
            	    mapaRutinas.put(key, value);
            	}



            	for(Map.Entry<String, List<Ejercicio>> entry : mapaRutinas.entrySet()) {
            		JButton btn = new JButton();
            		
            		ActionListener buttonActionListener = new ActionListener() {
            		    @Override
            		    public void actionPerformed(ActionEvent e) {
            		        JButton btn = (JButton) e.getSource();

            		        String textButton = btn.getText();  
            		        
            		        String key = textButton.split(":")[0].trim(); 

            		        List<Ejercicio> listaEjerciciosDeLaRutina = mapaRutinas.get(key);

            		        if (listaEjerciciosDeLaRutina != null) {
            		            Random random = new Random();
            		            int randomNumber = random.nextInt(3); 
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
            		            	nombreField.setText("RutinaComb");
            		            	panelCambiarNombre.add(new JLabel("Nombre de la nueva rutina: "));
            		            	panelCambiarNombre.add(nombreField);

            		            	int respuesta = JOptionPane.showOptionDialog(
            		            	    null, 
            		            	    panelCambiarNombre, 
            		            	    "Hay otra rutina con el mismo nombre", 
            		            	    JOptionPane.OK_CANCEL_OPTION, 
            		            	    JOptionPane.WARNING_MESSAGE,
            		            	    null, 
            		            	    new Object[]{"Aceptar", "Cancelar"},
            		            	    "Aceptar" 
            		            	);

            		            	if (respuesta == JOptionPane.OK_OPTION && !nombreField.getText().trim().equals("") && !gestor.existeNombreRutina(nombreField.getText())) {
            		            	    nuevaRutina.setNombre(nombreField.getText());
            		            	    gestor.insertarRutina(nuevaRutina, usuario);

            		            	    dispose();
            		            	    ventanaAnterior.dispose();
            		            	    new Rutinas_guardadas(gestor, usuario);

            		            	} else if (respuesta == JOptionPane.OK_OPTION) {
            		            	    JOptionPane.showMessageDialog(
            		            	        null, 
            		            	        "El nombre de la rutina ya existe o ha introducido un nombre incorrecto", 
            		            	        "Error", 
            		            	        JOptionPane.ERROR_MESSAGE 
            		            	    );
            		            	}

            		            }



            					
            					
            		        } else {
            		            System.out.println("No se encontraron ejercicios para la rutina: " + key);
            		        }
            		    }
            		};


            	    
            		String nombreLabel ="";
            		
            		for(Ejercicio ej : entry.getValue()) {
            			

            			nombreLabel =entry.getKey() + ": " + entry.getValue();
            		}
            		

            		btn.addActionListener(buttonActionListener);
            		btn.setText(nombreLabel);
            		btn.setVisible(true);
            		panelDWN.add(btn);
            		panelDWN.revalidate(); 
                    panelDWN.repaint(); 
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
	
	

