package nextQuest.ifc;

import nextQuest.server.Ability;
import java.rmi.*;

public interface iUserManagerAdmin extends iUserManager
{

    void createAbility(Ability a) throws RemoteException, nqException;

    void createUser(String name, String LoginName, String primaryPassword, Boolean personalist, Boolean leader) throws RemoteException, nqException;

    void editUser(iUser usr, String LoginName, String name, String primaryPassword, Boolean personalist, Boolean leader) throws RemoteException, nqException;

    void updateUserAbilities(iUser usr, Ability[] abs) throws RemoteException, nqException;
    
    void removeAbility(Ability a) throws RemoteException, nqException;

    void removeUser(iUser usr) throws RemoteException, nqException;
    
}
