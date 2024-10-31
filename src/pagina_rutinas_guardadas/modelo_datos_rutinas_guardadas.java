package pagina_rutinas_guardadas;

import java.util.Arrays;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import clases_de_apyo.Rutina;

public class modelo_datos_rutinas_guardadas extends DefaultTableModel {
private static final long serialVersionUID = 1L;
	
	private List<Rutina> rutinas;
	private final List<String> headers = Arrays.asList(
			"Nombre",
			"Numero de ejercicio", 
			"Objetivo de la sesión"
			);

	public modelo_datos_rutinas_guardadas(List<Rutina> rutinas) {
	    super(); 
	    this.rutinas = rutinas;
	    for (String header : headers) {
	        this.addColumn(header);
	    }
	}
	
	public String getColumnName(int column) {
		return headers.get(column);
	}

	@Override

	public int getRowCount() {
	    return (rutinas != null) ? rutinas.size() : 0;

	}
	@Override
	public int getColumnCount() {
		return headers.size(); 
	}
	
	@Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
	
	@Override
    public void setValueAt(Object aValue, int row, int column) {    	
    }
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
	    Rutina rutina = rutinas.get(rowIndex);
	    switch (columnIndex) {
	        case 0: return rutina.getNombre(); 
	        case 1: return rutina.getLista_ejercicios().size(); 
	        case 2: return rutina.getObjetivo();  
	        default: return null;
	    }
	}

}
