package nextQuest.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import nextQuest.ifc.iUserManager;

public class UserManager  extends UnicastRemoteObject  implements iUserManager {
    public UserManager()  throws RemoteException
    {
	
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
