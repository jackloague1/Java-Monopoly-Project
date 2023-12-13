package src.spaces;

import java.awt.Image;
import src.game.Player;

/**
 * Inherits from the Space parent class and represents a railroad space.
 */
public class RailroadProperty extends Space {
    public Image image;

    public String group;
    public int price;
    public int mortgageValue;
    public int unMortgageFee;
    public int[] groupRent;
    public int currentRent;
    public boolean isMortgaged;
    
    public Player owner;

    /**
    * Constructor.
    */
    public RailroadProperty(Image image, int x, int y, int spaceNumber, String name, int price, 
                            int[] groupRent) {
        super.xcoordinate = x;
        super.ycoordinate = y;        
        super.spaceNumber = spaceNumber;
        super.type = "Railroad";
        super.name = name;

        this.image = image;

        this.group = "Railroad";
        this.price = price;
        this.groupRent = groupRent;

        mortgageValue = price / 2;
        unMortgageFee = Math.round(mortgageValue + (mortgageValue * 0.1f));
        
        currentRent = groupRent[0];

        isMortgaged = false;
    }
}