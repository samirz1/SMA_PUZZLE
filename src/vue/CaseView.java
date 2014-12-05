package vue;

import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import modele.Case;

public class CaseView extends JPanel{

	private static final long serialVersionUID = 1L;

	//***************************************************
	//ATTRIBUTS
	//***************************************************
	private Case casee;
	private JLabel label;
	
	//***************************************************
	//METHODES
	//***************************************************
	public CaseView(Case casee){
		this.setCasee(casee);
		this.setLayout(new GridBagLayout());
		this.MAJ();
	}
	
	public synchronized void MAJ(){
		if(this.getCasee().getAgent()!=null){
			this.setBackground(Color.GRAY);
			//TODO A OPTIMISER
			if(label != null) this.remove(label);
			if(this.getCasee().getAgent() != null)
				label = new JLabel(Integer.toString(this.getCasee().getAgent().getNumeroPort()));
			
			//TODO IMAGE
			//this.getCasee().getAgent().getImage().getImage().getScaledInstance(5, 5, java.awt.Image.SCALE_SMOOTH);
			//label.setIcon(this.getCasee().getAgent().getImage());
			
			this.add(label);
			
		}else {
			this.setBackground(Color.WHITE);
			if(label != null) this.remove(label);
		}
	}
	
	//***************************************************
	//GETTERS AND SETTERS
	//***************************************************
	public Case getCasee() {
		return casee;
	}

	public void setCasee(Case casee) {
		this.casee = casee;
	}
	
}
