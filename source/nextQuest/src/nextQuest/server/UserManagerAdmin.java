package nextQuest.server;

import java.rmi.RemoteException;
import nextQuest.ifc.iUserManagerAdmin;

public class UserManagerAdmin extends UserManager implements iUserManagerAdmin {
    public UserManagerAdmin()  throws RemoteException
    {}
    
    @Override
    public void createUser() {
    }

    @Override
    public void editUser() {
    }

    @Override
    public void removeUser() {
    }

    @Override
    public void createAbility() {
    }

    @Override
    public void removeAbility() {
    }
}
