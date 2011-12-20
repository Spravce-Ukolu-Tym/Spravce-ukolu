package nextQuest.guiClient;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import nextQuest.guiClient.ProjectsForm;
import nextQuest.ifc.iPrivilegedRole;
import nextQuest.ifc.iRoleAdmin;
import nextQuest.ifc.iRoleLeader;
import nextQuest.ifc.iRolePersonalist;
import nextQuest.ifc.iTask;
import nextQuest.ifc.iUser;
import nextQuest.ifc.iUserManagerAdmin;
import nextQuest.ifc.nqException;
import nextQuest.mock.ProjectManagerMock;
import nextQuest.mock.TaskManagerMock;
import nextQuest.mock.UserManagerAdminMock;
import nextQuest.server.Project;
import nextQuest.server.Task;
import nextQuest.server.User;
import nextQuest.server.UserInfo;

public class MainWindow extends javax.swing.JFrame {
    private LoginDialog parentWindow;
    private iUser usr;
    private iRoleAdmin radmin = null;
    private iRoleLeader rlead = null;
    private iRolePersonalist rper = null;
    private ProjectManagerMock pm;
    private iUserManagerAdmin uma = null;
    private StaffControl staffControl;
    private QuestsControl questsControl;
    QuestsPanel quests = new QuestsPanel();

    /** Creates new form NewJFrame */
    public MainWindow(LoginDialog parentWindow, final iUser usr) throws RemoteException, nqException {
        initComponents();
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screen.width / 2 - getWidth() / 2, screen.height / 2 - getHeight() / 2);

        this.parentWindow = parentWindow;
        
        // zobrazení karet dle oprávnění
        iPrivilegedRole[] roles;
        try
        {
            roles = usr.getRoles(); //ziskat seznam roli
        }
        catch (nqException e)
        {
            System.out.printf("Fail! Exception %s, message: %s\n", e.getType().toString(), e.getMessage());
            return;
        }
        boolean admin = false, leader = false, personalist = false;
        for (iPrivilegedRole rl : roles) //test existujicich roli
        {
            if (rl instanceof iRoleAdmin)
            {
                admin = true;
                radmin = (iRoleAdmin) rl;
            }
            else if (rl instanceof iRoleLeader)
            {
                leader = true;
                rlead = (iRoleLeader) rl;
            }
            else if (rl instanceof iRolePersonalist)
            {
                personalist = true;
                rper = (iRolePersonalist) rl;
            }
        }
        if (!admin) {
            tabbed_pane.remove(pane_projects);
        }
        if (!(leader || admin)) {
            tabbed_pane.remove(pane_tasks);
        }
        if (!(personalist || admin)) {
            tabbed_pane.remove(pane_staff);
        }
        setUserManagerAdmin();

        // inicializace "stavového" řádku
        this.usr = usr;
        try {
            t_name.setText(usr.getName());
            t_authorization.setText((admin ? " admin" : "") + (leader ? " leader" : "") + (personalist ? " personalist" : ""));
        } catch (RemoteException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        } catch (nqException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }

