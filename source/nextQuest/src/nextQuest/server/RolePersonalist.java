package nextQuest.server;

import java.rmi.RemoteException;
import java.sql.Connection;
import nextQuest.ifc.*;

public class RolePersonalist extends PrivilegedRole implements iRolePersonalist {
    public RolePersonalist(Connection con, User u)  throws RemoteException
    {
	super(con, u);
    }
    
    
    @Override
    public UserManagerAdmin getUserManagerAdmin() throws RemoteException, nqException
    {
        return new UserManagerAdmin(this.con, this.user);
    }
}
