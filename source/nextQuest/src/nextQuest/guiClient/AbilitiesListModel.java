package nextQuest.guiClient;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractListModel;
import nextQuest.ifc.nqException;
import nextQuest.server.Ability;

public class AbilitiesListModel extends AbstractListModel {
        private Set<Ability> abilityList = new HashSet<Ability>();

        public AbilitiesListModel(Ability [] abilities) {
            updateList(abilities);
        }

        @Override
        public int getSize() {
            return abilityList.size();
        }

        @Override
        public String getElementAt(int index) {
            Ability abl = (Ability) abilityList.toArray()[index];
            return abl.getName();
        }

        public Ability getAbility(int index) {
            return (Ability) abilityList.toArray()[index];
        }

        public void updateList(Ability [] abilities) {
            abilityList.removeAll(abilityList);
            abilityList.addAll(Arrays.asList(abilities));
        }
}