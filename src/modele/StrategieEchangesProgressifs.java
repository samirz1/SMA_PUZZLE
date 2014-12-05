package modele;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;

import modele.messages.Message;
import modele.messages.Performatif;

public class StrategieEchangesProgressifs extends Strategie{

	public StrategieEchangesProgressifs() {}

	@Override
	public void executionStrategie() {
		Agent agent = getAgent();
		Message msg = this.receptionMessage();
		this.traitementMessage(msg);
		this.perception();
		this.raisonnement();
		this.action();
	}
	
	public Message receptionMessage() {
		try {
			Agent agent = getAgent();
			agent.setSocketServer(new ServerSocket(agent.getNumeroPort()));
			agent.setSocket(agent.getSocketServer().accept());
			//Récupération des flux
			agent.setOut(new ObjectOutputStream(agent.getSocket().getOutputStream()));
			agent.getOut().flush();
			agent.setIn(new ObjectInputStream(agent.getSocket().getInputStream()));
			
			//On reconstruit le message reçu
			return generateMessage((String) agent.getIn().readObject());
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//Récupération d'un objet Message à partir du message reçu au format String
	public Message generateMessage(String msg){
		
		String[] splitted = msg.split(Message.getSeparateur());
		
		//TODO tester la méthode
		System.out.println(msg);
		String emetteur = splitted[0];
		String performatif = splitted[2];
		String contenu = splitted[3];
		
		return new Message(getAgent().getAgents().get(emetteur), getAgent(), Performatif.valueOf(performatif), contenu);
	}

	public void traitementMessage(Message msg) {

	}
	
	public void envoieMessage(Message msg){
		
	}

	public void raisonnement() {

	}

	public void action() {

	}

	public void perception() {

	}

}
