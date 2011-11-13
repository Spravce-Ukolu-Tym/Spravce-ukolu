package nextQuest.server;

import java.rmi.RemoteException;
import nextQuest.ifc.iTaskManagerLeader;

public class TaskManagerLeader extends TaskManager implements iTaskManagerLeader {
    public TaskManagerLeader()  throws RemoteException
    {}
    
    @Override
    public Task[] getTasksByUser() {
        return null;
    }

    @Override
    public Task[] getTasksByProject() {
        return null;
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
}
