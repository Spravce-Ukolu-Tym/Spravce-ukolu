package nextQuest.guiClient;

import java.rmi.RemoteException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import nextQuest.ifc.Static;
import nextQuest.ifc.iConnector;
import nextQuest.ifc.iUser;
import nextQuest.ifc.nqException;

public class LoginControl {
    private static iConnector connector;
    private static final LoginControl instance = new LoginControl();

    // Private constructor prevents instantiation from other classes
    private LoginControl() { }

    public static LoginControl getInstance(iConnector con) {
            connector = con;
            return instance;
    }

    public iUser login(long sid, String login, String password) throws RemoteException, nqException {
        try {
            String salt = connector.getPasswordSalt(sid);
            String pswd = Static.MD5(Static.MD5(password).concat(salt));
            return connector.Login(sid, login, pswd);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginControl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
