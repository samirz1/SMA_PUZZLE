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
		for(int y=0; y<nbLignes; y++){
			for(int x=0; x<nbColonnes; x++)
				this.getCases().put(x+(y*(nbColonnes)), new Case(x+(y*(nbColonnes)), y, x));
		}
	}
	
	public Case getCaseXY(int x, int y){
		if(x >= 0 && y >=0 && x < nbColonnes && y < nbLignes)
			return this.getCases().get(x+(y*(nbColonnes)));
		else 
			return null;
	}
	
	public int getNumeroCase(int x, int y){
		return x+(y*(nbColonnes));
	}
	
	//TODO marche ?
	public Integer[] getCaseFromNumero(int numCase){
		Integer[] retour = new Integer[2];
		retour[1] = (int) numCase / nbColonnes;
		retour[0] = numCase % nbColonnes;
		return retour;
	}
	
	//TODO vérifier que marche parfaitement
	//Retourne false si la case est occupée ou si la case n'existe pas
	public boolean isCaseEmpty(int x, int y){
		if(getCaseXY(x, y) != null){
			if(getCaseXY(x, y).getAgent() == null){
				return true;
			}
			else{
				return false;
			}
		}
		else{
			return false;
		}
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
