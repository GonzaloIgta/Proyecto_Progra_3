package main;

import gestorbd.GestorBD;
import interfaces_graficas.Login;


public class Main {

	public static void main(String[] args) {

		GestorBD gestor = new GestorBD();
		gestor.crearBBDD();
		new Login(gestor);
		
	}

}
