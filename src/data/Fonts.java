package src.data;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

/**
* Stores custom fonts that will be used in the game.
*/
public class Fonts {
    public Font pixeloidSans;

    public Fonts() {
        createFonts();
    }

    /**
    * Creates all custom fonts that will be used in the game.
    */
    public void createFonts() {
        try {
            pixeloidSans 
                = Font.createFont(Font.TRUETYPE_FONT, new File("src/fonts/PixeloidSans.ttf"));
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }   
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(pixeloidSans);
        pixeloidSans = pixeloidSans.deriveFont(Font.PLAIN, 12f);
    }
}
