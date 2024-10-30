package interfaces_graficas;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
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
import javax.swing.table.DefaultTableModel;

import clases_de_apyo.Modelo_de_datos_rutinas;
import clases_de_apyo.Rutina;


public class Rutinas_guardadas extends JFrame {
	private ArrayList<Rutina> rutinas;
	private JTextField txtFiltro;	
	private DefaultTableModel modelo_de_datos;



	public Rutinas_guardadas(ArrayList<Rutina> rutinas) {
		this.rutinas = rutinas;

		//para que se cierre al darle a la x
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //titulo de la ventana
        setTitle("DEUSTOGYM");

        // Creamos la ventana y definimos distribucion
		JPanel ventana_principal = new JPanel();
		ventana_principal.setLayout(new GridBagLayout());

		
		
		//hay abria que pasarle rutinas
		JScrollPane scrollPanelRutinas = new JScrollPane();
		scrollPanelRutinas.setBorder(new TitledBorder("Rutinas"));
		this.txtFiltro = new JTextField(20);		
		DocumentListener documentListener = new DocumentListener() {

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
			
		};
		this.txtFiltro.getDocument().addDocumentListener(documentListener);
		JPanel panelFiltro = new JPanel();
		panelFiltro.add(new JLabel("Filtro por nombre: "));
		panelFiltro.add(txtFiltro);
		
		
		modelo_de_datos = new Modelo_de_datos_rutinas(rutinas); 
		JTable tablaRutinas = new JTable(modelo_de_datos);
		scrollPanelRutinas.setViewportView(tablaRutinas);
		GridBagConstraints constantes = new GridBagConstraints();
		constantes.gridx = 0; // Columna 0. No necesita estirarse, no ponemos weightx
		constantes.gridy = 0; // Fila 0. Necesita estirarse, hay que poner weighty
		constantes.gridwidth = 2;
		constantes.gridheight = 2;
		constantes.weighty = 1.0; // La fila 0 debe estirarse, le ponemos un 1.0
		this.getContentPane().add (scrollPanelRutinas, constantes);
		constantes.weighty = 0.0; // Restauramos al valor por defecto, para no afectar a los siguientes componentes.
		
		/*
		constantes.gridx = 0; // Columna 0. No necesita estirarse, no ponemos weightx
		constantes.gridy = 0; // Fila 0. Necesita estirarse, hay que poner weighty
		constantes.gridwidth = 2;
		constantes.gridheight = 2;
		constantes.weighty = 1.0; // La fila 0 debe estirarse, le ponemos un 1.0
		 */
		//ventana_principal.add(BorderLayout.NORTH, panelFiltro);		
		
		
        

		
		//FUENTE-EXTERNA
				//URL: (https://www.forosdelweb.com/f45/ajuste-automatico-jframe-853529/)
				//SIN-CAMBIOS
		//Hacer que la pantalla se habra a la mitad del tamaño
		float escalar = 0.5F; // una ventana al 50% del tamaño de la pantalla
		int ancho = (int)(Toolkit.getDefaultToolkit().getScreenSize(). width*escalar);
		int alto = (int)(Toolkit.getDefaultToolkit().getScreenSize(). height*escalar);
		this.setSize(ancho,alto);

        // Permitir que el JFrame sea redimensionable
        this.setResizable(true);


     	
        // Centrar la ventana en la pantalla
     	setLocationRelativeTo(null);

        // Hacemos visible la ventana principal
        setVisible(true);
        
        add(ventana_principal, BorderLayout.CENTER);

        
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
