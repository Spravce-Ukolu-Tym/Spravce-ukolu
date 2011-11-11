package nextQuest.server;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class nextQuestServer
{

    public static void main(String[] args) throws RemoteException, AlreadyBoundException, NotBoundException, InterruptedException
    {
	System.out.println("Server starting...");

	System.setSecurityManager(new RMISecurityManager());

	Registry r;


	try
	{
	    //LocateRegistry.createRegistry(6969);
	    r = LocateRegistry.getRegistry();
	    System.out.println("Found registry...");
	}
	catch (Exception e)
	{
	    System.out.println("Creating registry...");
	    LocateRegistry.createRegistry(1099);
	    r = LocateRegistry.getRegistry();
	}


	System.out.println("Binding...");
	r.bind("mega", new Mega());
	System.out.println("Bound...");

	
	while(true)
	{
	    Thread.sleep(1000);
	}
	
	
    }
}
