package nextQuest.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import nextQuest.ifc.*;

public class Project  extends UnicastRemoteObject implements iProject, Comparable<Project> {
    private int idproject;
    private String Name;
    private User Creator;
    private User Leader;
    private int Priority;

    public Project(int idproject, String Name, User Creator, User Leader, int Priority) throws RemoteException{
        this.idproject = idproject;
        this.Name = Name;
        this.Creator = Creator;
        this.Leader = Leader;
        this.Priority = Priority;
    }

    @Override
    public int getID() {
        return idproject;
    }

    @Override
    public String getName() {
        return Name;
    }

    @Override
    public iUser getUserCreatedBy() {
        return Creator;
    }

    @Override
    public iUser getLeader() {
        return Leader;
    }

    @Override
    public int getPriority() {
        return Priority;
    }

    @Override
    public int compareTo(Project o) {
        return o.Priority-Priority;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Project other = (Project) obj;
        if (this.idproject != other.idproject) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.idproject;
        return hash;
    }

    
}

