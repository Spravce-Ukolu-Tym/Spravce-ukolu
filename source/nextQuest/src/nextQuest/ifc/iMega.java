package nextQuest.ifc;

import java.rmi.*;
import java.rmi.server.*;
public interface iMega extends Remote
{
    public iUser CreateUser() throws RemoteException;
}
