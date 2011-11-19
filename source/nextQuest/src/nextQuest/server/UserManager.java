package nextQuest.server;

import nextQuest.ifc.Ability;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import nextQuest.ifc.iUserManager;

public class UserManager  extends UnicastRemoteObject  implements iUserManager {
    private Connection con;
    private User user;
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
