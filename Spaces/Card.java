package spaces;

/**
 * Inherits from the Space parent class and represents a card space (Chance or Community Chest).
 */
public class Card extends Space 
{

    public Card(int x, int y, int spaceNumber, String name)
    {
        super.x = x;
        super.y = y;        
        super.spaceNumber = spaceNumber;
        super.type = "Card";
        super.name = name;
    }
}
