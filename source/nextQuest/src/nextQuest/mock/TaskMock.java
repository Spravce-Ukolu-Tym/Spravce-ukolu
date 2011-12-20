package nextQuest.mock;

import java.rmi.RemoteException;
import nextQuest.ifc.*;
import java.util.Date;
import nextQuest.server.Ability;
import nextQuest.server.Project;
import nextQuest.server.Task;
import nextQuest.server.User;
import nextQuest.server.UserInfo;

public class TaskMock implements iTask, Comparable<Task> {
    private Date CreaitonDate;
    private String Description;
    private Integer MaxHours;
    private User Creator;
    private User AssignedTo;
    private Boolean isSubtask;
    private TaskMock[] Subtasks;
    private Integer Percentage;
    private Ability[] NecessaryAbilities;
    private Project Project;
    private String Name;

    public TaskMock(Date CreaitonDate, String Description, Integer MaxHours, User Creator, User AssignedTo, Boolean isSubtask, TaskMock[] Subtasks, Integer Percentage, Ability[] NecessaryAbilities, Project Project, String Name) throws RemoteException{
        this.CreaitonDate = CreaitonDate;
        this.Description = Description;
        this.MaxHours = MaxHours;
        this.Creator = Creator;
        this.AssignedTo = AssignedTo;
        this.isSubtask = isSubtask;
        this.Subtasks = Subtasks;
        this.Percentage = Percentage;
        this.NecessaryAbilities = NecessaryAbilities;
        this.Project = Project;
        this.Name = Name;
    }

    @Override
    public void accept() throws RemoteException, nqException {
    }

    @Override
    public Date getCreationDate() throws RemoteException, nqException {
        return CreaitonDate;
    }

    @Override
    public UserInfo getCreatorInfo() throws RemoteException, nqException {
        return null;
    }

    @Override
    public Date getDeadlineDate() throws RemoteException, nqException {
        return null;
    }

    @Override
    public String getDescription() throws RemoteException, nqException {
        return Description;
    }

    @Override
    public Integer getMaxHours() throws RemoteException, nqException {
        return MaxHours;
    }

    @Override
    public Ability[] getNecessaryAbilities() throws RemoteException, nqException {
        return null;
    }

    @Override
    public int getPriority() throws RemoteException, nqException {
        return 0;
    }

    @Override
    public Project getProjectInfo() throws RemoteException, nqException {
        return Project;
    }

    @Override
    public eTaskStatus getStatus() throws RemoteException, nqException {
        return null;
    }

    @Override
    public iTask[] getSubtasks() throws RemoteException, nqException {
        return Subtasks;
    }

    @Override
    public String getTitle() throws RemoteException, nqException {
        return Name;
    }

    @Override
    public Boolean isSubtask() throws RemoteException, nqException {
        return isSubtask;
    }

    @Override
    public void reject(String Reason) throws RemoteException, nqException {
    }

    @Override
    public void returnTask() throws RemoteException, nqException {
    }

    @Override
    public String getName() throws RemoteException, nqException {
        return null;
    }

    @Override
    public int compareTo(Task o) {
        try
	{
	    return this.CreaitonDate.compareTo(o.getCreationDate());
	}
	catch (Exception e)
	{
	    return -1; //co s tim?
	}
    }

    @Override
    public int getPercentage() throws RemoteException, nqException {
        return Percentage;
    }
    
    @Override 
    public int getsysid() throws RemoteException, nqException{
	return System.identityHashCode(this);
    }

}

