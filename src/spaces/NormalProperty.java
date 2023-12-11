package src.spaces;

import src.game.Player;

/**
 * Inherits from the Space parent class and represents a propety space.
 */
public class NormalProperty extends Space {
    public String group;
    public int price;
    public int normalRent;
    public int buildingPrice;
    public int[] buildingRent;
    public int currentRent;
    public int buildingAmount;

    public Player owner;

    /**
    * Constructor.
    */
    public NormalProperty(int x, int y, int spaceNumber, String name, String group, 
                          int price, int normalRent, int buildingPrice, int[] buildingRent) {
        super.xcoordinate = x;
        super.ycoordinate = y;
        super.spaceNumber = spaceNumber;
        super.type = "Normal Property";
        super.name = name;

        this.group = group;
        this.price = price;
        this.normalRent = normalRent;
        this.buildingPrice = buildingPrice;
        this.buildingRent = buildingRent;

        currentRent = normalRent;
        buildingAmount = 0;
    }

    /**
    * Checks if a single player owns all other properties of the same group as this one.
    */
    // public void checkIfMonopoly() {
    //     for (int i = 0; i < spaceData.normalProperties.size; i++) {
            
    //     }
    // }

    
    /**
    * Returns the current rent of the property.
    */
    // public int getRent() {
    //     int rent = 0;



    //     return rent;
    // }
}
