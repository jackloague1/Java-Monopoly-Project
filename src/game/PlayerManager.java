package src.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;


import javax.swing.ImageIcon;


import src.data.Fonts;
import src.data.GameStates;
import src.data.SpaceData;

/**
 * Represents the GUI for the player manager.
 */
public class PlayerManager {
    public GamePanel gamePanel;
    public Fonts fonts;
    public SpaceData spaceData;
    public SaveLoad saveLoad;
    public Dice dice;
    public ArrayList<Profile> profiles;
    public ArrayList<Player> players;

    Image mortgageButtonImage;
    Image tradingButtonImage;
    Image buildingButtonImage;

    /**
    * Constructor.
    */
    public PlayerManager(GamePanel gamePanel, Fonts fonts, SpaceData spaceData, SaveLoad saveLoad, 
                         Dice dice, ArrayList<Profile> profiles, ArrayList<Player> players) {
        this.gamePanel = gamePanel;
        this.fonts = fonts;
        this.spaceData = spaceData;
        this.saveLoad = saveLoad;
        this.dice = dice;
        this.profiles = profiles;
        this.players = players;

        mortgageButtonImage = new ImageIcon("images/Player-Manager-Mortgage-Image.png").getImage();
        tradingButtonImage = new ImageIcon("images/Player-Manager-Trading-Image.png").getImage();
        buildingButtonImage = new ImageIcon("images/Player-Manager-Building-Image.png").getImage();
    }

    /**
    * Draws the player manager on screen.
    */
    public void draw(Graphics2D g2d) {
        if (GameStates.currentGameState == GameStates.MANAGER_MENU_STATE) {
            // Screen title.
            HelperFunctions.drawText(g2d, "Player Manager", fonts.pixeloidSans, Color.white, 
                                     48, 300, 25, 200, 50, true, true);

            // Mortgaging/unmortgaging button box.
            HelperFunctions.drawBlankBox(g2d, Color.black, Color.gray, 100, 125, 600, 100, 5);
            g2d.drawImage(mortgageButtonImage, 610, 135, null);

            // Trading button box.
            HelperFunctions.drawBlankBox(g2d, Color.black, Color.gray, 100, 275, 600, 100, 5);
            g2d.drawImage(tradingButtonImage, 610, 285, null);

            // Building button box.
            HelperFunctions.drawBlankBox(g2d, Color.black, Color.gray, 100, 425, 600, 100, 5);
            g2d.drawImage(buildingButtonImage, 610, 435, null);

            // Declare bankruptcy button box.
            HelperFunctions.drawBlankBox(g2d, Color.black, Color.gray, 100, 575, 600, 100, 5);

            // Back button box.
            HelperFunctions.drawBlankBox(g2d, Color.black, Color.gray, 20, 750, 100, 30, 2);
        } else if (GameStates.currentGameState == GameStates.TRADING_PLAYER_SELECT_STATE) {
            // Screen title.
            HelperFunctions.drawText(g2d, "Trading", fonts.pixeloidSans, Color.white, 
                                     48, 300, 25, 200, 50, true, true);
            // Back button box.
            HelperFunctions.drawBlankBox(g2d, Color.black, Color.gray, 20, 750, 100, 30, 2);
        }
    }
}
