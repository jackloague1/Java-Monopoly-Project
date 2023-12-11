package src.spaces;

import src.game.Player;

/**
 * Inherits from the Space parent class and represents a railroad space.
 */
public class RailroadProperty extends Space {
    public String group;
    public int price;
    public int[] groupRent;
    public int currentRent;

    public Player owner;

    /**
    * Constructor.
    */
    public RailroadProperty(int x, int y, int spaceNumber, String name, int price, 
                            int[] groupRent) {
        super.xcoordinate = x;
        super.ycoordinate = y;        
        super.spaceNumber = spaceNumber;
        super.type = "Railroad";
        super.name = name;

        this.group = "Railroad";
        this.price = price;
        this.groupRent = groupRent;
        
        currentRent = groupRent[0];
    }
}