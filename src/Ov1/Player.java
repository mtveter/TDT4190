package Ov1;

import java.util.Scanner;

public class Player {
	
	public Player() {
		System.setSecurityManager(new LiberalSecurityManager());
		
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
	
	public void server() {
		
	}
	
	public void client(String ip, String port, Scanner scan){
		
	}
}
