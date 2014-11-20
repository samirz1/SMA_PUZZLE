package modele;

import java.util.HashMap;

public class Puzzle {

	//***************************************************
	//ATTRIBUTS
	//***************************************************
	private HashMap<Integer, Case> cases;
	private int nbLignes;
	private int nbColonnes;

	//***************************************************
	//METHODES
	//***************************************************
	public Puzzle(int nbLignes, int nbColonnes) {
		super();
		this.nbLignes = nbLignes;
		this.nbColonnes = nbColonnes;
		this.setCases(new HashMap<Integer, Case>());
	}
	
	//***************************************************
	//GETTERS AND SETTERS
	//***************************************************
	public HashMap<Integer, Case> getCases() {
		return cases;
	}
	public void setCases(HashMap<Integer, Case> cases) {
		this.cases = cases;
	}
	public int getNbLignes() {
		return nbLignes;
	}
	public void setNbLignes(int nbLignes) {
		this.nbLignes = nbLignes;
	}
	public int getNbColonnes() {
		return nbColonnes;
	}
	public void setNbColonnes(int nbColonnes) {
		this.nbColonnes = nbColonnes;
	}
	
}
