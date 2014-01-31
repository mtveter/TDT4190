package Ov1;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class Implementation extends UnicastRemoteObject implements RMIInterface{
	int turn= 0;
	private final char playerMarks[] = {'X', 'O'};
	TicTacToe ttt;
	BoardModel bm;
	RMIInterface client;
	protected Implementation(TicTacToe ttt) throws RemoteException {
		super();
		this.ttt = ttt;
		this.bm = ttt.getBoardModel();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void win() throws RemoteException {
		ttt.win();
	}

	@Override
	public void setClient(RMIInterface client) {
		this.client = client;
		
	}
	@Override
	public boolean placeServer(int x, int y) throws RemoteException{
		boolean b = bm.setCell(x, y, playerMarks[turn]);
		changeTurn();
		return b;
	}

	@Override
	public boolean placeClient(int x, int y) throws RemoteException {
		return client.placeServer(x, y);
	}

	@Override
	public void changeTurn() {
		turn = 1 - turn;
	}

	@Override
	public int getTurnWin() throws RemoteException {
		return 1- turn;
		
	}

	@Override
	public void tellOpponent() throws RemoteException {
		win();
		client.win();
	}
	

}
