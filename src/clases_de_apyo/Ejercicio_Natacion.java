package clases_de_apyo;

import java.util.Objects;

public class Ejercicio_Natacion extends Ejercicio {

	
	
	protected EstiloNat estilo;
	protected int duracion;
	public enum TipoNat {
	    
	    Mar_Abierto, 
	    Piscina;

	    public String toString() {
	        return name().replace("_", " ");
	    }
	    
	  
	}
	
	
	public enum EstiloNat{
		
		Crol, 
		Braza,
		Mariposa, 
		Espalda;	  
		
		public String toString() {
	        return name().replace("_", " ");
	    }
	}
	
	
	
	public Ejercicio_Natacion(String nombre, String ubicacion_foto, EstiloNat estilo, int duracion) {
		super(nombre, ubicacion_foto);
		this.estilo = estilo;
		this.duracion = duracion;

	}


	public EstiloNat getEstilo() {
		return estilo;
	}

	public void setEstilo(EstiloNat estilo) {
		this.estilo = estilo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(duracion, estilo);
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
		Ejercicio_Natacion other = (Ejercicio_Natacion) obj;
		return duracion == other.duracion && estilo == other.estilo;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	
	
	
}