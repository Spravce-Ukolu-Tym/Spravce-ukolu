package nextQuest.ifc;

import java.rmi.*;
public interface iConnector extends Remote
{
    public iUser Login(String username, String pass) throws RemoteException, nqException;
}
