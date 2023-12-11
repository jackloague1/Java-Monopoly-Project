package src.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import src.spaces.NormalProperty;

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

    /**
    * Draws a blank box on screen.
    */
    public static void drawBlankBox(Graphics2D g2d, Color borderColor, Color mainColor,
                           int x, int y, int width, int height, int borderWidth) {
        g2d.setColor(borderColor);
        g2d.fillRect(x, y, width, height);
        g2d.setColor(mainColor);
        g2d.fillRect(x + borderWidth, y + borderWidth, 
                     width - (borderWidth * 2), height - (borderWidth * 2));
    }

    /**
    * Returns the color group of a normal property.
    */
    public static Color getColor(NormalProperty property) {
        Color color = new Color(0, 0, 0);
        if (property.group == "Purple") {
            color = new Color(90, 0, 123);
        } else if (property.group == "Light Blue") {
            color = new Color(181, 239, 239);
        } else if (property.group == "Pink") {
            color = new Color(181, 33, 123);
        } else if (property.group == "Orange") {
            color = new Color(231, 156, 33);
        } else if (property.group == "Red") {
            color = new Color(181, 49, 33);
        } else if (property.group == "Yellow") {
            color = new Color(231, 231, 148);
        } else if (property.group == "Green") {
            color = new Color(90, 231, 49);
        } else if (property.group == "Blue") {
            color = new Color(16, 16, 165);
        }

        return color;
    }
}
