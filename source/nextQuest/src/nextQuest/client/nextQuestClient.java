package nextQuest.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import nextQuest.ifc.iMega;

public class nextQuestClient
{
    public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException
    {
	System.out.println("Client starting...");
	
	iMega mg = (iMega) Naming.lookup("rmi://localhost/mega");
	
	System.out.println("Whoa, got something");
	
	System.out.println(mg.CreateUser().getNum());
	System.out.println(mg.CreateUser().getNum());
	System.out.println(mg.CreateUser().getNum());
	System.out.println(mg.CreateUser().getNum());
	System.out.println(mg.CreateUser().getNum());
	System.out.println(mg.CreateUser().getNum());
	
	
    }
}
