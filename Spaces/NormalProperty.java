package spaces;

import game.Player;

/**
 * Inherits from the Space parent class and represents a propety space.
 */
public class NormalProperty extends Space {
    public String group;
    public int price;
    public int rent;
    public int buildingPrice;
    public int[] buildingRentPrices;

    public Player owner;

    /**
    * Constructor.
    */
    public NormalProperty(int x, int y, int spaceNumber, String name, String group, 
                          int price, int rent, int buildingPrice, int[] buildingRentPrices) {
        super.xcoordinate = x;
        super.ycoordinate = y;
        super.spaceNumber = spaceNumber;
        super.type = "Normal Property";
        super.name = name;

        this.group = group;
        this.price = price;
        this.rent = rent;
        this.buildingPrice = buildingPrice;
        this.buildingRentPrices = buildingRentPrices;
    }
}
