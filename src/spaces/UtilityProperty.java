package src.spaces;

import src.game.Player;

/**
 * Inherits from the Space parent class and represents a utility space.
 */
public class UtilityProperty extends Space {
    public String group;
    public int price;
    public int mortgageValue;
    public int unMortgageFee;
    public int[] rentMultiplier;
    public int currentMultiplier;
    public boolean isMortgaged;

    public Player owner;

    /**
    * Constructor.
    */
    public UtilityProperty(int x, int y, int spaceNumber, String name, int price, 
                           int[] rentMultiplier) {
        super.xcoordinate = x;
        super.ycoordinate = y;        
        super.spaceNumber = spaceNumber;
        super.type = "Utility";
        super.name = name;
        
        this.group = "Utility";
        this.price = price;
        this.rentMultiplier = rentMultiplier;

        mortgageValue = price / 2;
        unMortgageFee = Math.round(mortgageValue + (mortgageValue * 0.1f));
        
        currentMultiplier = rentMultiplier[0];

        isMortgaged = false;
    }
}
