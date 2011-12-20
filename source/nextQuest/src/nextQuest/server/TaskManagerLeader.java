package nextQuest.server;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import nextQuest.ifc.*;

public class TaskManagerLeader extends TaskManager implements iTaskManagerLeader
{

    public TaskManagerLeader(Connection con, User usr) throws RemoteException
    {
	super(con, usr);
    }

    @Override
    public void approveTask(iTask t) throws RemoteException, nqException
    {
	Task tt = taskPool.get().getimpl(t);


	tt.setStatus(eTaskStatus.COMPLETED);

    }

    @Override
    public void assignTaskAutomatically(iTask t) throws RemoteException, nqException
    {
	throw new nqException(nqExceptionType.GeneralError, "Not supported yet.");
    }

    @Override
    public void assignTasksManually(iTask t, UserInfo ui) throws RemoteException, nqException
    {
	Task tt = taskPool.get().getimpl(t);

	tt.setStatus(eTaskStatus.ASSIGNED);
	tt.assignTo(ui);

	//throw new nqException(nqExceptionType.GeneralError, String.format("Bad parameter (not instanceof Task - %s)"));

    }

    @Override
    public iTask createTask(Project proj, iTask parent, String title, String description, int priority, Date deadline, int maxhours, Ability[] ablist) throws RemoteException, nqException
    {
	System.out.printf("Create task, parent = %s\n", parent == null ? "null" : "something");

	PreparedStatement stat;
	try
	{
	    stat = this.con.prepareStatement("INSERT INTO `Tasks` "
		    + "(`idProject`, `idUserCreatedBy`, `idParentTask`, `TaskStatus`, `Title`, `Description`, `Priority`, `CreationDate`, `DeadlineDate`, `MaxHours`, `isSubTask`) VALUES"
		    + " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

	    stat.setInt(1, proj.getID());
	    stat.setInt(2, this.u.getID());
	    stat.setInt(3, parent == null ? -1 : 
		    ((parent instanceof Task) ? ((Task)parent).getID() : taskPool.get().getimpl(parent).getID())
		    
		    ) ;
	    stat.setString(4, eTaskStatus.CREATED.toString());
	    stat.setString(5, title);
	    stat.setString(6, description);
	    stat.setInt(7, priority);
	    stat.setObject(8, new Date());
	    stat.setObject(9, deadline);
	    stat.setInt(10, maxhours);
	    stat.setBoolean(11, parent != null);

	    if (stat.executeUpdate() != 1)
	    {
		throw new nqException(nqExceptionType.DBError, "DB error");
	    }

	    int id = -1;

	    ResultSet rs = stat.getGeneratedKeys();
	    while (rs.next())
	    {
		if (id != -1)
		{
		    throw new nqException(nqExceptionType.DBError, "Frozen universe broke killed my cat");
		}

		id = rs.getInt(1);
		if (ablist != null)
		{
		    for (Ability ab : ablist)
		    {
			stat = this.con.prepareStatement("INSERT INTO `TaskAbility` (`idTask`, `idAbility`, `Level`) VALUES (?, ?, ?)");
			stat.setInt(1, id);
			stat.setInt(2, ab.getID());
			stat.setInt(3, ab.getLevel());
		    }
		}
	    }

	    if (id != -1)
	    {
		return TaskManager.getTaskByID(id, con);
	    }


	    throw new nqException(nqExceptionType.ServerError, "Server error : nothing created..");
	}
	catch (SQLException ex)
	{
	    throw new nqException(nqExceptionType.ServerError, "Server error : ".concat(ex.getMessage()));
	}
    }

    @Override
    public iTask[] getTasksByProject(Project pi) throws RemoteException, nqException
    {
	PreparedStatement stat;
	try
	{
	    stat = this.con.prepareStatement("SELECT `idTask`, `idProject`, `idUserCreatedBy`, `idUserAssignedTo`, `idParentTask`, `TaskStatus`, `Title`,"
		    + " `Description`, `Priority`, `CreationDate`, `DeadlineDate`, `MaxHours`, `isSubTask`, `Rating` "
		    + "FROM Tasks WHERE isSubTask = 0 AND idProject = ?");

	    stat.setInt(1, pi.getID());

	    return TaskManager.getTasks(stat, this.con);
	}
	catch (SQLException ex)
	{
	    throw new nqException(nqExceptionType.ServerError, "Server error : ".concat(ex.getMessage()));
	}
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
