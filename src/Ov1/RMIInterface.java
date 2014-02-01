package Ov1;


import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIInterface extends Remote{
	public void win() throws RemoteException;
	public void setClient(RMIInterface client) throws RemoteException;
	public boolean placeServer(int x, int y) throws RemoteException;
	public boolean placeClient(int x, int y, char c) throws RemoteException;
	public void changeTurn() throws RemoteException;
	public int getTurn() throws RemoteException;
	public void tellOpponent() throws RemoteException;
	public int getWinner() throws RemoteException;
	public void winner() throws RemoteException;
	public boolean Havewinner() throws RemoteException;
	public boolean connected() throws RemoteException;
	public void setStatusMessage(String message) throws RemoteException;
	public void setStatusMessageClient(String message) throws RemoteException;
}
