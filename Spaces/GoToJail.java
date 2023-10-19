package spaces;

/**
 * Inherits from the Space parent class and represents the Go To Jail space.
 */
public class GoToJail extends Space
{
    public GoToJail(int x, int y, int spaceNumber, String name)
    {
        super.x = x;
        super.y = y;
        super.spaceNumber = spaceNumber;
        super.name = name;
        super.type = "Go To Jail";
    }
}
