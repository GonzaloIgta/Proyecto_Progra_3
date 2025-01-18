package domain;


import java.util.Objects;

public class Ejercicio_cardio extends Ejercicio {
	protected int duracion;


	public enum TipoCardio{
		
		Correr, 
		Bici_estática, 
		Ciclismo_de_montaña, 
		Ciclismo_de_carretera, 
		Comba;
	    @Override
	    public String toString() {
	        return name().replace("_", " ");
	    }
	}

	public Ejercicio_cardio(String nombre, String ubicacion_foto, int duracion) {
		super(nombre, ubicacion_foto);
		this.duracion = duracion;

	}









	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(duracion);
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ejercicio_cardio other = (Ejercicio_cardio) obj;
		return duracion == other.duracion;
	}









	public int getDuracion() {
		return duracion;
	}






	public void setDuracion(int duracion) {
		this.duracion = duracion;
	} 
	
	
	
}
