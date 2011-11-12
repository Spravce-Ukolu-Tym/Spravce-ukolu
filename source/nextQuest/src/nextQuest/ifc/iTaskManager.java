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
public interface iTaskManager extends Remote
{

    Task[] getAssingnedTasks();

    void updateTask();
    
}
