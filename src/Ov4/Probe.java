package Ov4;

import java.util.ArrayList;

public class Probe extends Thread{
	
	private ArrayList<Integer> visited;
	private Server owner;
	
	public Probe(ServerImpl owner, Transaction transaction) {
		owner.println("Heihei. Jeg er en probe", transaction.getId());
		visited = new ArrayList<Integer>();
		this.owner = owner;
	}
	
@Override
public void run() {
	try{
	owner.receiveProbe(visited);
	}catch(Exception e){
		e.printStackTrace();
	}
}
}
