/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nextQuest.ifc;

import java.rmi.Remote;
import nextQuest.server.Ability;
import nextQuest.server.PrivilegedRole;
import nextQuest.server.TaskManager;

/**
 *
 * @author suk
 */
public interface iUser extends Remote
{

    Ability[] getAbilityList();

    String getLoginName();

    String getName();

    Boolean getPermissionAdmin();

    Boolean getPermissionLeader();

    Boolean getPermissionPersonalist();

    PrivilegedRole[] getRoles();

    TaskManager getTaskManager();

    void requestChange();
    
}
