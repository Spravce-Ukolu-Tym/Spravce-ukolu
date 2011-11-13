package nextQuest.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import nextQuest.ifc.iProjectManager;

public class ProjectManager  extends UnicastRemoteObject implements iProjectManager {
    public ProjectManager() throws RemoteException
    {
	
    }
    @Override
    public void createProject() {
    }
}
