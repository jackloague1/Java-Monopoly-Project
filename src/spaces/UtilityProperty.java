package src.spaces;

import src.game.Player;

/**
 * Inherits from the Space parent class and represents a utility space.
 */
public class UtilityProperty extends Space {
    public String group;
    public int price;
    public int rent;

    public Player owner;

    /**
    * Constructor.
    */
    public UtilityProperty(int x, int y, int spaceNumber, String name, int price) {
        super.xcoordinate = x;
        super.ycoordinate = y;        
        super.spaceNumber = spaceNumber;
        super.type = "Utility";
        super.name = name;
        
        this.group = "Utility";
        this.price = price;
    }
}
