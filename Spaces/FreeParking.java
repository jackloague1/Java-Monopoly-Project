package spaces;

/**
 * Inherits from the Space parent class and represents the Free Parking space.
 */
public class FreeParking extends Space
{
    public FreeParking(int x, int y, int spaceNumber, String name)
    {
        super.x = x;
        super.y = y;
        super.spaceNumber = spaceNumber;
        super.name = name;
        super.type = "Free Parking";
    }
}
