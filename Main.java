package ar.edu.unlu.Zombie;

import ar.edu.unlu.Zombie.controlador.Controlador;
import ar.edu.unlu.Zombie.modelo.Zombie;
import ar.edu.unlu.Zombie.vista.VistaConsola;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Zombie modelo = new Zombie();
		VistaConsola vista = new VistaConsola();
		Controlador controlador = new Controlador(vista,modelo);
		vista.iniciar();
	}
}

