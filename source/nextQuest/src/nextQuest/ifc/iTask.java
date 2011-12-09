package nextQuest.ifc;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

import nextQuest.server.Ability;
import nextQuest.server.Task;
import nextQuest.server.UserInfo;

/**
 *
 * @author suk
 */
public interface iTask extends Remote
{

    void accept() throws RemoteException, nqException;

    Date getCreationDate() throws RemoteException, nqException;

    UserInfo getCreatorInfo() throws RemoteException, nqException;

    Date getDeadlineDate() throws RemoteException, nqException;

    String getDescription() throws RemoteException, nqException;

    Integer getMaxHours() throws RemoteException, nqException;

    Ability[] getNecessaryAbilities() throws RemoteException, nqException;

    int getPriority() throws RemoteException, nqException;

    ProjectInfo getProjectInfo() throws RemoteException, nqException;

    eTaskStatus getStatus() throws RemoteException, nqException;

    Task[] getSubtasks() throws RemoteException, nqException;

    String getTitle() throws RemoteException, nqException;

    Boolean isSubtask() throws RemoteException, nqException;

    void reject(String Reason) throws RemoteException, nqException;

    void returnTask() throws RemoteException, nqException;
    
}
