package clases_de_apyo;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

//IAG (ChatGPT)
//SIN CAMBIOS 

public class Rescalar_imagen {

	public Rescalar_imagen() {}
    public void setScaledImage(JLabel jlabel, String imagePath, int width, int height) {
        // Cargar la imagen desde el recurso
        ImageIcon originalIcon = new ImageIcon(this.getClass().getResource(imagePath));
        
        // Escalar la imagen al tamaño deseado
        Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        
        // Crear un nuevo ImageIcon con la imagen escalada y establecerlo en el JLabel
        jlabel.setIcon(new ImageIcon(scaledImage));
    }
    public void setScaledImage(JButton jbutton, String imagePath, int width, int height) {
        // Cargar la imagen desde el recurso
        ImageIcon originalIcon = new ImageIcon(this.getClass().getResource(imagePath));
        
        // Escalar la imagen al tamaño deseado
        Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        
        // Crear un nuevo ImageIcon con la imagen escalada y establecerlo en el JLabel
        jbutton.setIcon(new ImageIcon(scaledImage));
    }
}
