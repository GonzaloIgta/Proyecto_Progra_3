package interfaces_graficas;

import clases_de_apyo.Rescalar_imagen;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

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
    private HashMap<String, String> baseDeDatos = new HashMap<>();
    
    // Usuario y contraseña predeterminados
    private final String USUARIO = "";
    private final String CONTRASEÑA = "";

    public Login() {
        // Configuración de la ventana
        setTitle("Login DeustoGym");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Imagen de usuario en la parte superior
        label_de_foto_usuario = new JLabel();
        label_de_foto_usuario.setHorizontalAlignment(SwingConstants.CENTER); // Centra la imagen en el panel
        new Rescalar_imagen().setScaledImage(label_de_foto_usuario, "/resourses/images/icono_usuario.png", 100, 100); // Tamaño 100x100
        add(label_de_foto_usuario, BorderLayout.NORTH);

        // Panel para los campos de entrada
        JPanel panel = new JPanel(new GridLayout(3, 1));
        
        // Campos de entrada
        texto_usuario = new JTextField();
        TitledBorder bordeUsuario = BorderFactory.createTitledBorder("Usuario");
        bordeUsuario.setTitleJustification(TitledBorder.LEFT);
        texto_usuario.setBorder(bordeUsuario);
        panel.add(texto_usuario);

        // Panel para la contraseña y el botón de mostrar contraseña
        JPanel panel_contraseña = new JPanel(new BorderLayout());
        texto_contraseña = new JPasswordField();
        TitledBorder bordeContraseña = BorderFactory.createTitledBorder("Contraseña");
        bordeContraseña.setTitleJustification(TitledBorder.LEFT);
        texto_contraseña.setBorder(bordeContraseña);

        // Botón para mostrar/ocultar contraseña
        botonMostrarContraseña = new JButton();
        botonMostrarContraseña.setFocusable(false);
        setIconoOjo(botonMostrarContraseña, mostrandoContraseña); // Establece el ícono inicial
        botonMostrarContraseña.addActionListener(e -> toggleMostrarContraseña());
        
        panel_contraseña.add(texto_contraseña, BorderLayout.CENTER);
        panel_contraseña.add(botonMostrarContraseña, BorderLayout.EAST);
        
        panel.add(panel_contraseña);

        // Botón de login
        JPanel panelBoton = new JPanel();
        botonLogin = new JButton("Iniciar sesión");
        botonLogin.setFocusable(false);
        botonLogin.addActionListener(this);
        panelBoton.add(botonLogin);
        panel.add(panelBoton);
        
        getRootPane().setDefaultButton(botonLogin);
        
        JPanel panelBoton2 = new JPanel();
        botonRegistrar = new JButton("Registrarse");
        botonRegistrar.setFocusable(false);
        botonRegistrar.addActionListener(e -> openRegisterWindow());
        panelBoton2.add(botonRegistrar);
        panel.add(panelBoton2);

        // Estado del login
        statusLabel = new JLabel(" ");
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(statusLabel, BorderLayout.SOUTH);
       
        this.add(panel, BorderLayout.CENTER);
        
        ImageIcon icono = new ImageIcon(this.getClass().getResource("/resourses/images/deustoicon.png"));
        this.setIconImage(icono.getImage());
        
        this.setVisible(true);
    }

	//FUENTE-EXTERNA
	//IA: (Chatgpt)
	//ADAPTADO (Usado para implementar boton de mostar contraseña)
    private void toggleMostrarContraseña() {
        mostrandoContraseña = !mostrandoContraseña; //Cambia de true a false y vicebersa
        if (mostrandoContraseña) {
            texto_contraseña.setEchoChar((char) 0); 
        } else {
            texto_contraseña.setEchoChar('•'); 
        }        setIconoOjo(botonMostrarContraseña, mostrandoContraseña); 
    }

    private void setIconoOjo(JButton boton, boolean abierto) {
        String iconPath = abierto ? "/resourses/images/ojo_abierto.png" : "/resourses/images/ojo_cerrado.png";
        new Rescalar_imagen().setScaledImage(boton, iconPath, 20, 20); 
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	//Leer los valores ingresados
    	String usuario = texto_usuario.getText();
        String contraseña = new String(texto_contraseña.getPassword());

        if (baseDeDatos.containsKey(usuario) && baseDeDatos.get(usuario).equals(contraseña)) {
            statusLabel.setText("Login exitoso");
            JOptionPane.showMessageDialog(this, "Bienvenido " + usuario + "!");
            new Pagina_principal();
            dispose();
        } else {
            statusLabel.setText("Usuario o contraseña incorrectos");
        }
    }
    
    //FUENTE-EXTERNA
  	//IA: (Chatgpt)
    private void openRegisterWindow() {
        
        JFrame frameRegistrar = new JFrame("Registrar DeustoGym");
        frameRegistrar.setSize(400, 300);
        frameRegistrar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameRegistrar.setLocationRelativeTo(null);
        frameRegistrar.setLayout(new BorderLayout());
        
        label_de_foto_usuario = new JLabel();
        label_de_foto_usuario.setHorizontalAlignment(SwingConstants.CENTER); // Centra la imagen en el panel
        new Rescalar_imagen().setScaledImage(label_de_foto_usuario, "/resourses/images/icono_usuario.png", 100, 100); // Tamaño 100x100
        frameRegistrar.add(label_de_foto_usuario, BorderLayout.NORTH);

        // Panel para los campos de entrada
        JPanel panel = new JPanel(new GridLayout(3, 1));
        
        // Campos de entrada
        nuevoUsuario = new JTextField();
        TitledBorder bordeUsuario = BorderFactory.createTitledBorder("Usuario");
        bordeUsuario.setTitleJustification(TitledBorder.LEFT);
        nuevoUsuario.setBorder(bordeUsuario);
        panel.add(nuevoUsuario);

        // Panel para la contraseña y el botón de mostrar contraseña
        JPanel panel_contraseña = new JPanel(new BorderLayout());
        nuevaContraseña = new JPasswordField();
        TitledBorder bordeContraseña = BorderFactory.createTitledBorder("Contraseña");
        bordeContraseña.setTitleJustification(TitledBorder.LEFT);
        nuevaContraseña.setBorder(bordeContraseña);
        
        botonMostrarContraseña = new JButton();
        botonMostrarContraseña.setFocusable(false);
        setIconoOjo(botonMostrarContraseña, mostrandoContraseña); // Establece el ícono inicial
        botonMostrarContraseña.addActionListener(e -> toggleMostrarContraseña());
        
        panel_contraseña.add(nuevaContraseña, BorderLayout.CENTER);
        panel_contraseña.add(botonMostrarContraseña, BorderLayout.EAST);
        
        panel.add(panel_contraseña);

        /*JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Nuevo Usuario:"));
        JTextField nuevoUsuario = new JTextField();
        panel.add(nuevoUsuario);

        panel.add(new JLabel("Nueva Contraseña:"));
        JPasswordField nuevaContraseña = new JPasswordField();
        panel.add(nuevaContraseña);*/

        JButton botonCrearCuenta = new JButton("Crear cuenta");
        botonCrearCuenta.addActionListener(e -> {
            String usuario = nuevoUsuario.getText();
            String contraseña = new String(nuevaContraseña.getPassword());

            if (usuario.isEmpty() || contraseña.isEmpty()) {
                JOptionPane.showMessageDialog(frameRegistrar, "Por favor, complete todos los campos.");
            } else if (baseDeDatos.containsKey(usuario)) {
                JOptionPane.showMessageDialog(frameRegistrar, "El usuario ya existe. Elija otro nombre.");
            } else {
                //Guardamos el nuevo usuario y contraseña
                baseDeDatos.put(usuario, contraseña);
                JOptionPane.showMessageDialog(frameRegistrar, "Registro exitoso. Ya puede iniciar sesión.");
                frameRegistrar.dispose();
            }
        });
        panel.add(botonCrearCuenta);

        frameRegistrar.add(panel, BorderLayout.CENTER);
        frameRegistrar.setVisible(true);
    }
}
