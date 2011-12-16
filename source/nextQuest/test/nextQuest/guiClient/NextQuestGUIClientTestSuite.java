package nextQuest.guiClient;

import java.io.FileInputStream;
import java.rmi.Naming;
import java.util.Scanner;
import nextQuest.ifc.iConnector;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({nextQuest.guiClient.LoginControlTest.class,nextQuest.guiClient.AbilityControlTest.class,nextQuest.guiClient.QuestsControlTest.class,nextQuest.guiClient.StaffControlTest.class})
public class NextQuestGUIClientTestSuite {
    public static iConnector mg;

    @BeforeClass
    public static void setUpClass() throws Exception {
        String server = "";
        int port = -1;

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

        if(server.equals("") || port == -1) {
            System.exit(0);
        }

	mg = (iConnector) Naming.lookup(server);
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        mg = null;
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

}