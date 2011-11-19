package nextQuest.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import nextQuest.ifc.iPrivilegedRole;

public class PrivilegedRole  extends UnicastRemoteObject implements iPrivilegedRole {
    protected User user;
    protected Connection con;
    public PrivilegedRole(Connection con, User user) throws RemoteException
    {
	this.con = con;
	this.user = user;
    }
    
    @Override
    public void getStats() {
    }
}
