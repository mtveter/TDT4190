package Ov1;


import java.rmi.Remote;
import java.rmi.RemoteException;

import Ov1.BoardModel.Cell;

public interface RMIInterface extends Remote{
	public int checkwin() throws RemoteException;
	public void setClient(RMIInterface client) throws RemoteException;
	public void placeServer(int x, int y, char c) throws RemoteException;
	public void placeClient(int x, int y, char c) throws RemoteException;
	public void changeTurn() throws RemoteException;
}
