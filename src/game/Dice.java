package src.game;

import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * Represents the dice in game.
 */
public class Dice {
    public final Image diceSpriteSheet;

    final int minNumber;
    final int maxNumber;

    final int diceWidth;
    final int diceHeight;

    public GamePanel gamePanel;

    public int leftDie;
    public int rightDie;
    public int result;

    int leftDieX;
    int leftDieY;
    int rightDieX;
    int rightDieY;

    int leftDieSpriteSheetX;
    int leftDieSpriteSheetY;
    int rightDieSpriteSheetX;
    int rightDieSpriteSheetY;

    int iterations = 0;

    /**
    * Constructor.
    */
    public Dice(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        diceSpriteSheet = new ImageIcon("images/Dice-Sprite-Sheet.png").getImage();

        minNumber = 1;
        maxNumber = 6;

        diceWidth = 38;
        diceHeight = 38;

        leftDie = 1;
        rightDie = 1;

        leftDieX = 361;
        leftDieY = 532;
        rightDieX = 411;
        rightDieY = 532;
    }

    /**
    * Calculates the result number as well as the correct sprite to use for each die when a player 
      rolls the dice.
    */
    public void getDiceResult() {
        leftDie = (int) ((Math.random() * (maxNumber)) + minNumber);
        rightDie = (int) ((Math.random() * (maxNumber)) + minNumber);
        // leftDie = 5;
        // rightDie = 5;
        System.out.println(leftDie);
        System.out.println(rightDie);

        result = leftDie + rightDie;

        leftDieSpriteSheetX = diceWidth * (leftDie - 1);
        rightDieSpriteSheetY = 0;
        rightDieSpriteSheetX = diceWidth * (rightDie - 1);
        rightDieSpriteSheetY = 0;       
    }

    // public void move()
    // {
    //     if (gamePanel.diceDelta >= Settings.DICE_MOVE_SPEED)
    //     {
    //         if (iterations < 10)
    //         {
    //             leftDieX = leftDieX + 5;
    //             leftDieY = leftDieY - 5;
    //             rightDieX = rightDieX + 5;
    //             rightDieY = rightDieY - 5;

    //             if (iterations == 0 || iterations == 3 || iterations == 6)
    //             {
    //                 leftDieSpriteSheetX = DICE_WIDTH * (((int) ((Math.random() * (MAX_NUMBER)) + MIN_NUMBER)) -1);
    //                 leftDieSpriteSheetY = 0;
    //                 rightDieSpriteSheetX = DICE_WIDTH * (((int) ((Math.random() * (MAX_NUMBER)) + MIN_NUMBER)) -1);
    //                 rightDieSpriteSheetY = 0;
    //             }
    //             else if (iterations == 1 || iterations == 4 || iterations == 7)
    //             {
    //                 leftDieSpriteSheetX = DICE_WIDTH * (((int) ((Math.random() * (MAX_NUMBER)) + MIN_NUMBER)) -1);
    //                 leftDieSpriteSheetY = 38;
    //                 rightDieSpriteSheetX = DICE_WIDTH * (((int) ((Math.random() * (MAX_NUMBER)) + MIN_NUMBER)) -1);
    //                 rightDieSpriteSheetY = 38;
    //             }
    //             else if (iterations == 2 || iterations == 5 || iterations == 8)
    //             {
    //                 leftDieSpriteSheetX = DICE_WIDTH * (((int) ((Math.random() * (MAX_NUMBER)) + MIN_NUMBER)) -1);
    //                 leftDieSpriteSheetY = 76;
    //                 rightDieSpriteSheetX = DICE_WIDTH * (((int) ((Math.random() * (MAX_NUMBER)) + MIN_NUMBER)) -1);
    //                 rightDieSpriteSheetY = 76;
    //             }
    //             else if (iterations == 9)
    //             {
    //                 leftDieSpriteSheetX = DICE_WIDTH * (leftDie - 1);
    //                 leftDieSpriteSheetY = 0;
    //                 rightDieSpriteSheetX = DICE_WIDTH * (rightDie - 1);
    //                 rightDieSpriteSheetY = 0;
    //             }

    //             iterations++;
    //         }
    //     }
    // }

    /**
    * Draws the dice sprites on screen.
    */
    public void draw(Graphics2D g2d) {
        g2d.drawImage(diceSpriteSheet, leftDieX, leftDieY, 
                      leftDieX + diceWidth, leftDieY + diceHeight, 
                      leftDieSpriteSheetX, leftDieSpriteSheetY, 
                      leftDieSpriteSheetX + diceWidth, leftDieSpriteSheetY + diceHeight, null);
        g2d.drawImage(diceSpriteSheet, rightDieX, rightDieY, 
                      rightDieX + diceWidth, rightDieY + diceHeight, 
                      rightDieSpriteSheetX, rightDieSpriteSheetY, 
                      rightDieSpriteSheetX + diceWidth, rightDieSpriteSheetY + diceHeight, null);
    }
}
