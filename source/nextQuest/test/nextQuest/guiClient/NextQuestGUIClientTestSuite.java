package nextQuest.guiClient;

import java.io.FileInputStream;
import java.rmi.Naming;
import java.util.Scanner;
import nextQuest.ifc.iConnector;
import nextQuest.ifc.iPrivilegedRole;
import nextQuest.ifc.iRoleAdmin;
import nextQuest.ifc.iRoleLeader;
import nextQuest.ifc.iRolePersonalist;
import nextQuest.ifc.iTaskManager;
import nextQuest.ifc.iUser;
import nextQuest.ifc.iUserManagerAdmin;
import nextQuest.mock.TaskManagerMock;
import nextQuest.mock.UserManagerAdminMock;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({nextQuest.guiClient.LoginControlTest.class, nextQuest.guiClient.QuestsControlTest.class, nextQuest.guiClient.StaffControlTest.class, nextQuest.guiClient.AbilityControlTest.class})
public class NextQuestGUIClientTestSuite {
    public static iConnector mg;
    public static iUserManagerAdmin uma;
    public static iTaskManager tm;

    @BeforeClass
    public static void setUpClass() throws Exception {
        // připojení se na server a databázi
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
        
        // vytvoření objektů nebzytných pro práci testovaných managerů
        /*
        long sid = NextQuestGUIClientTestSuite.mg.createLoginSession();
        iUser usr = LoginControl.getInstance(NextQuestGUIClientTestSuite.mg).login(sid, "root", "heslo");
        iRoleAdmin radmin = null;
        iRoleLeader rlead = null;
        iRolePersonalist rper = null;
        iPrivilegedRole[] roles = usr.getRoles();
        for (iPrivilegedRole rl : roles) {
	    if (rl instanceof iRoleAdmin) {
		radmin = (iRoleAdmin) rl;
	    } else if (rl instanceof iRoleLeader) {
		rlead = (iRoleLeader) rl;
	    } else if (rl instanceof iRolePersonalist) {
		rper = (iRolePersonalist) rl;
	    }
        }
        iPrivilegedRole pRole = radmin;
        if(radmin == null) pRole = rper;
        if(pRole instanceof iRoleAdmin) {
            NextQuestGUIClientTestSuite.uma = ((iRoleAdmin) pRole).getUserManagerAdmin();
        } else if(pRole instanceof iRolePersonalist) {
            NextQuestGUIClientTestSuite.uma = ((iRolePersonalist) pRole).getUserManagerAdmin();
        }*/
        
        uma = new UserManagerAdminMock();
        
        //tm = usr.getTaskManager();
        tm = new TaskManagerMock();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        mg = null;
        uma = null;
        tm = null;
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
