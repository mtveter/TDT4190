package Ov1;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class Implementation extends UnicastRemoteObject implements RMIInterface{
	BoardModel bm;
	protected Implementation(BoardModel bm) throws RemoteException {
		super();
		this.bm = bm;
		// TODO Auto-generated constructor stub
	}

	@Override
	public int checkwin() {
		return bm.getRowCount();
	}

}
