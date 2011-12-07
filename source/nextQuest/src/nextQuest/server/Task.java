package nextQuest.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import nextQuest.ifc.*;
import java.util.Date;

public class Task  extends UnicastRemoteObject implements iTask {
    private Date CreaitonDate;
    private String Description;
    private Integer MaxHours;
    private User Creator;
    private User AssignedTo;
    private Boolean isSubtask;
    private Task[] Subtasks;
    private Integer Percentage;
    private Ability[] NecessaryAbilities;
    private Project Project;
    private String Name;

    public Task(Date CreaitonDate, String Description, Integer MaxHours, User Creator, User AssignedTo, Boolean isSubtask, Task[] Subtasks, Integer Percentage, Ability[] NecessaryAbilities, Project Project, String Name) throws RemoteException{
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
    public User getAssignedTo() {
        return AssignedTo;
    }

    @Override
    public Date getCreaitonDate() {
        return CreaitonDate;
    }

    @Override
    public User getCreator() {
        return Creator;
    }

    @Override
    public String getDescription() {
        return Description;
    }

    @Override
    public Integer getMaxHours() {
        return MaxHours;
    }

    @Override
    public Ability[] getNecessaryAbilities() {
        return NecessaryAbilities;
    }

    @Override
    public Integer getPercentage() {
        return Percentage;
    }

    @Override
    public Task[] getSubtasks() {
        return Subtasks;
    }

    @Override
    public Boolean getIsSubtask() {
        return isSubtask;
    }

    @Override
    public void accept() {
    }

    @Override
    public void reject(String Reason) {
    }

    @Override
    public void returnTask() {
    }

    @Override
    public Project getProject() {
        return Project;
    }

    @Override
    public String getName() {
        return Name;
    }
}

