package nextQuest.ifc;

import java.rmi.*;

public interface iUserManager extends Remote
{

    iUser[] findUsersByAbilities() throws RemoteException, nqException;

    iAbility[] listAblities() throws RemoteException, nqException;
    
}
