package nextQuest.ifc;

import java.rmi.*;
import nextQuest.server.Ability;
import java.util.Date;
import nextQuest.server.Project;



public interface iTask extends Remote
{

    void accept()  throws RemoteException, nqException;

    iUser getAssignedTo()  throws RemoteException, nqException;

    Date getCreaitonDate()  throws RemoteException, nqException;

    iUser getCreator()  throws RemoteException, nqException;

    String getDescription()  throws RemoteException, nqException;
    
    Boolean getIsSubtask()  throws RemoteException, nqException;

    Integer getMaxHours()   throws RemoteException, nqException;

    Ability[] getNecessaryAbilities() throws RemoteException, nqException;

    Integer getPercentage() throws RemoteException, nqException;

    iTask[] getSubtasks()  throws RemoteException, nqException;

    void reject(String Reason) throws RemoteException, nqException;

    void returnTask() throws RemoteException, nqException;

    Project getProject() throws RemoteException, nqException;

    String getName() throws RemoteException, nqException;
    
}
