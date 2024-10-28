package clases_de_apyo;

import java.util.ArrayList;
import java.util.Objects;

import clases_de_apyo.Ejercicio_gym.Musculo_trabajado;

public class Rutina {
	String nombre,tipo_rutina;
	ArrayList<Ejercicio> lista_ejercicios;
	public Rutina(String nombre,String tipo_rutina, ArrayList<Ejercicio> lista_ejercicios) {
		this.nombre = nombre;
		this.tipo_rutina = tipo_rutina;
		this.lista_ejercicios = lista_ejercicios;
	}
	
	public Rutina(String nombre, String tipo_rutina) {
		this.nombre = nombre;
		this.tipo_rutina = tipo_rutina;
		this.lista_ejercicios = new ArrayList<>();
	}
	
	public boolean añadir_ejercicio(Ejercicio ejercicio) {
		return  this.lista_ejercicios.add(ejercicio);
	}
	
	public String getTipo_rutina() {
		return tipo_rutina;
	}

	public void setTipo_rutina(String tipo_rutina) {
		this.tipo_rutina = tipo_rutina;
	}

	public ArrayList<Musculo_trabajado> musculos_trabajados(){
		ArrayList<Musculo_trabajado> musculos = new ArrayList<>();
		for(Ejercicio ejercicio : this.lista_ejercicios) {
			if(ejercicio instanceof Ejercicio_gym) {
				Musculo_trabajado musculo = ((Ejercicio_gym) ejercicio).getMusculo_trabajado();
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

	@Override
	public int hashCode() {
		return Objects.hash(lista_ejercicios, nombre);
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
		return Objects.equals(lista_ejercicios, other.lista_ejercicios) && Objects.equals(nombre, other.nombre) && Objects.equals(tipo_rutina, other.tipo_rutina);
	}
	
	
}
