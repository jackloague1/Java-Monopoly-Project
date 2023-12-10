package src.spaces;

/**
 * Inherits from the Space parent class and represents the Free Parking space.
 */
public class FreeParking extends Space {
    /**
    * Constructor.
    */
    public FreeParking(int x, int y, int spaceNumber, String name) {
        super.xcoordinate = x;
        super.ycoordinate = y;
        super.spaceNumber = spaceNumber;
        super.name = name;
        super.type = "Free Parking";
    }
}
