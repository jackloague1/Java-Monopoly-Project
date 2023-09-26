package Spaces;
// Represents a railroad space

import Game.Player;

public class RailroadProperty extends Space 
{
    public String group;
    public int price;
    public int rent;

    public Player owner;

    public RailroadProperty(int x, int y, int spaceNumber, String name, int price, int rent)
    {
        super.x = x;
        super.y = y;        
        super.spaceNumber = spaceNumber;
        super.type = "Railroad";
        super.name = name;

        this.group = "Railroad";
        this.price = price;
        this.rent = rent;
    }
}