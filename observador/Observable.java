package ar.edu.unlu.Zombie.observador;

public interface Observable {
	public void addObserver(Observador observador);
	public void notificar(Object evento);
}
