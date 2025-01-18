package main;

import java.util.ArrayList;
import java.util.List;

import domain.Ejercicio;
import domain.Ejercicio_Natacion;
import domain.Ejercicio_cardio;
import domain.Ejercicio_gym;
import domain.Ejercicio_Natacion.EstiloNat;
import domain.Ejercicio_cardio.TipoCardio;
import domain.Ejercicio_gym.EjAbdominales;
import domain.Ejercicio_gym.EjAductor;
import domain.Ejercicio_gym.EjAntebrazo;
import domain.Ejercicio_gym.EjBiceps;
import domain.Ejercicio_gym.EjCuadriceps;
import domain.Ejercicio_gym.EjEspaldaInf;
import domain.Ejercicio_gym.EjEspaldaSup;
import domain.Ejercicio_gym.EjFemoral;
import domain.Ejercicio_gym.EjGluteo;
import domain.Ejercicio_gym.EjHombro;
import domain.Ejercicio_gym.EjIsquiotibiales;
import domain.Ejercicio_gym.EjPecho;
import domain.Ejercicio_gym.EjTriceps;
import db.GestorBD;
import gui.Login;


public class Main {
	
	
    public static List<Ejercicio> ejercicios;


	public static void main(String[] args) {

		GestorBD gestor = new GestorBD();
		gestor.borrarDatos();
		gestor.borrarBBDD();
		gestor.crearBBDD();
		new Login(gestor);
		
		ejercicios = new ArrayList<>();

		
        // Agregar ejercicios de cardio
        for (TipoCardio tipo : TipoCardio.values()) {
            ejercicios.add(new Ejercicio_cardio(tipo.toString(), "foto_cardio.jpg", 30)); // Ejercicio_cardio
        }

        // Agregar ejercicios de brazos
        for (EjTriceps tipo : EjTriceps.values()) {
            ejercicios.add(new Ejercicio_gym(tipo.toString(), "foto_triceps.jpg", 4, 20)); // Ejercicio_gym
        }
        for (EjBiceps tipo : EjBiceps.values()) {
            ejercicios.add(new Ejercicio_gym(tipo.toString(), "foto_biceps.jpg", 4, 15)); // Ejercicio_gym
        }
        for (EjAntebrazo tipo : EjAntebrazo.values()) {
            ejercicios.add(new Ejercicio_gym(tipo.toString(), "foto_antebrazo.jpg", 3, 10)); // Ejercicio_gym
        }
        for (EjHombro tipo : EjHombro.values()) {
            ejercicios.add(new Ejercicio_gym(tipo.toString(), "foto_hombro.jpg", 4, 20)); // Ejercicio_gym
        }

        // Agregar ejercicios de torso
        for (EjPecho tipo : EjPecho.values()) {
            ejercicios.add(new Ejercicio_gym(tipo.toString(), "foto_pecho.jpg", 4, 25)); // Ejercicio_gym
        }
        for (EjAbdominales tipo : EjAbdominales.values()) {
            ejercicios.add(new Ejercicio_gym(tipo.toString(), "foto_abdominales.jpg", 4, 0)); // Sin peso
        }
        for (EjEspaldaSup tipo : EjEspaldaSup.values()) {
            ejercicios.add(new Ejercicio_gym(tipo.toString(), "foto_espalda_sup.jpg", 4, 30)); // Ejercicio_gym
        }
        for (EjEspaldaInf tipo : EjEspaldaInf.values()) {
            ejercicios.add(new Ejercicio_gym(tipo.toString(), "foto_espalda_inf.jpg", 3, 40)); // Ejercicio_gym
        }

        // Agregar ejercicios de piernas
        for (EjGluteo tipo : EjGluteo.values()) {
            ejercicios.add(new Ejercicio_gym(tipo.toString(), "foto_gluteo.jpg", 3, 20)); // Ejercicio_gym
        }
        for (EjIsquiotibiales tipo : EjIsquiotibiales.values()) {
            ejercicios.add(new Ejercicio_gym(tipo.toString(), "foto_isquios.jpg", 3, 25)); // Ejercicio_gym
        }
        for (EjAductor tipo : EjAductor.values()) {
            ejercicios.add(new Ejercicio_gym(tipo.toString(), "foto_aductor.jpg", 3, 20)); // Ejercicio_gym
        }
        for (EjFemoral tipo : EjFemoral.values()) {
            ejercicios.add(new Ejercicio_gym(tipo.toString(), "foto_femoral.jpg", 3, 25)); // Ejercicio_gym
        }
        for (EjCuadriceps tipo : EjCuadriceps.values()) {
            ejercicios.add(new Ejercicio_gym(tipo.toString(), "foto_cuadriceps.jpg", 4, 30)); // Ejercicio_gym
        }

        // Agregar ejercicios de natación
        for (EstiloNat estilo : EstiloNat.values()) {
            ejercicios.add(new Ejercicio_Natacion(estilo.toString(), "foto_natacion.jpg", estilo, 60)); // Ejercicio_Natacion
        }
		
	}

}