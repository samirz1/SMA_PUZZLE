package vue;

import javax.swing.JFrame;

import modele.Puzzle;

public class PuzzleView extends JFrame{

	private static final long serialVersionUID = 1L;

	//***************************************************
	//ATTRIBUTS
	//***************************************************
	private Puzzle puzzle;

	//***************************************************
	//METHODES
	//***************************************************
	public PuzzleView(Puzzle puzzle){
		this.setTitle("PUZZLE - ESPENEL & HIMMICH");
	    this.setSize(400, 500);
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);             
	    this.setVisible(true);
	}

	//***************************************************
	//GETTERS AND SETTERS
	//***************************************************
	public Puzzle getPuzzle() {
		return puzzle;
	}

	public void setPuzzle(Puzzle puzzle) {
		this.puzzle = puzzle;
	}	
	
}
