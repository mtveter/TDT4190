package Ov1;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIInterface extends Remote{
	public int checkwin() throws RemoteException;
}
