package gui;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import db.GestorBD;
import domain.Rescalar_imagen;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Login extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    private JTextField texto_usuario;
    private JPasswordField texto_contraseña;
    private JButton botonLogin;
    private JTextField nuevoUsuario;
    private JPasswordField nuevaContraseña;
    private JButton botonRegistrar;
    private JLabel statusLabel,dondehora;
    private JLabel label_de_foto_usuario;
    private JButton botonMostrarContraseña;
    private boolean mostrandoContraseña = false;

    private GestorBD gestorBD;

    public Login(GestorBD gestor) {
        // Inicialización del gestor de base de datos
        gestorBD = gestor;

        // Configuración de la ventana
        setTitle("Login DeustoGym");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Imagen de usuario en la parte superior
        JPanel panel_arriba = new JPanel(new BorderLayout());
        label_de_foto_usuario = new JLabel();
        dondehora = new JLabel();
        dondehora.setHorizontalAlignment(SwingConstants.RIGHT);
        label_de_foto_usuario.setHorizontalAlignment(SwingConstants.CENTER);
        new Rescalar_imagen().setScaledImage(label_de_foto_usuario, "resources/images/icono_usuario.png", 120, 120);
        
        panel_arriba.add(label_de_foto_usuario, BorderLayout.CENTER);
        panel_arriba.add(dondehora, BorderLayout.NORTH);

        this.add(panel_arriba, BorderLayout.NORTH);

        //Iniciamos el thread para mostrar hora y fecha
	    iniciarHiloHora();

        // Panel para los campos de entrada
        JPanel panel = new JPanel(new GridLayout(3, 1));

        // Campos de entrada
        texto_usuario = new JTextField();
        TitledBorder bordeUsuario = BorderFactory.createTitledBorder("Usuario");
        bordeUsuario.setTitleJustification(TitledBorder.LEFT);
        texto_usuario.setBorder(bordeUsuario);
        panel.add(texto_usuario);

        JPanel panel_contraseña = new JPanel(new BorderLayout());
        texto_contraseña = new JPasswordField();
        TitledBorder bordeContraseña = BorderFactory.createTitledBorder("Contraseña");
        bordeContraseña.setTitleJustification(TitledBorder.LEFT);
        texto_contraseña.setBorder(bordeContraseña);

        botonMostrarContraseña = new JButton();
        botonMostrarContraseña.addActionListener(e -> toggleMostrarContraseña());
        botonMostrarContraseña.setFocusable(false);
        setIconoOjo(botonMostrarContraseña, mostrandoContraseña);
       
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

        // Botón de registro
        JPanel panelBoton2 = new JPanel();
        botonRegistrar = new JButton("Registrarse");
        botonRegistrar.setFocusable(false);
        botonRegistrar.addActionListener(e -> openRegisterWindow());
        panelBoton2.add(botonRegistrar);
        panel.add(panelBoton2);
	 
        this.add(panel, BorderLayout.CENTER);
        
		ImageIcon icono = new ImageIcon("resources/images/deustoicon.png");
		this.setIconImage(icono.getImage());
        
	    

        // Estado del login
        statusLabel = new JLabel(" ");
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(statusLabel, BorderLayout.SOUTH);

        setVisible(true);
    }
    
    //IAG: ChatGPT
    //ADAPTADO: Usado para mostrar la contraseña tanto al iniciar sesion como al registrarse
    private void toggleMostrarContraseña() {
        mostrandoContraseña = !mostrandoContraseña;
        if (mostrandoContraseña) {
            texto_contraseña.setEchoChar((char) 0); // Muestra el texto
        } else {
            texto_contraseña.setEchoChar('•'); // Oculta el texto
        }		
        setIconoOjo(botonMostrarContraseña, mostrandoContraseña); 
    }
    private void setIconoOjo(JButton boton, boolean abierto) {
        String iconPath = abierto ? "resources/images/ojo_abierto.png" : "resources/images/ojo_cerrado.png";
        new Rescalar_imagen().setScaledImage(boton, iconPath, 20, 20); 
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String usuario = texto_usuario.getText();
        String contraseña = new String(texto_contraseña.getPassword());

        if (gestorBD.verificarUsuario(usuario, contraseña)) {
            JOptionPane.showMessageDialog(this, "Login exitoso. Bienvenido " + usuario + "!");
            new Pagina_principal(gestorBD,usuario);
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
        
        label_de_foto_usuario = new JLabel();
        label_de_foto_usuario.setHorizontalAlignment(SwingConstants.CENTER); // Centra la imagen en el panel
        new Rescalar_imagen().setScaledImage(label_de_foto_usuario, "resourses/images/icono_usuario.png", 100, 100); // Tamaño 100x100
        frameRegistrar.add(label_de_foto_usuario, BorderLayout.NORTH);


        JPanel panel = new JPanel(new GridLayout(3, 1));

        nuevoUsuario = new JTextField();
        TitledBorder bordeUsuario = BorderFactory.createTitledBorder("Usuario");
        bordeUsuario.setTitleJustification(TitledBorder.LEFT);
        nuevoUsuario.setBorder(bordeUsuario);
        panel.add(nuevoUsuario);

        JPanel panel_contraseña = new JPanel(new BorderLayout());
        texto_contraseña = new JPasswordField();
        TitledBorder bordeContraseña = BorderFactory.createTitledBorder("Contraseña");
        bordeContraseña.setTitleJustification(TitledBorder.LEFT);
        texto_contraseña.setBorder(bordeContraseña);
        
        botonMostrarContraseña = new JButton();
        botonMostrarContraseña.addActionListener(e -> toggleMostrarContraseña());
        botonMostrarContraseña.setFocusable(false);
        
        panel_contraseña.add(texto_contraseña, BorderLayout.CENTER);
        panel_contraseña.add(botonMostrarContraseña, BorderLayout.EAST);
        //NO ENTIENDO POR QUE PERO LLAMANDO A LA FUNCION DOS VECES SE VE EL ICONO DEL OJO BIEN
        //SI NO LO PONES NO SE VE NADA HASTA QUE CLICKAS
        toggleMostrarContraseña();
        toggleMostrarContraseña();
        

        panel.add(panel_contraseña);

        JButton botonCrearCuenta = new JButton("Crear cuenta");
        botonCrearCuenta.addActionListener(e -> {
            String usuario = nuevoUsuario.getText();
            String contraseña = new String(texto_contraseña.getPassword());

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
    
    private void iniciarHiloHora() {
	    
		Thread hiloHora = new Thread (() -> {
			while(true) {
				try {
					LocalDateTime ahora = LocalDateTime.now();
					DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm:ss - dd/MM/yyyy");
					 String horaFechaFormateada = ahora.format(formato);
					 
					 SwingUtilities.invokeLater(() -> dondehora.setText(horaFechaFormateada));
					 
					// Actualiza cada segundo
					 Thread.sleep(1000); 
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		hiloHora.start();
	}
    
    

    
}

