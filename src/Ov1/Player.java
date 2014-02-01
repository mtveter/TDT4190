package Ov1;


import java.io.Serializable;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import Ov1.BoardModel.Cell;

public class Player {
	private TicTacToe ttt;
	 private static String adresse = "JavaRMItest";
	 
	public Player() {
		
		try {
            Scanner scan = new Scanner(System.in);
            System.out.println("Please enter IP address and port to connect to");
            System.out.println("If no address or port is supplied, you will take the role as server\nIP: ");
            String ip = scan.nextLine();
            System.out.println("Port: ");
            String port = scan.nextLine();
            
            if(ip.equals("") && port.equals(""))
                    server();
            else
                    client(ip, port, scan);
    } catch (Exception e) {
            e.printStackTrace();
    }
	}
	public void server() throws RemoteException, NotBoundException, AlreadyBoundException, InterruptedException {
		System.out.println("Starting game ...");
		ttt = new TicTacToe(0);
		Implementation imp = new Implementation(ttt);
		ttt.setRint((RMIInterface) imp);
		Registry registry = LocateRegistry.createRegistry(1000);
		registry.bind(adresse, imp);
		System.out.println("server kj�rer");
	}
	
	public void client(String ip, String port, Scanner scan) throws RemoteException, NotBoundException, InterruptedException{
		Registry registry = LocateRegistry.getRegistry("localhost", 1000);
		RMIInterface rint = (RMIInterface) registry.lookup(adresse);
		ttt = new TicTacToe(rint,1);
		Implementation imp = new Implementation(ttt);
		rint.setClient((RMIInterface) imp);
		System.out.println("wait finnished");
	}
	public static void main(String[] args) {
		Player p = new Player();
	}
}