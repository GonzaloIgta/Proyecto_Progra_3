package clases_de_apyo;

import java.util.Objects;

public abstract class Ejercicio {
	
	protected String nombre,ubicacion_foto;
	
	
	public Ejercicio(String nombre, String ubicacion_foto) {
		super();
		this.nombre = nombre;
		this.ubicacion_foto = ubicacion_foto;
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


	public abstract int getid();


	

}
