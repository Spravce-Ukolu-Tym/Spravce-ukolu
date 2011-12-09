package nextQuest.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import nextQuest.ifc.*;

public class TaskManager extends UnicastRemoteObject implements iTaskManager
{

    private Connection con;
    private User u;

    public TaskManager(Connection con, User u) throws RemoteException
    {
	this.con = con;
	this.u = u;
    }

    @Override
    public Task[] getAssingnedTasks() throws nqException, RemoteException
    {
	PreparedStatement stat;
	try
	{
	    stat = this.con.prepareStatement("SELECT `idTask`, `idProject`, `idUserCreatedBy`, `idUserAssignedTo`, `idParentTask`, `TaskStatus`, `Title`,"
		    + " `Description`, `Priority`, `CreationDate`, `DeadlineDate`, `MaxHours`, `isSubTask`, `Rating` "
		    + "FROM Tasks WHERE isSubTask = 0 AND idUserAssignedTo = ?");

	    stat.setInt(1, this.u.getID());

	    return getTasks(stat);
	}
	catch (SQLException ex)
	{
	    throw new nqException(nqExceptionType.ServerError, "Server error : ".concat(ex.getMessage()));
	}

    }

    static Task fillTask(ResultSet rs) throws SQLException, RemoteException, nqException
    {
	return new Task(rs.getInt("idTask"), rs.getInt("idProject"), rs.getInt("idUserCreatedBy"), rs.getDate("DeadlineDate"), rs.getDate("CreationDate"),
			rs.getString("Description"), rs.getInt("MaxHours"), rs.getByte("isSubtask") == 1, rs.getInt("Priority"), rs.getString("Title"), eTaskStatus.valueOf(rs.getString("TaskStatus")));
    }

    @Override
    public void updateTask() throws RemoteException
    {
	throw new UnsupportedOperationException("NetusimKCemuTotoByloZamyslenoException()");
    }

    static Task[] getTasks(PreparedStatement stat) throws SQLException, RemoteException, nqException
    {
	List<Task> tl = new ArrayList<Task>();

	ResultSet rs = stat.executeQuery();
	while (rs.next())
	{
	    tl.add(fillTask(rs));
	}


	return tl.toArray(new Task[tl.size()]);
    }
}
