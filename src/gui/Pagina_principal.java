package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

import db.GestorBD;
import domain.Rutina;

public class Pagina_principal extends JFrame {
    private static final long serialVersionUID = 1;
	private GestorBD gestor;
    private  String usuario;
    private boolean mostrandoContraseña = false;
    
  
    public Pagina_principal(GestorBD gestor,String usuario) {
    	this.usuario = usuario;
        this.gestor = gestor;
        //CREAR OBJETOS PARA CADA VENTANA
        Planning planing = new Planning(gestor.getTodasRutinas(usuario),gestor,usuario);
        ImageIcon icono = new ImageIcon("resources/images/deustoicon.png");
        this.setIconImage(icono.getImage());
        
        //IAG: ChatGPT
        //SIN CAMBIOS, SOLAMENTE EL JLayeredPane
        
        // Crear un JLayeredPane para manejar capas
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(480, 680));

        // Agregar la imagen de fondo en la capa inferior
        ImageIcon imagen_fondo = new ImageIcon("resources/images/Deustogym.jpg");
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
			new Rutinas_guardadas(gestor,usuario);
        });
        
        layeredPane.add(ventana_principal, Integer.valueOf(1)); // Agregar el panel en la capa 1 (superior)

  
        JMenuBar menuBar = new JMenuBar();

        JMenu menu = new JMenu("Opciones");
        JMenuItem cerrarSesion = new JMenuItem("Cerrar sesión");


        
        
        cerrarSesion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Mostrar el JOptionPane de confirmación
                int confirmacion = JOptionPane.showConfirmDialog(
                    null,
                    "¿Estás seguro de que quieres cerrar sesión?",
                    "Confirmar cierre de sesión",
                    JOptionPane.YES_NO_OPTION
                );

                if (confirmacion == JOptionPane.YES_OPTION) {
                    dispose(); 
            		new Login(gestor);

                    
                }
            }
        });
        
        JMenuItem actualizarperfil = new JMenuItem("Actualizar perfil");
        actualizarperfil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarDialogoActualizarContrasena();
            }
        });       
        menu.add(actualizarperfil);
        menu.addSeparator();
        menu.add(cerrarSesion);
        menuBar.add(menu);
        this.setJMenuBar(menuBar);
        // Configurar la ventana principal
        setContentPane(layeredPane);
        pack();
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("DEUSTOGYM");
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    
    private  void mostrarDialogoActualizarContrasena() {
        // Crear componentes
        JPasswordField campoContrasena = new JPasswordField(15);
        JPasswordField campoRepetirContrasena = new JPasswordField(15);

        // Inicialmente oculta la contraseña
        campoContrasena.setEchoChar('•');
        campoRepetirContrasena.setEchoChar('•');

        JButton botonMostrar = new JButton();
        botonMostrar.setText("Mostrar");
        // Acción del botón de mostrar/ocultar contraseña
        botonMostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleMostrarContraseña(campoContrasena, campoRepetirContrasena, botonMostrar);
            }

			
        });

        // Crear un panel para los campos
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 5, 5));

        panel.add(new JLabel("Nueva contraseña:"));
        panel.add(campoContrasena);
        panel.add(new JLabel("Repetir contraseña:"));
        panel.add(campoRepetirContrasena);
        panel.add(new JLabel()); // Espacio vacío
        panel.add(botonMostrar);

        // Mostrar el cuadro de diálogo
        int resultado = JOptionPane.showConfirmDialog(
            null,
            panel,
            "Actualizar Contraseña",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE
        );

        if (resultado == JOptionPane.OK_OPTION) {
            String contrasena = new String(campoContrasena.getPassword());
            String repetirContrasena = new String(campoRepetirContrasena.getPassword());

            // Validar las contraseñas
            if (contrasena.isEmpty() || repetirContrasena.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Los campos no pueden estar vacíos.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (!contrasena.equals(repetirContrasena)) {
                JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Contraseña actualizada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                gestor.actualizarContraseña(usuario,contrasena);
            }
        }
    }

    private  void toggleMostrarContraseña(JPasswordField campo1, JPasswordField campo2, JButton boton) {
        mostrandoContraseña = !mostrandoContraseña;
        if (mostrandoContraseña) {
            campo1.setEchoChar((char) 0); // Muestra el texto
            campo2.setEchoChar((char) 0);
            boton.setText("Ocultar");
        } else {
            campo1.setEchoChar('•'); 
            campo2.setEchoChar('•');
            boton.setText("Mostrar");

        }
    }

 

}
