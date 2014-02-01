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
	 private static String adresse = "JavaRMItest";
	 boolean isServer = false;
	 
	public Player() throws RemoteException, NotBoundException, AlreadyBoundException, InterruptedException {
		
		try {
			Registry registryet = LocateRegistry.getRegistry("localhost", 1000);
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
	public void server() throws RemoteException, NotBoundException, AlreadyBoundException, InterruptedException {
		System.out.println("Starting game ...");
		ttt = new TicTacToe(0);
		Implementation imp = new Implementation(ttt);
		ttt.setRint((RMIInterface) imp);
		imp.setStatusMessage("Waiting for Client");
		Registry registry = LocateRegistry.createRegistry(1000);
		registry.bind(adresse, imp);
		System.out.println("server kjører");
	}
	
	public void client() throws RemoteException, NotBoundException, InterruptedException{
		Registry registry = LocateRegistry.getRegistry("localhost", 1000);
		RMIInterface rint = (RMIInterface) registry.lookup(adresse);
		ttt = new TicTacToe(rint,1);
		Implementation imp = new Implementation(ttt);
		rint.setClient((RMIInterface) imp);
		rint.setStatusMessage("Client connected you Are X");
		rint.setStatusMessageClient("Connected to Server. You are O");
		System.out.println("Client running");
	}
	public static void main(String[] args) throws RemoteException, NotBoundException, AlreadyBoundException, InterruptedException {
		Player p = new Player();
	}
}