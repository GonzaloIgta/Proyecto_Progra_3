package clases_de_apyo;

import java.awt.GridLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
//FUENTE-EXTERNA
//URL: (EXAMEN 22-23)
//ADAPTADO (ADAPTADO A DATOS NECESITADOS)
import javax.swing.border.TitledBorder;

public class Dialogo_asignar extends JDialog{

	private static final long serialVersionUID = 1L;

	public Dialogo_asignar(Rutina rutina) {
		JPanel panel_asignar = new JPanel();
		panel_asignar.setBorder(new TitledBorder("Datos del vuelo"));
		panel_asignar.setLayout(new GridLayout(5, 1));
		panel_asignar.show();
		this.add(panel_asignar);

		

	}

}
