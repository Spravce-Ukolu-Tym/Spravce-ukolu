package nextQuest.guiClient;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import nextQuest.ifc.*;

public class nextQuestClient
{
    public static void main(String[] args)
    {
        ConnectingInformer inf = new ConnectingInformer();
        inf.setVisible(true);

        String server = "";
        int port = -1;
        try {
            Scanner s = new Scanner(new FileInputStream("config.txt"), "UTF8");
            String arg;
            while(s.hasNext()) {
                arg = s.next();
                String[] pr = arg.split("=");

                if (pr.length != 2) {
                    continue;
                }
                // vyhodnocení config parametrů
                if (pr[0].equals("server")) {
                    server = pr[1];
                } else if(pr[0].equals("port")) {
                    port = Integer.parseInt(pr[1]);
                }
            }
            s.close();
        } catch (IOException e) {
            inf.setVisible(false);
            JOptionPane.showMessageDialog(null, "Mising configuration file..", "Error", JOptionPane.ERROR_MESSAGE, null);
            System.exit(0);
        }
        if(server.equals("") || port == -1) {
            inf.setVisible(false);
            JOptionPane.showMessageDialog(null, "Bad configuration file format..", "Error", JOptionPane.ERROR_MESSAGE, null);
            System.exit(0);
        }

	iConnector mg;
        try {
            mg = (iConnector) Naming.lookup(server);
            inf.setVisible(false);
            LoginDialog loginScreen = new LoginDialog(mg);
            loginScreen.setVisible(true);
        } catch (NotBoundException ex) {
            inf.setVisible(false);
            JOptionPane.showMessageDialog(null, "Not bound..", "Error", JOptionPane.ERROR_MESSAGE, null);
            Logger.getLogger(nextQuestClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            inf.setVisible(false);
            JOptionPane.showMessageDialog(null, "Malformed URL..", "Error", JOptionPane.ERROR_MESSAGE, null);
            Logger.getLogger(nextQuestClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            inf.setVisible(false);
            JOptionPane.showMessageDialog(null, "Remote exception..", "Error", JOptionPane.ERROR_MESSAGE, null);
            Logger.getLogger(nextQuestClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
