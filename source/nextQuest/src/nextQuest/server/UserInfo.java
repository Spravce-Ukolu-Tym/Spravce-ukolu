package nextQuest.server;

import java.io.Serializable;

public class UserInfo implements Serializable
{
    protected String Name;
    protected String LoginName;
    protected Boolean PermissionAdmin;
    protected Boolean PermissionLeader;
    protected Boolean PermissionPersonalist;
    
    private int iduser;
    
    public UserInfo(int iduser, String Name, String LoginName, Boolean PermissionAdmin, Boolean PermissionLeader, Boolean PermissionPersonalist) 
    {
	this.Name = Name;
        this.LoginName = LoginName;
        this.PermissionAdmin = PermissionAdmin;
        this.PermissionLeader = PermissionLeader;
        this.PermissionPersonalist = PermissionPersonalist;
        
	this.iduser = iduser;
    }
    
    /**
     * @return the Name
     */
    public String getName()
    {
	return Name;
    }

    /**
     * @return the LoginName
     */
    public String getLoginName()
    {
	return LoginName;
    }

    /**
     * @return the PermissionAdmin
     */
    public Boolean getPermissionAdmin()
    {
	return PermissionAdmin;
    }

    /**
     * @return the PermissionLeader
     */
    public Boolean getPermissionLeader()
    {
	return PermissionLeader;
    }

    /**
     * @return the PermissionPersonalist
     */
    public Boolean getPermissionPersonalist()
    {
	return PermissionPersonalist;
    }

    /**
     * @param Name the Name to set
     */
    public void setName(String Name)
    {
	this.Name = Name;
    }

    /**
     * @param LoginName the LoginName to set
     */
    public void setLoginName(String LoginName)
    {
	this.LoginName = LoginName;
    }

    /**
     * @param PermissionAdmin the PermissionAdmin to set
     */
    public void setPermissionAdmin(Boolean PermissionAdmin)
    {
	this.PermissionAdmin = PermissionAdmin;
    }

    /**
     * @param PermissionLeader the PermissionLeader to set
     */
    public void setPermissionLeader(Boolean PermissionLeader)
    {
	this.PermissionLeader = PermissionLeader;
    }

    /**
     * @param PermissionPersonalist the PermissionPersonalist to set
     */
    public void setPermissionPersonalist(Boolean PermissionPersonalist)
    {
	this.PermissionPersonalist = PermissionPersonalist;
    }
    
    
}
