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
public interface iProjectManager extends Remote
{
    void createProject(String Name, UserInfo Leader,int Priority) throws RemoteException, nqException;
    Project[] listProjects() throws RemoteException, nqException;
}
