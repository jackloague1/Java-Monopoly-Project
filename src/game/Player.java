package src.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.JLabel;
import src.data.GameStates;
import src.data.Settings;
import src.data.SpaceData;
import src.spaces.Space;

/**
 * Represents a player in game.
 */
public class Player {
    public final int playerWidth;
    public final int playerHeight;

    public GamePanel gamePanel;
    public SpaceData spaceData;
    public Ui ui;
    public Dice dice;

    public int[] coordinates;
    public int xcoordinate;
    public int ycoordinate;
    public int coordinateOffset;
    public int currentSpaceNumber;
    public int playerNumber;
    public String name;
    public int money;
    public boolean passedGo;
    public ArrayList<Space> propertiesOwned;

    public JLabel playerLabel;
    public Image token;
    
    /**
    * Constructor.
    */
    public Player(GamePanel gamePanel, SpaceData spaceData, Ui ui, Dice dice, int playerNumber, 
                  int coordinateOffset, String name, Image token) {
        this.gamePanel = gamePanel;
        this.spaceData = spaceData;
        this.ui = ui;
        this.dice = dice;

        this.playerNumber = playerNumber;

        this.coordinateOffset = coordinateOffset;
        
        this.name = name;
        this.token = token;

        playerWidth = 30;
        playerHeight = 30;

        playerLabel = new JLabel();
        playerLabel.setBounds(xcoordinate, ycoordinate, playerWidth, playerHeight);

        coordinates = new int[2];
        xcoordinate = 660;
        ycoordinate = 660;
        currentSpaceNumber = 0;
        money = 1500;
        passedGo = false;
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

                if (currentSpaceNumber < (spaceData.NUM_OF_SPACES - 1)) {
                    currentSpaceNumber = currentSpaceNumber + 1;
                } else {
                    currentSpaceNumber = 0;
                }

                coordinates = spaceData.getSpaceCoordinates(currentSpaceNumber, coordinateOffset);
                xcoordinate = coordinates[0];
                ycoordinate = coordinates[1];

                checkifPassedGo();
            } else {
                // if (passedGo == true) {
                //     passedGo = false;
                // }
                ui.managerButton.setForeground(Color.white);
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
        spaceData.getSpace(currentSpaceNumber);

        if (spaceData.currentSpaceType == "Go") {
            // passedGo = true;
            money = money + Settings.SALARY;
            ui.managerButton.setForeground(Color.white);

            GameStates.currentGameState = GameStates.SPACE_EVENT_STATE;
        }
    }

    /**
    * Checks if a player has landed on the Free Parking space.
    */
    public void checkifOnFreeParking() {
        spaceData.getSpace(currentSpaceNumber);

        if (spaceData.currentSpaceType == "Free Parking") {
            money = money + Settings.FREE_PARKING_BONUS;
        }
    }

    /**
    * Draws a given player token on screen.
    */
    public void draw(Graphics2D g2d) {
        g2d.drawImage(token, xcoordinate, ycoordinate, playerWidth, playerHeight, null);
    }
}
