package spaces;

import game.Player;

/**
 * Inherits from the Space parent class and represents a propety space.
 */
public class NormalProperty extends Space 
{
    public String group;
    public int price;
    public int rent;
    public int[] buildingRentPrices;

    public Player owner;

    public NormalProperty(int x, int y, int spaceNumber, String name, String group, int price, int rent, int[] buildingRentPrices)
    {
        super.x = x;
        super.y = y;
        super.spaceNumber = spaceNumber;
        super.type = "Normal Property";
        super.name = name;

        this.group = group;
        this.price = price;
        this.rent = rent;
        this.buildingRentPrices = buildingRentPrices;
    }
}
