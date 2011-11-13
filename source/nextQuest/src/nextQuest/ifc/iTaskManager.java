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
public interface iTaskManager extends Remote
{

    iTask[] getAssingnedTasks() throws RemoteException, nqException;

    void updateTask() throws RemoteException, nqException;
    
}
