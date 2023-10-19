package spaces;

/**
 * Inherits from the Space parent class and represents a tax space.
 */
public class Tax extends Space 
{

    public Tax(int x, int y, int spaceNumber, String name)
    {
        super.x = x;
        super. y = y;        
        super.spaceNumber = spaceNumber;
        super.type = "Tax";
        super.name = name;
    }
}
