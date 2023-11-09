package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * Represents the information box above the main game board that displays attributes of the
 * player, including their name, avatar (token icon), and their cash amount.
 */
public class PlayerInfoBox {
    public GamePanel gamePanel;
    public Player currentPlayer;

    public final Image playerInfoBoxBorder;
    
    /**
    * Constructor.
    */
    public PlayerInfoBox(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        playerInfoBoxBorder = new ImageIcon("images/Player-Info-Border.png").getImage();
    }

    /**
    * Sets the current player based on the current player number in GamePanel.
    */
    public void setCurrentPlayer() {
        currentPlayer = gamePanel.players.get(gamePanel.currentPlayerNumber - 1);
    }

    /**
    * Draws the current player's info box on screen.
    */
    public void draw(Graphics2D g2d) {
        g2d.setFont(new Font("Times New Roman", Font.PLAIN, 18));

        g2d.drawImage(playerInfoBoxBorder, 80, 0, null);
        HelperFunctions.drawText(g2d, currentPlayer.name, Color.black, 18, 122, 
                           2, 100, 30, false, true);
        g2d.drawImage(currentPlayer.playerImage, 82, 2, currentPlayer.playerWidth, 
                      currentPlayer.playerHeight, null);
        HelperFunctions.drawText(g2d, "$ " + String.valueOf(currentPlayer.money), Color.black, 
                                 18, 660, 2, 50, 30, false, true);
    }
}
