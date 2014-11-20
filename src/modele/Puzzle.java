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
	public Puzzle(int nbColonnes, int nbLignes) {
		super();
		this.nbColonnes = nbColonnes;
		this.nbLignes = nbLignes;		
		//CREATION DES CASES
		this.setCases(new HashMap<Integer, Case>());
		for(int i=0; i<nbLignes; i++){
			for(int j=0; j<nbColonnes; j++)
				this.getCases().put(j+(i*(nbLignes)), new Case(j+(i*(nbLignes)), i, j));
		}
	}
	
	public Case getCaseXY(int x, int y){
		return this.getCases().get(x+(y*(nbColonnes)));
	}
	
	public void afficher(){
		Case c;
		for(int i=0; i<nbLignes; i++){
			for(int j=0; j<nbColonnes; j++){
				c = this.getCases().get(j+(i*nbColonnes));
				System.out.print(c.getNumero() + ":");
				if(c.getAgent() == null) System.out.print("X  ");
				else System.out.print("AGENT  ");
			}
			System.out.println();
		}
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
