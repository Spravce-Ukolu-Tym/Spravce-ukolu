/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nextQuest.guiClient;

import nextQuest.ifc.iUser;
import nextQuest.ifc.iUserManagerAdmin;
import nextQuest.server.Ability;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tomas
 */
public class StaffControlTest {

    public StaffControlTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     * Test of getInstance method, of class StaffControl.
     */
    @Test
    public void testGetInstance_iUserManagerAdmin_StaffTableModel() {
        System.out.println("getInstance");
        iUserManagerAdmin umadmin = null;
        StaffTableModel tableModel = null;
        StaffControl expResult = null;
        StaffControl result = StaffControl.getInstance(umadmin, tableModel);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInstance method, of class StaffControl.
     */
    @Test
    public void testGetInstance_0args() {
        System.out.println("getInstance");
        StaffControl expResult = null;
        StaffControl result = StaffControl.getInstance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removePerson method, of class StaffControl.
     */
    @Test
    public void testRemovePerson() throws Exception {
        System.out.println("removePerson");
        iUser user = null;
        StaffControl instance = null;
        instance.removePerson(user);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addPerson method, of class StaffControl.
     */
    @Test
    public void testAddPerson() throws Exception {
        System.out.println("addPerson");
        String login = "";
        String name = "";
        String password = "";
        boolean leader = false;
        boolean personalist = false;
        Ability[] abilities = null;
        StaffControl instance = null;
        instance.addPerson(login, name, password, leader, personalist, abilities);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of changePerson method, of class StaffControl.
     */
    @Test
    public void testChangePerson() throws Exception {
        System.out.println("changePerson");
        iUser user = null;
        String login = "";
        String name = "";
        String password = "";
        boolean leader = false;
        boolean personalist = false;
        Ability[] abilities = null;
        StaffControl instance = null;
        instance.changePerson(user, login, name, password, leader, personalist, abilities);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}