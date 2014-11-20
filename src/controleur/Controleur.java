package controleur;

import java.util.HashMap;

import vue.PuzzleView;
import modele.Agent;

public class Controleur {

	//***************************************************
	//ATTRIBUTS
	//***************************************************
	private HashMap<Integer, Agent> agents;
	private PuzzleView puzzleView;

	//***************************************************
	//METHODES
	//***************************************************
	public Controleur(int nbLignes, int nbColonnes, int nbAgents){
		this.setAgents(new HashMap<Integer, Agent>());
		for(int i=0; i<nbAgents; i++){
			//this.getAgents().put(i+1111, new Agent())
		}
		this.setPuzzleView(new PuzzleView(nbLignes, nbColonnes));
	}
	
	//***************************************************
	//GETTERS AND SETTERS
	//***************************************************
	public HashMap<Integer, Agent> getAgents() {
		return agents;
	}

	public void setAgents(HashMap<Integer, Agent> agents) {
		this.agents = agents;
	}

	public PuzzleView getPuzzleView() {
		return puzzleView;
	}

	public void setPuzzleView(PuzzleView puzzleView) {
		this.puzzleView = puzzleView;
	}
}
