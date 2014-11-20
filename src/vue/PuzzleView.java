package vue;

import javax.swing.JFrame;

public class PuzzleView extends JFrame{

	private static final long serialVersionUID = 1L;

	//***************************************************
	//ATTRIBUTS
	//***************************************************
	

	//***************************************************
	//METHODES
	//***************************************************
	public PuzzleView(int nbLignes, int nbColonnes){
		this.setTitle("PUZZLE - ESPENEL & HIMMICH");
	    this.setSize(400, 500);
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);             
	    this.setVisible(true);
	}
	
	//***************************************************
	//GETTERS AND SETTERS
	//***************************************************
	
	
}
