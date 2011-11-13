package nextQuest.server;

import java.rmi.RemoteException;
import nextQuest.ifc.iRolePersonalist;

public class RolePersonalist extends PrivilegedRole implements iRolePersonalist {
    public RolePersonalist()  throws RemoteException
    {
    }
    
    
    @Override
    public UserManagerAdmin getUserManagerAdmin() {
        return null;
    }
}
