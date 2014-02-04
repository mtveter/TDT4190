package Ov1;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

//Implementasjon av RMI-interfacet
public class Implementation extends UnicastRemoteObject implements RMIInterface{
	//Deklarerer variabler
	int turn;
	private final char playerMarks[] = {'X', 'O'};
	TicTacToe ttt;
	BoardModel bm;
	RMIInterface client;
	boolean isConnected;
	boolean haveWinner;
	//Konstruktør
	protected Implementation(TicTacToe ttt) throws RemoteException {
		super();
		isConnected = false;
		haveWinner = false;
		turn = 0;
		this.ttt = ttt;
		this.bm = ttt.getBoardModel();
	}
	
	//Sjekker om brettet i sin tilstand har en vinner
	@Override
	public void win() throws RemoteException {
		ttt.win();
	}

	//Setter klient
	@Override
	public void setClient(RMIInterface client) {
		this.client = client;
		isConnected = true;	
	}
	
	//plasserer en brikke på server og sender beskjed til klient om også plassere
	@Override
	public boolean placeServer(int x, int y) throws RemoteException{
		boolean b = bm.setCell(x, y, playerMarks[turn]);
		client.placeClient(x, y, playerMarks[turn]);
		changeTurn();
		return b;
	}

	//Plasserer en brikke hos klienten
	@Override
	public void placeClient(int x, int y, char c) throws RemoteException {
		bm.setCell(x, y, c);
	}
	//Bytter tur
	@Override
	public void changeTurn() {
		turn = 1 - turn;
	}
	//returnerer hvem sin tur det er
	@Override
	public int getTurn() throws RemoteException {
		return turn;
		
	}
	//kalles når man har en vinner. Setter statusbeskjed og blokkerer for flere trekk
	@Override
	public void winner() throws RemoteException {
		win();
		client.win();
		haveWinner = true;
	}

	// Sjekker om man har en vinner
	@Override
	public boolean Havewinner() throws RemoteException {
		return haveWinner;
	}

	// Sjekker om man har en connection mellom server og klient
	@Override
	public boolean connected() throws RemoteException {
		return isConnected;
	}

	//Setter statusbeskjed hos server
	@Override
	public void setStatusMessage(String message) throws RemoteException {
		ttt.setStatusMessage(message);
	}
	//Setter statusbeskjed hos klient
	@Override
	public void setStatusMessageClient(String message) throws RemoteException {
		client.setStatusMessage(message);
	}
	

}
