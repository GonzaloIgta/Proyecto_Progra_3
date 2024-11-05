package interfaces_graficas;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import clases_de_apyo.Ejercicio;
import clases_de_apyo.Ejercicio_Natacion;
import clases_de_apyo.Musculo_trabajado;
import clases_de_apyo.Ejercicio_gym;
import clases_de_apyo.Ejercicio_runing;
import clases_de_apyo.Rutina;
import clases_de_apyo.Rutina.Objetivo_de_la_sesion;
import clases_de_apyo.estilo_natacion;

public class Pagina_principal extends JFrame{
	private static final long serialVersionUID = 1;

	

	public Pagina_principal() {
		ArrayList<Rutina> rutinas = new ArrayList<>();
        rutinas.add(new Rutina("Rutina de Fuerza", Rutina.Objetivo_de_la_sesion.MUSCULACION, new ArrayList<>()));
        rutinas.add(new Rutina("Rutina de Cardio", Rutina.Objetivo_de_la_sesion.PERDIDA_DE_PESO, new ArrayList<>()));
        rutinas.add(new Rutina("Rutina de Resistencia", Rutina.Objetivo_de_la_sesion.CARDIOVASCULAR, new ArrayList<>()));
        ArrayList<Ejercicio> ejerciciosMusculacion = new ArrayList<>();
        ejerciciosMusculacion.add(new Ejercicio_gym("Press de banca", "ubicacion_foto", Musculo_trabajado.PECHO, 10, 4, 60));
        ejerciciosMusculacion.add(new Ejercicio_gym("Sentadilla", "ubicacion_foto", Musculo_trabajado.PIERNA, 12, 4, 80));
        ejerciciosMusculacion.add(new Ejercicio_gym("Remo con barra", "ubicacion_foto", Musculo_trabajado.ESPALDA, 10, 3, 50));
        ejerciciosMusculacion.add(new Ejercicio_gym("Curl de bíceps", "ubicacion_foto", Musculo_trabajado.TRICEPS, 12, 3, 20));
        ejerciciosMusculacion.add(new Ejercicio_Natacion("Natación estilo libre", "ubicacion_foto", estilo_natacion.ESPALDA, 1.0f, 30.0f));
        ejerciciosMusculacion.add(new Ejercicio_Natacion("Natación de espalda", "ubicacion_foto", estilo_natacion.MARIPOSA, 0.8f, 35.0f));
        ejerciciosMusculacion.add(new Ejercicio_runing("Correr en la pista", "ubicacion_foto", 5, 30));
        ejerciciosMusculacion.add(new Ejercicio_runing("Correr en la calle", "ubicacion_foto", 10, 60));
        Rutina rutinaMusculacion = new Rutina("Rutina de Musculación", Objetivo_de_la_sesion.MUSCULACION, ejerciciosMusculacion);
        rutinas.add(rutinaMusculacion);
        
		Rutinas_guardadas rutinas_guardadas = new Rutinas_guardadas(rutinas);

		Nueva_Rutina nueva_rutina = new Nueva_Rutina();
		
		Planning planing = new Planning();
        
        
		// Creamos la ventana y definimos distribucion
		
		JPanel ventana_principal = new JPanel();
		
		ventana_principal.setLayout(new GridBagLayout());	
		
		
	      //PREGUNTAR A ROBERTO -------------------------------------------------------------------

			ImageIcon imagen_fondo = new ImageIcon(this.getClass().getResource("/resourses/images/Deustogym.jpg"));

			JLabel label_fondo = new JLabel(imagen_fondo);
			
			label_fondo.setSize(480,680);
			
			this.add(label_fondo); 
			
			ImageIcon icono = new ImageIcon(this.getClass().getResource("/resourses/images/deustoicon.png"));
			
			this.setIconImage(icono.getImage());
			
			
			
			//FUENTE-EXTERNA
			//URL: (https://old.chuidiang.org/java/layout/GridBagLayout/GridBagLayout.php)
			//ADAPTADO (Usado para entender y adapatar como usar gridbag layout y constraint )
		    //------------------------------------------------------------------------------------------
		   
		GridBagConstraints gbc = new GridBagConstraints();
		
		
		//BOTON RUTINAS GUARDADAS -----------------------------------------------------
		
		JButton boton_rutinas_guardadas = new JButton("Rutinas guardadas");
		
		boton_rutinas_guardadas.setPreferredSize(new Dimension(150,70));
		boton_rutinas_guardadas.setFocusable(false);
		gbc.gridx = 0; //posicion de columna 0
		gbc.gridy = 1; //posicion de fila 1
		gbc.gridwidth = 1; //el componente ocupara 1 solo espacio en el eje de columnas
		gbc.gridheight = 1; //el componente ocupara 1 solo espacio en el eje de filas
        gbc.weighty = 1.0;  //se distribuye el componente en el 100% del espacio      //FUENTE-EXTERNA, URL :https://stackoverflow.com/questions/5789513/weightx-and-weighty-in-java-gridbaglayout
        gbc.anchor = GridBagConstraints.CENTER; //AL NO CAMBIAR LUEGO EL GBC, TODOS LOS BOTONES ESTARAN CENTRADOS
        boton_rutinas_guardadas.setOpaque(true);
		ventana_principal.add(boton_rutinas_guardadas,gbc); //añadir el boton al GridBagConstraints

		
		//BOTON PLANNING -------------------------------------------------------------
		
		JButton planning = new JButton("Planning semanal");
		
		planning.setPreferredSize(new Dimension(150,70));
		planning.setFocusable(false);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 1.0;
		planning.setOpaque(true);
		ventana_principal.add(planning,gbc);
		
		
		ActionListener listener_boton_planning = e -> {
				
			planing.open();
			dispose();
			};
		planning.addActionListener(listener_boton_planning);
		
		//añadir boton Rutinas guardadas
		
		
		
		ActionListener listener_rutinas_guardadas = e -> {
			
			rutinas_guardadas.open();
			dispose();
			//HAY QUE PASARLE RUTINAS
        };
        
        
		boton_rutinas_guardadas.addActionListener(listener_rutinas_guardadas);
		
		
		
		
		//BOTON AÑADIR RUTINA ---------------------------
		JButton boton_añadir_rutina = new JButton("Añadir nueva rutina");
		
		boton_añadir_rutina.setPreferredSize(new Dimension(150,70));
		boton_añadir_rutina.setFocusable(false);
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
        gbc.weighty = 1.0;
        boton_añadir_rutina.setOpaque(true);
		ventana_principal.add(boton_añadir_rutina,gbc);
		ActionListener listener_boton_añadir = e -> {
			
			new Nueva_Rutina();
			dispose();
	        };
	        boton_añadir_rutina.addActionListener(listener_boton_añadir);
		

	  
	    // Hacemos visible la ventana principal
	    setVisible(true);

	    //no dejar hacer pantalla completa
	    setResizable(false);
	
	    setSize(480,680);
	 	
	    // Centrar la ventana en la pantalla
	 	setLocationRelativeTo(null);
	
		//para que se cierre al darle a la x
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    //titulo de la ventana
	    setTitle("DEUSTOGYM");
	
        // Centrar la ventana en la pantalla
     	setLocationRelativeTo(null);

        // Hacemos visible la ventana principal
        setVisible(true);

        ventana_principal.setOpaque(false);
        
      
		this.add(ventana_principal);


	}
	
	
	

}


