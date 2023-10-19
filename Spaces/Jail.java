package spaces;

/**
 * Inherits from the Space parent class and represents the Jail/Just Visiting space.
 */
public class Jail extends Space 
{
    public Jail(int x, int y, int spaceNumber, String name)
    {
        super.x = x;
        super.y = y;
        super.spaceNumber = spaceNumber;
        super.name = name;
        super.type = "Jail";
    }
}
