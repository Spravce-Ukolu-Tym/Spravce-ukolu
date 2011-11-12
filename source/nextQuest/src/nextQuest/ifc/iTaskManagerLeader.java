/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nextQuest.ifc;

import java.rmi.Remote;
import nextQuest.server.Task;

/**
 *
 * @author suk
 */
public interface iTaskManagerLeader extends Remote
{

    void approveTask();

    void assignTaskAutomatically();

    void assignTasksManually();

    void createTask();

    Task[] getTasksByProject();

    Task[] getTasksByUser();
    
}
