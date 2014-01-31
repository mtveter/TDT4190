package Ov1;


import java.rmi.Remote;
import java.rmi.RemoteException;

import Ov1.BoardModel.Cell;

public interface RMIInterface extends Remote{
	public void win() throws RemoteException;
	public void setClient(RMIInterface client) throws RemoteException;
	public boolean placeServer(int x, int y) throws RemoteException;
	public boolean placeClient(int x, int y) throws RemoteException;
	public void changeTurn() throws RemoteException;
	public int getTurnWin() throws RemoteException;
	public void tellOpponent() throws RemoteException;
}
