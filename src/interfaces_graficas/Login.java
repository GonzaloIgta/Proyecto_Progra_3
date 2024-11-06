package interfaces_graficas;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame implements ActionListener {
    
    private JTextField userField;
    private JPasswordField passField;
    private JButton botonLogin;
    private JLabel statusLabel;

    //Usuario y contraseña predeterminados 
    private final String USUARIO = "Erik";
    private final String CONTRASEÑA = "1234";

    public Login() {
        //Configuración de la ventana
        setTitle("Login DeustoGym");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        //Panel para los campos de entrada
        JPanel panel = new JPanel(new GridLayout(3, 1));
        
        //Campos de entrada
        userField = new JTextField();
        TitledBorder bordeUsuario = BorderFactory.createTitledBorder("Usuario");
        bordeUsuario.setTitleJustification(TitledBorder.LEFT);
        userField.setBorder(bordeUsuario);
        panel.add(userField);

        passField = new JPasswordField();
        TitledBorder bordeContraseña = BorderFactory.createTitledBorder("Contraseña");
        bordeUsuario.setTitleJustification(TitledBorder.LEFT);
        passField.setBorder(bordeContraseña);
        panel.add(passField);

        //Botón de login
        JPanel panelBoton = new JPanel();
        botonLogin = new JButton("Iniciar sesión");
        botonLogin.setFocusable(false);
        botonLogin.addActionListener(this);
        panelBoton.add(botonLogin);
        panel.add(panelBoton);

        //Estado del login
        statusLabel = new JLabel(" ");
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(statusLabel, BorderLayout.SOUTH);
       
        
        this.add(panel, BorderLayout.CENTER);
        
       ImageIcon icono = new ImageIcon(this.getClass().getResource("/resourses/images/deustoicon.png"));
       this.setIconImage(icono.getImage());
       
       
       this.add(panel, BorderLayout.CENTER);
       this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Leer los valores ingresados
        String username = userField.getText();
        String password = new String(passField.getPassword());

        //Verificar si el usuario y la contraseña son correctos
        if (username.equals(USUARIO) && password.equals(CONTRASEÑA)) {
            statusLabel.setText("Login exitoso");
            JOptionPane.showMessageDialog(this, "Bienvenido " + username + "!");
            new Pagina_principal();
        } else {
            statusLabel.setText("Usuario o contraseña incorrectos");
        }
    }

    
}

