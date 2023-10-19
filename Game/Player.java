package game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import spaces.Space;
import data.GameStates;
import data.Settings;
import data.SpaceData;

/**
 * Represents a player in game.
 */
public class Player extends JPanel
{
    public GamePanel gamePanel;
    public SpaceData spaceData;
    public Dice dice;

    public static final int PLAYER_WIDTH = 30;
    public static final int PLAYER_HEIGHT = 30;

    public int[] coordinates = new int[2];
    public int x = 661;
    public int y = 661;
    public int currentSpaceNumber = 0;
    public int playerNumber;
    public String name;
    public int money = 1500;
    public ArrayList<Space> propertiesOwned = new ArrayList<Space>();
    public boolean running = false;

    public JLabel playerLabel;
    public Image playerImage;
    
    public Player(GamePanel gamePanel, SpaceData spaceData, Dice dice, int playerNumber, String name)
    {
        this.gamePanel = gamePanel;
        this.spaceData = spaceData;
        this.dice = dice;

        this.playerNumber = playerNumber;
        this.name = name;

        playerLabel = new JLabel();
        playerLabel.setBounds(x, y, PLAYER_WIDTH, PLAYER_HEIGHT);

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

        gamePanel.add(playerLabel);
    }

    public void move()
    {
        if (gamePanel.playerDelta >= Settings.PLAYER_MOVE_SPEED)
        {
            if (dice.result != 0)
            {
                dice.result--;

                if (currentSpaceNumber < (SpaceData.NUM_OF_SPACES - 1))
                {
                    currentSpaceNumber = currentSpaceNumber + 1;
                }
                else
                {
                    currentSpaceNumber = 0;
                }

                coordinates = spaceData.getSpaceCoordinates(currentSpaceNumber);
                x = coordinates[0];
                y = coordinates[1];

                checkifPassedGo();
            }
            else
            {
                GameStates.currentGameState = GameStates.SPACE_EVENT_STATE;
                checkifOnFreeParking();
                gamePanel.update();
            }
        }
    }

    public void checkifPassedGo()
    {
        if (currentSpaceNumber == 0)
        {
            money = money + Settings.SALARY;
        }
    }

    public void checkifOnFreeParking()
    {
        if (currentSpaceNumber == 20)
        {
            money = money + Settings.FREE_PARKING_BONUS;
        }
    }

    public void paintComponent(Graphics g) {

        // Converts the Graphics object parameter to a Graphics2D object
        Graphics2D g2d = (Graphics2D)g;

        g2d.fillRect(x, y, 30, 30);
    }

    public void draw(Graphics2D g2d)
    {
        g2d.drawImage(playerImage, x, y, PLAYER_WIDTH, PLAYER_HEIGHT, null);
    }
}
