package clases_de_apyo;

import java.util.Objects;

public class Ejercicio_Natacion extends Ejercicio {

	
	
	protected estilo_natacion estilo;
	protected float distancia_en_km,ritmo;
	
	public Ejercicio_Natacion(String nombre, String ubicacion_foto, estilo_natacion estilo, float distancia_en_km,
			float ritmo) {
		super(nombre, ubicacion_foto);
		this.estilo = estilo;
		this.distancia_en_km = distancia_en_km;
		this.ritmo = ritmo;
	}

	public estilo_natacion getEstilo() {
		return estilo;
	}

	public void setEstilo(estilo_natacion estilo) {
		this.estilo = estilo;
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
		result = prime * result + Objects.hash(distancia_en_km, estilo, ritmo);
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
		return Float.floatToIntBits(distancia_en_km) == Float.floatToIntBits(other.distancia_en_km)
				&& estilo == other.estilo && Float.floatToIntBits(ritmo) == Float.floatToIntBits(other.ritmo);
	}

	
}
