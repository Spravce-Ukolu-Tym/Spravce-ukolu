package nextQuest.guiClient;

import nextQuest.ifc.iTask;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class QuestsControlTest {
    private static QuestsControl questsControl;
    
    public QuestsControlTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        questsControl = QuestsControl.getInstance();
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
     * Test mechanizmu odevzdání úlohy
     */
    @Test
    public void testReturnTask() throws Exception {
        System.out.println("returnTask");
        iTask t = NextQuestGUIClientTestSuite.tm.getAssingnedTasks()[0];
        questsControl.returnTask(t);
        // TODO: ověřit nějak, že byl úkol odevzdán
    }

     /**
     * Test mechanizmu odmítnutí úkolu - zadání nekorektních údajů
     */
    @Test (expected=WrongInputException.class)
    public void testRejectIncorrect() throws Exception {
        System.out.println("reject incorrect");
        iTask t = NextQuestGUIClientTestSuite.tm.getAssingnedTasks()[0];
        questsControl.reject(t, "");
    }
    
    /**
     * Test mechanizmu odmítnutí úkolu - zadání korektních údajů
     */
    @Test
    public void testRejectCorrect() throws Exception {
        System.out.println("reject correct");
        iTask t = NextQuestGUIClientTestSuite.tm.getAssingnedTasks()[0];
        questsControl.reject(t, null);
        questsControl.reject(t, "reason");
        // ověření, že byl úkol odmítnut
        //NextQuestGUIClientTestSuite.tm.getAssingnedTasks()[0].getStatus().equals(eTaskStatus.REJECTED);
    }
    
}
