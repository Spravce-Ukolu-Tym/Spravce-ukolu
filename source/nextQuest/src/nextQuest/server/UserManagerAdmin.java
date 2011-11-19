package nextQuest.server;

import java.rmi.RemoteException;
import java.sql.Connection;
import nextQuest.ifc.Ability;
import nextQuest.ifc.iUserManagerAdmin;
import nextQuest.ifc.nqException;

public class UserManagerAdmin extends UserManager implements iUserManagerAdmin {
    public UserManagerAdmin(Connection con, User u)  throws RemoteException
    {
	super(con, u);
    }
    
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
    public void createAbility(Ability a) throws RemoteException, nqException
    {
	System.out.printf("Create ability: %s, %s\n", a.getName(), a.getDescription());
    }

    @Override
    public void removeAbility(Ability a) throws RemoteException, nqException
    {
	
    }
}
