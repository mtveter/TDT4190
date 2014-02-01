package Ov1;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class Implementation extends UnicastRemoteObject implements RMIInterface{
	int turn;
	private final char playerMarks[] = {'X', 'O'};
	TicTacToe ttt;
	BoardModel bm;
	RMIInterface client;
	boolean haveWinner;
	protected Implementation(TicTacToe ttt) throws RemoteException {
		super();
		haveWinner = false;
		turn = 0;
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
		client.placeClient(x, y, playerMarks[turn]);
		changeTurn();
		return b;
	}

	@Override
	public boolean placeClient(int x, int y, char c) throws RemoteException {
		bm.setCell(x, y, c);
		return true;
	}

	@Override
	public void changeTurn() {
		turn = 1 - turn;
	}

	@Override
	public int getTurn() throws RemoteException {
		return turn;
		
	}

	@Override
	public void tellOpponent() throws RemoteException {
		win();
		client.win();
	}

	@Override
	public int getWinner() throws RemoteException {
		return 1- turn;
	}

	@Override
	public void winner() throws RemoteException {
		haveWinner = true;
	}

	@Override
	public boolean Havewinner() throws RemoteException {
		return haveWinner;
	}
	

}
