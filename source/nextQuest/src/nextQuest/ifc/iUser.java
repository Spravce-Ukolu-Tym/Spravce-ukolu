/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nextQuest.ifc;

import java.rmi.*;
/**
 *
 * @author suk
 */
public interface iUser extends Remote
{

    iAbility[] getAbilityList() throws RemoteException, nqException;

    String getLoginName() throws RemoteException, nqException;

    String getName() throws RemoteException, nqException;

    Boolean getPermissionAdmin() throws RemoteException, nqException;

    Boolean getPermissionLeader() throws RemoteException, nqException;

    Boolean getPermissionPersonalist() throws RemoteException, nqException;

    iPrivilegedRole[] getRoles() throws RemoteException, nqException;

    iTaskManager getTaskManager() throws RemoteException, nqException;

    void requestChange() throws RemoteException, nqException;
    
}
