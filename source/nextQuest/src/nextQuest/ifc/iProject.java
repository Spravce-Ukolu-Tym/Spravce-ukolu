package nextQuest.ifc;

import java.rmi.*;



public interface iProject extends Remote
{

    int getID()  throws RemoteException, nqException;

    String getName() throws RemoteException, nqException;

    iUser getUserCreatedBy() throws RemoteException, nqException;

    iUser getLeader() throws RemoteException, nqException;

    int getPriority() throws RemoteException, nqException;

}
