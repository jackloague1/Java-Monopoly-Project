package Spaces;

import java.util.ArrayList;

// Represents a space on the board
public class Space extends Board
{
    public int x;
    public int y;    
    public int spaceNumber;
    public String type;
    public String name;
    public ArrayList<NormalProperty> properties = new ArrayList<NormalProperty>();
    public ArrayList<Card> cards = new ArrayList<Card>();
}
