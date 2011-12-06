package nextQuest.mock;

import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import nextQuest.ifc.nqException;
import nextQuest.server.Ability;
import nextQuest.server.User;
import nextQuest.server.UserInfo;

public class DatabaseMock {
    private static final DatabaseMock instance = new DatabaseMock();

    private static Set<User> userTable;
    private static Set<Ability> abilityTable;
    private static Set<UserAbilitiesMock> userAbilitiesTable;

    public DatabaseMock() {
        try {
            userTable = new HashSet<User>();
            userTable.add(new User(0, "Pepa", "Omáčka", Boolean.TRUE, Boolean.FALSE, Boolean.TRUE, null));
            userTable.add(new User(1, "Alan", "Šmejd", Boolean.TRUE, Boolean.FALSE, Boolean.TRUE, null));
            userTable.add(new User(2, "Barbora", "Halousková", Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, null));
            userTable.add(new User(3, "Lucie", "Hronová", Boolean.FALSE, Boolean.TRUE, Boolean.TRUE, null));
            userTable.add(new User(4, "Lucie", "Jandová", Boolean.TRUE, Boolean.FALSE, Boolean.TRUE, null));

            abilityTable = new HashSet<Ability>();
            abilityTable.add(new Ability("PHP", "Uživatel umí jazyk PHP."));
            abilityTable.add(new Ability("Java", "Uživatel umí jazyk Java."));
            abilityTable.add(new Ability("SQL", "Uživatel umí jazyk SQL."));
            abilityTable.add(new Ability("HTML", "Uživatel umí jazyk HTML."));

            userAbilitiesTable = new HashSet<UserAbilitiesMock>();
        } catch (RemoteException ex) {
            Logger.getLogger(DatabaseMock.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static HashSet<UserAbilitiesMock> getUserAbilities() {
        return (HashSet<UserAbilitiesMock>) userAbilitiesTable;
    }

    public static HashSet<User> getUsers() {
        return (HashSet<User>) userTable;
    }

    public static HashSet<UserInfo> getUsersInfo() throws RemoteException {
        UserInfo usrInf;
        HashSet<UserInfo> users = new HashSet<UserInfo>();
        for (User user : userTable) {
            try {
                usrInf = new UserInfo(user.getID(), user.getName(), user.getLoginName(), user.getPermissionAdmin(), user.getPermissionLeader(), user.getPermissionPersonalist());
                users.add(usrInf);
            } catch (nqException ex) {
                Logger.getLogger(DatabaseMock.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return users;
    }

    public static HashSet<Ability> getAbilities() {
        return (HashSet<Ability>) abilityTable;
    }
}
