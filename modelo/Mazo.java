package ar.edu.unlu.Zombie.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
//import java.util.List;

public class Mazo {
	private Stack<Carta> mazo = new Stack<Carta>();

	public Mazo() {
		cargarCartas();
	}

//--------------------------------------------------------------------------------------------------
	public void cargarCartas() {
		for (int i = 1; i <= 12; i++) { // for para numeros
			Tipo[] valores = Tipo.values();// paso valores de enum tipo a array valores
			for (Tipo tipoCarta : valores) {
				Carta cartaMazo = new Carta(tipoCarta, i);
				mazo.add(cartaMazo);
			}
		}
		Carta comodin = new Carta(); // comodin no tiene valor ni numero
		mazo.add(comodin); // carta nro 49
	}

	// dar una carta
	public Carta darCarta(Carta carta) {
		return carta;
	}

	// dar las cartas a los jugadores hasta que se acabe el mazo
	public void repartir(ArrayList<Jugador> Jugadores) {
		Integer i = 0; // jugadores
		while (mazo.size() != 0) {
			Jugadores.get(i).agregarCartaAMano(mazo.pop());
			if (i.equals(Jugadores.size()-1)) {
				i = 0;
			} else {
				i++;
			}
		}
	}

	// mezclar
	public void mezclar() {
		Collections.shuffle(mazo);
		
	}

}
