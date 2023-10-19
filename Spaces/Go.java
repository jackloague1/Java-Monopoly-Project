package spaces;
// Represents the Go space

/**
 * Inherits from the Space parent class and represents the Go space.
 */
public class Go extends Space 
{
    int salary;

    public Go(int x, int y, int spaceNumber, String name, int salary)
    {
        super.x = x;
        super.y = y;
        super.spaceNumber = spaceNumber;
        super.name = name;
        super.type = "Go";
        this.salary = salary;
    }
}
