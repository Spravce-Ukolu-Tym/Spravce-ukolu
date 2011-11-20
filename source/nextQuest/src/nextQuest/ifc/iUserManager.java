package nextQuest.ifc;

import java.rmi.*;
import nextQuest.server.Ability;


public interface iUserManager extends Remote
{

    iUser[] findUsersByAbilities() throws RemoteException, nqException;

    Ability[] listAblities() throws RemoteException, nqException;
    
}
