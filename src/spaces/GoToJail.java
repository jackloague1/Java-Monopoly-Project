package src.spaces;

/**
 * Inherits from the Space parent class and represents the Go To Jail space.
 */
public class GoToJail extends Space {

    /**
    * Constructor.
    */
    public GoToJail(int x, int y, int spaceNumber, String name) {
        super.xcoordinate = x;
        super.ycoordinate = y;
        super.spaceNumber = spaceNumber;
        super.name = name;
        super.type = "Go To Jail";
    }
}
