package src.game;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Contains functions that will save data when the program is exited, and load data whenever the
 * program is started.
 */
public class SaveLoad {
    // FileOutputStream profilesFile;

    /**
    * Constructor
    */
    // SaveLoad() throws IOException {
    //     profilesFile = new FileOutputStream("Profiles.txt");
    // }

    /**
    * Saves all profiles once the program is exited.
    */
    public void saveProfiles(ArrayList<Profile> profiles, FileOutputStream fileOutputStream) throws IOException {
        // FileOutputStream fileOutputStream = new FileOutputStream("src/saved-data/Profiles.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(profiles);
        objectOutputStream.flush();
        objectOutputStream.close();
    }

    /**
    * Loads all profiles once the program is started.
    */
    public ArrayList<Profile> loadProfiles(FileInputStream fileInputStream) throws IOException, ClassNotFoundException {
        // FileInputStream fileInputStream = new FileInputStream("src/saved-data/Profiles.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream); 
        ArrayList<Profile> profiles = (ArrayList<Profile>) objectInputStream.readObject();
        objectInputStream.close();

        return profiles;
    }    
}
