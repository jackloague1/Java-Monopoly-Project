package src.spaces;

/**
 * Inherits from the Space parent class and represents the Go space.
 */
public class Go extends Space {
    int salary;

    /**
    * Constructor.
    */
    public Go(int x, int y, int spaceNumber, String name, int salary) {
        super.xcoordinate = x;
        super.ycoordinate = y;
        super.spaceNumber = spaceNumber;
        super.name = name;
        super.type = "Go";
        this.salary = salary;
    }
}
