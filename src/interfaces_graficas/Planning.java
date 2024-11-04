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
		
		
        
        //titulo de la ventana
        this.setTitle("Planning Semanal");
        
		
		
		float escalar = 0.7F; // una ventana al 50% del tamaño de la pantalla
		int ancho = (int)(Toolkit.getDefaultToolkit().getScreenSize(). width*escalar);
		int alto = (int)(Toolkit.getDefaultToolkit().getScreenSize(). height*escalar);
		this.setSize(ancho,alto);
		
		this.setResizable(false);
		
     	this.setLocationRelativeTo(null);
     	
     	this.setLayout(new GridLayout(1,7));
     	
     	JPanel panelL = new JPanel();
     	panelL.setLayout(new GridLayout(2,1));
     	JButton botRL = new JButton("Rutina Lunes");
     	JButton botCL = new JButton("Cambiar");
     	botRL.setFocusable(false);
     	botCL.setFocusable(false);
     	panelL.add(botRL);
     	panelL.add(botCL);
     	this.add(panelL);
     	JPanel panelM = new JPanel();
     	panelM.setLayout(new GridLayout(2,1));
     	JButton botRM = new JButton("Rutina Martes");
     	JButton botCM = new JButton("Cambiar");
     	botRM.setFocusable(false);
     	botCM.setFocusable(false);
     	panelM.add(botRM);
     	panelM.add(botCM);
     	this.add(panelM);
     	JPanel panelX = new JPanel();
     	panelX.setLayout(new GridLayout(2,1));
     	JButton botRX = new JButton("Rutina Miercoles");
     	JButton botCX = new JButton("Cambiar");
     	botRX.setFocusable(false);
     	botCX.setFocusable(false);
     	panelX.add(botRX);
     	panelX.add(botCX);
     	this.add(panelX);
     	JPanel panelJ = new JPanel();
     	panelJ.setLayout(new GridLayout(2,1));
     	JButton botRJ = new JButton("Rutina Jueves");
     	JButton botCJ = new JButton("Cambiar");
     	botRJ.setFocusable(false);
     	botCJ.setFocusable(false);
     	panelJ.add(botRJ);
     	panelJ.add(botCJ);
     	this.add(panelJ);
     	JPanel panelV = new JPanel();
     	panelV.setLayout(new GridLayout(2,1));
     	JButton botRV = new JButton("Rutina Viernes");
     	JButton botCV = new JButton("Cambiar");
     	botRV.setFocusable(false);
     	botCV.setFocusable(false);
     	panelV.add(botRV);
     	panelV.add(botCV);
     	this.add(panelV);
     	JPanel panelS = new JPanel();
     	panelS.setLayout(new GridLayout(2,1));
     	JButton botRS = new JButton("Rutina Sabado");
     	JButton botCS = new JButton("Cambiar");
     	botRS.setFocusable(false);
     	botCS.setFocusable(false);
     	panelS.add(botRS);
     	panelS.add(botCS);
     	this.add(panelS);
     	JPanel panelD = new JPanel();
     	panelD.setLayout(new GridLayout(2,1));
     	JButton botRD = new JButton("Rutina Domingo");
     	JButton botCD = new JButton("Cambiar");
     	botRD.setFocusable(false);
     	botCD.setFocusable(false);
     	panelD.add(botRD);
     	panelD.add(botCD);
     	this.add(panelD);
     	
     	this.setVisible(true);
		
	}
}
