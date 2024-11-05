package clases_de_apyo;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

public class Modelo_de_datos_rutinas extends DefaultTableModel {

private static final long serialVersionUID = 1L;
	
	private List<Rutina> rutinas;
	private final List<String> headers = Arrays.asList(
		    "Nombre",
		    "Objetivo",
		    "Nº Ejercicios"		);


	public Modelo_de_datos_rutinas(List<Rutina> rutinas) {
		this.rutinas = rutinas;
	}
	
	public String getColumnName(int column) {
		return headers.get(column);
	}

	@Override

	public int getRowCount() {
		if (rutinas != null) {
			return rutinas.size();
		} else { 
			return 0;
		}
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
		
	    Rutina rutina = rutinas.get(rowIndex);  // Obtiene la rutina en la fila correspondiente
	    if(columnIndex == 999) { //999 = codigo para devolver_la_lista_de_ejercicios para crear el modelo de datos ejercicio
			return rutina;
		}
	    switch (columnIndex) {
	        case 0: return rutina.getNombre();           // Nombre de la rutina
	        case 1: return rutina.getObjetivo();         // Objetivo de la rutina
	        case 2: return rutina.getNumeroEjercicios(); // Número de ejercicios
	        default: return null;
	    }
	}


}
