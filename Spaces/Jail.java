package spaces;

/**
 * Inherits from the Space parent class and represents the Jail/Just Visiting space.
 */
public class Jail extends Space {

    /**
    * Constructor.
    */
    public Jail(int x, int y, int spaceNumber, String name) {
        super.xcoordinate = x;
        super.ycoordinate = y;
        super.spaceNumber = spaceNumber;
        super.name = name;
        super.type = "Jail";
    }
}
