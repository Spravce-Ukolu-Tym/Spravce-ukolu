/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nextQuest.ifc;

import java.rmi.*;
import java.util.Date;
import nextQuest.server.Ability;
import nextQuest.server.Project;
import nextQuest.server.UserInfo;

/**
 *
 * @author suk
 */
public interface iTaskManagerLeader extends Remote
{

    void approveTask(iTask t) throws RemoteException, nqException;

    void assignTaskAutomatically(iTask t) throws RemoteException, nqException;

    void assignTasksManually(iTask t, UserInfo ui) throws RemoteException, nqException;

    iTask createTask(Project proj, iTask parent, String title, String description, int priority, Date deadline, int maxhours, Ability[] abl) throws RemoteException, nqException;

    iTask[] getTasksByProject(Project pi) throws RemoteException, nqException;

    iTask[] getTasksByUser(UserInfo ui) throws RemoteException, nqException;

    iTask[] getCreatedTasks() throws RemoteException, nqException;
}
