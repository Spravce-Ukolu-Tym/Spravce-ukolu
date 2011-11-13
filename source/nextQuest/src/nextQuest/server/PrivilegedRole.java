package nextQuest.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import nextQuest.ifc.iPrivilegedRole;

public class PrivilegedRole  extends UnicastRemoteObject implements iPrivilegedRole {
    public PrivilegedRole() throws RemoteException
    {
	
    }
    
    @Override
    public void getStats() {
    }
}
