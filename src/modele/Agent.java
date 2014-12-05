package modele;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.activation.ActivateFailedException;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.ImageIcon;

import modele.messages.Message;
import modele.messages.Performatif;

public class Agent extends Thread {

	// ***************************************************
	// ATTRIBUTS
	// ***************************************************
	private int xArrive;
	private int yArrive;
	private int xCourant;
	private int yCourant;
	private ImageIcon image;
	private int numeroPort;
	private ServerSocket socketServer;
	private Socket socket;
	private Case casee;
	private Puzzle puzzle;
	private HashMap<Integer, Agent> agents;
	
	private boolean activated = false;

	private Strategie strategie;
	private ObjectOutputStream out;
	private ObjectInputStream in;

	// ***************************************************
	// METHODES
	// ***************************************************
	public Agent(Strategie strategie, int xCourant, int yCourant, int xArrive,
			int yArrive, int numeroPort, HashMap<Integer, Agent> agents,
			Puzzle puzzle) {
		super();
		this.strategie = strategie;
		this.strategie.setAgent(this);
		this.setxCourant(xCourant);
		this.setyCourant(yCourant);
		this.xArrive = xArrive;
		this.yArrive = yArrive;
		this.numeroPort = numeroPort;
		this.setPuzzle(puzzle);
		this.setAgents(agents);
		this.MAJcase();
		this.setImage(new ImageIcon("images//image"+Integer.toString(this.getNumeroPort()-1110)+".jpg"));
	}

	@Override
	public void run() {
		while (!this.puzzleFini()) {
			
			if(this.activated){
				strategie.executionStrategie();
			}
		}
	}

	public boolean puzzleFini() {
		boolean fini = true;
		Iterator<Integer> iter = agents.keySet().iterator();
		while (iter.hasNext()) {
			if (!this.getAgents().get(iter.next()).estArrive()) {
				fini = false;
				break;
			}
		}
		return fini;
	}

	public boolean estArrive() {
		return ((this.getxCourant() == this.getxArrive()) && (this
				.getyCourant() == this.getyArrive()));
	}

	public void MAJcase() {
		// On enléve l'agent de la case
		if (this.getCasee() != null)
			this.getCasee().setAgent(null);
		// On modifie la case de l'agent et l'agent dans la case
		this.setCasee(this.getPuzzle().getCaseXY(this.getxCourant(),
				this.getyCourant()));
		this.getCasee().setAgent(this);
		System.out.println("POUR (" + this.getxCourant() + ","
				+ this.getyCourant() + ")  ON OBTIENT "
				+ this.getCasee().getNumero());
	}

	public synchronized void setPositionCourante(int x, int y) {
		this.setxCourant(x);
		this.setyCourant(y);
		this.MAJcase();
		System.out.println();
		puzzle.afficher();
	}

	// ***************************************************
	// GETTERS AND SETTERS
	// ***************************************************
	public int getxArrive() {
		return xArrive;
	}

	public void setxArrive(int xArrive) {
		this.xArrive = xArrive;
	}

	public int getyArrive() {
		return yArrive;
	}

	public void setyArrive(int yArrive) {
		this.yArrive = yArrive;
	}

	public ImageIcon getImage() {
		return image;
	}

	public void setImage(ImageIcon image) {
		this.image = image;
	}

	public ServerSocket getSocketServer() {
		return socketServer;
	}

	public void setSocketServer(ServerSocket socketServer) {
		this.socketServer = socketServer;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public int getNumeroPort() {
		return numeroPort;
	}

	public void setNumeroPort(int numeroPort) {
		this.numeroPort = numeroPort;
	}

	public Case getCasee() {
		return casee;
	}

	public void setCasee(Case casee) {
		this.casee = casee;
	}

	public HashMap<Integer, Agent> getAgents() {
		return agents;
	}

	public void setAgents(HashMap<Integer, Agent> agents) {
		this.agents = agents;
	}

	public Puzzle getPuzzle() {
		return puzzle;
	}

	public void setPuzzle(Puzzle puzzle) {
		this.puzzle = puzzle;
	}

	public int getxCourant() {
		return xCourant;
	}

	public void setxCourant(int xCourant) {
		this.xCourant = xCourant;
	}

	public int getyCourant() {
		return yCourant;
	}

	public void setyCourant(int yCourant) {
		this.yCourant = yCourant;
	}

	public ObjectOutputStream getOut() {
		return out;
	}

	public void setOut(ObjectOutputStream out) {
		this.out = out;
	}

	public ObjectInputStream getIn() {
		return in;
	}

	public void setIn(ObjectInputStream in) {
		this.in = in;
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

}
