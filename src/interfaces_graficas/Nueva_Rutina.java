package interfaces_graficas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;

public class Nueva_Rutina extends JFrame{
	private static final long serialVersionUID = 1L;


	public Nueva_Rutina() {

		//para que se cierre al darle a la x
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //titulo de la ventana
        setTitle("Nueva Rutina");

        // Creamos la ventana y definimos distribucion
		JPanel ventanaPrincipal_nuevaRutina = new JPanel(new BorderLayout());
				
		JPanel ventana_central_MuestraRutinas = new JPanel(new GridLayout());
		ventana_central_MuestraRutinas.setSize(10, 10);

		//FUENTE-EXTERNA
		//URL: (https://chuidiang.org/index.php?title=Uso_de_Layouts)
		//ADAPTADO (hemos analizado el codigo para entender como funcionaba el layout y modificado para añadirle los elementos que necesitabamos)

		JPanel layout_botones = new JPanel(new FlowLayout());
		
		layout_botones.add(new JButton("Fuerza"));
		layout_botones.add(new JButton("Cardio"));
		layout_botones.add(new JButton("Natación"));
		
		ventanaPrincipal_nuevaRutina.add(layout_botones, BorderLayout.NORTH);


		
		//GridBagConstraints gbc = new GridBagConstraints();
		
		
		//ventana_nueva_rutina.setLayout(new GridBagLayout());
		
		ventanaPrincipal_nuevaRutina.add(new JTree(), BorderLayout.WEST);
		ventanaPrincipal_nuevaRutina.add(ventana_central_MuestraRutinas);
        
        
		ventanaPrincipal_nuevaRutina.setBackground(new Color(130, 195, 65));
		
		
		//añadimos el icono de la aplicacion en la parte de arriba
		ImageIcon icono_aplicacion = new ImageIcon("resourses/images/logotipo.png");
		
		//FUENTE-EXTERNA
				//URL: (https://blog.aspose.com/es/imaging/resize-images-in-java/)
				/*ADAPTADO (hemos modificado el tipo de escalado asi como las dimensiones 
				en el codigo de la web se guardaba la imagen una vez procesada y nosotros lo hemos 
				modificado de tal manera que vuelva crear otra imagen )
				 */

	
		//desde aqui
		
		
		//hasta aqui
		
		
		/*
		//añadir boton Rutinas guardadas
		JButton btn_rutina_fuerza = new JButton();
		btn_rutina_fuerza.setText("Fuerza");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        ventana_nueva_rutina.add(btn_rutina_fuerza,gbc);
		ActionListener listener_fuerza = e -> {
			
        };
        
        btn_rutina_fuerza.addActionListener(listener_fuerza);

        
		
		//añadir boton Rutinas guardadas
		JButton btn_rutina_cardio = new JButton();
		btn_rutina_cardio.setText("Cardio");
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
        gbc.weightx = 1.0;
       gbc.fill = GridBagConstraints.HORIZONTAL; 
        ventana_nueva_rutina.add(btn_rutina_cardio,gbc);
		ActionListener listener_cardio = e -> {
			
        };
        
        btn_rutina_cardio.addActionListener(listener_cardio);
        
		//añadir boton Rutinas guardadas
		JButton btn_rutina_natacion = new JButton();
		btn_rutina_natacion.setText("Natación");
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        ventana_nueva_rutina.add(btn_rutina_natacion,gbc);
		ActionListener listener_natacion= e -> {
			
        };
        
        btn_rutina_natacion.addActionListener(listener_natacion);
		*/
		
		


	
		
		
        
        // Definimos el tamaño de la ventana
        //setSize(3000, 3000);  //FALTA HACER QUE SEA AUTOAJUSTABLE
        //setExtendedState(JFrame.MAXIMIZED_BOTH);    //Hace que se abra en pantalla completa
        
     // Definir el tamaño mínimo de la ventana (ancho, alto en píxeles)
     	setSize(350, 600);
		setResizable(false);
		
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

        
        
		this.add(ventanaPrincipal_nuevaRutina);

	}

}
