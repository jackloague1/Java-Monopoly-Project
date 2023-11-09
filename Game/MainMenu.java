package game;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * The main menu screen for the game.
 */
public class MainMenu
{
    int startButtonX;
    int startButtonY;
    int startButtonWidth;
    int startButtonHeight;
    
    MainMenu()
    {

    }

    public void draw(Graphics2D g2d)
    {
        // HelperFunctions.drawText(g2d, "Monopoly", Color.black, 72, 200, 200, 400, 100);
        // HelperFunctions.drawText(g2d, "Start", Color.white, 32, 300, 400, 200, 50);
        // HelperFunctions.drawText(g2d, "Profiles", Color.white, 32, 300, 450, 200, 50);
        // HelperFunctions.drawText(g2d, "Options", Color.white, 32, 300, 500, 200, 50);

        g2d.setColor(Color.black);
        g2d.drawPolygon(new int [] {270, 270, 300}, new int [] {410, 440, 425}, 3);
    }
}
