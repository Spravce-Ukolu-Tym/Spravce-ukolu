package nextQuest.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import nextQuest.ifc.*;

public class User extends UnicastRemoteObject  implements iUser {
    private String Name;
    private String LoginName;
    private Boolean PermissionAdmin;
    private Boolean PermissionLeader;
    private Boolean PermissionPersonalist;
    private iAbility[] AbilityList;
    private iPrivilegedRole[] Roles;

    private int iduser;
    private Connection con;
    
    public User(int iduser, String Name, String LoginName, Boolean PermissionAdmin, Boolean PermissionLeader, Boolean PermissionPersonalist, Connection con) throws RemoteException
    {	
        this.Name = Name;
        this.LoginName = LoginName;
        this.PermissionAdmin = PermissionAdmin;
        this.PermissionLeader = PermissionLeader;
        this.PermissionPersonalist = PermissionPersonalist;
        /*this.AbilityList = AbilityList;
        this.Roles = Roles;*/
	
	this.con = con;
	this.iduser = iduser;
    }

    @Override
    public String getLoginName() {
        return LoginName;
    }

    @Override
    public String getName() {
        return Name;
    }

    @Override
    public Boolean getPermissionAdmin() {
        return PermissionAdmin;
    }

    @Override
    public Boolean getPermissionLeader() {
        return PermissionLeader;
    }

    @Override
    public Boolean getPermissionPersonalist() {
        return PermissionPersonalist;
    }

    
    
    @Override
    public iAbility[] getAbilityList() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public iPrivilegedRole[] getRoles() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public iTaskManager getTaskManager() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void requestChange() {
	throw new UnsupportedOperationException("Not yet implemented");
    }
}
