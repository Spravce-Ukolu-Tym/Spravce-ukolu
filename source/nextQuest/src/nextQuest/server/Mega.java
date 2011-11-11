package nextQuest.server;

import nextQuest.ifc.iMega;
import java.rmi.server.*;
import java.rmi.*;
import nextQuest.ifc.iUser;


public class Mega extends UnicastRemoteObject  implements iMega
{
    int neco;
    public Mega() throws RemoteException
    {
	this.neco = 0;
    }
    public iUser CreateUser() throws RemoteException
    {
	return new User(this.neco++);
    }
}
