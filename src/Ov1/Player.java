package Ov1;


import java.rmi.AlreadyBoundException;
import java.rmi.ConnectException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Player {
	Registry registryet;
	private TicTacToe ttt;
	 private static String adresse = "TictacToeAdress"; // Adressen for adresselockup
	 boolean isServer = false;
	 
	 //Konstruktør som prøver å lokalisere et registry. Lager server hvis den ikke finner og client hvis den finner
	public Player() throws RemoteException, NotBoundException, AlreadyBoundException, InterruptedException {
		
		try {
			Registry registryet = LocateRegistry.getRegistry("localhost", 3041);
			RMIInterface rint = (RMIInterface) registryet.lookup(adresse);
		}
		catch (Exception e) {
			server();
			isServer = true;
		}
		if(!isServer){
			client();
		}

	}
	//Metode for å opprette server. Lager nytt tictactoe-spill og binder interfacet til et registry på portnummer 3041
	public void server() throws RemoteException, NotBoundException, AlreadyBoundException, InterruptedException {
		System.out.println("Starting server");
		ttt = new TicTacToe(0);
		Implementation imp = new Implementation(ttt);
		ttt.setRint((RMIInterface) imp);
		imp.setStatusMessage("Waiting for Client");
		Registry registry = LocateRegistry.createRegistry(3041);
		registry.bind(adresse, imp);
		System.out.println("Server running");
	}
	//Metode for klient. Lager nytt Tictactoe til klienten, henter ut registriet fra server og setter sin implementasjon som klient
	public void client() throws RemoteException, NotBoundException, InterruptedException{
		System.out.println("Starting Client");
		Registry registry = LocateRegistry.getRegistry("localhost", 3041);
		RMIInterface rint = (RMIInterface) registry.lookup(adresse);
		ttt = new TicTacToe(1);
		ttt.setRint(rint);
		Implementation imp = new Implementation(ttt);
		rint.setClient((RMIInterface) imp);
		rint.setStatusMessage("Client connected you Are X");
		rint.setStatusMessageClient("Connected to Server. You are O");
		System.out.println("Client running");
	}
	//Mainmetode som oppretter en ny player
	public static void main(String[] args) throws RemoteException, NotBoundException, AlreadyBoundException, InterruptedException {
		Player p = new Player();
	}
}