package numerosbis;

import java.util.*;

import java.net.*;
import java.io.*;

public class numerosbis {
	
	public static Map<InetAddress,String> hm_users;
	final static int port = 4321;
	public static ServerSocket socketServeur;
	public static Socket socketClient;
	public static List<ServeurThreadTCP> listeThread;
	
	public static void main(String[] args) {	
		try {
			socketServeur = new ServerSocket(port);
			System.out.println("Lancement du serveur");
			while (true) {
				socketClient = socketServeur.accept();
				ServeurThreadTCP serveur_thread = new ServeurThreadTCP(socketClient);
				listeThread.add(serveur_thread);
				Thread t = new Thread(serveur_thread);
				t.start();
				}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void onenvoielasauce() {
		System.out.println("On envoie la hashmap à chaque thread");
		for(ServeurThreadTCP i:listeThread) {
			try {
				i.oos.writeObject(numerosbis.hm_users);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Ca a pas marché olol");
			}
		}
	}
	
}
