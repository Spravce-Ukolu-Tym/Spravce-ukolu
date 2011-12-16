package nextQuest.guiClient;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import nextQuest.ifc.iTask;
import nextQuest.ifc.nqException;
import nextQuest.server.Task;

public class QuestsControl {
    private static final QuestsControl instance = new QuestsControl();

    // Private constructor prevents instantiation from other classes
    private QuestsControl() { }

    public static QuestsControl getInstance() {
            return instance;
    }

    /**
     * odevzdání úkolu
     * @param task
     */
    public void returnTask(iTask t) throws RemoteException {
        try {
            t.returnTask();
        } catch (nqException ex) {
            Logger.getLogger(QuestsControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * odmítnutí úkolu
     * @param task
     */
    public void reject(iTask t, String reason) throws WrongInputException, RemoteException {
        if(reason == null) return;
        else if(reason.equals("")) throw new WrongInputException("Please input your reason");
        try {
            t.reject(reason);
        } catch (nqException ex) {
            Logger.getLogger(QuestsControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
