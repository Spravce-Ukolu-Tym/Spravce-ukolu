package nextQuest.guiClient;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import nextQuest.ifc.iTask;
import nextQuest.ifc.iUser;
import nextQuest.ifc.nqException;
import nextQuest.mock.TaskManagerMock;
import nextQuest.server.Project;

public class ProjectsTableModel extends AbstractTableModel {
        private String[] columnNames = {"Project", "Priority"};
        private ArrayList<Project> projects = new ArrayList<Project>();

        public ProjectsTableModel(iTask [] tasks) throws RemoteException {
            try {
                for (iTask task : tasks) {
                    if(!projects.contains(task.getProject())) projects.add(task.getProject());   // získat z úkolů projekty, ve kterých jsou zařazeny
                }
                Collections.sort((List) projects);
            } catch (nqException ex) {
                Logger.getLogger(ProjectsTableModel.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }

        @Override
        public int getRowCount() {
            return projects.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            String columnName = columnNames[columnIndex]; // pro přehlednost
            if(columnName.equals("Project")) {
                return projects.get(rowIndex).getName();
            } else if(columnName.equals("Priority")) {
                return projects.get(rowIndex).getPriority();
            } else {
                return "error value";
            }
        }

        public Project getElemetAt(int index) {
            return projects.get(index);
        }
}

