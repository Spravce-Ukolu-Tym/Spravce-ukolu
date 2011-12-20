package nextQuest.guiClient;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import nextQuest.ifc.iTask;
import nextQuest.ifc.nqException;
import nextQuest.mock.ProjectManagerMock;
import nextQuest.server.Project;

public class ProjectsTableModelAll extends AbstractTableModel {
        private String[] columnNames = {"Project", "Leader"};
        private ArrayList<Project> projects = new ArrayList<Project>();
        public ProjectsTableModelAll(Project[] project) throws RemoteException{
         projects.addAll(Arrays.asList(project));
         Collections.sort((List) projects);
        }
        public void makeRefresh(Project[] project){
                projects.removeAll(projects);
                projects.addAll(Arrays.asList(project));
                Collections.sort((List) projects);
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
            } else if(columnName.equals("Leader")) {
                if( projects.get(rowIndex).getLeader()==null){
                 return "";  
                }else{
                return projects.get(rowIndex).getLeader().getName();
                }
            } else {
                return "error value";
            }
        }
        public Project getElementAt(int rowIndex) {
        return projects.get(rowIndex);
    }
        
    }