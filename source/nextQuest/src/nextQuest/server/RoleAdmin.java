package nextQuest.server;

import java.rmi.RemoteException;
import nextQuest.ifc.iRoleAdmin;

public class RoleAdmin extends PrivilegedRole implements iRoleAdmin {
    public RoleAdmin() throws RemoteException
    {}
    
    @Override
    public UserManagerAdmin getUserManagerAdmin() {
        return null;
    }

    @Override
    public TaskManagerLeader getTaskManagerLeader() {
        return null;
    }

    @Override
    public ProjectManager getProjectManager() {
        return null;
    }
}
