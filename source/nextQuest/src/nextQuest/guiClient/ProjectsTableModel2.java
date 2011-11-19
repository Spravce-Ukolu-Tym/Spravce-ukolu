/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nextQuest.guiClient;

import javax.swing.table.AbstractTableModel;
import nextQuest.ifc.iUser;

/**
 *
 * @author Tomas
 */
public class ProjectsTableModel2 extends AbstractTableModel {
    private String[] columnNames = {"Project", "Progress in %"};
    private Object[][] data;

    public ProjectsTableModel2() {
        /*
        try {
            iTask[] tasks = usr.getTaskManager().getAssingnedTasks();
            for (iTask task : tasks) {
                // získat z úkolů projekty, ve kterých jsou zařazeny
            }
        } catch (RemoteException ex) {
            Logger.getLogger(ProjectsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (nqException ex) {
            Logger.getLogger(ProjectsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        data = new Object[10][2];
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex].length;
    }

}
