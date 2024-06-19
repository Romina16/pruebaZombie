package ar.edu.unlu.Zombie.vista;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ar.edu.unlu.Zombie.controlador.Controlador;
import ar.edu.unlu.Zombie.modelo.Carta;
import ar.edu.unlu.Zombie.modelo.Jugador;

public class VistaConsola implements IVista {
	private Controlador controlador;
	public VistaConsola vista;

// ----------------------------------------------------------------	

	// comnienzo del juego
	public void comenzarJuego() {
		int opcion = 1;
		while (opcion != 0) {

		}
	}

	// mostrar menu
	public int mostrarMenu() {
		int opcion = -1;
		while (opcion < 0 || opcion > 3) {
			System.out.println("---------------------------------");
			System.out.println("--            Zombie           --");
			System.out.println("---------------------------------");
			System.out.println();
			System.out.println("---------------------------------");
			System.out.println("--             MENU            --");
			System.out.println("---------------------------------");
			System.out.println("--           Opciones          --");
			System.out.println("---------------------------------");
			System.out.println("--    Seleccione una opcion    --");
			System.out.println(" 1)  Agregar Jugador             ");
			System.out.println(" 2)  Lista de Jugadores          ");
			System.out.println(" 3)  Jugar                       ");
			System.out.println();
			System.out.println(" 0)  Salir del juego             ");
			System.out.println("---------------------------------");
			Scanner sc = new Scanner(System.in);
			opcion = sc.nextInt();
		}
		return opcion;
	}

	@Override
	public void iniciar() {
		System.out.println("inicio el juego");
		int opcion = -1;
		while (opcion != 0) {
			opcion = mostrarMenu();
			switch (opcion) {
			case 1:
				// AGREGAR JUGADOR
				this.agregarJugador();
				System.out.println("Jugador agregado");
				esperarEnter();
				break;
			case 2:
				mostrarJugadores();
				esperarEnter();
				break;
			case 3:
				System.out.println("opcion 4 jugar");
				jugar();
				break;
			}
			
		}
		System.out.println("Salio del juego");
	}

private void jugar() {
		controlador.jugar();
	}
public void mostrarDescarte( ArrayList<ArrayList<Carta>> descartesIniciales) {
	Integer i = 1;
	for (ArrayList<Carta> arregloDescarte: descartesIniciales) {
		System.out.println("Descarte de Jugador "+ i.toString());
		for(Carta cartaDescarteJugador: arregloDescarte ) {
			System.out.println(cartaDescarteJugador.retornaValor());
		}
		i++;
	}
}

// CONFIGURACIONES 
	// ESPERAR ENTER
	public void esperarEnter() {
		System.out.println("Presione ENTER para continuar...");
		Scanner sc = new Scanner(System.in);
		String espera = sc.nextLine();
	}

	// AGREGAR JUGADOR
	private String agregarJugador() {
		String nombre = "";
		while (nombre.equals("")) {
			System.out.println("---------------------------------");
			System.out.println("        Agregando Jugador        ");
			System.out.println("---------------------------------");
			System.out.println("  Ingrese el nombre del Jugador  ");
			System.out.println("---------------------------------");
			Scanner sc = new Scanner(System.in);
			nombre = sc.nextLine();
			this.controlador.agregarJugador(nombre);
		}
		return nombre;
	}
	
	//MOSTRAR LISTA DE JUGADORES
	public void mostrarJugadores() {
		this.controlador.mostrarJugadores();
	}

// ----------------------------------------------------------------
	// set controlador mvc
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	// MOSTRAR JUGADORES
	public void mostrarJugadores(List<Jugador> jugadores) {
		for (Jugador j : jugadores) {
			System.out.println("Jugador: " + j.getNombre());
		}
	}

	// MOSTRAR JUGADORES
	public void mostrarCartas(List<Carta> arrayCarta) {
		for (Carta c :  arrayCarta) {
			System.out.println("Carta " + c.retornaValor());
		}
	}

}


