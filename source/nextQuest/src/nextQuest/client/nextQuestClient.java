package nextQuest.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Scanner;
import nextQuest.ifc.*;
import nextQuest.server.Ability;
import nextQuest.server.Project;
import nextQuest.server.UserInfo;

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

	iUserManagerAdmin uma = null;
	iTaskManagerLeader tml = null;
	iProjectManager prm = null;
	try
	{
	    if (radmin != null)
	    {
		uma = radmin.getUserManagerAdmin(); // tak ziskat userManager pro adminy
	    }
	    else if (rper != null)
	    {
		uma = rper.getUserManagerAdmin();
	    }

	    if (radmin != null)
	    {
		prm = radmin.getProjectManager();
	    }


	    if (radmin != null)
	    {
		tml = radmin.getTaskManagerLeader();
	    }
	    else if (rlead != null)
	    {
		tml = rlead.getTaskManagerLeader();
	    }
	}
	catch (nqException e)
	{
	    System.out.printf("Fail! Exception %s, message: %s\n", e.getType().toString(), e.getMessage());
	    return;
	}



	CreateSomeTasks(tml, uma, prm);

	//CreateAbilities(radmin);
	//CreateFewUsers(uma);
	/*
	System.out.println("List abilities:");
	ListAbilities(radmin);
	
	System.out.println("\n--------------------------\nList users:");
	ListUsers(uma, tml);
	
	System.out.println("\n--------------------------\nList projects:");
	ListProjects(prm);
	*/
	
    }

    private static void CreateSomeTasks(iTaskManagerLeader tml, iUserManagerAdmin uma, iProjectManager prm) throws RemoteException
    {
	try
	{
	    Project[] projs = prm.listProjects();
	    Project pr1 = projs[0];
	    Project pr2 = projs[1];
	    Project pr3 = projs[2];


	    Ability[] abls = uma.listAblities();

	    //pri vytvareni ukolu (ci usera) a nastavovani abilit je treba nastavit level, a to se dela presne takhle:
	    abls[0].setLevel(3);
	    abls[1].setLevel(2);
	    abls[2].setLevel(4);
	    abls[3].setLevel(5);
	    abls[4].setLevel(1);
	    abls[5].setLevel(3);
	    abls[6].setLevel(3);
	    abls[7].setLevel(2);
	    abls[8].setLevel(4);
	    abls[9].setLevel(5);
	    abls[10].setLevel(1);
	    abls[11].setLevel(3);
	    //samozrejme, vzdycky to vezmu podle nejakyho - hotovyho - seznamu!
	    
	    
	    /*cyklus TASK:
	kdo: *  [leader]   (leader)	(user)		(user)		 (leader)
	co   *	(create)   (assign)	accept		return		 accept
	stav * CREATED -> ASSIGNED -> IN_PROGRESS -> APPROVE_WAITING -> COMPLETED
	     *                      |
	     *                      |   (user)      (leader)
	     *                      |   reject	    accept
	     *                      |_ REJECTED -> CREATED
	     * 
	     */
	    
	    
	    UserInfo[] abs = uma.listAllUsers();

	    for (UserInfo a : abs)
	    {
		if (a.getLoginName().equals("agatlus"))
		{

		    //parent je null, pokud je to "naduloha". Abilities muzou - mely by byt null, pokud je naduloha. 
		    //spravne by i MaxHours melo byt pocitano jakou soucet maxhours poduloh
		    iTask tsk = tml.createTask(pr1, null, "Vyrobit sporak", "Soucastky sezenes na aukru, zbytek snad umis...", 5, new Date(2012, 5, 12), 666, null);
		    
		    if(tsk == null)
			System.out.println("waaat?");
		    
		    tml.assignTasksManually(tsk, a);
		    

		    tml.assignTasksManually(tml.createTask(pr1, tsk, "Trouba", "Trouba je soucasti sporaku. Vyrobit taky!", 3, new Date(2012, 3, 1), 111, new Ability[]	{abls[0]}), a);
		    tml.assignTasksManually(tml.createTask(pr1, tsk, "Plynovy horak", "Bez toho to nefunguje!", 3, new Date(2012, 3, 1), 111, new Ability[]	{abls[1]}), a);
		    tml.assignTasksManually(tml.createTask(pr1, tsk, "Ovladaci panel", "Co nejslozitejsi, aby to uzivatel nepochopil a bouchlo mu to doma", 3, new Date(2012, 4, 5), 111, new Ability[]	{abls[2]}), a);
		    tml.assignTasksManually(tml.createTask(pr1, tsk, "Testovaci hrnec", "Smaltovany hrnec co se bude pouzivat na betatesting tvojho sporaku", 3, new Date(2012, 2, 1), 111, new Ability[]	{abls[3]}), a);
		    tml.assignTasksManually(tml.createTask(pr1, tsk, "Ohen", "Vynalezni ohen!", 3, new Date(2012, 1, 1), 111, new Ability[]	{abls[4]}), a);
		    tml.assignTasksManually(tml.createTask(pr1, tsk, "Plyn", "Vyrob trosku plynu!", 3, new Date(2012, 2, 1), 111, new Ability[]	{abls[5], abls[6]}), a);
		}
		
		else if(a.getLoginName().equals("tomamar"))
		{
		    iTask tsk = tml.createTask(pr2, null, "Napis kod", "Nevim co sem uz psat a nebavi me to vymejslet", 5, new Date(2012, 5, 12), 666, null);
		    tml.assignTasksManually(tsk, a);

		    tml.assignTasksManually(tml.createTask(pr2, tsk, "Detaily kodu", "Kod v necem", 3, new Date(2012, 3, 1), 111, new Ability[]	{abls[10]}), a);
		    tml.assignTasksManually(tml.createTask(pr2, tsk, "Jeste vetsi detaily", "A kod v necem jinym", 3, new Date(2012, 3, 1), 111, new Ability[]	{abls[11]}), a);
		    
		}
	    }
	}
	catch (nqException e)
	{
	    System.out.printf("Fail! Exception %s, message: %s\n", e.getType().toString(), e.getMessage());
	    return;
	}
    }

    private static void ListProjects(iProjectManager prm) throws RemoteException
    {
	try
	{
	    Project[] projs = prm.listProjects();

	    for (Project pr : projs)
	    {
		System.out.printf("Project %s:\n  Leader: %s (%s)\n  Created by: %s (%s)\n", pr.getName(), pr.getLeader().getName(), pr.getLeader().getLoginName(), pr.getUserCreatedBy().getName(), pr.getUserCreatedBy().getLoginName());
	    }
	}
	catch (nqException e)
	{
	    System.out.printf("Fail! Exception %s, message: %s\n", e.getType().toString(), e.getMessage());
	    return;
	}
    }

    private static void ListUsers(iUserManagerAdmin uma, iTaskManagerLeader tml) throws RemoteException
    {
	try
	{
	    UserInfo[] abs = uma.listAllUsers();

	    for (UserInfo a : abs)
	    {
		System.out.printf("User: %s ('%s')\n", a.getName(), a.getLoginName());

		iTask[] tasks = tml.getTasksByUser(a);
		for (iTask tsk : tasks)
		{
		    UserInfo creator = tsk.getCreatorInfo();
		    System.out.printf("  Task: %s (%s) by %s (%s)\n", tsk.getTitle(), tsk.getDescription(), creator.getLoginName(), creator.getName());
		}

	    }
	}
	catch (nqException e)
	{
	    System.out.printf("Fail! Exception %s, message: %s\n", e.getType().toString(), e.getMessage());
	    return;
	}


    }

    private static void ListAbilities(iRoleAdmin radmin) throws RemoteException
    {
	try
	{
	    iUserManagerAdmin uma = radmin.getUserManagerAdmin();
	    Ability[] abs = uma.listAblities();

	    for (Ability a : abs)
	    {
		System.out.printf("Ability: %s means '%s'\n", a.getName(), a.getDescription());
	    }
	}
	catch (nqException e)
	{
	    System.out.printf("Fail! Exception %s, message: %s\n", e.getType().toString(), e.getMessage());
	    return;
	}
    }

    private static void CreateFewUsers(iUserManagerAdmin uma) throws RemoteException
    {
	if (uma == null)
	{
	    System.out.printf("!! uma = null !!");
	    return;
	}


	try
	{
	    //uma.createUser("pepanov", "Pepa Novák", Static.MD5("heslo"), false, false);
	    //uma.createUser("mychaso", "Mychal Soušek", Static.MD5("heslo"), false, true); //leader
	    //uma.createUser("lachike", "Lachim Kečuos", Static.MD5("heslo"), false, true); //leader
	    //uma.createUser("vseleader", "Leader Všeho", Static.MD5("heslo"),true, false); 
	    //uma.createUser("jarmihus", "Jarmila Hustá", Static.MD5("heslo"), false, false);
	    //uma.createUser("agatlus", "Agáta Tlustá", Static.MD5("heslo"), false, false);
	    //uma.createUser("tomamar", "Tomáš Marný", Static.MD5("heslo"), false, false);
	    //uma.createUser("jezikri", "Jéžiš Kristůs", Static.MD5("heslo"), false, false);
	    uma.createUser("null", "null", Static.MD5("heslo"), false, false);
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
