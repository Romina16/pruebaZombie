package ar.edu.unlu.Zombie.controlador;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unlu.Zombie.modelo.Carta;
import ar.edu.unlu.Zombie.modelo.Eventos;
import ar.edu.unlu.Zombie.modelo.Jugador;
import ar.edu.unlu.Zombie.modelo.Zombie;
import ar.edu.unlu.Zombie.observador.Observable;
import ar.edu.unlu.Zombie.observador.Observador;
import ar.edu.unlu.Zombie.vista.VistaConsola;

public class Controlador implements Observador {
	private VistaConsola vista;
	private Zombie modelo;

	public Controlador(VistaConsola vista, Zombie modelo) {
		this.vista = vista;
		this.modelo = modelo;
		this.vista.setControlador(this);
		this.modelo.addObserver(this);
	}
//--------------------------------------------------------------------------------	
	// metodos que llaman al modelo

	// INICIAR
	public void comenzarJuego() {
		this.modelo.inicio();
	}

	// AGREGAR JUGADOR
	public void agregarJugador(String nombre) {
		this.modelo.agregarJugador(nombre);
	}
	//MOSTRAR LISTA DE JUGADORES
	public void mostrarJugadores() {
		List<Jugador> jugadores = this.modelo.listaJugadores();
		this.vista.mostrarJugadores(jugadores);
	}

//-------------------------------------------------------------------------------	
	@Override
	public void actualizar(Object evento, Observable observable) {
		if (evento instanceof Eventos) {
			switch ((Eventos) evento) {
			case AGREGAR_JUGADOR:
				List<Jugador> jugadores = this.modelo.listaJugadores();
				this.vista.mostrarJugadores(jugadores);
				break;
			case DESCARTE_INICIAL_TERMINADO:
				ArrayList<ArrayList<Carta>> descartesIniciales = this.modelo.descarte();
				this.vista.mostrarDescarte(descartesIniciales);
				break;
			}

		}

	}

	public void jugar() {
		modelo.jugar(); //cambio x jugar
	}
}
