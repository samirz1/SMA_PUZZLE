package modele.messages;

import modele.Agent;

public class Message {

	
	private Agent emetteur;
	private Agent recepteur;
	private Performatif performatif;
	private String contenu;
	private static String separateur = " / ";
	
	public Message(Agent emetteur, Agent recepteur, Performatif performatif,
			String contenu) {
		super();
		this.emetteur = emetteur;
		this.recepteur = recepteur;
		this.performatif = performatif;
		this.contenu = contenu;
	}
	
	public String toString(){
		return new String(emetteur.getName() + "/" + recepteur.getName() + "/" + performatif.toString() + " " + contenu);
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

	public static String getSeparateur() {
		return separateur;
	}

	public static void setSeparateur(String separateur) {
		Message.separateur = separateur;
	}
	
}
