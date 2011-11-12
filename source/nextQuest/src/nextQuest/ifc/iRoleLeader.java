/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nextQuest.ifc;

import java.rmi.Remote;
import nextQuest.server.TaskManagerLeader;
import nextQuest.server.UserManager;

/**
 *
 * @author suk
 */
public interface iRoleLeader extends Remote
{

    TaskManagerLeader getTaskManagerLeader();

    UserManager getUserManager();
    
}
