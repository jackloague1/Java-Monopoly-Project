package game;

import data.GameStates;
import data.Settings;
import data.SpaceData;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import spaces.Space;

/**
 * Represents a player in game.
 */
public class Player {
    public final int playerWidth;
    public final int playerHeight;

    public GamePanel gamePanel;
    public SpaceData spaceData;
    public Dice dice;

    public int[] coordinates;
    public int xcoordinate;
    public int ycoordinate;
    public int currentSpaceNumber;
    public int playerNumber;
    public String name;
    public int money;
    public ArrayList<Space> propertiesOwned;

    public JLabel playerLabel;
    public Image playerImage;
    
    /**
    * Constructor method.
    */
    public Player(GamePanel gamePanel, SpaceData spaceData, Dice dice, int playerNumber, 
                  String name) {
        this.gamePanel = gamePanel;
        this.spaceData = spaceData;
        this.dice = dice;

        this.playerNumber = playerNumber;
        this.name = name;

        playerWidth = 30;
        playerHeight = 30;

        playerLabel = new JLabel();
        playerLabel.setBounds(xcoordinate, ycoordinate, playerWidth, playerHeight);

        if (playerNumber == 1)
        {
            playerImage = new ImageIcon("testbox.png").getImage();
        }
        else if (playerNumber == 2)
        {
            playerImage = new ImageIcon("testbox-2.png").getImage();
        }
        else if (playerNumber == 3)
        {
            playerImage = new ImageIcon("testbox-3.png").getImage();     
        }
        else if (playerNumber == 4)
        {
            playerImage = new ImageIcon("testbox-4.png").getImage();
        }

        coordinates = new int[2];
        xcoordinate = 661;
        ycoordinate = 661;
        currentSpaceNumber = 30;
        money = 1500;
        propertiesOwned = new ArrayList<Space>();

        gamePanel.add(playerLabel);
    }

    /**
    * Moves a player the amount of spaces displayed on the dice.
    */
    public void move() {
        if (gamePanel.playerDelta >= Settings.PLAYER_MOVE_SPEED) {
            if (dice.result != 0) {
                dice.result--;

                if (currentSpaceNumber < (SpaceData.NUM_OF_SPACES - 1)) {
                    currentSpaceNumber = currentSpaceNumber + 1;
                } else {
                    currentSpaceNumber = 0;
                }

                coordinates = spaceData.getSpaceCoordinates(currentSpaceNumber);
                xcoordinate = coordinates[0];
                ycoordinate = coordinates[1];

                checkifPassedGo();
            } else {
                GameStates.currentGameState = GameStates.SPACE_EVENT_STATE;
                checkifOnFreeParking();
                gamePanel.update();
            }
        }
    }

    /**
    * Checks if a player passed or has landed on the Go space.
    */
    public void checkifPassedGo() {
        SpaceData.getSpace(currentSpaceNumber);

        if (SpaceData.currentSpaceType == "Go") {
            money = money + Settings.SALARY;

            GameStates.currentGameState = GameStates.SPACE_EVENT_STATE;
        }
    }

    public void checkifOnFreeParking()
    {
        SpaceData.getSpace(currentSpaceNumber);

        if (SpaceData.currentSpaceType == "Free Parking")
        {
            money = money + Settings.FREE_PARKING_BONUS;
        }
    }

    /**
    * Draws a given player token on screen.
    */
    public void draw(Graphics2D g2d) {
        g2d.drawImage(playerImage, xcoordinate, ycoordinate, playerWidth, playerHeight, null);
    }
}
