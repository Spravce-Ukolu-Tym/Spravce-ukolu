package nextQuest.mock;

import nextQuest.server.*;
import java.rmi.RemoteException;
import java.util.HashSet;
import nextQuest.ifc.iTask;
import nextQuest.ifc.iTaskManager;

public class TaskManagerMock implements iTaskManager {
    
    public TaskManagerMock() throws RemoteException
    {}
    
    @Override
    public iTask[] getAssingnedTasks() {
        HashSet<TaskMock> hs = DatabaseMock.getTasks();
        TaskMock [] tm = hs.toArray(new TaskMock[0]);
        return (iTask[]) tm; // pro jednoduchost bez rozeznani prirazeneho uzivatele
    }

    @Override
    public void updateTask() {
    }
}
