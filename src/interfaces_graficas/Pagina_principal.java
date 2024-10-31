package interfaces_graficas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import clases_de_apyo.Rutina;

public class Pagina_principal extends JFrame{
	private static final long serialVersionUID = 1L;
	

	public Pagina_principal() {
		
		
		// Creamos la ventana y definimos distribucion
		
		JPanel ventana_principal = new JPanel();
		
		ventana_principal.setLayout(new GridBagLayout());	
		
		//PREGUNTAR A ROBERTO -------------------------------------------------------------------

		ImageIcon imagen_fondo = new ImageIcon(this.getClass().getResource("Deustogym.jpg"));

		JLabel label_fondo = new JLabel(imagen_fondo);
		
		label_fondo.setSize(480,680);
		
		this.add(label_fondo); 
		
		ImageIcon icono = new ImageIcon("deustoicon.png");
		
		this.setIconImage(icono.getImage());
		
		
		

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
		
		ventana_principal.add(planning,gbc);
		
		
		ActionListener listener_boton_planning = e -> {
				
			new Planning();
			dispose();
			};
		planning.addActionListener(listener_boton_planning);
		
		//añadir boton Rutinas guardadas
		
		
		
		ActionListener listener_rutinas_guardadas = e -> {
	        Rutina rutina = new Rutina("Rutina de Fuerza", Rutina.Objetivo_de_la_sesion.MUSCULACION, new ArrayList<>());
			ArrayList<Rutina> rutinas = new ArrayList<>();
			rutinas.add(rutina);
			new Rutinas_guardadas(rutinas);
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
		
		
		ventana_principal.add(boton_añadir_rutina,gbc);
		ActionListener listener_boton_añadir = e -> {
			
			new Nueva_Rutina();
			dispose();
	        };
	        boton_añadir_rutina.addActionListener(listener_boton_añadir);
		
		
		
        
        

		//FUENTE-EXTERNA
				//URL: (https://www.forosdelweb.com/f45/ajuste-automatico-jframe-853529/)
				//SIN-CAMBIOS
		//Hacer que la pantalla se habra a la mitad del tamaño
	    
	    /*
		float escalar = 0.5F; // una ventana al 50% del tamaño de la pantalla
		int ancho = (int)(Toolkit.getDefaultToolkit().getScreenSize(). width*escalar);
		int alto = (int)(Toolkit.getDefaultToolkit().getScreenSize(). height*escalar);
		this.setSize(ancho,alto);

		*/
	        

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
	

	   
 
	  
		this.add(ventana_principal);


	}
	
	
	

}


