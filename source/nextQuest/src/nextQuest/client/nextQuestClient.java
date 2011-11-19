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
	String server = "rmi://localhost/con"; //inicializacni hodnota pro pripad, ze v argumentech nebude nic takovyho nalezeno
	//String server = "rmi://nextquest.sytes.net/con";

	for (String arg : args) //rozparsovani argumentu ...
	{
	    String[] pr = arg.split("=");

	    if (pr.length != 2)
	    {
		System.out.printf("ERROR: Bad argument format '%s'\n", arg);
		continue;
	    }


	    if (pr[0].equals("server"))
	    {
		server = pr[1];
	    }
	    else
	    {
		System.out.printf("WARNING: Unknown argument '%s'\n", pr[0]);
	    }
	}


	System.out.println("Client starting...");

	iConnector mg = (iConnector) Naming.lookup(server); //pripojit se na server, ziskat "connector" tzn to cim se lze prihlasit

	System.out.println("Connected...");
	System.out.print("Login:");
	String name = new Scanner(System.in).nextLine();
	System.out.print("Password:");
	String pass = new Scanner(System.in).nextLine(); //new String(System.console().readPassword("Password:"));	 //fuck

	iUser usr; 
	try
	{
	    usr = mg.Login(name, pass); //pokusit se prihlasit
	    System.out.printf("Loged in! I am '%s', permissions:\n", usr.getName());
	}
	catch (nqException e) //nqException je potreba vzdy odchytit!
	{
	    System.out.printf("Fail! Exception %s, message: %s\n", e.getType().toString(), e.getMessage());
	    return;
	}


	iPrivilegedRole[] roles;
	try
	{
	    roles = usr.getRoles(); //ziskat seznam roli
	}
	catch (nqException e)
	{
	    System.out.printf("Fail! Exception %s, message: %s\n", e.getType().toString(), e.getMessage());
	    return;
	}



	iRoleAdmin radmin = null;
	iRoleLeader rlead = null;
	iRolePersonalist rper = null;
	for (iPrivilegedRole rl : roles) //test existujicich roli
	{
	    if (rl instanceof iRoleAdmin)
	    {
		System.out.println("  Admin permission...");
		radmin = (iRoleAdmin) rl;
	    }
	    else if (rl instanceof iRoleLeader)
	    {
		System.out.println("  Leader permission...");
		rlead = (iRoleLeader) rl;
	    }
	    else if (rl instanceof iRolePersonalist)
	    {
		System.out.println("  Personalist permission...");
		rper = (iRolePersonalist) rl;
	    }
	}


	if (radmin != null) ///pokud je admin
	{
	    try
	    {
		iUserManagerAdmin uma = radmin.getUserManagerAdmin(); // tak ziskat userManager pro adminy
		
		uma.createAbility(new Ability("Abilita", "Totalni popisek", 666)); //vytvorit novou schopnost (pri vytvareni nehraje level roli)
	    }
	    catch (nqException e)
	    {
		System.out.printf("Fail! Exception %s, message: %s\n", e.getType().toString(), e.getMessage());
	    }
	}


    }
}
