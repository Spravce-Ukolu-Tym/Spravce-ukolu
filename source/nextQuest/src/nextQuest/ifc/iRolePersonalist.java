/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nextQuest.ifc;

import java.rmi.Remote;
import nextQuest.server.UserManagerAdmin;

/**
 *
 * @author suk
 */
public interface iRolePersonalist extends Remote
{

    UserManagerAdmin getUserManagerAdmin();
    
}
