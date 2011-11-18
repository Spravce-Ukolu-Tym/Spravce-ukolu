package nextQuest.guiClient;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;
import javax.swing.JOptionPane;
import nextQuest.ifc.*;

public class nextQuestClient
{
    public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException
    {
        String server = "";
        try {
            Scanner s = new Scanner(new FileInputStream("config.txt"), "UTF8");
            server = s.next();
            s.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Mising configuration file..", "Error", JOptionPane.ERROR_MESSAGE, null);
        }

	iConnector mg = (iConnector) Naming.lookup(server);

        LoginDialog loginScreen = new LoginDialog(mg);
        loginScreen.setVisible(true);
    }
}
