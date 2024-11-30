package clases_de_apyo;

import java.util.ArrayList;
import java.util.Objects;

import clases_de_apyo.Ejercicio_gym.PartesDelCuerpo;

public class Rutina {
	public enum Objetivo_de_la_sesion{
		MUSCULACION,CARDIOVASCULAR,PERDIDA_DE_PESO
	}
	
	private int id = -1;
	protected Objetivo_de_la_sesion objetivo;
	protected String nombre;
	protected ArrayList<Ejercicio> lista_ejercicios;
	public Rutina(String nombre,Objetivo_de_la_sesion objetivo, ArrayList<Ejercicio> lista_ejercicios) {
		this.nombre = nombre;
		this.objetivo = objetivo;
		this.lista_ejercicios = lista_ejercicios;
	}
	
	public Rutina(String nombre, Objetivo_de_la_sesion objetivo) {
		this.nombre = nombre;
		this.objetivo = objetivo;
		this.lista_ejercicios = new ArrayList<>();
	}
	
	public boolean añadir_ejercicio(Ejercicio ejercicio) {
		return  this.lista_ejercicios.add(ejercicio);
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public Objetivo_de_la_sesion getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(Objetivo_de_la_sesion objetivo) {
		this.objetivo = objetivo;
	}

	public ArrayList<PartesDelCuerpo> musculos_trabajados(){
		ArrayList<PartesDelCuerpo> musculos = new ArrayList<>();
		for(Ejercicio ejercicio : this.lista_ejercicios) {
			if(ejercicio instanceof Ejercicio_gym) {
				PartesDelCuerpo musculo = ((Ejercicio_gym) ejercicio).getMusculo_trabajado();
				if(!musculos.contains(musculo)){
					musculos.add(musculo);
				}
			}
		}
		return musculos;
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Ejercicio> getLista_ejercicios() {
		return lista_ejercicios;
	}

	public void setLista_ejercicios(ArrayList<Ejercicio> lista_ejercicios) {
		this.lista_ejercicios = lista_ejercicios;
	}

	public String printLista_Ejercicios() {
		String TodosEjercicios = "";
		
		for (Ejercicio ejercicio : lista_ejercicios) {
			TodosEjercicios += ejercicio.getNombre() +", ";
		}
		return TodosEjercicios;
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(lista_ejercicios, nombre, objetivo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rutina other = (Rutina) obj;
		return Objects.equals(lista_ejercicios, other.lista_ejercicios) && Objects.equals(nombre, other.nombre)
				&& objetivo == other.objetivo;
	}

	public int getNumeroEjercicios() {
		if(this.lista_ejercicios!=null) {
			return lista_ejercicios.size();
		}
		return 0;
	}
	
	
}
