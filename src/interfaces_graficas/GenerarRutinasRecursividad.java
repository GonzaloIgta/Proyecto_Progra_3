package interfaces_graficas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;

import clases_de_apyo.Ejercicio;

import clases_de_apyo.Rutina;


public class GenerarRutinasRecursividad extends JFrame {
	
	/**
	 * 
	 */
	Ejercicio ej;
	private static final long serialVersionUID = 1L;
	List<Ejercicio >listaEjercicios;
	
	public GenerarRutinasRecursividad(List<Ejercicio >listaEjercicios) {
		
		this.listaEjercicios = listaEjercicios;
	
		JPanel panelGenerarRutinas = new JPanel(new BorderLayout());

		JPanel panelUP = new JPanel(new FlowLayout());
		
		
		panelUP.add(new JLabel("Número Rutinas"));
		 // Crear el JSpinner con un modelo de números
        SpinnerNumberModel modelRutinas = new SpinnerNumberModel(0, 0, 20, 1); // valor inicial, mínimo, máximo, incremento
        JSpinner spinnerRutinas = new JSpinner(modelRutinas);
        spinnerRutinas.setBounds(100, 50, 100, 30); // Posición y tamaño	

        
        panelUP.add(spinnerRutinas);
        
        
		panelUP.add(new JLabel("Número Ejercicios"));

		 // Crear el JSpinner con un modelo de números
        SpinnerNumberModel modelEjercicio = new SpinnerNumberModel(0, 0, 10, 1); // valor inicial, mínimo, máximo, incremento
        JSpinner spinnerEjercicio = new JSpinner(modelEjercicio);
        spinnerEjercicio.setBounds(100, 50, 100, 30); // Posición y tamaño

        
        panelUP.add(spinnerEjercicio);
        
        
        
        JButton generar = new JButton();
        generar.setText("Generar");
		
        generar.addActionListener(e -> {
			
			//llamada al metodo;
		});
        
        panelUP.add(generar);


        
        panelGenerarRutinas.add(panelUP, BorderLayout.NORTH);
        


        
		
        JPanel panelDWN = new JPanel();
		JScrollPane scrollPane = new JScrollPane(panelDWN);
		panelGenerarRutinas.add(scrollPane);
		
		
		this.add(panelGenerarRutinas);
		this.setSize(400, 400);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("GenerarRutinas");
        setLocationRelativeTo(null);
        setVisible(true);
	}
	
	
	
	private List<Rutina> generarRutinas(int NumRutinas, int NumEjercicios, List<Ejercicio> listaEjercicios){
		List<Rutina> listaRutinas = new ArrayList<>();
		

		



		
		
		generarRutinas(listaRutinas, listaEjercicios, new ArrayList<>(),  NumRutinas,  NumEjercicios);
		
		return listaRutinas;
		
		
	}
	
	private void generarRutinas(List<Rutina> result, List<Ejercicio> todosEjercicios, List<Ejercicio> listaAux, int NumRutinas, int NumEjercicios){

		
		
		
	}
	
	

}
