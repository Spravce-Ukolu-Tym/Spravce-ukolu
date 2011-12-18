package nextQuest.guiClient;

import nextQuest.ifc.iRolePersonalist;
import nextQuest.ifc.iRoleLeader;
import nextQuest.ifc.iRoleAdmin;
import nextQuest.ifc.iPrivilegedRole;
import nextQuest.ifc.iUser;
import nextQuest.ifc.nqException;
import nextQuest.mock.TaskManagerMock;
import nextQuest.mock.UserManagerAdminMock;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class LoginControlTest {
    private static LoginControl loginControl;
    
    public LoginControlTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        loginControl = LoginControl.getInstance(NextQuestGUIClientTestSuite.mg);
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Testuje, zda funguje přihlášení se správnými identifikačními údaji
     */
    @Test
    public void testSuccessfulLogin() throws Exception {
        System.out.println("successful login");
        long sid = NextQuestGUIClientTestSuite.mg.createLoginSession();
        iUser usr = loginControl.login(sid, "root", "heslo");    
        assertEquals(usr.getLoginName(), "root");
    }
    
    /**
     * Testuje, zda bude pokus o přihlášení s nesprávnými identifikačními údaji odmítnut
     */
    @Test (expected=nqException.class)
    public void testUnsuccessfulLogin() throws Exception {
        System.out.println("unsuccessful login");
        long sid = NextQuestGUIClientTestSuite.mg.createLoginSession();
        iUser usr = loginControl.login(sid, "root", "spatne_heslo");
        assertEquals(usr.getLoginName(), "root");
    }
}
