package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import domain.Ejercicio;
import domain.Ejercicio_Natacion;
import domain.Ejercicio_cardio;
import domain.Ejercicio_gym;
import domain.Rutina;
import domain.Rutina.Objetivo_de_la_sesion;


public class GenerarRutinasRecursividad extends JFrame {
	
	/**
	 * 
	 */
	
    private String selectedValue = "Ejercicio_cardio";

	private Ejercicio ej;
	private static final long serialVersionUID = 1L;
	private List<Ejercicio> listaEjercicios;
	private List<List<Ejercicio>> combinacionEjercicios;
	public static int idRutinaAL;
	
	public GenerarRutinasRecursividad(List<Ejercicio >listaEjercicios) {
		
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
        SpinnerNumberModel modelEjercicio2= new SpinnerNumberModel(0, 0, 10, 1); // valor inicial, mínimo, máximo, incremento

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
        	

        	combinacionEjercicios = new ArrayList<>(generarRutinas(spinnerValue[0] , listaEjercicios, spinnerValue2[0], selectedValue));
        	


        	for(List<Ejercicio> lej : combinacionEjercicios) {
        		JButton btn = new JButton();
        		
        		idRutinaAL++;
        		String nombreLabel = "RutinaAL"+idRutinaAL+": ";
        		for(Ejercicio ej : lej) {
        			
        			System.out.println(ej.getNombre());
        			nombreLabel = nombreLabel+ej.getNombre()+", ";
        		}
        		btn.setText(nombreLabel);
        		btn.setVisible(true);
        		panelDWN.add(btn);
        		panelDWN.revalidate(); // Revalidar el panel después de agregar los nuevos componentes
                panelDWN.repaint(); // Repintar para asegurar que se muestre todo
        		this.repaint();
        		       		
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
	
	

