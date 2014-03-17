package Ov4;

import java.util.ArrayList;

public class Probe extends Thread{
	
	private ArrayList<Integer> visited;
	private Server owner;
	private int resourceId;
	
	public Probe(ServerImpl owner, int resourceId) {
		visited = new ArrayList<Integer>();
		this.owner = owner;
		this.resourceId = resourceId;
	}
	
@Override
public void run() {
	try{
	owner.receiveProbe(visited, resourceId);
	}catch(Exception e){
		e.printStackTrace();
	}
}
}
