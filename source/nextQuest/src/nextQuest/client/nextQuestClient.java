package nextQuest.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import nextQuest.ifc.*;
import nextQuest.server.Ability;

public class nextQuestClient
{

    public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException, NoSuchAlgorithmException
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
	    long sid = mg.createLoginSession();//ziskam login session ID

	    String salt = mg.getPasswordSalt(sid); //ziskam "sul"... 

	    pass = Static.MD5(Static.MD5(pass).concat(salt)); // zahashovat heslo a prihashovat k tomu sul

	    usr = mg.Login(sid, name, pass); //pokusit se prihlasit
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





	//CreateAbilities(radmin);
	CreateFewUsers(radmin);

    }

    private static void CreateFewUsers(iRoleAdmin radmin) throws RemoteException
    {
	iUserManagerAdmin uma;
	try
	{
	    uma = radmin.getUserManagerAdmin(); // tak ziskat userManager pro adminy
	}
	catch (nqException e)
	{
	    System.out.printf("Fail! Exception %s, message: %s\n", e.getType().toString(), e.getMessage());
	    return;
	}

	try
	{
	    uma.createUser("pepanov", "Pepa Novák", Static.MD5("heslo"), false, false);
	    uma.createUser("mychaso", "Mychal Soušek", Static.MD5("heslo"), false, true); //leader
	    uma.createUser("lachike", "Lachim Kečuos", Static.MD5("heslo"), false, true); //leader

	}
	catch (nqException e)
	{
	    System.out.printf("Fail! Exception %s, message: %s\n", e.getType().toString(), e.getMessage());
	}
	catch (NoSuchAlgorithmException nse)
	{
	    System.out.println("Java sux!");
	}
    }

    private static void CreateAbilities(iRoleAdmin radmin) throws RemoteException
    {
	if (radmin != null) ///pokud je admin
	{
	    String[] abils = new String[]
	    {
		"Java", "C#", "PHP", "SQL", "Python", "Perl", "Brainfuck", "Assembler", "Windows", "Linux", "Bash", "Pascal", "Visual Basic", "VHDL", "UML", "XML", "HTML", "CSS"
	    };


	    iUserManagerAdmin uma;
	    try
	    {
		uma = radmin.getUserManagerAdmin(); // tak ziskat userManager pro adminy
	    }
	    catch (nqException e)
	    {
		System.out.printf("Fail! Exception %s, message: %s\n", e.getType().toString(), e.getMessage());
		return;
	    }

	    for (String abl : abils)
	    {
		try
		{

		    uma.createAbility(new Ability(abl, String.format("Uživatel je schopen používat: %s", abl), 666)); //vytvorit novou schopnost (pri vytvareni nehraje level roli)
		}
		catch (nqException e)
		{
		    System.out.printf("Fail! Exception %s, message: %s\n", e.getType().toString(), e.getMessage());
		}
	    }
	}
    }
}
