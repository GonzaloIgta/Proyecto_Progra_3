package interfaces_graficas;

import gestorbd.GestorBD;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import clases_de_apyo.Rescalar_imagen;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    private JTextField texto_usuario;
    private JPasswordField texto_contraseña;
    private JButton botonLogin;
    private JTextField nuevoUsuario;
    private JPasswordField nuevaContraseña;
    private JButton botonRegistrar;
    private JLabel statusLabel;
    private JLabel label_de_foto_usuario;
    private JButton botonMostrarContraseña;
    private boolean mostrandoContraseña = false;

    private GestorBD gestorBD;

    public Login(GestorBD gestor) {
        // Inicialización del gestor de base de datos
        gestorBD = gestor;
        gestorBD.crearTablaUsuarios(); // Crea la tabla de usuarios si no existe

        // Configuración de la ventana
        setTitle("Login DeustoGym");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Imagen de usuario en la parte superior
        label_de_foto_usuario = new JLabel();
        label_de_foto_usuario.setHorizontalAlignment(SwingConstants.CENTER);
        new Rescalar_imagen().setScaledImage(label_de_foto_usuario, "/resourses/images/icono_usuario.png", 120, 120);
        add(label_de_foto_usuario, BorderLayout.NORTH);

        // Panel para los campos de entrada
        JPanel panel = new JPanel(new GridLayout(3, 1));

        // Campos de entrada
        texto_usuario = new JTextField();
        TitledBorder bordeUsuario = BorderFactory.createTitledBorder("Usuario");
        texto_usuario.setBorder(bordeUsuario);
        panel.add(texto_usuario);

        JPanel panel_contraseña = new JPanel(new BorderLayout());
        texto_contraseña = new JPasswordField();
        TitledBorder bordeContraseña = BorderFactory.createTitledBorder("Contraseña");
        texto_contraseña.setBorder(bordeContraseña);

        botonMostrarContraseña = new JButton("Mostrar");
        botonMostrarContraseña.addActionListener(e -> toggleMostrarContraseña());
        panel_contraseña.add(texto_contraseña, BorderLayout.CENTER);
        panel_contraseña.add(botonMostrarContraseña, BorderLayout.EAST);

        panel.add(panel_contraseña);

        // Botón de login
        JPanel panelBoton = new JPanel();
        botonLogin = new JButton("Iniciar sesión");
        botonLogin.addActionListener(this);
        panelBoton.add(botonLogin);
        panel.add(panelBoton);

        // Botón de registro
        JPanel panelBoton2 = new JPanel();
        botonRegistrar = new JButton("Registrarse");
        botonRegistrar.addActionListener(e -> openRegisterWindow());
        panelBoton2.add(botonRegistrar);
        panel.add(panelBoton2);

        add(panel, BorderLayout.CENTER);

        // Estado del login
        statusLabel = new JLabel(" ");
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(statusLabel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void toggleMostrarContraseña() {
        mostrandoContraseña = !mostrandoContraseña;
        if (mostrandoContraseña) {
            texto_contraseña.setEchoChar((char) 0); // Muestra el texto
        } else {
            texto_contraseña.setEchoChar('•'); // Oculta el texto
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String usuario = texto_usuario.getText();
        String contraseña = new String(texto_contraseña.getPassword());

        if (gestorBD.verificarUsuario(usuario, contraseña)) {
            JOptionPane.showMessageDialog(this, "Login exitoso. Bienvenido " + usuario + "!");
            new Pagina_principal(gestorBD);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos");
        }
    }

    private void openRegisterWindow() {
        JFrame frameRegistrar = new JFrame("Registrar DeustoGym");
        frameRegistrar.setSize(400, 300);
        frameRegistrar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameRegistrar.setLocationRelativeTo(null);
        frameRegistrar.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(3, 1));

        nuevoUsuario = new JTextField();
        TitledBorder bordeUsuario = BorderFactory.createTitledBorder("Nuevo Usuario");
        nuevoUsuario.setBorder(bordeUsuario);
        panel.add(nuevoUsuario);

        JPanel panel_contraseña = new JPanel(new BorderLayout());
        nuevaContraseña = new JPasswordField();
        TitledBorder bordeContraseña = BorderFactory.createTitledBorder("Nueva Contraseña");
        nuevaContraseña.setBorder(bordeContraseña);
        panel_contraseña.add(nuevaContraseña, BorderLayout.CENTER);

        panel.add(panel_contraseña);

        JButton botonCrearCuenta = new JButton("Crear cuenta");
        botonCrearCuenta.addActionListener(e -> {
            String usuario = nuevoUsuario.getText();
            String contraseña = new String(nuevaContraseña.getPassword());

            if (usuario.isEmpty() || contraseña.isEmpty()) {
                JOptionPane.showMessageDialog(frameRegistrar, "Por favor, complete todos los campos.");
            } else if (gestorBD.insertarUsuario(usuario, contraseña)) {
                JOptionPane.showMessageDialog(frameRegistrar, "Registro exitoso. Ya puede iniciar sesión.");
                frameRegistrar.dispose();
            } else {
                JOptionPane.showMessageDialog(frameRegistrar, "El usuario ya existe. Elija otro nombre.");
            }
        });

        panel.add(botonCrearCuenta);

        frameRegistrar.add(panel, BorderLayout.CENTER);
        frameRegistrar.setVisible(true);
    }

    
}

