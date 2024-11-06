package interfaces_graficas;

import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import clases_de_apyo.Rutina;

public class Planning extends JFrame{
	
	public Planning(){
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
        
        //titulo de la ventana
        this.setTitle("Planning Semanal");
        
		
		
		float escalar = 0.7F; // una ventana al 50% del tamaño de la pantalla
		int ancho = (int)(Toolkit.getDefaultToolkit().getScreenSize(). width*escalar);
		int alto = (int)(Toolkit.getDefaultToolkit().getScreenSize(). height*escalar);
		this.setSize(ancho,alto);
		
		this.setResizable(false);
		
     	this.setLocationRelativeTo(null);
     	
     	this.setLayout(new GridLayout(1,7));
     	
     	for(int i =1; i <= 7; i++){
     		JPanel panel = new JPanel();
         	panel.setLayout(new GridLayout(2,1));

         	JButton botC = new JButton("Cambiar");
         	botC.setFocusable(false);
         	panel.add(new JPanel());
         	panel.add(botC);
         	this.add(panel);
     	}
     	
     	
		
	}
	public void open() {
        setVisible(true);
	}

}
