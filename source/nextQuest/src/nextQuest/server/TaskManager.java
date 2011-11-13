package nextQuest.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import nextQuest.ifc.iTaskManager;

public class TaskManager  extends UnicastRemoteObject implements iTaskManager {
    
    public TaskManager() throws RemoteException
    {}
    
    @Override
    public Task[] getAssingnedTasks() {
        return null;
    }

    @Override
    public void updateTask() {
    }
}
