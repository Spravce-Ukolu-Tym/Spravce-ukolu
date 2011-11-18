package nextQuest.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;
import nextQuest.ifc.*;

public class nextQuestClient
{
    public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException
    {
	//String server = "rmi://localhost/con";
	String server = "rmi://nextquest.sytes.net/con";

	for(String arg : args)
	{
	    String[] pr = arg.split("=");

	    if(pr.length != 2){
		System.out.printf("ERROR: Bad argument format '%s'\n", arg);
		continue;}


	    if(pr[0].equals("server"))
		server = pr[1];
	    else
		System.out.printf("ERROR: Unknown argument '%s'\n", pr[0]);
	}


	System.out.println("Client starting...");

	iConnector mg = (iConnector) Naming.lookup(server);

	System.out.println("Connected...");
	System.out.print("Login:");
	String name = new Scanner(System.in).nextLine();
	System.out.print("Password:");
	String pass = new Scanner(System.in).nextLine(); //new String(System.console().readPassword("Password:"));	 //fuck

	iUser usr;
	try
	{
	    usr = mg.Login(name, pass);
	    System.out.printf("Loged in! I am %s\n", usr.getName());
	}
	catch(nqException e)
	{
	    System.out.printf("Fail! Exception %s, message: %s", e.getType().toString(), e.getMessage());
	}

    }
}