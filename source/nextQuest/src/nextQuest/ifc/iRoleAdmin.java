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
public interface iRoleAdmin extends Remote
{

    iProjectManager getProjectManager() throws RemoteException, nqException;

    iTaskManagerLeader getTaskManagerLeader() throws RemoteException, nqException;

    iUserManagerAdmin getUserManagerAdmin() throws RemoteException, nqException;
    
}
