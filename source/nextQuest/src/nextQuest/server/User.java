public class User {
    private String Name;
    private String LoginName;
    private Boolean PermissionAdmin;
    private Boolean PermissionLeader;
    private Boolean PermissionPersonalist;
    private Ability[] AbilityList;
    private PrivilegedRole[] Roles;

    public User(String Name, String LoginName, Boolean PermissionAdmin, Boolean PermissionLeader, Boolean PermissionPersonalist, Ability[] AbilityList, PrivilegedRole[] Roles) {
        this.Name = Name;
        this.LoginName = LoginName;
        this.PermissionAdmin = PermissionAdmin;
        this.PermissionLeader = PermissionLeader;
        this.PermissionPersonalist = PermissionPersonalist;
        this.AbilityList = AbilityList;
        this.Roles = Roles;
    }

    public String getLoginName() {
        return LoginName;
    }

    public String getName() {
        return Name;
    }

    public Boolean getPermissionAdmin() {
        return PermissionAdmin;
    }

    public Boolean getPermissionLeader() {
        return PermissionLeader;
    }

    public Boolean getPermissionPersonalist() {
        return PermissionPersonalist;
    }

    public Ability[] getAbilityList() {
        return AbilityList;
    }

    public PrivilegedRole[] getRoles() {
        return Roles;
    }

    public TaskManager getTaskManager() {
        return null;
    }

    public void requestChange() {
    }
}
