package domain;

import java.util.Objects;

public abstract class Ejercicio implements Comparable<Ejercicio>{
	


	public static enum tipoEjercicio{
		
		Ejercicio_cardio, 
		Ejercicio_gym,
		Ejercicio_Natacion
	}



	protected String nombre,ubicacion_foto;
	
	
	public Ejercicio(String nombre, String ubicacion_foto) {
		super();
		this.nombre = nombre;
		this.ubicacion_foto = ubicacion_foto;
	}

	
	public String toString() {
		// TODO Auto-generated method stub
		return this.getNombre();
	}
	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getUbicacion_foto() {
		return ubicacion_foto;
	}


	public void setUbicacion_foto(String ubicacion_foto) {
		this.ubicacion_foto = ubicacion_foto;
	}


	@Override
	public int hashCode() {
		return Objects.hash(nombre, ubicacion_foto);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ejercicio other = (Ejercicio) obj;
		return Objects.equals(nombre, other.nombre) && Objects.equals(ubicacion_foto, other.ubicacion_foto);
	}

	@Override
	public int compareTo(Ejercicio otro) {
	    // Comparar primero por nombre (orden alfabético)
	    int resultado = this.nombre.compareTo(otro.nombre);
	    
	    // Si los nombres son iguales, comparar por ubicación de la foto
	    if (resultado == 0) {
	        resultado = this.ubicacion_foto.compareTo(otro.ubicacion_foto);
	    }

	    return resultado;
	}



	

}