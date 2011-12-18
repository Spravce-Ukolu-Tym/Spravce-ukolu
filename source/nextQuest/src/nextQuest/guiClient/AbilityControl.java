package nextQuest.guiClient;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import nextQuest.ifc.iUserManagerAdmin;
import nextQuest.ifc.nqException;
import nextQuest.server.Ability;

public class AbilityControl {
    private static final AbilityControl instance = new AbilityControl();
    private static iUserManagerAdmin uma;
    private static AbilitiesListModel model;

    // Private constructor prevents instantiation from other classes
    private AbilityControl() { }

    public static AbilityControl getInstance(iUserManagerAdmin umadmin, AbilitiesListModel listModel) {
            uma = umadmin;
            model = listModel;
            return instance;
    }

    public static AbilityControl getInstance() {
            return instance;
    }

     /**
     * odebere vybranou schopnost ze seznamu stávajících
     */
    public void removeAbility(int index) throws RemoteException {
        try {
            uma.removeAbility(model.getAbility(index));
            model.updateList(uma.listAblities());
        } catch (nqException ex) {
            Logger.getLogger(AbilityEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * přidá novou schopnost ke stávajícím
     */
    public void addAbility(String name, String description) throws RemoteException, WrongInputException {
        if(name.length() < 2 || name.length() > 20) {
            throw new WrongInputException("Length of a name must be between 2 and 20 characters...");
        } else if (description.length() < 3 || description.length() > 250) {
            throw new WrongInputException("Length of a description must be between 3 and 250 characters...");
        }

        try {
            uma.createAbility(new Ability(name, description, 666)); // na levelu zde nezáleží
            model.updateList(uma.listAblities());
        } catch (nqException ex) {
            Logger.getLogger(NewAbilityForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
