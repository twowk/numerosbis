package numerosbis;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ServeurThreadTCP extends Thread{
	
	private Socket socket;
	
	public ServeurThreadTCP(Socket ClientSocket) {
		socket = ClientSocket;
	}
	
	public void run() {
		try {
			String message = "";
			System.out.println("Connexion avec le client : " + socket.getInetAddress());
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintStream out = new PrintStream(socket.getOutputStream());
			message = in.readLine();
			out.println("Bonjour " + message);
			
			//TODO 
			
			
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
