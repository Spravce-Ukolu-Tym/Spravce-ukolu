package nextQuest.server;

import java.sql.SQLException;
import nextQuest.ifc.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserManager extends UnicastRemoteObject implements iUserManager
{

    protected Connection con;
    protected User user;

    public UserManager(Connection con, User u) throws RemoteException
    {
	this.con = con;
	this.user = u;
    }

    @Override
    public UserInfo[] findUsersByAbilities()
    {
	return null;
    }

    @Override
    public Ability[] listAblities() throws RemoteException, nqException
    {
	try
	{
	    PreparedStatement ps = this.con.prepareStatement(
		    "SELECT idAbility, `Name`, `Description` FROM AbilityList");

	    List<Ability> al = new ArrayList<Ability>();
	    
	    
	    ResultSet rs = ps.executeQuery();
	    while(rs.next())
	    {
		al.add(new Ability(rs.getByte("idAbility"), rs.getString("Name"), rs.getString("Description")));
	    }
	    
	    return al.toArray(new Ability[al.size()]);
	
	}
	catch (SQLException e)
	{
	    throw new nqException(nqExceptionType.ServerError, "Server error : ".concat(e.getMessage()));
	}
    }

    @Override
    public UserInfo[] listAllUsers() throws RemoteException, nqException
    {
	try
	{
	    PreparedStatement ps = this.con.prepareStatement(
		    "SELECT idUser, LoginName, Name, permAdmin, permLeader, permPersonalist FROM Users");

	    return getUserList(ps);
	}
	catch (SQLException e)
	{
	    throw new nqException(nqExceptionType.ServerError, "Server error : ".concat(e.getMessage()));
	}
    }

    private UserInfo[] getUserList(PreparedStatement sql) throws SQLException
    {
	List<UserInfo> lu = new ArrayList<UserInfo>();
	ResultSet rs = sql.executeQuery();
	while (rs.next())
	{
	    lu.add(fillUserInfo(rs));
	}

	return lu.toArray(new UserInfo[lu.size()]);
    }

    private UserInfo fillUserInfo(ResultSet rs) throws SQLException
    {
	return new UserInfo(rs.getInt("iduser"),
			    rs.getString("name"),
			    rs.getString("LoginName"),
			    rs.getByte("permAdmin") == 1,
			    rs.getByte("permLeader") == 1,
			    rs.getByte("permPersonalist") == 1);
    }
}
