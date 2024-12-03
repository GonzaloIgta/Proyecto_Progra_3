package interfaces_graficas;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import javax.swing.*;
import clases_de_apyo.Ejercicio;
import clases_de_apyo.Ejercicio_Natacion;
import clases_de_apyo.Ejercicio_Natacion.EstiloNat;
import clases_de_apyo.Ejercicio_gym;
import clases_de_apyo.Ejercicio_cardio;
import clases_de_apyo.Rutina;
import gestorbd.GestorBD;

public class Pagina_principal extends JFrame {
    private static final long serialVersionUID = 1;
	private GestorBD gestor;
    public static ArrayList<Rutina> rutinas = new ArrayList<>();
    private  String usuario;
    
  
    public Pagina_principal(GestorBD gestor,String usuario) {
    	this.usuario = usuario;
        this.gestor = gestor;
        //CREAR OBJETOS PARA CADA VENTANA
        Rutinas_guardadas rutinas_guardadas = new Rutinas_guardadas(gestor,usuario);
        Planning planing = new Planning(gestor.getTodasRutinas(usuario),gestor,usuario);
        ImageIcon icono = new ImageIcon(this.getClass().getResource("/resourses/images/deustoicon.png"));
        this.setIconImage(icono.getImage());
        
        //FUENTE EXTERNA
        //IAG
        //SIN ADAPTAR, SOLAMENTE EL JLayeredPane
        
        // Crear un JLayeredPane para manejar capas
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(480, 680));

        // Agregar la imagen de fondo en la capa inferior
        ImageIcon imagen_fondo = new ImageIcon(this.getClass().getResource("/resourses/images/Deustogym.jpg"));
        JLabel label_fondo = new JLabel(imagen_fondo);
        label_fondo.setBounds(0, 0, 480, 680);
        layeredPane.add(label_fondo, Integer.valueOf(0)); // Agregar imagen en la capa 0 (fondo)

        // Crear panel para los botones y configurarlo en GridBagLayout
        JPanel ventana_principal = new JPanel(new GridBagLayout());
        ventana_principal.setOpaque(false); // Hacer el panel de botones transparente
        ventana_principal.setBounds(0, 0, 480, 680);
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Botón Rutinas Guardadas
        JButton boton_rutinas_guardadas = new JButton("Rutinas guardadas");
        boton_rutinas_guardadas.setPreferredSize(new Dimension(150, 70));
        boton_rutinas_guardadas.setFocusable(false);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        ventana_principal.add(boton_rutinas_guardadas, gbc);

        // Botón Planning Semanal
        JButton planning = new JButton("Planning semanal");
        planning.setPreferredSize(new Dimension(150, 70));
        planning.setFocusable(false);
        gbc.gridy = 2;
        ventana_principal.add(planning, gbc);

        planning.addActionListener(e -> {
            planing.open();
            dispose();
        });

        boton_rutinas_guardadas.addActionListener(e -> {
            dispose();
            rutinas_guardadas.open();
        });
        
        


        // Agregar el panel de botones en la capa superior
        layeredPane.add(ventana_principal, Integer.valueOf(1)); // Agregar el panel en la capa 1 (superior)

        // Configurar la ventana principal
        setContentPane(layeredPane);
        pack();
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("DEUSTOGYM");
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
