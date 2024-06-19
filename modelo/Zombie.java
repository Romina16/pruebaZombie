package ar.edu.unlu.Zombie.modelo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

//import ar.edu.unlu.Zombie.controlador.Controlador;
import ar.edu.unlu.Zombie.observador.Observable;
import ar.edu.unlu.Zombie.observador.Observador;

public class Zombie implements Observable {
	// private Queue <Jugador> turnosJugadores = new LinkedList<Jugador>();// cola
	// para manejar turnos
	private ArrayList<Jugador> misJugadores = new ArrayList<Jugador>();
	private Mazo mazo;
	private ArrayList<Observador> observadores;

	public Zombie() {
		mazo = new Mazo();
		misJugadores = new ArrayList<Jugador>();
		observadores = new ArrayList<Observador>();
	}

//------------------------------------------------------------------------------
	// inicio
	public void inicio() {
		Zombie juego = new Zombie();
		this.comienzoRyD();
		this.empezarJuego();
		
	}


	public void agregarJugador(String nombre) {
		misJugadores.add(new Jugador(nombre));
		this.notificar(Eventos.AGREGAR_JUGADOR);
	}

//Repartir y Descartar
	public void comienzoRyD() {
		this.mazo = new Mazo();
		this.mazo.mezclar();
		mazo.repartir(this.misJugadores);	
		this.notificar(Eventos.DESCARTE_INICIAL_TERMINADO);
	}
	
	public ArrayList<ArrayList<Carta>> descarte(){
		ArrayList<ArrayList<Carta>> descartes = new ArrayList<ArrayList<Carta>>();
		for (Jugador j : this.misJugadores) {
			descartes.add(j.descarteInicial());
			System.out.println("dentro ");
		}
		return descartes;
	}
	
	/*
	 * System.out.println(j.manoJugador()); System.out.println("DESCARTE DE : " +
	 * j.getNombre()); ArrayList<Carta> arrayPruebaDescarte = j.descarteInicial();
	 * mostrarDescarte(arrayPruebaDescarte);
	 * System.out.println("mano del jugador antes");
	 * System.out.println(j.manoJugador());
	 * j.eliminarDescarteInicial(arrayPruebaDescarte);
	 * System.out.println("mano del jugador despues");
	 * System.out.println(j.manoJugador());
	 * System.out.println("--------------------------------------"); } return
	 * descartes; }
	 */
	private void empezarJuego() {
	
	
}

	public void jugar() {
		this.mazo = new Mazo();
		this.mazo.mezclar();
		mazo.repartir(misJugadores);

		for (Jugador j : misJugadores) {
			System.out.println(j.manoJugador());
			System.out.println("DESCARTE DE : " + j.getNombre());
			ArrayList<Carta> arrayPruebaDescarte = j.descarteInicial();
			mostrarDescarte(arrayPruebaDescarte);
			System.out.println("mano del jugador antes");
			System.out.println(j.manoJugador());
			j.eliminarDescarteInicial(arrayPruebaDescarte);
			System.out.println("mano del jugador despues");
			System.out.println(j.manoJugador());
			System.out.println("--------------------------------------");
		}
		// ---------------------------------------------------------------

		// --------------------MANEJO DE TURNOS----------------------
		manejoRonda();
	}

//------------------------------------------------------------------------------
	@Override
	public void addObserver(Observador observador) {
		observadores.add(observador);
	}

	@Override
	public void notificar(Object evento) {
		for (Observador o : this.observadores) {
			o.actualizar(evento, this);
		}
	}

	public List<Jugador> listaJugadores() {
		return this.misJugadores;
	}

	// ------------------------------------------------------------------------------

	// INICIARLIZAR TURNOS retorna un array con jugadores
	public ArrayList<Jugador> inicializarTurnos(ArrayList<Jugador> jugadores) {
		ArrayList<Jugador> turnos = new ArrayList<Jugador>();
		for (int i = 0; i < jugadores.size(); i++) {
			turnos.add(jugadores.get(i));
		}
		// Collections.shuffle(turnos);
		return turnos;
	}

