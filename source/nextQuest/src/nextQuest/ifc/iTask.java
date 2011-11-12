/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nextQuest.ifc;

import java.rmi.Remote;
import java.util.Date;
import nextQuest.server.Ability;
import nextQuest.server.Task;
import nextQuest.server.User;

/**
 *
 * @author suk
 */
public interface iTask extends Remote
{

    void accept();

    User getAssignedTo();

    Date getCreaitonDate();

    User getCreator();

    String getDescription();

    Boolean getIsSubtask();

    Integer getMaxHours();

    Ability[] getNecessaryAbilities();

    Integer getPercentage();

    Task[] getSubtasks();

    void reject(String Reason);

    void returnTask();
    
}
