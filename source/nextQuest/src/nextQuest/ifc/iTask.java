package nextQuest.ifc;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import nextQuest.server.Project;

import nextQuest.server.Ability;
import nextQuest.server.UserInfo;

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

    int getPercentage() throws RemoteException, nqException;
    
    Project getProjectInfo() throws RemoteException, nqException;

    eTaskStatus getStatus() throws RemoteException, nqException;

    iTask[] getSubtasks() throws RemoteException, nqException;

    String getTitle() throws RemoteException, nqException;

    Boolean isSubtask() throws RemoteException, nqException;

    void reject(String Reason) throws RemoteException, nqException;

    void returnTask() throws RemoteException, nqException;

    String getName() throws RemoteException, nqException;
    
<<<<<<< HEAD
    int getsysid() throws RemoteException, nqException;
=======
    iTask getthis() throws RemoteException, nqException;
>>>>>>> ce1abc7551f076824a536d317be3d1498719e524
}
