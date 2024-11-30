package main;

import gestorbd.GestorBD;
import interfaces_graficas.Login;


public class Main {

	public static void main(String[] args) {
		new Login();

		GestorBD gestor = new GestorBD();
		gestor.borrarBBDD();
	}

}
