package nextQuest.server;

import nextQuest.ifc.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;

public class UserManager  extends UnicastRemoteObject  implements iUserManager {
    protected Connection con;
    protected User user;
    public UserManager(Connection con, User u)  throws RemoteException
    {
	this.con = con;
	this.user = u;
    }
    
    
    @Override
    public User[] findUsersByAbilities() {
        return null;
    }

    @Override
    public Ability[] listAblities() {
        return null;
    }
}
