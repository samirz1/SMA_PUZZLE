package controleur;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import modele.Agent;
import modele.Case;
import modele.Puzzle;
import modele.StrategieCheminDirect;
import modele.StrategieMeilleurChemin;
import vue.PuzzleView;

public class Controleur {

	public static void main(String[] args){
		new Controleur(5, 5, 4);
		
	}
	
	//***************************************************
	//ATTRIBUTS
	//*************************************************** 
	private HashMap<Integer, Agent> agents;
	private PuzzleView puzzleView;

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
			}while(this.caseCourantePrise(xDepart, yDepart));
			do{
				xArrive = r.nextInt(nbColonnes-1);
				yArrive = r.nextInt(nbLignes-1);
			}while(this.caseArrivePrise(xArrive, yArrive));
			this.getAgents().put(i+1111, new Agent(new StrategieMeilleurChemin(),xDepart, yDepart, xArrive, yArrive, i+1111, this.getAgents(), puzzle));
		}
		//CREATION DU PUZZLEVIEW
		this.setPuzzleView(new PuzzleView(puzzle));
		puzzle.afficher();
		
		//OBSERVER OBSERVABLE
		Iterator<Integer> iter = puzzle.getCases().keySet().iterator();
		while(iter.hasNext()){
			Case c = puzzle.getCases().get(iter.next());
			c.addObserver(puzzleView);
		}
		
		//TODO 
		// ON test un agent actif
		/*
		agents.get(1112).setPositionCourante(0, 1);
		agents.get(1113).setPositionCourante(3, 1);
		agents.get(1114).setPositionCourante(0, 2);
		*/
		
		agents.get(1113).setPositionCourante(1, 0);
		agents.get(1114).setPositionCourante(2, 1);
		
		//TODO
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		agents.get(1111).setActivated(true);
		agents.get(1112).setActivated(true);
		
		
		if(puzzle.isCaseEmpty(1, 2)){
			agents.get(1111).setPositionCourante(1, 2);
			agents.get(1111).setxArrive(3);
			agents.get(1111).setyArrive(2);
		}
		else{
			System.out.println("TEST RATE");
		}
		
		if(puzzle.isCaseEmpty(2, 3)){
			agents.get(1112).setPositionCourante(2, 3);
			agents.get(1112).setxArrive(3);
			agents.get(1112).setyArrive(0);
		}
		else{
			System.out.println("TEST RATE");
		}
		
		agents.get(1111).start();
		agents.get(1112).start();
		
	}
	
	public boolean caseCourantePrise(int x, int y){
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
