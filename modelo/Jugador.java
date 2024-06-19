package ar.edu.unlu.Zombie.modelo;

import java.util.ArrayList;

public class Jugador {
	private String nombre;
	private ArrayList<Carta> mano = new ArrayList<Carta>();
	private Boolean turno;
	private Boolean ganador;

	public Jugador(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Carta> getMano() {
		return mano;
	}

	public String getNombre() {
		return nombre;
	}

	public Boolean getTurno() {
		return turno;
	}

	public void setTurno(Boolean turno) {
		this.turno = turno;
	}

	public Boolean getGanador() {
		return ganador;
	}

	public void setGanador() {
		this.ganador = true;
	}

//---------------------------------------------------------------------------------
//juego

	// SOLO SE USA CUANDO SE agrega
	public void agregarCartaAMano(Carta cartaAgregada) {
		mano.add(cartaAgregada);
	}
	// retorna la carta eliminada o null si no pudo eliminar 
	public Carta removerCarta(int posicion) {
		return posicion > 0 || posicion < mano.size() ? mano.remove(posicion) : null;
	}

	// CONTROLA SI ES COMODIN
	public Boolean esComodin(Carta cartaAgregar) {
		return cartaAgregar.getTipo() == null ;
	}// return cartaAgregar.gettipo == null 
	// responsabilidad de la carta
	// juntar pareja
	// retorna la posicion donde encuentra el primer par de la carta nueva
	public Integer encontrarParejaBORRAR(Carta cartaNueva) {
		int b = -1;
		int i = 1;
		while (i <= mano.size() && (b != -1)) {
			if (mano.get(i).getNumero() == cartaNueva.getNumero()) {
				b = i;
				i++;
			}
		}
		return b;
	}
	// DESCARTE INICIAL retorna un array para luego mostrar
	public ArrayList descarteInicialAAA() {
		int i = 0;
		ArrayList<Carta> parejasIniciales = new ArrayList<Carta>(); // para mostrar descartes iniciales
		boolean pareja = false;
		while (i <= mano.size()) {
			int j = i + 1;
			while (j <= mano.size() && !(pareja)) {
				if (mano.get(i).getNumero() == mano.get(j).getNumero()) {
					pareja = true;
					parejasIniciales.add(mano.remove(i));
					parejasIniciales.add(mano.remove(j));
				}
				j++;
			}
			pareja = false;
			i++;
		}
		return parejasIniciales;
	}
	
	public Integer cantidadCartas() {
		return mano.size();
	}

	public String manoJugador() {
		String s = "" + this.nombre + " ";
		for (Carta c : mano) {
			s = s + c.retornaValor() + "\n";
		}
		return s;
	}
	// buscar una carta si se encuentra en el descarte en descarte
		public boolean buscarDescarte(Carta c, ArrayList<Carta> parejasIniciales) {
			int i = 0;
			boolean encontrado = false;
			while (i < parejasIniciales.size()) {
				if (parejasIniciales.get(i).equals(c)) {
					encontrado = true;
				}
				i++;
			}
			return encontrado;
		}
		// juntar pareja
		// retorna la posicion donde encuentra el primer par de la carta nueva
		public Integer encontrarPareja(Carta cartaNueva) {
			Integer posicion = -1;
			int i = 0;
			while (i < mano.size() && (posicion == -1)) {
				if (mano.get(i).getNumero() == cartaNueva.getNumero()) {
					posicion = i;
				}
				i++;
			}
			return posicion;
		}

		// juntar pareja DESCARTE INICIAL
		// LO MISMO PERO BUSCA A PARTIR DE UNA POSICION
		// retorna la posicion donde encuentra el primer par de la carta nueva
		public int encontrarPareja(Carta cartaNueva, int desde) {
			int posicion = -1;
			int i = desde;
			if (!(esComodin(cartaNueva))) {
			while (i < mano.size() && (posicion == -1)) {
				if (mano.get(i).getNumero() == cartaNueva.getNumero()) {
					posicion = i;
				}
				i++;
			}
			}
			return posicion;
		}	
	
	//DESCARTE INICIAL
		// busqueda y eliminar al final de i
		public ArrayList<Carta> descarteInicial() {
			int posicionEncontrada = -1;
			ArrayList<Carta> parejasIniciales = new ArrayList<Carta>(); // para mostrar descartes iniciales
			for (int i = 0; i < mano.size() - 1; i++) {
				// si la carta que busco esta en el descarte no la busco
				if (!(buscarDescarte(mano.get(i), parejasIniciales))) {
					posicionEncontrada = this.encontrarPareja(mano.get(i), i + 1);
					if (posicionEncontrada != -1) {
						parejasIniciales.add(mano.get(i));
						parejasIniciales.add(mano.get(posicionEncontrada));
					}
				}
			}
			return parejasIniciales;
		}

		public void eliminarDescarteInicial(ArrayList<Carta> parejasIniciales) {
			int i = 0;
			while (i < parejasIniciales.size()) {
				this.mano.remove(parejasIniciales.get(i));
				i++;
			}
		}
}
