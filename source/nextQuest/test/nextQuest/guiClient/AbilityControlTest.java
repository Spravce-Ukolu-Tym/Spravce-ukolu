package nextQuest.guiClient;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class AbilityControlTest {

    public AbilityControlTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        //AbilityControl control = AbilityControl.getInstance(null, null)
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     * Test of removeAbility method, of class AbilityControl.
     */
    @Test
    public void testRemoveAbility() throws Exception {
        System.out.println("removeAbility");
        int index = 0;
        AbilityControl instance = null;
        instance.removeAbility(index);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addAbility method, of class AbilityControl.
     */
    @Test
    public void testAddAbility() throws Exception {


        System.out.println("addAbility");
        String name = "";
        String description = "";
        AbilityControl instance = null;
        instance.addAbility(name, description);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}