package nextQuest.guiClient;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import nextQuest.ifc.iUserManager;
import nextQuest.ifc.nqException;
import nextQuest.server.UserInfo;
import nextQuest.server.UserManager;

public class StaffTableModel extends AbstractTableModel {
    private String[] columnNames = {"Name", "Rating", "Abilities", "Projetcs"};
    private UserInfo [] users;
    private iUserManager uma;

    public StaffTableModel(iUserManager uma) throws RemoteException {
        this.uma = uma;
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
        } else if(columnName.equals("Raitings")) {
            return "raiting";
        } else if(columnName.equals("Abilities")) {
            return "abilities";
        } else if(columnName.equals("Projects")) {
            return "projects";
        } else {
            return "error value";
        }
    }

    private void updateContent() throws RemoteException {
        try {
            users = uma.listAllUsers();
        } catch (nqException ex) {
            Logger.getLogger(StaffTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