	// MANEJO DE TURNOS EN UNA RONDA
	public void manejoRonda() {
		ArrayList<Jugador> turnos = inicializarTurnos(this.misJugadores);
		ArrayList<Jugador> turnos2;
		int i = 0;
		int j = i + 1;
		while (turnos.size() > 1) { // mientras haya un jugador
			// le doy turno a un jugador
			if (i == turnos.size() - 1) {
				j = 0;
			}
			turno(turnos.get(i), turnos.get(j), turnos); // hace su jugada TURNO
			// constrolo JUGADOR SIGUE en juego
			if (turnos.get(i).cantidadCartas() == 0) {
				turnos.get(i).setGanador();
				turnos.remove(i);
			}
			// paso al siguiente
			if (i == turnos.size() - 1) {
				i = -1;
			}
			i++;
			j++;
		}
		// hay un solo jugador po lo que se termina el juego
		System.out.println("fin del juego");
		System.out.println("El jugador perdedor es: " + turnos.get(0).getNombre());
	}

	// MOSTRAR MANO DEL DE A LADO mano.size
	public String mostrar(Integer cantidad) {
		String s = "[ ";
		for (Integer i = 0; i < cantidad; i++) {
			s = s + i.toString() + ", ";
		}
		s = s + " ]";
		return s;
	}

	// MANEJO DE TURNOS
	public void turno(Jugador jugadorPide, Jugador jugadorDa, ArrayList<Jugador> turnos) {
		System.out.println("----------------------------------");
		System.out.println("Turno de: " + jugadorPide.getNombre());
		// pedir carta
		System.out.println("El jugador a su derecha tiene: " + jugadorDa.cantidadCartas().toString() + " cartas");
		System.out.println("Ingrese la posicion de la carta que desea tomar");
		System.out.println(mostrar(jugadorDa.cantidadCartas()));
		Scanner sc = new Scanner(System.in);
		Integer posicion = sc.nextInt();
		// validacion de la posicion
		while (posicion < 0 || posicion > jugadorDa.cantidadCartas() - 1) {
			System.out.println("Posicion invalida");
			System.out.println("Vuelva a ingresar un valor");
			Scanner sc2 = new Scanner(System.in);
			posicion = sc.nextInt();
		}
		pedirCarta(jugadorPide, jugadorDa, posicion);
		// -----------------------------------
		// termino el turno
		// chequeo de turno

	}

	// MOSTRAR MANO
	public void mostrarMano(ArrayList<Carta> mano) {
		System.out.println("Mano de: ");
		for (Carta c : mano) {
			System.out.println("Carta: " + c.retornaValor());
		}
		System.out.println("Cantidad de Cartas en mano " + mano.size());
	}

	// MOSTRAR DESCARTE
	public void mostrarDescarte(ArrayList<Carta> descarte) {
		System.out.println("Descarte: ");
		for (Carta c : descarte) {
			System.out.println("CARTA DESCARTADA: " + c.retornaValor());
		}
		System.out.println("Cantidad de Cartas DESCARTADAS: " + descarte.size());
	}

	// PEDIR CARTA
	public void pedirCarta(Jugador jugadorPide, Jugador jugadorDa, Integer posicion) {
		Carta cartaRecibida = jugadorDa.removerCarta(posicion);
		Integer posicionCartaPareja = -1;
		if (!(jugadorPide.esComodin(cartaRecibida))) {
			posicionCartaPareja = jugadorPide.encontrarPareja(cartaRecibida);
			System.out.println(cartaRecibida.retornaValor());
			System.out.println(posicionCartaPareja.toString());

			if (posicionCartaPareja == -1) { // no hay pareja
				jugadorPide.agregarCartaAMano(cartaRecibida);
				System.out.println("Recibiste " + cartaRecibida.retornaValor()
						+ " pero NO lograste formar una pareja. Se AGREGO la carta a tu mano ");
			} else {
				System.out.println("Recibiste " + cartaRecibida.retornaValor() + " y FORMASTE UNA PAREJA con tu carta "
						+ jugadorPide.getMano().get(posicionCartaPareja).retornaValor());
				jugadorPide.removerCarta(posicionCartaPareja); // revisar
			}

		} else {
			System.out.println("Recibiste un COMODIN. Se agrego a tu mano"); //agregar la carta!!
		}
		System.out.println("Fin del turno");
		System.out.println("-----------------------------------------");
	}

}
