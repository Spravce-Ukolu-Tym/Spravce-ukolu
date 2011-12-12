package nextQuest.server;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import nextQuest.ifc.*;

public class TaskManagerLeader extends TaskManager implements iTaskManagerLeader {
    public TaskManagerLeader(Connection con, User usr)  throws RemoteException
    {
	super(con, usr);
    }
    
    @Override
    public void createTask() {
    }

    @Override
    public void assignTaskAutomatically() {
    }

    @Override
    public void assignTasksManually() {
    }

    @Override
    public void approveTask() {
    }

    @Override
    public iTask[] getTasksByProject(Project pi) throws RemoteException, nqException
    {
	throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public iTask[] getTasksByUser(UserInfo ui) throws RemoteException, nqException
    {
	PreparedStatement stat;
	try
	{
	    stat = this.con.prepareStatement("SELECT `idTask`, `idProject`, `idUserCreatedBy`, `idUserAssignedTo`, `idParentTask`, `TaskStatus`, `Title`,"
		    + " `Description`, `Priority`, `CreationDate`, `DeadlineDate`, `MaxHours`, `isSubTask`, `Rating` "
		    + "FROM Tasks WHERE isSubTask = 0 AND idUserAssignedTo = ?");

	    stat.setInt(1, ui.getID());

	    return TaskManager.getTasks(stat, this.con);
	}
	catch (SQLException ex)
	{
	    throw new nqException(nqExceptionType.ServerError, "Server error : ".concat(ex.getMessage()));
	}
    }

    @Override
    public iTask[] getCreatedTasks() throws RemoteException, nqException
    {
	throw new UnsupportedOperationException("Not supported yet.");
    }
}
