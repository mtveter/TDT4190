package Ov4;

public class Probe extends Thread{

	public Probe(ServerImpl owner, Transaction transaction) {
		owner.println("Heihei. Jeg er en probe", transaction.getId());
	}

}
