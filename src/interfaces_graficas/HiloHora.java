package interfaces_graficas;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class HiloHora {
	
	JLabel lblHoraFecha =  new JLabel();
	
	private void iniciarHiloHora() {
	    
		Thread hiloHoraFecha = new Thread (() -> {
			while(true) {
				try {
					LocalDateTime ahora = LocalDateTime.now();
					DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm:ss - dd/MM/yyyy");
					 String horaFechaFormateada = ahora.format(formato);
					 
					 SwingUtilities.invokeLater(() -> lblHoraFecha.setText(horaFechaFormateada));
					// Actualiza cada segundo
					 Thread.sleep(1000); 
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		hiloHoraFecha.start();
	}

}
