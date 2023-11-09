package spaces;

/**
 * Inherits from the Space parent class and represents a card space (Chance or Community Chest).
 */
public class Card extends Space {

    /**
    * Constructor.
    */
    public Card(int x, int y, int spaceNumber, String name) {
        super.xcoordinate = x;
        super.ycoordinate = y;        
        super.spaceNumber = spaceNumber;
        super.type = "Card";
        super.name = name;
    }
}
