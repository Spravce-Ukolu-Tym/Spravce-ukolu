package nextQuest.ifc;

import java.rmi.*;
import nextQuest.server.Ability;
import nextQuest.server.UserInfo;


public interface iUserManager extends Remote
{

    UserInfo[] findUsersByAbilities() throws RemoteException, nqException;
    UserInfo[] listAllUsers() throws RemoteException, nqException;
    
    Ability[] listAblities() throws RemoteException, nqException;
    
}
