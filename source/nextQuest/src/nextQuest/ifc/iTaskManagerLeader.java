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
public interface iTaskManagerLeader extends Remote
{

    void approveTask() throws RemoteException, nqException;

    void assignTaskAutomatically() throws RemoteException, nqException;

    void assignTasksManually() throws RemoteException, nqException;

    void createTask() throws RemoteException, nqException;

    iTask[] getTasksByProject() throws RemoteException, nqException;

    iTask[] getTasksByUser() throws RemoteException, nqException;
    
}
