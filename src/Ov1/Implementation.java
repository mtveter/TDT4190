package Ov1;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class Implementation extends UnicastRemoteObject implements RMIInterface{
	BoardModel bm;
	RMIInterface client;
	protected Implementation(BoardModel bm) throws RemoteException {
		super();
		this.bm = bm;
		// TODO Auto-generated constructor stub
	}

	@Override
	public int checkwin() {
		return bm.getRowCount();
	}

	@Override
	public void setClient(RMIInterface client) {
		this.client = client;
		
	}
	@Override
	public void placeServer(int x, int y, char c) throws RemoteException{
		bm.setCell(x, y, c);
		
	}

	@Override
	public void placeClient(int x, int y, char c) throws RemoteException {
		client.placeServer(x, y, c);
		
	}

	@Override
	public void changeTurn() {
		
		
	}

}
