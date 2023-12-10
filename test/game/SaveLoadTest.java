package test.game;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.Test;
import src.game.Profile;
import src.game.SaveLoad;

/**
 * This class will store unit tests for the SaveLoad class.
 */
public class SaveLoadTest {
    
    /**
    * Tests the saveProfiles and loadProfiles methods in the SaveLoad class to ensure that the
    * software can successfully save and load an arraylist of profile objects.
    */
    @Test
    public void saveAndLoadProfilesTest() throws IOException, ClassNotFoundException {
        SaveLoad saveLoadTest = new SaveLoad();

        ArrayList<Profile> savedProfiles = new ArrayList<Profile>();
        ArrayList<Profile> loadedProfiles = new ArrayList<Profile>();
        
        savedProfiles.add(new Profile("name1", 0, 0));
        savedProfiles.add(new Profile("name2", 0, 0));
        savedProfiles.add(new Profile("name3", 0, 0));

        saveLoadTest.saveProfiles(savedProfiles, 
                                  new FileOutputStream("test/saved-data/ProfilesTest.txt"));

        loadedProfiles = 
            saveLoadTest.loadProfiles(new FileInputStream("test/saved-data/ProfilesTest.txt"));

        // Compare the saved and loaded profile lists to see if they contain the same profile names
        // in the same order.
        for (int i = 0; i < savedProfiles.size(); i++) {
            assertEquals(savedProfiles.get(i).name, loadedProfiles.get(i).name);
        }
    }
}
