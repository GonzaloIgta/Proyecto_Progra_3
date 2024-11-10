package clases_de_apyo;

import java.util.Objects;

public class Ejercicio_gym extends  Ejercicio{
	
	protected int repeticiones,series, peso;
	public PartesDelCuerpo musculo_trabajado;

	
	public enum PartesDelCuerpo{
		
		Brazos, Torso, Pierna;
	}
	public enum MuscBrazos{
		
		Triceps, Biceps, Antebrazo, Hombro;
		
	}
	public enum MuscTorso{
		
		Pecho, Abdominales, Espalda_Superior, Espalda_Inferior; 
	    @Override
	    public String toString() {
	        // Reemplaza las barras bajas por espacios y convierte a mayúsculas cada palabra
	        return name().replace("_", " ");
	    }
	}
	
	public enum MuscPierna{
		
		Gluteo, Isquiotibiales, Aductor, Femoral, Cuádriceps, 
		
	}
	
	
	
	//los siguientes enum los hemos creado con Chat para ahorrarnos tiempo en esta tarea tan simple
	public enum EjTriceps {
	    FONDOS_EN_PARALELAS, 
	    EXTENSION_CON_POLEA, 
	    PRESS_FRANCES;

	    @Override
	    public String toString() {
	        return name().replace("_", " ").toLowerCase();
	    }
	}

	public enum EjBiceps {
	    CURL_CON_BARRA_Z, 
	    CURL_CON_MANCUERNAS, 
	    CURL_EN_PREDICADOR, 
	    CURL_MARTILLO, 
	    CURL_EN_CABLE;

	    @Override
	    public String toString() {
	        return name().replace("_", " ").toLowerCase();
	    }
	}

	public enum EjAntebrazo {
	    CURL_MUÑECA, 
	    CURL_INVERSO_MUNECA, 
	    LEVANTAMIENTO_DISCO;

	    @Override
	    public String toString() {
	        return name().replace("_", " ").toLowerCase();
	    }
	}

	public enum EjHombro {
	    PRESS_MILITAR, 
	    ELEVACIONES_LATERALES, 
	    ELEVACIONES_FRONTALES, 
	    REMO_AL_MENTON, 
	    FACE_PULLS;

	    @Override
	    public String toString() {
	        return name().replace("_", " ").toLowerCase();
	    }
	}

	public enum EjPecho {
	    PRESS_BANCA_PLANO, 
	    PRESS_BANCA_INCLINADO,
	    PRESS_BANCA_PLANO_CON_MANCUERNAS, 
	    PRESS_BANCA_INCLINADO_CON_MANCUERNAS,
	    FONDOS_PARA_PECHO, 
	    PRESS_EN_MAQUINA, 
	    CRUCES_EN_POLEA_ARRIBA_ABAJO,
	    CRUCES_EN_POLEA_ABAJO_ARRIBA
	    ;

	    @Override
	    public String toString() {
	        return name().replace("_", " ").toLowerCase();
	    }
	}

	public enum EjAbdominales {
	    CRUNCH, 
	    ELEVACIONES_DE_PIERNAS, 
	    PLANCHA;

	    @Override
	    public String toString() {
	        return name().replace("_", " ").toLowerCase();
	    }
	}

	public enum EjEspaldaSuperior {
	    DOMINADAS, 
	    REMO_CON_BARRA, 
	    JALONES_EN_POLEA, 
	    REMO_EN_MAQUINA, 
	    PULL_OVER_EN_POLEA;

	    @Override
	    public String toString() {
	        return name().replace("_", " ").toLowerCase();
	    }
	}

	public enum EjEspaldaInferior {
	    PESO_MUERTO, 
	    PESO_MUERTO_RUMANO, 
	    HIPEREXTENSIONES;


	    @Override
	    public String toString() {
	        return name().replace("_", " ").toLowerCase();
	    }
	}

	public enum EjGluteo {
	    HIP_THRUST, 
	    SENTADILLA_PROFUNDA, 
	    SENTADILLA_BULGARA;

	    @Override
	    public String toString() {
	        return name().replace("_", " ").toLowerCase();
	    }
	}

	public enum EjIsquiotibiales {
	    PESO_MUERTO_RUMANO, 
	    CURL_FEMORAL, 
	    SENTADILLA_SUMO;

	    @Override
	    public String toString() {
	        return name().replace("_", " ").toLowerCase();
	    }
	}

	public enum EjAductor {
	    SENTADILLA_SUMO, 
	    MAQUINA_ADUCTORES, 
	    ELEVACION_PIERNA_POLEA,  
	    SENTADILLAS_PROFUNDAS;

	    @Override
	    public String toString() {
	        return name().replace("_", " ").toLowerCase();
	    }
	}

	public enum EjFemoral {
	    CURL_FEMORAL_ACOSTADO, 
	    PESO_MUERTO_UNA_PIERNA, 
	    PESO_MUERTO_PIERNA_RECTAS, 
	    PRENSA_PIES_ALTOS;

	    @Override
	    public String toString() {
	        return name().replace("_", " ").toLowerCase();
	    }
	}

	public enum EjCuadriceps {
	    SENTADILLA, 
	    EXTENSION_PIERNA, 
	    PRENSA_PIERNA, 
	    SENTADILLA_HACK, 
	    LUNGES;

	    @Override
	    public String toString() {
	        return name().replace("_", " ").toLowerCase();
	    }
	}

	
	
	public Ejercicio_gym(String nombre, String ubicacion_foto,
			 int series,int peso) {
		super(nombre, ubicacion_foto);
		this.series = series;
		this.peso = peso;
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

	public PartesDelCuerpo getMusculo_trabajado() {
		return musculo_trabajado;
	}

	public void setMusculo_trabajado(PartesDelCuerpo musculo_trabajado) {
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

