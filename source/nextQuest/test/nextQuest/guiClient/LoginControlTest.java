package nextQuest.guiClient;

import nextQuest.ifc.iUser;
import org.junit.AfterClass;
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

    /**
     * Test of login method, of class LoginControl.
     */
    @Test
    public void testLogin() throws Exception {
        System.out.println("login");
        long sid = NextQuestGUIClientTestSuite.mg.createLoginSession();
        iUser usr = loginControl.login(sid, "root", "heslo");
        assertEquals(usr.getLoginName(), "root");
    }

}