        // inicializace karty Quests
        questsControl = QuestsControl.getInstance();
        ProjectsTableModel tableOfProjects = new ProjectsTableModel(new TaskManagerMock().getAssingnedTasks()/*usr.getTaskManager().getAssingnedTasks()*/);
        table_projects.setModel(tableOfProjects);
        table_projects.updateUI();
        table_projects.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                try {
                    updateQuestList();
                } catch (RemoteException ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        scroll_quests.setViewportView(quests_help_panel);
        quests_help_panel.add(quests);
        updateQuestList();

        // inicializace karty Projects
        pm = new ProjectManagerMock(usr);
        ProjectsTableModelAll tableOfProjects2 = new ProjectsTableModelAll(pm.listProjects());
        table_projects2.setModel(tableOfProjects2);
        table_projects2.updateUI();
        // inicializace karty Staff
        StaffTableModel tableOfStaff;
        try {
            tableOfStaff = new StaffTableModel(uma.listAllUsers());
            staffControl = StaffControl.getInstance(uma, tableOfStaff);
            table_staff.setModel(tableOfStaff);
            table_staff.setRowSelectionInterval(0, 0);
        } catch (nqException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }

        setVisible(true);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        t_name = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        t_authorization = new javax.swing.JLabel();
        tabbed_pane = new javax.swing.JTabbedPane();
        pane_quests = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_projects = new javax.swing.JTable();
        l_projectName = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        b_return_task = new javax.swing.JButton();
        b_reject_task = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        scroll_quests = new javax.swing.JScrollPane();
        quests_help_panel = new javax.swing.JPanel();
        pane_projects = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        table_projects2 = new javax.swing.JTable();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        pane_tasks = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        pane_staff = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_staff = new javax.swing.JTable();
        b_add_new_person = new javax.swing.JButton();
        b_delete_person = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        b_edit_person = new javax.swing.JButton();
        panel_user = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        p_old_password = new javax.swing.JPasswordField();
        p_new_password = new javax.swing.JPasswordField();
        p_password_again = new javax.swing.JPasswordField();
        b_change_password = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jLabel1.setText("Name:");
        jPanel6.add(jLabel1);
        jPanel6.add(t_name);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel6.add(jSeparator1);

        jLabel2.setText("Authorization:");
        jPanel6.add(jLabel2);
        jPanel6.add(t_authorization);

        getContentPane().add(jPanel6, java.awt.BorderLayout.PAGE_END);

        table_projects.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(table_projects);

        l_projectName.setText("ProjectName");

        b_return_task.setText("Return");
        b_return_task.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_return_taskActionPerformed(evt);
            }
        });

        b_reject_task.setText("Reject");
        b_reject_task.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_reject_taskActionPerformed(evt);
            }
        });

        jButton4.setText("Print");

        quests_help_panel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        scroll_quests.setViewportView(quests_help_panel);

        javax.swing.GroupLayout pane_questsLayout = new javax.swing.GroupLayout(pane_quests);
        pane_quests.setLayout(pane_questsLayout);
        pane_questsLayout.setHorizontalGroup(
            pane_questsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pane_questsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pane_questsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE)
                    .addComponent(l_projectName)
                    .addComponent(scroll_quests, javax.swing.GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pane_questsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(b_return_task, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                    .addComponent(b_reject_task, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pane_questsLayout.setVerticalGroup(
            pane_questsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pane_questsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pane_questsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                    .addGroup(pane_questsLayout.createSequentialGroup()
                        .addComponent(l_projectName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addGroup(pane_questsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pane_questsLayout.createSequentialGroup()
                                .addComponent(b_return_task)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(b_reject_task)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4))
                            .addComponent(scroll_quests, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE))))
                .addContainerGap())
        );

        tabbed_pane.addTab("Quests", pane_quests);

        table_projects2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(table_projects2);

        jButton8.setText("Add new project");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setText("Edit project");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setText("Show details...");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton14.setText("Print list");

        javax.swing.GroupLayout pane_projectsLayout = new javax.swing.GroupLayout(pane_projects);
        pane_projects.setLayout(pane_projectsLayout);
        pane_projectsLayout.setHorizontalGroup(
            pane_projectsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pane_projectsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 581, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pane_projectsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pane_projectsLayout.setVerticalGroup(
            pane_projectsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pane_projectsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pane_projectsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
                    .addGroup(pane_projectsLayout.createSequentialGroup()
                        .addComponent(jButton8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9)
                        .addGap(28, 28, 28)
                        .addComponent(jButton10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton14)))
                .addContainerGap())
        );

        tabbed_pane.addTab("Projects", pane_projects);

        jLabel3.setText("Tasks for approval:");

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane4.setViewportView(jList1);

        jLabel4.setText("Task details:");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("Details..");
        jLabel5.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel5.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        jButton11.setText("Add new Task");

        jButton12.setText("Approve");

        jButton13.setText("Reject");

        javax.swing.GroupLayout pane_tasksLayout = new javax.swing.GroupLayout(pane_tasks);
        pane_tasks.setLayout(pane_tasksLayout);
        pane_tasksLayout.setHorizontalGroup(
            pane_tasksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pane_tasksLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pane_tasksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(pane_tasksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(pane_tasksLayout.createSequentialGroup()
                        .addGroup(pane_tasksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pane_tasksLayout.createSequentialGroup()
                                .addComponent(jButton13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton12))
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton11)))
                .addContainerGap())
        );
        pane_tasksLayout.setVerticalGroup(
            pane_tasksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pane_tasksLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pane_tasksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pane_tasksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton11)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
                    .addGroup(pane_tasksLayout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pane_tasksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton12)
                            .addComponent(jButton13))))
                .addContainerGap())
        );

        tabbed_pane.addTab("Tasks", pane_tasks);

        table_staff.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        table_staff.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(table_staff);

        b_add_new_person.setText("Add new person");
        b_add_new_person.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_add_new_personActionPerformed(evt);
            }
        });

        b_delete_person.setText("Delete person");
        b_delete_person.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_delete_personActionPerformed(evt);
            }
        });

        jButton7.setText("Print list");

        b_edit_person.setText("Edit person");
        b_edit_person.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_edit_personActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pane_staffLayout = new javax.swing.GroupLayout(pane_staff);
        pane_staff.setLayout(pane_staffLayout);
        pane_staffLayout.setHorizontalGroup(
            pane_staffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pane_staffLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 581, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pane_staffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(b_add_new_person, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(b_edit_person, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(b_delete_person, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pane_staffLayout.setVerticalGroup(
            pane_staffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pane_staffLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pane_staffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
                    .addGroup(pane_staffLayout.createSequentialGroup()
                        .addComponent(b_add_new_person)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b_edit_person)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b_delete_person)
                        .addGap(34, 34, 34)
                        .addComponent(jButton7)))
                .addContainerGap())
        );

        tabbed_pane.addTab("Staff", pane_staff);

        jLabel6.setText("Old password:");

        jLabel7.setText("New password:");

        jLabel8.setText("New password again:");

        p_old_password.setText("jPasswordField1");

        p_new_password.setText("jPasswordField2");

        p_password_again.setText("jPasswordField3");

        b_change_password.setText("Change password");
        b_change_password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_change_passwordActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_userLayout = new javax.swing.GroupLayout(panel_user);
        panel_user.setLayout(panel_userLayout);
        panel_userLayout.setHorizontalGroup(
            panel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_userLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(b_change_password)
                    .addGroup(panel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(panel_userLayout.createSequentialGroup()
                            .addComponent(jLabel8)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(p_password_again, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panel_userLayout.createSequentialGroup()
                            .addComponent(jLabel7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(p_new_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panel_userLayout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addGap(91, 91, 91)
                            .addComponent(p_old_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(441, Short.MAX_VALUE))
        );
        panel_userLayout.setVerticalGroup(
            panel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_userLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(p_old_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(p_new_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(p_password_again, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(b_change_password)
                .addContainerGap(338, Short.MAX_VALUE))
        );

        tabbed_pane.addTab("User", panel_user);

        getContentPane().add(tabbed_pane, java.awt.BorderLayout.CENTER);
        tabbed_pane.getAccessibleContext().setAccessibleName("");

        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 0, 0));

        jButton1.setText("Log out");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton1);

        getContentPane().add(jPanel5, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        parentWindow.setVisible(true);
        dispose();
}//GEN-LAST:event_jButton1ActionPerformed

    private void b_delete_personActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_delete_personActionPerformed
        if(table_staff.getSelectedRow()>=table_staff.getRowCount()) {
            JOptionPane.showMessageDialog(parentWindow, "None of the users selected.", "Error", JOptionPane.ERROR_MESSAGE ,null);
            return;
        }
        try {
            int selectedRow = table_staff.getSelectedRow();
            UserInfo usrInf = ((StaffTableModel) table_staff.getModel()).getElementAt(selectedRow);
            iUser user = new User(usrInf.getID(), usrInf.getName(), usrInf.getLoginName(), usrInf.getPermissionAdmin(), usrInf.getPermissionLeader(), usrInf.getPermissionPersonalist(), null);

            staffControl.removePerson(user);
            table_staff.updateUI();
            if(table_staff.getSelectedRow()>=table_staff.getRowCount() && table_staff.getRowCount()!=0) {
                table_staff.setRowSelectionInterval(table_staff.getRowCount()-1, table_staff.getRowCount()-1);
            }
 
        } catch (RemoteException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
}//GEN-LAST:event_b_delete_personActionPerformed

    private void b_add_new_personActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_add_new_personActionPerformed
        try {
            PersonForm newPersonForm = new PersonForm(this, true, uma, null);
            table_staff.updateUI();
        } catch (RemoteException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }//GEN-LAST:event_b_add_new_personActionPerformed

    private void b_edit_personActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_edit_personActionPerformed
        try {
            PersonForm newPersonForm = new PersonForm(this, true, uma,
                    ((StaffTableModel) table_staff.getModel()).getElementAt(table_staff.getSelectedRow()));
            table_staff.updateUI();
        } catch (RemoteException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }//GEN-LAST:event_b_edit_personActionPerformed

    private void b_change_passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_change_passwordActionPerformed
        if(p_new_password.getText().equals(p_password_again.getText())) {
        
        }
        //změna hesla
    }//GEN-LAST:event_b_change_passwordActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        try {
            ProjectsForm newProjectForm = new ProjectsForm(this, pm, null,uma);
            ((ProjectsTableModelAll) table_projects2.getModel()).makeRefresh(pm.listProjects());
            table_projects2.updateUI();
        } catch (nqException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton8ActionPerformed

    private void b_return_taskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_return_taskActionPerformed
        try {
            questsControl.returnTask(quests.getSelectedTask());
        } catch (RemoteException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_b_return_taskActionPerformed

    private void b_reject_taskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_reject_taskActionPerformed
        String reason;
        do {
            try {
                reason = JOptionPane.showInputDialog(null, "Input your reason for rejecting this task:", "Task reject", JOptionPane.OK_CANCEL_OPTION | JOptionPane.INFORMATION_MESSAGE);
                questsControl.reject(quests.getSelectedTask(), reason);
                break;
            } catch (RemoteException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            } catch (WrongInputException ex) {
                JOptionPane.showMessageDialog(null, ex.getDescription() ,"No reason inputed", JOptionPane.WARNING_MESSAGE);
            }
        } while(true);
    }//GEN-LAST:event_b_reject_taskActionPerformed

private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
    try {
            ProjectsForm newProjectForm = new ProjectsForm(this, pm, ((ProjectsTableModelAll) table_projects2.getModel()).getElementAt(table_projects2.getSelectedRow()),uma);
            ((ProjectsTableModelAll) table_projects2.getModel()).makeRefresh(pm.listProjects());
            table_projects2.updateUI();
        } catch (nqException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
}//GEN-LAST:event_jButton9ActionPerformed

private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        try {
            ProjectsFormDetail newProjectFormDetail = new ProjectsFormDetail(this,((ProjectsTableModelAll) table_projects2.getModel()).getElementAt(table_projects2.getSelectedRow()));
        } catch (RemoteException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        } catch (nqException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
}//GEN-LAST:event_jButton10ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_add_new_person;
    private javax.swing.JButton b_change_password;
    private javax.swing.JButton b_delete_person;
    private javax.swing.JButton b_edit_person;
    private javax.swing.JButton b_reject_task;
    private javax.swing.JButton b_return_task;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel l_projectName;
    private javax.swing.JPasswordField p_new_password;
    private javax.swing.JPasswordField p_old_password;
    private javax.swing.JPasswordField p_password_again;
    private javax.swing.JPanel pane_projects;
    private javax.swing.JPanel pane_quests;
    private javax.swing.JPanel pane_staff;
    private javax.swing.JPanel pane_tasks;
    private javax.swing.JPanel panel_user;
    private javax.swing.JPanel quests_help_panel;
    private javax.swing.JScrollPane scroll_quests;
    private javax.swing.JLabel t_authorization;
    private javax.swing.JLabel t_name;
    private javax.swing.JTabbedPane tabbed_pane;
    private javax.swing.JTable table_projects;
    private javax.swing.JTable table_projects2;
    private javax.swing.JTable table_staff;
    // End of variables declaration//GEN-END:variables

    private void setUserManagerAdmin() throws RemoteException {
        /*
        iPrivilegedRole pRole = radmin;
        if(radmin == null) pRole = rper;
        try {
            if(pRole instanceof iRoleAdmin) {
                uma = ((iRoleAdmin) pRole).getUserManagerAdmin();
            } else if(pRole instanceof iRolePersonalist) {
                uma = ((iRolePersonalist) pRole).getUserManagerAdmin();
            }
        } catch (nqException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
         */
        this.uma = new UserManagerAdminMock();
    }

    public void updateQuestList() throws RemoteException {
        if(table_projects.getSelectedRow() == -1) table_projects.setRowSelectionInterval(0, 0);
        l_projectName.setText((String) table_projects.getValueAt(table_projects.getSelectedRow(), 0));

        Project selectedProject = ((ProjectsTableModel) table_projects.getModel()).getElemetAt(table_projects.getSelectedRow());
        iTask [] tasks = new TaskManagerMock().getAssingnedTasks(); /*usr.getTaskManager().getAssingnedTasks()*/
        ArrayList<iTask> selectedTasks = new ArrayList<iTask>();
        for (iTask task : tasks) {
            try {
                if (task.getProjectInfo().equals(selectedProject)) {
                    selectedTasks.add((iTask) task);
                }
            } catch (nqException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        quests.updateModel(selectedTasks.toArray(new iTask[0]));
    }

}
