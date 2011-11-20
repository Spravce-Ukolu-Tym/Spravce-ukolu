package nextQuest.server;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import nextQuest.ifc.*;
import java.rmi.server.*;
import java.rmi.*;
import java.util.HashMap;
import java.util.Random;


public class Connector extends UnicastRemoteObject  implements iConnector
{
    Connection con;
    HashMap<Long, String> sessions;
    Random rand;
    
    
    Connector(Connection con) throws RemoteException
    {
	this.con = con;
	this.rand = new Random(this.hashCode());
	this.sessions = new HashMap<Long, String>();
    }
    
    @Override
    public iUser Login(long session, String username, String pass) throws RemoteException, nqException
    {
	System.out.println("Login!");
	
	String salt;
	try
	{
	    salt = this.sessions.get(session);
	}
	catch(Exception e)
	{
	    throw new nqException(nqExceptionType.GeneralError, "Session fail: ".concat(e.getMessage()));
	}
	
	
	try
	{
	    PreparedStatement stat = this.con.prepareStatement("SELECT idUser, LoginName, Name, permAdmin, permLeader, permPersonalist FROM Users WHERE LoginName = ? AND MD5(CONCAT(Password, ?)) = ?");
	    
	    //System.out.printf("USR: %s\nSALT: %s\nPASS: %s\n", username, salt, pass);
	    
	    
	    stat.setString(1, username);
	    stat.setString(2, salt);
	    stat.setString(3, pass);
	    
	    
	    ResultSet rs = stat.executeQuery();
	    
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

    @Override
    public long createLoginSession() throws RemoteException
    {
	long ls;
	
	do
	{
	    ls = this.rand.nextLong();
	} while (this.sessions.containsKey(ls));
	
	byte[] rnd = new byte[64];
	this.rand.nextBytes(rnd);
	
	this.sessions.put(ls, Static.Base64encode(rnd));
	
	return ls;
    }

    @Override
    public String getPasswordSalt(long session) throws RemoteException
    {
	return this.sessions.get(session);
    }
}
