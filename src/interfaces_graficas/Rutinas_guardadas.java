package interfaces_graficas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableCellRenderer;

import clases_de_apyo.Modelo_de_datos_rutinas;
import clases_de_apyo.Rescalar_imagen;
import clases_de_apyo.Rutina;

public class Rutinas_guardadas extends JFrame {
	private static final long serialVersionUID = 1L;
	private List<Rutina> rutinas;
    private JTextField txtFiltro;
    private Modelo_de_datos_rutinas modelo_de_datos;
    private JTable tabla_rutinas;
    private JScrollPane scrollPanelRutinas;
    private JProgressBar barra = new JProgressBar(JProgressBar.VERTICAL);
    private Rescalar_imagen rescalar = new Rescalar_imagen();
    
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

        


        
        initTables();
        
        
        // Añadimos tabla	
        constantes.gridx = 0;
        constantes.gridy = 0;
        constantes.gridwidth = 2;
        constantes.gridheight = 2;
        constantes.fill = GridBagConstraints.BOTH; // El área de texto debe estirarse en ambos sentidos.
        constantes.weighty = 1.0;
        ventana_principal.add(scrollPanelRutinas, constantes); // También añadido al panel principal
        constantes.weighty = 0.0; // Restauramos al valor por defecto, para no afectar a los siguientes componentes.

        
        // Añadir progres bar de la derecha
        constantes.gridx = 2; 
        constantes.gridy = 0; 
        constantes.gridwidth = 1; 
        constantes.gridheight = GridBagConstraints.REMAINDER; // O utiliza un valor mayor si tienes varias filas
        constantes.weightx = 0.5; 
        constantes.weighty = 0; 
        barra.setValue(0);
        barra.setStringPainted(true);
        JPanel sitio_donde_colocamos_barra = new JPanel();
        sitio_donde_colocamos_barra.setLayout(new BorderLayout());
        sitio_donde_colocamos_barra.add(barra);
        sitio_donde_colocamos_barra.setBorder(new TitledBorder("Exigencia del entrenamiento"));   
        ventana_principal.add(sitio_donde_colocamos_barra, constantes); // También añadido al panel principal
    
        
        // Añadir ventana_principal al JFrames
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




    

    public List<Rutina> getRutinas() {
        return rutinas;
    }

    public void setRutinas(List<Rutina> rutinas) {
        this.rutinas = rutinas;
    }
    
    private void initTables() {
        // Crear el modelo de datos y la tabla
        modelo_de_datos = new Modelo_de_datos_rutinas(rutinas); 
        JTable tablaRutinas = new JTable(modelo_de_datos);
        this.tabla_rutinas=tablaRutinas;
        this.scrollPanelRutinas = new JScrollPane(tablaRutinas);
        scrollPanelRutinas.setBorder(new TitledBorder("Rutinas"));
		
	  
	    TableCellRenderer cellRenderer = new TableCellRenderer() {
	        @Override
	        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            	JLabel jlabel = new JLabel();
        		jlabel.setOpaque(true);
            	jlabel.setVerticalAlignment(SwingConstants.CENTER);

            	
            	//poner iconos en vez de texto
	            if (column==1 ) {
	            	jlabel.setHorizontalAlignment(SwingConstants.CENTER); 
	            	if(value.toString().equals("MUSCULACION")) {
	            		rescalar.setScaledImage(jlabel, "/resourses/images/musculacion.png", 40, 40);
	            	} else if(value.toString().equals("CARDIOVASCULAR")){
	            		rescalar.setScaledImage(jlabel, "/resourses/images/cardio.png", 40, 40);
	            	} else if(value.toString().equals("PERDIDA_DE_PESO")){
	            		rescalar.setScaledImage(jlabel, "/resourses/images/perdida_de_peso.png", 40, 40);
	            	}else {
	            		jlabel.setText("FALTA DE METER IMAGEN EN INIT  TABLE");
	            	}
        			jlabel.setToolTipText(value.toString());

	            	
	                
	            } else {
	            	jlabel.setText(value.toString());
	            	
	            }
	            
	           
	            
	             //cambiar fonde dependiendo de fila par o impar
            	if(row %2 ==0) {
	            	jlabel.setBackground(new Color(250, 249, 249));
            	} else {
            		jlabel.setBackground(new Color(190, 227, 219));
            	}
            	
            	
            	

            	if(isSelected) {
            		jlabel.setBackground(tabla_rutinas.getSelectionBackground());
            		actualizar_barra(row);
            	}
            	
            	
	            return jlabel;
	        }
	    };
	    
	    this.tabla_rutinas.setRowHeight(40);
	    this.tabla_rutinas.setDefaultRenderer(Object.class, cellRenderer);
    }
    
    private void actualizar_barra(int fila) {
    	int num_ejercicios = (int)modelo_de_datos.getValueAt(fila, 2);
    	barra.setValue(num_ejercicios*7);
   
    }
    
}
