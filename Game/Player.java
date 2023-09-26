package Game;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import Data.GameStates;
import Data.SpaceData;
import Spaces.Space;

// Represents a player
public class Player extends JPanel implements ActionListener
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
    public ImageIcon playerImage;

    public Timer timer;
    
    public Player(GamePanel gamePanel, SpaceData spaceData, Dice dice, String name)
    {
        this.gamePanel = gamePanel;
        this.spaceData = spaceData;
        this.dice = dice;
        this.name = name;

        playerLabel = new JLabel();
        playerLabel.setBounds(x, y, PLAYER_WIDTH, PLAYER_HEIGHT);

        playerImage = new ImageIcon("testbox.png");
        playerLabel.setIcon(playerImage);

        gamePanel.add(playerLabel);
    }

    public void startTimer()
    {
        running = true;
        timer = new Timer(200, this);
        timer.start();
    }

    public void move()
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
        }
        else
        {
            GameStates.currentGameState = GameStates.SPACE_EVENT_STATE;
            gamePanel.running = false;            
            gamePanel.timer.stop();
            gamePanel.update();
        }
    }

    public void update()
    {

    }

    public void paintComponent(Graphics g) {

        // Converts the Graphics object parameter to a Graphics2D object
        Graphics2D g2d = (Graphics2D)g;

        g2d.fillRect(x, y, 30, 30);
    }

    public void draw(Graphics2D g2d)
    {
        // g2d.fillRect(x, y, 30, 30);
        // g2d.drawImage(image, x, y, null);
        playerLabel.setBounds(x, y, PLAYER_WIDTH, PLAYER_HEIGHT);

        g2d.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        g2d.drawString("$ " + String.valueOf(money), 80, 50);
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if (running)
        {
            move();
        }

        revalidate();
        repaint();
    }
}
