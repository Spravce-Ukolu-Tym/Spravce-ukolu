package nextQuest.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import nextQuest.ifc.*;

public class User extends UnicastRemoteObject  implements iUser {
    private String Name;
    private String LoginName;
    private Boolean PermissionAdmin;
    private Boolean PermissionLeader;
    private Boolean PermissionPersonalist;

    private iPrivilegedRole[] roles;
    
    private int iduser;
    private Connection con;
    
    
    
    public User(int iduser, String Name, String LoginName, Boolean PermissionAdmin, Boolean PermissionLeader, Boolean PermissionPersonalist, Connection con) throws RemoteException
    {	
        this.Name = Name;
        this.LoginName = LoginName;
        this.PermissionAdmin = PermissionAdmin;
        this.PermissionLeader = PermissionLeader;
        this.PermissionPersonalist = PermissionPersonalist;
        this.roles = null;
	
	this.con = con;
	this.iduser = iduser;
    }

    @Override
    public int getID()
    {
	return this.iduser;
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
    public Ability[] getAbilityList() {
	return null;	
    }

    @Override
    public iPrivilegedRole[] getRoles() throws RemoteException {
        
	if(this.roles == null)
	{
	    List<iPrivilegedRole> rls = new ArrayList<iPrivilegedRole>();
	    if(this.PermissionAdmin)
		rls.add(new RoleAdmin(this.con, this));
	    
	    if(this.PermissionLeader)
		rls.add(new RoleLeader(this.con, this));
	    
	    if(this.PermissionPersonalist)
		rls.add(new RolePersonalist(this.con, this));
	    
	    
	    return (this.roles = rls.toArray(new iPrivilegedRole[rls.size()]));
	}
	else 
	    return this.roles;
	
    }

    @Override
    public iTaskManager getTaskManager() {
        return null;
    }

    @Override
    public void requestChange() {
	throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Boolean getPermissionAdmin() throws RemoteException, nqException
    {
	return this.PermissionAdmin;
    }

    @Override
    public Boolean getPermissionLeader() throws RemoteException, nqException
    {
	return this.PermissionLeader;
    }

    @Override
    public Boolean getPermissionPersonalist() throws RemoteException, nqException
    {
	return this.PermissionPersonalist;
    }

    public void setLoginName(String LoginName) {
        this.LoginName = LoginName;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setPermissionLeader(Boolean PermissionLeader) {
        this.PermissionLeader = PermissionLeader;
    }

    public void setPermissionPersonalist(Boolean PermissionPersonalist) {
        this.PermissionPersonalist = PermissionPersonalist;
    }



    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.iduser != other.iduser) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.iduser;
        return hash;
    }

    
}
