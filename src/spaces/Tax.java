package src.spaces;

/**
 * Inherits from the Space parent class and represents a tax space.
 */
public class Tax extends Space {

    public int fee;

    /**
    * Constructor.
    */
    public Tax(int x, int y, int spaceNumber, String name, int fee) {
        super.xcoordinate = x;
        super. ycoordinate = y;        
        super.spaceNumber = spaceNumber;
        super.type = "Tax";
        super.name = name;

        this.fee = fee;
    }
}
