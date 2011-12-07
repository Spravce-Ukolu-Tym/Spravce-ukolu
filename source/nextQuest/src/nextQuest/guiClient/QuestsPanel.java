package nextQuest.guiClient;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import nextQuest.ifc.iTask;
import nextQuest.ifc.nqException;

public class QuestsPanel extends JPanel {
        iTask [] model;

        public QuestsPanel() {
            LayoutManager mgr = new GridLayout(0, 1, 0, 0);
            setLayout(mgr);
        }

        public void updateModel(iTask [] quests) throws RemoteException {
            this.model = quests;
            update();
        }

        private void update() throws RemoteException {
            removeAll();

            QuestsRow row;
            String name = "", description = "";
            int progress = 0;
            for (iTask task : model) {
                try {
                    name = task.getName();
                    description = task.getDescription();
                    progress = task.getPercentage();
                    row = new QuestsRow(name, description, progress);
                    add(row);
                    //add(new JSeparator());
                } catch (nqException ex) {
                    Logger.getLogger(QuestsPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            updateUI();
        }
    }