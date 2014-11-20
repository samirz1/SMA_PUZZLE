package controleur;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import modele.Agent;
import modele.Puzzle;
import vue.PuzzleView;

public class Controleur {

	public static void main(String[] args){
		controleur = new Controleur(5, 5, 4);
		
	}
	
	//***************************************************
	//ATTRIBUTS
	//***************************************************
	private HashMap<Integer, Agent> agents;
	private PuzzleView puzzleView;
	private static Controleur controleur;

	//***************************************************
	//METHODES
	//***************************************************
	public Controleur(int nbColonnes, int nbLignes, int nbAgents){
		//CREATION DU PUZZLE
		Puzzle puzzle = new Puzzle(nbColonnes, nbLignes);
		//CREATION DES AGENTS
		this.setAgents(new HashMap<Integer, Agent>());
		Random r = new Random();
		int xDepart, yDepart, xArrive, yArrive;
		for(int i=0; i<nbAgents; i++){
			do{
				xDepart = r.nextInt(nbColonnes-1);
				yDepart = r.nextInt(nbLignes-1);
			}while(this.caseDepartPrise(xDepart, yDepart));
			do{
				xArrive = r.nextInt(nbColonnes-1);
				yArrive = r.nextInt(nbLignes-1);
			}while(this.caseArrivePrise(xArrive, yArrive));
			this.getAgents().put(i+1111, new Agent(xDepart, yDepart, xArrive, yArrive, i+1111, this.getAgents(), puzzle));
		}
		//CREATION DU PUZZLEVIEW
		this.setPuzzleView(new PuzzleView(puzzle));
	}
	
	public boolean caseDepartPrise(int x, int y){
		boolean prise = false;
		Iterator<Integer> iter = this.getAgents().keySet().iterator();
		while(iter.hasNext()){
			Agent a = this.getAgents().get(iter.next());
			if(a.getxCourant()==x && a.getyCourant()==y){
				prise = true;
				break;
			}
		}
		return prise;
	}
	
	public boolean caseArrivePrise(int x, int y){
		boolean prise = false;
		Iterator<Integer> iter = this.getAgents().keySet().iterator();
		while(iter.hasNext()){
			Agent a = this.getAgents().get(iter.next());
			if(a.getxArrive()==x && a.getyArrive()==y){
				prise = true;
				break;
			}
		}
		return prise;
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
