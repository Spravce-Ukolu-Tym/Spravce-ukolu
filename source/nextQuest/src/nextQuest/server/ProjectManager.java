package nextQuest.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import nextQuest.ifc.*;

public class ProjectManager extends UnicastRemoteObject implements iProjectManager
{

    private final Connection con;
    private final User usr;

    public ProjectManager(Connection con, User usr) throws RemoteException
    {
	this.con = con;
	this.usr = usr;
    }

    @Override
    public void createProject(String Name, UserInfo Leader,int Priority) throws nqException, RemoteException
    {
	System.out.printf("Create project %s (Leader: %s)\n", Name, Leader.getName());

	PreparedStatement stat;
	try
	{
	    stat = this.con.prepareStatement("INSERT INTO `Projects` (`idUserCreatedBy`, `idLeader`, `Name`) VALUES (?, ?, ?)");

	    stat.setInt(1, this.usr.getID());
	    stat.setInt(2, Leader.getID());
	    stat.setString(3, Name);


	    stat.executeUpdate();

	}
	catch (SQLException ex)
	{
	    throw new nqException(nqExceptionType.DBError, String.format("DB Error (%d): %s", ex.getErrorCode(), ex.getMessage()));
	}
    }

    @Override
    public Project[] listProjects() throws RemoteException, nqException
    {
	List<Project> pr = new ArrayList<Project>();

	try
	{
	    PreparedStatement stat = this.con.prepareStatement("SELECT `idProject`, `idUserCreatedBy`, `idLeader`, `Name` FROM Projects");
	    ResultSet rs = stat.executeQuery();
	    while (rs.next())
	    {
		UserInfo cb = UserManager.getUserByID(rs.getInt("idUserCreatedBy"), con);
		UserInfo ld = UserManager.getUserByID(rs.getInt("idLeader"), con);

		pr.add(new Project(rs.getInt("idProject"), rs.getString("Name"), cb, ld,1));
	    }
	    
	    return pr.toArray(new Project[pr.size()]);
	}
	catch (SQLException e)
	{
	    throw new nqException(nqExceptionType.ServerError, "Server error : ".concat(e.getMessage()));
	}
    }
}
