package nextQuest.mock;

import nextQuest.server.*;
import java.rmi.RemoteException;
import nextQuest.ifc.iTaskManager;

public class TaskManagerMock implements iTaskManager {
    
    public TaskManagerMock() throws RemoteException
    {}
    
    @Override
    public Task[] getAssingnedTasks() {
        return DatabaseMock.getTasks().toArray(new Task[0]); // pro jednoduchost bez rozeznani prirazeneho uzivatele
    }

    @Override
    public void updateTask() {
    }
}
