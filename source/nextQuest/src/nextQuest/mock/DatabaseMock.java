package nextQuest.mock;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import nextQuest.ifc.eTaskStatus;
import nextQuest.ifc.nqException;
import nextQuest.server.Ability;
import nextQuest.server.Project;
import nextQuest.server.Task;
import nextQuest.server.User;
import nextQuest.server.UserInfo;

public class DatabaseMock {
    private static final DatabaseMock instance = new DatabaseMock();

    private static Set<User> userTable;
    private static Set<Ability> abilityTable;
    private static Set<UserAbilitiesMock> userAbilitiesTable;
    private static Set<TaskMock> taskTable;
    private static Set<Project> projectsTable;

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

            projectsTable = new HashSet<Project>();
            projectsTable.add(new Project(0, "Udrzba vlasti", null, null));
            projectsTable.add(new Project(1, "Stop koureni", null, null));
            projectsTable.add(new Project(2, "NextQuest", null, null));

            taskTable = new HashSet<TaskMock>();
            taskTable.add(new TaskMock(new Date(2011, 12, 8), "Podel celeho domu.", 2, userTable.toArray(new User[0])[0], null, false, null, 20, abilityTable.toArray(new Ability[0]), projectsTable.toArray(new Project[0])[2], "Zamet chodnik"));
            taskTable.add(new TaskMock(new Date(2011, 12, 6), "Nezapomen na to.", 2, userTable.toArray(new User[0])[0], null, false, null, 52, abilityTable.toArray(new Ability[0]), projectsTable.toArray(new Project[0])[2], "Vymen zarovku"));
            TaskMock subTask1 = new TaskMock(new Date(2011, 12, 7), "nejprve jedno.", 2, userTable.toArray(new User[0])[0], null, true, null, 10, abilityTable.toArray(new Ability[0]), projectsTable.toArray(new Project[0])[2], "Pocitej 2");
            TaskMock subTask2 = new TaskMock(new Date(2011, 12, 7), "poté i to druhé jedno.", 2, userTable.toArray(new User[0])[0], null, true, null, 10, abilityTable.toArray(new Ability[0]), projectsTable.toArray(new Project[0])[2], "Pocitej 3");
            TaskMock [] arrayOfSubtasks = {subTask1, subTask2};
            TaskMock mainTask = new TaskMock(new Date(2011, 12, 7), "Odecti 2 cisla.", 2, userTable.toArray(new User[0])[0], null, false, arrayOfSubtasks, 11, abilityTable.toArray(new Ability[0]), projectsTable.toArray(new Project[0])[2], "Pocitej");
            taskTable.add(mainTask);
            taskTable.add(new TaskMock(new Date(2011, 12, 7), "Naprogramuj to a to a to rychle.", 2, userTable.toArray(new User[0])[0], null, false, null, 11, abilityTable.toArray(new Ability[0]), projectsTable.toArray(new Project[0])[0], "Naprogramuj to a to"));
            taskTable.add(new TaskMock(new Date(2011, 12, 7), "A pak hned vstan a makej.", 2, userTable.toArray(new User[0])[0], null, false, null, 95, abilityTable.toArray(new Ability[0]), projectsTable.toArray(new Project[0])[1], "Jdi spat"));
            taskTable.add(subTask2);
            taskTable.add(subTask1);

        } catch (RemoteException ex) {
            Logger.getLogger(DatabaseMock.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static HashSet<Project> getProjects() {
        return (HashSet<Project>) projectsTable;
    }

    public static HashSet<TaskMock> getTasks() {
        return (HashSet<TaskMock>) taskTable;
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
