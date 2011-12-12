package nextQuest.server;

import java.rmi.RemoteException;
import java.sql.Connection;
import nextQuest.ifc.*;

public class RoleAdmin extends PrivilegedRole implements iRoleAdmin {
    public RoleAdmin(Connection con, User u) throws RemoteException
    {
	super(con, u);
    }
    
    
    @Override
    public iUserManagerAdmin getUserManagerAdmin() throws RemoteException, nqException
    {
        return new UserManagerAdmin(this.con, this.user);
    }

    @Override
    public iTaskManagerLeader getTaskManagerLeader() throws RemoteException, nqException
    {
        return new TaskManagerLeader(this.con, this.user);
    }

    @Override
    public iProjectManager getProjectManager() throws RemoteException, nqException
    {
        return new ProjectManager(this.con, this.user);
    }
}
