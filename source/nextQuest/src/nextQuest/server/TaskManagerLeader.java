package nextQuest.server;

import java.rmi.RemoteException;
import nextQuest.ifc.ProjectInfo;
import nextQuest.ifc.iTask;
import nextQuest.ifc.iTaskManagerLeader;
import nextQuest.ifc.nqException;

public class TaskManagerLeader extends TaskManager implements iTaskManagerLeader {
    public TaskManagerLeader()  throws RemoteException
    {
	super(null, null);
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

    @Override
    public iTask[] getTasksByProject(ProjectInfo pi) throws RemoteException, nqException
    {
	throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public iTask[] getTasksByUser(UserInfo ui) throws RemoteException, nqException
    {
	throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public iTask[] getCreatedTasks() throws RemoteException, nqException
    {
	throw new UnsupportedOperationException("Not supported yet.");
    }
}
