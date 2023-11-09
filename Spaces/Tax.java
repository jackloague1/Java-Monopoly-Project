package spaces;

/**
 * Inherits from the Space parent class and represents a tax space.
 */
public class Tax extends Space {

    /**
    * Constructor.
    */
    public Tax(int x, int y, int spaceNumber, String name) {
        super.xcoordinate = x;
        super. ycoordinate = y;        
        super.spaceNumber = spaceNumber;
        super.type = "Tax";
        super.name = name;
    }
}
