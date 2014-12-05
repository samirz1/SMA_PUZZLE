package modele;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StrategieCheminDirect extends Strategie{

	public StrategieCheminDirect(){}
	
	@Override
	public void executionStrategie() {
		
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
		//Démarrage du parcours en profondeur
		//********************************************************************************
		if(!trouve && existeChemin){
			int nbCases = puzzle.getNbColonnes() * puzzle.getNbLignes();
			//Case précédemment rencontrée dans le chemin pour chaque case (num case / num rencontrée)
			HashMap<Integer, Integer> precedente = new HashMap<Integer, Integer>();
			//Couleurs des cases marqués (numéro de la case / couleur)
			HashMap<Integer, String> couleurs = new HashMap<Integer, String>();
			for(int i = 0; i < nbCases; i++){
				//On initialise à null toutes les cases précédentes
				precedente.put(i, null);
				//On initialise toutes les couleurs à blanc
				couleurs.put(i, "blanc");
			}
			
			//On part de la case courante
			visiterProfondeur(agent, puzzle, xCourant, yCourant, xArrivee, yArrivee, precedente, couleurs);
			
			//Fin du parcours
			//On test si la case a été trouvée
			if(precedente.get(puzzle.getNumeroCase(xArrivee, yArrivee)) != null){
				
				System.out.println("ARRIVE mon but était x=" + xArrivee + " y=" + yArrivee);
				System.out.println("Mon départ était x=" + xCourant + " y=" + yCourant);
				System.out.println("REAFFICHAGE DU CHEMIN : ");
				
				int numCaseArrivee = puzzle.getNumeroCase(xArrivee, yArrivee);
				int numCaseDepart = puzzle.getNumeroCase(xCourant, yCourant);
				Integer numCasePrec = -1;
				List<Integer> chemin = new ArrayList<Integer>();
				
				int numCaseCourante = numCaseArrivee;
				chemin.add(0, numCaseCourante);
				
				while(numCaseCourante != numCaseDepart){
					numCaseCourante = precedente.get(numCaseCourante);
					chemin.add(0, numCaseCourante);
				}
				
				Integer[] coord;
				for(Integer casee : chemin){
					
					try {
						agent.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					coord = puzzle.getCaseFromNumero(casee);
					
					agent.setPositionCourante(coord[0], coord[1]);
				}
				
				System.out.println("FINI");
				//TODO
				agent.setActivated(false);
				
				try {
					agent.sleep(100000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			else{
				System.out.println("NON TROUVE");
			}
			
		}
	}
	
	public boolean visiterProfondeur(Agent agent, Puzzle puzzle, int xCourant, int yCourant, int xArrivee, int yArrivee, HashMap<Integer, Integer> precedente, HashMap<Integer, String> couleurs){
		//On colore la case c en gris
		couleurs.put(puzzle.getNumeroCase(xCourant, yCourant), "gris");
		
		int xSuivant, ySuivant;
		boolean trouve = false;
		
		//------------------------------------------------
		//Pour toute cases accessible à partir de c
		//------------------------------------------------
		//Test de la case en haut
		xSuivant = xCourant; ySuivant = yCourant - 1;
		if(puzzle.isCaseEmpty(xSuivant, ySuivant) && !trouve){
			if(couleurs.get(puzzle.getNumeroCase(xSuivant, ySuivant)).equals("blanc")){
				
				//On ne continu la visite que si on a pas trouvé la case but
				if(xSuivant != xArrivee || ySuivant != yArrivee){
					
					/*
					agent.setPositionCourante(xSuivant, ySuivant);
					try {
						agent.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					*/
					
				
					if(visiterProfondeur(agent, puzzle, xSuivant, ySuivant, xArrivee, yArrivee, precedente, couleurs)){
						trouve = true;
						//TODO vérifier que cela fonctionne et qu'il ne faut pas mettre la ligne suivante avant
						precedente.put(puzzle.getNumeroCase(xSuivant, ySuivant), puzzle.getNumeroCase(xCourant, yCourant));
						
					}
				}
				else{
					trouve = true;
					//TODO vérifier que cela fonctionne et qu'il ne faut pas mettre la ligne suivante avant
					precedente.put(puzzle.getNumeroCase(xSuivant, ySuivant), puzzle.getNumeroCase(xCourant, yCourant));
				}
			}
		}
		//Test de la case en bas
		xSuivant = xCourant; ySuivant = yCourant + 1;
		if(puzzle.isCaseEmpty(xSuivant, ySuivant) && !trouve){
			if(couleurs.get(puzzle.getNumeroCase(xSuivant, ySuivant)).equals("blanc")){
				
				//On ne continu la visite que si on a pas trouvé la case but
				if(xSuivant != xArrivee || ySuivant != yArrivee){
					
					
					/*
					agent.setPositionCourante(xSuivant, ySuivant);
					try {
						agent.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					*/
					
					
					if(visiterProfondeur(agent, puzzle, xSuivant, ySuivant, xArrivee, yArrivee, precedente, couleurs)){
						trouve = true;
						//TODO vérifier que cela fonctionne et qu'il ne faut pas mettre la ligne suivante avant
						precedente.put(puzzle.getNumeroCase(xSuivant, ySuivant), puzzle.getNumeroCase(xCourant, yCourant));
						
					}
				}
				else{
					trouve = true;
					//TODO vérifier que cela fonctionne et qu'il ne faut pas mettre la ligne suivante avant
					precedente.put(puzzle.getNumeroCase(xSuivant, ySuivant), puzzle.getNumeroCase(xCourant, yCourant));
				}
			}
		}
		//Test de la case à droite
		xSuivant = xCourant + 1; ySuivant = yCourant;
		if(puzzle.isCaseEmpty(xSuivant, ySuivant) && !trouve){
			if(couleurs.get(puzzle.getNumeroCase(xSuivant, ySuivant)).equals("blanc")){
				
				//On ne continu la visite que si on a pas trouvé la case but
				if(xSuivant != xArrivee || ySuivant != yArrivee){
					
					
					/*
					agent.setPositionCourante(xSuivant, ySuivant);
					try {
						agent.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					*/
					
					
					if(visiterProfondeur(agent, puzzle, xSuivant, ySuivant, xArrivee, yArrivee, precedente, couleurs)){
						trouve = true;
						//TODO vérifier que cela fonctionne et qu'il ne faut pas mettre la ligne suivante avant
						precedente.put(puzzle.getNumeroCase(xSuivant, ySuivant), puzzle.getNumeroCase(xCourant, yCourant));
						
					}
				}
				else{
					trouve = true;
					//TODO vérifier que cela fonctionne et qu'il ne faut pas mettre la ligne suivante avant
					precedente.put(puzzle.getNumeroCase(xSuivant, ySuivant), puzzle.getNumeroCase(xCourant, yCourant));
				}
			}
		}
		//Test de la case à gauche
		xSuivant = xCourant - 1; ySuivant = yCourant;
		if(puzzle.isCaseEmpty(xSuivant, ySuivant) && !trouve){
			if(couleurs.get(puzzle.getNumeroCase(xSuivant, ySuivant)).equals("blanc")){
				
				//On ne continu la visite que si on a pas trouvé la case but
				if(xSuivant != xArrivee || ySuivant != yArrivee){
					
					
					/*
					agent.setPositionCourante(xSuivant, ySuivant);
					try {
						agent.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					*/
					
					
					if(visiterProfondeur(agent, puzzle, xSuivant, ySuivant, xArrivee, yArrivee, precedente, couleurs)){
						trouve = true;
						//TODO vérifier que cela fonctionne et qu'il ne faut pas mettre la ligne suivante avant
						precedente.put(puzzle.getNumeroCase(xSuivant, ySuivant), puzzle.getNumeroCase(xCourant, yCourant));
						
					}
				}
				else{
					trouve = true;
					//TODO vérifier que cela fonctionne et qu'il ne faut pas mettre la ligne suivante avant
					precedente.put(puzzle.getNumeroCase(xSuivant, ySuivant), puzzle.getNumeroCase(xCourant, yCourant));
				}
				
			}
		}
		
		//On a testé tous les successeurs
		couleurs.put(puzzle.getNumeroCase(xCourant, yCourant), "noir");
		
		return trouve;
	}
	
}
