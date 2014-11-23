package modele.messages;

public enum Performatif {

	Request("Request"),
	Inform("Inform"),
	Inquire("Inquire");
	
	private String label;
	
	private Performatif(String label) {
		this.label = label;
	}
	
	public String toString(){
		return label;
	}
	
}
