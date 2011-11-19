/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nextQuest.guiClient;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import nextQuest.ifc.iTask;
import nextQuest.ifc.iUser;
import nextQuest.ifc.nqException;
import nextQuest.server.Task;

/**
 *
 * @author Tomas
 */
public class ProjectsTableModel extends AbstractTableModel {
    private String[] columnNames = {"Project", "Priority"};
    private Object[][] data;

    public ProjectsTableModel(iUser usr) {
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
