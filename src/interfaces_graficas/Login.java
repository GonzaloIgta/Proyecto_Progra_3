package interfaces_graficas;

import clases_de_apyo.Rescalar_imagen;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame implements ActionListener {
    
    private JTextField texto_usuario;
    private JPasswordField texto_contraseña;
    private JButton botonLogin;
    private JLabel statusLabel;
    private JLabel label_de_foto_usuario;
    private JButton botonMostrarContraseña;
    private boolean mostrandoContraseña = false; 
    
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
        // Leer los valores ingresados
        String username = texto_usuario.getText();
        String password = new String(texto_contraseña.getPassword());

        // Verificar si el usuario y la contraseña son correctos
        if (username.equals(USUARIO) && password.equals(CONTRASEÑA)) {
            statusLabel.setText("Login exitoso");
            JOptionPane.showMessageDialog(this, "Bienvenido " + username + "!");
            new Pagina_principal();
            dispose();
            
        } else {
            statusLabel.setText("Usuario o contraseña incorrectos");
        }
    }
}
