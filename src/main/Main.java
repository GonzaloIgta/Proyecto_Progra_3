package main;

import java.util.ArrayList;
import java.util.List;

import clases_de_apyo.Ejercicio;
import clases_de_apyo.Ejercicio_Natacion;
import clases_de_apyo.Ejercicio_cardio;
import clases_de_apyo.Ejercicio_gym;
import clases_de_apyo.Ejercicio_Natacion.EstiloNat;
import clases_de_apyo.Ejercicio_cardio.TipoCardio;
import clases_de_apyo.Ejercicio_gym.EjAbdominales;
import clases_de_apyo.Ejercicio_gym.EjAductor;
import clases_de_apyo.Ejercicio_gym.EjAntebrazo;
import clases_de_apyo.Ejercicio_gym.EjBiceps;
import clases_de_apyo.Ejercicio_gym.EjCuadriceps;
import clases_de_apyo.Ejercicio_gym.EjEspaldaInf;
import clases_de_apyo.Ejercicio_gym.EjEspaldaSup;
import clases_de_apyo.Ejercicio_gym.EjFemoral;
import clases_de_apyo.Ejercicio_gym.EjGluteo;
import clases_de_apyo.Ejercicio_gym.EjHombro;
import clases_de_apyo.Ejercicio_gym.EjIsquiotibiales;
import clases_de_apyo.Ejercicio_gym.EjPecho;
import clases_de_apyo.Ejercicio_gym.EjTriceps;
import gestorbd.GestorBD;
import interfaces_graficas.Login;


public class Main {

	public static void main(String[] args) {

		GestorBD gestor = new GestorBD();
		gestor.borrarDatos();
		gestor.borrarBBDD();
		gestor.crearBBDD();
		new Login(gestor);
		
		List<Ejercicio>ejercicios = new ArrayList<>();

		
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
