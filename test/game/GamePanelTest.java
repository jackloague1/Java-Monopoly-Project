package test.game;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import org.junit.Test;
import src.game.GamePanel;
import src.game.Profile;

/**
 * This class will store unit tests for the GamePanel class.
 */
public class GamePanelTest {
    
    /**
    * Tests the isUniqueProfileName method in the GamePanel class to ensure that the software can
    * determine if a given profile name is unique compared to existing profile names in an
    * arraylist of profile objects.
    */
    @Test
    public void isUniqueProfileNameTest() {
        GamePanel gamePanelTest = new GamePanel();

        ArrayList<Profile> profilesTest = new ArrayList<Profile>();

        profilesTest.add(new Profile("name1", 0, 0));
        profilesTest.add(new Profile("name2", 0, 0));
        profilesTest.add(new Profile("name3", 0, 0));

        assertEquals(true, gamePanelTest.isUniqueProfileName("name4", profilesTest));
        assertEquals(false, gamePanelTest.isUniqueProfileName("name3", profilesTest));
    }
}
