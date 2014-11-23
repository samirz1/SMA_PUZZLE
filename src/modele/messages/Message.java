package modele.messages;

import modele.Agent;

public class Message {

	
	Agent emetteur;
	Agent recepteur;
	Performatif performatif;
	String contenu;
	
	public Message(Agent emetteur, Agent recepteur, Performatif performatif,
			String contenu) {
		super();
		this.emetteur = emetteur;
		this.recepteur = recepteur;
		this.performatif = performatif;
		this.contenu = contenu;
	}
	
	public String toString(){
		return new String(emetteur.getName() + " " + recepteur.getName() + " " + performatif.toString() + " " + contenu);
	}
	
	//GETTERS SETTERS
	public Agent getEmetteur() {
		return emetteur;
	}
	public void setEmetteur(Agent emetteur) {
		this.emetteur = emetteur;
	}
	public Agent getRecepteur() {
		return recepteur;
	}
	public void setRecepteur(Agent recepteur) {
		this.recepteur = recepteur;
	}
	public Performatif getPerformatif() {
		return performatif;
	}
	public void setPerformatif(Performatif performatif) {
		this.performatif = performatif;
	}
	public String getContenu() {
		return contenu;
	}
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	
}
