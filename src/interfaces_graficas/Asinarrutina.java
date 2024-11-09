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
        // FUENTE-EXTERNA
        // IA: (ChatGpt)
        // ADAPTADO (no sabía cómo crear borde)
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Añadir bordes al panel

        // Cambiar los días numéricos a días de la semana
        String[] diasSemana = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
        JComboBox<String> diaSemanaComboBox = new JComboBox<>(diasSemana);
        diaSemanaComboBox.setFont(new Font("Arial", Font.PLAIN, 16));

        JButton agregarButton = new JButton("Añadir Rutina");
        agregarButton.setFont(new Font("Arial", Font.BOLD, 16));
        agregarButton.addActionListener(e -> {
            String diaSeleccionado = (String) diaSemanaComboBox.getSelectedItem();
            // Aquí puedes manejar la asignación de la rutina
            // por ejemplo, asignarRutina(diaSeleccionado, rutina_seleccionada);
            asignarRutina(diaSeleccionado, rutina_seleccionada);
        });

        panel.add(new JLabel("Selecciona el día de la semana:"));
        panel.add(diaSemanaComboBox);
        panel.add(agregarButton);

        add(panel, BorderLayout.CENTER);

        panel.setBackground(Color.BLUE);
        agregarButton.setBackground(new Color(30, 144, 255));
        agregarButton.setForeground(Color.WHITE); 
        setVisible(true);
        setLocationRelativeTo(null); 
    }

    public void asignarRutina(String dia, Rutina rutina_a_asignar) {
        JOptionPane.showMessageDialog(this, "Rutina asignada para el " + dia);
        this.dispose();
    }
}
