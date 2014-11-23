package modele;

import java.util.Observable;

public class Case extends Observable {

	// ***************************************************
	// ATTRIBUTS
	// ***************************************************
	private int numero;
	private int x;
	private int y;
	private Agent agent;

	// ***************************************************
	// METHODES
	// ***************************************************
	public Case(int numero, int x, int y) {
		super();
		this.numero = numero;
		this.x = x;
		this.y = y;
	}

	public boolean estLibre(){
		return this.getAgent() == null;
	}
	
	@Override
	public void notifyObservers() {
		super.notifyObservers();		
	}
	
	// ***************************************************
	// GETTERS AND SETTERS
	// ***************************************************
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
		this.notifyObservers();
	}

}
