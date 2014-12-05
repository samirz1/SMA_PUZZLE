package modele;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StrategieMeilleurChemin extends Strategie{

	public StrategieMeilleurChemin() {}
	
	@Override
	//Utilisation d'un algorithme de parcours en largeur
	public synchronized void executionStrategie() {
		
		//*************************************************************************
		//INITIALISATIONS
		//*************************************************************************
		Agent agent = getAgent();
		Puzzle puzzle = agent.getPuzzle();
		boolean trouve = false;
		boolean existeChemin = true;
		
		//TODO
		//TEST arrivée => x = 0 y = 3 / départ x= 2 y = 0
		/*
		if(puzzle.isCaseEmpty(2, 0)){
			agent.setPositionCourante(2, 0);
			agent.setxArrive(0);
			agent.setyArrive(3);
		}
		else{
			System.out.println("TEST RATE");
		}
		*/
		
		
		//On récupère la position initiale de l'agent
		int xCourant = agent.getxCourant();
		int yCourant = agent.getyCourant();
		//On récupère le but de l'agent
		int xArrivee = agent.getxArrive();
		int yArrivee = agent.getyArrive();
		
		//Si l'agent est déjà sur sa case but
		if(xCourant == xArrivee && yCourant == yArrivee){
			trouve = true;
			System.out.println("DEJA SUR LA CASE BUT");
			agent.setActivated(false);
		}
		//Si la case d'arrivée est prise, il n'y a pas de chemin pour y accéder
		else if(!puzzle.isCaseEmpty(xArrivee, yArrivee)){
			existeChemin = false;
			System.out.println("CASE BUT PRISE");
			agent.setActivated(false);
		}
		
		//********************************************************************************
		//Démarrage du parcours en largeur
		//********************************************************************************
		if(!trouve && existeChemin){
			int nbCases = puzzle.getNbColonnes() * puzzle.getNbLignes();
			//On garde une liste des cases à explorer
			Set<Integer> explorees = new HashSet<Integer>();
			List<Integer> casesAExplorer = new ArrayList<Integer>();
			
			//Case précédemment rencontrée dans le chemin pour chaque case (num case / num precedente)
			HashMap<Integer, Integer> precedente = new HashMap<Integer, Integer>();

			for(int i = 0; i < nbCases; i++){
				//On initialise à null toutes les cases précédentes
				precedente.put(i, null);
			}
			
			Integer caseUp, caseDown, caseLeft, caseRight;
			

			//On initialise la liste des cases à explorer
			caseUp = freeCaseUp(xCourant, yCourant, precedente, explorees);
			caseDown = freeCaseDown(xCourant, yCourant, precedente, explorees);
			caseLeft = freeCaseLeft(xCourant, yCourant, precedente, explorees);
			caseRight = freeCaseRight(xCourant, yCourant, precedente, explorees);
			
			if(caseUp != null){
				casesAExplorer.add(caseUp);
			}
			if(caseDown != null){
				casesAExplorer.add(caseDown);
			}
			if(caseRight != null){
				casesAExplorer.add(caseRight);		
			}
			if(caseLeft != null){
				casesAExplorer.add(caseLeft);
			}
			
			trouve = false;
			Integer caseArrivee = puzzle.getNumeroCase(xArrivee, yArrivee);
			Integer caseTest;
			Integer[] coordTest;
			//Tant qu'il reste des cases à explorer et que la case but n'a pas été atteinte
			while (casesAExplorer.size() > 0 && !trouve){
				
				//On test si la case est la case but
				caseTest = casesAExplorer.remove(0);
				if(caseTest == caseArrivee){
					trouve = true;
				}
				else{
					explorees.add(caseTest);
					coordTest = puzzle.getCaseFromNumero(caseTest);
					//On explore les cases accessibles à partir de la case testée
					caseUp = freeCaseUp(coordTest[0], coordTest[1], precedente, explorees);
					caseDown = freeCaseDown(coordTest[0], coordTest[1], precedente, explorees);
					caseLeft = freeCaseLeft(coordTest[0], coordTest[1], precedente, explorees);
					caseRight = freeCaseRight(coordTest[0], coordTest[1], precedente, explorees);
					
					if(caseUp != null){
						casesAExplorer.add(caseUp);
					}
					if(caseDown != null){
						casesAExplorer.add(caseDown);
					}
					if(caseRight != null){
						casesAExplorer.add(caseRight);		
					}
					if(caseLeft != null){
						casesAExplorer.add(caseLeft);
					}
				}
			}
			
			//On affiche le chemin s'il y en a un
			if(trouve){
			
				System.out.println("ARRIVE mon but était x=" + xArrivee + " y=" + yArrivee);
				System.out.println("Mon départ était x=" + xCourant + " y=" + yCourant);
				System.out.println("REAFFICHAGE DU CHEMIN : ");
				
				int numCaseArrivee = puzzle.getNumeroCase(xArrivee, yArrivee);
				int numCaseDepart = puzzle.getNumeroCase(xCourant, yCourant);

				List<Integer> chemin = new ArrayList<Integer>();
				
				int numCaseCourante = numCaseArrivee;
				chemin.add(0, numCaseCourante);
				
				while(numCaseCourante != numCaseDepart){
					numCaseCourante = precedente.get(numCaseCourante);
					chemin.add(0, numCaseCourante);
				}
				
				Integer[] coord;
				try {
					agent.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				coord = puzzle.getCaseFromNumero(chemin.get(1));
				
				agent.setPositionCourante(coord[0], coord[1]);
				
				System.out.println("BOUGE");
				
				try {
					agent.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
			else{
				System.out.println("NON TROUVE");
				agent.setActivated(false);
			}
			
			
		}
	}
	
	private Integer freeCase(int xCourant, int yCourant, int xSuivant, int ySuivant, HashMap<Integer, Integer> precedente, Set<Integer> explorees){
		
		Agent agent = getAgent();
		Puzzle puzzle = agent.getPuzzle();
		
		//Test de la case
		
		int numCase = puzzle.getNumeroCase(xSuivant, ySuivant);
		if(puzzle.isCaseEmpty(xSuivant, ySuivant) && !explorees.contains(numCase)){
			int numPrecedente = puzzle.getNumeroCase(xCourant, yCourant);
			//On indique la case prédédente dans le chemin
			precedente.put(numCase, numPrecedente);
			return numCase;
		}
		else {
			return null;
		}
	}
	
	//Test si la case en haut est libre et si elle n'est pas dans les cases déjà explorées
	//Retourne le numéro de la case ou null si elle n'est pas à explorer
	public Integer freeCaseUp(int xCourant, int yCourant, HashMap<Integer, Integer> precedente, Set<Integer> explorees){
		
		int xSuivant, ySuivant;
		
		//Test de la case en haut
		xSuivant = xCourant; ySuivant = yCourant - 1;
		
		return freeCase(xCourant, yCourant, xSuivant, ySuivant, precedente, explorees);
	}
	
	
	
	//Test si la case en haut est libre et si elle n'est pas dans les cases déjà explorées
	//Retourne le numéro de la case ou null si elle n'est pas à explorer
	public Integer freeCaseDown(int xCourant, int yCourant, HashMap<Integer, Integer> precedente, Set<Integer> explorees){
		
		int xSuivant, ySuivant;
		
		//Test de la case en haut
		xSuivant = xCourant; ySuivant = yCourant + 1;
		
		return freeCase(xCourant, yCourant, xSuivant, ySuivant, precedente, explorees);
	}
	
	//Test si la case en haut est libre et si elle n'est pas dans les cases déjà explorées
	//Retourne le numéro de la case ou null si elle n'est pas à explorer
	public Integer freeCaseRight(int xCourant, int yCourant, HashMap<Integer, Integer> precedente, Set<Integer> explorees){
		
		int xSuivant, ySuivant;
		
		//Test de la case en haut
		xSuivant = xCourant + 1; ySuivant = yCourant;
		
		return freeCase(xCourant, yCourant, xSuivant, ySuivant, precedente, explorees);
	}
	
	//Test si la case en haut est libre et si elle n'est pas dans les cases déjà explorées
	//Retourne le numéro de la case ou null si elle n'est pas à explorer
	public Integer freeCaseLeft(int xCourant, int yCourant, HashMap<Integer, Integer> precedente, Set<Integer> explorees){
		
		int xSuivant, ySuivant;
		
		//Test de la case en haut
		xSuivant = xCourant - 1; ySuivant = yCourant;
		
		return freeCase(xCourant, yCourant, xSuivant, ySuivant, precedente, explorees);
	}
	
}
