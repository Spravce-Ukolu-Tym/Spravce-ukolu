package nextQuest.mock;

import java.rmi.RemoteException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import nextQuest.ifc.iUser;
import nextQuest.ifc.iUserManagerAdmin;
import nextQuest.ifc.nqException;
import nextQuest.server.Ability;
import nextQuest.server.User;
import nextQuest.server.UserInfo;

public class UserManagerAdminMock implements iUserManagerAdmin {
    @Override
    public void createAbility(Ability a)
    {
        DatabaseMock.getAbilities().add(a);
    }

    @Override
    public void removeAbility(Ability a)
    {
        DatabaseMock.getAbilities().remove(a);
    }

    @Override
    public void createUser(String LoginName, String Name, String primaryPassword, Boolean leader, Boolean personalist) throws RemoteException
    {
        int iduser = DatabaseMock.getUsers().size()+1;
        DatabaseMock.getUsers().add(new User(iduser, Name, LoginName, personalist, personalist, personalist, null));
    }

    @Override
    public void editUser(iUser usr, String LoginName, String Name, String primaryPassword, Boolean personalist, Boolean leader) throws RemoteException
    {
        HashSet<User> users = DatabaseMock.getUsers();
        for (Iterator<User> it = users.iterator(); it.hasNext();) {
            User user = it.next();
            if(user.equals(usr)) {
                user.setLoginName(LoginName);
                user.setName(Name);
                user.setPermissionLeader(leader);
                user.setPermissionPersonalist(personalist);
            }
        }
    }

    @Override
    public void updateUserAbilities(iUser usr, Ability[] abs) throws RemoteException
    {
        for (Ability ability : abs) {
            System.out.println(ability);
            try {
                DatabaseMock.getUserAbilities().add(new UserAbilitiesMock(usr.getID(), ability, ability.getLevel()));
            } catch (nqException ex) {
                Logger.getLogger(UserManagerAdminMock.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void removeUser(iUser usr) throws RemoteException
    {
        DatabaseMock.getUsers().remove((User) usr);
    }

    @Override
    public UserInfo[] findUsersByAbilities() throws RemoteException, nqException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public UserInfo[] listAllUsers() throws RemoteException, nqException {
        HashSet<UserInfo> usrInf = DatabaseMock.getUsersInfo();
        return usrInf.toArray(new UserInfo[0]);
    }

    @Override
    public Ability[] listAblities() throws RemoteException, nqException {
        HashSet<Ability> abilities = DatabaseMock.getAbilities();
        return abilities.toArray(new Ability[0]);
    }
}
