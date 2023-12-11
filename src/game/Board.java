package src.game;

import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import src.data.GameStates;

/**
* Represents the game board.
*/
public class Board {
    Image boardImage;
    Image csufLogo;
    Image goToJailSpace;

    /**
    * Contructor.
    */
    Board() {
        boardImage = new ImageIcon("images/Board.png").getImage();
        csufLogo = new ImageIcon("images/csuf-logo.png").getImage();
        goToJailSpace = new ImageIcon("images/Go-To-Jail-Space.png").getImage();
    }

    /**
    * Draws the game board on screen.
    */
    public void draw(Graphics2D g2d) {
        g2d.drawImage(boardImage, 79, 79, null);

        g2d.drawImage(goToJailSpace, 635, 90, null);

        if (GameStates.currentGameState != GameStates.SPACE_EVENT_STATE) {
            g2d.drawImage(csufLogo, 300, 300, 200, 200, null);
        }
    }
    
}
