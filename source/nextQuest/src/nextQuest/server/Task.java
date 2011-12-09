package nextQuest.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import nextQuest.ifc.*;
import java.util.Date;

public class Task implements iTask  
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
    
    
    /* tohle tu nebude (to se bude ziskavat z DB*/
    private Task[] Subtasks;
    private Ability[] NecessaryAbilities;

    
    
    public Task(int idTask, int idProject, int idUserCreatedBy, Date DeadlineDate, 
		Date CreationDate, String Description, int MaxHours, boolean isSubtask, 
		int Priority, String Title, eTaskStatus Status) throws RemoteException, nqException
    {
	this.idTask = idTask;
	this.idProject = idProject;
	this.idUserCreatedBy = idUserCreatedBy;
	this.DeadlineDate = DeadlineDate;
	this.CreationDate= CreationDate;
	this.Description = Description;
	this.MaxHours = MaxHours;
	this.isSubtask = isSubtask;
	this.Priority = Priority;
	this.Title= Title;
	this.Status = Status;
	
    }
    
    
    
    @Override
    public void accept() throws RemoteException, nqException
    {
	
    }
    
    @Override
    public void reject(String Reason)throws RemoteException, nqException
    {
    }

   
    @Override
    public void returnTask()throws RemoteException, nqException
    {
    }

    
    @Override
    public ProjectInfo getProjectInfo()throws RemoteException, nqException
    {
	throw new UnsupportedOperationException("Not supported yet.");
    }

    int getID()throws RemoteException, nqException
    {
	return idTask;
    }

    @Override
    public UserInfo getCreatorInfo()throws RemoteException, nqException
    {
	return null;
    }

    @Override
    public Date getDeadlineDate()throws RemoteException, nqException
    {
	return DeadlineDate;
    }

    @Override
    public Date getCreationDate()throws RemoteException, nqException
    {
	return CreationDate;
    }

    @Override
    public String getDescription()throws RemoteException, nqException
    {
	return Description;
    }

    @Override
    public Integer getMaxHours()throws RemoteException, nqException
    {
	return MaxHours;
    }

    @Override
    public Boolean isSubtask()throws RemoteException, nqException
    {
	return isSubtask;
    }

    @Override
    public Task[] getSubtasks()throws RemoteException, nqException
    {
	return Subtasks;
    }

    @Override
    public Ability[] getNecessaryAbilities()throws RemoteException, nqException
    {
	return NecessaryAbilities;
    }

    @Override
    public int getPriority()throws RemoteException, nqException
    {
	return Priority;
    }

    @Override
    public String getTitle()throws RemoteException, nqException
    {
	return Title;
    }

    @Override
    public eTaskStatus getStatus()throws RemoteException, nqException
    {
	return Status;
    }
}
