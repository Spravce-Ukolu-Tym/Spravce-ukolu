/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nextQuest.ifc;

import java.rmi.Remote;
import nextQuest.server.ProjectManager;
import nextQuest.server.TaskManagerLeader;
import nextQuest.server.UserManagerAdmin;

/**
 *
 * @author suk
 */
public interface iRoleAdmin extends Remote
{

    ProjectManager getProjectManager();

    TaskManagerLeader getTaskManagerLeader();

    UserManagerAdmin getUserManagerAdmin();
    
}
