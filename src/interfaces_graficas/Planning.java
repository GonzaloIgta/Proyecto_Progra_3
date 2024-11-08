package interfaces_graficas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import clases_de_apyo.Rutina;

public class Planning extends JFrame{
	int contadorRutina;
	public Planning(){
		
        
        //titulo de la ventana
        this.setTitle("Planning Semanal");
        
		float escalar = 0.7F; // una ventana al 50% del tamaño de la pantalla
		int ancho = (int)(Toolkit.getDefaultToolkit().getScreenSize(). width*escalar);
		int alto = (int)(Toolkit.getDefaultToolkit().getScreenSize(). height*escalar);
		this.setSize(ancho,alto);
		
		this.setResizable(false);
		
     	this.setLocationRelativeTo(null);
     			
     	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
     	     	
     	//Panel con grid layout con 7 columnas para cada dia de la semana
     	JPanel panelPrincipal = new JPanel(new GridLayout(1,7));
     	
     	JButton botonVolver = new JButton("Volver al Menu Principal"); //nuevo boton para volver al menu principal
     	botonVolver.setFocusable(false);
     	
     	botonVolver.addActionListener(new ActionListener() {  //listener del boton
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Pagina_principal();
				dispose();
			}
		});
     	
     	JPanel panelVolver = new JPanel(new FlowLayout(FlowLayout.LEFT)); //nuevo panel para meter el boton y lo ponemos a la izquierda
     	panelVolver.add(botonVolver); //añadimos boton al panel
     	
     	
     	String[] diaSemana = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};

     	
     	//FUENTE EXTERNA
     	//IAG
     	//ADAPTADO
     	
     	
     	for (String dia : diaSemana) {
     		
     		//Crear un panel para cada dia
     		JPanel panelDia = new JPanel(new BorderLayout()); //Crea el panel para cada dia donde se almacenara nombre, rutinas, y botones
			JLabel labelDia = new JLabel(dia, JLabel.CENTER); //Crea el label para los nombres de los dias
     		JPanel panelRutinas = new JPanel(new GridLayout(6,1)); //Crea panel para almacenar 5 rutinas y boton para añadir rutinas
     		     		
     		panelDia.setBorder(new LineBorder(Color.BLACK)); //Añade borde para separar cada dia

     		panelRutinas.setBorder(new EmptyBorder(30, 0, 0, 0)); //Añade separacion vertical del panel de las rutinas (el del centro con el del north)
     		
     		
     		//Crear jlabel no hay rutinas
     		JLabel mensajeSinRutinas = new JLabel("No hay ninguna rutina");
     		mensajeSinRutinas.setForeground(Color.RED);  //color de letra rojo
     		mensajeSinRutinas.setHorizontalAlignment(JLabel.CENTER); // Aseguramos que el texto este centrado
     		mensajeSinRutinas.setVerticalAlignment(JLabel.CENTER);
     		mensajeSinRutinas.setPreferredSize(new Dimension(200, 60)); // Establecemos un tamaño preferido para que el texto pueda envolver

     		//añadir jlabel para iniciar 
     		panelRutinas.add(mensajeSinRutinas);
     		

     		
     		JButton botonAgregarRutina = new JButton("+");
     		botonAgregarRutina.setFocusable(false);
     		botonAgregarRutina.addActionListener(new ActionListener() {
				int contadorRutina = 0;
				@Override
				public void actionPerformed(ActionEvent e) {
					if (contadorRutina < 5) {
						
						JPanel panelRutina = new JPanel();
						JButton rutinaBoton = new JButton("Rutina" + (contadorRutina + 1));
						rutinaBoton.setFocusable(false);
						JButton eliminarBoton = new JButton("Eliminar");

						eliminarBoton.setFocusable(false);
						
						eliminarBoton.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
							panelRutinas.remove(panelRutina);
							panelRutinas.revalidate();  // Actualiza el panel
                            panelRutinas.repaint();  // Redibuja el panel
							
							contadorRutina--;
						
							if (contadorRutina < 5) {
								botonAgregarRutina.setEnabled(true);
							}
							
							
							if(contadorRutina == 0 ) {
								panelRutinas.add(mensajeSinRutinas);
								panelRutinas.revalidate();
								panelRutinas.repaint();
							}
							
							
							}
						});
						
						panelRutina.add(rutinaBoton);
						panelRutina.add(eliminarBoton);
						panelRutinas.add(panelRutina);
						panelRutinas.revalidate();  // Actualiza el panel para enseñar la nueva rutina
                        panelRutinas.repaint();  // Redibuja el panel
                        contadorRutina++;
                        
                        if (contadorRutina > 0) {
                        	panelRutinas.remove(mensajeSinRutinas);
                        	panelRutinas.revalidate(); // Actualiza el panel para eliminar el mensaje
                            panelRutinas.repaint(); // Redibuja el panel
                        } 
       
					} else {
                        	botonAgregarRutina.setEnabled(false);
                        }
				}
			});
     		
     		//Añadir el nombre del dia y su panel con rutinas y botones
     		panelDia.add(labelDia, BorderLayout.NORTH);
     		panelDia.add(panelRutinas, BorderLayout.CENTER);
     		panelDia.add(botonAgregarRutina,BorderLayout.SOUTH);
     		
     		
     		panelPrincipal.add(panelDia);
     	}
     	
     	
     
     	add(panelPrincipal,BorderLayout.CENTER);
     	add(panelVolver,BorderLayout.NORTH);
     	
     	 	
     	
     	
	}
	public void open() {
        setVisible(true);
	}

}
