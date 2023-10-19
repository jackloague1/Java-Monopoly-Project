package game;

import java.awt.Font;
import java.awt.Graphics2D;

/**
 * Represents the information box above the main game board that displays attributes of the
 * player, including their name, avatar (token icon), and their cash amount.
 */
public class PlayerInfoBox 
{
    public GamePanel gamePanel;
    public Player currentPlayer;

    public PlayerInfoBox(GamePanel gamePanel)
    {
        this.gamePanel = gamePanel;
    }

    public void setCurrentPlayer()
    {
        currentPlayer = gamePanel.players.get(gamePanel.currentPlayerNumber-1);
    }

    public void draw(Graphics2D g2d)
    {
        g2d.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        g2d.drawString("$ " + String.valueOf(currentPlayer.money), 160, 60);
        g2d.drawString(currentPlayer.name, 260, 60);
        g2d.drawImage(currentPlayer.playerImage, 360, 40, currentPlayer.PLAYER_WIDTH, currentPlayer.PLAYER_HEIGHT, null);
    }
}
