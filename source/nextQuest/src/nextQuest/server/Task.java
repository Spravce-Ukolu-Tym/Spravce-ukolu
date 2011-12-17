package nextQuest.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import nextQuest.ifc.*;
import java.util.Date;

public class Task extends UnicastRemoteObject implements iTask, Comparable<Task>
{

    private int idTask;
    private int idProject;
    private int idUserCreatedBy;
    private Date DeadlineDate;
    private Date CreationDate;
    private String Description;
    private int MaxHours;
    private boolean isSubtask;
    private int Priority;
    private String Title;
    private eTaskStatus Status;
    private String Name;
    Connection con;

    public Task(Connection con, int idTask, int idProject, int idUserCreatedBy, Date DeadlineDate,
		Date CreationDate, String Description, int MaxHours, boolean isSubtask,
		int Priority, String Title, eTaskStatus Status) throws RemoteException, nqException
    {
	this.con = con;

	this.idTask = idTask;
	this.idProject = idProject;
	this.idUserCreatedBy = idUserCreatedBy;
	this.DeadlineDate = DeadlineDate;
	this.CreationDate = CreationDate;
	this.Description = Description;
	this.MaxHours = MaxHours;
	this.isSubtask = isSubtask;
	this.Priority = Priority;
	this.Title = Title;
	this.Status = Status;

    }

    void setStatus(eTaskStatus status) throws nqException
    {
	PreparedStatement stat;
	try
	{
	    stat = this.con.prepareStatement("UPDATE `Tasks` SET `TaskStatus` = ? WHERE `idTask` = ?");
	    stat.setString(1, status.toString());
	    stat.setInt(2, this.idTask);
	}
	catch(SQLException ex)
	{
	    throw new nqException(nqExceptionType.ServerError, "Server error : ".concat(ex.getMessage()));
	}
    }
    
    void assignTo(UserInfo usr) throws nqException
    {
	PreparedStatement stat;
	try
	{
	    stat = this.con.prepareStatement("UPDATE `Tasks` SET `idUserAssignedTo` = ? WHERE `idTask` = ?");
	    stat.setInt(1, usr.getID());
	    stat.setInt(2, this.idTask);
	}
	catch(SQLException ex)
	{
	    throw new nqException(nqExceptionType.ServerError, "Server error : ".concat(ex.getMessage()));
	}
    }
    
    @Override
    public void accept() throws RemoteException, nqException
    {
	this.setStatus(eTaskStatus.IN_PROGRESS);
    }

    @Override
    public void reject(String Reason) throws RemoteException, nqException
    {
	this.setStatus(eTaskStatus.REJECTED);
    }

    @Override
    public void returnTask() throws RemoteException, nqException
    {
	this.setStatus(eTaskStatus.APPROVE_WAITING);
    }

    int getID() throws RemoteException, nqException
    {
	return idTask;
    }

    @Override
    public UserInfo getCreatorInfo() throws RemoteException, nqException
    {
	return UserManager.getUserByID(this.idUserCreatedBy, this.con);
    }

    @Override
    public Date getDeadlineDate() throws RemoteException, nqException
    {
	return DeadlineDate;
    }

    @Override
    public Date getCreationDate() throws RemoteException, nqException
    {
	return CreationDate;
    }

    @Override
    public String getDescription() throws RemoteException, nqException
    {
	return Description;
    }

    @Override
    public Integer getMaxHours() throws RemoteException, nqException
    {
	return MaxHours;
    }

    @Override
    public Boolean isSubtask() throws RemoteException, nqException
    {
	return isSubtask;
    }

    @Override
    public iTask[] getSubtasks() throws RemoteException, nqException
    {
	PreparedStatement stat;
	try
	{
	    stat = this.con.prepareStatement("SELECT `idTask`, `idProject`, `idUserCreatedBy`, `idUserAssignedTo`, `idParentTask`, `TaskStatus`, `Title`,"
		    + " `Description`, `Priority`, `CreationDate`, `DeadlineDate`, `MaxHours`, `isSubTask`, `Rating` "
		    + "FROM Tasks WHERE isSubTask = 1 AND `idParentTask` = ? ");

	    stat.setInt(1, this.idTask);

	    return TaskManager.getTasks(stat, this.con);
	}
	catch (SQLException ex)
	{
	    throw new nqException(nqExceptionType.ServerError, "Server error : ".concat(ex.getMessage()));
	}
    }

    @Override
    public int getPercentage() throws RemoteException, nqException
    {
	PreparedStatement stat;
	//stat = this.con.prepareStatement("SELECT COUNT(*) FROM `Tasks` WHERE isSubTask = 1 AND `idParentTask` = ? AND ");
	
	return 50;
    }
    
    @Override
    public Ability[] getNecessaryAbilities() throws RemoteException, nqException
    {
	return null; //!--TODO--!MS!
    }

    @Override
    public int getPriority() throws RemoteException, nqException
    {
	return Priority;
    }

    @Override
    public String getTitle() throws RemoteException, nqException
    {
	return Title;
    }

    @Override
    public eTaskStatus getStatus() throws RemoteException, nqException
    {
	return Status;
    }

    @Override
    public Project getProjectInfo() throws nqException, RemoteException
    {
	try
	{
	    return Project.GetProjectByID(this.idProject, this.con);
	}
	catch (SQLException ex)
	{
	    throw new nqException(nqExceptionType.ServerError, "Server error : ".concat(ex.getMessage()));
	}
    }

    @Override
    public String getName() throws nqException, RemoteException
    {
	return Name;
    }

    @Override
    public int compareTo(Task o) //nemuze mit nic extra co by "throws" 
    {
	try
	{
	    return CreationDate.compareTo(o.getCreationDate());
	}
	catch (Exception e)
	{
	    return -1; //co s tim?
	}
    }

    @Override
    public iTask getthis() throws RemoteException, nqException
    {
	return this;
    }
}
