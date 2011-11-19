package nextQuest.ifc;

import java.rmi.*;

public interface iUserManagerAdmin extends iUserManager
{

    void createAbility(Ability a) throws RemoteException, nqException;

    void createUser() throws RemoteException, nqException;

    void editUser() throws RemoteException, nqException;

    void removeAbility(Ability a) throws RemoteException, nqException;

    void removeUser() throws RemoteException, nqException;
    
}
