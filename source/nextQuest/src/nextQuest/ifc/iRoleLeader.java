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
public interface iRoleLeader extends Remote
{

    iTaskManagerLeader getTaskManagerLeader() throws RemoteException, nqException;

    iUserManager getUserManager() throws RemoteException, nqException;
    
}
