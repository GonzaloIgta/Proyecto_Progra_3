package interfaces_graficas;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;

import clases_de_apyo.Ejercicio;
import clases_de_apyo.Ejercicio_Natacion;
import clases_de_apyo.Musculo_trabajado;
import clases_de_apyo.Ejercicio_gym;
import clases_de_apyo.Ejercicio_runing;
import clases_de_apyo.Rutina;
import clases_de_apyo.Rutina.Objetivo_de_la_sesion;
import clases_de_apyo.estilo_natacion;

public class Pagina_principal extends JFrame {
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

        
        //CREAR OBJETOS PARA CADA VENTANA
        Rutinas_guardadas rutinas_guardadas = new Rutinas_guardadas(rutinas);
        Nueva_Rutina nueva_rutina = new Nueva_Rutina();
        Planning planing = new Planning(rutinas);

        
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
            rutinas_guardadas.open();
            dispose();
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
