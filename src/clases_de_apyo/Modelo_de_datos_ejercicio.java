package clases_de_apyo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.table.DefaultTableModel;

public class Modelo_de_datos_ejercicio extends DefaultTableModel {

private static final long serialVersionUID = 1L;
	
	private ArrayList<Ejercicio> ejercicios;
	private final List<String> headers = Arrays.asList(
		    "Nombre",
		    "Tipo",
		    "Foto"
		    );


	public Modelo_de_datos_ejercicio(ArrayList<Ejercicio> ejercicios) {
		this.ejercicios = ejercicios;
	}
	
	public String getColumnName(int column) {
		return headers.get(column);
	}

	@Override

	public int getRowCount() {
		if (ejercicios != null) {
			return ejercicios.size();
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
	    Ejercicio ejercicio = ejercicios.get(rowIndex);
	    switch (columnIndex) {
	        case 0: return ejercicio.getNombre();           // nombre del ejercicio
	        case 1: { // tipo de ejercicio
	        	if(ejercicio instanceof Ejercicio_gym) {
	        		return "GYM";
	        	} else if(ejercicio instanceof Ejercicio_Natacion) {
	        		return "Natacion";
	        	} else if(ejercicio instanceof Ejercicio_runing) {
	        		return "Running";
	        	}
	        	break;
	        }
	        case 2: return ejercicio.getUbicacion_foto(); //path de la foto
	        default: return null;
	    }
		return null;
		
	}

	public void setListaEjercicios(ArrayList<Ejercicio> ejercicios) {
		this.ejercicios = ejercicios;
		fireTableDataChanged(); //IA (CHAT GPT)
		
	}

	public ArrayList<Ejercicio> getEjercicios() {
		return ejercicios;
	}

	public void setEjercicios(ArrayList<Ejercicio> ejercicios) {
		this.ejercicios = ejercicios;
	}

	public List<String> getHeaders() {
		return headers;
	}


}
