package nextQuest.server;

import java.rmi.RemoteException;
import java.sql.*;
import nextQuest.ifc.*;
import nextQuest.ifc.iUserManagerAdmin;
import nextQuest.ifc.nqException;
import nextQuest.ifc.nqExceptionType;

public class UserManagerAdmin extends UserManager implements iUserManagerAdmin {
    public UserManagerAdmin(Connection con, User u)  throws RemoteException
    {
	super(con, u);
    }

    @Override
    public void createAbility(Ability a) throws RemoteException, nqException
    {
	System.out.printf("Create ability: %s, %s\n", a.getName(), a.getDescription());
	
	PreparedStatement stat;
	try
	{
	    stat = this.con.prepareStatement("INSERT INTO `AbilityList` (`Name`, `Description`) VALUES(?, ?)");
	
	    stat.setString(1, a.getName());
	    stat.setString(2, a.getDescription());
	    
	    int res = stat.executeUpdate();	    
	}
	catch (SQLException ex)
	{
	    throw new nqException(nqExceptionType.DBError, String.format("DB Error (%d): %s", ex.getErrorCode(), ex.getMessage()));
	}
    }

    @Override
    public void removeAbility(Ability a) throws RemoteException, nqException
    {
	System.out.printf("Remove ability: [%d] %s, %s\n",a.getID(), a.getName(), a.getDescription());
	
	PreparedStatement stat;
	try
	{
	    if(a.getID() == -1)
	    {
		stat = this.con.prepareStatement("REMOVE FROM `AbilityList` WHERE `Name` = ?");	
		stat.setString(1, a.getName());
	    }
	    else
	    {
		stat = this.con.prepareStatement("REMOVE FROM `AbilityList` WHERE `idability` = ?");	
		stat.setInt(1, a.getID());
	    }	    
	    int res = stat.executeUpdate();	    
	    
	    if(res == 0)
		throw new nqException(nqExceptionType.DBSoftError, "Nothing deleted");
	}
	catch (SQLException ex)
	{
	    throw new nqException(nqExceptionType.DBError, String.format("DB Error (%d): %s", ex.getErrorCode(), ex.getMessage()));
	}
    }

    @Override
    public void createUser(String LoginName, String Name, String primaryPassword, Boolean leader, Boolean personalist) throws RemoteException, nqException
    {
	System.out.printf("Create user %s (login: %s)\n", Name, LoginName);
	
	PreparedStatement stat;
	try
	{
	    stat = this.con.prepareStatement("INSERT INTO Users (`idUserCreatedBy`, `LoginName`, `Name`, `Password`, `permLeader`, `permPersonalist`) VALUES (?, ?, ?, ?, ? ,?)");
	    
	    stat.setInt(1, this.user.getID());
	    stat.setString(2, LoginName);
	    stat.setString(3, Name);
	    stat.setString(4, primaryPassword);
	    stat.setByte(5, (byte)(leader ? 1 : 0));
	    stat.setByte(6, (byte)(personalist ? 1 : 0));
	    
	    
	    stat.executeUpdate();
	    
	}
	catch (SQLException ex)
	{
	    throw new nqException(nqExceptionType.DBError, String.format("DB Error (%d): %s", ex.getErrorCode(), ex.getMessage()));
	}
    }

    @Override
    public void editUser(iUser usr, String LoginName, String Name, String primaryPassword, Boolean personalist, Boolean leader) throws RemoteException, nqException
    {
	if(true) throw new nqException(nqExceptionType.ServerError, "Tohle jsem jeste nenaimplementoval");
	
	System.out.printf("Edit user %s (login: %s)\n", Name, LoginName);
	
	PreparedStatement stat;
	try
	{
	    //!--TODO--! pripadne pridat casy last edit a edited by 
	    
	    
	    
	    stat = this.con.prepareStatement("UPDATE Users SET `LoginName` = ?, `Name` = ?, `permLeader` = ?, `permPersonalist` = ? WHERE userid = ?");
	    
	    stat.setString(1, LoginName);
	    stat.setString(2, Name);
	    stat.setString(3, primaryPassword);
	    /*stat.setByte(, (byte)(leader ? 1 : 0));*/
	    stat.setByte(6, (byte)(personalist ? 1 : 0));
	    
	    
	    stat.executeUpdate();
	    
	}
	catch (SQLException ex)
	{
	    throw new nqException(nqExceptionType.DBError, String.format("DB Error (%d): %s", ex.getErrorCode(), ex.getMessage()));
	}
    }

    @Override
    public void updateUserAbilities(iUser usr, Ability[] abs)
    {
	throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void removeUser(iUser usr) throws RemoteException, nqException
    {
	throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Ability[] listAbilitiesByUser(iUser usr) throws RemoteException, nqException
    {
	throw new UnsupportedOperationException("Not supported yet.");
    }
}
