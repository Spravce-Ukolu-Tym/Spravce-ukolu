package nextQuest.guiClient;

import java.rmi.RemoteException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import nextQuest.ifc.Static;
import nextQuest.ifc.iUser;
import nextQuest.ifc.iUserManagerAdmin;
import nextQuest.ifc.nqException;
import nextQuest.server.Ability;
import nextQuest.server.User;
import nextQuest.server.UserInfo;

public class StaffControl {
    private static final StaffControl instance = new StaffControl();
    private static iUserManagerAdmin uma;
    private static StaffTableModel model;

    // Private constructor prevents instantiation from other classes
    private StaffControl() { }

    public static StaffControl getInstance(iUserManagerAdmin umadmin, StaffTableModel tableModel) {
            uma = umadmin;
            model = tableModel;
            return instance;
    }

    public static StaffControl getInstance() {
            return instance;
    }

    /**
     * odebrání uživatele
     */
    public void removePerson(iUser user) throws RemoteException {
        try {
            uma.removeUser(user);
            model.updateContent(uma.listAllUsers());
        } catch (nqException ex) {
            Logger.getLogger(StaffControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * přidání uživatele - korektní vstupy
     */
    public void addPerson(String login, String name, String password, boolean leader, boolean personalist, Ability [] abilities) throws RemoteException, WrongInputException {
        try {
            checkInputs(login, name, password);
            uma.createUser(login, name, Static.MD5(password), leader, personalist);
            updatePersonAbilities(null, abilities);
            model.updateContent(uma.listAllUsers());
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(StaffControl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (nqException ex) {
            Logger.getLogger(StaffControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * změna údajů o uživateli
     */
    public void changePerson(iUser user, String login, String name, String password, boolean leader, boolean personalist, Ability [] abilities) throws RemoteException, WrongInputException {
        try {
            checkInputs(login, name, password);
            uma.editUser(user, login, name, Static.MD5(password), leader, personalist);
            updatePersonAbilities(user, abilities);
            model.updateContent(uma.listAllUsers());
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(StaffControl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (nqException ex) {
            Logger.getLogger(StaffControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * nastavení schopností u uživatele
     */
    private void updatePersonAbilities(iUser user, Ability [] abilities) throws RemoteException {
        try {
            iUser usr;
            if(user == null) {
                // zjištění posledního přidaného pomocí nejvyššího ID
                List<UserInfo> users = (List<UserInfo>) Arrays.asList(uma.listAllUsers());
                Collections.sort(users, new Comparator<UserInfo>() {

                    @Override
                    public int compare(UserInfo o1, UserInfo o2) {
                        return o2.getID() - o1.getID(); // seřazení podle ID sestupně
                    }
                });
                UserInfo usrInf = users.get(0);
                usr = new User(usrInf.getID(), usrInf.getName(), usrInf.getLoginName(), usrInf.getPermissionAdmin(), usrInf.getPermissionLeader(), usrInf.getPermissionPersonalist(), null);
            } else {
                usr = user;
            }
            uma.updateUserAbilities(usr, abilities);
        } catch (nqException ex) {
            Logger.getLogger(StaffControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * kontrola vstupů
     * @return logická hodnota - prošlo/neprošlo
     */
    private void checkInputs(String login, String name, String password) throws WrongInputException {
        if(name.length() < 3 || name.length() > 40) {
            throw new WrongInputException("Length of a name must be between 3 and 40 characters...");
        } else if (login.length() < 3 || login.length() > 20) {
            throw new WrongInputException("Length of a login must be between 3 and 20 characters...");
        } else if (password.length() < 3 || password.length() > 10) {
            throw new WrongInputException("Length of a password must be between 3 and 10 characters...");
        }
    }
}
