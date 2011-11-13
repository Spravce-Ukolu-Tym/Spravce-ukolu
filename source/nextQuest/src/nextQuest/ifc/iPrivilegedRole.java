/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nextQuest.ifc;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author suk
 */
public interface iPrivilegedRole extends Remote
{

    void getStats()  throws RemoteException, nqException;
    
}
