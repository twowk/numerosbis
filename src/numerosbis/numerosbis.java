package numerosbis;

import java.util.*;


import java.net.*;
import java.io.*;

public class numerosbis {
	
	public static Map<InetAddress,String> hm_users;
	final static int port = 1235;
	public static ServerSocket socketServeur;
	public static ArrayList<ServeurThreadTCP> listeThread;
	public static GUI_numerosbis graphic_thread = new GUI_numerosbis();
	
	public static void main(String[] args) {	
		//Lancement du GUI
		StartGUI_Thread();
		//Affichage de l'addresse du serveur
		try {
			System.out.println(getLocalAddress());
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//Connexion aux clients
		try {
			socketServeur = new ServerSocket(port);
			System.out.println("Lancement du serveur");
			listeThread = new ArrayList<ServeurThreadTCP>();
			hm_users = new HashMap<>();
			while (true) {
				System.out.println("on rentre dans le accept");
				Socket socketClient = socketServeur.accept();
				System.out.println(socketClient);
				
				System.out.println("accept new connection");
				ServeurThreadTCP serveur_thread = new ServeurThreadTCP(socketClient);
				System.out.println(serveur_thread);
				
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
	
	
	
	public static InetAddress getLocalAddress() throws SocketException{
	    Enumeration<NetworkInterface> ifaces = NetworkInterface.getNetworkInterfaces();
	    while( ifaces.hasMoreElements() )
	    {
	      NetworkInterface iface = ifaces.nextElement();
	      Enumeration<InetAddress> addresses = iface.getInetAddresses();

	      while( addresses.hasMoreElements() )
	      {
	        InetAddress addr = addresses.nextElement();
	        if( addr instanceof Inet4Address && !addr.isLoopbackAddress() )
	        {
	          return addr;
	        }
	      }
	    }

	    return null;
	  }
	
	//Start The GUI_Thread in another thread
		public static void StartGUI_Thread() {
			Thread t = new Thread(graphic_thread);
			t.start();
		}
}


