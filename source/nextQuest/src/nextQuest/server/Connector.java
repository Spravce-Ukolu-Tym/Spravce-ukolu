package nextQuest.server;

import nextQuest.ifc.iConnector;
import java.rmi.server.*;
import java.rmi.*;
import nextQuest.ifc.iUser;


public class Connector extends UnicastRemoteObject  implements iConnector
{
    public Connector() throws RemoteException
    {
	
    }
    
    @Override
    public iUser Login(String username, String pass) throws RemoteException
    {
	System.out.printf("Login(%s, %s)\n", username, pass);
	return null;
    }
}
