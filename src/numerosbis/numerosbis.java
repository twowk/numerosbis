package numerosbis;

import java.util.Map;

import java.net.*;
import java.io.*;

public class numerosbis {
	
	public static Map<String,InetAddress> hm_users;
	final static int port = 4321;
	public static ServerSocket socketServeur;
	public static Socket socketClient;
	public static ServeurThreadTCP serveur_thread;
	
	public static void main(String[] args) {	
		try {
			socketServeur = new ServerSocket(port);
			System.out.println("Lancement du serveur");
			while (true) {
				socketClient = socketServeur.accept();
				serveur_thread = new ServeurThreadTCP(socketClient);
				StartServeur();
				}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void StartServeur() {
		Thread t = new Thread(serveur_thread);
		t.start();
	}
	
	
}
