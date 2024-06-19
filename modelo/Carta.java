package ar.edu.unlu.Zombie.modelo;

public class Carta {
	private Tipo Tipo;
	private int Numero;

	public Carta(Tipo tipo, int Numero) {//constructor normal
		this.Tipo = tipo;
		this.Numero = Numero;
	}
	public Carta() { //constructor comodin
		this.Tipo = null;
		this.Numero = 0;
	}
//-----------------------------------------------------------------	
	public Tipo getTipo() {
		return Tipo;
	}
	public int getNumero() {
		return Numero;
	}
//-----------------------------------------------------------------
	public String retornaValor() {
		return (this.Tipo == null) ? "Comodin" :
			("Numero: " + this.Numero + " Palo " + this.Tipo);
	}

}
