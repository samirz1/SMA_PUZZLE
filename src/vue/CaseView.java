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

	//***************************************************
	//METHODES
	//***************************************************
	public CaseView(Case casee){
		this.setCasee(casee);
		this.setLayout(new GridBagLayout());
		this.MAJ();
	}
	
	public void MAJ(){
		if(this.getCasee().getAgent()!=null){
			this.setBackground(Color.GRAY);
			this.add(new JLabel(Integer.toString(this.getCasee().getAgent().getNumeroPort())));
		}else 
			this.setBackground(Color.WHITE);
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
