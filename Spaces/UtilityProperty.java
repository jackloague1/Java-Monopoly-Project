package Spaces;
// Represents a utility space

import Game.Player;

public class UtilityProperty extends Space
{
    public String group;
    public int price;
    public int rent;

    public Player owner;

    public UtilityProperty(int x, int y, int spaceNumber, String name, int price)
    {
        super.x = x;
        super.y = y;        
        super.spaceNumber = spaceNumber;
        super.type = "Utility";
        super.name = name;
        
        this.group = "Utility";
        this.price = price;
    }
}
