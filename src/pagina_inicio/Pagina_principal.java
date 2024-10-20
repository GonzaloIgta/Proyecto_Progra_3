package pagina_inicio;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Pagina_principal extends JFrame{
	private static final long serialVersionUID = 1L;



	public Pagina_principal() {

		//para que se cierre al darle a la x
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //titulo de la ventana
        setTitle("GYMAPP");

        //Le añadimos color de fondo
		this.setBackground(new Color(130, 195, 65));

        // Creamos la ventana y definimos distribucion
		JPanel ventana_principal = new JPanel();
		
		
		//FUENTE-EXTERNA
		//URL: (https://chuidiang.org/index.php?title=Uso_de_Layouts)
		//ADAPTADO (hemos analizado el codigo para entender como funcionaba el layout y modificado para añadirle los elementos que necesitabamos)

		GridBagConstraints gbc = new GridBagConstraints();
		
		
		ventana_principal.setLayout(new GridBagLayout());
		ventana_principal.setBackground(Color.BLUE);
		
		
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
		gbc.gridy = 0;
		gbc.gridwidth = 1;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.NORTH; // aseguramos que esté en la parte superior
		ventana_principal.add(icono,gbc);
		
		
		
		//añadir boton Rutinas guardadas
		JButton rutinas_guardadas = new JButton();
		rutinas_guardadas.setText("Rutinas guardadas");
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.CENTER; //aseguramos que este centro
		ventana_principal.add(rutinas_guardadas,gbc);
		
		//Separar botones
		ImageIcon separar = new ImageIcon("resourses/images/foto_separar.jpg");
		Image separar_i = separar.getImage(); // Obtener la imagen original
        Image separar_redimensionada = separar_i.getScaledInstance(100, 100, Image.SCALE_SMOOTH); // Redimensionar a 100x100 píxeles
        ImageIcon separar_redimensionado = new ImageIcon(separar_redimensionada); 
		JLabel para_separar = new JLabel();
		para_separar.setIcon(separar_redimensionado);
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
        gbc.weightx = 1.0;
        //gbc.anchor = GridBagConstraints.NORTH; // aseguramos que esté en la parte superior
		ventana_principal.add(para_separar,gbc);
		
		//añadir boton añadir
		JButton añadir = new JButton();
		añadir.setText("Añadir nueva rutina");
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.SOUTH; //aseguramos que este abajo
		ventana_principal.add(añadir,gbc);
		
		
		
		
		this.add(ventana_principal);
        
        // Definimos el tamaño de la ventana
        //setSize(3000, 3000);  //FALTA HACER QUE SEA AUTOAJUSTABLE
        setExtendedState(JFrame.MAXIMIZED_BOTH);    //Hace que se abra en pantalla completa
        
     // Definir el tamaño mínimo de la ventana (ancho, alto en píxeles)
     	setMinimumSize(new Dimension(500, 400));
     	
     // Centrar la ventana en la pantalla
     	setLocationRelativeTo(null);

        // Hacemos visible la ventana principal
        setVisible(true);

	}
	
	
	
	 public static void main(String[] args) {
		 
		 Pagina_principal pagina = new Pagina_principal();
		 
	 }
}


