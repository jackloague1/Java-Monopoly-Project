package src.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import src.data.Fonts;

/**
 * Represents the information box above the main game board that displays attributes of the
 * player, including their name, avatar (token icon), and their cash amount.
 */
public class PlayerInfoBox {
    public GamePanel gamePanel;
    public Fonts fonts;
    public Player currentPlayer;

    public final Image playerInfoBoxBorder;
    
    /**
    * Constructor.
    */
    public PlayerInfoBox(GamePanel gamePanel, Fonts fonts) {
        this.gamePanel = gamePanel;
        this.fonts = fonts;
        playerInfoBoxBorder = new ImageIcon("images/Player-Info-Border.png").getImage();
    }

    /**
    * Sets the current player based on the current player number in GamePanel.
    */
    public void setCurrentPlayer() {
        currentPlayer = gamePanel.players.get(gamePanel.currentPlayerNumber - 1);
        System.out.print(currentPlayer.playerNumber);
    }

    /**
    * Draws the current player's info box on screen.
    */
    public void draw(Graphics2D g2d) {
        g2d.setFont(new Font("Times New Roman", Font.PLAIN, 18));

        g2d.drawImage(playerInfoBoxBorder, 79, 0, null);
        g2d.drawImage(currentPlayer.token, 81, 2, currentPlayer.playerWidth, 
                      currentPlayer.playerHeight, null);
        HelperFunctions.drawText(g2d, currentPlayer.name, fonts.pixeloidSans, Color.black, 18, 121, 
                           2, 100, 30, false, true);
        HelperFunctions.drawText(g2d, "$ " + String.valueOf(currentPlayer.money), 
                                 fonts.pixeloidSans, Color.black, 18, 629, 2, 50, 30, false, true);
    }
}
