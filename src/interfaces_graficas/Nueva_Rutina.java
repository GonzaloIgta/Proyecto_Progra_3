package interfaces_graficas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;


public class Nueva_Rutina extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private JTable tablaRutina;
	private DefaultTableModel modeloDatosTablaRutina;


	public Nueva_Rutina() {

		//para que se cierre al darle a la x
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //titulo de la ventana
        setTitle("Nueva Rutina");
        
        // Creamos la ventana y definimos distribucion
		JPanel ventanaPrincipal_nuevaRutina = new JPanel(new BorderLayout());
		JScrollPane scrollPane = new JScrollPane(this.tablaRutina);
	    JPanel ventana_central_MuestraRutinas = new JPanel(new BorderLayout());
		initTableRutina();
		
		
		
		
		
		
		

		//FUENTE-EXTERNA
		//URL: (https://chuidiang.org/index.php?title=Uso_de_Layouts)
		//ADAPTADO (hemos analizado el codigo para entender como funcionaba el layout y modificado para añadirle los elementos que necesitabamos)

		JPanel layout_botones = new JPanel(new FlowLayout());
		layout_botones.setBorder(new TitledBorder("Selecciona el tipo de entrenamiento que quieres añadir: "));
		//
		JButton fuerza = new JButton("Fuerza");
		fuerza.setFocusable(false);
		layout_botones.add(fuerza);
		JButton cardio = new JButton("Cardio");
		cardio.setFocusable(false);
		layout_botones.add(cardio);
		JButton natacion = new JButton("Natacion");
		natacion.setFocusable(false);
		layout_botones.add(natacion);
		
		ventanaPrincipal_nuevaRutina.add(layout_botones, BorderLayout.NORTH);

//
		
		//GridBagConstraints gbc = new GridBagConstraints();
		
		
		//ventana_nueva_rutina.setLayout(new GridBagLayout());
		
		ventanaPrincipal_nuevaRutina.add(new JTree(), BorderLayout.WEST);
		ventanaPrincipal_nuevaRutina.add(ventana_central_MuestraRutinas);
		JTree arbol = new JTree();
		ventanaPrincipal_nuevaRutina.add(arbol, BorderLayout.WEST);
		ventanaPrincipal_nuevaRutina.add(ventana_central_MuestraRutinas);
        
        
		ventanaPrincipal_nuevaRutina.setBackground(new Color(130, 195, 65));
		
		
		//añadimos el icono de la aplicacion en la parte de arriba
		ImageIcon icono_aplicacion = new ImageIcon("resourses/images/logotipo.png");
		
	
        
    
        
     
     	
		setResizable(false);
		
		//FUENTE-EXTERNA
				//URL: (https://www.forosdelweb.com/f45/ajuste-automatico-jframe-853529/)
				//SIN-CAMBIOS
		//Hacer que la pantalla se habra a la mitad del tamaño
		float escalar = 0.5F; // una ventana al 50% del tamaño de la pantalla
		int ancho = (int)(Toolkit.getDefaultToolkit().getScreenSize(). width*escalar);
		int alto = (int)(Toolkit.getDefaultToolkit().getScreenSize(). height*escalar);
		this.setSize(ancho,alto);

        


     	
        // Centrar la ventana en la pantalla
     	setLocationRelativeTo(null);

        // Hacemos visible la ventana principal
        setVisible(true);

	     ventana_central_MuestraRutinas.add(scrollPane, BorderLayout.CENTER);

        
		this.add(ventanaPrincipal_nuevaRutina);

	}
	
	private void initTableRutina() {
        // Crear cabecera de la tabla
        Vector<String> cabeceraTabla = new Vector<String>(
            Arrays.asList("Tipo Entrenamiento", "Ejercicio", "Musculo", "Series", "Repeticiones", "Tiempo")
        );
        // Crear el modelo de datos de la tabla
        this.modeloDatosTablaRutina = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceraTabla);

        // Crear la tabla y configurar celdas editables
        this.tablaRutina = new JTable(this.modeloDatosTablaRutina) {
            public boolean isCellEditable(int row, int column) {
                return column == 3 || column == 4 || column == 5;
            }
        };

        // Añadir la tabla a un JScrollPane y agregar el JScrollPane al panel central
       // JScrollPane scrollPane = new JScrollPane(this.tablaRutina);
        //JPanel ventana_central_MuestraRutinas = new JPanel(new BorderLayout());
        //ventana_central_MuestraRutinas.add(scrollPane, BorderLayout.CENTER);
        
        // Añadir el panel de la tabla al panel principal
    }}

