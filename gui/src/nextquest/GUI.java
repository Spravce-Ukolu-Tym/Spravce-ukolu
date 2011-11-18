/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nextquest;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import nextquest.GUI_quests;

/**
 *
 * @author obik
 */
public class GUI extends JFrame  {
  private JButton LogOut;
    public GUI(){
      super("NextQuest");
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      Container cont = getContentPane();
      cont.setLayout(new BorderLayout());
//TOP - Log out
      JPanel topPanel = new JPanel(false);
              LogOut = new JButton("Log out");
        topPanel.setLayout(new BorderLayout());
        topPanel.add(LogOut,BorderLayout.LINE_END);
//END TOP
//CENTER- Tab Panel
      JTabbedPane Tabb = new JTabbedPane();
      GUI_quests Gquest = new GUI_quests();
      JComponent quest = Gquest;
Tabb.addTab("Quests", null, quest,
                  "NAPOVEDA");

JComponent stat = makeTextPanel("Obsah Statistics");
Tabb.addTab("Statistics", null, stat,
                  "NAPOVEDA");

JComponent staff = makeTextPanel("Obsah Staff");
Tabb.addTab("Staff", null, staff,
                  "NAPOVEDA");

JComponent user = makeTextPanel("Obsah User");
Tabb.addTab("User", null, user,
                      "NAPOVEDA");
//END CENTER
//PAGE END
      JPanel botPanel = new JPanel(false);
             JLabel Authorization = new JLabel("Authorization");
             JLabel Name = new JLabel("Name");
             JLabel Online = new JLabel("Online");
        botPanel.add(Authorization);
        botPanel.add(Name);
        botPanel.add(Online);
         JPanel bottomPanel = new JPanel(false);
          bottomPanel.setLayout(new BorderLayout());
          bottomPanel.add(botPanel,BorderLayout.LINE_END);
//END PAGE END
 //ADD CONTAINER GUI
    cont.add(topPanel,BorderLayout.PAGE_START);
    cont.add(Tabb,BorderLayout.CENTER);
    cont.add(bottomPanel,BorderLayout.PAGE_END);
    pack();
    setVisible(true);
    }
        protected JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }

}
