package nextQuest.guiClient;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractListModel;
import javax.swing.JOptionPane;
import nextQuest.ifc.iUserManagerAdmin;
import nextQuest.ifc.nqException;
import nextQuest.server.Ability;

public class AbilityEditor extends javax.swing.JDialog {
    private Frame parent;
    private iUserManagerAdmin uma;
    private AbilitiesListModel listModel;
    private AbilityControl abilityControl;

    /** Creates new form AbilityEditor */
    public AbilityEditor(java.awt.Frame parent, boolean modal, iUserManagerAdmin uma) throws RemoteException {
        super(parent, modal);
        this.parent = parent;
        this.uma = uma;
        try {
            listModel = new AbilitiesListModel(uma.listAblities());
        } catch (nqException ex) {
            Logger.getLogger(AbilityEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
        abilityControl = AbilityControl.getInstance(uma, listModel);

        initComponents();
        // inicializace seznamu schopností
        list_abilities.setModel(listModel);
        if(((AbilitiesListModel) list_abilities.getModel()).getSize()>0) list_abilities.setSelectedIndex(0);
        updateAbilityDescription();

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screen.width / 2 - getWidth() / 2, screen.height / 2 - getHeight() / 2);
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

        jScrollPane2 = new javax.swing.JScrollPane();
        l_description = new javax.swing.JLabel();
        b_add_ability = new javax.swing.JButton();
        b_remove = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        list_abilities = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        b_cancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ability Editor");
        setModal(true);
        setResizable(false);

        l_description.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        l_description.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        l_description.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        l_description.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        l_description.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jScrollPane2.setViewportView(l_description);

        b_add_ability.setText("Add new");
        b_add_ability.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_add_abilityActionPerformed(evt);
            }
        });

        b_remove.setText("Remove");
        b_remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_removeActionPerformed(evt);
            }
        });

        jLabel2.setText("Ability detail:");

        list_abilities.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        list_abilities.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        list_abilities.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                list_abilitiesValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(list_abilities);

        jLabel1.setText("Abilities:");

        b_cancel.setText("Cancel");
        b_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_cancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(b_cancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(b_remove, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(b_add_ability, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(51, 51, 51)
                        .addComponent(jLabel2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(b_add_ability)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b_remove)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                        .addComponent(b_cancel))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void b_add_abilityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_add_abilityActionPerformed
        NewAbilityForm newAbilityForm = new NewAbilityForm(parent, true, uma);
        list_abilities.updateUI();
        updateAbilityDescription();
}//GEN-LAST:event_b_add_abilityActionPerformed

    private void list_abilitiesValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_list_abilitiesValueChanged
        updateAbilityDescription();
    }//GEN-LAST:event_list_abilitiesValueChanged

    private void b_removeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_removeActionPerformed
        if(((AbilitiesListModel) list_abilities.getModel()).getSize()<=0) {
            JOptionPane.showMessageDialog(parent, "No ability to delete!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            abilityControl.removeAbility(listModel.getAbility(list_abilities.getSelectedIndex()));
            list_abilities.updateUI();
        } catch (RemoteException ex) {
            Logger.getLogger(AbilityEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(((AbilitiesListModel) list_abilities.getModel()).getSize()>0) list_abilities.setSelectedIndex(((AbilitiesListModel) list_abilities.getModel()).getSize()-1);
    }//GEN-LAST:event_b_removeActionPerformed

    private void b_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_cancelActionPerformed
        dispose();
    }//GEN-LAST:event_b_cancelActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_add_ability;
    private javax.swing.JButton b_cancel;
    private javax.swing.JButton b_remove;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel l_description;
    private javax.swing.JList list_abilities;
    // End of variables declaration//GEN-END:variables

    private void updateAbilityDescription() {
        if(((AbilitiesListModel) list_abilities.getModel()).getSize()<=0) return;
        Ability abl = (Ability) ((AbilitiesListModel) list_abilities.getModel()).getAbility(list_abilities.getSelectedIndex());
        l_description.setText("<html>"+abl.getDescription()+"</html>");
    }

    

}