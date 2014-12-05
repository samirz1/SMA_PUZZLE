package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

import modele.Case;
import modele.Puzzle;

public class PuzzleView extends JFrame implements Observer{

	private static final long serialVersionUID = 1L;

	// ***************************************************
	// ATTRIBUTS
	// ***************************************************
	private Puzzle puzzle;
	private HashMap<Integer, CaseView> caseViews;
	private JPanel panel;

	// ***************************************************
	// METHODES
	// ***************************************************
	public PuzzleView(Puzzle puzzle) {
		this.setTitle("PUZZLE - ESPENEL & HIMMICH");
		this.setSize(400, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setPuzzle(puzzle);
		this.setCaseViews(new HashMap<Integer, CaseView>());
		this.liaisonCaseCaseView();
		
		panel = new JPanel();
		panel.setBackground(Color.ORANGE);
		this.setContentPane(panel);
		panel.setLayout(new GridLayout(puzzle.getNbLignes(), puzzle.getNbLignes(), 5, 5));
		for(int i=0; i<this.puzzle.getCases().size(); i++){
			panel.add(this.getCaseViews().get(i));
		}	
		
		this.setVisible(true);
	}
	
	private void liaisonCaseCaseView(){
		Case caseee;
		Iterator<Integer> iter = this.getPuzzle().getCases().keySet().iterator();
		while(iter.hasNext()){
			caseee = this.getPuzzle().getCases().get(iter.next());
			this.getCaseViews().put(caseee.getNumero(), new CaseView(caseee));
		}
	}
	
	public void MAJPuzzleView(){
		CaseView caseView;
		Iterator<Integer> iter = this.getCaseViews().keySet().iterator();
		while(iter.hasNext()){
			caseView = this.getCaseViews().get(iter.next());
			caseView.MAJ();
		}
		this.paintComponents(this.getGraphics());
	}


	@Override
	public void update(Observable arg0, Object arg1) {
		this.MAJPuzzleView();		
	}
	
	// ***************************************************
	// GETTERS AND SETTERS
	// ***************************************************
	public Puzzle getPuzzle() {
		return puzzle;
	}

	public void setPuzzle(Puzzle puzzle) {
		this.puzzle = puzzle;
	}

	public HashMap<Integer, CaseView> getCaseViews() {
		return caseViews;
	}

	public void setCaseViews(HashMap<Integer, CaseView> caseViews) {
		this.caseViews = caseViews;
	}

}
