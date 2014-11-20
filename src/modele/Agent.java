package modele;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.ImageIcon;

public class Agent extends Thread {

	// ***************************************************
	// ATTRIBUTS
	// ***************************************************
	private int xDepart;
	private int yDepart;
	private int xArrive;
	private int yArrive;
	private ImageIcon image;
	private int numeroPort;
	private ServerSocket socketServer;
	private Socket socket;
	private Case casee;
	private HashMap<Integer, Agent> agents;

	// ***************************************************
	// METHODES
	// ***************************************************
	public Agent(int xDepart, int yDepart, int xArrive, int yArrive,
			int numeroPort, HashMap<Integer, Agent> agents) {
		super();
		this.xDepart = xDepart;
		this.yDepart = yDepart;
		this.xArrive = xArrive;
		this.yArrive = yArrive;
		this.numeroPort = numeroPort;
		this.setAgents(agents);
	}

	@Override
	public void run() {
		while (!this.puzzleFini()) {
			this.receptionMessage();
			this.traitementMessage();
			this.perception();
			this.raisonnement();
			this.action();
		}
	}

	public void receptionMessage() {

	}

	public void traitementMessage() {

	}

	public void raisonnement() {

	}

	public void action() {

	}

	public void perception() {

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
		return ((this.getxDepart() == this.getxArrive()) && (this.getyDepart() == this
				.getyArrive()));
	}

	// ***************************************************
	// GETTERS AND SETTERS
	// ***************************************************
	public int getxDepart() {
		return xDepart;
	}

	public void setxDepart(int xDepart) {
		this.xDepart = xDepart;
	}

	public int getyDepart() {
		return yDepart;
	}

	public void setyDepart(int yDepart) {
		this.yDepart = yDepart;
	}

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

}
