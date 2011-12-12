/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nextQuest.ifc;

import java.rmi.*;
import nextQuest.server.Project;
import nextQuest.server.UserInfo;

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

    iTask[] getTasksByProject(Project pi) throws RemoteException, nqException;

    iTask[] getTasksByUser(UserInfo ui) throws RemoteException, nqException;

    iTask[] getCreatedTasks() throws RemoteException, nqException;
}
