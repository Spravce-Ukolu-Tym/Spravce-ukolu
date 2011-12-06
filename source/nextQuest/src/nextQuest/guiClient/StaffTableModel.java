package nextQuest.guiClient;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import nextQuest.ifc.iUserManager;
import nextQuest.ifc.nqException;
import nextQuest.mock.UserManagerAdminMock;
import nextQuest.server.User;
import nextQuest.server.UserInfo;

public class StaffTableModel extends AbstractTableModel {
    private String[] columnNames = {"Name", "Rating", "Abilities", "Projects"};
    private UserInfo [] users;
    private iUserManager uma;

    public StaffTableModel(iUserManager uma) throws RemoteException {
        this.uma = new UserManagerAdminMock(); //this.uma = uma;
        updateContent();
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public int getRowCount() {
        return users.length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getValueAt(int rowIndex, int columnIndex) {
        String columnName = columnNames[columnIndex]; // pro přehlednost
        if(columnName.equals("Name")) {
            return users[rowIndex].getName();
        } else if(columnName.equals("Rating")) {
            return "rating";
        } else if(columnName.equals("Abilities")) {
            return "abilities";
        } else if(columnName.equals("Projects")) {
            return "projects";
        } else {
            return "error value";
        }
    }

    public UserInfo getElementAt(int rowIndex) {
        return users[rowIndex];
    }

    public void updateContent() throws RemoteException {
        try {
            UserInfo [] unsorted = uma.listAllUsers();
            List<UserInfo> sorted = (List<UserInfo>) Arrays.asList(unsorted);
            Collections.sort(sorted, new Comparator<UserInfo>() {
                @Override
                public int compare(UserInfo o1, UserInfo o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });
            users = sorted.toArray(new UserInfo[0]);
            //users = uma.listAllUsers();
        } catch (nqException ex) {
            Logger.getLogger(StaffTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
