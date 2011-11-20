package nextQuest.ifc;

import java.rmi.*;
public interface iConnector extends Remote
{
    public long createLoginSession() throws RemoteException;
    public String getPasswordSalt(long session) throws RemoteException;
    public iUser Login(long session, String username, String passhash) throws RemoteException, nqException;
}
