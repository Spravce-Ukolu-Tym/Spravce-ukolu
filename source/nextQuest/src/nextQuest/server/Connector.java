package nextQuest.server;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import nextQuest.ifc.*;
import java.rmi.server.*;
import java.rmi.*;


public class Connector extends UnicastRemoteObject  implements iConnector
{
    Connection con;
    public Connector() throws RemoteException
    {
	
    }

    Connector(Connection con) throws RemoteException
    {
	this.con = con;
    }
    
    @Override
    public iUser Login(String username, String pass) throws RemoteException, nqException
    {
	System.out.println("Login!");
	try
	{
	    Statement stat = this.con.createStatement();
	    
	    ResultSet rs = stat.executeQuery(String.format("SELECT idUser, LoginName, Name, permAdmin, permLeader, permPersonalist FROM Users WHERE LoginName = '%s' AND Password = md5('%s')", username, pass));
	    
	    if(rs.next())
	    {
		return new User(rs.getInt("idUser"), rs.getString("Name"), rs.getString("LoginName"), rs.getByte("permAdmin") == 1, rs.getByte("permLeader") == 1, rs.getByte("permPersonalist") == 1, con);
	    }
	    else
	    {
		throw new nqException(nqExceptionType.BadLogin, "Bad login - wrong password or username");
	    }
	}
	catch (SQLException ex)
	{
	    throw new nqException(nqExceptionType.ServerError, "Server error : ".concat(ex.getMessage()));
	}
    }
}
