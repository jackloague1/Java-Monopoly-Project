package src.game;

import java.io.Serializable;

/**
 * Represents a player profile.
 */
public class Profile implements Serializable {
    public String name;
    int nameXcoordinate;
    int nameYcoordinate;
    boolean selected;

    /**
    * Constructor.
    */
    public Profile(String name, int nameXcoordinate, int nameYcoordinate) {
        this.name = name;
        this.nameXcoordinate = nameXcoordinate;
        this.nameYcoordinate = nameYcoordinate;
        selected = false;
    }
}
