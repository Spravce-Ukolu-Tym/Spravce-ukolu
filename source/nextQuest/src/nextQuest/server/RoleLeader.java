package nextQuest.server;

import java.rmi.RemoteException;
import java.sql.Connection;
import nextQuest.ifc.*;

public class RoleLeader extends PrivilegedRole implements iRoleLeader {
    
    public RoleLeader(Connection con, User u) throws RemoteException
    {
	super(con, u);
    }
    
    
    @Override
    public TaskManagerLeader getTaskManagerLeader() throws RemoteException, nqException
    {
	return new TaskManagerLeader(this.con, this.user);
    }

    @Override
    public UserManager getUserManager() throws RemoteException, nqException
    {
        return new UserManager(this.con, this.user);
    }
}
