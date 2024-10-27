package clases_de_apyo;

import java.util.Objects;

public class Ejercicio_runing extends Ejercicio {
	protected float distancia_en_km,ritmo;


	public Ejercicio_runing(String nombre, String ubicacion_foto, float distancia_en_km, float ritmo) {
		super(nombre, ubicacion_foto);
		this.distancia_en_km = distancia_en_km;
		this.ritmo = ritmo;
	}

	public float getDistancia_en_km() {
		return distancia_en_km;
	}


	public void setDistancia_en_km(float distancia_en_km) {
		this.distancia_en_km = distancia_en_km;
	}


	public float getRitmo() {
		return ritmo;
	}


	public void setRitmo(float ritmo) {
		this.ritmo = ritmo;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(distancia_en_km, ritmo);
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
		Ejercicio_runing other = (Ejercicio_runing) obj;
		return Float.floatToIntBits(distancia_en_km) == Float.floatToIntBits(other.distancia_en_km)
				&& Float.floatToIntBits(ritmo) == Float.floatToIntBits(other.ritmo);
	} 
	
	
	
}
