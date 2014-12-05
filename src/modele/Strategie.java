package modele;

public abstract class Strategie {

	private Agent agent;
	
	//***************************************************
	//CONSTRUCTEUR
	//***************************************************
	public Strategie(){}
	
	//***************************************************
	//METHODES
	//***************************************************
	public abstract void executionStrategie();

	
	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}
	
}
