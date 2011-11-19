/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nextquest;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;



/**
 *
 * @author obik
 */
public class GUI_quests extends JPanel  {
String labels[][]=new String[5][2];
  public GUI_quests() {
    //LEFT
    String data[][] = {
    {"001","1"},
    {"002","2"},
    {"003","1"},
    {"004","1"},
    {"005","3"},
        {"001","1"},
    {"002","2"},
    {"003","1"},
    {"004","1"},
    {"005","3"},
        {"001","1"},
    {"002","2"},
    {"003","1"},
    {"004","1"},
    {"005","3"},
        {"001","1"},
    {"002","2"},
    {"003","1"},
    {"004","1"},
    {"005","3"},    {"001","1"},
    {"002","2"},
    {"003","1"},
    {"004","1"},
    {"005","3"},
        {"001","1"},
    {"002","2"},
    {"003","1"},
    {"004","1"},
    {"005","3"},

};
  String col[] = {"Project","Priority"};
  JTable table = new JTable(data,col);
  JTableHeader header = table.getTableHeader();
  header.setBackground(Color.lightGray);
  JScrollPane scrollPane = new JScrollPane(table);
  //END LEFT
  //CENTER
    JLabel label = new JLabel("Projekt");
    //END CENTER
  //RIGHT BUTTONS
  JButton Accept = new JButton("Accept");
  JButton Reject = new JButton("Reject");
  JButton Print = new JButton("Print");
  JPanel butPanel = new JPanel(false);
  butPanel.setLayout(new GridLayout(12, 0));
  butPanel.add(Accept);
  butPanel.add(Reject);
  butPanel.add(Print);
  //END RIGHT BUTTON
  this.setLayout(new BorderLayout());
  this.add(scrollPane,BorderLayout.LINE_START);
  this.add(label,BorderLayout.CENTER);
  this.add(butPanel,BorderLayout.LINE_END);
  }

}
