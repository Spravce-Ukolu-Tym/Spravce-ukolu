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
public interface iRolePersonalist extends iPrivilegedRole
{
    iUserManagerAdmin getUserManagerAdmin() throws RemoteException, nqException;
    
}
