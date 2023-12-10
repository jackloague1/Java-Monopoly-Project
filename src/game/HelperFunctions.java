package src.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

/**
 * This class contains various functions that will be used by multiple classes throughout the game.
 */
public class HelperFunctions {
    //public static void drawSpriteFrame(Graphics2D g2d, Image image)
    /**
    * Draws text within a specified area.
    */
    public static void drawText(Graphics2D g2d, String text, Font font, Color color, int fontSize,
                                int rectX, int rectY, int rectWidth, int rectHeight,
                                boolean centerX, boolean centerY) {
        font = font.deriveFont(Font.PLAIN, fontSize);
        FontMetrics fontMetrics = g2d.getFontMetrics(font);

        // If centerX is true, the text will be centered horizontally.
        if (centerX == true) {
            rectX = rectX + (rectWidth - fontMetrics.stringWidth(text)) / 2;
        }

        // If centerY is true, the text will be centered vertically.
        if (centerY == true) {
            rectY = rectY + ((rectHeight - fontMetrics.getHeight()) / 2) + fontMetrics.getAscent();
        }

        g2d.setColor(color);
        g2d.setFont(font);
        g2d.drawString(text, rectX, rectY);
    }
}
