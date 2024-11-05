package interfaces_graficas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import clases_de_apyo.Rutina;

public class Asinarrutina extends JFrame {
	Rutina rutina_seleccionada;
    private static final long serialVersionUID = 1L;

    public Asinarrutina(Rutinas_guardadas donde_se_llama) {
    	rutina_seleccionada = donde_se_llama.getRutinaSeleccionada();
        setTitle("Asignar Rutina");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        ImageIcon icono = new ImageIcon(this.getClass().getResource("/resourses/images/deustoicon.png"));
        setIconImage(icono.getImage());
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 10, 10)); 
        //FUENTE-EXTERNA
		//IA: (ChatGpt)
		//ADAPTADO (no sabia como crear borde)
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Añadir bordes al panel

        String[] dias = new String[31];
        for (int i = 0; i < 31; i++) {
            dias[i] = String.valueOf(i + 1);
        }
        JComboBox<String> diaComboBox = new JComboBox<>(dias);
        diaComboBox.setFont(new Font("Arial", Font.PLAIN, 16));

        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", 
                          "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        JComboBox<String> mesComboBox = new JComboBox<>(meses);
        mesComboBox.setFont(new Font("Arial", Font.PLAIN, 16));

        // Crear combo box para años
        String[] años = new String[51];
        for (int i = 0; i < 51; i++) {
            años[i] = String.valueOf(2024 + i); 
        }
        JComboBox<String> añoComboBox = new JComboBox<>(años);
        añoComboBox.setFont(new Font("Arial", Font.PLAIN, 16));

        // Crear botón para añadir rutina
        JButton agregarButton = new JButton("Añadir Rutina");
        agregarButton.setFont(new Font("Arial", Font.BOLD, 16));
        agregarButton.addActionListener(e -> {
            String diaSeleccionado = (String) diaComboBox.getSelectedItem();
            String mesSeleccionado = (String) mesComboBox.getSelectedItem();
            String añoSeleccionado = (String) añoComboBox.getSelectedItem();
            asignarRutina(diaSeleccionado, mesSeleccionado, añoSeleccionado, rutina_seleccionada);
        });

        panel.add(new JLabel("Selecciona el día:"));
        panel.add(diaComboBox);
        panel.add(new JLabel("Selecciona el mes:"));
        panel.add(mesComboBox);
        panel.add(new JLabel("Selecciona el año:"));
        panel.add(añoComboBox);
        panel.add(agregarButton);

        add(panel, BorderLayout.CENTER);

        panel.setBackground(Color.BLUE);
        agregarButton.setBackground(new Color(30, 144, 255));
        agregarButton.setForeground(Color.WHITE); 
        setVisible(true);
        setLocationRelativeTo(null); 
    }


    private void asignarRutina(String dia, String mes, String año, Rutina rutina_a_asignar) {
        JOptionPane.showMessageDialog(this, "FALTA QUE IMPLEMENTEIS EL COMO RECIBIRLA EN EL PLANING");
    }


}
