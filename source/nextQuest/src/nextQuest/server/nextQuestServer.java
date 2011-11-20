package nextQuest.server;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class nextQuestServer
{

    public static void main(String[] args) throws RemoteException, AlreadyBoundException, NotBoundException, InterruptedException, ClassNotFoundException, SQLException
    {
	System.out.println("Server starting...");

	String dbname = "nqdata";
	String dbuser = "root";
	String dbpass = "";
	String mysql = "localhost";
	int myport = 3306;
	
	
	for(String arg : args)
	{
	    String[] pr = arg.split("=");
	    
	    if(pr.length != 2){
		System.out.printf("ERROR: Bad argument format '%s'\n", arg);
		continue;}
	    
	    
	    if(pr[0].equals("mysql"))
		mysql = pr[1];
	    else if(pr[0].equals("myport"))
		myport = Integer.getInteger(pr[1]);
	    else if (pr[0].equals("dbname"))
		dbname = pr[1];
	    else if (pr[0].equals("dbuser"))
		dbuser = pr[1];
	    else if (pr[0].equals("dbpass"))
		dbpass = pr[1];
	    else
		System.out.printf("ERROR: Unknown argument '%s'\n", pr[0]);
	}
	
	
	System.out.printf("Connecting to mysql @ %s:%d\n", mysql, myport);
	Class.forName("com.mysql.jdbc.Driver");
	
	String constr = String.format("jdbc:mysql://%s:%d/%s", mysql, myport, dbname);
	
	Connection con = DriverManager.getConnection(constr, dbuser, dbpass);
	
	System.out.printf("Connected...\n");
	
	
	
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
	    System.out.println("Creating registry, takze to muze blbnout!");
	    LocateRegistry.createRegistry(1099);
	    r = LocateRegistry.getRegistry();
	}


	System.out.println("Binding...");
	r.bind("con", new Connector(con));
	System.out.println("Bound...");

	
	
    }
}
