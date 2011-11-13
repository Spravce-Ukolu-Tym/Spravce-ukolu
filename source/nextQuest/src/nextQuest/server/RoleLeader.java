package nextQuest.server;

import nextQuest.ifc.iRoleLeader;

public class RoleLeader implements iRoleLeader {
    @Override
    public TaskManagerLeader getTaskManagerLeader() {
        return null;
    }

    @Override
    public UserManager getUserManager() {
        return null;
    }
}
