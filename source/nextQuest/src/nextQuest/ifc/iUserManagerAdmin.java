package nextQuest.ifc;

import java.rmi.*;

public interface iUserManagerAdmin extends Remote
{

    void createAbility() throws RemoteException, nqException;

    void createUser() throws RemoteException, nqException;

    void editUser() throws RemoteException, nqException;

    void removeAbility() throws RemoteException, nqException;

    void removeUser() throws RemoteException, nqException;
    
}
