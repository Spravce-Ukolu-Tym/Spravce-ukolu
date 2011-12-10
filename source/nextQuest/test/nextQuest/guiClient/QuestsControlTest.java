/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nextQuest.guiClient;

import nextQuest.server.Task;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tomas
 */
public class QuestsControlTest {

    public QuestsControlTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     * Test of getInstance method, of class QuestsControl.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        QuestsControl expResult = null;
        QuestsControl result = QuestsControl.getInstance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of returnTask method, of class QuestsControl.
     */
    @Test
    public void testReturnTask() {
        System.out.println("returnTask");
        Task t = null;
        QuestsControl instance = null;
        instance.returnTask(t);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of reject method, of class QuestsControl.
     */
    @Test
    public void testReject() throws Exception {
        System.out.println("reject");
        Task t = null;
        String reason = "";
        QuestsControl instance = null;
        instance.reject(t, reason);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}