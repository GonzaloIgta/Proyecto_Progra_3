package interfaces_graficas;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import javax.swing.*;
import clases_de_apyo.Ejercicio;
import clases_de_apyo.Ejercicio_Natacion;
import clases_de_apyo.Musculo_trabajado;
import clases_de_apyo.Ejercicio_gym;
import clases_de_apyo.Ejercicio_runing;
import clases_de_apyo.Rutina;
import clases_de_apyo.estilo_natacion;

public class Pagina_principal extends JFrame {
    private static final long serialVersionUID = 1;
    
    public static ArrayList<Rutina> rutinas = new ArrayList<>();
    
    static{
		//FUENTE EXTERNA
        //IAG
        //SIN ADAPTAR PARA CREAR RUTINAS DE EJEMPLO
		
		
    	// Rutina 1: Musculación - Ejercicios de gimnasio
    	ArrayList<Ejercicio> ejercicios1 = new ArrayList<>();
    	ejercicios1.add(new Ejercicio_gym("Press de Banca", "/path/to/photo", Musculo_trabajado.PECHO, 12, 4, 80));
    	ejercicios1.add(new Ejercicio_gym("Curl de Bíceps", "/path/to/photo", Musculo_trabajado.BICEPS, 12, 3, 20));
    	ejercicios1.add(new Ejercicio_gym("Sentadilla", "/path/to/photo", Musculo_trabajado.PIERNA, 10, 4, 60));
    	ejercicios1.add(new Ejercicio_gym("Press Militar", "/path/to/photo", Musculo_trabajado.HOMBRO, 10, 4, 50));
    	ejercicios1.add(new Ejercicio_gym("Extensiones de Tríceps", "/path/to/photo", Musculo_trabajado.TRICEPS, 12, 3, 25));
    	rutinas.add(new Rutina("Rutina Musculación 1", Rutina.Objetivo_de_la_sesion.MUSCULACION, ejercicios1));

    	// Rutina 2: Cardio - Natación
    	ArrayList<Ejercicio> ejercicios2 = new ArrayList<>();
    	ejercicios2.add(new Ejercicio_Natacion("Nadar Estilo Libre", "/path/to/photo", estilo_natacion.NORMAL, 1.5f, 2.5f));
    	ejercicios2.add(new Ejercicio_Natacion("Nadar Espalda", "/path/to/photo", estilo_natacion.ESPALDA, 1.0f, 3.0f));
    	ejercicios2.add(new Ejercicio_Natacion("Nadar Mariposa", "/path/to/photo", estilo_natacion.MARIPOSA, 1.2f, 2.8f));
    	rutinas.add(new Rutina("Rutina Cardio 1", Rutina.Objetivo_de_la_sesion.CARDIOVASCULAR, ejercicios2));

    	// Rutina 3: Pérdida de peso - Running
    	ArrayList<Ejercicio> ejercicios3 = new ArrayList<>();
    	ejercicios3.add(new Ejercicio_runing("Correr en cinta", "/path/to/photo", 5.0f, 6.0f));
    	ejercicios3.add(new Ejercicio_runing("Correr al aire libre", "/path/to/photo", 7.0f, 7.5f));
    	ejercicios3.add(new Ejercicio_runing("Running en pista", "/path/to/photo", 3.0f, 8.0f));
    	rutinas.add(new Rutina("Rutina Pérdida de Peso 1", Rutina.Objetivo_de_la_sesion.PERDIDA_DE_PESO, ejercicios3));

    	// Rutina 4: Musculación + Cardio
    	ArrayList<Ejercicio> ejercicios4 = new ArrayList<>();
    	ejercicios4.add(new Ejercicio_gym("Sentadillas", "/path/to/photo", Musculo_trabajado.PIERNA, 15, 4, 60));
    	ejercicios4.add(new Ejercicio_Natacion("Nadar Mariposa", "/path/to/photo", estilo_natacion.MARIPOSA, 1.0f, 2.0f));
    	ejercicios4.add(new Ejercicio_gym("Remo con barra", "/path/to/photo", Musculo_trabajado.ESPALDA, 10, 4, 70));
    	rutinas.add(new Rutina("Rutina Combinada 1", Rutina.Objetivo_de_la_sesion.MUSCULACION, ejercicios4));

    	// Rutina 5: Cardio + Running
    	ArrayList<Ejercicio> ejercicios5 = new ArrayList<>();
    	ejercicios5.add(new Ejercicio_runing("Correr al aire libre", "/path/to/photo", 7.0f, 7.5f));
    	ejercicios5.add(new Ejercicio_Natacion("Nadar Estilo Libre", "/path/to/photo", estilo_natacion.NORMAL, 2.0f, 2.3f));
    	ejercicios5.add(new Ejercicio_runing("Running en pista", "/path/to/photo", 4.0f, 6.0f));
    	rutinas.add(new Rutina("Rutina Cardio Running 1", Rutina.Objetivo_de_la_sesion.CARDIOVASCULAR, ejercicios5));

    	// Rutina 6: Musculación + Pérdida de peso
    	ArrayList<Ejercicio> ejercicios6 = new ArrayList<>();
    	ejercicios6.add(new Ejercicio_gym("Press Militar", "/path/to/photo", Musculo_trabajado.HOMBRO, 10, 4, 50));
    	ejercicios6.add(new Ejercicio_runing("Trote ligero", "/path/to/photo", 3.0f, 8.0f));
    	ejercicios6.add(new Ejercicio_gym("Remo con barra", "/path/to/photo", Musculo_trabajado.ESPALDA, 12, 3, 65));
    	rutinas.add(new Rutina("Rutina Musculación Cardio 1", Rutina.Objetivo_de_la_sesion.MUSCULACION, ejercicios6));

    	// Rutina 7: Pérdida de peso + Cardio
    	ArrayList<Ejercicio> ejercicios7 = new ArrayList<>();
    	ejercicios7.add(new Ejercicio_runing("Correr en montaña", "/path/to/photo", 6.0f, 7.0f));
    	ejercicios7.add(new Ejercicio_Natacion("Nadar Espalda", "/path/to/photo", estilo_natacion.ESPALDA, 1.2f, 2.8f));
    	ejercicios7.add(new Ejercicio_runing("Correr en pista", "/path/to/photo", 5.0f, 7.0f));
    	rutinas.add(new Rutina("Rutina Cardio Running 2", Rutina.Objetivo_de_la_sesion.PERDIDA_DE_PESO, ejercicios7));

    	// Rutina 8: Cardio + Running + Musculación
    	ArrayList<Ejercicio> ejercicios8 = new ArrayList<>();
    	ejercicios8.add(new Ejercicio_runing("Correr al aire libre", "/path/to/photo", 4.0f, 6.0f));
    	ejercicios8.add(new Ejercicio_gym("Curl de Bíceps", "/path/to/photo", Musculo_trabajado.BICEPS, 12, 3, 18));
    	ejercicios8.add(new Ejercicio_gym("Sentadilla", "/path/to/photo", Musculo_trabajado.PIERNA, 15, 4, 65));
    	rutinas.add(new Rutina("Rutina Combinada Running Musculación", Rutina.Objetivo_de_la_sesion.CARDIOVASCULAR, ejercicios8));

    	// Rutina 9: Musculación + Running
    	ArrayList<Ejercicio> ejercicios9 = new ArrayList<>();
    	ejercicios9.add(new Ejercicio_gym("Remo con Barra", "/path/to/photo", Musculo_trabajado.ESPALDA, 10, 4, 70));
    	ejercicios9.add(new Ejercicio_runing("Running en pista", "/path/to/photo", 5.0f, 6.5f));
    	ejercicios9.add(new Ejercicio_gym("Press de Banca", "/path/to/photo", Musculo_trabajado.PECHO, 12, 4, 80));
    	rutinas.add(new Rutina("Rutina Musculación Running", Rutina.Objetivo_de_la_sesion.MUSCULACION, ejercicios9));

    	// Rutina 10: Cardio + Musculación + Pérdida de peso
    	ArrayList<Ejercicio> ejercicios10 = new ArrayList<>();
    	ejercicios10.add(new Ejercicio_Natacion("Nadar Mariposa", "/path/to/photo", estilo_natacion.MARIPOSA, 1.5f, 2.5f));
    	ejercicios10.add(new Ejercicio_gym("Sentadillas", "/path/to/photo", Musculo_trabajado.PIERNA, 15, 4, 65));
    	ejercicios10.add(new Ejercicio_runing("Correr en montaña", "/path/to/photo", 6.0f, 8.0f));
    	ejercicios10.add(new Ejercicio_gym("Press Militar", "/path/to/photo", Musculo_trabajado.HOMBRO, 12, 3, 50));
    	rutinas.add(new Rutina("Rutina Combinada Completa", Rutina.Objetivo_de_la_sesion.PERDIDA_DE_PESO, ejercicios10));

    }
    public Pagina_principal() {

        
        //CREAR OBJETOS PARA CADA VENTANA
        Rutinas_guardadas rutinas_guardadas = new Rutinas_guardadas(Pagina_principal.rutinas);
        Planning planing = new Planning(Pagina_principal.rutinas);
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
