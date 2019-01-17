package numerosbis;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;

public class ServeurThreadTCP extends Thread{
	
	private Socket socket;
	public ObjectOutputStream oos;
	private ObjectInputStream ois;
	private InetAddress addrClient;
	private String etatClient;
	
	
	public ServeurThreadTCP(Socket ClientSocket) {
		socket = ClientSocket;
	}
	
	public void run() {
		try {
			addrClient = socket.getInetAddress();
			System.out.println("Connexion avec le client : " + addrClient);
			System.out.println("création du stream de sortie");
			oos = new ObjectOutputStream(socket.getOutputStream()); 
			
			//Attente de l'état du client
			ois = new ObjectInputStream(socket.getInputStream()); 
			while(true) {
				etatClient = (String) ois.readObject(); 
				System.out.println(etatClient);
				System.out.println(numerosbis.hm_users);
				
				System.out.println("on enlève l'user de la hashmap");
				if (numerosbis.hm_users.containsKey(addrClient)) numerosbis.hm_users.remove(addrClient);
				
				System.out.println("verification de l'état déconnecté");
				if(etatClient.equals("Deconnexion")) {
					//On supprime l'ip et l'état du GUI
					GUI_numerosbis.removeUser(addrClient);
					break;
				}
				
				//Ajout à la hashmap
				System.out.println("On ajoute le client et son état à la hashmap");
				numerosbis.hm_users.put(addrClient, etatClient);
				
				//Ajout au GUI
				GUI_numerosbis.changeState(addrClient,etatClient);
				
				System.out.println("On envoie la sauce");
				numerosbis.onenvoielasauce();
			}
			numerosbis.listeThread.remove(this);
			
			oos.close();
			ois.close();
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
