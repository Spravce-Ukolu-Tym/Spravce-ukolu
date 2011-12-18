package nextQuest.guiClient;

import nextQuest.ifc.iUser;
import nextQuest.server.Ability;
import nextQuest.server.User;
import nextQuest.server.UserInfo;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class StaffControlTest {
    private static StaffControl staffControl;
    
    public StaffControlTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        staffControl = StaffControl.getInstance(NextQuestGUIClientTestSuite.uma, new StaffTableModel(new UserInfo[0]));
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
     * Test přidání zaměstnance - nekorektní údaje
     */
    @Test (expected=WrongInputException.class)
    public void testAddPersonIncorrect() throws Exception {
        System.out.println("addPerson incorrect");
        String login = "michato4";
        String name = "Tomáš Michálek";
        String password = "he"; // krátké
        boolean leader = false;
        boolean personalist = true;
        Ability[] possibleAbilities = NextQuestGUIClientTestSuite.uma.listAblities();
        Ability[] abilities = new Ability[2];
        possibleAbilities[0].setLevel(3);
        abilities[0] = possibleAbilities[0];
        possibleAbilities[1].setLevel(2);
        abilities[1] = possibleAbilities[1];
        staffControl.addPerson(login, name, password, leader, personalist, abilities);
    }
    
    /**
     * Test přidání zaměstnance - korektní údaje
     */
    @Test
    public void testAddPersonCorrect() throws Exception {
        System.out.println("addPerson correct");
        String login = "michato4";
        String name = "Tomáš Michálek";
        String password = "heslo";
        boolean leader = false;
        boolean personalist = true;
        Ability[] possibleAbilities = NextQuestGUIClientTestSuite.uma.listAblities();
        Ability[] abilities = new Ability[2];
        possibleAbilities[0].setLevel(3);
        abilities[0] = possibleAbilities[0];
        possibleAbilities[1].setLevel(2);
        abilities[1] = possibleAbilities[1];
        staffControl.addPerson(login, name, password, leader, personalist, abilities);
        
        // kontrola že je opravdu v db
        boolean isAdded = false;
        UserInfo[] allUsers = NextQuestGUIClientTestSuite.uma.listAllUsers();
        for (UserInfo userInfo : allUsers) {
            if(userInfo.getLoginName().equals(login)) {
                isAdded = true;
                break;
            }
        }
        assertTrue(isAdded);
    }

     /**
     * Test změny údajů zaměstnance - nekorektní údaje
     */
    @Test (expected=WrongInputException.class)
    public void testChangePersonIncorrect() throws Exception {
        System.out.println("changePerson incorrect");
        iUser user = null;
        UserInfo[] allUsers = NextQuestGUIClientTestSuite.uma.listAllUsers();
        for (UserInfo u : allUsers) {
            if(u.getLoginName().equals("michato4")) {
                user = new User(u.getID(), u.getName(), u.getLoginName(), u.getPermissionAdmin(), u.getPermissionLeader(), u.getPermissionPersonalist(), null);
                break;
            }
        }
        String login = "tm"; // krátké
        String name = "Tomáš Michálek";
        String password = "jine_heslo";
        boolean leader = true;
        boolean personalist = false;
        Ability[] possibleAbilities = NextQuestGUIClientTestSuite.uma.listAblities();
        Ability[] abilities = new Ability[2];
        possibleAbilities[3].setLevel(1);
        abilities[0] = possibleAbilities[3];
        staffControl.changePerson(user, login, name, password, leader, personalist, abilities);
    }
    
    /**
     * Test změny údajů zaměstnance - korektní údaje
     */
    @Test
    public void testChangePersonCorrect() throws Exception {
        System.out.println("changePerson correct");
        iUser user = null;
        UserInfo[] allUsers = NextQuestGUIClientTestSuite.uma.listAllUsers();
        for (UserInfo u : allUsers) {
            if(u.getLoginName().equals("michato4")) {
                user = new User(u.getID(), u.getName(), u.getLoginName(), u.getPermissionAdmin(), u.getPermissionLeader(), u.getPermissionPersonalist(), null);
                break;
            }
        }
        String login = "michato5";
        String name = "Tomáš Michálek";
        String password = "jine_heslo";
        boolean leader = true;
        boolean personalist = false;
        Ability[] possibleAbilities = NextQuestGUIClientTestSuite.uma.listAblities();
        Ability[] abilities = new Ability[2];
        possibleAbilities[3].setLevel(1);
        abilities[0] = possibleAbilities[3];
        staffControl.changePerson(user, login, name, password, leader, personalist, abilities);
        
        // kontrola změny v db
        boolean hasChanged = false;
        allUsers = NextQuestGUIClientTestSuite.uma.listAllUsers(); // znovunačtení údajů ze serveru
        for (UserInfo userInfo : allUsers) {
            if(userInfo.getLoginName().equals(login)) {
                hasChanged = true;
            } else if(userInfo.getLoginName().equals("michato4")) {
                hasChanged = false;
                break;
            }
        }
        assertTrue(hasChanged);
    }
    
    /**
     * Test odebrání zaměstnance
     */
    @Test
    public void testRemovePerson() throws Exception {
        System.out.println("removePerson");
        iUser user = null;
        UserInfo[] allUsers = NextQuestGUIClientTestSuite.uma.listAllUsers();
        for (UserInfo u : allUsers) {
            if(u.getLoginName().equals("michato5")) {
                user = new User(u.getID(), u.getName(), u.getLoginName(), u.getPermissionAdmin(), u.getPermissionLeader(), u.getPermissionPersonalist(), null);
                break;
            }
        }
        staffControl.removePerson(user);
        
        // kontrola zda byl odebrán z db
        boolean isDeleted = true;
        allUsers = NextQuestGUIClientTestSuite.uma.listAllUsers(); // znovunačtení údajů ze severu
        for (UserInfo u : allUsers) {
            if(u.getLoginName().equals("michato5")) {
                isDeleted = false;
                break;
            }
        }
        assertTrue(isDeleted);
    }
}
