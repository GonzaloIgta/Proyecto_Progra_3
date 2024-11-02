package clases_de_apyo;

import java.util.Objects;

public class Ejercicio_gym extends  Ejercicio{
	
	protected int repeticiones,series, peso;
	public Musculo_trabajado musculo_trabajado;

	public Ejercicio_gym(String nombre, String ubicacion_foto, Musculo_trabajado musculo_trabajado,
			int repeticiones, int series,int peso) {
		super(nombre, ubicacion_foto);
		this.musculo_trabajado = musculo_trabajado;
		this.repeticiones = repeticiones;
		this.series = series;
		this.peso = peso;
	}

	
	public int getRepeticiones() {
		return repeticiones;
	}

	public void setRepeticiones(int repeticiones) {
		this.repeticiones = repeticiones;
	}

	public int getSeries() {
		return series;
	}

	public void setSeries(int series) {
		this.series = series;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public Musculo_trabajado getMusculo_trabajado() {
		return musculo_trabajado;
	}

	public void setMusculo_trabajado(Musculo_trabajado musculo_trabajado) {
		this.musculo_trabajado = musculo_trabajado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(musculo_trabajado, peso, repeticiones, series);
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
		Ejercicio_gym other = (Ejercicio_gym) obj;
		return musculo_trabajado == other.musculo_trabajado && peso == other.peso && repeticiones == other.repeticiones
				&& series == other.series;
	}

	
	
	
}
