package interfaces_graficas;

import java.awt.Color;
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
	private String prueba;
	private String prueba1;
	private String prueba2;
	private String prueba3;
	private String prueba4;




	public Pagina_principal() {

		//para que se cierre al darle a la x
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //titulo de la ventana
        setTitle("DEUSTOGYM");

        // Creamos la ventana y definimos distribucion
		JPanel ventana_principal = new JPanel();
		
		
		//FUENTE-EXTERNA
		//URL: (https://chuidiang.org/index.php?title=Uso_de_Layouts)
		//ADAPTADO (hemos analizado el codigo para entender como funcionaba el layout y modificado para añadirle los elementos que necesitabamos)

		GridBagConstraints gbc = new GridBagConstraints();
		
		
		ventana_principal.setLayout(new GridBagLayout());
		
		ventana_principal.setBackground(new Color(130, 195, 65));
		
		
		//añadimos el icono de la aplicacion en la parte de arriba
		ImageIcon icono_aplicacion = new ImageIcon("resourses/images/logotipo.png");
		
		//FUENTE-EXTERNA
				//URL: (https://blog.aspose.com/es/imaging/resize-images-in-java/)
				/*ADAPTADO (hemos modificado el tipo de escalado asi como las dimensiones 
				en el codigo de la web se guardaba la imagen una vez procesada y nosotros lo hemos 
				modificado de tal manera que vuelva crear otra imagen )
				 */
		// Redimensionar el icono
        Image imagen_original = icono_aplicacion.getImage(); // Obtener la imagen original
        Image imagen_redimensionada = imagen_original.getScaledInstance(100, 100, Image.SCALE_SMOOTH); // Redimensionar a 100x100 píxeles
        ImageIcon icono_redimensionado = new ImageIcon(imagen_redimensionada); 
		JLabel icono = new JLabel();
		icono.setIcon(icono_redimensionado);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.NORTH; // aseguramos que esté en la parte superior
		ventana_principal.add(icono,gbc);
		
		//añadir boton planning
		JButton planning = new JButton();
		planning.setFocusable(false);
		planning.setText("Planning semanal");
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.weightx = 1.0;
		gbc.anchor = GridBagConstraints.NORTH; //aseguramos que este arriba
		ventana_principal.add(planning,gbc);
		ActionListener listener_boton_planning = e -> {
				
			//new Planning();
			//dispose();
			};
		planning.addActionListener(listener_boton_planning);
		
		//añadir boton Rutinas guardadas
		JButton boton_rutinas_guardadas = new JButton();
		boton_rutinas_guardadas.setFocusable(false);
		boton_rutinas_guardadas.setText("Rutinas guardadas");
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.CENTER; //aseguramos que este centro
		ventana_principal.add(boton_rutinas_guardadas,gbc);
		ActionListener listener_rutinas_guardadas = e -> {
	        Rutina rutina = new Rutina("Rutina de Fuerza", Rutina.Objetivo_de_la_sesion.MUSCULACION, new ArrayList<>());
			ArrayList<Rutina> rutinas = new ArrayList<>();
			rutinas.add(rutina);
			new Rutinas_guardadas(rutinas);
			dispose();
			//HAY QUE PASARLE RUTINAS
        };
        
        
		boton_rutinas_guardadas.addActionListener(listener_rutinas_guardadas);
		
		
		//Separar botones
		ImageIcon separar = new ImageIcon("resourses/images/foto_separar.jpg");
		Image separar_i = separar.getImage(); // Obtener la imagen original
        Image separar_redimensionada = separar_i.getScaledInstance(100, 100, Image.SCALE_SMOOTH); // Redimensionar a 100x100 píxeles
        ImageIcon separar_redimensionado = new ImageIcon(separar_redimensionada); 
		JLabel para_separar = new JLabel();
		para_separar.setIcon(separar_redimensionado);
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
        gbc.weightx = 1.0;
		ventana_principal.add(para_separar,gbc);
		
		//añadir boton añadir
		JButton añadir = new JButton();
		añadir.setFocusable(false);
		añadir.setText("Añadir nueva rutina");
		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.gridwidth = 1;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.SOUTH; //aseguramos que este abajo
		ventana_principal.add(añadir,gbc);
		ActionListener listener_boton_añadir = e -> {
			
			new Nueva_Rutina();
			dispose();
	        };
	    añadir.addActionListener(listener_boton_añadir);
		
		
		
        
        

		//FUENTE-EXTERNA
				//URL: (https://www.forosdelweb.com/f45/ajuste-automatico-jframe-853529/)
				//SIN-CAMBIOS
		//Hacer que la pantalla se habra a la mitad del tamaño
		float escalar = 0.5F; // una ventana al 50% del tamaño de la pantalla
		int ancho = (int)(Toolkit.getDefaultToolkit().getScreenSize(). width*escalar);
		int alto = (int)(Toolkit.getDefaultToolkit().getScreenSize(). height*escalar);
		this.setSize(ancho,alto);

        // Permitir que el JFrame sea redimensionable
        this.setResizable(true);


     	
        // Centrar la ventana en la pantalla
     	setLocationRelativeTo(null);

        // Hacemos visible la ventana principal
        setVisible(true);

        
        
		this.add(ventana_principal);

	}
	
	
	

}


