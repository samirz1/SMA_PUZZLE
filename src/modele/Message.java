package modele;

public class Message {

	// ***************************************************
	// ATTRIBUTS
	// ***************************************************
	private Agent emetteur;
	private Agent recepteur;
	private String performatif;
	private String action;

	// ***************************************************
	// METHODES
	// ***************************************************
	public Message(Agent emetteur, Agent recepteur, String performatif,
			String action) {
		super();
		this.emetteur = emetteur;
		this.recepteur = recepteur;
		this.performatif = performatif;
		this.action = action;
	}

	// ***************************************************
	// GETTERS AND SETTERS
	// ***************************************************
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

	public String getPerformatif() {
		return performatif;
	}

	public void setPerformatif(String performatif) {
		this.performatif = performatif;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

}
