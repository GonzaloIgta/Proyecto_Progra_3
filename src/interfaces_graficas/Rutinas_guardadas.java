package interfaces_graficas;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import clases_de_apyo.Modelo_de_datos_rutinas;
import clases_de_apyo.Rutina;

public class Rutinas_guardadas extends JFrame {
	private static final long serialVersionUID = 1L;
	private List<Rutina> rutinas;
    private JTextField txtFiltro;
    private Modelo_de_datos_rutinas modelo_de_datos;

    public Rutinas_guardadas(ArrayList<Rutina> rutinas) {
        this.rutinas = rutinas;

        // Para que se cierre al darle a la X
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Título de la ventana
        setTitle("DEUSTOGYM");

        // Crear ventana principal y establecer layout
        JPanel ventana_principal = new JPanel();
        ventana_principal.setLayout(new GridBagLayout());
        GridBagConstraints constantes = new GridBagConstraints();

        

        // Crear el modelo de datos y la tabla
        modelo_de_datos = new Modelo_de_datos_rutinas(rutinas); 
        JTable tablaRutinas = new JTable(modelo_de_datos);
        JScrollPane scrollPanelRutinas = new JScrollPane(tablaRutinas);
        scrollPanelRutinas.setBorder(new TitledBorder("Rutinas"));

        // Añadimos tabla	
        constantes.gridx = 0;
        constantes.gridy = 0;
        constantes.gridwidth = 2;
        constantes.gridheight = 2;
        constantes.fill = GridBagConstraints.BOTH; // El área de texto debe estirarse en ambos sentidos.
        constantes.weighty = 1.0;
        ventana_principal.add(scrollPanelRutinas, constantes); // También añadido al panel principal
        constantes.weighty = 0.0; // Restauramos al valor por defecto, para no afectar a los siguientes componentes.

        
        // Añadir progres bar de la derecha
        constantes.gridx = 2; 
        constantes.gridy = 0; 
        constantes.gridwidth = 1; 
        constantes.gridheight = GridBagConstraints.REMAINDER; // O utiliza un valor mayor si tienes varias filas
        constantes.weightx = 0.5; 
        constantes.weighty = 0; 
        constantes.fill = GridBagConstraints.BOTH;
        JProgressBar barra = new JProgressBar(JProgressBar.VERTICAL);
        barra.setValue(50);
        barra.setStringPainted(true);
        ventana_principal.add(barra, constantes); // También añadido al panel principal
    
        
        // Añadir ventana_principal al JFrames
        this.setContentPane(ventana_principal); // Establece el panel principal como contenido del JFrame

        // Ajustar tamaño del JFrame
        float escalar = 0.5F;
        int ancho = (int) (Toolkit.getDefaultToolkit().getScreenSize().width * escalar);
        int alto = (int) (Toolkit.getDefaultToolkit().getScreenSize().height * escalar);
        this.setSize(ancho, alto);
        this.setResizable(true);
        setLocationRelativeTo(null);
        setVisible(true);
    }




    

	//FUENTE-EXTERNA
			  //IAG (herramienta: ChatGPT)
			  //SIN CAMBIOS
    // Método para escalar la imagen
    private ImageIcon scaleImage(String path, int width, int height) {
        ImageIcon icon = new ImageIcon(Rutinas_guardadas.class.getResource(path));
        Image img = icon.getImage(); // Obtener la imagen original
        Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH); // Escalar
        return new ImageIcon(scaledImg); // Retornar la imagen escalada
    }
    public List<Rutina> getRutinas() {
        return rutinas;
    }

    public void setRutinas(List<Rutina> rutinas) {
        this.rutinas = rutinas;
    }
}
