package nextQuest.guiClient;

import nextQuest.server.Ability;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class AbilityControlTest {
    private static AbilityControl abilityControl;
    
    public AbilityControlTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        abilityControl = AbilityControl.getInstance(NextQuestGUIClientTestSuite.uma, new AbilitiesListModel(new Ability[0]));
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
     * Test přidání nové schopnosti - nekorektní údaje
     */
    @Test (expected=WrongInputException.class)
    public void testAddAbilityIncorrect() throws Exception {
        System.out.println("addAbility incorrect");
        String name = "a"; // krátké
        String description = "some logn description";
        abilityControl.addAbility(name, description);
    }
    
    /**
     * Test přidání nové schopnosti - korektní údaje
     */
    @Test
    public void testAddAbilityCorrect() throws Exception {
        System.out.println("addAbility correct");
        String name = "ability name";
        String description = "some logn description";
        abilityControl.addAbility(name, description);
        
        // kontrola vložení do db
        boolean isAdded = false;
        Ability[] allAbilities = NextQuestGUIClientTestSuite.uma.listAblities();
        for (Ability ability : allAbilities) {
            if(ability.equals(new Ability(name, description))) {
                isAdded = true;
                break;
            }
        }
        assertTrue(isAdded);
    }
    
    /**
     * Test odstranění schopnosti
     */
    @Test
    public void testRemoveAbility() throws Exception {
        System.out.println("removeAbility");
        Ability[] allAbilities = NextQuestGUIClientTestSuite.uma.listAblities();
        Ability abl = null;
        for (Ability ability : allAbilities) {
            if(ability.getName().equals("ability name")) {
                abl = ability;
                break;
            }
        }
        abilityControl.removeAbility(abl);

        // kontrola odstranění z db
        boolean isDeleted = true;
        allAbilities = NextQuestGUIClientTestSuite.uma.listAblities();
        for (Ability ability : allAbilities) {
            if(ability.equals(abl)) {
                isDeleted = false;
                break;
            }
        }
        assertTrue(isDeleted);
    }

}
