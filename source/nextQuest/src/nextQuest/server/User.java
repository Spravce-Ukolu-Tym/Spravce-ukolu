package nextQuest.server;

import java.rmi.server.*;
import java.rmi.*;
import nextQuest.ifc.iUser; 

public class User extends UnicastRemoteObject  implements iUser
{
    int randnum;
    public User(int neco) throws RemoteException
    {
	randnum = neco;
    }
    @Override
    public int getNum() throws RemoteException
    {
	System.out.println("Tak!");
	return this.randnum;
    }    
}
