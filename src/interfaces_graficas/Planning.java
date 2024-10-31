package interfaces_graficas;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Planning extends JFrame{
	
	public Planning(){
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setVisible(true);
        
        //titulo de la ventana
        this.setTitle("Planning Semanal");
        
		
		
		float escalar = 0.5F; // una ventana al 50% del tamaño de la pantalla
		int ancho = (int)(Toolkit.getDefaultToolkit().getScreenSize(). width*escalar);
		int alto = (int)(Toolkit.getDefaultToolkit().getScreenSize(). height*escalar);
		this.setSize(ancho,alto);
		
		this.setResizable(false);
		
     	this.setLocationRelativeTo(null);
     	
     	this.setLayout(new GridLayout(1,7));
     	
     	JPanel panel = new JPanel();
     	JLabel label = new JLabel();
     	JButton bot = new JButton("Lunes");
     	label.setText("Lunes");
     	label.setHorizontalTextPosition(JLabel.CENTER);
     	label.setVerticalTextPosition(JLabel.TOP);
     	panel.add(bot);
     	label.add(panel);
     	this.add(label);
     	this.add(new JButton("Martes"));
     	this.add(new JButton("Miercoles"));
     	this.add(new JButton("Jueves"));
     	this.add(new JButton("Viernes"));
     	this.add(new JButton("Sabado"));
     	this.add(new JButton("Domingo"));
     	//coment
		
	}
}
