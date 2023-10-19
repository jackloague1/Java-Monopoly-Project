package game;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Represents the dice in game.
 */
public class Dice 
{
    final int MIN_NUMBER = 1;
    final int MAX_NUMBER = 6;

    public GamePanel gamePanel;

    public int leftDie = 1;
    public int rightDie = 1;
    public int result;

    public static Image diceImages[] = {new ImageIcon("images/dice-block-1.png").getImage(),
                                        new ImageIcon("images/dice-block-2.png").getImage(),
                                        new ImageIcon("images/dice-block-3.png").getImage(),
                                        new ImageIcon("images/dice-block-4.png").getImage(),
                                        new ImageIcon("images/dice-block-5.png").getImage(),
                                        new ImageIcon("images/dice-block-6.png").getImage()};

    public static JLabel diceLabel;

    public Dice(GamePanel gamePanel)
    {
        this.gamePanel = gamePanel;
    }

    public void getDiceResult()
    {
        leftDie = (int) ((Math.random() * (MAX_NUMBER)) + MIN_NUMBER);
        rightDie = (int) ((Math.random() * (MAX_NUMBER)) + MIN_NUMBER);
        System.out.println(leftDie);
        System.out.println(rightDie);

        result = leftDie + rightDie;
    }

    public void draw(Graphics2D g2d)
    {
        g2d.drawImage(diceImages[leftDie - 1], 361, 532, null);
        g2d.drawImage(diceImages[rightDie - 1], 411, 532, null);
    }
}
