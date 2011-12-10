package nextQuest.guiClient;

import nextQuest.ifc.iUser;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class LoginControlTest {

    public LoginControlTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        
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
        long sid = 0L;
        String login = "";
        String password = "";
        LoginControl instance = null;
        iUser expResult = null;
        iUser result = instance.login(sid, login, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}