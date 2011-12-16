package nextQuest.guiClient;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import nextQuest.ifc.iTask;
import nextQuest.ifc.nqException;
import nextQuest.server.Task;

public class QuestsPanel extends JPanel {
        private List<iTask> model;
        private QuestsRow selectedRow;
        private QuestsRow highlightedRow;

        public QuestsPanel() {
            LayoutManager mgr = new GridLayout(0, 1, 0, 0);
            setLayout(mgr);
        }

        public void updateModel(iTask [] quests) throws RemoteException {
            model = Arrays.asList(quests);
            Collections.sort(model, new Comparator<iTask>(){

            @Override
            public int compare(iTask o1, iTask o2) {
                try {
                    return o1.getCreationDate().compareTo(o2.getCreationDate());
                } catch (RemoteException ex) {
                    return -1;
                } catch (nqException ex) {
                    return -1;
                }
            }
        });

            update();
        }

        private void update() throws RemoteException {
            removeAll();
            selectedRow = null;

            for (iTask task : model) {
                try {
                    if(task.isSubtask()) continue; // subtasky se zpracují spolu s hlavním taskem
                    appendRowTask(task);

                    //připojení podúkolů
                    iTask [] subtasks = task.getSubtasks();
                    if(subtasks == null) continue;
                    else {
                        for (iTask sub : subtasks) {
                            appendRowTask(sub);
                        }
                    }
                } catch (nqException ex) {
                    Logger.getLogger(QuestsPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            updateUI();
        }

        private void appendRowTask(iTask task) throws RemoteException {
            QuestsRow row = new QuestsRow(task);
            add(row);
            row.updateSize();
            row.registerMouseListener();
            if(selectedRow == null) selectRow(row);
        }

        public void selectRow(QuestsRow row) {
            if(selectedRow != null)
                selectedRow.setBackground(new Color(240, 240, 240));
            selectedRow= row;
            selectedRow.setBackground(new Color(153, 153, 255));
        }

        public iTask getSelectedTask() {
            return selectedRow.getTask();
        }

        public void setHighlightedRow(QuestsRow highlightedRow) {
            if(this.highlightedRow != null && this.highlightedRow != selectedRow) {
                this.highlightedRow.setBackground(new Color(240, 240, 240));
            }

            this.highlightedRow = highlightedRow;

            if(this.highlightedRow != selectedRow) {
                this.highlightedRow.setBackground(new Color(204, 204, 255));
            }
        }

}