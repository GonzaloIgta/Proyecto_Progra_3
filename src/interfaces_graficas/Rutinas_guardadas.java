package interfaces_graficas;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import clases_de_apyo.Modelo_de_datos_rutinas;
import clases_de_apyo.Rutina;

public class Rutinas_guardadas extends JFrame {
    private ArrayList<Rutina> rutinas;
    private JTextField txtFiltro;
    private Modelo_de_datos_rutinas modelo_de_datos;

    public Rutinas_guardadas(ArrayList<Rutina> rutinas) {
        this.rutinas = rutinas;

        // Para que se cierre al darle a la X
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Título de la ventana
        setTitle("DEUSTOGYM");

        // Crear ventana principal y establecer layout
        JPanel ventana_principal = new JPanel();
        ventana_principal.setLayout(new GridBagLayout());
        GridBagConstraints constantes = new GridBagConstraints();

        // FILTRO
        txtFiltro = new JTextField(20);
        txtFiltro.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                System.out.println("insertUpdate");
                FiltrarRutinas(txtFiltro.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                System.out.println("removeUpdate");
                FiltrarRutinas(txtFiltro.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                System.out.println("changedUpdate");
                FiltrarRutinas(txtFiltro.getText());
            }
        });

        JPanel panelFiltro = new JPanel();
        panelFiltro.add(new JLabel("Filtro por nombre: "));
        panelFiltro.add(txtFiltro);

        constantes.gridx = 1;
        constantes.gridy = 2;
        constantes.gridwidth = 1;
        constantes.gridheight = 1;
        constantes.weightx = 1.0;
        constantes.fill = GridBagConstraints.HORIZONTAL; //se estira solo horizontal
        ventana_principal.add(panelFiltro, constantes); // Añadido al panel principal

        // Crear el modelo de datos y la tabla
        modelo_de_datos = new Modelo_de_datos_rutinas(rutinas); 
        JTable tablaRutinas = new JTable(modelo_de_datos);
        JScrollPane scrollPanelRutinas = new JScrollPane(tablaRutinas);
        scrollPanelRutinas.setBorder(new TitledBorder("Rutinas"));

        // Añadimos tabla	
        constantes.gridx = 0;
        constantes.gridy = 0;
        constantes.gridwidth = 2;
        constantes.gridheight = 2;
        constantes.fill = GridBagConstraints.BOTH; // El área de texto debe estirarse en ambos sentidos.
        constantes.weighty = 1.0;
        ventana_principal.add(scrollPanelRutinas, constantes); // También añadido al panel principal
        constantes.weighty = 0.0; // Restauramos al valor por defecto, para no afectar a los siguientes componentes.

        // Añadir ventana_principal al JFrame
        this.setContentPane(ventana_principal); // Establece el panel principal como contenido del JFrame

        // Ajustar tamaño del JFrame
        float escalar = 0.5F;
        int ancho = (int) (Toolkit.getDefaultToolkit().getScreenSize().width * escalar);
        int alto = (int) (Toolkit.getDefaultToolkit().getScreenSize().height * escalar);
        this.setSize(ancho, alto);
        this.setResizable(true);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void FiltrarRutinas(String titulo) {
        System.out.print("falta implementar");
    }

    public ArrayList<Rutina> getRutinas() {
        return rutinas;
    }

    public void setRutinas(ArrayList<Rutina> rutinas) {
        this.rutinas = rutinas;
    }
}
